package ar.utn.reportwizard.service;

import ar.utn.reportwizard.dao.impl.SpecialtyDAOImpl;
import ar.utn.reportwizard.model.Specialty;
import java.util.List;

public class SpecialtyService {

    private final SpecialtyDAOImpl dao;

    public SpecialtyService() {
        this.dao = new SpecialtyDAOImpl();
    }

    public Boolean create(Specialty specialty) {
        return dao.create(specialty);
    }

    public List<Specialty> findAll() {
        return dao.findAll();
    }

    public Specialty findById(Long id) {
        return dao.findById(id);
    }

}
