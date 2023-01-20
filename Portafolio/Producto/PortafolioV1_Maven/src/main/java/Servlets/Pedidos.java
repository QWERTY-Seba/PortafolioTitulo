/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Pedido;
import DAO.DAO_Pedidos;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Seba
 */
public class Pedidos extends HttpServlet {

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
        

        int id = (int) request.getSession().getAttribute("id_cliente");
        request.setAttribute("lista_Pedidos", DAO_Pedidos.ListarPedido(id));
        request.getRequestDispatcher("/app/Pedidos.jsp").forward(request, response);
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
        JsonObject object = new Gson().fromJson(response_json, JsonObject.class);
        
        Integer s , u , c;
        s = object.get("id_subasta").getAsInt();
        u = (int)request.getSession(false).getAttribute("id_cliente");
        c = object.get("cantidad").getAsInt();
        
        Integer id = DAO.DAO_Pedidos.RegistrarPedido(s,u,c);
        
        if(DAO.Subastas.ListarVentasInternas(true).stream().filter(t -> t.getId_subasta() == s ).findFirst().orElse(null).getTipo_subasta().equals("Venta_Local")){
            DAO.DAO_Pedidos.AprobarPedidos(id.toString());
        }
        
               
        
        response.setContentType("application/json");
        response.getWriter().printf("{\"id_pedido\":\"%d\"}",id);
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
