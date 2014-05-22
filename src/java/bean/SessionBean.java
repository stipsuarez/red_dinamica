package bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import clases.Usuarios;
import controllers.UsuariosController;
import controllers.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * 
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements InterfaceBean, Serializable {

    /**
     * Creates a new instance of Bean
     */
    String ingresarCedula;
    String ingresarContraseña;
    @EJB
    facade.UsuariosFacade ejbFacadeUsuarios;

    public SessionBean() {
    }

    //No sirvió
    public boolean validarSesion() {
        /*
         System.out.println("nombre = " + usuario.getNombre());
         if (usuario.getNombre().isEmpty())
         {
         try {
         FacesContext context = FacesContext.getCurrentInstance();
         context.getExternalContext().redirect("/software/faces/views/ingresar.xhtml");
         context.responseComplete();
         } catch (IOException ex) {
         Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         * */

        return true;
    }

    public String getIngresarCedula() {
        return ingresarCedula;
    }

    public void setIngresarCedula(String ingresarCedula) {
        this.ingresarCedula = ingresarCedula;
    }

    public String getIngresarContraseña() {
        return ingresarContraseña;
    }

    public void setIngresarContraseña(String ingresarContraseña) {
        this.ingresarContraseña = ingresarContraseña;
    }

    public boolean isActiva() {
        return session.isActiva();

    }

    public String comprobar() {


        try {
            Usuarios user;
            user = (Usuarios) ejbFacadeUsuarios.find(ingresarCedula);
            if (user != null && user.getUsrPass().trim().matches(ingresarContraseña) ) {
                UsuariosController.setCurrent(user);
                
                session.setActiva(true);
                return "login";
                //FacesContext.getCurrentInstance().getExternalContext().redirect("/ResidenciasUIS/indexRegistrado.xhtml");
            } else {
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique nombre y/o contraseña!", "Verifique nombre y/o contraseña!"));
                JsfUtil.addErrorMessage("La cedula y la contraseña no coinciden");
                return null;
            }
        } catch (Exception e) {

            System.out.println("Excepcion!!!....");
        }
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique nombre y/o contraseña!", "Verifique nombre y/o contraseña!"));
        JsfUtil.addErrorMessage("La cedula y la contraseña no coinciden");
        return null;
    }
    
    public void validarUsuario() throws IOException {

        if (ejbFacadeUsuarios.validarLogueo(ingresarCedula, ingresarContraseña)) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ResidenciasUIS/indexRegistrado.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique nombre y/o contraseña!", "Verifique nombre y/o contraseña!"));
        }
    }

    public String cerrarSesion() {

        UsuariosController.getCurrent().setUsrId(null);
        UsuariosController.setCurrent(new Usuarios());
        session.setActiva(false);
        return "index";
    }
}
