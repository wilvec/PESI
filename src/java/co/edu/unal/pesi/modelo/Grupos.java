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
@Table(name = "grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g"),
    @NamedQuery(name = "Grupos.findById", query = "SELECT g FROM Grupos g WHERE g.id = :id"),
    @NamedQuery(name = "Grupos.findByNombre", query = "SELECT g FROM Grupos g WHERE g.nombre = :nombre")})
public class Grupos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gruposId")
    private List<Subgrupos> subgruposList;
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

    public Grupos() {
    }

    public Grupos(Integer id) {
        this.id = id;
    }

    public Grupos(Integer id, String nombre) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.Grupos[ id=" + id + " ]";
    }

    @XmlTransient
    public List<Subgrupos> getSubgruposList() {
        return subgruposList;
    }

    public void setSubgruposList(List<Subgrupos> subgruposList) {
        this.subgruposList = subgruposList;
    }
    
}
