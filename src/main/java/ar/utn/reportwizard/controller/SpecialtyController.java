package ar.utn.reportwizard.controller;

import ar.utn.reportwizard.model.Specialty;
import ar.utn.reportwizard.service.SpecialtyService;
import java.util.List;
import java.util.Scanner;

public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final Scanner scanner;

    public SpecialtyController() {
        this.specialtyService = new SpecialtyService();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public List<Specialty> findAll() {
        return specialtyService.findAll();
    }

}
