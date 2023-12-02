package ar.utn.reportwizard.controller;

import ar.utn.reportwizard.model.Customer;
import ar.utn.reportwizard.model.Service;
import ar.utn.reportwizard.service.CustomerService;
import ar.utn.reportwizard.service.ServiceService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private final CustomerService customerService;
    private final ServiceService serviceService;

    private final Scanner scanner;

    public CustomerController() {
        this.customerService = new CustomerService();
        this.serviceService = new ServiceService();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void create() {
        Customer newCustomer = new Customer();
        System.out.println("\nSistema de Creacion de Clientes, por favor ingrese los datos a continuacion:\n\n-DATOS CLIENTE-");

        while (true) {
            System.out.print("Razon Social: ");
            String corporateName = scanner.nextLine();

            if (corporateName.isBlank()) {
                System.out.println("!!!No puede dejar este campo vacio");
            } else {
                newCustomer.setCorporate_name(corporateName);
                break;
            }
        }

        while (true) {
            System.out.print("CUIT: ");
            String cuit = scanner.nextLine();

            if (cuit.isBlank()) {
                System.out.println("!!!No puede dejar este campo vacio");
            } else {
                newCustomer.setCuit(cuit);
                break;
            }
        }

        System.out.println("\n-DATOS SERVICIOS-");
        List<Service> services = this.serviceService.findAll();

        while (true) {
            Boolean createManually = Boolean.FALSE;

            try {
                System.out.println("Ingrese: #ID para agregar. C para crearle un nuevo servicio. X para finalizar: ");
                System.out.print(services + "\n>_ ");
                String idSelectedStr = scanner.next();
                if (idSelectedStr.toUpperCase().equals("X")) {
                    break;
                }

                if (idSelectedStr.isBlank()) {
                    System.out.println("!!!No puede dejar este campo vacio");
                } else if (idSelectedStr.toUpperCase().equals("C")) {
                    createManually = Boolean.TRUE;
                } else {
                    int idSelected = Integer.parseInt(idSelectedStr);

                    Boolean idInService = newCustomer.getServices().stream().anyMatch(each -> (each.getId() == idSelected));

                    if (idInService) {
                        System.out.println("!!!El servicio ya se encuentra en el cliente\n");
                    } else {
                        Boolean idExist = services.stream().anyMatch(each -> (each.getId() == idSelected));

                        if (idExist) {
                            services.stream()
                                    .filter(service -> (service.getId() == idSelected))
                                    .forEach(service -> newCustomer.getServices().add(service));
                        }

                        System.out.print("\n[Se agrego correctamente el servicio]\n\nDesea añadir otro?\n\t1. SI\n\t2. NO\nOpcion >_ ");
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
                    Service newService = new Service();

                    System.out.print("Título: ");
                    String title = scanner.nextLine();
                    newService.setTitle(title);

                    newCustomer.getServices().add(newService);

                    System.out.print("\n[Se agrego correctamente el servicio: " + newService.getTitle() + "]\n\nDesea añadir otro?\n\t1. SI\n\t2. NO\nOpcion >_ ");
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

        Boolean hasBeenCreated = this.customerService.create(newCustomer);

        if (hasBeenCreated) {
            System.out.println("[CLIENTE CREADO]\n\t" + newCustomer);
            System.out.print("[SERVICIOS ATRIBUIDOS]\n\t");
            for (Service s : newCustomer.getServices()) {
                System.out.print("|| " + s + " ");
            }
            System.out.println("||\n");

            System.out.println("[El Cliente se agrego correctamente]");
        } else {
            System.out.println("[Hubo un error, el Cliente NO se agrego]");
        }
    }

    public void findAll() {

        System.out.println("Lista de Clientes");
        List<Customer> customers = new ArrayList<>(this.customerService.findAll());
        for (Customer c : customers) {
            if (!c.getIsDeleted()) {
                System.out.print("\nCliente: " + c + "\nServicio/s: ");
                for (Service s : c.getServices()) {
                    System.out.print("|| " + s + " ");
                }
                System.out.println("||");
            }
        }
    }

    public void findById() {
        System.out.println("-BUSQUEDA DE CLIENTES POR ID-");
        while (true) {
            Long id = 0L;
            while (true) {
                try {
                    System.out.print("ID: ");
                    String idStr = scanner.next();
                    id = Long.valueOf(idStr);
                    break;
                } catch (Exception e) {
                    System.out.println("!!!Solo se aceptan numeros, intentelo nuevamente.");
                }
            }

            Customer customer = this.customerService.findById(id);
            if (customer == null) {
                System.out.println("El cliente con id: " + id + "No existe, intente nuevamente.");
            } else {
                System.out.print("\nCliente: " + customer + "\nServicio/s: ");
                for (Service s : customer.getServices()) {
                    System.out.print("|| " + s + " ");
                }
                System.out.println("||");

                System.out.print("\nDesea buscar otro?\n\t1. SI\n\t2. NO\nOpcion >_ ");
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

    public void deleteById() {
        System.out.println("-ELIMINAR CLIENTE POR ID-");
        while (true) {
            Long id = 0L;
            while (true) {
                try {
                    System.out.print("ID: ");
                    String idStr = scanner.next();
                    id = Long.parseLong(idStr);
                    break;
                } catch (Exception e) {
                    System.out.println("!!!Solo se aceptan numeros, intentelo nuevamente.\n");
                }
            }

            Customer customer = this.customerService.findById(id);
            if (customer == null) {
                System.out.println("!!!El cliente con id: " + id + " no existe, intente nuevamente.\n");
            } else if (customer.getIsDeleted()) {
                System.out.println("!!!Este cliente ya se encuentra eliminado.");
            } else {
                Boolean hasBeenDeleted = this.customerService.logicalDeleteById(id);
                System.out.print("\nCliente Eliminado: " + customer + "\nEspecialidad/es: ");
                for (Service s : customer.getServices()) {
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
        System.out.println("-EDITAR CLIENTE POR ID-");

        while (true) {
            this.findAll();
            System.out.print("\nCliente a editar\n");
            Long id = 0L;
            while (true) {
                try {
                    System.out.print("ID: ");
                    String idStr = scanner.nextLine();
                    id = Long.parseLong(idStr);
                    break;
                } catch (Exception e) {
                    System.out.println("!!!Solo se aceptan numeros, intentelo nuevamente.\n");
                }
            }
            Customer customer = this.customerService.findById(id);
            if (customer == null) {
                System.out.println("!!!El tecnico con id: " + id + " no existe, intente nuevamente.\n");
            } else if (customer.getIsDeleted()) {
                System.out.println("!!!Este cliente se encuentra eliminado.");
            } else {
                System.out.println("\nUsted eligio: " + customer + "\n");
                System.out.println("Ingrese el nuevo valor, para ignorar presionar ENTER");

                System.out.print("Razon Social: " + customer.getCorporate_name() + " -> ");
                String newCorporateName = scanner.nextLine();
                if (!newCorporateName.isBlank()) {
                    customer.setCorporate_name(newCorporateName);
                }

                System.out.print("CUIT: " + customer.getCuit() + " -> ");
                String newCuit = scanner.nextLine();
                if (!newCuit.isBlank()) {
                    customer.setCuit(newCuit);
                }

                this.customerService.update(customer);

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

}
