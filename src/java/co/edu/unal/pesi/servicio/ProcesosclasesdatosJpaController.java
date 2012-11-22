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
import co.edu.unal.pesi.modelo.Procesos;
import co.edu.unal.pesi.modelo.Clasedatos;
import co.edu.unal.pesi.modelo.Procesosclasesdatos;
import co.edu.unal.pesi.modelo.ProcesosclasesdatosPK;
import co.edu.unal.pesi.servicio.exceptions.NonexistentEntityException;
import co.edu.unal.pesi.servicio.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wilvec
 */
public class ProcesosclasesdatosJpaController implements Serializable {

    public ProcesosclasesdatosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesosclasesdatos procesosclasesdatos) throws PreexistingEntityException, Exception {
        if (procesosclasesdatos.getProcesosclasesdatosPK() == null) {
            procesosclasesdatos.setProcesosclasesdatosPK(new ProcesosclasesdatosPK());
        }
        procesosclasesdatos.getProcesosclasesdatosPK().setClasedatosId(procesosclasesdatos.getClasedatos().getId());
        procesosclasesdatos.getProcesosclasesdatosPK().setProcesosId(procesosclasesdatos.getProcesos().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesos procesos = procesosclasesdatos.getProcesos();
            if (procesos != null) {
                procesos = em.getReference(procesos.getClass(), procesos.getId());
                procesosclasesdatos.setProcesos(procesos);
            }
            Clasedatos clasedatos = procesosclasesdatos.getClasedatos();
            if (clasedatos != null) {
                clasedatos = em.getReference(clasedatos.getClass(), clasedatos.getId());
                procesosclasesdatos.setClasedatos(clasedatos);
            }
            em.persist(procesosclasesdatos);
            if (procesos != null) {
                procesos.getProcesosclasesdatosList().add(procesosclasesdatos);
                procesos = em.merge(procesos);
            }
            if (clasedatos != null) {
                clasedatos.getProcesosclasesdatosList().add(procesosclasesdatos);
                clasedatos = em.merge(clasedatos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProcesosclasesdatos(procesosclasesdatos.getProcesosclasesdatosPK()) != null) {
                throw new PreexistingEntityException("Procesosclasesdatos " + procesosclasesdatos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesosclasesdatos procesosclasesdatos) throws NonexistentEntityException, Exception {
        procesosclasesdatos.getProcesosclasesdatosPK().setClasedatosId(procesosclasesdatos.getClasedatos().getId());
        procesosclasesdatos.getProcesosclasesdatosPK().setProcesosId(procesosclasesdatos.getProcesos().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesosclasesdatos persistentProcesosclasesdatos = em.find(Procesosclasesdatos.class, procesosclasesdatos.getProcesosclasesdatosPK());
            Procesos procesosOld = persistentProcesosclasesdatos.getProcesos();
            Procesos procesosNew = procesosclasesdatos.getProcesos();
            Clasedatos clasedatosOld = persistentProcesosclasesdatos.getClasedatos();
            Clasedatos clasedatosNew = procesosclasesdatos.getClasedatos();
            if (procesosNew != null) {
                procesosNew = em.getReference(procesosNew.getClass(), procesosNew.getId());
                procesosclasesdatos.setProcesos(procesosNew);
            }
            if (clasedatosNew != null) {
                clasedatosNew = em.getReference(clasedatosNew.getClass(), clasedatosNew.getId());
                procesosclasesdatos.setClasedatos(clasedatosNew);
            }
            procesosclasesdatos = em.merge(procesosclasesdatos);
            if (procesosOld != null && !procesosOld.equals(procesosNew)) {
                procesosOld.getProcesosclasesdatosList().remove(procesosclasesdatos);
                procesosOld = em.merge(procesosOld);
            }
            if (procesosNew != null && !procesosNew.equals(procesosOld)) {
                procesosNew.getProcesosclasesdatosList().add(procesosclasesdatos);
                procesosNew = em.merge(procesosNew);
            }
            if (clasedatosOld != null && !clasedatosOld.equals(clasedatosNew)) {
                clasedatosOld.getProcesosclasesdatosList().remove(procesosclasesdatos);
                clasedatosOld = em.merge(clasedatosOld);
            }
            if (clasedatosNew != null && !clasedatosNew.equals(clasedatosOld)) {
                clasedatosNew.getProcesosclasesdatosList().add(procesosclasesdatos);
                clasedatosNew = em.merge(clasedatosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProcesosclasesdatosPK id = procesosclasesdatos.getProcesosclasesdatosPK();
                if (findProcesosclasesdatos(id) == null) {
                    throw new NonexistentEntityException("The procesosclasesdatos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProcesosclasesdatosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesosclasesdatos procesosclasesdatos;
            try {
                procesosclasesdatos = em.getReference(Procesosclasesdatos.class, id);
                procesosclasesdatos.getProcesosclasesdatosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesosclasesdatos with id " + id + " no longer exists.", enfe);
            }
            Procesos procesos = procesosclasesdatos.getProcesos();
            if (procesos != null) {
                procesos.getProcesosclasesdatosList().remove(procesosclasesdatos);
                procesos = em.merge(procesos);
            }
            Clasedatos clasedatos = procesosclasesdatos.getClasedatos();
            if (clasedatos != null) {
                clasedatos.getProcesosclasesdatosList().remove(procesosclasesdatos);
                clasedatos = em.merge(clasedatos);
            }
            em.remove(procesosclasesdatos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procesosclasesdatos> findProcesosclasesdatosEntities() {
        return findProcesosclasesdatosEntities(true, -1, -1);
    }

    public List<Procesosclasesdatos> findProcesosclasesdatosEntities(int maxResults, int firstResult) {
        return findProcesosclasesdatosEntities(false, maxResults, firstResult);
    }

    private List<Procesosclasesdatos> findProcesosclasesdatosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesosclasesdatos.class));
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

    public Procesosclasesdatos findProcesosclasesdatos(ProcesosclasesdatosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesosclasesdatos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesosclasesdatosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesosclasesdatos> rt = cq.from(Procesosclasesdatos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
