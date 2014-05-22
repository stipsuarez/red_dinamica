/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "colectivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colectivos.findAll", query = "SELECT c FROM Colectivos c"),
    @NamedQuery(name = "Colectivos.findByColectId", query = "SELECT c FROM Colectivos c WHERE c.colectId = :colectId"),
    @NamedQuery(name = "Colectivos.findByColectTitulo", query = "SELECT c FROM Colectivos c WHERE c.colectTitulo = :colectTitulo"),
    @NamedQuery(name = "Colectivos.findByColectFecha", query = "SELECT c FROM Colectivos c WHERE c.colectFecha = :colectFecha")})
public class Colectivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "colect_id")
    private Integer colectId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "colect_titulo")
    private String colectTitulo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "colect_descripcion")
    private String colectDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "colect_fecha")
    @Temporal(TemporalType.DATE)
    private Date colectFecha;
    @JoinTable(name = "forma_parte", joinColumns = {
        @JoinColumn(name = "forma_colect_id", referencedColumnName = "colect_id")}, inverseJoinColumns = {
        @JoinColumn(name = "forma_usr_id", referencedColumnName = "usr_id")})
    @ManyToMany
    private Collection<Usuarios> usuariosCollection;
    @JoinColumn(name = "colect_usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuarios colectUsrId;

    public Colectivos() {
    }

    public Colectivos(Integer colectId) {
        this.colectId = colectId;
    }

    public Colectivos(Integer colectId, String colectTitulo, String colectDescripcion, Date colectFecha) {
        this.colectId = colectId;
        this.colectTitulo = colectTitulo;
        this.colectDescripcion = colectDescripcion;
        this.colectFecha = colectFecha;
    }

    public Integer getColectId() {
        return colectId;
    }

    public void setColectId(Integer colectId) {
        this.colectId = colectId;
    }

    public String getColectTitulo() {
        return colectTitulo;
    }

    public void setColectTitulo(String colectTitulo) {
        this.colectTitulo = colectTitulo;
    }

    public String getColectDescripcion() {
        return colectDescripcion;
    }

    public void setColectDescripcion(String colectDescripcion) {
        this.colectDescripcion = colectDescripcion;
    }

    public Date getColectFecha() {
        return colectFecha;
    }

    public void setColectFecha(Date colectFecha) {
        this.colectFecha = colectFecha;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    public Usuarios getColectUsrId() {
        return colectUsrId;
    }

    public void setColectUsrId(Usuarios colectUsrId) {
        this.colectUsrId = colectUsrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colectId != null ? colectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colectivos)) {
            return false;
        }
        Colectivos other = (Colectivos) object;
        if ((this.colectId == null && other.colectId != null) || (this.colectId != null && !this.colectId.equals(other.colectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Colectivos[ colectId=" + colectId + " ]";
    }
    
}
