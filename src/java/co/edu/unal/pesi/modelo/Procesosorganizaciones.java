/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wilvec
 */
@Entity
@Table(name = "procesosorganizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procesosorganizaciones.findAll", query = "SELECT p FROM Procesosorganizaciones p"),
    @NamedQuery(name = "Procesosorganizaciones.findByProcesosId", query = "SELECT p FROM Procesosorganizaciones p WHERE p.procesosorganizacionesPK.procesosId = :procesosId"),
    @NamedQuery(name = "Procesosorganizaciones.findByOrganizacionesId", query = "SELECT p FROM Procesosorganizaciones p WHERE p.procesosorganizacionesPK.organizacionesId = :organizacionesId"),
    @NamedQuery(name = "Procesosorganizaciones.findByResponsabilidad", query = "SELECT p FROM Procesosorganizaciones p WHERE p.responsabilidad = :responsabilidad")})
public class Procesosorganizaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProcesosorganizacionesPK procesosorganizacionesPK;
    @Basic(optional = false)
    @Column(name = "responsabilidad")
    private int responsabilidad;
    @JoinColumn(name = "organizaciones_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Organizaciones organizaciones;
    @JoinColumn(name = "procesos_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Procesos procesos;

    public Procesosorganizaciones() {
    }

    public Procesosorganizaciones(ProcesosorganizacionesPK procesosorganizacionesPK) {
        this.procesosorganizacionesPK = procesosorganizacionesPK;
    }

    public Procesosorganizaciones(ProcesosorganizacionesPK procesosorganizacionesPK, int responsabilidad) {
        this.procesosorganizacionesPK = procesosorganizacionesPK;
        this.responsabilidad = responsabilidad;
    }

    public Procesosorganizaciones(int procesosId, int organizacionesId) {
        this.procesosorganizacionesPK = new ProcesosorganizacionesPK(procesosId, organizacionesId);
    }

    public ProcesosorganizacionesPK getProcesosorganizacionesPK() {
        return procesosorganizacionesPK;
    }

    public void setProcesosorganizacionesPK(ProcesosorganizacionesPK procesosorganizacionesPK) {
        this.procesosorganizacionesPK = procesosorganizacionesPK;
    }

    public int getResponsabilidad() {
        return responsabilidad;
    }

    public void setResponsabilidad(int responsabilidad) {
        this.responsabilidad = responsabilidad;
    }

    public Organizaciones getOrganizaciones() {
        return organizaciones;
    }

    public void setOrganizaciones(Organizaciones organizaciones) {
        this.organizaciones = organizaciones;
    }

    public Procesos getProcesos() {
        return procesos;
    }

    public void setProcesos(Procesos procesos) {
        this.procesos = procesos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procesosorganizacionesPK != null ? procesosorganizacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesosorganizaciones)) {
            return false;
        }
        Procesosorganizaciones other = (Procesosorganizaciones) object;
        if ((this.procesosorganizacionesPK == null && other.procesosorganizacionesPK != null) || (this.procesosorganizacionesPK != null && !this.procesosorganizacionesPK.equals(other.procesosorganizacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.Procesosorganizaciones[ procesosorganizacionesPK=" + procesosorganizacionesPK + " ]";
    }
    
}
