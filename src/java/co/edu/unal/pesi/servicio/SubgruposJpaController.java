/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.servicio;

import co.edu.unal.pesi.modelo.Procesos;
import co.edu.unal.pesi.modelo.Subgrupos;
import co.edu.unal.pesi.servicio.exceptions.IllegalOrphanException;
import co.edu.unal.pesi.servicio.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wilvec
 */
public class SubgruposJpaController implements Serializable {

    public SubgruposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Subgrupos subgrupos) {
        if (subgrupos.getProcesosList() == null) {
            subgrupos.setProcesosList(new ArrayList<Procesos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Procesos> attachedProcesosList = new ArrayList<Procesos>();
            for (Procesos procesosListProcesosToAttach : subgrupos.getProcesosList()) {
                procesosListProcesosToAttach = em.getReference(procesosListProcesosToAttach.getClass(), procesosListProcesosToAttach.getId());
                attachedProcesosList.add(procesosListProcesosToAttach);
            }
            subgrupos.setProcesosList(attachedProcesosList);
            em.persist(subgrupos);
            for (Procesos procesosListProcesos : subgrupos.getProcesosList()) {
                Subgrupos oldSubgruposIdOfProcesosListProcesos = procesosListProcesos.getSubgruposId();
                procesosListProcesos.setSubgruposId(subgrupos);
                procesosListProcesos = em.merge(procesosListProcesos);
                if (oldSubgruposIdOfProcesosListProcesos != null) {
                    oldSubgruposIdOfProcesosListProcesos.getProcesosList().remove(procesosListProcesos);
                    oldSubgruposIdOfProcesosListProcesos = em.merge(oldSubgruposIdOfProcesosListProcesos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subgrupos subgrupos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subgrupos persistentSubgrupos = em.find(Subgrupos.class, subgrupos.getId());
            List<Procesos> procesosListOld = persistentSubgrupos.getProcesosList();
            List<Procesos> procesosListNew = subgrupos.getProcesosList();
            List<String> illegalOrphanMessages = null;
            for (Procesos procesosListOldProcesos : procesosListOld) {
                if (!procesosListNew.contains(procesosListOldProcesos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procesos " + procesosListOldProcesos + " since its subgruposId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Procesos> attachedProcesosListNew = new ArrayList<Procesos>();
            for (Procesos procesosListNewProcesosToAttach : procesosListNew) {
                procesosListNewProcesosToAttach = em.getReference(procesosListNewProcesosToAttach.getClass(), procesosListNewProcesosToAttach.getId());
                attachedProcesosListNew.add(procesosListNewProcesosToAttach);
            }
            procesosListNew = attachedProcesosListNew;
            subgrupos.setProcesosList(procesosListNew);
            subgrupos = em.merge(subgrupos);
            for (Procesos procesosListNewProcesos : procesosListNew) {
                if (!procesosListOld.contains(procesosListNewProcesos)) {
                    Subgrupos oldSubgruposIdOfProcesosListNewProcesos = procesosListNewProcesos.getSubgruposId();
                    procesosListNewProcesos.setSubgruposId(subgrupos);
                    procesosListNewProcesos = em.merge(procesosListNewProcesos);
                    if (oldSubgruposIdOfProcesosListNewProcesos != null && !oldSubgruposIdOfProcesosListNewProcesos.equals(subgrupos)) {
                        oldSubgruposIdOfProcesosListNewProcesos.getProcesosList().remove(procesosListNewProcesos);
                        oldSubgruposIdOfProcesosListNewProcesos = em.merge(oldSubgruposIdOfProcesosListNewProcesos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = subgrupos.getId();
                if (findSubgrupos(id) == null) {
                    throw new NonexistentEntityException("The subgrupos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subgrupos subgrupos;
            try {
                subgrupos = em.getReference(Subgrupos.class, id);
                subgrupos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subgrupos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Procesos> procesosListOrphanCheck = subgrupos.getProcesosList();
            for (Procesos procesosListOrphanCheckProcesos : procesosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subgrupos (" + subgrupos + ") cannot be destroyed since the Procesos " + procesosListOrphanCheckProcesos + " in its procesosList field has a non-nullable subgruposId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(subgrupos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Subgrupos> findSubgruposEntities() {
        return findSubgruposEntities(true, -1, -1);
    }

    public List<Subgrupos> findSubgruposEntities(int maxResults, int firstResult) {
        return findSubgruposEntities(false, maxResults, firstResult);
    }

    private List<Subgrupos> findSubgruposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subgrupos.class));
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

    public Subgrupos findSubgrupos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subgrupos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubgruposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subgrupos> rt = cq.from(Subgrupos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
