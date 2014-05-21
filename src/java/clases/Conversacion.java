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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "conversacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversacion.findAll", query = "SELECT c FROM Conversacion c"),
    @NamedQuery(name = "Conversacion.findByConvId", query = "SELECT c FROM Conversacion c WHERE c.convId = :convId"),
    @NamedQuery(name = "Conversacion.findByConvAsunto", query = "SELECT c FROM Conversacion c WHERE c.convAsunto = :convAsunto"),
    @NamedQuery(name = "Conversacion.findByUltimo", query = "SELECT c FROM Conversacion c WHERE c.ultimo = :ultimo"),
    @NamedQuery(name = "Conversacion.findByConvNumero", query = "SELECT c FROM Conversacion c WHERE c.convNumero = :convNumero")})
public class Conversacion implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "conv_ultimo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date convUltimo;
    @JoinColumn(name = "conv_usr2_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuarios convUsr2Id;
    @JoinColumn(name = "conv_usr1_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuarios convUsr1Id;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conv_id")
    private Integer convId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "conv_asunto")
    private String convAsunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ultimo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conv_numero")
    private int convNumero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "msjConversacion")
    private Collection<Mensaje> mensajeCollection;
    @JoinColumn(name = "conv_usr1", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios convUsr1;
    @JoinColumn(name = "conv_usr2", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios convUsr2;

    public Conversacion() {
    }

    public Conversacion(Integer convId) {
        this.convId = convId;
    }

    public Conversacion(Integer convId, String convAsunto, Date ultimo, int convNumero) {
        this.convId = convId;
        this.convAsunto = convAsunto;
        this.ultimo = ultimo;
        this.convNumero = convNumero;
    }

    public Integer getConvId() {
        return convId;
    }

    public void setConvId(Integer convId) {
        this.convId = convId;
    }

    public String getConvAsunto() {
        return convAsunto;
    }

    public void setConvAsunto(String convAsunto) {
        this.convAsunto = convAsunto;
    }

    public Date getUltimo() {
        return ultimo;
    }

    public void setUltimo(Date ultimo) {
        this.ultimo = ultimo;
    }

    public int getConvNumero() {
        return convNumero;
    }

    public void setConvNumero(int convNumero) {
        this.convNumero = convNumero;
    }

    @XmlTransient
    public Collection<Mensaje> getMensajeCollection() {
        return mensajeCollection;
    }

    public void setMensajeCollection(Collection<Mensaje> mensajeCollection) {
        this.mensajeCollection = mensajeCollection;
    }

    public Usuarios getConvUsr1() {
        return convUsr1;
    }

    public void setConvUsr1(Usuarios convUsr1) {
        this.convUsr1 = convUsr1;
    }

    public Usuarios getConvUsr2() {
        return convUsr2;
    }

    public void setConvUsr2(Usuarios convUsr2) {
        this.convUsr2 = convUsr2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (convId != null ? convId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversacion)) {
            return false;
        }
        Conversacion other = (Conversacion) object;
        if ((this.convId == null && other.convId != null) || (this.convId != null && !this.convId.equals(other.convId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Conversacion[ convId=" + convId + " ]";
    }

    public Date getConvUltimo() {
        return convUltimo;
    }

    public void setConvUltimo(Date convUltimo) {
        this.convUltimo = convUltimo;
    }

    public Usuarios getConvUsr2Id() {
        return convUsr2Id;
    }

    public void setConvUsr2Id(Usuarios convUsr2Id) {
        this.convUsr2Id = convUsr2Id;
    }

    public Usuarios getConvUsr1Id() {
        return convUsr1Id;
    }

    public void setConvUsr1Id(Usuarios convUsr1Id) {
        this.convUsr1Id = convUsr1Id;
    }
    
}
