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
    @NamedQuery(name = "Usuarios.findByUsrEstado", query = "SELECT u FROM Usuarios u WHERE u.usrEstado = :usrEstado"),
    @NamedQuery(name = "Usuarios.findByUsrFoto", query = "SELECT u FROM Usuarios u WHERE u.usrFoto = :usrFoto")})
public class Usuarios implements Serializable {
    @ManyToMany(mappedBy = "usuariosCollection")
    private Collection<Colectivos> colectivosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colectUsrId")
    private Collection<Colectivos> colectivosCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private Collection<UsrTieneHistorial> usrTieneHistorialCollection;
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
    @Column(name = "usr_foto")
    private Boolean usrFoto;
    @JoinTable(name = "contactos", joinColumns = {
        @JoinColumn(name = "cont_usr_id", referencedColumnName = "usr_cc")}, inverseJoinColumns = {
        @JoinColumn(name = "cont_contacto_id", referencedColumnName = "usr_cc")})
    @ManyToMany
    private Collection<Usuarios> usuariosCollection;
    @ManyToMany(mappedBy = "usuariosCollection")
    private Collection<Usuarios> usuariosCollection1;
    @JoinColumn(name = "usr_universidad", referencedColumnName = "universidad_id")
    @ManyToOne
    private Universidades usrUniversidad;
    @JoinColumn(name = "usr_departamento", referencedColumnName = "departamento_id")
    @ManyToOne
    private Departamentos usrDepartamento;
    @JoinColumn(name = "usr_ciudad", referencedColumnName = "ciudad_id")
    @ManyToOne
    private Ciudad usrCiudad;

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

    public Boolean getUsrFoto() {
        return usrFoto;
    }

    public void setUsrFoto(Boolean usrFoto) {
        this.usrFoto = usrFoto;
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

    public Ciudad getUsrCiudad() {
        return usrCiudad;
    }

    public void setUsrCiudad(Ciudad usrCiudad) {
        this.usrCiudad = usrCiudad;
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
    public Collection<UsrTieneHistorial> getUsrTieneHistorialCollection() {
        return usrTieneHistorialCollection;
    }

    public void setUsrTieneHistorialCollection(Collection<UsrTieneHistorial> usrTieneHistorialCollection) {
        this.usrTieneHistorialCollection = usrTieneHistorialCollection;
    }

    @XmlTransient
    public Collection<Colectivos> getColectivosCollection() {
        return colectivosCollection;
    }

    public void setColectivosCollection(Collection<Colectivos> colectivosCollection) {
        this.colectivosCollection = colectivosCollection;
    }

    @XmlTransient
    public Collection<Colectivos> getColectivosCollection1() {
        return colectivosCollection1;
    }

    public void setColectivosCollection1(Collection<Colectivos> colectivosCollection1) {
        this.colectivosCollection1 = colectivosCollection1;
    }    
}
