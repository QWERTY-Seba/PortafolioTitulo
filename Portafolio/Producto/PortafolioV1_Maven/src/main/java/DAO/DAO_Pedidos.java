/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Cliente;
import Clases.Pedido;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Session;

/**
 *
 * @author Seba
 */
public class DAO_Pedidos {
    
    
    public static Pedido PedidoById(Integer id){
        Session ss = Conexion.getSession();
        Pedido p = new Pedido();
        try {
            Query q = ss.createSQLQuery("CALL PedidoById(:id)")
                    .addEntity(Clases.Pedido.class).setParameter("id", id);
            p = (Pedido) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return p;
    }
    
    /*
    public static Pedido PedidoById(Integer id){
        Session ss = Conexion.getSession();
        Pedido p = new Pedido();
        try {
                StoredProcedureQuery query = ss
                        .createStoredProcedureQuery("PedidoById",Clases.Pedido.class)
                        .setParameter(0, id)
                        .registerStoredProcedureParameter(
                                1,
                                Class.class,
                                ParameterMode.REF_CURSOR
                        );
                query.execute();   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return p;
    }*/
 

    
    
    
    public static List<Pedido> ListarPedidoSubasta(Integer id){
        Session ss = Conexion.getSession();
        List<Pedido> p = new ArrayList<Pedido>();
        try{
            Query q  = ss.createSQLQuery("CALL ListarPedidoSubasta(:id)").addEntity(Clases.Pedido.class).setParameter("id", id);
            p = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return p; 
    
    }
    
    public static List<Pedido> ListarPedido(int u){
        Session ss = Conexion.getSession();
        List<Pedido> p = new ArrayList<Pedido>();
        
        try{
            Query q  = ss.createSQLQuery("CALL PedidosByUsuario(:id)")
                    .addEntity(Clases.Pedido.class)
                    .setParameter("id", u);
             p = q.getResultList();
           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return p; 
    }
     /**
     * Modificable a return BigInteger
     * @param s id_Subasta 
     * @param u id_Usuario
     * @param c Cantidad
     * @param p id_Pedido
     */
    //EN USO 1/12/2021
    public static Integer RegistrarPedido(int s,int u, int c){
        Session ss = Conexion.getSession();
        Integer id = 0;
        try{
            id  = (Integer) ss.createSQLQuery("CALL CrearPedido(:id_s,:id_u,:c)")
                    .setParameter("id_s", s)
                    .setParameter("id_u", u)
                    .setParameter("c", c)
                    .uniqueResult();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return id;
    }
    
    public static void AprobarPedidos(String content){
        Session ss = Conexion.getSession();
        try {
            List<String> correos = ss.createSQLQuery("CALL AprobarPedidos(:array)")
                    .setParameter("array", content).getResultList();
            //Devolver ids que no se insertaron o si algo por el estilo
            
            new Thread( () -> {
                for(String correo : correos){
                    Clases.TestCorreo.EnviarCorreoPedido(correo);
                }
            }).start(); 
            
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }
    public static void RechazarPedidos(String content){
        Session ss = Conexion.getSession();
        try {
            Object nada = ss.createSQLQuery("CALL RechazarPedidos(:array)")
                    .setParameter("array", content).uniqueResult();
            //Devolver ids que no se insertaron o si algo por el estilo
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }
    
    
    
    
    //TESTT
     public static List<Pedido> LP(int u){
        Session ss = Conexion.getSession();
        List<Pedido> lp = new ArrayList<Pedido>();
        
        try{
            lp = ss.createSQLQuery("CALL ListarPedidoSubasta(:id)")
                        .addEntity(Clases.Pedido.class)
                        .setParameter("id", u).getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lp; 
    }
    
    
} 
