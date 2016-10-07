/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.GruposAlumnos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Laboratorio
 */
@Stateless
public class GruposAlumnosFacade extends AbstractFacade<GruposAlumnos> implements GruposAlumnosFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_Corto2_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GruposAlumnosFacade() {
        super(GruposAlumnos.class);
    }
    
}
