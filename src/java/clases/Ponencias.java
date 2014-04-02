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
@Table(name = "ponencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ponencias.findAll", query = "SELECT p FROM Ponencias p"),
    @NamedQuery(name = "Ponencias.findByPonenciaId", query = "SELECT p FROM Ponencias p WHERE p.ponenciaId = :ponenciaId")})
public class Ponencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ponencia_id")
    private Integer ponenciaId;

    public Ponencias() {
    }

    public Ponencias(Integer ponenciaId) {
        this.ponenciaId = ponenciaId;
    }

    public Integer getPonenciaId() {
        return ponenciaId;
    }

    public void setPonenciaId(Integer ponenciaId) {
        this.ponenciaId = ponenciaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ponenciaId != null ? ponenciaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ponencias)) {
            return false;
        }
        Ponencias other = (Ponencias) object;
        if ((this.ponenciaId == null && other.ponenciaId != null) || (this.ponenciaId != null && !this.ponenciaId.equals(other.ponenciaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Ponencias[ ponenciaId=" + ponenciaId + " ]";
    }
    
}
