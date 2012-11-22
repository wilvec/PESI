/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author wilvec
 */
@Embeddable
public class ProcesosclasesdatosPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "clasedatos_id")
    private int clasedatosId;
    @Basic(optional = false)
    @Column(name = "procesos_id")
    private int procesosId;

    public ProcesosclasesdatosPK() {
    }

    public ProcesosclasesdatosPK(int clasedatosId, int procesosId) {
        this.clasedatosId = clasedatosId;
        this.procesosId = procesosId;
    }

    public int getClasedatosId() {
        return clasedatosId;
    }

    public void setClasedatosId(int clasedatosId) {
        this.clasedatosId = clasedatosId;
    }

    public int getProcesosId() {
        return procesosId;
    }

    public void setProcesosId(int procesosId) {
        this.procesosId = procesosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clasedatosId;
        hash += (int) procesosId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesosclasesdatosPK)) {
            return false;
        }
        ProcesosclasesdatosPK other = (ProcesosclasesdatosPK) object;
        if (this.clasedatosId != other.clasedatosId) {
            return false;
        }
        if (this.procesosId != other.procesosId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.ProcesosclasesdatosPK[ clasedatosId=" + clasedatosId + ", procesosId=" + procesosId + " ]";
    }
    
}
