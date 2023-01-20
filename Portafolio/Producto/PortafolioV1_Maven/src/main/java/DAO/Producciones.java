/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.math.BigInteger;
import Clases.Produccion;
import Clases.Registro_Produccion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author Seba
 */
public class Producciones {
        
    public static BigInteger CrearProduccion(String d,String f, String c, int ci,int u, int p, String estado){
        Session ss = Conexion.getSession();
        BigInteger i = new BigInteger("0");
        try{
            i  = (BigInteger) ss.createSQLQuery("CALL CrearProduccion(:d,:f,:c,:ci,:u,:p,:e)")
                    .setParameter("d", d)
                    .setParameter("f", f)
                    .setParameter("c", c)
                    .setParameter("ci", ci)
                    .setParameter("u", u)
                    .setParameter("p", p)
                    .setParameter("e", estado).uniqueResult();
            System.out.println(i);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return i;
    }
    
    public static void crearRegistroProduccion(BigInteger p,int s, int c, int pr){
        Session ss = Conexion.getSession();
        try{
            BigInteger i  = (BigInteger) ss.createSQLQuery("CALL CrearRegistroProduccion(:p,:s,:c,:pr)")
                    .setParameter("p", p)
                    .setParameter("s", s)
                    .setParameter("c", c)
                    .setParameter("pr", pr).uniqueResult();
            System.out.println(i);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
    }
        
        public static List<Produccion> ListarProduccion(int u){
        Session ss = Conexion.getSession();
        List<Produccion> p = new ArrayList<Produccion>();
        try{
            /*
            List<Object[]> cosas  = ss.createSQLQuery("CALL ListarProduccionesUsuario(:u)")
                    //.addEntity(Produccion.class)
                    .setParameter("u", u).getResultList();
            cosas.forEach(s -> System.out.println(Arrays.toString(s) ) );
            */
            
            p  = ss.createSQLQuery("CALL ListarProduccionesUsuario(:u)")
                    .addEntity(Produccion.class)
                    .setParameter("u", u).getResultList();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return p;
        }
            
        public static List<Produccion> ListarProduccionesParaVenta(){
        Session ss = Conexion.getSession();
        List<Produccion> p = new ArrayList<Produccion>();
        try {
            Query q = ss.createSQLQuery("CALL ListarProduccionesParaVenta()").addEntity(Clases.Produccion.class);
            p = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return p;
    }
        

        public static List<Registro_Produccion> ListarRegistroProduccion(int p){
        Session ss = Conexion.getSession();
        List<Registro_Produccion> lp = new ArrayList<Registro_Produccion>();
        try{
            Query q  = ss.createSQLQuery("CALL ListarRegistroProduccion(:p)").addEntity(Clases.Registro_Produccion.class).setParameter("p", p);
            lp = q.getResultList();            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lp;
        }





}



