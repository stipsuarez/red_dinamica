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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    @NamedQuery(name = "Usuarios.findByUsrSexo", query = "SELECT u FROM Usuarios u WHERE u.usrSexo = :usrSexo"),
    @NamedQuery(name = "Usuarios.findByUsrTipo", query = "SELECT u FROM Usuarios u WHERE u.usrTipo = :usrTipo"),
    @NamedQuery(name = "Usuarios.findByUsrEstado", query = "SELECT u FROM Usuarios u WHERE u.usrEstado = :usrEstado")})
public class Usuarios implements Serializable {
    @Column(name = "usr_foto")
    private Boolean usrFoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudEnviadaA")
    private Collection<Solicitudes> solicitudesCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @Size(max = 30)
    @Column(name = "usr_sexo")
    private String usrSexo;
    @Column(name = "usr_tipo")
    private Integer usrTipo;
    @Size(max = 30)
    @Column(name = "usr_estado")
    private String usrEstado;
    @JoinTable(name = "contactos", joinColumns = {
        @JoinColumn(name = "cont_usuario", referencedColumnName = "usr_cc")}, inverseJoinColumns = {
        @JoinColumn(name = "cont_contacto", referencedColumnName = "usr_cc")})
    @ManyToMany
    private Collection<Usuarios> usuariosCollection;
    @ManyToMany(mappedBy = "usuariosCollection")
    private Collection<Usuarios> usuariosCollection1;
    @JoinTable(name = "crea", joinColumns = {
        @JoinColumn(name = "crea_usuario", referencedColumnName = "usr_cc")}, inverseJoinColumns = {
        @JoinColumn(name = "crea_proyecto", referencedColumnName = "proyectos_id")})
    @ManyToMany
    private Collection<Proyectos> proyectosCollection;
    @JoinTable(name = "forma_parte", joinColumns = {
        @JoinColumn(name = "forma_parte_usuario", referencedColumnName = "usr_cc")}, inverseJoinColumns = {
        @JoinColumn(name = "forma_parte_grupo", referencedColumnName = "grupo_id")})
    @ManyToMany
    private Collection<Grupos> gruposCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comentHechoPor")
    private Collection<Comentarios> comentariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resUsuario")
    private Collection<RespuestasComent> respuestasComentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "msjDestinatario")
    private Collection<Mensaje> mensajeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "msjRemitente")
    private Collection<Mensaje> mensajeCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventoCreadoPor")
    private Collection<Eventos> eventosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "convUsr2")
    private Collection<Conversacion> conversacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "convUsr1")
    private Collection<Conversacion> conversacionCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foroCreadoPor")
    private Collection<Foros> forosCollection;
    @JoinColumn(name = "usr_ciudad", referencedColumnName = "ciudad_id")
    @ManyToOne
    private Ciudad usrCiudad;
    @JoinColumn(name = "usr_universidad", referencedColumnName = "universidad_id")
    @ManyToOne
    private Universidades usrUniversidad;
    @JoinColumn(name = "usr_departamento", referencedColumnName = "departamento_id")
    @ManyToOne
    private Departamentos usrDepartamento;

    public Usuarios() {
    }

    public Usuarios(Integer usrCc) {
        this.usrCc = usrCc;
    }

    public Usuarios(Integer usrCc, String usrNombres, String usrApellidos, String usrEmail, String usrPass) {
        this.usrCc = usrCc;
        this.usrNombres = usrNombres;
        this.usrApellidos = usrApellidos;
        this.usrEmail = usrEmail;
        this.usrPass = usrPass;
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

    public String getUsrSexo() {
        return usrSexo;
    }

    public void setUsrSexo(String usrSexo) {
        this.usrSexo = usrSexo;
    }

    public Integer getUsrTipo() {
        return usrTipo;
    }

    public void setUsrTipo(Integer usrTipo) {
        this.usrTipo = usrTipo;
    }

    public String getUsrEstado() {
        return usrEstado;
    }

    public void setUsrEstado(String usrEstado) {
        this.usrEstado = usrEstado;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection1() {
        return usuariosCollection1;
    }

    public void setUsuariosCollection1(Collection<Usuarios> usuariosCollection1) {
        this.usuariosCollection1 = usuariosCollection1;
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
    public Collection<Comentarios> getComentariosCollection() {
        return comentariosCollection;
    }

    public void setComentariosCollection(Collection<Comentarios> comentariosCollection) {
        this.comentariosCollection = comentariosCollection;
    }

    @XmlTransient
    public Collection<RespuestasComent> getRespuestasComentCollection() {
        return respuestasComentCollection;
    }

    public void setRespuestasComentCollection(Collection<RespuestasComent> respuestasComentCollection) {
        this.respuestasComentCollection = respuestasComentCollection;
    }

    @XmlTransient
    public Collection<Mensaje> getMensajeCollection() {
        return mensajeCollection;
    }

    public void setMensajeCollection(Collection<Mensaje> mensajeCollection) {
        this.mensajeCollection = mensajeCollection;
    }

    @XmlTransient
    public Collection<Mensaje> getMensajeCollection1() {
        return mensajeCollection1;
    }

    public void setMensajeCollection1(Collection<Mensaje> mensajeCollection1) {
        this.mensajeCollection1 = mensajeCollection1;
    }

    @XmlTransient
    public Collection<Eventos> getEventosCollection() {
        return eventosCollection;
    }

    public void setEventosCollection(Collection<Eventos> eventosCollection) {
        this.eventosCollection = eventosCollection;
    }

    @XmlTransient
    public Collection<Conversacion> getConversacionCollection() {
        return conversacionCollection;
    }

    public void setConversacionCollection(Collection<Conversacion> conversacionCollection) {
        this.conversacionCollection = conversacionCollection;
    }

    @XmlTransient
    public Collection<Conversacion> getConversacionCollection1() {
        return conversacionCollection1;
    }

    public void setConversacionCollection1(Collection<Conversacion> conversacionCollection1) {
        this.conversacionCollection1 = conversacionCollection1;
    }

    @XmlTransient
    public Collection<Foros> getForosCollection() {
        return forosCollection;
    }

    public void setForosCollection(Collection<Foros> forosCollection) {
        this.forosCollection = forosCollection;
    }

    public Ciudad getUsrCiudad() {
        return usrCiudad;
    }

    public void setUsrCiudad(Ciudad usrCiudad) {
        this.usrCiudad = usrCiudad;
    }

    public Universidades getUsrUniversidad() {
        return usrUniversidad;
    }

    public void setUsrUniversidad(Universidades usrUniversidad) {
        this.usrUniversidad = usrUniversidad;
    }

    public Departamentos getUsrDepartamento() {
        return usrDepartamento;
    }

    public void setUsrDepartamento(Departamentos usrDepartamento) {
        this.usrDepartamento = usrDepartamento;
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

    @XmlTransient
    public Collection<Solicitudes> getSolicitudesCollection() {
        return solicitudesCollection;
    }

    public void setSolicitudesCollection(Collection<Solicitudes> solicitudesCollection) {
        this.solicitudesCollection = solicitudesCollection;
    }

    public Boolean getUsrFoto() {
        return usrFoto;
    }

    public void setUsrFoto(Boolean usrFoto) {
        this.usrFoto = usrFoto;
    }
    
}
