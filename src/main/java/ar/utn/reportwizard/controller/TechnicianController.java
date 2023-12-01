package ar.utn.reportwizard.controller;

import ar.utn.reportwizard.model.Specialty;
import ar.utn.reportwizard.model.Technician;
import ar.utn.reportwizard.service.SpecialtyService;
import ar.utn.reportwizard.service.TechnicianService;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TechnicianController {

    private final TechnicianService tService;
    private final SpecialtyService sService;

    private final Scanner scanner;

    public TechnicianController() {
        this.tService = new TechnicianService();
        this.sService = new SpecialtyService();
        this.scanner = new Scanner(System.in);
    }

    public void create() {
        Technician newTechnician = new Technician();

        System.out.println("\nSistema de Creacion de Tecnicos, por favor ingrese los datos a continuacion:\n\nDATOS DEL TECNICO.");

        System.out.print("Ingrese el nombre del técnico: ");
        String name = scanner.nextLine();
        newTechnician.setName(name);

        String referred_contact_method;
        while (true) {
            System.out.print("Ingrese el método de contacto preferido:\n1. whatsapp\n2. email\n>_ ");

            int opcion = 0;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
            }

            if (opcion == 1) {
                referred_contact_method = "whatsapp";
                break;
            } else if (opcion == 2) {
                referred_contact_method = "email";
                break;
            } else {
                System.out.println("!!!Opcion incorrecta, vuelva a intentarlo.");
                scanner.next();
                System.out.println("");
            }
        }
        newTechnician.setPreferred_contact_method(referred_contact_method);

        System.out.println("\nESPECIALIDADES.");
        while (true) {
            Specialty newSpecialty = new Specialty();

            System.out.print("Título de la especialidad: ");
            String title = scanner.next();
            newSpecialty.setTitle(title);

            LocalTime max_resolution_time = null;
            while (true) {
                try {
                    System.out.print("Tiempo máximo de resolución en HORAS(Max. 23): ");
                    Integer hours = scanner.nextInt();
                    if (hours < 1 || hours > 23) {
                        throw new Exception();
                    }
                    max_resolution_time = LocalTime.parse("00:00:00", DateTimeFormatter.ISO_LOCAL_TIME).plusHours(hours);
                    System.out.println(max_resolution_time);
                    break;
                } catch (Exception e) {
                    System.out.print("!!!La hora maxima debe ser un numero, entre 1 y 23.\nPresiones ENTER para continuar: ");
                    scanner.nextLine();
                    scanner.nextLine();
                }
            }
            newSpecialty.setMax_resolution_time(max_resolution_time);

            newTechnician.getSpecialties().add(newSpecialty);

            System.out.print("Se agrego correctamente.\n\nDesea añadir otra especialidad?\n1. SI\n2. NO\n>_ ");
            int opcion = scanner.nextInt();

            System.out.println(newSpecialty);
            if (opcion == 1) {
            } else if (opcion == 2) {
                break;
            } else {
                System.out.println("!!!Opcion incorrecta, intentelo de nuevo");
                scanner.nextLine();
            }
        }

        Boolean technicianHasBeenCreated = this.tService.create(newTechnician);

        if (technicianHasBeenCreated) {
            System.out.println("TECNICO CREADO: " + newTechnician);
            System.out.println("ESPECIALIDADES ATRIBUIDAS: " + newTechnician.getSpecialties());
            System.out.print("El Tecnico se agrego correctamente.\nPrsione ENTER para continuar. ");
            scanner.nextLine();
            scanner.nextLine();
        } else {
            System.out.print("El Tecnico NO se agrego correctamente.\nPrsione ENTER para continuar. ");
            scanner.nextLine();
            scanner.nextLine();
        }
    }

    public void findAll() {
        System.out.println("Lista de Tecnicos");
        List<Technician> technicians = new ArrayList<>(this.tService.findAll());
        for (Technician technician : technicians) {
            System.out.println(technician + " " + technician.getSpecialties() + "\n");
        }
        System.out.print("Presione ENTER para continuar. ");
        scanner.nextLine();
        scanner.nextLine();
    }

    public void findById() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteById() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
