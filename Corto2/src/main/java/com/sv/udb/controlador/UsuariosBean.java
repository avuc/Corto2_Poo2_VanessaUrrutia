/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
/**
 *
 * @author Laboratorio
 */
@Named(value = "usuariosBean")
@Dependent
public class UsuariosBean implements Serializable{
 @EJB
     private UsuariosFacadeLocal FCDEUsuarios;    
    private Usuarios objeUsuarios;
    private List<Usuarios> listUsuarios;
    private boolean guardar;
    private Logger logger = Logger.getLogger(RolesBean.class);

    public UsuariosFacadeLocal getFCDEUsuarios() {
        return FCDEUsuarios;
    }

    public Usuarios getObjeUsuarios() {
        return objeUsuarios;
    }

    public List<Usuarios> getListUsuarios() {
        return listUsuarios;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setFCDEUsuarios(UsuariosFacadeLocal FCDEUsuarios) {
        this.FCDEUsuarios = FCDEUsuarios;
    }

    public void setObjeUsuarios(Usuarios objeUsuarios) {
        this.objeUsuarios = objeUsuarios;
    }

    public void setListUsuarios(List<Usuarios> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    
    
    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
    }
    
    
      @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
         public void limpForm()
    {
        this.objeUsuarios = new Usuarios();
        this.guardar = true;        
    }
    
         public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEUsuarios.create(this.objeUsuarios);
            logger.info("Guardado: " + this.objeUsuarios.getAcceUsua()+" " + this.objeUsuarios.getContUsua()+ this.objeUsuarios.getEstaUsua());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.listUsuarios.add(this.objeUsuarios);
            this.limpForm();            
        }
        catch(Exception ex)
        {
            logger.error("Error al guardar: ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
    }
          
         public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance();
        try
        {
            this.listUsuarios.remove(this.objeUsuarios);
            FCDEUsuarios.edit(this.objeUsuarios);
            logger.info("Modificado: "+this.objeUsuarios.getAcceUsua()+" " + this.objeUsuarios.getContUsua()+ this.objeUsuarios.getEstaUsua());
             this.listUsuarios.add(this.objeUsuarios);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al modificar: ",ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
    }
       
       
            public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEUsuarios.remove(this.objeUsuarios);
            this.listUsuarios.remove(this.objeUsuarios);
            logger.info("Eliminado: " +this.objeUsuarios.getAcceUsua()+" " + this.objeUsuarios.getContUsua()+ this.objeUsuarios.getEstaUsua());
              this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            logger.error("Error al eliminar: ", ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
    }
            
                public void consTodo()
    {
        try
        {
            this.listUsuarios = FCDEUsuarios.findAll();
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar registros ", ex);
            ex.printStackTrace();
        }
    }
       
     public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiAlumPara"));
        try
        {
            this.objeUsuarios = FCDEUsuarios.find(codi);
            this.guardar = false;
            logger.info("Consultado "  +this.objeUsuarios.getAcceUsua()+" " + this.objeUsuarios.getContUsua()+ this.objeUsuarios.getEstaUsua());
               ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", this.objeUsuarios.getAcceUsua(), this.objeUsuarios.getContUsua(),this.objeUsuarios.getEstaUsua()) + "')");
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar registro",ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
                
}
