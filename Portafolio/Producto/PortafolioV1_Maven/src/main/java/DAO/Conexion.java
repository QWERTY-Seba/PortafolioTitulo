/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Seba
 */
public class Conexion {
    //"/resources/hibernate.cfg.xml"
    final static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    
    public static Session getSession(){
        return sf.openSession();
    }
    public static void cerrarSesion(Session s){
        
    }
    
    //Mandar Query Externa Creo
    public static String get(String p_url){
        String result = "";
        try{
            InputStream response = new URL(p_url).openStream();
            Scanner s = new Scanner(response).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";
        }catch(Exception e){}
        return result; 
    }
    
}
