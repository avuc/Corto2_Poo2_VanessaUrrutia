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
@Named(value = "usuariosRolesBean")
@Dependent
public class UsuariosRolesBean {

    /**
     * Creates a new instance of UsuariosRolesBean
     */
    public UsuariosRolesBean() {
    }
    
}
