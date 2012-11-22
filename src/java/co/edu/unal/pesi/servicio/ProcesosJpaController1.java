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
import co.edu.unal.pesi.modelo.Subgrupos;
import co.edu.unal.pesi.modelo.Clasedatos;
import co.edu.unal.pesi.modelo.Procesos;
import java.util.ArrayList;
import java.util.List;
import co.edu.unal.pesi.modelo.Procesosorganizaciones;
import co.edu.unal.pesi.modelo.Procesosclasesdatos;
import co.edu.unal.pesi.servicio.exceptions.IllegalOrphanException;
import co.edu.unal.pesi.servicio.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author wilvec
 */
public class ProcesosJpaController1 implements Serializable {

    public ProcesosJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Procesos procesos) {
        if (procesos.getClasedatosList() == null) {
            procesos.setClasedatosList(new ArrayList<Clasedatos>());
        }
        if (procesos.getProcesosorganizacionesList() == null) {
            procesos.setProcesosorganizacionesList(new ArrayList<Procesosorganizaciones>());
        }
        if (procesos.getProcesosclasesdatosList() == null) {
            procesos.setProcesosclasesdatosList(new ArrayList<Procesosclasesdatos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subgrupos subgruposId = procesos.getSubgruposId();
            if (subgruposId != null) {
                subgruposId = em.getReference(subgruposId.getClass(), subgruposId.getId());
                procesos.setSubgruposId(subgruposId);
            }
            List<Clasedatos> attachedClasedatosList = new ArrayList<Clasedatos>();
            for (Clasedatos clasedatosListClasedatosToAttach : procesos.getClasedatosList()) {
                clasedatosListClasedatosToAttach = em.getReference(clasedatosListClasedatosToAttach.getClass(), clasedatosListClasedatosToAttach.getId());
                attachedClasedatosList.add(clasedatosListClasedatosToAttach);
            }
            procesos.setClasedatosList(attachedClasedatosList);
            List<Procesosorganizaciones> attachedProcesosorganizacionesList = new ArrayList<Procesosorganizaciones>();
            for (Procesosorganizaciones procesosorganizacionesListProcesosorganizacionesToAttach : procesos.getProcesosorganizacionesList()) {
                procesosorganizacionesListProcesosorganizacionesToAttach = em.getReference(procesosorganizacionesListProcesosorganizacionesToAttach.getClass(), procesosorganizacionesListProcesosorganizacionesToAttach.getProcesosorganizacionesPK());
                attachedProcesosorganizacionesList.add(procesosorganizacionesListProcesosorganizacionesToAttach);
            }
            procesos.setProcesosorganizacionesList(attachedProcesosorganizacionesList);
            List<Procesosclasesdatos> attachedProcesosclasesdatosList = new ArrayList<Procesosclasesdatos>();
            for (Procesosclasesdatos procesosclasesdatosListProcesosclasesdatosToAttach : procesos.getProcesosclasesdatosList()) {
                procesosclasesdatosListProcesosclasesdatosToAttach = em.getReference(procesosclasesdatosListProcesosclasesdatosToAttach.getClass(), procesosclasesdatosListProcesosclasesdatosToAttach.getProcesosclasesdatosPK());
                attachedProcesosclasesdatosList.add(procesosclasesdatosListProcesosclasesdatosToAttach);
            }
            procesos.setProcesosclasesdatosList(attachedProcesosclasesdatosList);
            em.persist(procesos);
            if (subgruposId != null) {
                subgruposId.getProcesosList().add(procesos);
                subgruposId = em.merge(subgruposId);
            }
            for (Clasedatos clasedatosListClasedatos : procesos.getClasedatosList()) {
                clasedatosListClasedatos.getProcesosList().add(procesos);
                clasedatosListClasedatos = em.merge(clasedatosListClasedatos);
            }
            for (Procesosorganizaciones procesosorganizacionesListProcesosorganizaciones : procesos.getProcesosorganizacionesList()) {
                Procesos oldProcesosOfProcesosorganizacionesListProcesosorganizaciones = procesosorganizacionesListProcesosorganizaciones.getProcesos();
                procesosorganizacionesListProcesosorganizaciones.setProcesos(procesos);
                procesosorganizacionesListProcesosorganizaciones = em.merge(procesosorganizacionesListProcesosorganizaciones);
                if (oldProcesosOfProcesosorganizacionesListProcesosorganizaciones != null) {
                    oldProcesosOfProcesosorganizacionesListProcesosorganizaciones.getProcesosorganizacionesList().remove(procesosorganizacionesListProcesosorganizaciones);
                    oldProcesosOfProcesosorganizacionesListProcesosorganizaciones = em.merge(oldProcesosOfProcesosorganizacionesListProcesosorganizaciones);
                }
            }
            for (Procesosclasesdatos procesosclasesdatosListProcesosclasesdatos : procesos.getProcesosclasesdatosList()) {
                Procesos oldProcesosOfProcesosclasesdatosListProcesosclasesdatos = procesosclasesdatosListProcesosclasesdatos.getProcesos();
                procesosclasesdatosListProcesosclasesdatos.setProcesos(procesos);
                procesosclasesdatosListProcesosclasesdatos = em.merge(procesosclasesdatosListProcesosclasesdatos);
                if (oldProcesosOfProcesosclasesdatosListProcesosclasesdatos != null) {
                    oldProcesosOfProcesosclasesdatosListProcesosclasesdatos.getProcesosclasesdatosList().remove(procesosclasesdatosListProcesosclasesdatos);
                    oldProcesosOfProcesosclasesdatosListProcesosclasesdatos = em.merge(oldProcesosOfProcesosclasesdatosListProcesosclasesdatos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Procesos procesos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesos persistentProcesos = em.find(Procesos.class, procesos.getId());
            Subgrupos subgruposIdOld = persistentProcesos.getSubgruposId();
            Subgrupos subgruposIdNew = procesos.getSubgruposId();
            List<Clasedatos> clasedatosListOld = persistentProcesos.getClasedatosList();
            List<Clasedatos> clasedatosListNew = procesos.getClasedatosList();
            List<Procesosorganizaciones> procesosorganizacionesListOld = persistentProcesos.getProcesosorganizacionesList();
            List<Procesosorganizaciones> procesosorganizacionesListNew = procesos.getProcesosorganizacionesList();
            List<Procesosclasesdatos> procesosclasesdatosListOld = persistentProcesos.getProcesosclasesdatosList();
            List<Procesosclasesdatos> procesosclasesdatosListNew = procesos.getProcesosclasesdatosList();
            List<String> illegalOrphanMessages = null;
            for (Procesosorganizaciones procesosorganizacionesListOldProcesosorganizaciones : procesosorganizacionesListOld) {
                if (!procesosorganizacionesListNew.contains(procesosorganizacionesListOldProcesosorganizaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procesosorganizaciones " + procesosorganizacionesListOldProcesosorganizaciones + " since its procesos field is not nullable.");
                }
            }
            for (Procesosclasesdatos procesosclasesdatosListOldProcesosclasesdatos : procesosclasesdatosListOld) {
                if (!procesosclasesdatosListNew.contains(procesosclasesdatosListOldProcesosclasesdatos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Procesosclasesdatos " + procesosclasesdatosListOldProcesosclasesdatos + " since its procesos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (subgruposIdNew != null) {
                subgruposIdNew = em.getReference(subgruposIdNew.getClass(), subgruposIdNew.getId());
                procesos.setSubgruposId(subgruposIdNew);
            }
            List<Clasedatos> attachedClasedatosListNew = new ArrayList<Clasedatos>();
            for (Clasedatos clasedatosListNewClasedatosToAttach : clasedatosListNew) {
                clasedatosListNewClasedatosToAttach = em.getReference(clasedatosListNewClasedatosToAttach.getClass(), clasedatosListNewClasedatosToAttach.getId());
                attachedClasedatosListNew.add(clasedatosListNewClasedatosToAttach);
            }
            clasedatosListNew = attachedClasedatosListNew;
            procesos.setClasedatosList(clasedatosListNew);
            List<Procesosorganizaciones> attachedProcesosorganizacionesListNew = new ArrayList<Procesosorganizaciones>();
            for (Procesosorganizaciones procesosorganizacionesListNewProcesosorganizacionesToAttach : procesosorganizacionesListNew) {
                procesosorganizacionesListNewProcesosorganizacionesToAttach = em.getReference(procesosorganizacionesListNewProcesosorganizacionesToAttach.getClass(), procesosorganizacionesListNewProcesosorganizacionesToAttach.getProcesosorganizacionesPK());
                attachedProcesosorganizacionesListNew.add(procesosorganizacionesListNewProcesosorganizacionesToAttach);
            }
            procesosorganizacionesListNew = attachedProcesosorganizacionesListNew;
            procesos.setProcesosorganizacionesList(procesosorganizacionesListNew);
            List<Procesosclasesdatos> attachedProcesosclasesdatosListNew = new ArrayList<Procesosclasesdatos>();
            for (Procesosclasesdatos procesosclasesdatosListNewProcesosclasesdatosToAttach : procesosclasesdatosListNew) {
                procesosclasesdatosListNewProcesosclasesdatosToAttach = em.getReference(procesosclasesdatosListNewProcesosclasesdatosToAttach.getClass(), procesosclasesdatosListNewProcesosclasesdatosToAttach.getProcesosclasesdatosPK());
                attachedProcesosclasesdatosListNew.add(procesosclasesdatosListNewProcesosclasesdatosToAttach);
            }
            procesosclasesdatosListNew = attachedProcesosclasesdatosListNew;
            procesos.setProcesosclasesdatosList(procesosclasesdatosListNew);
            procesos = em.merge(procesos);
            if (subgruposIdOld != null && !subgruposIdOld.equals(subgruposIdNew)) {
                subgruposIdOld.getProcesosList().remove(procesos);
                subgruposIdOld = em.merge(subgruposIdOld);
            }
            if (subgruposIdNew != null && !subgruposIdNew.equals(subgruposIdOld)) {
                subgruposIdNew.getProcesosList().add(procesos);
                subgruposIdNew = em.merge(subgruposIdNew);
            }
            for (Clasedatos clasedatosListOldClasedatos : clasedatosListOld) {
                if (!clasedatosListNew.contains(clasedatosListOldClasedatos)) {
                    clasedatosListOldClasedatos.getProcesosList().remove(procesos);
                    clasedatosListOldClasedatos = em.merge(clasedatosListOldClasedatos);
                }
            }
            for (Clasedatos clasedatosListNewClasedatos : clasedatosListNew) {
                if (!clasedatosListOld.contains(clasedatosListNewClasedatos)) {
                    clasedatosListNewClasedatos.getProcesosList().add(procesos);
                    clasedatosListNewClasedatos = em.merge(clasedatosListNewClasedatos);
                }
            }
            for (Procesosorganizaciones procesosorganizacionesListNewProcesosorganizaciones : procesosorganizacionesListNew) {
                if (!procesosorganizacionesListOld.contains(procesosorganizacionesListNewProcesosorganizaciones)) {
                    Procesos oldProcesosOfProcesosorganizacionesListNewProcesosorganizaciones = procesosorganizacionesListNewProcesosorganizaciones.getProcesos();
                    procesosorganizacionesListNewProcesosorganizaciones.setProcesos(procesos);
                    procesosorganizacionesListNewProcesosorganizaciones = em.merge(procesosorganizacionesListNewProcesosorganizaciones);
                    if (oldProcesosOfProcesosorganizacionesListNewProcesosorganizaciones != null && !oldProcesosOfProcesosorganizacionesListNewProcesosorganizaciones.equals(procesos)) {
                        oldProcesosOfProcesosorganizacionesListNewProcesosorganizaciones.getProcesosorganizacionesList().remove(procesosorganizacionesListNewProcesosorganizaciones);
                        oldProcesosOfProcesosorganizacionesListNewProcesosorganizaciones = em.merge(oldProcesosOfProcesosorganizacionesListNewProcesosorganizaciones);
                    }
                }
            }
            for (Procesosclasesdatos procesosclasesdatosListNewProcesosclasesdatos : procesosclasesdatosListNew) {
                if (!procesosclasesdatosListOld.contains(procesosclasesdatosListNewProcesosclasesdatos)) {
                    Procesos oldProcesosOfProcesosclasesdatosListNewProcesosclasesdatos = procesosclasesdatosListNewProcesosclasesdatos.getProcesos();
                    procesosclasesdatosListNewProcesosclasesdatos.setProcesos(procesos);
                    procesosclasesdatosListNewProcesosclasesdatos = em.merge(procesosclasesdatosListNewProcesosclasesdatos);
                    if (oldProcesosOfProcesosclasesdatosListNewProcesosclasesdatos != null && !oldProcesosOfProcesosclasesdatosListNewProcesosclasesdatos.equals(procesos)) {
                        oldProcesosOfProcesosclasesdatosListNewProcesosclasesdatos.getProcesosclasesdatosList().remove(procesosclasesdatosListNewProcesosclasesdatos);
                        oldProcesosOfProcesosclasesdatosListNewProcesosclasesdatos = em.merge(oldProcesosOfProcesosclasesdatosListNewProcesosclasesdatos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = procesos.getId();
                if (findProcesos(id) == null) {
                    throw new NonexistentEntityException("The procesos with id " + id + " no longer exists.");
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
            Procesos procesos;
            try {
                procesos = em.getReference(Procesos.class, id);
                procesos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The procesos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Procesosorganizaciones> procesosorganizacionesListOrphanCheck = procesos.getProcesosorganizacionesList();
            for (Procesosorganizaciones procesosorganizacionesListOrphanCheckProcesosorganizaciones : procesosorganizacionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesos (" + procesos + ") cannot be destroyed since the Procesosorganizaciones " + procesosorganizacionesListOrphanCheckProcesosorganizaciones + " in its procesosorganizacionesList field has a non-nullable procesos field.");
            }
            List<Procesosclasesdatos> procesosclasesdatosListOrphanCheck = procesos.getProcesosclasesdatosList();
            for (Procesosclasesdatos procesosclasesdatosListOrphanCheckProcesosclasesdatos : procesosclasesdatosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Procesos (" + procesos + ") cannot be destroyed since the Procesosclasesdatos " + procesosclasesdatosListOrphanCheckProcesosclasesdatos + " in its procesosclasesdatosList field has a non-nullable procesos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Subgrupos subgruposId = procesos.getSubgruposId();
            if (subgruposId != null) {
                subgruposId.getProcesosList().remove(procesos);
                subgruposId = em.merge(subgruposId);
            }
            List<Clasedatos> clasedatosList = procesos.getClasedatosList();
            for (Clasedatos clasedatosListClasedatos : clasedatosList) {
                clasedatosListClasedatos.getProcesosList().remove(procesos);
                clasedatosListClasedatos = em.merge(clasedatosListClasedatos);
            }
            em.remove(procesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Procesos> findProcesosEntities() {
        return findProcesosEntities(true, -1, -1);
    }

    public List<Procesos> findProcesosEntities(int maxResults, int firstResult) {
        return findProcesosEntities(false, maxResults, firstResult);
    }

    private List<Procesos> findProcesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Procesos.class));
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

    public Procesos findProcesos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Procesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Procesos> rt = cq.from(Procesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
