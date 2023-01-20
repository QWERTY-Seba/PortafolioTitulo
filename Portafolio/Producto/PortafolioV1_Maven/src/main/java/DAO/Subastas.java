/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Pedido;
import Clases.Produccion;
import Clases.Subasta;
import Clases.Subasta_Produccion;
import Clases.Subasta_Venta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Seba
 */
public class Subastas {
    

    public static void CrearSubastaVenta(Subasta_Venta sv){
        Session ss = Conexion.getSession();
        try {
            Object o = ss.createSQLQuery("CALL CrearSubastaVenta(:p_fecha_termino,:p_tipo_subasta,:p_precio_kg,:p_id_produccion)")
                    .setParameter("p_fecha_termino", Integer.valueOf(sv.getFecha_termino()))
                    .setParameter("p_tipo_subasta", sv.getTipo_subasta())
                    .setParameter("p_precio_kg", sv.getPrecio_por_kg())
                    .setParameter("p_id_produccion", sv.getProduccion().getId_produccion()).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    
    }
//
    public static void CrearSubastaProduccion(Integer id_solicitud){
        Session ss = Conexion.getSession();

        try {
            Object o = ss.createSQLQuery("CALL CrearSubastaProduccion(:p_id_solicitud)")
                    .setParameter("p_id_solicitud", id_solicitud).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }
    
    public static void CrearSubastaTransporte(Integer id_solicitud){
        Session ss = Conexion.getSession();

        try {
            Object o = ss.createSQLQuery("CALL CrearSubastaTransporte(:p_id_solicitud)")
                    .setParameter("p_id_solicitud", id_solicitud).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }
    
    
    
    public static List<Clases.Subasta_Transporte> ListarSubastaTransporte(boolean todo){
        Session ss = Conexion.getSession();
        List<Clases.Subasta_Transporte> lista = new ArrayList<Clases.Subasta_Transporte>();
        
        try{
            lista = ss.createSQLQuery("CALL ListarSubastas('Subasta_Transporte',:todo)")
                    .setParameter("todo", todo)
                    .addEntity(Clases.Subasta_Transporte.class).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lista; 
    
    }
        
    public static List<Subasta_Produccion> ListarVentasExternas(boolean todo){
        Session ss = Conexion.getSession();
        List<Subasta_Produccion> lsp = new ArrayList<Subasta_Produccion>();
        
        try{
            lsp = ss.createSQLQuery("CALL ListarSubastas('Subasta_Produccion',:todo)")
                    .setParameter("todo", todo)
                    .addEntity(Clases.Subasta_Produccion.class).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lsp; 
    }
    
    
    public static List<Subasta_Venta> ListarVentasInternas(boolean todo){
        Session ss = Conexion.getSession();
        List<Subasta_Venta> lsp = new ArrayList<Subasta_Venta>();
        try{
            lsp  = ss.createSQLQuery("CALL ListarSubastas('Venta_Local,Venta_Interna',:todo)")
                    .setParameter("todo", todo)
                    .addEntity(Clases.Subasta_Venta.class)
                    .getResultList();
                  
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lsp; 
    }
    
    
    
    
    // Listar Subastas entre I y el ultimo valor de la sequencia
    @Deprecated
    public static List<Subasta_Venta> ListarVentasInternasRango(int id){
        Session ss = Conexion.getSession();
        List<Subasta_Venta> lsp = new ArrayList<Subasta_Venta>();
        try{
            Query q  = ss.createSQLQuery("CALL ListarVentasInternas(:id)").addEntity(Clases.Subasta_Venta.class).setParameter("id", id);
            lsp = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lsp; 
    }
    
    
    @Deprecated
    public static List<Produccion> ProduccionesPorSubasta(int s){
        Session ss = Conexion.getSession();
        List<Produccion> lp = new ArrayList<Produccion>();
        
        try{
            Query q  = ss.createSQLQuery("CALL ListarProducciones(:s)")
                    .addEntity(Clases.Produccion.class)
                    .setParameter("s", s);
            lp = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lp; 
    
    }
    
    
    
    public static List<Object[]> ListarEstadisticaInterna(){
        
        Session ss = Conexion.getSession();
        List<Object[]> lp = new ArrayList<Object[]>();
        
        try{
            lp  = ss.createSQLQuery("CALL ListarEstadisticaInterna()")
                    .getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lp; 
    
    
    }
}
    
    


