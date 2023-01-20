/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;




import java.io.IOException;
import java.util.List;

import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.payouts.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import tech.tablesaw.api.Table;


import tech.tablesaw.table.*;
import tech.tablesaw.columns.*;

/**
 *
 * @author Seba
 */
public class MainTest {
    /**
     * @param args the command line arguments
     */
                
    public static void main(String[] args) {    
        
        
        //Grafico A , Cantidad de compras registradas por tipo de subasta
                
        //Tabla A Cantidad de compras segun calidad
        
        //Grafico B Top barra de los paises desde donde mas se compra
 
        //Grafico C Top de las frutas segun la cantidad de repeticion pais
        
        
        
        
    
        
        
        /*
        new Thread(() -> {
            while(true){
                try{
                    TimeUnit.MINUTES.sleep(2);
                }catch(Exception e){
                    e.printStackTrace();
                }
                DAO.Ofertas.ListarOfertasSeleccionadas().forEach(s -> {
                    Clases.TestCorreo.EnviarCorreoOferta(s);

                });
            }
        }).start();
        */

        //@Formula(value="(SELECT IFNULL(sum(r.cantidad_seleccionada),0) as cantidad_vendida FROM registro_produccion r 
        //WHERE r.id_produccion = id_produccion and r.estado in (\"Aprobado\"))")
        
        
        /*
        Clases.Produccion p = DAO.Producciones.ListarProduccion(21).get(0);
        System.out.println(p.toString());
        System.out.println(
        p.getLr().stream()
                .filter(s -> s.getEstado().equals("Aprobado") || s.getEstado().equals("Pagado") )
                .map(s -> s.getCantidad_seleccionada())
                .reduce(0,Integer::sum));
        */
        

        /*
        Map<String,Integer> toPay = new HashMap<String,Integer>();
        toPay.put("sb-2gj7w8899903@personal.example.com", 11);
        toPay.put("sb-0cz8l8482896@personal.example.com", 15);
        toPay.put("sb-lbiie6897837@personal.example.com", 12);
        
        List<PayoutItem> items = toPay.entrySet().stream().map(s -> 
                        new PayoutItem()
                        .senderItemId("Test_txn_" + new Random().nextInt())
                        .note("Your 1$ Payout!")
                        .receiver(s.getKey())
                        .amount(new Currency().currency("USD").value(s.getValue().toString()))
                ).collect(Collectors.toList());
        
        
        CreatePayoutRequest request = new CreatePayoutRequest()
                .senderBatchHeader(new SenderBatchHeader()
                        .senderBatchId("Test_sdk_" + new Random().nextInt())
                        .emailMessage("SDK payouts test txn")
                        .emailSubject("Test_1103")
                        .note("Enjoy your Payout!!")
                        .recipientType("EMAIL"))
                .items(items);

		try {
                    
			PayoutsPostRequest httpRequest = new PayoutsPostRequest().requestBody(request);
			HttpResponse<CreatePayoutResponse> response = Credentials.client.execute(httpRequest);

			CreatePayoutResponse payouts = response.result();
			System.out.println("Payouts Batch ID: " + payouts.batchHeader().payoutBatchId());
			payouts.links().forEach(link -> System.out.println(link.rel() + " => " + link.method() + ":" + link.href()));
		
                
                
                }catch (IOException ioe) {
			if (ioe instanceof HttpException) {
				HttpException he = (HttpException) ioe;
				System.out.println(he.getMessage());
				he.headers().forEach(x -> System.out.println(x + " :" + he.headers().header(x)));
			} else {
			}
		}
            */  
        
        //Clases.Pedido p = DAO.DAO_Pedidos.PedidoById(32);
        //System.out.println(Clases.Registro_Estados.buscarCreado(p.getRegistro_estados()).toString());
        
        //System.out.println(Registro_Estados.buscarEstado(p.getRegistro_estados(),"Aprobado"));   
        /*
        Gson gson = new Gson();
        String json = "{\"name\":\" Spring REST tutorials\", \"author\":\"abc\",\"price\":\"9.99\"}";
        JsonObject o = gson.fromJson(json, JsonObject.class);
        */
        
        //System.out.println(DAO.Conexion.get("https://mindicador.cl/api"));
        
//        Gson gson = new Gson().newBuilder().create();
//        
//        List<Pedido> pedidos = DAO.DAO_Pedidos.ListarPedido(21);
//        //System.out.println(pedidos);
//        pedidos  = pedidos.stream().filter(s -> s.getEstado().equals("Aprobado")).collect(Collectors.toList());
//        
//        System.out.println(gson.toJson(pedidos));
        
        //pedidos.forEach(System.out::println);
        
        /*
        Solicitud solicitud = DAO.Solicitudes.SolicitudById(24);
        List<Produccion> producciones = DAO.Producciones.ListarProduccionesParaVenta();
        HashMap<Integer,Integer> resultados = new HashMap<Integer,Integer>();
        
        producciones.forEach(s -> {
            int calculo = ((s.getCantidad_inicial() * 1) / s.getLr().get(0).getPrecio_por_kg());
            resultados.put(s.getId_produccion(), calculo);
        });
        
        System.out.println(resultados);
        */
        
        // COMPRA DE 500KG DE CALIDAD 4
        // 2000 PTS
        
        // 300*4/500
        // 200*4/300
        
        
        
        
        /*
        String url = "http://localhost:8080/PortafolioV1/Login";
        
        
        
        
        
        
        
            try {
                URLConnection connection = new URL(url).openConnection();
                //connection.setRequestProperty("Accept-Charset", charset);
                InputStream response = connection.getInputStream();
                
                try(Scanner scanner = new Scanner(response)){
                    String responseBody = scanner.useDelimiter("\\A").next();
                    System.out.println(responseBody);
                }
               
                
                
            } catch (Exception e) {
            }
       
        */
        
        /*
        class a{
            int id;
            int cantidad;
            float calidad;
            int precio;

                public a(int id, int cantidad, float calidad, int precio) {
                    this.id = id;
                    this.cantidad = cantidad;
                    this.calidad = calidad;
                    this.precio = precio;
                }

            
                           
        }
        //Produccion requerida 1000 2 
        a b = new a(1,200,1.8f,500);
        a c = new a(2,300,1.8f,520);
        List<a> d = new ArrayList<a>();
        d.add(c);
        d.add(b);
        Map<int,int> m = new HashMap<int,int>();
        
        d.forEach(s ->{
        
        
        
        });
        */
        
        
        
        /*
        LocalDate l = LocalDate.now();
        
        ArrayList<Boolean> a = new ArrayList();       
        String[] dates = {"2021/11/06","2021/11/07","2021/11/06","2021/11/06","2021/11/06","2021/11/06","2021/11/07","2021/11/06","2021/11/06","2021/11/06","2021/11/06","2021/11/07","2021/11/06","2021/11/06","2021/11/06","2021/11/06","2021/11/07","2021/11/06","2021/11/06","2021/11/06"};
        ArrayList<Long[]> times= new ArrayList();
        
        for(int i = 1;i <= 10 ;i++){
            long startTime = System.nanoTime();
            for (String date : dates) {
                a.add(date.equals("2021/11/06"));
            }
            long endTime = System.nanoTime();
            times.add(new Long[]{endTime,startTime});
        }
        
        times.forEach(s -> System.out.println(Arrays.toString(s)));*/
        
        /*
        class money{
            int i = 9;
        }
        class pay extends money{
            int e = this.i + 1;
        }
        
        List<money> lm = new ArrayList<money>();
        pay m = new pay();
        lm.add(m);
        try{
        while(true){
            TimeUnit.SECONDS.sleep(30);
            System.out.println("a");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        */        
        //Pedidos.RegistrarPedido(1,1,231,1);
        /*
        List<Subasta_Venta> ls = Subastas.ListarVentasInternas();
        for (Subasta_Venta sv : ls){
            sv.setLpro(Subastas.ProduccionesPorSubasta(sv.getId_subasta()));
            System.out.println(sv.toString());
        }
        */
    }
    /*
    public static List<Subasta_Venta> ListarVentas(){
        List<Subasta_Venta> ls = Subastas.ListarVentasInternas();
        for (Subasta_Venta sv : ls){
            sv.setLpro(Subastas.ProduccionesPorSubasta(sv.getId_subasta()));
        }
        return ls;
    }  

      */  











        //SessionFactory mf = new Configuration().configure("/resources/hibernate.cfg.xml").addAnnotatedClass(Pedido.class).buildSessionFactory();
        //Session ss = mf.openSession();
        /*
        String id = "1";
        try{
            System.out.println(Usuarios.Ingreso("", "").toString());
            //Pedido p = new Pedido(3,"2021-10-07"," "," ","2021-09-30"," "," ");
            //ss.beginTransaction();
            //ss.save(p);
            //ss.getTransaction().commit();
            //Query q  = ss.createSQLQuery("CALL PedidoById(:id)").addEntity(Pedido.class).setParameter("id", id);
            //List<Pedido> lp = q.getResultList();
            //for (Pedido pfor : lp){
            //    System.out.println(pfor.toString());
            //}
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            mf.close();
        }

        */
    
    
}
