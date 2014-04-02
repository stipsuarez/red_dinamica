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
import javax.persistence.Id;
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
 * @author Naturaleza
 */
@Entity
@Table(name = "historial_general")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialGeneral.findAll", query = "SELECT h FROM HistorialGeneral h"),
    @NamedQuery(name = "HistorialGeneral.findByHistGenId", query = "SELECT h FROM HistorialGeneral h WHERE h.histGenId = :histGenId"),
    @NamedQuery(name = "HistorialGeneral.findByHistGenFechaHora", query = "SELECT h FROM HistorialGeneral h WHERE h.histGenFechaHora = :histGenFechaHora"),
    @NamedQuery(name = "HistorialGeneral.findByHistGenVisitas", query = "SELECT h FROM HistorialGeneral h WHERE h.histGenVisitas = :histGenVisitas")})
public class HistorialGeneral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "hist_gen_id")
    private Integer histGenId;
    @Column(name = "hist_gen_fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histGenFechaHora;
    @Column(name = "hist_gen_visitas")
    private Integer histGenVisitas;
    @JoinColumn(name = "hist_proyectos", referencedColumnName = "proyectos_id")
    @ManyToOne(optional = false)
    private Proyectos histProyectos;

    public HistorialGeneral() {
    }

    public HistorialGeneral(Integer histGenId) {
        this.histGenId = histGenId;
    }

    public Integer getHistGenId() {
        return histGenId;
    }

    public void setHistGenId(Integer histGenId) {
        this.histGenId = histGenId;
    }

    public Date getHistGenFechaHora() {
        return histGenFechaHora;
    }

    public void setHistGenFechaHora(Date histGenFechaHora) {
        this.histGenFechaHora = histGenFechaHora;
    }

    public Integer getHistGenVisitas() {
        return histGenVisitas;
    }

    public void setHistGenVisitas(Integer histGenVisitas) {
        this.histGenVisitas = histGenVisitas;
    }

    public Proyectos getHistProyectos() {
        return histProyectos;
    }

    public void setHistProyectos(Proyectos histProyectos) {
        this.histProyectos = histProyectos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (histGenId != null ? histGenId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialGeneral)) {
            return false;
        }
        HistorialGeneral other = (HistorialGeneral) object;
        if ((this.histGenId == null && other.histGenId != null) || (this.histGenId != null && !this.histGenId.equals(other.histGenId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.HistorialGeneral[ histGenId=" + histGenId + " ]";
    }
    
}
