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
 * @author Miguel
 */
@Entity
@Table(name = "noticias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticias.findAll", query = "SELECT n FROM Noticias n"),
    @NamedQuery(name = "Noticias.findByNoticiaId", query = "SELECT n FROM Noticias n WHERE n.noticiaId = :noticiaId"),
    @NamedQuery(name = "Noticias.findByNoticiaTitulo", query = "SELECT n FROM Noticias n WHERE n.noticiaTitulo = :noticiaTitulo"),
    @NamedQuery(name = "Noticias.findByNoticiaFecha", query = "SELECT n FROM Noticias n WHERE n.noticiaFecha = :noticiaFecha"),
    @NamedQuery(name = "Noticias.findByNoticiaImagen", query = "SELECT n FROM Noticias n WHERE n.noticiaImagen = :noticiaImagen"),
    @NamedQuery(name = "Noticias.findByNoticiaFuente", query = "SELECT n FROM Noticias n WHERE n.noticiaFuente = :noticiaFuente")})
public class Noticias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "noticia_id")
    private Integer noticiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "noticia_titulo")
    private String noticiaTitulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "noticia_fecha")
    @Temporal(TemporalType.DATE)
    private Date noticiaFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "noticia_imagen")
    private String noticiaImagen;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "noticia_descripcion")
    private String noticiaDescripcion;
    @Size(max = 45)
    @Column(name = "noticia_fuente")
    private String noticiaFuente;
    @JoinColumn(name = "noticia_usr_id", referencedColumnName = "usr_id")
    @ManyToOne(optional = false)
    private Usuarios noticiaUsrId;

    public Noticias() {
    }

    public Noticias(Integer noticiaId) {
        this.noticiaId = noticiaId;
    }

    public Noticias(Integer noticiaId, String noticiaTitulo, Date noticiaFecha, String noticiaImagen, String noticiaDescripcion) {
        this.noticiaId = noticiaId;
        this.noticiaTitulo = noticiaTitulo;
        this.noticiaFecha = noticiaFecha;
        this.noticiaImagen = noticiaImagen;
        this.noticiaDescripcion = noticiaDescripcion;
    }

    public Integer getNoticiaId() {
        return noticiaId;
    }

    public void setNoticiaId(Integer noticiaId) {
        this.noticiaId = noticiaId;
    }

    public String getNoticiaTitulo() {
        return noticiaTitulo;
    }

    public void setNoticiaTitulo(String noticiaTitulo) {
        this.noticiaTitulo = noticiaTitulo;
    }

    public Date getNoticiaFecha() {
        return noticiaFecha;
    }

    public void setNoticiaFecha(Date noticiaFecha) {
        this.noticiaFecha = noticiaFecha;
    }

    public String getNoticiaImagen() {
        return noticiaImagen;
    }

    public void setNoticiaImagen(String noticiaImagen) {
        this.noticiaImagen = noticiaImagen;
    }

    public String getNoticiaDescripcion() {
        return noticiaDescripcion;
    }

    public void setNoticiaDescripcion(String noticiaDescripcion) {
        this.noticiaDescripcion = noticiaDescripcion;
    }

    public String getNoticiaFuente() {
        return noticiaFuente;
    }

    public void setNoticiaFuente(String noticiaFuente) {
        this.noticiaFuente = noticiaFuente;
    }

    public Usuarios getNoticiaUsrId() {
        return noticiaUsrId;
    }

    public void setNoticiaUsrId(Usuarios noticiaUsrId) {
        this.noticiaUsrId = noticiaUsrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noticiaId != null ? noticiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticias)) {
            return false;
        }
        Noticias other = (Noticias) object;
        if ((this.noticiaId == null && other.noticiaId != null) || (this.noticiaId != null && !this.noticiaId.equals(other.noticiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Noticias[ noticiaId=" + noticiaId + " ]";
    }
    
}
