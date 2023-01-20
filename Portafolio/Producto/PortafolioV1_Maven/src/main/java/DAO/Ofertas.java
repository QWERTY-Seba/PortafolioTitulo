/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Oferta_Transporte;
import Clases.Pedido;
import Clases.Registro_Produccion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Seba
 */
public class Ofertas {
    
        public static List<String> ListarOfertasSeleccionadas(){
            Session ss = Conexion.getSession();
            List<String> lista = new ArrayList<String>();
            try{
                lista  = ss.createSQLQuery("CALL ListarCorreosSeleccionados()").
                            getResultList();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                ss.close();
            }
            return lista; 
        
        }
        
        public static List<Oferta_Transporte> ListarOfertasT(Integer id){
                Session ss = Conexion.getSession();
            List<Oferta_Transporte> p = new ArrayList<Oferta_Transporte>();
            try{
                p = ss.createSQLQuery("CALL ListarOfertasT(:id)").addEntity(Clases.Pedido.class).setParameter("id", id).getResultList();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                ss.close();
            }
            return p; 
        
        }
        
        public static List<Registro_Produccion> ListarOfertas(int u){
        Session ss = Conexion.getSession();
        List<Registro_Produccion> p = new ArrayList<Registro_Produccion>();
        try{
            Query q  = ss.createSQLQuery("CALL PedidosByUsuario(:id)").addEntity(Clases.Pedido.class).setParameter("id", u);
            p = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return p; 
    }
        
       //NO USAR GETSINGLERESULT , SP NO EXECUTE
      public static void CrearOfertaTrasporteCeNull(Oferta_Transporte ot){
        Session ss = Conexion.getSession();
        try{
            Integer a  = (Integer)ss.createSQLQuery("CALL CrearOfertaTrasporte(:id_sub,:id_tra,:mo_na,:mo_ex,:ce,:nc,:rut)")
                        .setParameter("id_sub", ot.getId_subasta())
                        .setParameter("id_tra", ot.getTransporte().getId_transporte())
                        .setParameter("mo_na", ot.getMonto_transporte_NA())
                        .setParameter("mo_ex", ot.getMonto_transporte_EX())
                        .setParameter("ce", ot.isCompania_externa())
                        .setParameter("nc", ot.getNombre_compania() )
                        .setParameter("rut", ot.getRut_compania()).uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
      }  
      
}
