package ar.utn.reportwizard.view;

import ar.utn.reportwizard.util.ConsoleUtil;
import ar.utn.reportwizard.util.Utils;
import java.util.Scanner;

public class MainView {

    public static void run() {

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        while (true) {
            ConsoleUtil.clearConsole();

            System.out.println("""
                               
 ____  ____  ____  _____  ____  ____    _    _  ____  ____    __    ____  ____  
(  _ \\( ___)(  _ \\(  _  )(  _ \\(_  _)  ( \\/\\/ )(_  _)(_   )  /__\\  (  _ \\(  _ \\ 
 )   / )__)  )___/ )(_)(  )   /  )(     )    (  _)(_  / /_  /(__)\\  )   / )(_) )
(_)\\_)(____)(__)  (_____)(_)\\_) (__)   (__/\\__)(____)(____)(__)(__)(_)\\_)(____/ 
                                                                                 V1.0
1. AREA DE RECURSOS HUMANOS
2. AREA COMERCIAL
3. MESA DE AYUDA
4. TECNICOS
0. SALIR""");

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
            System.out.println("\n\n");
        }
    }
}
