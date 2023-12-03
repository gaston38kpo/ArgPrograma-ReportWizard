package ar.utn.reportwizard.util;

import java.util.Scanner;

public class Utils {

    public static String getNonEmptyInput(String prompt) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (input.isBlank()) {
                System.out.println("!!!No puede dejar este campo vacio");
            } else {
                return input;
            }
        }
    }

    public static Long getLongInput(String prompt) {
        while (true) {
            try {
                Long number = Long.valueOf(getNonEmptyInput(prompt));
                return number;
            } catch (Exception e) {
                System.out.println("!!!Solo se aceptan numeros, intentelo nuevamente.\n");
            }
        }
    }

    public static int getIntInput(String prompt) {
        while (true) {
            try {
                int number = Integer.parseInt(getNonEmptyInput(prompt));
                return number;
            } catch (Exception e) {
                System.out.println("!!!Solo se aceptan numeros, intentelo nuevamente.\n");
            }
        }
    }

}
