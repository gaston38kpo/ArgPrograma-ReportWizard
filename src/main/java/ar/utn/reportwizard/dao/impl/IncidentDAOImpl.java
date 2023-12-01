package ar.utn.reportwizard.dao.impl;

import ar.utn.reportwizard.dao.IncidentDAO;
import ar.utn.reportwizard.model.Incident;
import ar.utn.reportwizard.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class IncidentDAOImpl implements Serializable, IncidentDAO {

    @Override
    public List<Incident> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Incident> incidents;

        try {
            TypedQuery<Incident> createQuery = em.createQuery("SELECT i FROM Incident i JOIN FETCH i.customer JOIN FETCH i.service", Incident.class);
            incidents = createQuery.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return incidents;
    }

    @Override
    public Incident findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Incident incident;

        try {
            incident = em.find(Incident.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return incident;
    }

    @Override
    public Boolean create(Incident incident) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenCreated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.persist(incident);
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
    public Boolean update(Incident incident) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenUpdated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(incident);
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
            Incident incident = em.find(Incident.class, id);
            em.remove(incident);
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
            Incident incident = em.find(Incident.class, id);
            incident.setIsDeleted(Boolean.TRUE);
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
            Incident incident = em.find(Incident.class, id);
            incident.setIsDeleted(Boolean.FALSE);
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
