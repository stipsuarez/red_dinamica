/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Naturaleza
 */
@Entity
@Table(name = "amigos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amigos.findAll", query = "SELECT a FROM Amigos a"),
    @NamedQuery(name = "Amigos.findByAmigoId", query = "SELECT a FROM Amigos a WHERE a.amigoId = :amigoId"),
    @NamedQuery(name = "Amigos.findByAmigoCc", query = "SELECT a FROM Amigos a WHERE a.amigoCc = :amigoCc")})
public class Amigos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "amigo_id")
    private Integer amigoId;
    @Column(name = "amigo_cc")
    private Integer amigoCc;
    @JoinColumn(name = "Usuarios_usr_cc", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios usuariosusrcc;

    public Amigos() {
    }

    public Amigos(Integer amigoId) {
        this.amigoId = amigoId;
    }

    public Integer getAmigoId() {
        return amigoId;
    }

    public void setAmigoId(Integer amigoId) {
        this.amigoId = amigoId;
    }

    public Integer getAmigoCc() {
        return amigoCc;
    }

    public void setAmigoCc(Integer amigoCc) {
        this.amigoCc = amigoCc;
    }

    public Usuarios getUsuariosusrcc() {
        return usuariosusrcc;
    }

    public void setUsuariosusrcc(Usuarios usuariosusrcc) {
        this.usuariosusrcc = usuariosusrcc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (amigoId != null ? amigoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amigos)) {
            return false;
        }
        Amigos other = (Amigos) object;
        if ((this.amigoId == null && other.amigoId != null) || (this.amigoId != null && !this.amigoId.equals(other.amigoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Amigos[ amigoId=" + amigoId + " ]";
    }
    
}
