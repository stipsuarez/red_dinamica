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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Naturaleza
 */
@Entity
@Table(name = "eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventos.findAll", query = "SELECT e FROM Eventos e"),
    @NamedQuery(name = "Eventos.findByEventoId", query = "SELECT e FROM Eventos e WHERE e.eventoId = :eventoId"),
    @NamedQuery(name = "Eventos.findByEventoTitulo", query = "SELECT e FROM Eventos e WHERE e.eventoTitulo = :eventoTitulo"),
    @NamedQuery(name = "Eventos.findByEventoDescripcion", query = "SELECT e FROM Eventos e WHERE e.eventoDescripcion = :eventoDescripcion")})
public class Eventos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "evento_id")
    private Integer eventoId;
    @Size(max = 100)
    @Column(name = "evento_titulo")
    private String eventoTitulo;
    @Size(max = 1000)
    @Column(name = "evento_descripcion")
    private String eventoDescripcion;
    @JoinColumn(name = "Usuarios_usr_cc", referencedColumnName = "usr_cc")
    @ManyToOne(optional = false)
    private Usuarios usuariosusrcc;

    public Eventos() {
    }

    public Eventos(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public String getEventoTitulo() {
        return eventoTitulo;
    }

    public void setEventoTitulo(String eventoTitulo) {
        this.eventoTitulo = eventoTitulo;
    }

    public String getEventoDescripcion() {
        return eventoDescripcion;
    }

    public void setEventoDescripcion(String eventoDescripcion) {
        this.eventoDescripcion = eventoDescripcion;
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
        hash += (eventoId != null ? eventoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.eventoId == null && other.eventoId != null) || (this.eventoId != null && !this.eventoId.equals(other.eventoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Eventos[ eventoId=" + eventoId + " ]";
    }
    
}
