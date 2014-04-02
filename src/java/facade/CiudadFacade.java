/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Ciudad;
import clases.Departamentos;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Naturaleza
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
    //Este es mi código
     public List<Ciudad> ciudadesSelecionadas(Integer departamento_id) {
        try {            
            String cadena = "SELECT * FROM Ciudad c WHERE c.Departamentos_departamento_id =" + departamento_id;
            TypedQuery<Ciudad> query2 = (TypedQuery<Ciudad>) em.createNativeQuery(cadena, Ciudad.class);
            return query2.getResultList();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar la BD: " + e + "\nLocalize: " + e.getLocalizedMessage(), "Error bd");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
}
    }
}
