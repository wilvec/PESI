package co.edu.unal.pesi.vista;

import co.edu.unal.pesi.modelo.Clasedatos;
import co.edu.unal.pesi.modelo.Entidad;
import co.edu.unal.pesi.modelo.Grupos;
import co.edu.unal.pesi.modelo.Organizaciones;
import co.edu.unal.pesi.modelo.Procesos;
import co.edu.unal.pesi.modelo.Procesosclasesdatos;
import co.edu.unal.pesi.modelo.Procesosorganizaciones;
import co.edu.unal.pesi.modelo.Subgrupos;
import co.edu.unal.pesi.servicio.ClasedatosJpaController;
import co.edu.unal.pesi.servicio.EntidadJpaController;
import co.edu.unal.pesi.servicio.GruposJpaController;
import co.edu.unal.pesi.servicio.OrganizacionesJpaController;
import co.edu.unal.pesi.servicio.ProcesosJpaController;
import co.edu.unal.pesi.servicio.ProcesosclasesdatosJpaController;
import co.edu.unal.pesi.servicio.ProcesosorganizacionesJpaController;
import co.edu.unal.pesi.servicio.SubgruposJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author wilvec
 */
public class Servicio {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PESIPU");
    OrganizacionesJpaController organizacionServicio = null;
    GruposJpaController grupos = null;
    SubgruposJpaController subgrupos = null;
    EntidadJpaController entidad = null;
    ClasedatosJpaController clasesDatos = null;
    ProcesosorganizacionesJpaController procorganizaciones = null;
    ProcesosJpaController procesos = null;
    ProcesosclasesdatosJpaController procCD = null;

    public OrganizacionesJpaController getControlOrganizaciones() {
        if (organizacionServicio == null) {
            organizacionServicio = new OrganizacionesJpaController(emf);
        }
        return organizacionServicio;
    }

    public GruposJpaController getCrlGrupos() {
        if (grupos == null) {
            grupos = new GruposJpaController(emf);
        }
        return grupos;
    }

    public SubgruposJpaController getCtrlSubGrupos() {
        if (subgrupos == null) {
            subgrupos = new SubgruposJpaController(emf);
        }
        return subgrupos;
    }

    public EntidadJpaController getCtrlEntidad() {
        if (entidad == null) {
            entidad = new EntidadJpaController(emf);
        }
        return entidad;
    }

    public ClasedatosJpaController getCtrlClaseDatos() {
        if (clasesDatos == null) {
            clasesDatos = new ClasedatosJpaController(emf);
        }
        return clasesDatos;
    }

    public ProcesosorganizacionesJpaController getCtrlProcOrga() {
        if (procorganizaciones == null) {
            procorganizaciones = new ProcesosorganizacionesJpaController(emf);
        }
        return procorganizaciones;
    }

    public ProcesosJpaController getCtrlProcesos() {
        if (procesos == null) {
            procesos = new ProcesosJpaController(emf);
        }
        return procesos;
    }
    
    public ProcesosclasesdatosJpaController getCtrlPCD(){
        if(procCD == null){
            procCD = new ProcesosclasesdatosJpaController(emf);
        }
        return procCD;
    }

    public List<Grupos> listarGrupos() {
        return getCrlGrupos().findGruposEntities();
    }

    public List<Subgrupos> listarSubgrupos() {
        return getCtrlSubGrupos().findSubgruposEntities();
    }

    public List<Organizaciones> listarOrganizaciones() {
        return getControlOrganizaciones().findOrganizacionesEntities();
    }

    public List<Procesosorganizaciones> listarProcesoOrganizaciones() {
        return getCtrlProcOrga().findProcesosorganizacionesEntities();
    }
    
    public List<Procesosclasesdatos> listarProcesoClasesDeDatos() {
        return getCtrlPCD().findProcesosclasesdatosEntities();
    }
    
    public List<Clasedatos> listarClasedatos(){
        return getCtrlClaseDatos().findClasedatosEntities();
    }
    
    public List<Procesosclasesdatos> listarPCD(){
        return getCtrlPCD().findProcesosclasesdatosEntities();
    }
    
    public List<Entidad> listarEntidad(){
        return getCtrlEntidad().findEntidadEntities();
    }
    
    public List<Procesos> listarProcesos(){
        return getCtrlProcesos().findProcesosEntities();
    }
}
