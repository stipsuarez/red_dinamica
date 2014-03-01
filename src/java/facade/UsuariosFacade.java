/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Naturaleza
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public boolean validarLogueo(String cedula, String contrasena) {
        /*Query q = em.createQuery("SELECT u FROM Usuarios u WHERE u.usrNombre = :usrNombre AND u.usrPassword= :pass", Usuarios.class);
        q.setParameter("usrNombre", nombreUsuario);
        q.setParameter("pass", contrasena);
        */
        int ced = Integer.parseInt(cedula);
        String sentencia ="SELECT * FROM Usuarios u WHERE u.usrCc = '"+ced+ "' AND u.usrPassword = '"+contrasena+"'";
        System.out.println(sentencia);
        Query q = em.createNativeQuery(sentencia, Usuarios.class);
        return q.getResultList().size()==1;
        
        
    }
    
}
