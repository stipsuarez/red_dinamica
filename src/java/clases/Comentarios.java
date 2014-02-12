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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
 * @author Naturaleza
 */
@Entity
@Table(name = "comentarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentarios.findAll", query = "SELECT c FROM Comentarios c"),
    @NamedQuery(name = "Comentarios.findByComentId", query = "SELECT c FROM Comentarios c WHERE c.comentId = :comentId"),
    @NamedQuery(name = "Comentarios.findByComentDescripcion", query = "SELECT c FROM Comentarios c WHERE c.comentDescripcion = :comentDescripcion"),
    @NamedQuery(name = "Comentarios.findByComentFechaHora", query = "SELECT c FROM Comentarios c WHERE c.comentFechaHora = :comentFechaHora")})
public class Comentarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "coment_id")
    private Integer comentId;
    @Size(max = 700)
    @Column(name = "coment_descripcion")
    private String comentDescripcion;
    @Column(name = "coment_fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date comentFechaHora;
    @JoinTable(name = "comentarios_has_comentarios", joinColumns = {
        @JoinColumn(name = "Comentarios_coment_id", referencedColumnName = "coment_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Comentarios_coment_id1", referencedColumnName = "coment_id")})
    @ManyToMany
    private Collection<Comentarios> comentariosCollection;
    @ManyToMany(mappedBy = "comentariosCollection")
    private Collection<Comentarios> comentariosCollection1;
    @JoinColumn(name = "Usuarios_usr_cc", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios usuariosusrcc;
    @JoinColumn(name = "Foros_foro_id", referencedColumnName = "foro_id")
    @ManyToOne(optional = false)
    private Foros forosforoid;

    public Comentarios() {
    }

    public Comentarios(Integer comentId) {
        this.comentId = comentId;
    }

    public Integer getComentId() {
        return comentId;
    }

    public void setComentId(Integer comentId) {
        this.comentId = comentId;
    }

    public String getComentDescripcion() {
        return comentDescripcion;
    }

    public void setComentDescripcion(String comentDescripcion) {
        this.comentDescripcion = comentDescripcion;
    }

    public Date getComentFechaHora() {
        return comentFechaHora;
    }

    public void setComentFechaHora(Date comentFechaHora) {
        this.comentFechaHora = comentFechaHora;
    }

    @XmlTransient
    public Collection<Comentarios> getComentariosCollection() {
        return comentariosCollection;
    }

    public void setComentariosCollection(Collection<Comentarios> comentariosCollection) {
        this.comentariosCollection = comentariosCollection;
    }

    @XmlTransient
    public Collection<Comentarios> getComentariosCollection1() {
        return comentariosCollection1;
    }

    public void setComentariosCollection1(Collection<Comentarios> comentariosCollection1) {
        this.comentariosCollection1 = comentariosCollection1;
    }

    public Usuarios getUsuariosusrcc() {
        return usuariosusrcc;
    }

    public void setUsuariosusrcc(Usuarios usuariosusrcc) {
        this.usuariosusrcc = usuariosusrcc;
    }

    public Foros getForosforoid() {
        return forosforoid;
    }

    public void setForosforoid(Foros forosforoid) {
        this.forosforoid = forosforoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comentId != null ? comentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentarios)) {
            return false;
        }
        Comentarios other = (Comentarios) object;
        if ((this.comentId == null && other.comentId != null) || (this.comentId != null && !this.comentId.equals(other.comentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Comentarios[ comentId=" + comentId + " ]";
    }
    
}
