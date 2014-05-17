/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "usr_tiene_historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsrTieneHistorial.findAll", query = "SELECT u FROM UsrTieneHistorial u"),
    @NamedQuery(name = "UsrTieneHistorial.findByHistorialUsrId", query = "SELECT u FROM UsrTieneHistorial u WHERE u.usrTieneHistorialPK.historialUsrId = :historialUsrId"),
    @NamedQuery(name = "UsrTieneHistorial.findByHistorialId", query = "SELECT u FROM UsrTieneHistorial u WHERE u.usrTieneHistorialPK.historialId = :historialId"),
    @NamedQuery(name = "UsrTieneHistorial.findByHistorialFecha", query = "SELECT u FROM UsrTieneHistorial u WHERE u.historialFecha = :historialFecha")})
public class UsrTieneHistorial implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsrTieneHistorialPK usrTieneHistorialPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "historial_fecha")
    @Temporal(TemporalType.DATE)
    private Date historialFecha;
    @JoinColumn(name = "historial_usr_id", referencedColumnName = "usr_cc", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuarios usuarios;
    @JoinColumn(name = "historial_id", referencedColumnName = "historial_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Historial historial;

    public UsrTieneHistorial() {
    }

    public UsrTieneHistorial(UsrTieneHistorialPK usrTieneHistorialPK) {
        this.usrTieneHistorialPK = usrTieneHistorialPK;
    }

    public UsrTieneHistorial(UsrTieneHistorialPK usrTieneHistorialPK, Date historialFecha) {
        this.usrTieneHistorialPK = usrTieneHistorialPK;
        this.historialFecha = historialFecha;
    }

    public UsrTieneHistorial(int historialUsrId, int historialId) {
        this.usrTieneHistorialPK = new UsrTieneHistorialPK(historialUsrId, historialId);
    }

    public UsrTieneHistorialPK getUsrTieneHistorialPK() {
        return usrTieneHistorialPK;
    }

    public void setUsrTieneHistorialPK(UsrTieneHistorialPK usrTieneHistorialPK) {
        this.usrTieneHistorialPK = usrTieneHistorialPK;
    }

    public Date getHistorialFecha() {
        return historialFecha;
    }

    public void setHistorialFecha(Date historialFecha) {
        this.historialFecha = historialFecha;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrTieneHistorialPK != null ? usrTieneHistorialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsrTieneHistorial)) {
            return false;
        }
        UsrTieneHistorial other = (UsrTieneHistorial) object;
        if ((this.usrTieneHistorialPK == null && other.usrTieneHistorialPK != null) || (this.usrTieneHistorialPK != null && !this.usrTieneHistorialPK.equals(other.usrTieneHistorialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.UsrTieneHistorial[ usrTieneHistorialPK=" + usrTieneHistorialPK + " ]";
    }
    
}
