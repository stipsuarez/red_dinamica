package controllers;

import clases.Universidades;
import clases.Usuarios;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import facade.UniversidadesFacade;
import facade.UsuariosFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
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
import javax.faces.validator.ValidatorException;

@Named("usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    private static Usuarios current;
    private DataModel items = null;
    @EJB
    private facade.UsuariosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public UsuariosController() {
         //items= new ListDataModel(getListaUsrs());
    }

    public Usuarios getSelected() {
        if (current == null) {
            current = new Usuarios();
            selectedItemIndex = -1;
        }
        return current;
    }

    public static Usuarios getCurrent() {
        return current;
    }

    public static void setCurrent(Usuarios current) {
        UsuariosController.current = current;
    }

    private UsuariosFacade getFacade() {
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
                    //if (flag == 0) 
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    //else return new ListDataModel(listaUsuariosE);
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
        current = (Usuarios) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Usuarios();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuariosCreated"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Usuarios) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Usuarios) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuariosDeleted"));
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

    public Usuarios getUsuarios(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Usuarios.class)
    public static class UsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosController controller = (UsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosController");
            return controller.getUsuarios(getKey(value));
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
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getUsrId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuarios.class.getName());
            }
        }
    }
//Este es mi código
    // Validar contraseña 
    private String pass1 = "";
    private String actualPass = "";
    private String nuevaPass = "";
    private String nuevaPassConfir = "";
    List<Usuarios> listaUsuarios = new ArrayList<>();

    public String getPass1() {
        return pass1;
    }

    private UniversidadesFacade getUFacade() {
        return this.Ufacade;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public void asignarCon(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        this.pass1 = arg2.toString();

    }

    public void validarCon(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        try {

            String attribute = (String) component.getAttributes().get("password");
            if (!value.equals(attribute)) {
                FacesMessage message = new FacesMessage();
                message.setSummary("La contraseña no coincide");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("datosFrom", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden"));

        }
    }

    public String getActualPass() {
        return actualPass;
    }

    public void setActualPass(String actualPass) {
        this.actualPass = actualPass;
    }

    public String getNuevaPass() {
        return nuevaPass;
    }

    public void setNuevaPass(String nuevaPass) {
        this.nuevaPass = nuevaPass;
    }

    public String getNuevaPassConfir() {
        return nuevaPassConfir;
    }

    public void setNuevaPassConfir(String nuevaPassConfir) {
        this.nuevaPassConfir = nuevaPassConfir;
    }

    public void validarPassActual(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        String actual = current.getUsrPass();
        actualPass = actual;
        if (!arg2.equals(actual)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña no es la actual " + actual + "  Vieja: arg2 =" + arg2, "La contraseña no es la actual: " + actual + "  Vieja: arg2 =" + arg2));

        }
    }

    public void nuevaPass(FacesContext arg0, UIComponent arg1, Object arg2) {
        nuevaPass = (String) arg2;
        current.setUsrPass(nuevaPass);
    }

    public void validarNuevaConfirmar(FacesContext arg0, UIComponent arg1, Object arg2)
            throws ValidatorException {
        nuevaPassConfir = (String) arg2;
        current.setUsrPass(nuevaPass);

        if (!arg2.equals(nuevaPass)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseñas no coinciden", "La contraseñas no coinciden "));
        }
    }
    //Buscar personas
    private String nombre_buscar;
    private String filtar_por = "1";// tipo de filtro al buscar usuarios:(1=nombre, 2= email, 3= universidad)
    @EJB
    private facade.UniversidadesFacade Ufacade;
    List<Usuarios> listaUsuariosE = new ArrayList<>();
    Universidades u_select;
    private Usuarios usrSelect;
    List<Universidades> listaUs = new ArrayList<>();
    private boolean buscarU = false;
    List<Usuarios> listaUsrs = new ArrayList<>();
   
    public String getFiltar_por() {
        return filtar_por;
    }

    public void setFiltar_por(String filtar_por) {
        this.filtar_por = filtar_por;
    }

    public Universidades getU_select() {
        return u_select;
    }

    public void setU_select(Universidades u_select) {
        this.u_select = u_select;
    }

    public List<Usuarios> getListaUsuariosE() {
        return listaUsuariosE;
    }

    public void setListaUsuariosE(List<Usuarios> listaUsuariosE) {
        this.listaUsuariosE = listaUsuariosE;
    }

    public String getNombre_buscar() {
        return nombre_buscar;
    }

    public void setNombre_buscar(String nombre_buscar) {
        this.nombre_buscar = nombre_buscar;
    }

    public Usuarios getUsrSelect() {
        return usrSelect;
    }

    public void setUsrSelect(Usuarios usrSelect) {
        this.usrSelect = usrSelect;
        JsfUtil.addSuccessMessage("Entra a cabiar el usuario seleccionado: " + this.usrSelect);
    }

    public void asignarNombreBuscar(FacesContext facesContext, UIComponent component, Object value) {
        try {
            this.nombre_buscar = value.toString();

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nombre: " + value + "Error: ", "" + e + "   " + e.getLocalizedMessage());
            FacesContext.getCurrentInstance().addMessage("buscar", msg);
        }
    }

    public List<Universidades> getListaUs() {
        return listaUs;
    }

    public void setListaUs(List<Universidades> listaUs) {
        this.listaUs = listaUs;
    }

    public List<Usuarios> getListaUsrs() {
        listaUsrs = ejbFacade.findAll();
        listaUsrs.remove(current);
        return listaUsrs;
    }

    public void setListaUsrs(List<Usuarios> listaUsrs) {
        this.listaUsrs = listaUsrs;
    }

    public void asignarUsuariosEncontradosPorNombre() {
        try {
            listaUsuariosE = ejbFacade.buscarUsuarios_por_nombre(this.nombre_buscar);
            listaUsuariosE.remove(current);
            HashSet<Usuarios> hashSet = new HashSet(listaUsuariosE);
            listaUsuariosE = new ArrayList<>();
            // Eliminamos Usuarios repetidos
            for (Iterator it = hashSet.iterator(); it.hasNext();) {
                listaUsuariosE.add((Usuarios) it.next());
            }
            if (listaUsuariosE.isEmpty()) {
                items = new ListDataModel(getListaUsrs());
                JsfUtil.addSuccessMessage("No hay usuarios");
            } else {
                items = new ListDataModel(listaUsuariosE);
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + this.nombre_buscar + "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }

    public void asignarUsuariosEncontradosPorEmail() {
        try {
            listaUsuariosE = ejbFacade.buscarUsuarios_por_email(this.nombre_buscar);
            listaUsuariosE.remove(current);
            HashSet<Usuarios> hashSet = new HashSet(listaUsuariosE);
            listaUsuariosE = new ArrayList<>();
            // Eliminamos Usuarios repetidos
            for (Iterator it = hashSet.iterator(); it.hasNext();) {
                listaUsuariosE.add((Usuarios) it.next());
            }
            if (listaUsuariosE.isEmpty()) {
                items = new ListDataModel(getListaUsrs());
                JsfUtil.addSuccessMessage("No hay usuarios");
            } else {
                items = new ListDataModel(listaUsuariosE);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + this.nombre_buscar + "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }

    public void asignarUsuariosEncontradosPorUniversidad() {
        try {
            listaUsuariosE = ejbFacade.buscarUsuarios_por_universidad(u_select.getUniversidadId());
            listaUsuariosE.remove(current);
            HashSet<Usuarios> hashSet = new HashSet(listaUsuariosE);
            listaUsuariosE = new ArrayList<>();
            // Eliminamos Usuarios repetidos
            for (Iterator it = hashSet.iterator(); it.hasNext();) {
                listaUsuariosE.add((Usuarios) it.next());
            }
            if (listaUsuariosE.isEmpty()) {
                items = new ListDataModel(getListaUsrs());
                JsfUtil.addSuccessMessage("No hay usuarios");
            } else {
                items = new ListDataModel(listaUsuariosE);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al asignar usuarios por U: " + this.nombre_buscar + "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }

    public boolean isBuscarU() {
        try {
            return buscarU;
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar las Universidades", "" + e + "\nLocalización: " + e.getLocalizedMessage()));
        }
        return false;
    }

    public void asignarFiltro(FacesContext facesContext, UIComponent component, Object value) {
        try {

            buscarU = false;
            nombre_buscar = "";
            listaUsuariosE = new ArrayList<>();
            items = new ListDataModel(getListaUsrs());//pasamos la lista al datamodel
            this.filtar_por = "" + value;
            if (filtar_por.equals("3")) {
                listaUs = Ufacade.findAll();
                u_select = listaUs.get(0);
                buscarU = true;

            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error filtro: " + value + " " + e + "\nLocalize: " + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }

    public void aplicarFiltro() {
        try {
            switch (filtar_por) {
                case "1":
                    asignarUsuariosEncontradosPorNombre();
                    break;
                case "2":
                    asignarUsuariosEncontradosPorEmail();
                    break;
            }

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error filtro: " + e + "\nLocalize: " + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }

    public void aplicarFiltroUs(FacesContext facesContext, UIComponent component, Object value) {
        try {
            asignarUsuariosEncontradosPorUniversidad();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error filtro: " + e + "\nLocalize: " + e.getLocalizedMessage(), "  nombre no asignado: " + e + "\nLocalize: " + e.getLocalizedMessage()));
        }
    }


    public void irA(String dire) throws IOException {
        try {

            switch (dire) {
                case "index":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/");
                    break;
                case "perfil":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/usuarios/perfiles.xhtml");
                    break;
                case "contacto":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/componentes/contactenos.xhtml");
                    break;
                case "colectivos":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/colectivos/colectivosTemplateClient.xhtml");
                    break;
                case "foros":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/foros/forosTemplateClient.xhtml");
                    break;
                case "enviarMsj":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/conversacion/conversacionTemplateClient.xhtml");
                    break;
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al direccionar al enviar el msj: " + e);
        }
    }
    
  @PostConstruct
    public void init() {
      items= new ListDataModel(getListaUsrs());
  }
}
