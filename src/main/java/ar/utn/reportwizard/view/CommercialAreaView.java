package ar.utn.reportwizard.view;

import ar.utn.reportwizard.controller.CustomerController;
import ar.utn.reportwizard.util.ConsoleUtil;
import java.util.Scanner;

public class CommercialAreaView {

    private CustomerController controller;
    private Scanner scanner;

    public CommercialAreaView() {
        this.controller = new CustomerController();
        this.scanner = new Scanner(System.in);
        this.menu();
    }

    private void menu() {
        while (true) {
            ConsoleUtil.clearConsole();

            System.out.println("AREA COMERCIAL\n\nCRUD Clientes");
            System.out.println(
                    """
1. Crear
2. Ver todos
3. Buscar por ID
4. Eliminar por ID
0. Volver al menu principal""");

            System.out.print("\nIngrese una opción: ");
            int option = -1;
            try {
                String optionStr = scanner.next();
                option = Integer.parseInt(optionStr);
            } catch (Exception e) {
                System.out.println("!!!Solo se permiten numeros!. ");
            }

            if (option == 0) {
                break;
            } else if (option == 1) {
                ConsoleUtil.clearConsole();
                this.controller.create();
            } else if (option == 2) {
                ConsoleUtil.clearConsole();
                this.controller.findAll();
            } else if (option == 3) {
                ConsoleUtil.clearConsole();
                this.controller.findById();
            } else if (option == 4) {
                ConsoleUtil.clearConsole();
                this.controller.deleteById();
            } else if (option != 0) {
                System.out.println("Opción inválida, intente de nuevo.");
            }

            System.out.print("\n[Presione ENTER para continuar] ");
            scanner.nextLine();
            scanner.nextLine();
            System.out.println("\n\n");
        }
    }
}
