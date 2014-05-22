/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Comentarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Miguel
 */
@Stateless
public class ComentariosFacade extends AbstractFacade<Comentarios> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentariosFacade() {
        super(Comentarios.class);
    }
    
    public List<Comentarios> ComentariosForo(Integer foro_id) {
        try {            
            String cadena = "SELECT * FROM Comentarios c WHERE c.coment_foro_id =" + foro_id;
            TypedQuery<Comentarios> query2 = (TypedQuery<Comentarios>) em.createNativeQuery(cadena, Comentarios.class);
            return query2.getResultList();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar la BD: " + e + "\nLocalize: " + e.getLocalizedMessage(), "Error bd");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
          }
        }    
}
