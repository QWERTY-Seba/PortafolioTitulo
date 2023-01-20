/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Seba
 */
public class DAO_Estados {
      
    
    //ESCRITO : 29/11/2021 - EN USO
      public static void ActualizarEstado(String entidad,String estado,int id){
        Session ss = Conexion.getSession();
        try {
            Object nada = ss.createSQLQuery("CALL ActualizarEstado(:entidad,:estado,:id)")
                            .setParameter("entidad", entidad)
                            .setParameter("estado", estado)
                            .setParameter("id", id).getSingleResult();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }   
}
