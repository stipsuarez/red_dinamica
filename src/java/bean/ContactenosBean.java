/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import clases.Usuarios;
import com.sun.xml.ws.developer.Serialization;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import facade.UsuariosFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "contactenos")
@SessionScoped
@Serialization
public class ContactenosBean {

@EJB
private UsuariosFacade ejbUsuariosFacade;
private List<Usuarios> usuarios = new ArrayList<>();
private List<Usuarios> admins = new ArrayList<>();

    public List<Usuarios> getAdmins() {
        usuarios = ejbUsuariosFacade.findAll();
        for (int i = 0; i < admins.size(); i++) {
            admins.remove(i);
            
        }
        
        for(int i = 0; i<usuarios.size();i++){            
            if(usuarios.get(i).getUsrTipo()==true){
                admins.add(usuarios.get(i));
            }
        }    
        return admins;
    }

    public void setAdmins(List<Usuarios> admins) {
        this.admins = admins;
    }
    /**
     * Creates a new instance of Contactenos
     */
    public ContactenosBean() {
    }
}
