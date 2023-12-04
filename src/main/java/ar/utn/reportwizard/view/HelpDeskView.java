package ar.utn.reportwizard.view;

import ar.utn.reportwizard.controller.CustomerController;
import ar.utn.reportwizard.controller.IncidentController;
import ar.utn.reportwizard.controller.SpecialtyController;
import ar.utn.reportwizard.model.Customer;
import ar.utn.reportwizard.model.Service;
import ar.utn.reportwizard.util.ConsoleUtil;
import ar.utn.reportwizard.util.Utils;
import java.util.List;
import java.util.Scanner;

public class HelpDeskView {

    private IncidentController incidentController;
    private CustomerController customerController;
    private Scanner scanner;

    public HelpDeskView() {
        this.incidentController = new IncidentController();
        this.customerController = new CustomerController();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
        this.menu();
    }

    private void menu() {

        while (true) {
            ConsoleUtil.clearConsole();

            System.out.println("MESA DE AYUDA");

            Customer customer = this.customerController.findCustomerByAttribute();
            if (customer == null) {
                break;
            }
            this.incidentController.create(customer);

            System.out.print("\n[Presione ENTER para continuar] ");
            scanner.nextLine();
            System.out.println("\n\n");
        }
    }
}
