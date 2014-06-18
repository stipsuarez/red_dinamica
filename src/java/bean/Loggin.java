package bean;


import clases.Usuarios;
import com.sun.xml.ws.developer.Serialization;
import controllers.UsuariosController;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import facade.UsuariosFacade;

@ManagedBean(name = "loggin")
@SessionScoped
@Serialization
public class Loggin implements InterfaceBean {

    private String cedula;
    private int ced;
    private String contrasena;
    private Usuarios usrActual;
    private String usrTipo;

   
    @EJB
    private UsuariosFacade usr;

    public Loggin(String cedula, String contrasena) {
        this.cedula = cedula;
        this.contrasena = contrasena;
    }

    public Usuarios getUsrActual() {
        return usrActual;
    }
    public void irPerfil() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/perfiles.xhtml");
    }

    public void setUsrActual(Usuarios usrActual) {
        this.usrActual = usrActual;
    }
    
    public Loggin() {
    }

     public String getUsrTipo() {
        return usrTipo;
    }

    public void setUsrTipo(String usrTipo) {
        this.usrTipo = usrTipo;
    }
    public int getCed() {
        return ced;
    }

    public void setCed(int ced) {
        this.ced = ced;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isActiva() {
        return session.isActiva();

    }

    public boolean isAdmin(){
        try {            
            boolean tipoUst = UsuariosController.getCurrent().getUsrTipo();
        return tipoUst==true;
        } catch (Exception e) {
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ""+e+"\nLocalización: "+e.getLocalizedMessage()));
        }
        return false;
    }
    
    public void comprobar() {


        try {
            Usuarios user;
            int cedu = Integer.parseInt(cedula);
            user = (Usuarios) usr.find(cedu);
            if (user != null && user.getUsrPass().trim().matches(contrasena)) {
                UsuariosController.setCurrent(user);
                usrActual=usuarioActual();
                setUsrActual(usrActual);
                FacesContext context2 = FacesContext.getCurrentInstance();
                HttpSession sessionv = (HttpSession) context2.getExternalContext().getSession(true);
                sessionv.setAttribute("user", usrActual);
                session.setActiva(true);
                //FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/");
                irA("perfil");
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique cédula y/o contraseña!", ""));

            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique cédula y/o contraseña!  " + e, ""));
            System.out.println("Excepcion!!!....");
        }


    }
    public Usuarios usuarioActual(){
        return  UsuariosController.getCurrent();
    }

    public void cerrarSesion() throws IOException {

        UsuariosController.getCurrent().setUsrId(null);
        UsuariosController.setCurrent(new Usuarios());
        session.setActiva(false);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/");
    }
    
    public void irA(String dire) throws IOException{
        switch(dire){
            case "index": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/"); break;
            case "perfil": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/usuarios/perfiles.xhtml"); break; 
            case "contacto": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/componentes/contactenos.xhtml"); break;    
            case "colectivos": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/colectivos/colectivosTemplateClient.xhtml"); break;    
            case "foros": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/foros/forosTemplateClient.xhtml"); break;   
            case "conversacion": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/conversacion/List.xhtml"); break;   
        }
    }
    
    public void validarUsuario() throws IOException {

        if (usr.validarLogueo(cedula, contrasena)) {
            
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verifique cédula y/o contraseña!", ""));

        }
    }

//Hay un problema con el redireccionamiento. Me tocó poner la dirección faces/crearUsuario.xhtml en lugar de red_dinamica/crearUsuario.xhtml para que
//pudiera funcionar.

    public void solicitarInscripcion() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Ingreso", "Bienvenido " + cedula));
        context.addMessage(null, new FacesMessage("Second Message", "Additional Info Here..."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/usuarios/registro_usr.xhtml");

    }
   
}
