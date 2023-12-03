package ar.utn.reportwizard.service;

import ar.utn.reportwizard.dao.impl.CustomerDAOImpl;
import ar.utn.reportwizard.model.Customer;
import java.util.List;

public class CustomerService {

    private CustomerDAOImpl dao;

    public CustomerService() {
        this.dao = new CustomerDAOImpl();
    }

    public List<Customer> findAll() {
        return dao.findAll();
    }

    public Boolean create(Customer newCustomer) {
        return dao.create(newCustomer);
    }

    public Customer findById(Long id) {
        return dao.findById(id);
    }

    public Boolean logicalDeleteById(Long id) {
        return dao.logicalDeleteById(id);
    }

    public Boolean update(Customer customer) {
        return dao.update(customer);
    }

    public Customer findByCuit(String cuit) {
        return dao.findByCuit(cuit);
    }

    public Customer findByCorporateName(String corporateName) {
        return dao.findByCorporateName(corporateName);
    }

}
