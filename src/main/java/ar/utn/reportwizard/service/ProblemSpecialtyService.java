package ar.utn.reportwizard.service;

import ar.utn.reportwizard.dao.impl.ProblemSpecialtyDAOImpl;
import ar.utn.reportwizard.model.ProblemSpecialty;

public class ProblemSpecialtyService {

    private ProblemSpecialtyDAOImpl dao;

    public ProblemSpecialtyService() {
        this.dao = new ProblemSpecialtyDAOImpl();
    }

    public void create(ProblemSpecialty newProblemSpecialty) {
        dao.create(newProblemSpecialty);
    }

}
