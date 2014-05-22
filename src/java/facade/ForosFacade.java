/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Foros;
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
public class ForosFacade extends AbstractFacade<Foros> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ForosFacade() {
        super(Foros.class);
    }
    
    //MI CODIGO

    public List<Foros> getForosSelecionados(Integer colectivo_id) {
        try {
            String cadena = "SELECT * FROM Foros f WHERE f.foro_colect_id =" + colectivo_id;
            TypedQuery<Foros> query2 = (TypedQuery<Foros>) em.createNativeQuery(cadena, Foros.class);
            return query2.getResultList();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar la BD de foros: " + e + "\nLocalize: " + e.getLocalizedMessage(), "Error bd");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
           }
        }    
}
