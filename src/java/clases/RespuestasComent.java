/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Naturaleza
 */
@Entity
@Table(name = "respuestas_coment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestasComent.findAll", query = "SELECT r FROM RespuestasComent r"),
    @NamedQuery(name = "RespuestasComent.findByResComentario", query = "SELECT r FROM RespuestasComent r WHERE r.respuestasComentPK.resComentario = :resComentario"),
    @NamedQuery(name = "RespuestasComent.findByResRespuesta", query = "SELECT r FROM RespuestasComent r WHERE r.respuestasComentPK.resRespuesta = :resRespuesta")})
public class RespuestasComent implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespuestasComentPK respuestasComentPK;
    @JoinColumn(name = "res_usuario", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios resUsuario;
    @JoinColumn(name = "res_respuesta", referencedColumnName = "coment_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comentarios comentarios;
    @JoinColumn(name = "res_comentario", referencedColumnName = "coment_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comentarios comentarios1;

    public RespuestasComent() {
    }

    public RespuestasComent(RespuestasComentPK respuestasComentPK) {
        this.respuestasComentPK = respuestasComentPK;
    }

    public RespuestasComent(int resComentario, int resRespuesta) {
        this.respuestasComentPK = new RespuestasComentPK(resComentario, resRespuesta);
    }

    public RespuestasComentPK getRespuestasComentPK() {
        return respuestasComentPK;
    }

    public void setRespuestasComentPK(RespuestasComentPK respuestasComentPK) {
        this.respuestasComentPK = respuestasComentPK;
    }

    public Usuarios getResUsuario() {
        return resUsuario;
    }

    public void setResUsuario(Usuarios resUsuario) {
        this.resUsuario = resUsuario;
    }

    public Comentarios getComentarios() {
        return comentarios;
    }

    public void setComentarios(Comentarios comentarios) {
        this.comentarios = comentarios;
    }

    public Comentarios getComentarios1() {
        return comentarios1;
    }

    public void setComentarios1(Comentarios comentarios1) {
        this.comentarios1 = comentarios1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respuestasComentPK != null ? respuestasComentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestasComent)) {
            return false;
        }
        RespuestasComent other = (RespuestasComent) object;
        if ((this.respuestasComentPK == null && other.respuestasComentPK != null) || (this.respuestasComentPK != null && !this.respuestasComentPK.equals(other.respuestasComentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.RespuestasComent[ respuestasComentPK=" + respuestasComentPK + " ]";
    }
    
}
