package ar.utn.reportwizard.view;

import ar.utn.reportwizard.controller.TechnicianController;
import ar.utn.reportwizard.model.Technician;
import ar.utn.reportwizard.util.ConsoleUtil;

public class TechnicianView {

    private final TechnicianController controller;

    public TechnicianView() {
        this.controller = new TechnicianController();
        this.menu();
    }

    private void menu() {
        ConsoleUtil.clearConsole();

        System.out.println("""
AREA DE TECNICOS
                    
                    MIS INCIDENTES""");

        Technician technician = this.controller.findById();
        this.controller.findProblemsByTechnician(technician);

    }
}
