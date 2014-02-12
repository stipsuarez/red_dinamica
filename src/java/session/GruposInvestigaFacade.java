/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import clases.GruposInvestiga;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Naturaleza
 */
@Stateless
public class GruposInvestigaFacade extends AbstractFacade<GruposInvestiga> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GruposInvestigaFacade() {
        super(GruposInvestiga.class);
    }
    
}
