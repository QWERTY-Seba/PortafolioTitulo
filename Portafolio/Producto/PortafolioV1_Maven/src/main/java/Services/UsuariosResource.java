/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Clases.Cliente;
import Clases.Contrato;
import Clases.Valores_Estaticos.Estados;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Seba
 */
@Path("Usuarios")
public class UsuariosResource {

    @Context
    private UriInfo context;
    
    private Gson gson = new GsonBuilder().serializeNulls().create();
    

    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
    }

    /**
     * Retrieves representation of an instance of Services.UsuariosResource
     * @param id -1 for todo
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") Integer id) {
        //return gson.toJson(DAO.Usuarios.ListarClientes());
        String r = new String();
        if(id == -1){
             r = gson.toJson(DAO.Usuarios.ListarClientes());
         }else{
             r = gson.toJson(DAO.Usuarios.ClienteById(id));
         }
         return r;        
    }  
    @GET
    @Path("/Pagos")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
         return gson.toJson(DAO.DAO_Pagos.ListarPagos());        
    }  
    @POST
    @Path("/Pagos")
    @Consumes(MediaType.APPLICATION_JSON)
    public void getJson(String content) {
        Object[] result = gson.fromJson(content, Object[].class);
        System.out.println(result[0] + " " + result[1]);
        DAO.DAO_Pagos.EnviarPago( ((Double)result[1]).intValue(),((Double)result[0]).floatValue() );
         //Recuperar correo de persona where id = result[1]
         //Recuperar monto de pedido, select from subasta join. ..
         //Enviar a DAO_Pagos
         

        //return gson.toJson(DAO.DAO_Pagos.ListarPagos());        
    }  
    
    @GET
    @Path("/{id}/Contrato")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContrato(@PathParam("id") Integer id) {
        List<Contrato> lc = DAO.Usuarios.ListarContratoCliente(id);
        //lc.forEach(s -> s.setLec(DAO.Usuarios.ListarEspecificacionContrato(s.getId_contrato())));
        
        return gson.toJson(lc);    
    }  
    @PUT
    @Path("/Actualizar")
    public void ActualizarCliente(String content) {
        System.out.println(content);
        DAO.Usuarios.ActualizarCliente(gson.fromJson(content,Cliente.class));
    }
    
    @PUT
    @Path("/Contrato/{id_contrato}/Cancelar")
    public void CancelarContrato(@PathParam("id_contrato") Integer id_c) {
        DAO.Usuarios.CancelarContrato(id_c);
    }
    
    @PUT
    @Path("/Contrato/{id_contrato}/Extender/{cantidad_meses}")
    public void ExtenderContrato(@PathParam("id_contrato") Integer id_c,@PathParam("cantidad_meses") Integer c_meses) {
        DAO.Usuarios.ExtenderContrato(id_c,c_meses);
    }
    
    @GET
    @Path("/Solicitud/")
    @Produces(MediaType.APPLICATION_JSON)
    public String ListarSolicitudes() {
        return gson.toJson(DAO.Solicitudes.ListarSolicitudes());
    }  
    
    @GET
    @Path("/{id}/Solicitud")
    @Produces(MediaType.APPLICATION_JSON)
    public String SolitudesCliente(@PathParam("id") Integer id) throws UnsupportedOperationException{
        return null;
    }  
    
    @GET
    @Path("/Produccion")
    @Produces(MediaType.APPLICATION_JSON)
    public String ProduccionesAVenta(){
        return gson.toJson(DAO.Producciones.ListarProduccionesParaVenta());
    } 
    @POST
    @Path("/Solicitud/e_costos")
    @Consumes(MediaType.APPLICATION_JSON)
    public void ProduccionesAVenta(String content){
        DAO.Solicitudes.CrearEspecificacion_Costos(gson.fromJson(content, Clases.Especificacion_Costos.class));
    } 
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void AgregarCliente(String content){
        DAO.Usuarios.CrearCliente(gson.fromJson(content, Cliente.class));
    }
    
    @POST
    @Path("/Contrato")
    @Consumes(MediaType.APPLICATION_JSON)
    public void CrearContrato(String content){
        Contrato contrato = gson.fromJson(content, Contrato.class);
        
        if(contrato.getTipo_contrato().equals(Estados.Tipos_Contrato.Acceso.name())){
            DAO.Usuarios.CrearContrato(contrato);
        }else if(contrato.getTipo_contrato().equals(Estados.Tipos_Contrato.Fruta.name())){
            DAO.Usuarios.CrearContratoFruta(contrato);
        }
    }
    
    /**
     * PUT method for updating or creating an instance of UsuariosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
