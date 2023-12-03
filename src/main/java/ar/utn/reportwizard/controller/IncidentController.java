package ar.utn.reportwizard.controller;

import ar.utn.reportwizard.model.Customer;
import ar.utn.reportwizard.model.Incident;
import ar.utn.reportwizard.model.Service;
import ar.utn.reportwizard.service.CustomerService;
import ar.utn.reportwizard.util.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncidentController {

    private final CustomerService customerService;

    private final Scanner scanner;

    public IncidentController() {
        this.customerService = new CustomerService();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void create(Customer customer) {
        List<Service> services = new ArrayList<>(customer.getServices());

        System.out.println("Por cual de estos Servicios desea reportar un incidente?");
        System.out.println(services);

        Service service = null;
        do {
            final Long idSelected = Utils.getLongInput(">_ ");

            service = services.stream()
                    .filter(s -> (s.getId() == idSelected))
                    .findFirst()
                    .orElse(null);

            if (service == null) {
                System.out.println("!!!El servicio no existe, intente nuevamente.");
            }
        } while (service == null);

        System.out.println("Reportando incidente de: " + customer.getCorporate_name() + ". Para el servicio: " + service + ".");

        Incident newIncident = new Incident();

        newIncident.setDescription(Utils.getNonEmptyInput("Descripcion del Incidente: "));
        ProblemController problemController = new ProblemController();

    }

}
