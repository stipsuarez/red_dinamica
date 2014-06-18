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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Naturaleza
 */
@Entity
@Table(name = "departamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamentos.findAll", query = "SELECT d FROM Departamentos d"),
    @NamedQuery(name = "Departamentos.findByDepartamentoId", query = "SELECT d FROM Departamentos d WHERE d.departamentoId = :departamentoId"),
    @NamedQuery(name = "Departamentos.findByDepartamentoNombre", query = "SELECT d FROM Departamentos d WHERE d.departamentoNombre = :departamentoNombre")})
public class Departamentos implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadDepartamento")
    private Collection<Ciudad> ciudadCollection;
    @OneToMany(mappedBy = "usrDepartamento")
    private Collection<Usuarios> usuariosCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "departamento_id")
    private Integer departamentoId;
    @Size(max = 45)
    @Column(name = "departamento_nombre")
    private String departamentoNombre;

    public Departamentos() {
    }

    public Departamentos(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (departamentoId != null ? departamentoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamentos)) {
            return false;
        }
        Departamentos other = (Departamentos) object;
        if ((this.departamentoId == null && other.departamentoId != null) || (this.departamentoId != null && !this.departamentoId.equals(other.departamentoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+departamentoNombre;
    }

    @XmlTransient
    public Collection<Ciudad> getCiudadCollection() {
        return ciudadCollection;
    }

    public void setCiudadCollection(Collection<Ciudad> ciudadCollection) {
        this.ciudadCollection = ciudadCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }
    
}
