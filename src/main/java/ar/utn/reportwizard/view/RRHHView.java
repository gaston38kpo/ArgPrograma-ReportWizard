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
5. Volver al menu principal""");

            System.out.print("Ingrese una opción: ");
            int opcion = 0;
            try {
                opcion = scanner.nextInt();
            } catch (Exception e) {
            }

            if (opcion == 1) {
                ConsoleUtil.clearConsole();
                this.controller.create();
            } else if (opcion == 2) {
                ConsoleUtil.clearConsole();
                this.controller.findAll();
            } else if (opcion == 3) {
                ConsoleUtil.clearConsole();
                this.controller.findById();
            } else if (opcion == 4) {
                ConsoleUtil.clearConsole();
                this.controller.deleteById();
            } else if (opcion == 5) {
                break;
            } else {
                if (opcion > 5 || opcion < 1) {
                    System.out.println("Opción inválida, presione Enter para intentarlo de nuevo... ");
                } else {
                    System.out.println("Solo se permiten numeros!, presione Enter para intentarlo de nuevo... ");
                }
            }

            scanner.nextLine();
            scanner.nextLine();
            System.out.println("\n\n");
        }

    }

}
