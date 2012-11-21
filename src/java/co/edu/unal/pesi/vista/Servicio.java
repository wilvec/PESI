package co.edu.unal.pesi.vista;

import co.edu.unal.pesi.servicio.ClasedatosJpaController;
import co.edu.unal.pesi.servicio.EntidadJpaController;
import co.edu.unal.pesi.servicio.GruposJpaController;
import co.edu.unal.pesi.servicio.OrganizacionesJpaController;
import co.edu.unal.pesi.servicio.SubgruposJpaController;
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
    
    public SubgruposJpaController getCtrlSubGrupos(){
        if (subgrupos == null) {
            subgrupos = new SubgruposJpaController(emf);
        }
        return subgrupos;
    }
    
    public EntidadJpaController getCtrlEntidad(){
        if(entidad==null){
            entidad=new EntidadJpaController(emf);
        }
        return entidad;
    }
    
    public ClasedatosJpaController getCtrlClaseDatos(){
        if(clasesDatos==null){
            clasesDatos=new ClasedatosJpaController(emf);
        }
        return clasesDatos;
    }
}