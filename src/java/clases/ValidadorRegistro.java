/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "validadorRegistro")
@RequestScoped
public class ValidadorRegistro {

    private String email = "";
    private String contrasena = "";
    private String contrasena2 = "";
    private Usuarios user;
    private int cedula = 0;
    private String valorCedula = "";
    

    public String getValorCedula() {
        return valorCedula;
    }

    public void setValorCedula(String valorCedula) {
        this.valorCedula = valorCedula;
    }

    public String getContrasena2() {
        return contrasena2;
    }

    public void setContrasena2(String contrasena2) {
        this.contrasena2 = contrasena2;
    }
    
    
    @EJB
    facade.UsuariosFacade ejbFacadeUsuarios;

    /**
     * Creates a new instance of ValidadorRegistro
     */
    public ValidadorRegistro() {
    }

    public void asignarCorreo(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {

        this.email = arg2.toString();

    }
    
    public void asignarDepartamento(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {

        this.email = arg2.toString();

    }


    public void validarCorreos(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {


        if (!arg2.toString().matches(this.email)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los correos no coinciden", "Los correos no coinciden"));
        }
    }

    public void asignarContrasena(FacesContext arg0, UIComponent arg1, Object arg2)
             {

        this.contrasena = arg2.toString();
                FacesContext context = FacesContext.getCurrentInstance();  
                context.addMessage("datosFrom",new FacesMessage(FacesMessage.SEVERITY_INFO, "akjhfds", "laksjhdfkjh alkjhfdla kjsdf "));
            
    }

    public void validarContrasenas(FacesContext arg0, UIComponent arg1, Object arg2)
            {
                try {
             contrasena2=(String) arg2;       
                

        if (!this.contrasena.matches("")) {
            if (!contrasena2.toString().matches(contrasena)) {
                FacesContext context = FacesContext.getCurrentInstance();  
                context.addMessage("datosFrom",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden", "Las contraseñas no coinciden"));
                
            }
            FacesContext context = FacesContext.getCurrentInstance();  
        context.addMessage("datosFrom",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Son iguales", "Las contraseñas coinciden"));
        }
             
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();  
                context.addMessage("datosFrom",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden"));
                
                }
    }

    public void ExisteCedula(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {

        try {

            user = (Usuarios) ejbFacadeUsuarios.find(arg2);
            if (user != null) {
                valorCedula = "Usuario actual: " + user;
                cedula = user.getUsrCc();
                if (cedula != 0) {
                    cedula = 0;
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cedula ingresada ya está registrada", "La cedula ingresada ya está registrada"));
                }
            }

        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error: " + e.getLocalizedMessage()));
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void imprimir() {
        FacesMessage msg = new FacesMessage("Confirmación", " se ha subido con éxito.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
