/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import controllers.UsuariosController;
import util.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author gaferneira
 */
@ManagedBean(name="validador")
@RequestScoped
public class Validador implements InterfaceBean, Serializable{

    /**
     * Creates a new instance of validador
     */
    
    private String pass="";
    private String email="";
    private int cedula=0;
    private Usuarios user;
    @EJB
    facade.UsuariosFacade ejbFacadeUsuarios;
    
    public Validador() {
    }

       public void asignarCorreo(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           
           this.email = arg2.toString();
           
       }
       
       public void validarCorreos(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           
           
            if (!arg2.toString().matches(this.email)) {
               throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los correos no coinciden","Los correos no coinciden"));
            }
       }
       
       public void noExisteCedula(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           
           try{
            user = (Usuarios) ejbFacadeUsuarios.find(arg2.toString());
            cedula = user.getUsrCc();
           }
           catch(Exception e)
           {
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cedula ingresada no está registrada","La cedula ingresada no está registrada"));
           }
       }
       
      public void existeCedula(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           
           try{
            user = (Usuarios) ejbFacadeUsuarios.find(arg2.toString());
            cedula =user.getUsrCc();
            if(cedula == 0)
            {throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cedula ingresada ya está registrada","La cedula ingresada ya está registrada"));
            }
            }
           catch(Exception e)
           {
           }
       }
              
       public void asignarContraseña(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           
           this.pass = arg2.toString();
           
       }
       
       public void validarContraseñas(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           
            if(!this.pass.matches(""))
            {if (!arg2.toString().matches(this.pass)) {
               throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden","Las contraseñas no coinciden"));
            }
            }
       }
       
       public void aceptarTerminos(FacesContext arg0, UIComponent arg1, Object arg2)
         throws ValidatorException {
           
           
            if (Boolean.parseBoolean(arg2.toString()) == false) {
               throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe aceptar los Terminos y Condiciones","Debe aceptar los Terminos y Condiciones"));
            }
       }
       
      public String registro()
      {
          if(pass.matches(UsuariosController.getCurrent().getUsrPass())){ return  "formularioRegistro";}
          else {
          JsfUtil.addErrorMessage("Las contraseñas no coinciden");
          return "registro";
          }
          
      }
      
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      
 
       
}
    