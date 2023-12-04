package ar.utn.reportwizard.dao.impl;

import ar.utn.reportwizard.dao.ProblemSpecialtyDAO;
import ar.utn.reportwizard.model.ProblemSpecialty;
import ar.utn.reportwizard.model.Technician;
import ar.utn.reportwizard.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProblemSpecialtyDAOImpl implements Serializable, ProblemSpecialtyDAO {

    @Override
    public List<ProblemSpecialty> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<ProblemSpecialty> problemSpecialtys;

        try {
            TypedQuery<ProblemSpecialty> createQuery = em.createQuery("SELECT DISTINCT ps FROM ProblemSpecialty ps JOIN FETCH ps.problem JOIN FETCH ps.specialty JOIN FETCH ps.technician", ProblemSpecialty.class);
            problemSpecialtys = createQuery.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return problemSpecialtys;
    }

    @Override
    public ProblemSpecialty findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        ProblemSpecialty problemSpecialty;

        try {
            problemSpecialty = em.find(ProblemSpecialty.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return problemSpecialty;
    }

    @Override
    public Boolean create(ProblemSpecialty problemSpecialty) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenCreated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(problemSpecialty);
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
    public Boolean update(ProblemSpecialty problemSpecialty) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenUpdated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(problemSpecialty);
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
            ProblemSpecialty problemSpecialty = em.find(ProblemSpecialty.class, id);
            em.remove(problemSpecialty);
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
            ProblemSpecialty problemSpecialty = em.find(ProblemSpecialty.class, id);
            problemSpecialty.setIsDeleted(Boolean.TRUE);
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
            ProblemSpecialty problemSpecialty = em.find(ProblemSpecialty.class, id);
            problemSpecialty.setIsDeleted(Boolean.FALSE);
            em.getTransaction().commit();
            hasBeenRestored = Boolean.TRUE;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return hasBeenRestored;
    }

    @Override
    public List<ProblemSpecialty> findByTechnicianId(Technician technician) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<ProblemSpecialty> problemSpecialtyList = null;

        try {
            TypedQuery<ProblemSpecialty> query = em.createQuery("FROM ProblemSpecialty ps WHERE ps.technician = :technician", ProblemSpecialty.class);
            query.setParameter("technician", technician);
            problemSpecialtyList = query.getResultList();
        } catch (Exception e) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return problemSpecialtyList;
    }
}
