 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Clases.Subasta_Venta;
import DAO.Conexion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
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

// SACAR ESTOS AL NORMALIZAR
import javax.ws.rs.core.Response;


import org.hibernate.Session;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import java.awt.Color;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import java.io.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import javax.imageio.ImageIO;

/**
 * REST Web Service
 *
 * @author Seba
 */
@Path("Subastas")
public class SubastasResource {

    @Context
    private UriInfo context;

    private Gson gson = new GsonBuilder().serializeNulls().create();
    /**
     * Creates a new instance of SubastasResource
     */
    public SubastasResource() {
    }

    /**
     * Retrieves representation of an instance of Services.SubastasResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("tipo") String tipo) {
        switch(tipo){
            case "Subasta_Produccion":
                return gson.toJson(DAO.Subastas.ListarVentasExternas(true));
            case "Subasta_Transporte":
                return gson.toJson(DAO.Subastas.ListarSubastaTransporte(true));
            case "Venta_Interna":
            case "Venta_Local":
                return gson.toJson(DAO.Subastas.ListarVentasInternas(true));
        }
        
        return null;
    }

    @GET
    @Path("/Pedidos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("id") Integer id) {
        return gson.toJson(DAO.DAO_Pedidos.LP(id));
    }

    
    @GET
    @Path("/Test")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response Test() {
    
        Session ss = Conexion.getSession();
        List<Object[]> lista2 = ss.createSQLQuery("select c.id_producto, count(c.id_producto) as producto from subasta a join registro_produccion b on a.id_subasta = b.id_subasta join produccion c on b.id_produccion = c.id_produccion join pedido d on a.id_subasta = d.id_subasta join persona f on f.id_cliente = d.id_cliente join pago e on e.id_pedido = d.id_pedido where a.tipo_subasta in (\"Venta_Local\",\"Venta_Interna\") and d.pagado = 1 group by f.pais order by c.id_producto desc limit 10;").getResultList();
        List<Object[]> lista3 = ss.createSQLQuery("select DATE_FORMAT(e.fecha_pago,'%Y-%m') 'a',\n"
                + "FLOOR(SUM(d.cantidad * a.precio_por_kg))\n"
                + "from subasta a join registro_produccion b on a.id_subasta = b.id_subasta join produccion c on b.id_produccion = c.id_produccion join pedido d on a.id_subasta = d.id_subasta join persona f on f.id_cliente = d.id_cliente join pago e on e.id_pedido = d.id_pedido where a.tipo_subasta in (\"Venta_Local\",\"Venta_Interna\") and d.pagado = 1 group by a;").getResultList();
        //Cantidad de Ventas por periodo
       
        
        
        
        
        
        
        
        
        
        
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        lista2.forEach(s -> dataset.addValue(((BigInteger)s[1]).intValue(), "Fruta_Pais", Clases.Valores_Estaticos.Frutas.getById((Integer)s[0]).name() ));
        
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        lista3.forEach(s -> dataset2.addValue(((BigDecimal)s[1]).intValue(), "Fruta_Pais", (String)s[0]  ));
        
        
        JFreeChart chart = ChartFactory.createBarChart(
                "Fruta mas demandada dentro de los ultimos meses", // Título
                "Frutas", // Etiqueta Coordenada X
                "Cantidad", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                false, // Muestra la leyenda de los productos (Producto A)
                true,
                true
        );
        
        JFreeChart lineChart = ChartFactory.createLineChart(
         "Ingreso Mensual de Ventas Internas",
         "Meses","Total de Ventas",
         dataset2,
         PlotOrientation.VERTICAL,
         true,true,false
        );
        DefaultPieDataset datasetp = new DefaultPieDataset( );
      JFreeChart piee = ChartFactory.createPieChart(
         "Mobile Sales",   // chart title 
         datasetp,          // data    
         true,             // include legend   
         true, 
         false);
        
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(Color.WHITE);
        chart.getRenderingHints().put(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, Boolean.TRUE);
        lineChart.setBackgroundPaint(Color.WHITE);
        lineChart.getPlot().setBackgroundPaint(Color.WHITE);
        lineChart.getRenderingHints().put(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, Boolean.TRUE);
        
        File pdf = new File("Hola2.pdf");
        try {
            int chart_h = 768;
            int chart_w = 1080;
            PdfWriter writer = new PdfWriter(pdf);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(chart.createBufferedImage(500, 400), "png", baos);
            byte[] bytes = baos.toByteArray();
            ImageData data = ImageDataFactory.create(bytes);
            Image img = new Image(data);
            document.add(img);
            
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            ImageIO.write(lineChart.createBufferedImage(500, 400), "png", baos2);
            byte[] bytes2 = baos2.toByteArray();
            ImageData data2 = ImageDataFactory.create(bytes2);
            Image img2 = new Image(data2);
            document.add(img2);
            
            ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
            ImageIO.write(piee.createBufferedImage(500, 400), "png", baos3);
            byte[] bytes3 = baos3.toByteArray();
            ImageData data3 = ImageDataFactory.create(bytes3);
            Image img3 = new Image(data3);
            document.add(img3);
            

            //Table table = new Table(4);
            //table.setWidth(500);

            
            

            //document.add(table);


            document.close();
            
           
            
        } catch (Exception e) {
        }
        return Response.ok(pdf, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + pdf.getName() + "\"" )
                .build();
        
    }
    
    
        
    @PUT
    @Path("/Pedidos/Aprobar/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void AprobarPedidos(String content) {
        System.out.println(content);
        DAO.DAO_Pedidos.AprobarPedidos(content);
    }
    
    @PUT
    @Path("/Pedidos/Rechazar/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void RechazarPedidos(String content) {
        System.out.println(content);
        DAO.DAO_Pedidos.RechazarPedidos(content);
    }
    
    @POST
    @Path("/S_Produccion/Crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public void CrearSubasta(String content) {
        DAO.Subastas.CrearSubastaProduccion(gson.fromJson(content, int.class));
    }
    @POST
    @Path("S_Transporte/Crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public void CrearSubastaT(String content) {
        DAO.Subastas.CrearSubastaTransporte(gson.fromJson(content, int.class));
    }
    
    @POST
    @Path("/S_Venta/Crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public void CrearSubastaVenta(String content) {
        DAO.Subastas.CrearSubastaVenta(gson.fromJson(content, Subasta_Venta.class));
    }
    /**
     * PUT method for updating or creating an instance of SubastasResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
