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
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
 * @author Naturaleza
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p"),
    @NamedQuery(name = "Proyectos.findByProyectosId", query = "SELECT p FROM Proyectos p WHERE p.proyectosId = :proyectosId"),
    @NamedQuery(name = "Proyectos.findByProyectoTitulo", query = "SELECT p FROM Proyectos p WHERE p.proyectoTitulo = :proyectoTitulo"),
    @NamedQuery(name = "Proyectos.findByProyectoAutor", query = "SELECT p FROM Proyectos p WHERE p.proyectoAutor = :proyectoAutor"),
    @NamedQuery(name = "Proyectos.findByProyectoFecha", query = "SELECT p FROM Proyectos p WHERE p.proyectoFecha = :proyectoFecha")})
public class Proyectos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "proyectos_id")
    private Integer proyectosId;
    @Size(max = 45)
    @Column(name = "proyecto_titulo")
    private String proyectoTitulo;
    @Size(max = 45)
    @Column(name = "proyecto_autor")
    private String proyectoAutor;
    @Column(name = "proyecto_fecha")
    @Temporal(TemporalType.DATE)
    private Date proyectoFecha;
    @ManyToMany(mappedBy = "proyectosCollection")
    private Collection<Usuarios> usuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "histProyectos")
    private Collection<HistorialGeneral> historialGeneralCollection;

    public Proyectos() {
    }

    public Proyectos(Integer proyectosId) {
        this.proyectosId = proyectosId;
    }

    public Integer getProyectosId() {
        return proyectosId;
    }

    public void setProyectosId(Integer proyectosId) {
        this.proyectosId = proyectosId;
    }

    public String getProyectoTitulo() {
        return proyectoTitulo;
    }

    public void setProyectoTitulo(String proyectoTitulo) {
        this.proyectoTitulo = proyectoTitulo;
    }

    public String getProyectoAutor() {
        return proyectoAutor;
    }

    public void setProyectoAutor(String proyectoAutor) {
        this.proyectoAutor = proyectoAutor;
    }

    public Date getProyectoFecha() {
        return proyectoFecha;
    }

    public void setProyectoFecha(Date proyectoFecha) {
        this.proyectoFecha = proyectoFecha;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @XmlTransient
    public Collection<HistorialGeneral> getHistorialGeneralCollection() {
        return historialGeneralCollection;
    }

    public void setHistorialGeneralCollection(Collection<HistorialGeneral> historialGeneralCollection) {
        this.historialGeneralCollection = historialGeneralCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyectosId != null ? proyectosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.proyectosId == null && other.proyectosId != null) || (this.proyectosId != null && !this.proyectosId.equals(other.proyectosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Proyectos[ proyectosId=" + proyectosId + " ]";
    }
    
}
