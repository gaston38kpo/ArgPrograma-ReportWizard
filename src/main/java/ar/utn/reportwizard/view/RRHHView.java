package ar.utn.reportwizard.view;

import ar.utn.reportwizard.controller.TechnicianController;
import ar.utn.reportwizard.util.ConsoleUtil;
import ar.utn.reportwizard.util.Utils;
import java.util.Scanner;

public class RRHHView {

    private TechnicianController controller;
    private Scanner scanner;

    public RRHHView() {
        this.controller = new TechnicianController();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
        this.menu();
    }

    private void menu() {

        while (true) {
            ConsoleUtil.clearConsole();

            System.out.println("""
AREA RRHH
                    
                    CRUD Tecnicos
1. Crear
2. Ver todos
3. Buscar por ID
4. Eliminar por ID
5. Editar
0. Volver al menu principal""");

            System.out.print("\nIngrese una opción: ");
            int option = -1;
            try {
                String optionStr = Utils.getNonEmptyInput(">_ ");
                option = Integer.parseInt(optionStr);
            } catch (Exception e) {
                System.out.println("!!!Solo se permiten numeros!. ");
            }

            if (option == 0) {
                break;
            }

            ConsoleUtil.clearConsole();
            if (option == 1) {
                this.controller.create();
            } else if (option == 2) {
                this.controller.findAll();
            } else if (option == 3) {
                this.controller.findById();
            } else if (option == 4) {
                this.controller.deleteById();
            } else if (option == 5) {
                this.controller.update();
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
