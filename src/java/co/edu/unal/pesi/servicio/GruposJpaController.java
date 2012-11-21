/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.servicio;

import co.edu.unal.pesi.modelo.Grupos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unal.pesi.modelo.Subgrupos;
import co.edu.unal.pesi.servicio.exceptions.IllegalOrphanException;
import co.edu.unal.pesi.servicio.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wilvec
 */
public class GruposJpaController implements Serializable {

    public GruposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupos grupos) {
        if (grupos.getSubgruposList() == null) {
            grupos.setSubgruposList(new ArrayList<Subgrupos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Subgrupos> attachedSubgruposList = new ArrayList<Subgrupos>();
            for (Subgrupos subgruposListSubgruposToAttach : grupos.getSubgruposList()) {
                subgruposListSubgruposToAttach = em.getReference(subgruposListSubgruposToAttach.getClass(), subgruposListSubgruposToAttach.getId());
                attachedSubgruposList.add(subgruposListSubgruposToAttach);
            }
            grupos.setSubgruposList(attachedSubgruposList);
            em.persist(grupos);
            for (Subgrupos subgruposListSubgrupos : grupos.getSubgruposList()) {
                Grupos oldGruposIdOfSubgruposListSubgrupos = subgruposListSubgrupos.getGruposId();
                subgruposListSubgrupos.setGruposId(grupos);
                subgruposListSubgrupos = em.merge(subgruposListSubgrupos);
                if (oldGruposIdOfSubgruposListSubgrupos != null) {
                    oldGruposIdOfSubgruposListSubgrupos.getSubgruposList().remove(subgruposListSubgrupos);
                    oldGruposIdOfSubgruposListSubgrupos = em.merge(oldGruposIdOfSubgruposListSubgrupos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupos grupos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupos persistentGrupos = em.find(Grupos.class, grupos.getId());
            List<Subgrupos> subgruposListOld = persistentGrupos.getSubgruposList();
            List<Subgrupos> subgruposListNew = grupos.getSubgruposList();
            List<String> illegalOrphanMessages = null;
            for (Subgrupos subgruposListOldSubgrupos : subgruposListOld) {
                if (!subgruposListNew.contains(subgruposListOldSubgrupos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Subgrupos " + subgruposListOldSubgrupos + " since its gruposId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Subgrupos> attachedSubgruposListNew = new ArrayList<Subgrupos>();
            for (Subgrupos subgruposListNewSubgruposToAttach : subgruposListNew) {
                subgruposListNewSubgruposToAttach = em.getReference(subgruposListNewSubgruposToAttach.getClass(), subgruposListNewSubgruposToAttach.getId());
                attachedSubgruposListNew.add(subgruposListNewSubgruposToAttach);
            }
            subgruposListNew = attachedSubgruposListNew;
            grupos.setSubgruposList(subgruposListNew);
            grupos = em.merge(grupos);
            for (Subgrupos subgruposListNewSubgrupos : subgruposListNew) {
                if (!subgruposListOld.contains(subgruposListNewSubgrupos)) {
                    Grupos oldGruposIdOfSubgruposListNewSubgrupos = subgruposListNewSubgrupos.getGruposId();
                    subgruposListNewSubgrupos.setGruposId(grupos);
                    subgruposListNewSubgrupos = em.merge(subgruposListNewSubgrupos);
                    if (oldGruposIdOfSubgruposListNewSubgrupos != null && !oldGruposIdOfSubgruposListNewSubgrupos.equals(grupos)) {
                        oldGruposIdOfSubgruposListNewSubgrupos.getSubgruposList().remove(subgruposListNewSubgrupos);
                        oldGruposIdOfSubgruposListNewSubgrupos = em.merge(oldGruposIdOfSubgruposListNewSubgrupos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupos.getId();
                if (findGrupos(id) == null) {
                    throw new NonexistentEntityException("The grupos with id " + id + " no longer exists.");
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
            Grupos grupos;
            try {
                grupos = em.getReference(Grupos.class, id);
                grupos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Subgrupos> subgruposListOrphanCheck = grupos.getSubgruposList();
            for (Subgrupos subgruposListOrphanCheckSubgrupos : subgruposListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupos (" + grupos + ") cannot be destroyed since the Subgrupos " + subgruposListOrphanCheckSubgrupos + " in its subgruposList field has a non-nullable gruposId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(grupos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupos> findGruposEntities() {
        return findGruposEntities(true, -1, -1);
    }

    public List<Grupos> findGruposEntities(int maxResults, int firstResult) {
        return findGruposEntities(false, maxResults, firstResult);
    }

    private List<Grupos> findGruposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupos.class));
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

    public Grupos findGrupos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGruposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupos> rt = cq.from(Grupos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
