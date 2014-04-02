/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Naturaleza
 */
@Embeddable
public class RespuestasComentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "res_comentario")
    private int resComentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "res_respuesta")
    private int resRespuesta;

    public RespuestasComentPK() {
    }

    public RespuestasComentPK(int resComentario, int resRespuesta) {
        this.resComentario = resComentario;
        this.resRespuesta = resRespuesta;
    }

    public int getResComentario() {
        return resComentario;
    }

    public void setResComentario(int resComentario) {
        this.resComentario = resComentario;
    }

    public int getResRespuesta() {
        return resRespuesta;
    }

    public void setResRespuesta(int resRespuesta) {
        this.resRespuesta = resRespuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) resComentario;
        hash += (int) resRespuesta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestasComentPK)) {
            return false;
        }
        RespuestasComentPK other = (RespuestasComentPK) object;
        if (this.resComentario != other.resComentario) {
            return false;
        }
        if (this.resRespuesta != other.resRespuesta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.RespuestasComentPK[ resComentario=" + resComentario + ", resRespuesta=" + resRespuesta + " ]";
    }
    
}
