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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    @EJB
    private UsuariosFacade ejbUsuariosFacade;
    List<Usuarios> listaNoAceptados = new ArrayList<>();
    List<Usuarios> listaUsuarios = new ArrayList<>();
    List<Usuarios> listaAceptados = new ArrayList<>();

    public void setListaAceptados(List<Usuarios> listaAceptados) {
        this.listaAceptados = listaAceptados;
    }

    public List<Usuarios> getListaAceptados() {
        return listaAceptados;
    }

    public List<Usuarios> getListaNoAceptados() {
        String ac="";
        listaUsuarios = ejbUsuariosFacade.findAll();
        for (int i = 0; i < listaNoAceptados.size(); i++) {
            listaNoAceptados.remove(i);
          }
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUsrEstado()==null || !listaUsuarios.get(i).getUsrEstado().equals("0") 
                    || !listaUsuarios.get(i).getUsrEstado().equals("")) {
                listaNoAceptados.add(listaUsuarios.get(i));
                ac+="\n"+listaNoAceptados.get(i);
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


    public void aceptarSolicitudes() throws IOException {

        for (int i = 0; i < listaAceptados.size(); i++) {
            listaAceptados.get(i).setUsrEstado("1");
            ejbUsuariosFacade.edit(listaAceptados.get(i));
        }
        if (listaAceptados.size() > 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Confirmación", "Cambios efectuados satisfactoriamente"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/pages/perfiles.xhtml");

            
        }
    }

    public solicitudBean() {
    }
}
