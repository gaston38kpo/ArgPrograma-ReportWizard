package ar.utn.reportwizard.service;

import ar.utn.reportwizard.dao.impl.CustomerDAOImpl;

public class CustomerService {

    private CustomerDAOImpl dao;

    public CustomerService() {
        this.dao = new CustomerDAOImpl();
    }
    
    

}
