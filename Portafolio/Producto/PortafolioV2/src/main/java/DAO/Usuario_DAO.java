/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Cliente;
import Clases.Contrato;
import Clases.Especificacion_Costos;
import Clases.Pago;
import Clases.Solicitud;
import Clases.Produccion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Seba
 */
public class Usuario_DAO {
    
    private static final String url = "http://localhost:8181/PortafolioV1_Maven/webresources/Usuarios/";
    
    public static void ActualizarUsuario(Cliente c){
        ABS.put(url+"Actualizar", c);
    }
    
    public static List<Pago> ListarPagos(){
        return ABS.get(url+"Pagos", Pago[].class);
    }
    
    public static void EnviarPago(float tarifa,Integer id_pedido){
        ABS.post(url + "Pagos", new Object[]{tarifa,id_pedido});    
    }
    public static void CrearContrato(Contrato c){
        ABS.post(url+"Contrato", c);
    }
    
    public static List<Cliente> ListarCliente(Integer id){
        return ABS.get(url+id, Cliente[].class);
    }
    
    public static void CrearCliente(Cliente c){
        ABS.post(url, c);
    }
    
    public static List<Contrato> ListarContratoCliente(Integer id){
        return ABS.get((url+id+"/Contrato"), Contrato[].class);
    }
    
    public static void CancelarContrato(Integer id_c){
        ABS.put(url+"Contrato/"+id_c+"/Cancelar", null);
    }
    
    public static void ExtenderContrato(Integer id_c, Integer cantidad_meses){
        ABS.put(url+"Contrato/"+ id_c +"/Extender/" + cantidad_meses, null);
    }
    
    public static List<Solicitud> ListarSolicitudes(){
        return ABS.get(url+"Solicitud/", Solicitud[].class);
    }
    
    public static List<Produccion> ListarProduccionesParaVenta(){
        return ABS.get(url+"Produccion", Produccion[].class);
    }
    public static void CrearEspecificacionCostos(Especificacion_Costos es){
        ABS.post(url+"Solicitud/e_costos", es);
    }
    
    @Deprecated
    public static Cliente ClienteById(Integer id){
        Cliente c = new Cliente();
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(url + "id=" + id);            
            CloseableHttpResponse response = httpClient.execute(request);
            InputStream source = response.getEntity().getContent();
            Reader reader = new InputStreamReader(source);
            Gson g = new Gson();
            Type tipo = new TypeToken<Cliente>(){}.getType();
            c = g.fromJson(reader, tipo);
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return c;
    }
}
