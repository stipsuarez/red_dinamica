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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "archivos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivos.findAll", query = "SELECT a FROM Archivos a"),
    @NamedQuery(name = "Archivos.findByArchivoId", query = "SELECT a FROM Archivos a WHERE a.archivoId = :archivoId"),
    @NamedQuery(name = "Archivos.findByArchivoTipo", query = "SELECT a FROM Archivos a WHERE a.archivoTipo = :archivoTipo"),
    @NamedQuery(name = "Archivos.findByArchivoNombre", query = "SELECT a FROM Archivos a WHERE a.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "Archivos.findByArchivoAutor", query = "SELECT a FROM Archivos a WHERE a.archivoAutor = :archivoAutor"),
    @NamedQuery(name = "Archivos.findByArchivoTema", query = "SELECT a FROM Archivos a WHERE a.archivoTema = :archivoTema"),
    @NamedQuery(name = "Archivos.findByArchivoVisitas", query = "SELECT a FROM Archivos a WHERE a.archivoVisitas = :archivoVisitas")})
public class Archivos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "archivo_id")
    private Integer archivoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "archivo_tipo")
    private int archivoTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "archivo_autor")
    private String archivoAutor;
    @Size(max = 100)
    @Column(name = "archivo_tema")
    private String archivoTema;
    @Lob
    @Size(max = 16777215)
    @Column(name = "archivo_resumen")
    private String archivoResumen;
    @Column(name = "archivo_visitas")
    private Integer archivoVisitas;
    @JoinTable(name = "clave_archivo", joinColumns = {
        @JoinColumn(name = "clave_archivo_id", referencedColumnName = "archivo_id")}, inverseJoinColumns = {
        @JoinColumn(name = "clave_clave_id", referencedColumnName = "palabra_id")})
    @ManyToMany
    private Collection<PalabrasClave> palabrasClaveCollection;
    @JoinColumn(name = "archivo_usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuarios archivoUsrId;
    @JoinColumn(name = "archivo_colectivo_id", referencedColumnName = "colect_id")
    @ManyToOne
    private Colectivos archivoColectivoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "historialArchivoId")
    private Collection<HistorialArchivos> historialArchivosCollection;

    public Archivos() {
    }

    public Archivos(Integer archivoId) {
        this.archivoId = archivoId;
    }

    public Archivos(Integer archivoId, int archivoTipo, String archivoNombre, String archivoAutor) {
        this.archivoId = archivoId;
        this.archivoTipo = archivoTipo;
        this.archivoNombre = archivoNombre;
        this.archivoAutor = archivoAutor;
    }

    public Integer getArchivoId() {
        return archivoId;
    }

    public void setArchivoId(Integer archivoId) {
        this.archivoId = archivoId;
    }

    public int getArchivoTipo() {
        return archivoTipo;
    }

    public void setArchivoTipo(int archivoTipo) {
        this.archivoTipo = archivoTipo;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoAutor() {
        return archivoAutor;
    }

    public void setArchivoAutor(String archivoAutor) {
        this.archivoAutor = archivoAutor;
    }

    public String getArchivoTema() {
        return archivoTema;
    }

    public void setArchivoTema(String archivoTema) {
        this.archivoTema = archivoTema;
    }

    public String getArchivoResumen() {
        return archivoResumen;
    }

    public void setArchivoResumen(String archivoResumen) {
        this.archivoResumen = archivoResumen;
    }

    public Integer getArchivoVisitas() {
        return archivoVisitas;
    }

    public void setArchivoVisitas(Integer archivoVisitas) {
        this.archivoVisitas = archivoVisitas;
    }

    @XmlTransient
    public Collection<PalabrasClave> getPalabrasClaveCollection() {
        return palabrasClaveCollection;
    }

    public void setPalabrasClaveCollection(Collection<PalabrasClave> palabrasClaveCollection) {
        this.palabrasClaveCollection = palabrasClaveCollection;
    }

    public Usuarios getArchivoUsrId() {
        return archivoUsrId;
    }

    public void setArchivoUsrId(Usuarios archivoUsrId) {
        this.archivoUsrId = archivoUsrId;
    }

    public Colectivos getArchivoColectivoId() {
        return archivoColectivoId;
    }

    public void setArchivoColectivoId(Colectivos archivoColectivoId) {
        this.archivoColectivoId = archivoColectivoId;
    }

    @XmlTransient
    public Collection<HistorialArchivos> getHistorialArchivosCollection() {
        return historialArchivosCollection;
    }

    public void setHistorialArchivosCollection(Collection<HistorialArchivos> historialArchivosCollection) {
        this.historialArchivosCollection = historialArchivosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (archivoId != null ? archivoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivos)) {
            return false;
        }
        Archivos other = (Archivos) object;
        if ((this.archivoId == null && other.archivoId != null) || (this.archivoId != null && !this.archivoId.equals(other.archivoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Archivos[ archivoId=" + archivoId + " ]";
    }
    
}
