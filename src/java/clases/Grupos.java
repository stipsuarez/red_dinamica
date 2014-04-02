/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Naturaleza
 */
@Entity
@Table(name = "grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupos.findAll", query = "SELECT g FROM Grupos g"),
    @NamedQuery(name = "Grupos.findByGrupoId", query = "SELECT g FROM Grupos g WHERE g.grupoId = :grupoId"),
    @NamedQuery(name = "Grupos.findByGrupoNombre", query = "SELECT g FROM Grupos g WHERE g.grupoNombre = :grupoNombre"),
    @NamedQuery(name = "Grupos.findByGrupoTipo", query = "SELECT g FROM Grupos g WHERE g.grupoTipo = :grupoTipo"),
    @NamedQuery(name = "Grupos.findByGruposcol", query = "SELECT g FROM Grupos g WHERE g.gruposcol = :gruposcol")})
public class Grupos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "grupo_id")
    private Integer grupoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "grupo_nombre")
    private String grupoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grupo_tipo")
    private int grupoTipo;
    @Size(max = 45)
    @Column(name = "Gruposcol")
    private String gruposcol;
    @ManyToMany(mappedBy = "gruposCollection")
    private Collection<Usuarios> usuariosCollection;

    public Grupos() {
    }

    public Grupos(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public Grupos(Integer grupoId, String grupoNombre, int grupoTipo) {
        this.grupoId = grupoId;
        this.grupoNombre = grupoNombre;
        this.grupoTipo = grupoTipo;
    }

    public Integer getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public String getGrupoNombre() {
        return grupoNombre;
    }

    public void setGrupoNombre(String grupoNombre) {
        this.grupoNombre = grupoNombre;
    }

    public int getGrupoTipo() {
        return grupoTipo;
    }

    public void setGrupoTipo(int grupoTipo) {
        this.grupoTipo = grupoTipo;
    }

    public String getGruposcol() {
        return gruposcol;
    }

    public void setGruposcol(String gruposcol) {
        this.gruposcol = gruposcol;
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
        hash += (grupoId != null ? grupoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.grupoId == null && other.grupoId != null) || (this.grupoId != null && !this.grupoId.equals(other.grupoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Grupos[ grupoId=" + grupoId + " ]";
    }
    
}
