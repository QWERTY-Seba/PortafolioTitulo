/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Valores_Estaticos;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Seba
 */

public enum Frutas {
    // Falta especificar el rango de calidades disponibles y las fechas en la que estara
    No_Especifica_Fruta("Manzana.png",0,0, 
            new Estados.Meses[]{}),
    Manzana("Manzana.png",60,180, 
            new Estados.Meses[]{}),
    Pera("Pera.png",34,70, 
            new Estados.Meses[]{}),
    Uvas("Uvas.jpg",22,28,
            new Estados.Meses[]{}),
    Cerezas("Cerezas.jpg", 20,30, 
            new Estados.Meses[]{}),
    Ciruelas("Ciruelas.jpg",36,108,
            new Estados.Meses[]{Estados.Meses.Noviembre,Estados.Meses.Diciembre,}),
    Arandanos("Pera.jpg",1,3, 
            new Estados.Meses[]{}),
    Mandarinas("Pera.jpg",18,40,
            new Estados.Meses[]{}),
    Kiwi("Kiwi.png",20,45,
            new Estados.Meses[]{});
    
   
    public final String label;
    public final int min;
    public final int max;
    public final Estados.Meses[] disponibilidad;


    private Frutas(String label, int min, int max, Estados.Meses[] disponibilidad) {
        this.label = label;
        this.min = min;
        this.max = max;
        this.disponibilidad = disponibilidad;
    }

    public static String Datos(){
            Map<String, List<Integer>> datos = new HashMap<>();

            for (Frutas f : Frutas.class.getEnumConstants()) {
                datos.put(f.name(), Arrays.asList(f.min, f.max));
            }
            return new Gson().toJson(datos);
    }
    public static Frutas getById(int index){
        return Frutas.values()[index];
    }
    public static String getByIdRuta(int index){
        return Frutas.values()[index].label;
    }
    
    public static Object[] getTodo(){
        return Frutas.class.getEnumConstants();
    }

}
