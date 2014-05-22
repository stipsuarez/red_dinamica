/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import clases.Usuarios;
import controllers.UsuariosController;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;
import facade.UsuariosFacade;
 
@ManagedBean(name="photoCamBean")
@ViewScoped
public class PhotoCamBean implements InterfaceBean, Serializable{
 
 @EJB
 private UsuariosFacade ejbUsuariosFacade;
    
    public void oncapture(CaptureEvent captureEvent) throws IOException, InterruptedException {
         // obtenemos los datos de la foto como array de bytes
        final byte[] datos = captureEvent.getData();
 
        final ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
        //asignamos un nombre a la foto segun el usuario actual.
        String foto = UsuariosController.getCurrent().getUsrId()+ ".png"; 
        
        FacesContext context = FacesContext.getCurrentInstance(); 
       // context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Foto dir = "+foto, "Foto dir = "+foto));
        
        //final String fileFoto = "C:\\red_dinamica\\web\\Recursos\\Imagenes\\perfil\\"+ foto;//para Stip
        final String fileFoto = "C:\\WebApplication1\\WebApplication1\\web\\Recursos\\Imagenes\\perfil\\"+ foto;//para oscar
        //Alteramos el valor de UsrId para el usuario actual con el fin de usar ese valor para rellenar el campo de imagen en el formulario
        
        System.out.println(fileFoto);
        
        File archivo = new File(fileFoto);
        archivo.delete();
        FileImageOutputStream outputStream = null;
        try {
            outputStream = new FileImageOutputStream(new File(fileFoto));
            // guardamos la imagen
            outputStream.write(datos, 0, datos.length);
            FacesContext.getCurrentInstance().getExternalContext().wait(5);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/perfiles.xhtml");
        } catch (IOException e) {
            throw new FacesException("Error guardando la foto.", e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
                
        if(SessionBean.session.isActiva()){
          
          context.addMessage(null, new FacesMessage("Cierre sesión", "Imagen cargada exitosamente. Inicie sesión nuevamente para efectuar cambios"));
          
        }
       
    }
 
    public boolean isVerFoto() {
        
        final ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
        Usuarios u = UsuariosController.getCurrent();
        String foto = ""+u.getUsrId();
        
        String fileFoto = servletContext.getRealPath("") + File.separator + "/Recursos/Imagenes/perfil/" + File.separator + foto;
        
        File file = new File(fileFoto);
        return file.exists();
        //return foto.equals(idUsuario);
           
        
    }
    //Arreglarlo
    public boolean isTieneFoto(){
     Usuarios u = UsuariosController.getCurrent();
        if(u.getUsrFoto()!=null) return true;
        return false;
    }
}
