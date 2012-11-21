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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "subgrupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subgrupos.findAll", query = "SELECT s FROM Subgrupos s"),
    @NamedQuery(name = "Subgrupos.findById", query = "SELECT s FROM Subgrupos s WHERE s.id = :id"),
    @NamedQuery(name = "Subgrupos.findByNombre", query = "SELECT s FROM Subgrupos s WHERE s.nombre = :nombre")})
public class Subgrupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subgruposId")
    private List<Procesos> procesosList;
    @JoinColumn(name = "grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Grupos gruposId;

    public Subgrupos() {
    }

    public Subgrupos(Integer id) {
        this.id = id;
    }

    public Subgrupos(Integer id, String nombre) {
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

    @XmlTransient
    public List<Procesos> getProcesosList() {
        return procesosList;
    }

    public void setProcesosList(List<Procesos> procesosList) {
        this.procesosList = procesosList;
    }

    public Grupos getGruposId() {
        return gruposId;
    }

    public void setGruposId(Grupos gruposId) {
        this.gruposId = gruposId;
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
        if (!(object instanceof Subgrupos)) {
            return false;
        }
        Subgrupos other = (Subgrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.Subgrupos[ id=" + id + " ]";
    }
    
}
