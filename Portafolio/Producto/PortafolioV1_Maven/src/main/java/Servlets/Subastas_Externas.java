/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Subasta_Produccion;
import DAO.Producciones;
import DAO.Solicitudes;
import DAO.Subastas;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Seba
 */
public class Subastas_Externas extends HttpServlet {

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
        
        List<Subasta_Produccion> sp = Subastas.ListarVentasExternas(false);
        
        
        request.setAttribute("Lista_Subasta", sp);
        request.getRequestDispatcher("/app/Subastas_Externas.jsp").forward(request, response);
        
    
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
        
   
        String d , f ,c ,e;
        int ci , u, p , pr , s;
        d = ""; //Detalle Removido Temporalmente
        f = request.getParameter("fecha_cosecha");
        c = request.getParameter("calidad");
        ci =  Integer.parseInt(request.getParameter("cantidad_inicial"));
        u =  (int) request.getSession(false).getAttribute("id_cliente");
        p = Integer.parseInt(request.getParameter("id_producto"));
        s = Integer.parseInt(request.getParameter("id_subasta"));
        pr = Integer.parseInt(request.getParameter("precio_por_kg"));
        e = Clases.Valores_Estaticos.Estados.Produccion.E_Externa.name();
        BigInteger i = Producciones.CrearProduccion(d, f, c, ci, u, p,e);
        Producciones.crearRegistroProduccion(i, s, ci, pr);
        response.sendRedirect("Ofertas");
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
