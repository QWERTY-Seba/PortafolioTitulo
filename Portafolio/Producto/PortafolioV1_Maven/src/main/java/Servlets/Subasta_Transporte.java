/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Oferta_Transporte;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
public class Subasta_Transporte extends HttpServlet {

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
        
        Integer id = (Integer) request.getSession(false).getAttribute("id_cliente");
        List<Clases.Transporte> listat = DAO.DAO_Transporte.ListarTransportes(id);
        List<Clases.Subasta_Transporte> lista = DAO.Subastas.ListarSubastaTransporte(false);
        request.setAttribute("lista_subastas", lista);
        request.setAttribute("lista_transportes", listat);
        request.getRequestDispatcher("Subastas_Transporte.jsp").forward(request, response);
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
        
        response.setContentType("application/json");
        try{
        
        BufferedReader bf =  request.getReader();
        String response_json = bf.readLine();
        Gson gson = new GsonBuilder().serializeNulls().create();        
        JsonObject oferta = gson.fromJson(response_json, JsonObject.class);
        Oferta_Transporte oferta_t = new Oferta_Transporte();
        
        if(oferta.get("compania_externa") != null){
            oferta_t.setCompania_externa(true);
            oferta_t.setNombre_compania(oferta.get("nombre_compania").getAsString());
            oferta_t.setRut_compania(oferta.get("rut_compania").getAsString());
        }else{
            oferta_t.setCompania_externa(false);
        }              
        if(oferta.get("monto_transporte_EX") != null){
            oferta_t.setMonto_transporte_EX(oferta.get("monto_transporte_EX").getAsInt());
        }
        
        oferta_t.setMonto_transporte_NA(oferta.get("monto_transporte_NA").getAsInt());
        oferta_t.setId_subasta(oferta.get("id_subasta").getAsInt());        
        oferta_t.getTransporte().setId_transporte(oferta.get("id_transporte").getAsInt());
        DAO.Ofertas.CrearOfertaTrasporteCeNull(oferta_t);
        response.getWriter().print("{\"mensaje\":\"vacio\",\"exito\":\"true\"}");

        }catch(Exception e){
            response.getWriter().print("{\"mensaje\":\"vacio\",\"exito\":\"false\"}");
        
        }
        
        
        
        
        
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
