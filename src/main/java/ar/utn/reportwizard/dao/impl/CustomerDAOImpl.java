package ar.utn.reportwizard.dao.impl;

import ar.utn.reportwizard.dao.CustomerDAO;
import ar.utn.reportwizard.model.Customer;
import ar.utn.reportwizard.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CustomerDAOImpl implements Serializable, CustomerDAO {

    @Override
    public List<Customer> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Customer> customers = null;

        try {
            TypedQuery<Customer> createQuery = em.createQuery("SELECT DISTINCT c FROM Customer c JOIN FETCH c.services", Customer.class);
            customers = createQuery.getResultList();
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return customers;
    }

    @Override
    public Customer findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Customer customer = null;

        try {
            customer = em.find(Customer.class, id);
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return customer;
    }

    @Override
    public Boolean create(Customer customer) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenCreated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
            hasBeenCreated = Boolean.TRUE;
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return hasBeenCreated;
    }

    @Override
    public Boolean update(Customer customer) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenUpdated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
            hasBeenUpdated = Boolean.TRUE;
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return hasBeenUpdated;
    }

    @Override
    public Boolean deleteById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenDeleted = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            em.remove(customer);
            em.getTransaction().commit();
            hasBeenDeleted = Boolean.TRUE;
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return hasBeenDeleted;
    }

    @Override
    public Boolean logicalDeleteById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenDeleted = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            customer.setIsDeleted(Boolean.TRUE);
            em.getTransaction().commit();
            hasBeenDeleted = Boolean.TRUE;
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return hasBeenDeleted;
    }

    @Override
    public Boolean logicalRestoreById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenRestored = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            customer.setIsDeleted(Boolean.FALSE);
            em.getTransaction().commit();
            hasBeenRestored = Boolean.TRUE;
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return hasBeenRestored;
    }

    @Override
    public Customer findByCuit(String cuit) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Customer customer = new Customer();

        try {
            TypedQuery<Customer> query = em.createQuery("FROM Customer WHERE cuit = :cuit", Customer.class);
            query.setParameter("cuit", cuit);
            customer = query.getSingleResult();
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return customer;
    }

    @Override
    public Customer findByCorporateName(String corporateName) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Customer customer = new Customer();

        try {
            TypedQuery<Customer> query = em.createQuery("FROM Customer WHERE corporate_name = :corporateName", Customer.class);
            query.setParameter("corporateName", corporateName);
            customer = query.getSingleResult();
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return customer;
    }

}
