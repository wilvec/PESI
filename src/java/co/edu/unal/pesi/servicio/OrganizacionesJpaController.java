/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.servicio;

import co.edu.unal.pesi.modelo.Organizaciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unal.pesi.modelo.Procesosorganizaciones;
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
public class OrganizacionesJpaController implements Serializable {

    public OrganizacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Organizaciones organizaciones) {
        if (organizaciones.getProcesosorganizacionesList() == null) {
            organizaciones.setProcesosorganizacionesList(new ArrayList<Procesosorganizaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Procesosorganizaciones> attachedProcesosorganizacionesList = new ArrayList<Procesosorganizaciones>();
            for (Procesosorganizaciones procesosorganizacionesListProcesosorganizacionesToAttach : organizaciones.getProcesosorganizacionesList()) {
                procesosorganizacionesListProcesosorganizacionesToAttach = em.getReference(procesosorganizacionesListProcesosorganizacionesToAttach.getClass(), procesosorganizacionesListProcesosorganizacionesToAttach.getProcesosorganizacionesPK());
                attachedProcesosorganizacionesList.add(procesosorganizacionesListProcesosorganizacionesToAttach);
            }
            organizaciones.setProcesosorganizacionesList(attachedProcesosorganizacionesList);
            em.persist(organizaciones);
            for (Procesosorganizaciones procesosorganizacionesListProcesosorganizaciones : organizaciones.getProcesosorganizacionesList()) {
                Organizaciones oldOrganizacionesOfProcesosorganizacionesListProcesosorganizaciones = procesosorganizacionesListProcesosorganizaciones.getOrganizaciones();
                procesosorganizacionesListProcesosorganizaciones.setOrganizaciones(organizaciones);
                procesosorganizacionesListProcesosorganizaciones = em.merge(procesosorganizacionesListProcesosorganizaciones);
                if (oldOrganizacionesOfProcesosorganizacionesListProcesosorganizaciones != null) {
                    oldOrganizacionesOfProcesosorganizacionesListProcesosorganizaciones.getProcesosorganizacionesList().remove(procesosorganizacionesListProcesosorganizaciones);
                    oldOrganizacionesOfProcesosorganizacionesListProcesosorganizaciones = em.merge(oldOrganizacionesOfProcesosorganizacionesListProcesosorganizaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Organizaciones organizaciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizaciones persistentOrganizaciones = em.find(Organizaciones.class, organizaciones.getId());
            List<Procesosorganizaciones> procesosorganizacionesListOld = persistentOrganizaciones.getProcesosorganizacionesList();
            List<Procesosorganizaciones> procesosorganizacionesListNew = organizaciones.getProcesosorganizacionesList();
            List<String> illegalOrphanMessages = null;
            for (Procesosorganizaciones procesosorganizacionesListOldProcesosorganizaciones : procesosorganizacionesListOld) {
                if (!procesosorganizacionesListNew.contains(procesosorganizacionesListOldProcesosorganizaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procesosorganizaciones " + procesosorganizacionesListOldProcesosorganizaciones + " since its organizaciones field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Procesosorganizaciones> attachedProcesosorganizacionesListNew = new ArrayList<Procesosorganizaciones>();
            for (Procesosorganizaciones procesosorganizacionesListNewProcesosorganizacionesToAttach : procesosorganizacionesListNew) {
                procesosorganizacionesListNewProcesosorganizacionesToAttach = em.getReference(procesosorganizacionesListNewProcesosorganizacionesToAttach.getClass(), procesosorganizacionesListNewProcesosorganizacionesToAttach.getProcesosorganizacionesPK());
                attachedProcesosorganizacionesListNew.add(procesosorganizacionesListNewProcesosorganizacionesToAttach);
            }
            procesosorganizacionesListNew = attachedProcesosorganizacionesListNew;
            organizaciones.setProcesosorganizacionesList(procesosorganizacionesListNew);
            organizaciones = em.merge(organizaciones);
            for (Procesosorganizaciones procesosorganizacionesListNewProcesosorganizaciones : procesosorganizacionesListNew) {
                if (!procesosorganizacionesListOld.contains(procesosorganizacionesListNewProcesosorganizaciones)) {
                    Organizaciones oldOrganizacionesOfProcesosorganizacionesListNewProcesosorganizaciones = procesosorganizacionesListNewProcesosorganizaciones.getOrganizaciones();
                    procesosorganizacionesListNewProcesosorganizaciones.setOrganizaciones(organizaciones);
                    procesosorganizacionesListNewProcesosorganizaciones = em.merge(procesosorganizacionesListNewProcesosorganizaciones);
                    if (oldOrganizacionesOfProcesosorganizacionesListNewProcesosorganizaciones != null && !oldOrganizacionesOfProcesosorganizacionesListNewProcesosorganizaciones.equals(organizaciones)) {
                        oldOrganizacionesOfProcesosorganizacionesListNewProcesosorganizaciones.getProcesosorganizacionesList().remove(procesosorganizacionesListNewProcesosorganizaciones);
                        oldOrganizacionesOfProcesosorganizacionesListNewProcesosorganizaciones = em.merge(oldOrganizacionesOfProcesosorganizacionesListNewProcesosorganizaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = organizaciones.getId();
                if (findOrganizaciones(id) == null) {
                    throw new NonexistentEntityException("The organizaciones with id " + id + " no longer exists.");
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
            Organizaciones organizaciones;
            try {
                organizaciones = em.getReference(Organizaciones.class, id);
                organizaciones.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The organizaciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Procesosorganizaciones> procesosorganizacionesListOrphanCheck = organizaciones.getProcesosorganizacionesList();
            for (Procesosorganizaciones procesosorganizacionesListOrphanCheckProcesosorganizaciones : procesosorganizacionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Organizaciones (" + organizaciones + ") cannot be destroyed since the Procesosorganizaciones " + procesosorganizacionesListOrphanCheckProcesosorganizaciones + " in its procesosorganizacionesList field has a non-nullable organizaciones field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(organizaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Organizaciones> findOrganizacionesEntities() {
        return findOrganizacionesEntities(true, -1, -1);
    }

    public List<Organizaciones> findOrganizacionesEntities(int maxResults, int firstResult) {
        return findOrganizacionesEntities(false, maxResults, firstResult);
    }

    private List<Organizaciones> findOrganizacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Organizaciones.class));
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

    public Organizaciones findOrganizaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Organizaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrganizacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Organizaciones> rt = cq.from(Organizaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
