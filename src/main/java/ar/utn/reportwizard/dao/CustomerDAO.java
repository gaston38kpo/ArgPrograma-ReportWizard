package ar.utn.reportwizard.dao;

import ar.utn.reportwizard.model.Customer;

public interface CustomerDAO extends DAO<Customer> {

    /**
     * Obtiene un registro por su cuit de la tabla Customer
     *
     * @param cuit
     * @return Customer
     */
    Customer findByCuit(String cuit);
}
