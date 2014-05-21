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
    @NamedQuery(name = "Usuarios.findByUsrId", query = "SELECT u FROM Usuarios u WHERE u.usrId = :usrId"),
    @NamedQuery(name = "Usuarios.findByUsrNombres", query = "SELECT u FROM Usuarios u WHERE u.usrNombres = :usrNombres"),
    @NamedQuery(name = "Usuarios.findByUsrApellidos", query = "SELECT u FROM Usuarios u WHERE u.usrApellidos = :usrApellidos"),
    @NamedQuery(name = "Usuarios.findByUsrEmail", query = "SELECT u FROM Usuarios u WHERE u.usrEmail = :usrEmail"),
    @NamedQuery(name = "Usuarios.findByUsrPass", query = "SELECT u FROM Usuarios u WHERE u.usrPass = :usrPass"),
    @NamedQuery(name = "Usuarios.findByUsrSexo", query = "SELECT u FROM Usuarios u WHERE u.usrSexo = :usrSexo"),
    @NamedQuery(name = "Usuarios.findByUsrTipo", query = "SELECT u FROM Usuarios u WHERE u.usrTipo = :usrTipo"),
    @NamedQuery(name = "Usuarios.findByUsrEstado", query = "SELECT u FROM Usuarios u WHERE u.usrEstado = :usrEstado"),
    @NamedQuery(name = "Usuarios.findByUsrFoto", query = "SELECT u FROM Usuarios u WHERE u.usrFoto = :usrFoto")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usr_id")
    private Integer usrId;
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
    @Column(name = "usr_sexo")
    private Boolean usrSexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_tipo")
    private boolean usrTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_estado")
    private boolean usrEstado;
    @Size(max = 45)
    @Column(name = "usr_foto")
    private String usrFoto;
    @JoinTable(name = "contactos", joinColumns = {
        @JoinColumn(name = "cont_usr_id", referencedColumnName = "usr_id")}, inverseJoinColumns = {
        @JoinColumn(name = "cont_contacto_id", referencedColumnName = "usr_id")})
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

    public Usuarios(Integer usrId) {
        this.usrId = usrId;
    }

    public Usuarios(Integer usrId, String usrNombres, String usrApellidos, String usrEmail, String usrPass, boolean usrTipo, boolean usrEstado) {
        this.usrId = usrId;
        this.usrNombres = usrNombres;
        this.usrApellidos = usrApellidos;
        this.usrEmail = usrEmail;
        this.usrPass = usrPass;
        this.usrTipo = usrTipo;
        this.usrEstado = usrEstado;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
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

    public Boolean getUsrSexo() {
        return usrSexo;
    }

    public void setUsrSexo(Boolean usrSexo) {
        this.usrSexo = usrSexo;
    }

    public boolean getUsrTipo() {
        return usrTipo;
    }

    public void setUsrTipo(boolean usrTipo) {
        this.usrTipo = usrTipo;
    }

    public boolean getUsrEstado() {
        return usrEstado;
    }

    public void setUsrEstado(boolean usrEstado) {
        this.usrEstado = usrEstado;
    }

    public String getUsrFoto() {
        return usrFoto;
    }

    public void setUsrFoto(String usrFoto) {
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
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clases.Usuarios[ usrId=" + usrId + " ]";
    }
    
}
