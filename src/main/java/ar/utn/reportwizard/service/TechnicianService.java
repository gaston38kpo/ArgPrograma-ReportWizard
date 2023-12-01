package ar.utn.reportwizard.service;

import ar.utn.reportwizard.dao.impl.TechnicianDAOImpl;
import ar.utn.reportwizard.model.Technician;
import java.util.List;

public class TechnicianService {

    private final TechnicianDAOImpl dao;

    public TechnicianService() {
        this.dao = new TechnicianDAOImpl();
    }

    public Boolean create(Technician newTechnician) {
        return dao.create(newTechnician);
    }

    public List<Technician> findAll() {
        return dao.findAll();
    }

}
