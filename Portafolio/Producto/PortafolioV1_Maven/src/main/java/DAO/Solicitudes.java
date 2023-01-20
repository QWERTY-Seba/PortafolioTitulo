/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Solicitud;
import Clases.Subasta_Produccion;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Seba
 */
public class Solicitudes {
       
    
    public static void NuevaSolicitud(Integer id_usuario, Integer id_producto, Integer cantidad, String Calidad){
        Session ss = Conexion.getSession();
        Solicitud s = new Solicitud();
        
        try{
            Integer i  = (Integer) ss.createSQLQuery("CALL CrearSolicitud(:id_u,:id_p,:c,:ca)")
                        .setParameter("id_u", id_usuario)
                        .setParameter("id_p", id_producto)
                        .setParameter("c", cantidad)
                        .setParameter("ca", Calidad)
                        .uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
            
    
    }
    
    public static void CrearEspecificacion_Costos(Clases.Especificacion_Costos es){
        Session ss = Conexion.getSession();
        try{
           Object nada = ss.createSQLQuery("CALL CrearEspecificacion_Costos(:s,:p1,:p2,:p3,:p4)")
                        .setParameter("s", es.getId_solicitud())
                        .setParameter("p1",es.getC_transporte())
                        .setParameter("p2", es.getC_aduana())
                        .setParameter("p3", es.getC_servicio())
                        .setParameter("p4", es.getC_comision())
                        .uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
    }
    
    
    public static Solicitud SolicitudById(Integer id_solicitud){
        Session ss = Conexion.getSession();
        Solicitud s = new Solicitud();
        
        try{
           s  = (Solicitud) ss.createSQLQuery("CALL SolicitudById(:s)")
                        .addEntity(Clases.Solicitud.class)
                        .setParameter("s", id_solicitud).uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        
        return s;
    }
    
    public static List<Solicitud> ListarSolicitudesUsuario(Integer id_usuario){
        Session ss = Conexion.getSession();
        List<Solicitud> ls = new ArrayList<Solicitud>();
        
        try{
           Query q  = ss.createSQLQuery("CALL ListarSolicitudesUsuario(:id_u)")
                        .addEntity(Clases.Solicitud.class)
                        .setParameter("id_u", id_usuario);
            ls = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        
        return ls;
    }
    
    
    public static List<Solicitud> ListarSolicitudes(){
        Session ss = Conexion.getSession();
        List<Solicitud> s = new ArrayList<>();
        
        try{
            Query q =  ss.createSQLQuery("CALL ListarSolicitudes()").addEntity(Clases.Solicitud.class);
            s  = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return s; 
    }
}
