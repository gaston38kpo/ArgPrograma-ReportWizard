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
}
