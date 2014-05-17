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
 * @author Miguel
 */
@Embeddable
public class UsrTieneHistorialPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "historial_usr_id")
    private int historialUsrId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "historial_id")
    private int historialId;

    public UsrTieneHistorialPK() {
    }

    public UsrTieneHistorialPK(int historialUsrId, int historialId) {
        this.historialUsrId = historialUsrId;
        this.historialId = historialId;
    }

    public int getHistorialUsrId() {
        return historialUsrId;
    }

    public void setHistorialUsrId(int historialUsrId) {
        this.historialUsrId = historialUsrId;
    }

    public int getHistorialId() {
        return historialId;
    }

    public void setHistorialId(int historialId) {
        this.historialId = historialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) historialUsrId;
        hash += (int) historialId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsrTieneHistorialPK)) {
            return false;
        }
        UsrTieneHistorialPK other = (UsrTieneHistorialPK) object;
        if (this.historialUsrId != other.historialUsrId) {
            return false;
        }
        if (this.historialId != other.historialId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.UsrTieneHistorialPK[ historialUsrId=" + historialUsrId + ", historialId=" + historialId + " ]";
    }
    
}
