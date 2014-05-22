/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "comentarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentarios.findAll", query = "SELECT c FROM Comentarios c"),
    @NamedQuery(name = "Comentarios.findByComentId", query = "SELECT c FROM Comentarios c WHERE c.comentId = :comentId"),
    @NamedQuery(name = "Comentarios.findByComentDescripcion", query = "SELECT c FROM Comentarios c WHERE c.comentDescripcion = :comentDescripcion"),
    @NamedQuery(name = "Comentarios.findByComentFecha", query = "SELECT c FROM Comentarios c WHERE c.comentFecha = :comentFecha")})
public class Comentarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coment_id")
    private Integer comentId;
    @Size(max = 700)
    @Column(name = "coment_descripcion")
    private String comentDescripcion;
    @Column(name = "coment_fecha")
    @Temporal(TemporalType.DATE)
    private Date comentFecha;
    @JoinColumn(name = "coment_foro_id", referencedColumnName = "foro_id")
    @ManyToOne(optional = false)
    private Foros comentForoId;
    @JoinColumn(name = "coment_usr_id", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios comentUsrId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comentarios")
    private Collection<RespuestasComent> respuestasComentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comentarios1")
    private Collection<RespuestasComent> respuestasComentCollection1;

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

    public Date getComentFecha() {
        return comentFecha;
    }

    public void setComentFecha(Date comentFecha) {
        this.comentFecha = comentFecha;
    }

    public Foros getComentForoId() {
        return comentForoId;
    }

    public void setComentForoId(Foros comentForoId) {
        this.comentForoId = comentForoId;
    }

    public Usuarios getComentUsrId() {
        return comentUsrId;
    }

    public void setComentUsrId(Usuarios comentUsrId) {
        this.comentUsrId = comentUsrId;
    }

    @XmlTransient
    public Collection<RespuestasComent> getRespuestasComentCollection() {
        return respuestasComentCollection;
    }

    public void setRespuestasComentCollection(Collection<RespuestasComent> respuestasComentCollection) {
        this.respuestasComentCollection = respuestasComentCollection;
    }

    @XmlTransient
    public Collection<RespuestasComent> getRespuestasComentCollection1() {
        return respuestasComentCollection1;
    }

    public void setRespuestasComentCollection1(Collection<RespuestasComent> respuestasComentCollection1) {
        this.respuestasComentCollection1 = respuestasComentCollection1;
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
