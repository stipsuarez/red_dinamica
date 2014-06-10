/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import clases.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Miguel
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
        String sentencia = "SELECT * FROM Usuarios u WHERE u.usrId = '" + ced + "' AND u.usrPassword = '" + contrasena + "'";
        System.out.println(sentencia);
        Query q = em.createNativeQuery(sentencia, Usuarios.class);
        return q.getResultList().size() == 1;


    }

    public List<Usuarios> buscarUsuarios_por_nombre(String nombre) {
        try {
            String sentencia = "SELECT * FROM Usuarios u WHERE u.usr_nombres LIKE '%" + nombre + "%' OR u.usr_apellidos LIKE '%" + nombre + "%'";
            Query q = em.createNativeQuery(sentencia, Usuarios.class);
            return q.getResultList();

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la consulta en la BD: " + e + "\nLocalize:" + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
        return null;
    }
    public List<Usuarios> buscarUsuarios_por_email(String email) {
        try {
            String sentencia = "SELECT * FROM Usuarios u WHERE  u.usr_email LIKE '%" + email + "%' ";
            Query q = em.createNativeQuery(sentencia, Usuarios.class);
            return q.getResultList();

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la consulta en la BD: " + e + "\nLocalize:" + e.getLocalizedMessage(), "   " + e.getLocalizedMessage()));
        }
        return null;
    }
    public List<Usuarios> buscarUsuarios_por_universidad(int universidad) {
        try {
            String sentencia = "SELECT * FROM Usuarios u WHERE  u.usr_universidad = " + universidad + " ";
            Query q = em.createNativeQuery(sentencia, Usuarios.class);
            return q.getResultList();

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la consulta en la BD: " + e + "\nLocalize:" + e.getLocalizedMessage(), "   " + e.getLocalizedMessage()));
        }
        return null;
    }
}
