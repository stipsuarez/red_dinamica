/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import clases.Eventos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Naturaleza
 */
@Stateless
public class EventosFacade extends AbstractFacade<Eventos> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventosFacade() {
        super(Eventos.class);
    }
    
}
