/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Pedido;
import Clases.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Seba
 */
public class Pagos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        //RECUPERAR PEDIDO INFO CLIENTE
        
        try{
            //Pedido pedido = new Pedido();
            //Clases.Subasta_Venta subasta = new Clases.Subasta_Venta();
            Integer id = (Integer) request.getSession(false).getAttribute("id_cliente");
            Integer tipo = Integer.valueOf(request.getParameter("tipo"));
            
            if(tipo == 1){
                Pedido pedido = DAO.DAO_Pedidos.PedidoById(Integer.parseInt(request.getParameter("id")));
                
                if (pedido.isPagado()) {
                    response.sendRedirect("Subasta_Interna");
                } else {
                    request.setAttribute("Pedido", pedido);
                    //request.setAttribute("subasta", subasta);
                    request.getRequestDispatcher("Pago.jsp").forward(request, response);
                }
            }else{
                Clases.Solicitud solicitud = DAO.Solicitudes.SolicitudById(Integer.parseInt(request.getParameter("id")));
                
                if (solicitud.isPagado()) {
                    response.sendRedirect("Subasta_Interna");
                } else {
                    request.setAttribute("solicitud", solicitud);
                    //request.setAttribute("subasta", subasta);
                    request.getRequestDispatcher("Pago_S.jsp").forward(request, response);
                }
            }
            
            //subasta = DAO.Subastas.ListarVentasInternas(true).stream().filter(s -> s.getId_subasta() == pedido.getId_subasta()).findFirst().orElse(null);
            
              
        }catch(Exception e){
            response.sendError(javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST);
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        BufferedReader bf =  request.getReader();
        String response_json = bf.readLine();
        Clases.Pago pago = new GsonBuilder().serializeNulls().create().fromJson(response_json, Clases.Pago.class);
        
        Integer id = 0;
        id = DAO.DAO_Pagos.RegistrarPago(pago);

        response.setContentType("application/json");
        response.getWriter().print("Pago ok");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
