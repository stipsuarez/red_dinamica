/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "respuestas_coment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestasComent.findAll", query = "SELECT r FROM RespuestasComent r"),
    @NamedQuery(name = "RespuestasComent.findByRespRespuestaId", query = "SELECT r FROM RespuestasComent r WHERE r.respuestasComentPK.respRespuestaId = :respRespuestaId"),
    @NamedQuery(name = "RespuestasComent.findByRespComentId", query = "SELECT r FROM RespuestasComent r WHERE r.respuestasComentPK.respComentId = :respComentId")})
public class RespuestasComent implements Serializable {
    @Size(max = 145)
    @Column(name = "resp_descripcion")
    private String respDescripcion;
    @Column(name = "resp_fecha")
    @Temporal(TemporalType.DATE)
    private Date respFecha;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespuestasComentPK respuestasComentPK;
    @JoinColumn(name = "resp_usr_id", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios respUsrId;
    @JoinColumn(name = "resp_coment_id", referencedColumnName = "coment_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comentarios comentarios;
    @JoinColumn(name = "resp_respuesta_id", referencedColumnName = "coment_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comentarios comentarios1;

    public RespuestasComent() {
    }

    public RespuestasComent(RespuestasComentPK respuestasComentPK) {
        this.respuestasComentPK = respuestasComentPK;
    }

    public RespuestasComent(int respRespuestaId, int respComentId) {
        this.respuestasComentPK = new RespuestasComentPK(respRespuestaId, respComentId);
    }

    public RespuestasComentPK getRespuestasComentPK() {
        return respuestasComentPK;
    }

    public void setRespuestasComentPK(RespuestasComentPK respuestasComentPK) {
        this.respuestasComentPK = respuestasComentPK;
    }

    public Usuarios getRespUsrId() {
        return respUsrId;
    }

    public void setRespUsrId(Usuarios respUsrId) {
        this.respUsrId = respUsrId;
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

    public String getRespDescripcion() {
        return respDescripcion;
    }

    public void setRespDescripcion(String respDescripcion) {
        this.respDescripcion = respDescripcion;
    }

    public Date getRespFecha() {
        return respFecha;
    }

    public void setRespFecha(Date respFecha) {
        this.respFecha = respFecha;
    }
    
}
