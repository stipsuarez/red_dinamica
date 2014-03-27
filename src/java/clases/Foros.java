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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Naturaleza
 */
@Entity
@Table(name = "foros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foros.findAll", query = "SELECT f FROM Foros f"),
    @NamedQuery(name = "Foros.findByForoId", query = "SELECT f FROM Foros f WHERE f.foroId = :foroId"),
    @NamedQuery(name = "Foros.findByForoNombre", query = "SELECT f FROM Foros f WHERE f.foroNombre = :foroNombre"),
    @NamedQuery(name = "Foros.findByForoTema", query = "SELECT f FROM Foros f WHERE f.foroTema = :foroTema"),
    @NamedQuery(name = "Foros.findByForoFecha", query = "SELECT f FROM Foros f WHERE f.foroFecha = :foroFecha"),
    @NamedQuery(name = "Foros.findByForoDescripcion", query = "SELECT f FROM Foros f WHERE f.foroDescripcion = :foroDescripcion")})
public class Foros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "foro_id")
    private Integer foroId;
    @Size(max = 45)
    @Column(name = "foro_nombre")
    private String foroNombre;
    @Size(max = 45)
    @Column(name = "foro_tema")
    private String foroTema;
    @Size(max = 45)
    @Column(name = "foro_fecha")
    private String foroFecha;
    @Size(max = 700)
    @Column(name = "foro_descripcion")
    private String foroDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forosforoid")
    private Collection<Comentarios> comentariosCollection;
    @JoinColumn(name = "Usuarios_usr_cc", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios usuariosusrcc;

    public Foros() {
    }

    public Foros(Integer foroId) {
        this.foroId = foroId;
    }

    public Integer getForoId() {
        return foroId;
    }

    public void setForoId(Integer foroId) {
        this.foroId = foroId;
    }

    public String getForoNombre() {
        return foroNombre;
    }

    public void setForoNombre(String foroNombre) {
        this.foroNombre = foroNombre;
    }

    public String getForoTema() {
        return foroTema;
    }

    public void setForoTema(String foroTema) {
        this.foroTema = foroTema;
    }

    public String getForoFecha() {
        return foroFecha;
    }

    public void setForoFecha(String foroFecha) {
        this.foroFecha = foroFecha;
    }

    public String getForoDescripcion() {
        return foroDescripcion;
    }

    public void setForoDescripcion(String foroDescripcion) {
        this.foroDescripcion = foroDescripcion;
    }

    @XmlTransient
    public Collection<Comentarios> getComentariosCollection() {
        return comentariosCollection;
    }

    public void setComentariosCollection(Collection<Comentarios> comentariosCollection) {
        this.comentariosCollection = comentariosCollection;
    }

    public Usuarios getUsuariosusrcc() {
        return usuariosusrcc;
    }

    public void setUsuariosusrcc(Usuarios usuariosusrcc) {
        this.usuariosusrcc = usuariosusrcc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foroId != null ? foroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foros)) {
            return false;
        }
        Foros other = (Foros) object;
        if ((this.foroId == null && other.foroId != null) || (this.foroId != null && !this.foroId.equals(other.foroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Foros[ foroId=" + foroId + " ]";
    }
    
}