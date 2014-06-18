package controllers;

import clases.Conversacion;
import clases.Mensaje;
import clases.Usuarios;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import facade.ConversacionFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@Named("conversacionController")
@SessionScoped
public class ConversacionController implements Serializable {
    
    private Conversacion current;
    private DataModel items = null;
    @EJB
    private facade.ConversacionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    public ConversacionController() {
    }
    
    public Conversacion getSelected() {
        if (current == null) {
            current = new Conversacion();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    private ConversacionFacade getFacade() {
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
        current = (Conversacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    public String prepareCreate() {
        current = new Conversacion();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String create() {
        try {
            
            JsfUtil.addSuccessMessage("creando Conversación...");
            getFacade().create(current);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ConversacionCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Error al crear: " + e + "  Localización: " + e.getLocalizedMessage() + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String prepareEdit() {
        current = (Conversacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ConversacionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String destroy() {
        current = (Conversacion) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ConversacionDeleted"));
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
    
    public Conversacion getConversacion(java.lang.Integer id) {
        return ejbFacade.find(id);
    }
    
    @FacesConverter(forClass = Conversacion.class)
    public static class ConversacionControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConversacionController controller = (ConversacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "conversacionController");
            return controller.getConversacion(getKey(value));
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
            if (object instanceof Conversacion) {
                Conversacion o = (Conversacion) object;
                return getStringKey(o.getConvId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Conversacion.class.getName());
            }
        }
    }
    //Mi código acá
    private String textoMsj;
    private Mensaje nuevo;
    private Usuarios usrDestino;
    private String ms;
    private Conversacion conv_select;
    private boolean existe_conv = false;
    
    public boolean isExiste_conv() {
        return existe_conv;
    }
    
    public void setExiste_conv(boolean existe_conv) {
        this.existe_conv = existe_conv;
    }
    
    public Conversacion getConv_select() {
        return conv_select;
    }
    
    public void setConv_select(Conversacion conv_select) {
        this.conv_select = conv_select;
    }
    
    public String getMs() {
        return ms;
    }
    
    public Usuarios getUsrDestino() {
        return usrDestino;
    }
    
    public void setUsrDestino(Usuarios usrDestino) throws IOException {
        try {
            this.usrDestino = usrDestino;
            cargarConversacion();
            
            ms = "Mi pruega" + usrDestino.getUsrApellidos();
        } catch (Exception e) {
            //FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/conversacion/conversacionTemplateClient.xhtml"); 
            ms = "error; " + e;
        }
        
        
    }
    
    public String getTextoMsj() {
        return textoMsj;
    }
    
    public void setTextoMsj(String textoMsj) {
        this.textoMsj = textoMsj;
    }
    
    @PostConstruct
    public void init() {
        try {
//            current = new Conversacion();
//            current.setConvUsr1Id(UsuariosController.getCurrent());
//            nuevo = new Mensaje();
//            items= new ListDataModel(ejbFacade.cargarConversaciones(UsuariosController.getCurrent().getUsrId()+""));
//            JsfUtil.addSuccessMessage("Creando conversación ");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al cargar la conversación: ");
        }
    }
    
    public void cargarConversacion() throws IOException {
        try {
            textoMsj = "";
            List<Conversacion> lista_conv = ejbFacade.existeConversacion(UsuariosController.getCurrent().getUsrId(), getUsrDestino().getUsrId());
            JsfUtil.addSuccessMessage("Consulta realizada... Tamaño: " + lista_conv.size());
            JsfUtil.addSuccessMessage("Destino: " + getUsrDestino());
            JsfUtil.addSuccessMessage("Origen: " + UsuariosController.getCurrent());
            HashSet<Usuarios> hashSet = new HashSet(lista_conv);
            lista_conv = new ArrayList<>();
            // Eliminamos Usuarios repetidos
            for (Iterator it = hashSet.iterator(); it.hasNext();) {
                lista_conv.add((Conversacion) it.next());
            }
            if (lista_conv.isEmpty()) {
                current = new Conversacion();
                current.setConvAsunto(" ");
                Date fechaActual = new Date();
                current.setConvUltimo(fechaActual);
                current.setConvNumero(0);
                current.setConvUsr1Id(UsuariosController.getCurrent());
                current.setConvUsr2Id(getUsrDestino());
                
                JsfUtil.addSuccessMessage("Antes del facade ... ");
                getFacade().create(current);
                JsfUtil.addSuccessMessage("Conversación nueva creada");
                recreateModel();
                setExiste_conv(false);
            } else {
                current = lista_conv.get(0);
                setExiste_conv(true);
                JsfUtil.addSuccessMessage("La conversación existe, cargando ...");
            }
            setConv_select(current);
            cargarConversaciones();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/conversacion/List.xhtml");
            JsfUtil.addSuccessMessage("Conversación nueva??  ...");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al enviar el mensaje: " + e + " Localize: " + e.getLocalizedMessage() + "  cause:   " + e.getCause());
        }
    }
    
    public void cargarConversaciones() {
        List<Conversacion> lista_conv = ejbFacade.cargarConversaciones(UsuariosController.getCurrent().getUsrId());
        HashSet<Usuarios> hashSet = new HashSet(lista_conv);
        lista_conv = new ArrayList<>();
        // Eliminamos Usuarios repetidos
        for (Iterator it = hashSet.iterator(); it.hasNext();) {
            lista_conv.add((Conversacion) it.next());
        }
        items = new ListDataModel(lista_conv);
    }

    public void enviarMsj() {
        try {
            setExiste_conv(true);
            Date fechaActual = new Date();
            Mensaje mensaje = new Mensaje();
            mensaje.setMsjTexto(textoMsj);
            mensaje.setMsjConversacion(current);
            mensaje.setMsjFecha(fechaActual);
            mensaje.setMsjLeido(false);
            mensaje.setMsjRemitente(UsuariosController.getCurrent());
            mensaje.setMsjDestinatario(usrDestino);
            current.getMensajeCollection().add(mensaje);
            update();
            setConv_select(current);
            JsfUtil.addSuccessMessage("Mensaje enviado!");
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Error al crear la conversación: " + e + "  Localización: " + e.getLocalizedMessage() + ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    public void asignarUsuario(Object arg2) {
        try {
            this.usrDestino = (Usuarios) arg2;
            JsfUtil.addSuccessMessage("Usuario seleccionado: " + arg2);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/red_dinamica/faces/web/conversacion/conversacionTemplateClient.xhtml");
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al asignar el usuario: ");
        }
    }
    
    public void asignarConversacion(Object arg2) {
        setConv_select((Conversacion) arg2);
    }
    
    public void onRowSelect(SelectEvent event) {
        try {
            setConv_select((Conversacion) (event.getObject()));
            current = conv_select;
            setUsrDestino(current.getConvUsr2Id());
            
            Collection<Mensaje> msjs =current.getMensajeCollection(); 
            for (Mensaje mensaje : msjs) {
                mensaje.setMsjLeido(true);
            }
            current.setMensajeCollection(msjs);
            update(); 
            JsfUtil.addSuccessMessage("Conversación seleccionada: " + ((Conversacion) event.getObject()).getConvId());
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al seleccionar la columna");
        }
    }
    
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Conversacion no seleccionada", "" + ((Conversacion) event.getObject()).getConvId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
