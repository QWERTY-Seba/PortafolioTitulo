/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Transporte;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Seba
 */
public class DAO_Transporte {
      
    
    public static List<Transporte> ListarTransportes(int id_usuario){
        Session ss = Conexion.getSession();
        List<Transporte> lista = new ArrayList<Transporte>();
        try {
            lista = ss.createSQLQuery("CALL ListarTransportes(:id_u)")
                    .addEntity(Clases.Transporte.class)
                    .setParameter("id_u", id_usuario).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return lista;
    }
    
    public static void CrearTransporte(Transporte t){
        Session ss = Conexion.getSession();
        try {
            int i = ss.createSQLQuery("CALL ").addEntity(Clases.Transporte.class)
                    .setParameter("id_u", t).executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    
    
    }
    
}
