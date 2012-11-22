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
@Table(name = "procesosclasesdatos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procesosclasesdatos.findAll", query = "SELECT p FROM Procesosclasesdatos p"),
    @NamedQuery(name = "Procesosclasesdatos.findByClasedatosId", query = "SELECT p FROM Procesosclasesdatos p WHERE p.procesosclasesdatosPK.clasedatosId = :clasedatosId"),
    @NamedQuery(name = "Procesosclasesdatos.findByProcesosId", query = "SELECT p FROM Procesosclasesdatos p WHERE p.procesosclasesdatosPK.procesosId = :procesosId"),
    @NamedQuery(name = "Procesosclasesdatos.findByTipouso", query = "SELECT p FROM Procesosclasesdatos p WHERE p.tipouso = :tipouso")})
public class Procesosclasesdatos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProcesosclasesdatosPK procesosclasesdatosPK;
    @Basic(optional = false)
    @Column(name = "tipouso")
    private String tipouso;
    @JoinColumn(name = "procesos_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Procesos procesos;
    @JoinColumn(name = "clasedatos_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clasedatos clasedatos;

    public Procesosclasesdatos() {
    }

    public Procesosclasesdatos(ProcesosclasesdatosPK procesosclasesdatosPK) {
        this.procesosclasesdatosPK = procesosclasesdatosPK;
    }

    public Procesosclasesdatos(ProcesosclasesdatosPK procesosclasesdatosPK, String tipouso) {
        this.procesosclasesdatosPK = procesosclasesdatosPK;
        this.tipouso = tipouso;
    }

    public Procesosclasesdatos(int clasedatosId, int procesosId) {
        this.procesosclasesdatosPK = new ProcesosclasesdatosPK(clasedatosId, procesosId);
    }

    public ProcesosclasesdatosPK getProcesosclasesdatosPK() {
        return procesosclasesdatosPK;
    }

    public void setProcesosclasesdatosPK(ProcesosclasesdatosPK procesosclasesdatosPK) {
        this.procesosclasesdatosPK = procesosclasesdatosPK;
    }

    public String getTipouso() {
        return tipouso;
    }

    public void setTipouso(String tipouso) {
        this.tipouso = tipouso;
    }

    public Procesos getProcesos() {
        return procesos;
    }

    public void setProcesos(Procesos procesos) {
        this.procesos = procesos;
    }

    public Clasedatos getClasedatos() {
        return clasedatos;
    }

    public void setClasedatos(Clasedatos clasedatos) {
        this.clasedatos = clasedatos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (procesosclasesdatosPK != null ? procesosclasesdatosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procesosclasesdatos)) {
            return false;
        }
        Procesosclasesdatos other = (Procesosclasesdatos) object;
        if ((this.procesosclasesdatosPK == null && other.procesosclasesdatosPK != null) || (this.procesosclasesdatosPK != null && !this.procesosclasesdatosPK.equals(other.procesosclasesdatosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.Procesosclasesdatos[ procesosclasesdatosPK=" + procesosclasesdatosPK + " ]";
    }
    
}
