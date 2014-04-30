package controllers;

import clases.Foros;
import clases.Usuarios;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import facade.ForosFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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

@Named("forosController")
@SessionScoped
public class ForosController implements Serializable {

    private static Foros current;
    private DataModel items = null;
    @EJB
    private facade.ForosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ForosController() {
    }

    public Foros getSelected() {
        if (current == null) {
            current = new Foros();
            selectedItemIndex = -1;
        }
        return current;
    }

      public static Foros getCurrent() {
        return current;
    }

    private ForosFacade getFacade() {
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

    public String prepareList() throws IOException {
        recreateModel();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/foros/List.xhtml");
        return "List";
    }

    public void prepareView() throws IOException {
        current = (Foros) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/comentarios/comentariosTemplateClient.xhtml");        
    }

    public String prepareCreate() {
        current = new Foros();
        selectedItemIndex = -1;
        return "Create";
    }

    public void create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ForosCreated"));
            prepareCreate();
            recreatePagination();
            recreateModel();     
            FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/foros/forosTemplateClient.xhtml");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            
        }
    }

    public String prepareEdit() {
        current = (Foros) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ForosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public void destroy() {
        current = (Foros) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ForosDeleted"));
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

    public void next() {
        getPagination().nextPage();
        recreateModel();
       
    }

    public void previous() {
        getPagination().previousPage();
        recreateModel();
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Foros getForos(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Foros.class)
    public static class ForosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ForosController controller = (ForosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "forosController");
            return controller.getForos(getKey(value));
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
            if (object instanceof Foros) {
                Foros o = (Foros) object;
                return getStringKey(o.getForoId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Foros.class.getName());
            }
        }
    }
    
                                       //MIS CAMBIOS
    
    private Usuarios usuario;
    Boolean mostrarLista = true;
    public String asignarTodo(){
        try {            
             this.usuario = UsuariosController.getCurrent();             
             current.setForoCreadoPor(usuario);
             Date fecha = new Date();
             current.setForoFecha(fecha);
             return current.getForoCreadoPor().getUsrNombres();
        } catch (Exception e) {
}
        return "nada";           
    }
     public String asignarfecha(){
        try {  
             Date fecha = new Date();
             current.setForoFecha(fecha);
             return current.getForoCreadoPor().getUsrNombres();
        } catch (Exception e) {
        }
        return "nada";           
    }
     public void imprimir() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("messagePanel", new FacesMessage("Exito: ", "Usuario"+usuario.getUsrNombres()));
    }
     
        public void irA(String dire) throws IOException{
        switch(dire){
            case "crear": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/foros/Create.xhtml"); break;
            case "perfil": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/pages/perfiles.xhtml"); break; 
            case "contacto": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/pages/contactenos.xhtml"); break;    
            case "foros": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/pages/foros.xhtml"); break;    
            case "regVacante": FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/registrarVacante.xhtml"); break;    
        }
    }
        
    private String titulo = "";
    public String titulo(){
        titulo = current.getForoNombre()+" - "+current.getForoTema();
        return titulo;
    }
      
}
