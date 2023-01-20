/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.util.List;

/**
 *
 * @author Seba
 */
public class Generador_Informes {
    
    
    public static void ListarEstadisticaInterna(){
        List<Object[]> lista = DAO.Subastas.ListarEstadisticaInterna();
        //Grafico A , Cantidad de compras registradas por tipo de subasta
        
        //Tabla A Cantidad de compras segun calidad
        
        //Grafico B Top barra de los paises desde donde mas se compra
        
        //Grafico C Top de las frutas segun la cantidad de repeticion pais
        
    
    }
}
