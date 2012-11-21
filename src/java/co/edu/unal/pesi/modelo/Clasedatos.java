/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unal.pesi.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wilvec
 */
@Entity
@Table(name = "clasedatos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasedatos.findAll", query = "SELECT c FROM Clasedatos c"),
    @NamedQuery(name = "Clasedatos.findById", query = "SELECT c FROM Clasedatos c WHERE c.id = :id"),
    @NamedQuery(name = "Clasedatos.findByCodigo", query = "SELECT c FROM Clasedatos c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Clasedatos.findByNombre", query = "SELECT c FROM Clasedatos c WHERE c.nombre = :nombre")})
public class Clasedatos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @JoinTable(name = "procesosclasesdatos", joinColumns = {
        @JoinColumn(name = "clasedatos_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "procesos_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Procesos> procesosList;
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Entidad entidadId;

    public Clasedatos() {
    }

    public Clasedatos(Integer id) {
        this.id = id;
    }

    public Clasedatos(Integer id, String codigo, String nombre) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
    public List<Procesos> getProcesosList() {
        return procesosList;
    }

    public void setProcesosList(List<Procesos> procesosList) {
        this.procesosList = procesosList;
    }

    public Entidad getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Entidad entidadId) {
        this.entidadId = entidadId;
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
        if (!(object instanceof Clasedatos)) {
            return false;
        }
        Clasedatos other = (Clasedatos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.Clasedatos[ id=" + id + " ]";
    }
    
}
