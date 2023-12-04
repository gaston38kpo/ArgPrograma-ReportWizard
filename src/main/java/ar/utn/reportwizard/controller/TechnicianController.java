package ar.utn.reportwizard.controller;

import ar.utn.reportwizard.model.Problem;
import ar.utn.reportwizard.model.ProblemSpecialty;
import ar.utn.reportwizard.model.Specialty;
import ar.utn.reportwizard.model.Technician;
import ar.utn.reportwizard.service.ProblemSpecialtyService;
import ar.utn.reportwizard.service.SpecialtyService;
import ar.utn.reportwizard.service.TechnicianService;
import ar.utn.reportwizard.util.Utils;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TechnicianController {

    private final TechnicianService technicianService;
    private final SpecialtyService specialtyService;
    private final ProblemSpecialtyService problemSpecialtyService;

    private final Scanner scanner;

    public TechnicianController() {
        this.technicianService = new TechnicianService();
        this.specialtyService = new SpecialtyService();
        this.problemSpecialtyService = new ProblemSpecialtyService();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void create() {
        Technician newTechnician = new Technician();

        System.out.println("\nSistema de Creacion de Tecnicos, por favor ingrese los datos a continuacion:\n\n-DATOS TECNICO-");

        newTechnician.setName(Utils.getNonEmptyInput("Nombre: "));

        String contactMethod;
        while (true) {
            System.out.print("Método de contacto preferido:\n\t1. WhatsApp\n\t2. Email\nOpcion >_ ");

            int opcion = 0;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
            }

            if (opcion == 1) {
                contactMethod = "whatsapp";
                break;
            } else if (opcion == 2) {
                contactMethod = "email";
                break;
            } else {
                System.out.println("!!!Opcion incorrecta, vuelva a intentarlo.");
                scanner.next();
                System.out.println("");
            }
        }
        newTechnician.setPreferred_contact_method(contactMethod);

        System.out.println("\n-DATOS ESPECIALIDADES-");
        List<Specialty> specialties = this.specialtyService.findAll();

        while (true) {
            Boolean createManually = Boolean.FALSE;

            try {
                System.out.println("Ingrese: #ID para agregarsela. C para crearle una nueva especialidad. X para finalizar: ");
                System.out.print(specialties);
                String idSelectedStr = Utils.getNonEmptyInput(">_ ");
                if (idSelectedStr.toUpperCase().equals("X")) {
                    System.out.println("!!!Cancelado por el Usuario");
                    return;
                }
                if (idSelectedStr.toUpperCase().equals("C")) {
                    createManually = Boolean.TRUE;
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

                    newSpecialty.setTitle(Utils.getNonEmptyInput("Título: "));

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
                    newSpecialty.setMax_resolution_time(new Date(Time.valueOf(max_resolution_time).getTime()));

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

        Boolean hasBeenCreated = this.technicianService.create(newTechnician);

        if (hasBeenCreated) {

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
        List<Technician> technicians = new ArrayList<>(this.technicianService.findAll());
        for (Technician t : technicians) {
            if (!t.getIsDeleted()) {
                System.out.print("\nTecnico: " + t + "\nEspecialidad/es: ");
                for (Specialty s : t.getSpecialties()) {
                    System.out.print("|| " + s + " ");
                }
                System.out.println("||");
            }
        }
    }

    public Technician findById() {
        System.out.println("-BUSQUEDA DE TECNICOS POR ID-");
        while (true) {
            Long id = Utils.getLongInput("ID: ");

            Technician technician = this.technicianService.findById(id);
            if (technician == null) {
                System.out.println("El tecnico con id: " + id + "No existe, intente nuevamente.");
            } else {
                System.out.print("\nTecnico: " + technician + "\nEspecialidad/es: ");
                for (Specialty s : technician.getSpecialties()) {
                    System.out.print("|| " + s + " ");
                }
                System.out.println("||");

                System.out.print("\nDesea buscar otro?\n\t1. SI\n\t2. NO\nOpcion >_ ");
                try {
                    int option = scanner.nextInt();

                    if (option == 2) {
                        return technician;
                    } else if (option > 2 || option < 1) {
                        System.out.println("!!!Opcion incorrecta, intentelo de nuevo");
                        scanner.nextLine();
                    }
                } catch (Exception e) {
                    System.out.println("!!!Entrada incorrecta, solo se admiten numeros");
                }
            }
        }
    }

    public void deleteById() {
        System.out.println("-ELIMINAR TECNICO POR ID-");
        while (true) {
            Long id = Utils.getLongInput("ID: ");

            Technician technician = this.technicianService.findById(id);
            if (technician == null) {
                System.out.println("!!!El tecnico con id: " + id + " no existe, intente nuevamente.\n");
            } else if (technician.getIsDeleted()) {
                System.out.println("!!!Este tecnico ya se encuentra eliminado.");
            } else {
                Boolean hasBeenDeleted = this.technicianService.logicalDeleteById(id);
                System.out.print("\nTecnico Eliminado: " + technician + "\nEspecialidad/es: ");
                for (Specialty s : technician.getSpecialties()) {
                    System.out.print("|| " + s + " ");
                }
                System.out.println("||");

                System.out.print("\nDesea eliminar a otro?\n\t1. SI\n\t2. NO\nOpcion >_ ");
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
        }
    }

    public void update() {
        System.out.println("-EDITAR TECNICO POR ID-");

        while (true) {
            this.findAll();
            System.out.print("\nTecnico a editar\n");
            Long id = Utils.getLongInput("ID: ");

            Technician technician = this.technicianService.findById(id);
            if (technician == null) {
                System.out.println("!!!El tecnico con id: " + id + " no existe, intente nuevamente.\n");
            } else if (technician.getIsDeleted()) {
                System.out.println("!!!Este tecnico se encuentra eliminado.");
            } else {
                System.out.println("\nUsted eligio: " + technician + "\n");
                System.out.println("Ingrese el nuevo valor, para ignorar presionar ENTER");
                System.out.print("Nombre: " + technician.getName() + " -> ");
                String newName = scanner.nextLine();
                if (!newName.isBlank()) {
                    technician.setName(newName);
                }

                String contactMethod;
                while (true) {
                    System.out.println("Método de contacto preferido: " + technician.getPreferred_contact_method().toUpperCase());
                    System.out.print("Nuevo:\n\t1. WhatsApp\n\t2. Email\nOpcion >_ ");

                    int opcion = 0;
                    try {
                        opcion = scanner.nextInt();
                    } catch (Exception e) {
                    }

                    if (opcion == 1) {
                        contactMethod = "whatsapp";
                        break;
                    } else if (opcion == 2) {
                        contactMethod = "email";
                        break;
                    } else {
                        System.out.println("!!!Opcion incorrecta, vuelva a intentarlo.");
                        scanner.next();
                        System.out.println("");
                    }
                }
                technician.setPreferred_contact_method(contactMethod);

                this.technicianService.update(technician);

                System.out.print("\nDesea editar a otro?\n\t1. SI\n\t2. NO\nOpcion >_ ");
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
        }
    }

    public void findProblemsByTechnician(Technician technician) {
        System.out.println("Bienvenido " + technician.getName());
        Technician managedTechnician = this.technicianService.findById(technician.getId());

        List<ProblemSpecialty> problemSpecialtyList = this.problemSpecialtyService.findByTechnicianId(managedTechnician);
        System.out.println("Problemas no resueltos: ");
        problemSpecialtyList.forEach(each -> {
            if (!each.getProblem().getIs_solved()) {
                System.out.println("\tProblema ID: " + each.getProblem().getId() + ". Descripcion: " + each.getProblem().getDescription());
                System.out.println("\t(Pertenece al Incidente ID: " + each.getProblem().getIncident().getId() + ". Descipcion: " + each.getProblem().getIncident().getDescription() + ")\n");
            }
        });

        System.out.println("Indique el ID del problema para marcarlo como resuelto. (0 para cancelar)");

        Long id = Utils.getLongInput("ID: ");
        if (id == 0) {
            return;
        }
        ProblemSpecialty selected = problemSpecialtyList.stream().filter(each -> each.getId() == id).findFirst().orElse(null);
        selected.getProblem().setIs_solved(Boolean.TRUE);
        int timeItWasSolved = Utils.getIntInput("Cuantas horas le llevo resolverlo?: ");
        selected.getProblem().setTime_it_was_solved(LocalTime.of(timeItWasSolved, 0));

        this.problemSpecialtyService.update(selected);

        System.out.println("[RESUELTO SATISFACTORIAMENTE, EL CLIENTE SERA NOTIFICADO EN BREVE]");

    }

}
