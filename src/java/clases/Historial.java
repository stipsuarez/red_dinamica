/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByHistorialId", query = "SELECT h FROM Historial h WHERE h.historialId = :historialId"),
    @NamedQuery(name = "Historial.findByHistorialDescrip", query = "SELECT h FROM Historial h WHERE h.historialDescrip = :historialDescrip")})
public class Historial implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historial")
    private Collection<UsrTieneHistorial> usrTieneHistorialCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "historial_id")
    private Integer historialId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "historial_descrip")
    private String historialDescrip;

    public Historial() {
    }

    public Historial(Integer historialId) {
        this.historialId = historialId;
    }

    public Historial(Integer historialId, String historialDescrip) {
        this.historialId = historialId;
        this.historialDescrip = historialDescrip;
    }

    public Integer getHistorialId() {
        return historialId;
    }

    public void setHistorialId(Integer historialId) {
        this.historialId = historialId;
    }

    public String getHistorialDescrip() {
        return historialDescrip;
    }

    public void setHistorialDescrip(String historialDescrip) {
        this.historialDescrip = historialDescrip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (historialId != null ? historialId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.historialId == null && other.historialId != null) || (this.historialId != null && !this.historialId.equals(other.historialId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Historial[ historialId=" + historialId + " ]";
    }

    @XmlTransient
    public Collection<UsrTieneHistorial> getUsrTieneHistorialCollection() {
        return usrTieneHistorialCollection;
    }

    public void setUsrTieneHistorialCollection(Collection<UsrTieneHistorial> usrTieneHistorialCollection) {
        this.usrTieneHistorialCollection = usrTieneHistorialCollection;
    }
    
}
