package ar.utn.reportwizard.service;

import ar.utn.reportwizard.dao.impl.ServiceDAOImpl;
import ar.utn.reportwizard.model.Service;
import java.util.List;

public class ServiceService {

    private ServiceDAOImpl dao;

    public ServiceService() {
        this.dao = new ServiceDAOImpl();
    }

    public List<Service> findAll() {
        return dao.findAll();
    }
}
