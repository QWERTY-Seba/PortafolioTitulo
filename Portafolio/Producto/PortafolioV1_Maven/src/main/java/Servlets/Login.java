/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Cliente;
import Clases.LoginEN;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Seba
 */
public class Login extends HttpServlet {

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
       request.getRequestDispatcher("/Login.html").forward(request, response);
       
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

        String mensaje = "";
        boolean exito = false;
        response.setContentType("application/json");

        BufferedReader bf =  request.getReader();
        String response_json = bf.readLine();
        JsonObject object = new Gson().fromJson(response_json, JsonObject.class);
        
        String correo = object.get("nombre_usuario").getAsString();
        String password_us = object.get("contrasenna_usuario").getAsString();
        
        try{
            List<Object[]> db = DAO.Usuarios.Ingreso(correo);           
            
            if(db.size() > 0 && LoginEN.verifyUserPassword(password_us,(String) db.get(0)[0], (String)db.get(0)[1])){
                if(DAO.Usuarios.ContratoExpirado(correo)){
                    Cliente c = DAO.Usuarios.ClienteByCorreo(correo);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("id_cliente", c.getId_cliente());
                    session.setAttribute("role", c.getC_role());
                    
                    mensaje = "ingreso correcto";
                    exito = true;
                    
                }else{
                    mensaje = "Error Contrato expirado";
                }
            }else{
                mensaje = "Error Usuario y/o Contrasenna no encontrado";
            }
        }catch(Exception e){
            mensaje = "Error Usuario y/o Contrasenna no encontrado";    
            //e.printStackTrace();
            
        }finally{
                response.getWriter().printf("{\"mensaje\":\"%s\",\"exito\":\"%b\"}", mensaje,exito);
        }
        //response.getWriter().printf("{\"mensaje\":\"%s\",\"exito\":\"%b\"}", mensaje,exito);
        
        
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
