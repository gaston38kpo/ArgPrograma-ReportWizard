package ar.utn.reportwizard.view;

import ar.utn.reportwizard.controller.TechnicianController;
import ar.utn.reportwizard.util.ConsoleUtil;
import java.util.Scanner;

public class RRHHView {

    private TechnicianController controller;
    private Scanner scanner;

    public RRHHView() {
        this.controller = new TechnicianController();
        this.scanner = new Scanner(System.in);
    }

    public void menu() {

        while (true) {
            ConsoleUtil.clearConsole();

            System.out.println("\n\nCRUD Tecnicos");
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
