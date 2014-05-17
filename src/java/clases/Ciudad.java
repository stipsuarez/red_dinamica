/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c"),
    @NamedQuery(name = "Ciudad.findByCiudadId", query = "SELECT c FROM Ciudad c WHERE c.ciudadId = :ciudadId"),
    @NamedQuery(name = "Ciudad.findByCiudadNombre", query = "SELECT c FROM Ciudad c WHERE c.ciudadNombre = :ciudadNombre"),
    @NamedQuery(name = "Ciudad.findByCiudadCodigoPostal", query = "SELECT c FROM Ciudad c WHERE c.ciudadCodigoPostal = :ciudadCodigoPostal")})
public class Ciudad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ciudad_id")
    private Integer ciudadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ciudad_nombre")
    private String ciudadNombre;
    @Size(max = 45)
    @Column(name = "ciudad_codigo_postal")
    private String ciudadCodigoPostal;
    @JoinColumn(name = "ciudad_departamento", referencedColumnName = "departamento_id")
    @ManyToOne(optional = false)
    private Departamentos ciudadDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "universidadCiudad")
    private Collection<Universidades> universidadesCollection;
    @OneToMany(mappedBy = "usrCiudad")
    private Collection<Usuarios> usuariosCollection;

    public Ciudad() {
    }

    public Ciudad(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public Ciudad(Integer ciudadId, String ciudadNombre) {
        this.ciudadId = ciudadId;
        this.ciudadNombre = ciudadNombre;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public String getCiudadCodigoPostal() {
        return ciudadCodigoPostal;
    }

    public void setCiudadCodigoPostal(String ciudadCodigoPostal) {
        this.ciudadCodigoPostal = ciudadCodigoPostal;
    }

    public Departamentos getCiudadDepartamento() {
        return ciudadDepartamento;
    }

    public void setCiudadDepartamento(Departamentos ciudadDepartamento) {
        this.ciudadDepartamento = ciudadDepartamento;
    }

    @XmlTransient
    public Collection<Universidades> getUniversidadesCollection() {
        return universidadesCollection;
    }

    public void setUniversidadesCollection(Collection<Universidades> universidadesCollection) {
        this.universidadesCollection = universidadesCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciudadId != null ? ciudadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        if ((this.ciudadId == null && other.ciudadId != null) || (this.ciudadId != null && !this.ciudadId.equals(other.ciudadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Ciudad[ ciudadId=" + ciudadId + " ]";
    }
    
}
