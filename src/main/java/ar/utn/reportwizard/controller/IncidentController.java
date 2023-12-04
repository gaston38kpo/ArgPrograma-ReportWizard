package ar.utn.reportwizard.controller;

import ar.utn.reportwizard.model.Customer;
import ar.utn.reportwizard.model.Incident;
import ar.utn.reportwizard.model.Problem;
import ar.utn.reportwizard.model.ProblemSpecialty;
import ar.utn.reportwizard.model.Service;
import ar.utn.reportwizard.model.Specialty;
import ar.utn.reportwizard.model.Technician;
import ar.utn.reportwizard.service.ProblemSpecialtyService;
import ar.utn.reportwizard.service.SpecialtyService;
import ar.utn.reportwizard.service.TechnicianService;
import ar.utn.reportwizard.util.JPAUtil;
import ar.utn.reportwizard.util.Utils;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;

public class IncidentController {

    private final ProblemSpecialtyService problemSpecialtyService;

    private final Scanner scanner;

    public IncidentController() {
        this.problemSpecialtyService = new ProblemSpecialtyService();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void create(Customer customer) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Customer managedCustomer = em.merge(customer);

        List<Service> services = new ArrayList<>(managedCustomer.getServices());

        System.out.println("Por cual de estos Servicios desea reportar un incidente?");
        System.out.println(services);

        Service service = null;
        do {
            final Long idInputService = Utils.getLongInput(">_ ");

            service = services.stream()
                    .filter(s -> (s.getId() == idInputService))
                    .findFirst()
                    .orElse(null);

            if (service == null) {
                System.out.println("!!!El servicio no existe, intente nuevamente.");
            }
        } while (service == null);

        System.out.println("Reportando incidente de: " + managedCustomer.getCorporate_name() + ". Para el servicio: " + service + ".");

        Incident newIncident = new Incident();
        newIncident.setCustomer(managedCustomer);
        newIncident.setService(service);
        newIncident.setDescription(Utils.getNonEmptyInput("Descripcion del Incidente: "));

        Boolean isComplexInput = null;
        while (isComplexInput == null) {
            String optionIsComplex = Utils.getNonEmptyInput("Es complejo?\n1. SI\n2. NO\n>_ ");
            if (optionIsComplex.toUpperCase().equals("1")) {
                isComplexInput = Boolean.TRUE;
                break;
            } else if (optionIsComplex.toUpperCase().equals("2")) {
                isComplexInput = Boolean.FALSE;
                break;
            } else {
                System.out.println("!!!Entrada invalida, intentelo nuevamente.");
            }
        }

        newIncident.setIs_complex(isComplexInput);

        Problem newProblem = new Problem();
        newProblem.setDescription(Utils.getNonEmptyInput("Tipo de Problema: "));
        Incident managedNewIncident = em.merge(newIncident);
        newProblem.setIncident(managedNewIncident);
        Problem managedNewProblem = em.merge(newProblem);

        ProblemSpecialty newProblemSpecialty = new ProblemSpecialty();
        newProblemSpecialty.setProblem(managedNewProblem);

        List<Specialty> specialties = new ArrayList<>(new SpecialtyService().findAll());
        Specialty managedSpecialty = null;
        do {
            System.out.println("Indique la especialidad atribuida a este Problema.");
            System.out.println(specialties);
            final Long idInputSpecialty = Utils.getLongInput("ID: ");

            Specialty specialty = new SpecialtyService().findById(idInputSpecialty);
            managedSpecialty = em.merge(specialty);

            if (specialty == null) {
                System.out.println("!!!La especialidad no existe, intente nuevamente.");
            }
        } while (managedSpecialty == null);

        newProblemSpecialty.setSpecialty(managedSpecialty);

        List<Technician> technicians = new ArrayList<>(new TechnicianService().findAll());

        List<Technician> filteredTechniciansBySpecialties = technicians.stream()
                .filter(t -> specialties.stream().anyMatch(s -> t.getSpecialties().contains(s)))
                .collect(Collectors.toList());

        Technician technician = null;
        do {
            System.out.println("Tecnicos Capacitados.");
            System.out.println(filteredTechniciansBySpecialties);
            final Long idInputTechnician = Utils.getLongInput("ID: ");

            technician = filteredTechniciansBySpecialties.stream()
                    .filter(s -> (s.getId() == idInputTechnician))
                    .findFirst()
                    .orElse(null);

            if (technician == null) {
                System.out.println("!!!El servicio no existe, intente nuevamente.");
            }
        } while (technician == null);

        Technician managedTechnician = em.merge(technician);
        newProblemSpecialty.setTechnician(managedTechnician);
        em.getTransaction().commit();

        LocalTime estimatedHours = LocalTime.MIN;
        Date maxResoltucionTime = newProblemSpecialty.getSpecialty().getMax_resolution_time();
        System.out.println("1-" + newProblemSpecialty.getSpecialty());
        System.out.println("2-" + newProblemSpecialty.getSpecialty().getMax_resolution_time());
        Boolean isComplex = newProblemSpecialty.getProblem().getIncident().getIs_complex();

        estimatedHours.plusHours(maxResoltucionTime.getHours());
        if (isComplex) {
            estimatedHours.plusHours(2);
        }

        newProblemSpecialty.getProblem().getIncident().setEstimated_hours(estimatedHours);

        System.out.println("Tiempo estimado de Resolucion del problema: " + estimatedHours);
        System.out.println("\n1. Confirmar\n2. Cancelar");

        while (true) {
            try {
                int option = scanner.nextInt();

                if (option == 1) {
                    break;
                } else if (option == 2) {
                    return;
                } else if (option > 2 || option < 1) {
                    System.out.println("!!!Opcion incorrecta, intentelo de nuevo");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("!!!Entrada incorrecta, solo se admiten numeros");
            }
        }

        problemSpecialtyService.create(newProblemSpecialty);

        System.out.print("\n[Se agrego correctamente el problema, el tecnico sera notificado]");

    }

}
