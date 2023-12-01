package ar.utn.reportwizard.controller;

import ar.utn.reportwizard.model.Specialty;
import ar.utn.reportwizard.model.Technician;
import ar.utn.reportwizard.service.SpecialtyService;
import ar.utn.reportwizard.service.TechnicianService;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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

        System.out.println("\nSistema de Creacion de Tecnicos, por favor ingrese los datos a continuacion:\n\n-DATOS TECNICO-");

        System.out.print("Nombre: ");
        String name = scanner.next();
        newTechnician.setName(name);

        String referred_contact_method;
        while (true) {
            System.out.print("Método de contacto preferido:\n\t1. WhatsApp\n\t2. Email\nOpcion >_ ");

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

        System.out.println("\n-DATOS ESPECIALIDADES-");
        List<Specialty> findAll = this.sService.findAll();
        
        List<Specialty> specialties = new ArrayList<>(this.sService.findAll());

        while (true) {
            Boolean createManually = Boolean.FALSE;

            try {
                System.out.println("Ingrese: #ID para agregarsela. C para crearle una nueva especialidad. X para finalizar: ");
                System.out.print(specialties + "\n>_ ");
                String idSelectedStr = scanner.next();
                if (idSelectedStr.toUpperCase().equals("C")) {
                    createManually = Boolean.TRUE;
                } else if (idSelectedStr.toUpperCase().equals("X")) {
                    break;
                } else {
                    int idSelected = Integer.parseInt(idSelectedStr);

                    Boolean idInTechnician = newTechnician.getSpecialties().stream().anyMatch(each -> (each.getId() == idSelected));

                    if (idInTechnician) {
                        System.out.println("!!!La especialidad ya se encuentra en el tecnico\n");
                    } else {
                        Boolean idExist = specialties.stream().anyMatch(each -> (each.getId() == idSelected));

                        if (idExist) {
                            specialties.stream()
                                    .filter(specialty -> (specialty.getId() == idSelected))
                                    .forEach(specialty -> newTechnician.getSpecialties().add(specialty));
                        }

                        System.out.print("\n[Se agrego correctamente la especialidad]\n\nDesea añadir otra?\n\t1. SI\n\t2. NO\nOpcion >_ ");
                        Boolean endLoop = Boolean.FALSE;

                        while (true) {
                            try {
                                int option = scanner.nextInt();

                                if (option == 1) {
                                    break;
                                } else if (option == 2) {
                                    endLoop = Boolean.TRUE;
                                    break;
                                } else if (option > 2 || option < 1) {
                                    System.out.println("!!!Opcion incorrecta, intentelo de nuevo");
                                    scanner.nextLine();
                                }
                            } catch (Exception e) {
                                System.out.println("!!!Entrada incorrecta, solo se admiten numeros");
                            }
                        }

                        if (endLoop) {
                            break;
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println("!!!Error, intentelo de nuevo");
                scanner.nextLine();
            }

            if (createManually) {
                System.out.println("\n--CREACION--");
                while (true) {
                    Specialty newSpecialty = new Specialty();

                    System.out.print("Título: ");
                    String title = scanner.next();
                    newSpecialty.setTitle(title);

                    LocalTime max_resolution_time = null;
                    while (true) {
                        try {
                            System.out.print("Tiempo máximo de resolución en HORAS(Min. 1 - Max. 23): ");
                            Integer hours = scanner.nextInt();
                            if (hours < 1 || hours > 23) {
                                throw new Exception();
                            }
                            max_resolution_time = LocalTime.parse("00:00:00", DateTimeFormatter.ISO_LOCAL_TIME).plusHours(hours);
                            break;
                        } catch (Exception e) {
                            System.out.print("!!!La hora maxima debe ser un numero, entre 1 y 23.\n\nPresiones ENTER para continuar: ");
                            scanner.nextLine();
                            scanner.nextLine();
                        }
                    }
                    newSpecialty.setMax_resolution_time(max_resolution_time);

                    newTechnician.getSpecialties().add(newSpecialty);

                    System.out.print("\n[Se agrego correctamente la especialidad: " + newSpecialty.getTitle() + "]\n\nDesea añadir otra?\n\t1. SI\n\t2. NO\nOpcion >_ ");
                    try {
                        int option = scanner.nextInt();

                        if (option == 2) {
                            break;
                        } else if (option > 2 || option < 1) {
                            System.out.println("!!!Opcion incorrecta, intentelo de nuevo");
                            scanner.nextLine();
                        }
                    } catch (Exception e) {
                        System.out.println("!!!Entrada incorrecta, solo se admiten numeros");
                    }
                }
                break;
            }

        }

        Boolean technicianHasBeenCreated = this.tService.create(newTechnician);

        if (technicianHasBeenCreated) {

            System.out.println("[TECNICO CREADO]\n\t" + newTechnician);
            System.out.print("[ESPECIALIDADES ATRIBUIDAS]\n\t");
            for (Specialty s : newTechnician.getSpecialties()) {
                System.out.print("|| " + s + " ");
            }
            System.out.println("||\n");

            System.out.println("[El Tecnico se agrego correctamente]");
        } else {
            System.out.println("[Hubo un error, el Tecnico NO se agrego]");
        }
    }

    public void findAll() {
        System.out.println("Lista de Tecnicos");
        List<Technician> technicians = new ArrayList<>(this.tService.findAll());
        for (Technician t : technicians) {
            System.out.print("\nTecnico: " + t + "\nEspecialidad/es: ");
            for (Specialty s : t.getSpecialties()) {
                System.out.print("|| " + s + " ");
            }
            System.out.println("||");
        }
    }

    public void findById() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteById() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
