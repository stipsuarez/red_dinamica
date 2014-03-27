package bean;

import clases.Usuarios;
import com.sun.xml.ws.developer.Serialization;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import facade.UsuariosFacade;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.persistence.Query;

/**
 *
 * @author Manolo
 */
@ManagedBean(name = "solicitudBean")
@SessionScoped
@Serialization
public class solicitudBean {

    private String usrNombre;
    private String usrApellido;
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;
    private String nombre_buscar;
    @EJB
    private UsuariosFacade ejbUsuariosFacade;
    List<Usuarios> listaNoAceptados = new ArrayList<>();
    List<Usuarios> listaUsuarios = new ArrayList<>();
    List<Usuarios> listaUsuariosE = new ArrayList<>();
    List<Usuarios> listaAceptados = new ArrayList<>();
    private Usuarios usrSelect;
    private Usuarios usrActual;

    public solicitudBean() {
    }

    public Usuarios getUsrActual() {
        return usrActual;
    }

    public void setUsrActual(Usuarios usrActual) {
        this.usrActual = usrActual;
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }

    public Usuarios getUsrSelect() {
        return usrSelect;
    }

    public void setUsrSelect(Usuarios usrSelect) {
        this.usrSelect = usrSelect;
    }

    public List<Usuarios> getListaUsuariosE() {
        return listaUsuariosE;
    }

    public void setListaUsuariosE(List<Usuarios> listaUsuariosE) {
        this.listaUsuariosE = listaUsuariosE;
    }

    public void setListaAceptados(List<Usuarios> listaAceptados) {
        this.listaAceptados = listaAceptados;
    }

    public List<Usuarios> getListaAceptados() {
        return listaAceptados;
    }

    public List<Usuarios> getListaNoAceptados() {
        String ac = "";
        listaUsuarios = ejbUsuariosFacade.findAll();
        for (int i = 0; i < listaNoAceptados.size(); i++) {
            listaNoAceptados.remove(i);
        }
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUsrEstado() == null || !listaUsuarios.get(i).getUsrEstado().equals("0")
                    || !listaUsuarios.get(i).getUsrEstado().equals("")) {
                listaNoAceptados.add(listaUsuarios.get(i));
                ac += "\n" + listaNoAceptados.get(i);
            }
        }

        return listaNoAceptados;
    }

    public String getUsrNombre() {
        return usrNombre;
    }

    public void setUsrNombre(String usrNombre) {
        this.usrNombre = usrNombre;
    }

    public String getUsrApellido() {
        return usrApellido;
    }

    public void setUsrApellido(String usrApellido) {
        this.usrApellido = usrApellido;
    }

    public List<Usuarios> getListaUsuarios() {

        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getNombre_buscar() {
        return nombre_buscar;
    }

    public void setNombre_buscar(String nombre_buscar) {
        this.nombre_buscar = nombre_buscar;
    }

    public void enviarSolicitud(FacesContext fc, UIComponent component, Object value) throws IOException {
        try {
             //Asignamos el id a la solicitud
            String sentencia = "SELECT MAX(solicitud_id) AS id FROM solicitudes";
            Query q = em.createNativeQuery(sentencia, Usuarios.class);
            int id_soli = q.getFirstResult()+1;
            int cc_amigo = usrSelect.getUsrCc();
            Date fechaActual= new Date();
            String estado ="0";
            usrActual = (Usuarios) component.getAttributes().get("usrAct");
            int cc_usrActual =usrActual.getUsrCc() ;
            
            
            

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la consulta en la BD: " + e + "\nLocalize:" + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }

    public void asignarNombreBuscar(FacesContext facesContext, UIComponent component, Object value) {
        try {
            this.nombre_buscar = value.toString();

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre: " + value + "Error: ", "" + e + "   " + e.getLocalizedMessage());
            FacesContext.getCurrentInstance().addMessage("buscar", msg);
        }
    }



    public void asignarUsuariosEncontrados() {
        try {

            listaUsuariosE = ejbUsuariosFacade.buscarUsuarios_por_nombre(this.nombre_buscar);

            HashSet<Usuarios> hashSet = new HashSet<Usuarios>(listaUsuariosE);
            for (int i = 0; i < listaUsuariosE.size(); i++) {
                listaUsuariosE.remove(i);
            }
            // Eliminamos Usuarios repetidos
            for (Iterator it = hashSet.iterator(); it.hasNext();) {
                listaUsuariosE.add((Usuarios) it.next());

            }


        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + this.nombre_buscar + "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }

    public void agregarAmigo() {
    }
}