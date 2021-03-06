/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "grupos_investiga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruposInvestiga.findAll", query = "SELECT g FROM GruposInvestiga g"),
    @NamedQuery(name = "GruposInvestiga.findByGruposInvestigaId", query = "SELECT g FROM GruposInvestiga g WHERE g.gruposInvestigaPK.gruposInvestigaId = :gruposInvestigaId"),
    @NamedQuery(name = "GruposInvestiga.findByGruposInvestigaNombre", query = "SELECT g FROM GruposInvestiga g WHERE g.gruposInvestigaNombre = :gruposInvestigaNombre"),
    @NamedQuery(name = "GruposInvestiga.findByGruposInvestigaUniversidad", query = "SELECT g FROM GruposInvestiga g WHERE g.gruposInvestigaPK.gruposInvestigaUniversidad = :gruposInvestigaUniversidad"),
    @NamedQuery(name = "GruposInvestiga.findByGruposInvestigaDescripcion", query = "SELECT g FROM GruposInvestiga g WHERE g.gruposInvestigaDescripcion = :gruposInvestigaDescripcion")})
public class GruposInvestiga implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruposInvestigaPK gruposInvestigaPK;
    @Size(max = 45)
    @Column(name = "grupos_investiga_nombre")
    private String gruposInvestigaNombre;
    @Size(max = 100)
    @Column(name = "grupos_investiga_descripcion")
    private String gruposInvestigaDescripcion;
    @JoinColumn(name = "grupos_investiga_universidad", referencedColumnName = "universidad_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Universidades universidades;

    public GruposInvestiga() {
    }

    public GruposInvestiga(GruposInvestigaPK gruposInvestigaPK) {
        this.gruposInvestigaPK = gruposInvestigaPK;
    }

    public GruposInvestiga(int gruposInvestigaId, int gruposInvestigaUniversidad) {
        this.gruposInvestigaPK = new GruposInvestigaPK(gruposInvestigaId, gruposInvestigaUniversidad);
    }

    public GruposInvestigaPK getGruposInvestigaPK() {
        return gruposInvestigaPK;
    }

    public void setGruposInvestigaPK(GruposInvestigaPK gruposInvestigaPK) {
        this.gruposInvestigaPK = gruposInvestigaPK;
    }

    public String getGruposInvestigaNombre() {
        return gruposInvestigaNombre;
    }

    public void setGruposInvestigaNombre(String gruposInvestigaNombre) {
        this.gruposInvestigaNombre = gruposInvestigaNombre;
    }

    public String getGruposInvestigaDescripcion() {
        return gruposInvestigaDescripcion;
    }

    public void setGruposInvestigaDescripcion(String gruposInvestigaDescripcion) {
        this.gruposInvestigaDescripcion = gruposInvestigaDescripcion;
    }

    public Universidades getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Universidades universidades) {
        this.universidades = universidades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruposInvestigaPK != null ? gruposInvestigaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruposInvestiga)) {
            return false;
        }
        GruposInvestiga other = (GruposInvestiga) object;
        if ((this.gruposInvestigaPK == null && other.gruposInvestigaPK != null) || (this.gruposInvestigaPK != null && !this.gruposInvestigaPK.equals(other.gruposInvestigaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.GruposInvestiga[ gruposInvestigaPK=" + gruposInvestigaPK + " ]";
    }
    
}
