/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author Naturaleza
 */
@Entity
@Table(name = "solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudes.findAll", query = "SELECT s FROM Solicitudes s"),
    @NamedQuery(name = "Solicitudes.findBySolicitudId", query = "SELECT s FROM Solicitudes s WHERE s.solicitudId = :solicitudId"),
    @NamedQuery(name = "Solicitudes.findBySolicitudCc", query = "SELECT s FROM Solicitudes s WHERE s.solicitudCc = :solicitudCc"),
    @NamedQuery(name = "Solicitudes.findBySolicitudFecha", query = "SELECT s FROM Solicitudes s WHERE s.solicitudFecha = :solicitudFecha"),
    @NamedQuery(name = "Solicitudes.findBySolicitudEstado", query = "SELECT s FROM Solicitudes s WHERE s.solicitudEstado = :solicitudEstado")})
public class Solicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "solicitud_id")
    private Integer solicitudId;
    @Column(name = "solicitud_cc")
    private Integer solicitudCc;
    @Column(name = "solicitud_fecha")
    @Temporal(TemporalType.DATE)
    private Date solicitudFecha;
    @Size(max = 30)
    @Column(name = "solicitud_estado")
    private String solicitudEstado;
    @JoinColumn(name = "solicitud_enviada_a", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios solicitudEnviadaA;

    public Solicitudes() {
    }

    public Solicitudes(Integer solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Integer getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(Integer solicitudId) {
        this.solicitudId = solicitudId;
    }

    public Integer getSolicitudCc() {
        return solicitudCc;
    }

    public void setSolicitudCc(Integer solicitudCc) {
        this.solicitudCc = solicitudCc;
    }

    public Date getSolicitudFecha() {
        return solicitudFecha;
    }

    public void setSolicitudFecha(Date solicitudFecha) {
        this.solicitudFecha = solicitudFecha;
    }

    public String getSolicitudEstado() {
        return solicitudEstado;
    }

    public void setSolicitudEstado(String solicitudEstado) {
        this.solicitudEstado = solicitudEstado;
    }

    public Usuarios getSolicitudEnviadaA() {
        return solicitudEnviadaA;
    }

    public void setSolicitudEnviadaA(Usuarios solicitudEnviadaA) {
        this.solicitudEnviadaA = solicitudEnviadaA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudId != null ? solicitudId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudes)) {
            return false;
        }
        Solicitudes other = (Solicitudes) object;
        if ((this.solicitudId == null && other.solicitudId != null) || (this.solicitudId != null && !this.solicitudId.equals(other.solicitudId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Solicitudes[ solicitudId=" + solicitudId + " ]";
    }
    
}
