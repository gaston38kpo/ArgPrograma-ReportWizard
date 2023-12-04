package ar.utn.reportwizard.service;

import ar.utn.reportwizard.dao.impl.ProblemSpecialtyDAOImpl;
import ar.utn.reportwizard.model.ProblemSpecialty;
import ar.utn.reportwizard.model.Technician;
import java.util.List;

public class ProblemSpecialtyService {

    private ProblemSpecialtyDAOImpl dao;

    public ProblemSpecialtyService() {
        this.dao = new ProblemSpecialtyDAOImpl();
    }

    public void create(ProblemSpecialty newProblemSpecialty) {
        this.dao.create(newProblemSpecialty);
    }

    public List<ProblemSpecialty> findByTechnicianId(Technician technician) {
        return this.dao.findByTechnicianId(technician);
    }

    public void update(ProblemSpecialty problemSpecialty) {
        this.dao.update(problemSpecialty);
    }

}
