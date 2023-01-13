/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ujian.demo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ujian.demo.exceptions.NonexistentEntityException;
import ujian.demo.exceptions.PreexistingEntityException;

/**
 *
 * @author Arhab Zuhan Assaifuddin 20200140062
 */
public class SimJpaController implements Serializable {

    public SimJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ujian_demo_jar_0.0.1-SNAPSHOTPU");
    
     public SimJpaController() {}

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sim sim) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sim);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSim(sim.getId()) != null) {
                throw new PreexistingEntityException("Sim " + sim + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sim sim) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sim = em.merge(sim);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sim.getId();
                if (findSim(id) == null) {
                    throw new NonexistentEntityException("The sim with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sim sim;
            try {
                sim = em.getReference(Sim.class, id);
                sim.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sim with id " + id + " no longer exists.", enfe);
            }
            em.remove(sim);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sim> findSimEntities() {
        return findSimEntities(true, -1, -1);
    }

    public List<Sim> findSimEntities(int maxResults, int firstResult) {
        return findSimEntities(false, maxResults, firstResult);
    }

    private List<Sim> findSimEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sim.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Sim findSim(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sim.class, id);
        } finally {
            em.close();
        }
    }

    public int getSimCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sim> rt = cq.from(Sim.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
