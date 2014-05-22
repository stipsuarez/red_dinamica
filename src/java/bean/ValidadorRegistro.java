/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import clases.Usuarios;
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
    private String contrasena="";
    private String pass1="";
    private String pass2="";
    private Usuarios user;
    private int cedula = 0;
    private String valorCedula = "";
    private String msg = "";
    @EJB
    facade.UsuariosFacade ejbFacadeUsuarios;

    /**
     * Creates a new instance of ValidadorRegistro
     */
    public ValidadorRegistro() {
       
    }

    public String getValorCedula() {
        return valorCedula;
    }
    

    public void setValorCedula(String valorCedula) {
        this.valorCedula = valorCedula;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
    

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public void asignarCon(FacesContext arg0, UIComponent arg1, Object arg2) 
        throws ValidatorException {
           try {
           this.contrasena = arg2.toString();
           this.pass1= arg2.toString();
       
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("datosFrom", new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña cambiada", "Contraseña cambiada con exito: "+contrasena));

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("datosFrom", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al asignar contraseña: ", "" + e));
        }

    }

    public void validarCon(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        try {
            
            String contr= this.pass1;
             //contrasena = (String) arg1.getAttributes().get("usrPassw");
              FacesContext context = FacesContext.getCurrentInstance();
              context.addMessage("datosFrom", new FacesMessage(FacesMessage.SEVERITY_INFO, "Comparando contra", ": con1: "+this.pass1+"   "+getContrasena()+"    con2= "+arg2));
            if(!contr.matches(""))
            {if (!arg2.toString().matches(contr)) {
               context.addMessage("datosFrom", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Diferentes", ": con1: "+this.pass1+"   "+getContrasena()+"    con2= "+arg2));
            }
            }
              
           
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("datosFrom", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden"));

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
