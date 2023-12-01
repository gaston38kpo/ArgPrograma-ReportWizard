package ar.utn.reportwizard.util;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ConsoleUtil {

    public static void clearConsole() {

        try {
            Robot limpiar = new Robot();
            limpiar.keyPress(KeyEvent.VK_CONTROL);
            limpiar.keyPress(KeyEvent.VK_L);
            limpiar.keyRelease(KeyEvent.VK_CONTROL);
            limpiar.keyRelease(KeyEvent.VK_L);

        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla" + e.getMessage());
        }

        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla" + e.getMessage());
        }
        
        try {
            String sistemaOperativo = System.getProperty("os.name");
            ArrayList<String> comando = new ArrayList<>();
            if (sistemaOperativo.contains("Windows")) {
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");

            } else {
                comando.add("clear");
            }

            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso = pb.inheritIO().start();
            iniciarProceso.waitFor();

        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla" + e.getMessage());
        }

    }
}
