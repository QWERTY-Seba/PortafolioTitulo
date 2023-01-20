/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Valores_Estaticos;

/**
 *
 * @author Seba
 */
public enum Accesos {
    Cliente_Externo(new String[]{"Login","Pagos","Cerrar_Session","Solicitudes","Home.jsp"}),
    Cliente_Interno(new String[]{"Login","Pagos","Cerrar_Session","Pedidos","Subasta_Interna","Home.jsp"}),
    Transportista(new String[]{"Login","Cerrar_Session","Transportes","Subasta_Transporte","Home.jsp"}),
    Productor(new String[]{"Login","Cerrar_Session","Subastas_Externas","Home.jsp"});
    
    private String[] servlets;

    private Accesos(String[] servlets) {
        this.servlets = servlets;
    }

    public String[] getServlets() {
        return servlets;
    }
    
    
    
}
