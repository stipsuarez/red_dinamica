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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
 * @author Naturaleza
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByUsrCc", query = "SELECT u FROM Usuarios u WHERE u.usrCc = :usrCc"),
    @NamedQuery(name = "Usuarios.findByUsrNombres", query = "SELECT u FROM Usuarios u WHERE u.usrNombres = :usrNombres"),
    @NamedQuery(name = "Usuarios.findByUsrApellidos", query = "SELECT u FROM Usuarios u WHERE u.usrApellidos = :usrApellidos"),
    @NamedQuery(name = "Usuarios.findByUsrEmail", query = "SELECT u FROM Usuarios u WHERE u.usrEmail = :usrEmail"),
    @NamedQuery(name = "Usuarios.findByUsrPass", query = "SELECT u FROM Usuarios u WHERE u.usrPass = :usrPass"),
    @NamedQuery(name = "Usuarios.findByUsrTipo", query = "SELECT u FROM Usuarios u WHERE u.usrTipo = :usrTipo"),
    @NamedQuery(name = "Usuarios.findByUsrSexo", query = "SELECT u FROM Usuarios u WHERE u.usrSexo = :usrSexo"),
    @NamedQuery(name = "Usuarios.findByUsrFechaNacimiento", query = "SELECT u FROM Usuarios u WHERE u.usrFechaNacimiento = :usrFechaNacimiento"),
    @NamedQuery(name = "Usuarios.findByUsrEstado", query = "SELECT u FROM Usuarios u WHERE u.usrEstado = :usrEstado")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_cc")
    private Integer usrCc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usr_nombres")
    private String usrNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usr_apellidos")
    private String usrApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usr_email")
    private String usrEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usr_pass")
    private String usrPass;
    @Column(name = "usr_tipo")
    private Integer usrTipo;
    @Size(max = 30)
    @Column(name = "usr_sexo")
    private String usrSexo;
    @Column(name = "usr_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date usrFechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usr_estado")
    private String usrEstado;
    @ManyToMany(mappedBy = "usuariosCollection")
    private Collection<Proyectos> proyectosCollection;
    @ManyToMany(mappedBy = "usuariosCollection")
    private Collection<Grupos> gruposCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosusrcc")
    private Collection<Amigos> amigosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosusrcc")
    private Collection<Comentarios> comentariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosusrcc")
    private Collection<Eventos> eventosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosusrcc")
    private Collection<Foros> forosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosusrcc")
    private Collection<Solicitudes> solicitudesCollection;
    @JoinColumn(name = "Ciudad_ciudad_id", referencedColumnName = "ciudad_id")
    @ManyToOne(optional = false)
    private Ciudad ciudadciudadid;
    @JoinColumn(name = "Universidades_universidad_id", referencedColumnName = "universidad_id")
    @ManyToOne(optional = false)
    private Universidades universidadesuniversidadid;
    @JoinColumn(name = "Departamentos_departamento_id", referencedColumnName = "departamento_id")
    @ManyToOne(optional = false)
    private Departamentos departamentosdepartamentoid;

    public Usuarios() {
    }

    public Usuarios(Integer usrCc) {
        this.usrCc = usrCc;
    }

    public Usuarios(Integer usrCc, String usrNombres, String usrApellidos, String usrEmail, String usrPass, String usrEstado) {
        this.usrCc = usrCc;
        this.usrNombres = usrNombres;
        this.usrApellidos = usrApellidos;
        this.usrEmail = usrEmail;
        this.usrPass = usrPass;
        this.usrEstado = usrEstado;
    }

    public Integer getUsrCc() {
        return usrCc;
    }

    public void setUsrCc(Integer usrCc) {
        this.usrCc = usrCc;
    }

    public String getUsrNombres() {
        return usrNombres;
    }

    public void setUsrNombres(String usrNombres) {
        this.usrNombres = usrNombres;
    }

    public String getUsrApellidos() {
        return usrApellidos;
    }

    public void setUsrApellidos(String usrApellidos) {
        this.usrApellidos = usrApellidos;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPass() {
        return usrPass;
    }

    public void setUsrPass(String usrPass) {
        this.usrPass = usrPass;
    }

    public Integer getUsrTipo() {
        return usrTipo;
    }

    public void setUsrTipo(Integer usrTipo) {
        this.usrTipo = usrTipo;
    }

    public String getUsrSexo() {
        return usrSexo;
    }

    public void setUsrSexo(String usrSexo) {
        this.usrSexo = usrSexo;
    }

    public Date getUsrFechaNacimiento() {
        return usrFechaNacimiento;
    }

    public void setUsrFechaNacimiento(Date usrFechaNacimiento) {
        this.usrFechaNacimiento = usrFechaNacimiento;
    }

    public String getUsrEstado() {
        return usrEstado;
    }

    public void setUsrEstado(String usrEstado) {
        this.usrEstado = usrEstado;
    }

    @XmlTransient
    public Collection<Proyectos> getProyectosCollection() {
        return proyectosCollection;
    }

    public void setProyectosCollection(Collection<Proyectos> proyectosCollection) {
        this.proyectosCollection = proyectosCollection;
    }

    @XmlTransient
    public Collection<Grupos> getGruposCollection() {
        return gruposCollection;
    }

    public void setGruposCollection(Collection<Grupos> gruposCollection) {
        this.gruposCollection = gruposCollection;
    }

    @XmlTransient
    public Collection<Amigos> getAmigosCollection() {
        return amigosCollection;
    }

    public void setAmigosCollection(Collection<Amigos> amigosCollection) {
        this.amigosCollection = amigosCollection;
    }

    @XmlTransient
    public Collection<Comentarios> getComentariosCollection() {
        return comentariosCollection;
    }

    public void setComentariosCollection(Collection<Comentarios> comentariosCollection) {
        this.comentariosCollection = comentariosCollection;
    }

    @XmlTransient
    public Collection<Eventos> getEventosCollection() {
        return eventosCollection;
    }

    public void setEventosCollection(Collection<Eventos> eventosCollection) {
        this.eventosCollection = eventosCollection;
    }

    @XmlTransient
    public Collection<Foros> getForosCollection() {
        return forosCollection;
    }

    public void setForosCollection(Collection<Foros> forosCollection) {
        this.forosCollection = forosCollection;
    }

    @XmlTransient
    public Collection<Solicitudes> getSolicitudesCollection() {
        return solicitudesCollection;
    }

    public void setSolicitudesCollection(Collection<Solicitudes> solicitudesCollection) {
        this.solicitudesCollection = solicitudesCollection;
    }

    public Ciudad getCiudadciudadid() {
        return ciudadciudadid;
    }

    public void setCiudadciudadid(Ciudad ciudadciudadid) {
        this.ciudadciudadid = ciudadciudadid;
    }

    public Universidades getUniversidadesuniversidadid() {
        return universidadesuniversidadid;
    }

    public void setUniversidadesuniversidadid(Universidades universidadesuniversidadid) {
        this.universidadesuniversidadid = universidadesuniversidadid;
    }

    public Departamentos getDepartamentosdepartamentoid() {
        return departamentosdepartamentoid;
    }

    public void setDepartamentosdepartamentoid(Departamentos departamentosdepartamentoid) {
        this.departamentosdepartamentoid = departamentosdepartamentoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrCc != null ? usrCc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usrCc == null && other.usrCc != null) || (this.usrCc != null && !this.usrCc.equals(other.usrCc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Usuarios[ usrCc=" + usrCc + " ]";
    }
    
}
