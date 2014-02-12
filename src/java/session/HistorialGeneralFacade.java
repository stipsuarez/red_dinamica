/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import clases.HistorialGeneral;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Naturaleza
 */
@Stateless
public class HistorialGeneralFacade extends AbstractFacade<HistorialGeneral> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialGeneralFacade() {
        super(HistorialGeneral.class);
    }
    
}
