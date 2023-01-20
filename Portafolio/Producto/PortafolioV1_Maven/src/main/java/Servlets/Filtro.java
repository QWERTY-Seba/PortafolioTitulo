/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/Filtro")
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig config)
        throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("id_cliente") == null || needsAuthentication(request.getRequestURI(), request)) { 
            response.sendRedirect("/PortafolioV1_Maven/Login"); 
        } else {
            chain.doFilter(req, res); 
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

    //basic validation of pages that do not require authentication
    
    private boolean needsAuthentication(String url, HttpServletRequest req) {
        String[] urls = {};
        System.out.println((String)req.getSession(false).getAttribute("role"));
        switch((String)req.getSession(false).getAttribute("role")){
            case "Productor":
                urls  = Clases.Valores_Estaticos.Accesos.Productor.getServlets();
                break;
            case "Transportista":
                urls  = Clases.Valores_Estaticos.Accesos.Transportista.getServlets();
                break;
            case "Cliente_Interno":
                urls  = Clases.Valores_Estaticos.Accesos.Cliente_Interno.getServlets();
                break;
            case "Cliente_Externo":
                urls  = Clases.Valores_Estaticos.Accesos.Cliente_Externo.getServlets();
                break;
        
        }
                
                
        for(String validUrl : urls) {
            System.out.println(url + " " + validUrl);
            if (url.endsWith(validUrl)) {
                System.out.println(url + " " + validUrl);
                return false;
            }
        }
        return true;
    }
}
