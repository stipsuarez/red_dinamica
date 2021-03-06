/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Universidades;
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
public class UniversidadesFacade extends AbstractFacade<Universidades> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UniversidadesFacade() {
        super(Universidades.class);
    }
    
    //NUESTRO CÓDIGO INICIA AQUÍ
     public List<Universidades> UniversidadSelecionadas(Integer ciudad_id) {
        try {            
            String cadena = "SELECT * FROM Universidades u WHERE u.universidad_ciudad =" + ciudad_id;
            TypedQuery<Universidades> query2 = (TypedQuery<Universidades>) em.createNativeQuery(cadena, Universidades.class);
           
            return query2.getResultList();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar la BD: " + e + "\nLocalize: " + e.getLocalizedMessage(), "Error bd");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
     
    public List<Universidades> buscarUniversidades(String universidad) {
        try {
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "query creado",""));
            String cadena = "SELECT * FROM Universidades u WHERE u.universidad_nombre LIKE '%" + universidad + "%' ";
            TypedQuery<Universidades> query2 = (TypedQuery<Universidades>) em.createNativeQuery(cadena, Universidades.class);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "retornandoo... ",""));
            return query2.getResultList();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al consultar la BD: " + e + "\nLocalize: " + e.getLocalizedMessage(), "Error bd");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
    }
}
