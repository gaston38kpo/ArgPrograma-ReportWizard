package ar.utn.reportwizard.dao.impl;

import ar.utn.reportwizard.dao.SpecialtyDAO;
import ar.utn.reportwizard.model.Specialty;
import ar.utn.reportwizard.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class SpecialtyDAOImpl implements Serializable, SpecialtyDAO {

    @Override
    public List<Specialty> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Specialty> specialtys;

        try {
            TypedQuery<Specialty> createQuery = em.createQuery("SELECT s FROM Specialty s", Specialty.class);
            specialtys = createQuery.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return specialtys;
    }

    @Override
    public Specialty findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Specialty specialty;

        try {
            specialty = em.find(Specialty.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return specialty;
    }

    @Override
    public Boolean create(Specialty specialty) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenCreated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.persist(specialty);
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
    public Boolean update(Specialty specialty) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenUpdated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(specialty);
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
            Specialty specialty = em.find(Specialty.class, id);
            em.remove(specialty);
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
            Specialty specialty = em.find(Specialty.class, id);
            specialty.setIsDeleted(Boolean.TRUE);
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
            Specialty specialty = em.find(Specialty.class, id);
            specialty.setIsDeleted(Boolean.FALSE);
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
