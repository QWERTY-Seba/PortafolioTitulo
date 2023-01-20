/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


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

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
/**
 *
 * @author Seba
 */
@Path("Payment")
public class PaymentResource {
    /**
     * REST Web Service
     *
     * @author Seba
     */

        @Context
        private UriInfo context;

        private Gson gson = new GsonBuilder().serializeNulls().create();

        public PaymentResource() {
        }

        
        
    @POST
    @Path("/Receive")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response PostPayment(@Context HttpHeaders headers,String content) {
        
        //Proceso de Indemnizacion
        //Cuando una solicitud llegue al estado X, recibido por el cliente 
        //Habilitar opcion de Solicitar indemnizacion, rellenar formulario con evidencia 
        //subirlo a la base de datos y mostrarlos de manera especifica en el sistema local
        
        //Proceso Seleccionar Mejores Producciones
        //Seleccionar Registro_Produccion where id_subasta = ?  y estado = ?

        //update set estado aceptado where Select .. order by .. 
        //update set estado cancelada where ... ^
        
        // En listado de solicitudes , cuando el estado sea mayor a 'subasta produccion terminado' , habilitar boton para ingresar los costos 
        // del proceso 
        // Solicitud posee id de especificacion de costos > id_solicitud, costos por transporte, impuestos aduaneros, pago por servicios y comisión empresa
        
        //Proceso compra saldo
        //Crear Pedido => Crear Registro Produccion => Crear payment => enviar Comprobante => comprobar restante subasta
        // Sacar id order, id pago , id pedido , payer , value , fecha pago sysdate()
        //Proceso pago de pedido
        //Actualizar pedido => Crear Registro Produccion => Crear payment => enviar Comprobante => comprobar restante subasta
        
        
        System.out.println(headers.getRequestHeaders().toString());   
        System.out.println(content);
        return Response.ok().build();
    }
}
