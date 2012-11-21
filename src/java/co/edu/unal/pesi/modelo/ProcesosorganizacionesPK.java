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
public class ProcesosorganizacionesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "procesos_id")
    private int procesosId;
    @Basic(optional = false)
    @Column(name = "organizaciones_id")
    private int organizacionesId;

    public ProcesosorganizacionesPK() {
    }

    public ProcesosorganizacionesPK(int procesosId, int organizacionesId) {
        this.procesosId = procesosId;
        this.organizacionesId = organizacionesId;
    }

    public int getProcesosId() {
        return procesosId;
    }

    public void setProcesosId(int procesosId) {
        this.procesosId = procesosId;
    }

    public int getOrganizacionesId() {
        return organizacionesId;
    }

    public void setOrganizacionesId(int organizacionesId) {
        this.organizacionesId = organizacionesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) procesosId;
        hash += (int) organizacionesId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesosorganizacionesPK)) {
            return false;
        }
        ProcesosorganizacionesPK other = (ProcesosorganizacionesPK) object;
        if (this.procesosId != other.procesosId) {
            return false;
        }
        if (this.organizacionesId != other.organizacionesId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.ProcesosorganizacionesPK[ procesosId=" + procesosId + ", organizacionesId=" + organizacionesId + " ]";
    }
    
}
