/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.servicio;

import co.edu.unal.pesi.modelo.Clasedatos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import co.edu.unal.pesi.modelo.Entidad;
import co.edu.unal.pesi.modelo.Procesos;
import java.util.ArrayList;
import java.util.List;
import co.edu.unal.pesi.modelo.Procesosclasesdatos;
import co.edu.unal.pesi.servicio.exceptions.IllegalOrphanException;
import co.edu.unal.pesi.servicio.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wilvec
 */
public class ClasedatosJpaController1 implements Serializable {

    public ClasedatosJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clasedatos clasedatos) {
        if (clasedatos.getProcesosList() == null) {
            clasedatos.setProcesosList(new ArrayList<Procesos>());
        }
        if (clasedatos.getProcesosclasesdatosList() == null) {
            clasedatos.setProcesosclasesdatosList(new ArrayList<Procesosclasesdatos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entidad entidadId = clasedatos.getEntidadId();
            if (entidadId != null) {
                entidadId = em.getReference(entidadId.getClass(), entidadId.getId());
                clasedatos.setEntidadId(entidadId);
            }
            List<Procesos> attachedProcesosList = new ArrayList<Procesos>();
            for (Procesos procesosListProcesosToAttach : clasedatos.getProcesosList()) {
                procesosListProcesosToAttach = em.getReference(procesosListProcesosToAttach.getClass(), procesosListProcesosToAttach.getId());
                attachedProcesosList.add(procesosListProcesosToAttach);
            }
            clasedatos.setProcesosList(attachedProcesosList);
            List<Procesosclasesdatos> attachedProcesosclasesdatosList = new ArrayList<Procesosclasesdatos>();
            for (Procesosclasesdatos procesosclasesdatosListProcesosclasesdatosToAttach : clasedatos.getProcesosclasesdatosList()) {
                procesosclasesdatosListProcesosclasesdatosToAttach = em.getReference(procesosclasesdatosListProcesosclasesdatosToAttach.getClass(), procesosclasesdatosListProcesosclasesdatosToAttach.getProcesosclasesdatosPK());
                attachedProcesosclasesdatosList.add(procesosclasesdatosListProcesosclasesdatosToAttach);
            }
            clasedatos.setProcesosclasesdatosList(attachedProcesosclasesdatosList);
            em.persist(clasedatos);
            if (entidadId != null) {
                entidadId.getClasedatosList().add(clasedatos);
                entidadId = em.merge(entidadId);
            }
            for (Procesos procesosListProcesos : clasedatos.getProcesosList()) {
                procesosListProcesos.getClasedatosList().add(clasedatos);
                procesosListProcesos = em.merge(procesosListProcesos);
            }
            for (Procesosclasesdatos procesosclasesdatosListProcesosclasesdatos : clasedatos.getProcesosclasesdatosList()) {
                Clasedatos oldClasedatosOfProcesosclasesdatosListProcesosclasesdatos = procesosclasesdatosListProcesosclasesdatos.getClasedatos();
                procesosclasesdatosListProcesosclasesdatos.setClasedatos(clasedatos);
                procesosclasesdatosListProcesosclasesdatos = em.merge(procesosclasesdatosListProcesosclasesdatos);
                if (oldClasedatosOfProcesosclasesdatosListProcesosclasesdatos != null) {
                    oldClasedatosOfProcesosclasesdatosListProcesosclasesdatos.getProcesosclasesdatosList().remove(procesosclasesdatosListProcesosclasesdatos);
                    oldClasedatosOfProcesosclasesdatosListProcesosclasesdatos = em.merge(oldClasedatosOfProcesosclasesdatosListProcesosclasesdatos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clasedatos clasedatos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clasedatos persistentClasedatos = em.find(Clasedatos.class, clasedatos.getId());
            Entidad entidadIdOld = persistentClasedatos.getEntidadId();
            Entidad entidadIdNew = clasedatos.getEntidadId();
            List<Procesos> procesosListOld = persistentClasedatos.getProcesosList();
            List<Procesos> procesosListNew = clasedatos.getProcesosList();
            List<Procesosclasesdatos> procesosclasesdatosListOld = persistentClasedatos.getProcesosclasesdatosList();
            List<Procesosclasesdatos> procesosclasesdatosListNew = clasedatos.getProcesosclasesdatosList();
            List<String> illegalOrphanMessages = null;
            for (Procesosclasesdatos procesosclasesdatosListOldProcesosclasesdatos : procesosclasesdatosListOld) {
                if (!procesosclasesdatosListNew.contains(procesosclasesdatosListOldProcesosclasesdatos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procesosclasesdatos " + procesosclasesdatosListOldProcesosclasesdatos + " since its clasedatos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (entidadIdNew != null) {
                entidadIdNew = em.getReference(entidadIdNew.getClass(), entidadIdNew.getId());
                clasedatos.setEntidadId(entidadIdNew);
            }
            List<Procesos> attachedProcesosListNew = new ArrayList<Procesos>();
            for (Procesos procesosListNewProcesosToAttach : procesosListNew) {
                procesosListNewProcesosToAttach = em.getReference(procesosListNewProcesosToAttach.getClass(), procesosListNewProcesosToAttach.getId());
                attachedProcesosListNew.add(procesosListNewProcesosToAttach);
            }
            procesosListNew = attachedProcesosListNew;
            clasedatos.setProcesosList(procesosListNew);
            List<Procesosclasesdatos> attachedProcesosclasesdatosListNew = new ArrayList<Procesosclasesdatos>();
            for (Procesosclasesdatos procesosclasesdatosListNewProcesosclasesdatosToAttach : procesosclasesdatosListNew) {
                procesosclasesdatosListNewProcesosclasesdatosToAttach = em.getReference(procesosclasesdatosListNewProcesosclasesdatosToAttach.getClass(), procesosclasesdatosListNewProcesosclasesdatosToAttach.getProcesosclasesdatosPK());
                attachedProcesosclasesdatosListNew.add(procesosclasesdatosListNewProcesosclasesdatosToAttach);
            }
            procesosclasesdatosListNew = attachedProcesosclasesdatosListNew;
            clasedatos.setProcesosclasesdatosList(procesosclasesdatosListNew);
            clasedatos = em.merge(clasedatos);
            if (entidadIdOld != null && !entidadIdOld.equals(entidadIdNew)) {
                entidadIdOld.getClasedatosList().remove(clasedatos);
                entidadIdOld = em.merge(entidadIdOld);
            }
            if (entidadIdNew != null && !entidadIdNew.equals(entidadIdOld)) {
                entidadIdNew.getClasedatosList().add(clasedatos);
                entidadIdNew = em.merge(entidadIdNew);
            }
            for (Procesos procesosListOldProcesos : procesosListOld) {
                if (!procesosListNew.contains(procesosListOldProcesos)) {
                    procesosListOldProcesos.getClasedatosList().remove(clasedatos);
                    procesosListOldProcesos = em.merge(procesosListOldProcesos);
                }
            }
            for (Procesos procesosListNewProcesos : procesosListNew) {
                if (!procesosListOld.contains(procesosListNewProcesos)) {
                    procesosListNewProcesos.getClasedatosList().add(clasedatos);
                    procesosListNewProcesos = em.merge(procesosListNewProcesos);
                }
            }
            for (Procesosclasesdatos procesosclasesdatosListNewProcesosclasesdatos : procesosclasesdatosListNew) {
                if (!procesosclasesdatosListOld.contains(procesosclasesdatosListNewProcesosclasesdatos)) {
                    Clasedatos oldClasedatosOfProcesosclasesdatosListNewProcesosclasesdatos = procesosclasesdatosListNewProcesosclasesdatos.getClasedatos();
                    procesosclasesdatosListNewProcesosclasesdatos.setClasedatos(clasedatos);
                    procesosclasesdatosListNewProcesosclasesdatos = em.merge(procesosclasesdatosListNewProcesosclasesdatos);
                    if (oldClasedatosOfProcesosclasesdatosListNewProcesosclasesdatos != null && !oldClasedatosOfProcesosclasesdatosListNewProcesosclasesdatos.equals(clasedatos)) {
                        oldClasedatosOfProcesosclasesdatosListNewProcesosclasesdatos.getProcesosclasesdatosList().remove(procesosclasesdatosListNewProcesosclasesdatos);
                        oldClasedatosOfProcesosclasesdatosListNewProcesosclasesdatos = em.merge(oldClasedatosOfProcesosclasesdatosListNewProcesosclasesdatos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clasedatos.getId();
                if (findClasedatos(id) == null) {
                    throw new NonexistentEntityException("The clasedatos with id " + id + " no longer exists.");
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
            Clasedatos clasedatos;
            try {
                clasedatos = em.getReference(Clasedatos.class, id);
                clasedatos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clasedatos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Procesosclasesdatos> procesosclasesdatosListOrphanCheck = clasedatos.getProcesosclasesdatosList();
            for (Procesosclasesdatos procesosclasesdatosListOrphanCheckProcesosclasesdatos : procesosclasesdatosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clasedatos (" + clasedatos + ") cannot be destroyed since the Procesosclasesdatos " + procesosclasesdatosListOrphanCheckProcesosclasesdatos + " in its procesosclasesdatosList field has a non-nullable clasedatos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Entidad entidadId = clasedatos.getEntidadId();
            if (entidadId != null) {
                entidadId.getClasedatosList().remove(clasedatos);
                entidadId = em.merge(entidadId);
            }
            List<Procesos> procesosList = clasedatos.getProcesosList();
            for (Procesos procesosListProcesos : procesosList) {
                procesosListProcesos.getClasedatosList().remove(clasedatos);
                procesosListProcesos = em.merge(procesosListProcesos);
            }
            em.remove(clasedatos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clasedatos> findClasedatosEntities() {
        return findClasedatosEntities(true, -1, -1);
    }

    public List<Clasedatos> findClasedatosEntities(int maxResults, int firstResult) {
        return findClasedatosEntities(false, maxResults, firstResult);
    }

    private List<Clasedatos> findClasedatosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clasedatos.class));
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

    public Clasedatos findClasedatos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clasedatos.class, id);
        } finally {
            em.close();
        }
    }

    public int getClasedatosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clasedatos> rt = cq.from(Clasedatos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
