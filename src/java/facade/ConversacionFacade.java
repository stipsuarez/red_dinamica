/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Conversacion;
import clases.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Naturaleza
 */
@Stateless
public class ConversacionFacade extends AbstractFacade<Conversacion> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConversacionFacade() {
        super(Conversacion.class);
    }
    public List<Conversacion> cargarConversaciones(int id_usrCurrent) {
        try {
            String sentencia = "SELECT * FROM Conversacion c WHERE c.conv_usr1_id = " + id_usrCurrent + " OR c.conv_usr2_id =" + id_usrCurrent;
            Query q = em.createNativeQuery(sentencia, Conversacion.class);
            return q.getResultList();

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la consulta en la BD: " + e + "\nLocalize:" + e.getLocalizedMessage(), "  id_usrCurrent no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
        return null;
    }
    
    public List<Conversacion> existeConversacion(int id_usrCurrent, int id_usrDestino) {
        try {
            String sentencia = "SELECT * FROM Conversacion c WHERE (c.conv_usr1_id = " + id_usrCurrent +" AND c.conv_usr2_id =" + id_usrDestino+") OR (c.conv_usr1_id = " + id_usrDestino +  " AND c.conv_usr2_id =" + id_usrCurrent+  ")";
            Query q = em.createNativeQuery(sentencia, Conversacion.class);
            return q.getResultList();

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la consulta en la BD: " + e + "\nLocalize:" + e.getLocalizedMessage(), "  id_usrCurrent no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
        return null;
    }
    
}
