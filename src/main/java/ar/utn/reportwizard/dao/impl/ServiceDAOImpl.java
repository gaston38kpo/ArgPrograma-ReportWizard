package ar.utn.reportwizard.dao.impl;

import ar.utn.reportwizard.dao.ServiceDAO;
import ar.utn.reportwizard.model.Service;
import ar.utn.reportwizard.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ServiceDAOImpl implements Serializable, ServiceDAO {

    @Override
    public List<Service> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Service> services;

        try {
            TypedQuery<Service> createQuery = em.createQuery("SELECT DISTINCT s FROM Service s", Service.class);
            services = createQuery.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return services;
    }

    @Override
    public Service findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Service service;

        try {
            service = em.find(Service.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return service;
    }

    @Override
    public Boolean create(Service service) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenCreated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.persist(service);
            em.getTransaction().commit();
            hasBeenCreated = Boolean.TRUE;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return hasBeenCreated;
    }

    @Override
    public Boolean update(Service service) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenUpdated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(service);
            em.getTransaction().commit();
            hasBeenUpdated = Boolean.TRUE;
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
            Service service = em.find(Service.class, id);
            em.remove(service);
            em.getTransaction().commit();
            hasBeenDeleted = Boolean.TRUE;
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
            Service service = em.find(Service.class, id);
            service.setIsDeleted(Boolean.TRUE);
            em.getTransaction().commit();
            hasBeenDeleted = Boolean.TRUE;
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
            Service service = em.find(Service.class, id);
            service.setIsDeleted(Boolean.FALSE);
            em.getTransaction().commit();
            hasBeenRestored = Boolean.TRUE;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return hasBeenRestored;
    }
}
