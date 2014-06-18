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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Naturaleza
 */
@Entity
@Table(name = "mensaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findByMsjId", query = "SELECT m FROM Mensaje m WHERE m.msjId = :msjId"),
    @NamedQuery(name = "Mensaje.findByMsjFecha", query = "SELECT m FROM Mensaje m WHERE m.msjFecha = :msjFecha"),
    @NamedQuery(name = "Mensaje.findByMsjLeido", query = "SELECT m FROM Mensaje m WHERE m.msjLeido = :msjLeido")})
public class Mensaje implements Serializable {
    @JoinColumn(name = "msj_destinatario", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuarios msjDestinatario;
    @JoinColumn(name = "msj_remitente", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuarios msjRemitente;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "msj_id")
    private Integer msjId;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "msj_texto")
    private String msjTexto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "msj_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date msjFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "msj_leido")
    private boolean msjLeido;
    @JoinColumn(name = "msj_conversacion", referencedColumnName = "conv_id")
    @ManyToOne(optional = false)
    private Conversacion msjConversacion;

    public Mensaje() {
    }

    public Mensaje(Integer msjId) {
        this.msjId = msjId;
    }

    public Mensaje(Integer msjId, Date msjFecha, boolean msjLeido) {
        this.msjId = msjId;
        this.msjFecha = msjFecha;
        this.msjLeido = msjLeido;
    }

    public Integer getMsjId() {
        return msjId;
    }

    public void setMsjId(Integer msjId) {
        this.msjId = msjId;
    }

    public String getMsjTexto() {
        return msjTexto;
    }

    public void setMsjTexto(String msjTexto) {
        this.msjTexto = msjTexto;
    }

    public Date getMsjFecha() {
        return msjFecha;
    }

    public void setMsjFecha(Date msjFecha) {
        this.msjFecha = msjFecha;
    }

    public boolean getMsjLeido() {
        return msjLeido;
    }

    public void setMsjLeido(boolean msjLeido) {
        this.msjLeido = msjLeido;
    }

    public Conversacion getMsjConversacion() {
        return msjConversacion;
    }

    public void setMsjConversacion(Conversacion msjConversacion) {
        this.msjConversacion = msjConversacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msjId != null ? msjId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.msjId == null && other.msjId != null) || (this.msjId != null && !this.msjId.equals(other.msjId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Mensaje[ msjId=" + msjId + " ]";
    }

    public Usuarios getMsjDestinatario() {
        return msjDestinatario;
    }

    public void setMsjDestinatario(Usuarios msjDestinatario) {
        this.msjDestinatario = msjDestinatario;
    }

    public Usuarios getMsjRemitente() {
        return msjRemitente;
    }

    public void setMsjRemitente(Usuarios msjRemitente) {
        this.msjRemitente = msjRemitente;
    }
    
}
