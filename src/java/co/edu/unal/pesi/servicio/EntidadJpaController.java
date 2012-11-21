/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.servicio;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unal.pesi.modelo.Clasedatos;
import co.edu.unal.pesi.modelo.Entidad;
import co.edu.unal.pesi.servicio.exceptions.IllegalOrphanException;
import co.edu.unal.pesi.servicio.exceptions.NonexistentEntityException;
import co.edu.unal.pesi.servicio.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wilvec
 */
public class EntidadJpaController implements Serializable {

    public EntidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entidad entidad) throws PreexistingEntityException, Exception {
        if (entidad.getClasedatosList() == null) {
            entidad.setClasedatosList(new ArrayList<Clasedatos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Clasedatos> attachedClasedatosList = new ArrayList<Clasedatos>();
            for (Clasedatos clasedatosListClasedatosToAttach : entidad.getClasedatosList()) {
                clasedatosListClasedatosToAttach = em.getReference(clasedatosListClasedatosToAttach.getClass(), clasedatosListClasedatosToAttach.getId());
                attachedClasedatosList.add(clasedatosListClasedatosToAttach);
            }
            entidad.setClasedatosList(attachedClasedatosList);
            em.persist(entidad);
            for (Clasedatos clasedatosListClasedatos : entidad.getClasedatosList()) {
                Entidad oldEntidadIdOfClasedatosListClasedatos = clasedatosListClasedatos.getEntidadId();
                clasedatosListClasedatos.setEntidadId(entidad);
                clasedatosListClasedatos = em.merge(clasedatosListClasedatos);
                if (oldEntidadIdOfClasedatosListClasedatos != null) {
                    oldEntidadIdOfClasedatosListClasedatos.getClasedatosList().remove(clasedatosListClasedatos);
                    oldEntidadIdOfClasedatosListClasedatos = em.merge(oldEntidadIdOfClasedatosListClasedatos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntidad(entidad.getId()) != null) {
                throw new PreexistingEntityException("Entidad " + entidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entidad entidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidad persistentEntidad = em.find(Entidad.class, entidad.getId());
            List<Clasedatos> clasedatosListOld = persistentEntidad.getClasedatosList();
            List<Clasedatos> clasedatosListNew = entidad.getClasedatosList();
            List<String> illegalOrphanMessages = null;
            for (Clasedatos clasedatosListOldClasedatos : clasedatosListOld) {
                if (!clasedatosListNew.contains(clasedatosListOldClasedatos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Clasedatos " + clasedatosListOldClasedatos + " since its entidadId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Clasedatos> attachedClasedatosListNew = new ArrayList<Clasedatos>();
            for (Clasedatos clasedatosListNewClasedatosToAttach : clasedatosListNew) {
                clasedatosListNewClasedatosToAttach = em.getReference(clasedatosListNewClasedatosToAttach.getClass(), clasedatosListNewClasedatosToAttach.getId());
                attachedClasedatosListNew.add(clasedatosListNewClasedatosToAttach);
            }
            clasedatosListNew = attachedClasedatosListNew;
            entidad.setClasedatosList(clasedatosListNew);
            entidad = em.merge(entidad);
            for (Clasedatos clasedatosListNewClasedatos : clasedatosListNew) {
                if (!clasedatosListOld.contains(clasedatosListNewClasedatos)) {
                    Entidad oldEntidadIdOfClasedatosListNewClasedatos = clasedatosListNewClasedatos.getEntidadId();
                    clasedatosListNewClasedatos.setEntidadId(entidad);
                    clasedatosListNewClasedatos = em.merge(clasedatosListNewClasedatos);
                    if (oldEntidadIdOfClasedatosListNewClasedatos != null && !oldEntidadIdOfClasedatosListNewClasedatos.equals(entidad)) {
                        oldEntidadIdOfClasedatosListNewClasedatos.getClasedatosList().remove(clasedatosListNewClasedatos);
                        oldEntidadIdOfClasedatosListNewClasedatos = em.merge(oldEntidadIdOfClasedatosListNewClasedatos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entidad.getId();
                if (findEntidad(id) == null) {
                    throw new NonexistentEntityException("The entidad with id " + id + " no longer exists.");
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
            Entidad entidad;
            try {
                entidad = em.getReference(Entidad.class, id);
                entidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Clasedatos> clasedatosListOrphanCheck = entidad.getClasedatosList();
            for (Clasedatos clasedatosListOrphanCheckClasedatos : clasedatosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entidad (" + entidad + ") cannot be destroyed since the Clasedatos " + clasedatosListOrphanCheckClasedatos + " in its clasedatosList field has a non-nullable entidadId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(entidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entidad> findEntidadEntities() {
        return findEntidadEntities(true, -1, -1);
    }

    public List<Entidad> findEntidadEntities(int maxResults, int firstResult) {
        return findEntidadEntities(false, maxResults, firstResult);
    }

    private List<Entidad> findEntidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entidad.class));
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

    public Entidad findEntidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entidad> rt = cq.from(Entidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
