package ar.utn.reportwizard.view;

import ar.utn.reportwizard.util.ConsoleUtil;
import java.util.Scanner;

public class MainView {

    public static void run() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            ConsoleUtil.clearConsole();

            System.out.println("INCIDENT WIZARD 1.0\n\n");
            System.out.println(
                    """
1. AREA DE RECURSOS HUMANOS
2. AREA COMERCIAL
3. MESA DE AYUDA
4. TECNICOS
0. SALIR""");

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
                new RRHHView();
            } else if (option == 2) {
                ConsoleUtil.clearConsole();
                new CommercialAreaView();
            } else if (option == 3) {
                ConsoleUtil.clearConsole();
                new HelpDeskView();
            } else if (option == 4) {
                ConsoleUtil.clearConsole();
                new TechnicianView();
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
