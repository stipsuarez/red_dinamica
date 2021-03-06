package controllers;

import clases.Solicitudes;
import clases.Usuarios;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import facade.SolicitudesFacade;
import facade.UsuariosFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named("solicitudesController")
@SessionScoped
public class SolicitudesController implements Serializable {

    private Solicitudes current;
    private DataModel items = null;
    @EJB
    private facade.SolicitudesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public SolicitudesController() {
    }

    public Solicitudes getSelected() {
        if (current == null) {
            current = new Solicitudes();
            selectedItemIndex = -1;
        }
        return current;
    }

    private SolicitudesFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Solicitudes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Solicitudes();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SolicitudesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Solicitudes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SolicitudesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Solicitudes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SolicitudesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Solicitudes getSolicitudes(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Solicitudes.class)
    public static class SolicitudesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SolicitudesController controller = (SolicitudesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "solicitudesController");
            return controller.getSolicitudes(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Solicitudes) {
                Solicitudes o = (Solicitudes) object;
                return getStringKey(o.getSolicitudId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Solicitudes.class.getName());
            }
        }
    }
    // otra sección
    //acá va mi código 
    private String usrNombre;
    private String usrApellido;
    @PersistenceContext(unitName = "red_dinamicaPU")
    private EntityManager em;
   
    @EJB
    private UsuariosFacade ejbUsuariosFacade;
    List<Usuarios> listaUsuarios = new ArrayList<>();
    List<Usuarios> listaUsuariosE = new ArrayList<>();
    List<Usuarios> listaAceptados = new ArrayList<>();
    private Usuarios usrSelect;
    private Usuarios usrActual;
    
    public Usuarios getUsrActual() {
        return usrActual;
    }

    public void setUsrActual(Usuarios usrActual) {
        this.usrActual = usrActual;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public Usuarios getUsrSelect() {
        return usrSelect;
    }

    public void setUsrSelect(Usuarios usrSelect) {
        this.usrSelect = usrSelect;
    }

    public List<Usuarios> getListaUsuariosE() {
        return listaUsuariosE;
    }

    public void setListaUsuariosE(List<Usuarios> listaUsuariosE) {
        this.listaUsuariosE = listaUsuariosE;
    }

    public void setListaAceptados(List<Usuarios> listaAceptados) {
        this.listaAceptados = listaAceptados;
    }

    public List<Usuarios> getListaAceptados() {
        return listaAceptados;
    }

    public List<Usuarios> getListaNoAceptados() {
        try {
            listaUsuarios = ejbUsuariosFacade.findAll();
            List<Usuarios> listaNoAceptados = new ArrayList<>();

            for (int i = 0; i < listaUsuarios.size(); i++) {
                if (listaUsuarios.get(i).getUsrEstado() == false) {
                    listaNoAceptados.add(listaUsuarios.get(i));
                }
            }
            return listaNoAceptados;

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e, "Las contraseñas no coinciden"));
            return null;
        }
    }

    public List<Usuarios> getListaUsuarios() {

        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void enviarSolicitud() throws IOException {
        try {
            //Asignamos el id a la solicitud
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "VANMOS!! ", ""));
            int cc_amigo = usrSelect.getUsrId();
            Date fechaActual = new Date();
            String estado = "0";
            FacesContext context2 = FacesContext.getCurrentInstance();
            HttpSession sessionv = (HttpSession) context2.getExternalContext().getSession(true);
            usrActual = (Usuarios) sessionv.getAttribute("user");



            current = new Solicitudes();
            current.setSolicitudCc(cc_amigo);
            current.setSolicitudFecha(fechaActual);
            current.setSolicitudEstado(estado);
            current.setSolicitudUsrId(usrActual);
            create();

            listaUsuariosE.remove(usrSelect);//Actualizar el estado del boton enviar solicitud
            RequestContext context1 = RequestContext.getCurrentInstance();

            context1.update("growlB");

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar la consulta en la BD: " + e + "\nLocalize:" + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }
    public void aceptarSolicitudes() throws IOException {
        for (int i = 0; i < listaAceptados.size(); i++) {
            listaAceptados.get(i).setUsrEstado(true);
            ejbUsuariosFacade.edit(listaAceptados.get(i));
        }
        if (listaAceptados.size() > 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Confirmación", "Cambios efectuados satisfactoriamente"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/usuarios/perfiles.xhtml");
        }
    }
    //asignar el tipo del filtro

 

}
