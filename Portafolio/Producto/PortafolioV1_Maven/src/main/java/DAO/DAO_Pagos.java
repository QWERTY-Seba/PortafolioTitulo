/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Pago;
import java.util.ArrayList;
import org.hibernate.Session;
import java.io.IOException;
import java.util.List;

import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.payouts.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


/**
 *
 * @author Seba
 */
public class DAO_Pagos {

    
    public static void EnviarPago(Integer id, Float tarifa){
        Session ss = Conexion.getSession();
        try {
            List<Object[]> resultado = ss.createSQLQuery("CALL InfoEnviarPago(:id)")
                    .setParameter("id", id)
                    .getResultList();
            MandarPago(
                        (String)resultado.get(0)[3],
                        Math.round( (( (Integer)resultado.get(0)[1] * (Integer)resultado.get(0)[2]) * (100 - tarifa) / 100) / 800),
                        "Distribucion Compra de Produccion " + resultado.get(0)[0] + " tarifa : " + tarifa, 
                        (Integer)resultado.get(0)[4],(String)resultado.get(0)[5],(String)resultado.get(0)[6]);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }
    
    public static void MandarPago(String correo, Integer total, String Descripcion, Integer id, String nombre, String pais){
        
        List<PayoutItem> items = Arrays.asList(
                        new PayoutItem()
                        .senderItemId("Test_txn_" + new Random().nextInt())
                        .note("Empty")
                        .receiver(correo)
                        .amount(new Currency().currency("USD")
                        .value(total.toString()) ));
                
        
        
        CreatePayoutRequest request = new CreatePayoutRequest()
                .senderBatchHeader(new SenderBatchHeader()
                        .senderBatchId("Test_sdk_" + new Random().nextInt())
                        .emailMessage("Empty")
                        .emailSubject(Descripcion)
                        .note("Empty")
                        .recipientType("EMAIL"))
                .items(items);

		try {
                        
			PayoutsPostRequest httpRequest = new PayoutsPostRequest().requestBody(request);
			HttpResponse<CreatePayoutResponse> response = Credentials.client.execute(httpRequest);

			CreatePayoutResponse payouts = response.result();
                        
			PayoutsGetRequest  request2 = new PayoutsGetRequest(payouts.batchHeader().payoutBatchId());
                        HttpResponse<PayoutBatch> response2 = Credentials.client.execute(request2);
                        PayoutBatch payout = response2.result();
                        
                        Pago pago = new Pago();
                        pago.setId_order(payout.batchHeader().payoutBatchId());
                        pago.setId_payment(payout.items().get(0).payoutItemId());
                        pago.setCurrency("USD");
                        pago.setValue(Double.valueOf(payout.items().get(0).payoutItem().amount().value()).intValue());    
                        pago.setCorreo(correo);
                        pago.setNombre(nombre);
                        pago.setPais(pais);
                        pago.setId_pago_in(id);
                        Integer i = RegistrarPago(pago);
                                                
                        //System.out.println("Payouts Batch ID: " + payouts.batchHeader().payoutBatchId());
			//payouts.links().forEach(link -> System.out.println(link.rel() + " => " + link.method() + ":" + link.href()));
		
                
                
                }catch (IOException ioe) {
			if (ioe instanceof HttpException) {
				HttpException he = (HttpException) ioe;
				System.out.println(he.getMessage());
				he.headers().forEach(x -> System.out.println(x + " :" + he.headers().header(x)));
			} else {
			}
		}
    }
    
    
    public static List<Pago> ListarPagos(){
        Session ss = Conexion.getSession();
        List<Pago> lista = new ArrayList<Pago>();
        
        try {
            lista = ss.createSQLQuery("CALL ListarPagos()")
                    .addEntity(Pago.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return lista;    
    }
    
    public static Integer RegistrarPago(Clases.Pago pago){
        Session ss = Conexion.getSession();
        Integer id = 0;
        try {
            id = (Integer) ss.createSQLQuery("CALL RegistrarPago(:id_order, :id_payment, :currency, :p_value, :id_pedido, :correo, :nombre, :pais,:id_p_2,:id_s)")
                    .setParameter("id_order", pago.getId_order())
                    .setParameter("id_payment", pago.getId_payment())
                    .setParameter("currency", pago.getCurrency())
                    .setParameter("p_value", pago.getValue())
                    .setParameter("id_pedido", pago.getId_pedido())
                    .setParameter("correo", pago.getCorreo())
                    .setParameter("nombre", pago.getNombre())
                    .setParameter("pais", pago.getPais())
                    .setParameter("id_p_2", pago.getId_pago_in())
                    .setParameter("id_s", pago.getId_solicitud())
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
        return 0;
    }
    
    public static boolean ComprobarPago(){
        throw new UnsupportedOperationException();
    }
    
    
}
