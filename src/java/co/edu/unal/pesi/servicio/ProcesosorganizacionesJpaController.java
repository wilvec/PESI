/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.servicio;

import co.edu.unal.pesi.modelo.Organizaciones;
import co.edu.unal.pesi.modelo.Organizaciones;
import co.edu.unal.pesi.modelo.Procesos;
import co.edu.unal.pesi.modelo.Procesos;
import co.edu.unal.pesi.modelo.Procesosorganizaciones;
import co.edu.unal.pesi.modelo.Procesosorganizaciones;
import co.edu.unal.pesi.modelo.ProcesosorganizacionesPK;
import co.edu.unal.pesi.modelo.ProcesosorganizacionesPK;
import co.edu.unal.pesi.servicio.exceptions.NonexistentEntityException;
import co.edu.unal.pesi.servicio.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author wilvec
 */
public class ProcesosorganizacionesJpaController implements Serializable {

    public ProcesosorganizacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesosorganizaciones procesosorganizaciones) throws PreexistingEntityException, Exception {
        if (procesosorganizaciones.getProcesosorganizacionesPK() == null) {
            procesosorganizaciones.setProcesosorganizacionesPK(new ProcesosorganizacionesPK());
        }
        procesosorganizaciones.getProcesosorganizacionesPK().setOrganizacionesId(procesosorganizaciones.getOrganizaciones().getId());
        procesosorganizaciones.getProcesosorganizacionesPK().setProcesosId(procesosorganizaciones.getProcesos().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Organizaciones organizaciones = procesosorganizaciones.getOrganizaciones();
            if (organizaciones != null) {
                organizaciones = em.getReference(organizaciones.getClass(), organizaciones.getId());
                procesosorganizaciones.setOrganizaciones(organizaciones);
            }
            Procesos procesos = procesosorganizaciones.getProcesos();
            if (procesos != null) {
                procesos = em.getReference(procesos.getClass(), procesos.getId());
                procesosorganizaciones.setProcesos(procesos);
            }
            em.persist(procesosorganizaciones);
            if (organizaciones != null) {
                organizaciones.getProcesosorganizacionesList().add(procesosorganizaciones);
                organizaciones = em.merge(organizaciones);
            }
            if (procesos != null) {
                procesos.getProcesosorganizacionesList().add(procesosorganizaciones);
                procesos = em.merge(procesos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesosorganizaciones(procesosorganizaciones.getProcesosorganizacionesPK()) != null) {
                throw new PreexistingEntityException("Procesosorganizaciones " + procesosorganizaciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesosorganizaciones procesosorganizaciones) throws NonexistentEntityException, Exception {
        procesosorganizaciones.getProcesosorganizacionesPK().setOrganizacionesId(procesosorganizaciones.getOrganizaciones().getId());
        procesosorganizaciones.getProcesosorganizacionesPK().setProcesosId(procesosorganizaciones.getProcesos().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesosorganizaciones persistentProcesosorganizaciones = em.find(Procesosorganizaciones.class, procesosorganizaciones.getProcesosorganizacionesPK());
            Organizaciones organizacionesOld = persistentProcesosorganizaciones.getOrganizaciones();
            Organizaciones organizacionesNew = procesosorganizaciones.getOrganizaciones();
            Procesos procesosOld = persistentProcesosorganizaciones.getProcesos();
            Procesos procesosNew = procesosorganizaciones.getProcesos();
            if (organizacionesNew != null) {
                organizacionesNew = em.getReference(organizacionesNew.getClass(), organizacionesNew.getId());
                procesosorganizaciones.setOrganizaciones(organizacionesNew);
            }
            if (procesosNew != null) {
                procesosNew = em.getReference(procesosNew.getClass(), procesosNew.getId());
                procesosorganizaciones.setProcesos(procesosNew);
            }
            procesosorganizaciones = em.merge(procesosorganizaciones);
            if (organizacionesOld != null && !organizacionesOld.equals(organizacionesNew)) {
                organizacionesOld.getProcesosorganizacionesList().remove(procesosorganizaciones);
                organizacionesOld = em.merge(organizacionesOld);
            }
            if (organizacionesNew != null && !organizacionesNew.equals(organizacionesOld)) {
                organizacionesNew.getProcesosorganizacionesList().add(procesosorganizaciones);
                organizacionesNew = em.merge(organizacionesNew);
            }
            if (procesosOld != null && !procesosOld.equals(procesosNew)) {
                procesosOld.getProcesosorganizacionesList().remove(procesosorganizaciones);
                procesosOld = em.merge(procesosOld);
            }
            if (procesosNew != null && !procesosNew.equals(procesosOld)) {
                procesosNew.getProcesosorganizacionesList().add(procesosorganizaciones);
                procesosNew = em.merge(procesosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProcesosorganizacionesPK id = procesosorganizaciones.getProcesosorganizacionesPK();
                if (findProcesosorganizaciones(id) == null) {
                    throw new NonexistentEntityException("The procesosorganizaciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProcesosorganizacionesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesosorganizaciones procesosorganizaciones;
            try {
                procesosorganizaciones = em.getReference(Procesosorganizaciones.class, id);
                procesosorganizaciones.getProcesosorganizacionesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesosorganizaciones with id " + id + " no longer exists.", enfe);
            }
            Organizaciones organizaciones = procesosorganizaciones.getOrganizaciones();
            if (organizaciones != null) {
                organizaciones.getProcesosorganizacionesList().remove(procesosorganizaciones);
                organizaciones = em.merge(organizaciones);
            }
            Procesos procesos = procesosorganizaciones.getProcesos();
            if (procesos != null) {
                procesos.getProcesosorganizacionesList().remove(procesosorganizaciones);
                procesos = em.merge(procesos);
            }
            em.remove(procesosorganizaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procesosorganizaciones> findProcesosorganizacionesEntities() {
        return findProcesosorganizacionesEntities(true, -1, -1);
    }

    public List<Procesosorganizaciones> findProcesosorganizacionesEntities(int maxResults, int firstResult) {
        return findProcesosorganizacionesEntities(false, maxResults, firstResult);
    }

    private List<Procesosorganizaciones> findProcesosorganizacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesosorganizaciones.class));
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

    public Procesosorganizaciones findProcesosorganizaciones(ProcesosorganizacionesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesosorganizaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesosorganizacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesosorganizaciones> rt = cq.from(Procesosorganizaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
