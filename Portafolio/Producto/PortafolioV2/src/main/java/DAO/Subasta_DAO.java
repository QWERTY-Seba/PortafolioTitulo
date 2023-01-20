/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Pedido;
import Clases.Subasta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Seba
 */
public class Subasta_DAO {
    
           
    private static final String url = "http://localhost:8181/PortafolioV1_Maven/webresources/Subastas/";
    
    public static void CrearSubastaVenta(Subasta s){
        ABS.post(url+"S_Venta/Crear", s);
    }
    
    public static void CrearSubastaE(Integer id){
        ABS.post(url+"S_Produccion/Crear", id);
    }
    public static void CrearSubastaT(Integer id){
        ABS.post(url+"S_Transporte/Crear", id);
    }
    
    public static List<Subasta> listar(String tipo){
        List<Subasta> s = ABS.get(url + tipo, Subasta[].class);
        return s;
    }
    
    public static List<Pedido> listarPedido(Integer id){
        List<Pedido> s = ABS.get(url+ "Pedidos/" + id, Pedido[].class);
        return s;
    }
    
    public static void AprobarPedidos(Vector<Integer> v){
        ABS.put(url+ "Pedidos/Aprobar/", v);
    }
    
    public static void RechazarPedidos(Vector<Integer> v){
        ABS.put(url + "Pedidos/Rechazar/",v);
    }
}
