/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wilvec
 */
@Entity
@Table(name = "organizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizaciones.findAll", query = "SELECT o FROM Organizaciones o"),
    @NamedQuery(name = "Organizaciones.findById", query = "SELECT o FROM Organizaciones o WHERE o.id = :id"),
    @NamedQuery(name = "Organizaciones.findByNombre", query = "SELECT o FROM Organizaciones o WHERE o.nombre = :nombre")})
public class Organizaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizaciones")
    private List<Procesosorganizaciones> procesosorganizacionesList;

    public Organizaciones() {
    }

    public Organizaciones(Integer id) {
        this.id = id;
    }

    public Organizaciones(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Procesosorganizaciones> getProcesosorganizacionesList() {
        return procesosorganizacionesList;
    }

    public void setProcesosorganizacionesList(List<Procesosorganizaciones> procesosorganizacionesList) {
        this.procesosorganizacionesList = procesosorganizacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organizaciones)) {
            return false;
        }
        Organizaciones other = (Organizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.Organizaciones[ id=" + id + " ]";
    }
    
}
