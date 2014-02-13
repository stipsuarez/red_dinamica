/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Solicitudes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Naturaleza
 */
@Stateless
public class SolicitudesFacade extends AbstractFacade<Solicitudes> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudesFacade() {
        super(Solicitudes.class);
    }
    
}
