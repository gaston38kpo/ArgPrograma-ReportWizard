package ar.utn.reportwizard.dao.impl;

import ar.utn.reportwizard.dao.ProblemDAO;
import ar.utn.reportwizard.model.Problem;
import ar.utn.reportwizard.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProblemDAOImpl implements Serializable, ProblemDAO {

   @Override
    public List<Problem> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Problem> problems;

        try {
            TypedQuery<Problem> createQuery = em.createQuery("SELECT DISTINCT p FROM Problem p JOIN FETCH p.incident JOIN FETCH p.specialties", Problem.class);
            problems = createQuery.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return problems;
    }

    @Override
    public Problem findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Problem problem;

        try {
            problem = em.find(Problem.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return problem;
    }

    @Override
    public Boolean create(Problem problem) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenCreated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.persist(problem);
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
    public Boolean update(Problem problem) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Boolean hasBeenUpdated = Boolean.FALSE;

        try {
            em.getTransaction().begin();
            em.merge(problem);
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
            Problem problem = em.find(Problem.class, id);
            em.remove(problem);
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
            Problem problem = em.find(Problem.class, id);
            problem.setIsDeleted(Boolean.TRUE);
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
            Problem problem = em.find(Problem.class, id);
            problem.setIsDeleted(Boolean.FALSE);
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
