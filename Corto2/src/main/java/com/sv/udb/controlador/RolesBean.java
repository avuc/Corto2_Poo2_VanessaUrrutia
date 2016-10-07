/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

import com.sv.udb.ejb.RolesFacadeLocal;
import com.sv.udb.modelo.Roles;
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
@Named(value = "rolesBean")
@ViewScoped
public class RolesBean implements Serializable{
    @EJB
     private RolesFacadeLocal FCDERoles;    
    private Roles objeRoles;
    private List<Roles> listRoles;
    private boolean guardar;
    private Logger logger = Logger.getLogger(RolesBean.class);

    public RolesFacadeLocal getFCDERoles() {
        return FCDERoles;
    }

    public Roles getObjeRoles() {
        return objeRoles;
    }

    public List<Roles> getListRoles() {
        return listRoles;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setFCDERoles(RolesFacadeLocal FCDERoles) {
        this.FCDERoles = FCDERoles;
    }

    public void setObjeRoles(Roles objeRoles) {
        this.objeRoles = objeRoles;
    }

    public void setListRoles(List<Roles> listRoles) {
        this.listRoles = listRoles;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    
    
    /**
     * Creates a new instance of RolesBean
     */
    public RolesBean() {    
    }
    
       @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
     public void limpForm()
    {
        this.objeRoles = new Roles();
        this.guardar = true;        
    }
    
       public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDERoles.create(this.objeRoles);
            logger.info("Guardado: " + this.objeRoles.getDescRole()+" " + this.objeRoles.getDireRole()+ this.objeRoles.getRefeRole()+ this.objeRoles.getEstaRole()+ this.objeRoles.getFechAltaRole()+ this.objeRoles.getFechBajaRole());
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            this.listRoles.add(this.objeRoles);
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
            this.listRoles.remove(this.objeRoles);
            FCDERoles.edit(this.objeRoles);
            logger.info("Modificado: "+this.objeRoles.getDescRole()+" "+ this.objeRoles.getDireRole()+ this.objeRoles.getRefeRole()+ this.objeRoles.getEstaRole()+ this.objeRoles.getFechAltaRole()+ this.objeRoles.getFechBajaRole());
            this.listRoles.add(this.objeRoles);
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
            FCDERoles.remove(this.objeRoles);
            this.listRoles.remove(this.objeRoles);
            logger.info("Eliminado: " +this.objeRoles.getDescRole()+" "+ this.objeRoles.getDireRole()+ this.objeRoles.getRefeRole()+ this.objeRoles.getEstaRole()+ this.objeRoles.getFechAltaRole()+ this.objeRoles.getFechBajaRole());
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
            this.listRoles = FCDERoles.findAll();
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
            this.objeRoles = FCDERoles.find(codi);
            this.guardar = false;
            logger.info("Consultado "  +this.objeRoles.getDescRole()+" "+ this.objeRoles.getDireRole()+ this.objeRoles.getRefeRole()+ this.objeRoles.getEstaRole()+ this.objeRoles.getFechAltaRole()+ this.objeRoles.getFechBajaRole());
           ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", this.objeRoles.getDescRole(), this.objeRoles.getDireRole(),this.objeRoles.getRefeRole(), this.objeRoles.getEstaRole(), this.objeRoles.getFechAltaRole(), this.objeRoles.getFechBajaRole() ) + "')");
        }
        catch(Exception ex)
        {
            logger.error("Error al consultar registro",ex);
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
    }
}
