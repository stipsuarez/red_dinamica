package controllers;

import clases.GruposInvestiga;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import facade.GruposInvestigaFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("gruposInvestigaController")
@SessionScoped
public class GruposInvestigaController implements Serializable {

    private GruposInvestiga current;
    private DataModel items = null;
    @EJB
    private facade.GruposInvestigaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public GruposInvestigaController() {
    }

    public GruposInvestiga getSelected() {
        if (current == null) {
            current = new GruposInvestiga();
            current.setGruposInvestigaPK(new clases.GruposInvestigaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private GruposInvestigaFacade getFacade() {
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
        current = (GruposInvestiga) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new GruposInvestiga();
        current.setGruposInvestigaPK(new clases.GruposInvestigaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getGruposInvestigaPK().setGruposInvestigaUniversidad(current.getUniversidades().getUniversidadId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruposInvestigaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (GruposInvestiga) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getGruposInvestigaPK().setGruposInvestigaUniversidad(current.getUniversidades().getUniversidadId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruposInvestigaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (GruposInvestiga) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruposInvestigaDeleted"));
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

    public GruposInvestiga getGruposInvestiga(clases.GruposInvestigaPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = GruposInvestiga.class)
    public static class GruposInvestigaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GruposInvestigaController controller = (GruposInvestigaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gruposInvestigaController");
            return controller.getGruposInvestiga(getKey(value));
        }

        clases.GruposInvestigaPK getKey(String value) {
            clases.GruposInvestigaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new clases.GruposInvestigaPK();
            key.setGruposInvestigaId(Integer.parseInt(values[0]));
            key.setGruposInvestigaUniversidad(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(clases.GruposInvestigaPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGruposInvestigaId());
            sb.append(SEPARATOR);
            sb.append(value.getGruposInvestigaUniversidad());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GruposInvestiga) {
                GruposInvestiga o = (GruposInvestiga) object;
                return getStringKey(o.getGruposInvestigaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + GruposInvestiga.class.getName());
            }
        }
    }
}
