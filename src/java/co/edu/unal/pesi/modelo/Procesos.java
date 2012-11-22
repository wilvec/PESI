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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procesos.findAll", query = "SELECT p FROM Procesos p"),
    @NamedQuery(name = "Procesos.findById", query = "SELECT p FROM Procesos p WHERE p.id = :id"),
    @NamedQuery(name = "Procesos.findByCodigo", query = "SELECT p FROM Procesos p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Procesos.findByNombre", query = "SELECT p FROM Procesos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Procesos.findByTipo", query = "SELECT p FROM Procesos p WHERE p.tipo = :tipo")})
public class Procesos implements Serializable {
    @Lob
    @Column(name = "actividades")
    private String actividades;
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
    @Basic(optional = false)
    @Lob
    @Column(name = "objetivos")
    private String objetivos;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    @ManyToMany(mappedBy = "procesosList")
    private List<Clasedatos> clasedatosList;
    @JoinColumn(name = "subgrupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subgrupos subgruposId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesos")
    private List<Procesosorganizaciones> procesosorganizacionesList;

    public Procesos() {
    }

    public Procesos(Integer id) {
        this.id = id;
    }

    public Procesos(Integer id, String codigo, String nombre, String objetivos, int tipo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.objetivos = objetivos;
        this.tipo = tipo;
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

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Clasedatos> getClasedatosList() {
        return clasedatosList;
    }

    public void setClasedatosList(List<Clasedatos> clasedatosList) {
        this.clasedatosList = clasedatosList;
    }

    public Subgrupos getSubgruposId() {
        return subgruposId;
    }

    public void setSubgruposId(Subgrupos subgruposId) {
        this.subgruposId = subgruposId;
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
        if (!(object instanceof Procesos)) {
            return false;
        }
        Procesos other = (Procesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.unal.pesi.modelo.Procesos[ id=" + id + " ]";
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }
    
}
