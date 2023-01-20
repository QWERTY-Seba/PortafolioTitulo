/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
/**
 *
 * @author Seba
 */

public enum Frutas {
    // Falta especificar el rango de calidades disponibles y las fechas en la que estara
    No_Especifica_Fruta,
    Manzana,
    Pera,
    Uvas,
    Cerezas,
    Ciruelas,
    Arandanos,
    Mandarinas,
    Nectarines;
   
    public static Frutas getById(int index){
        return Frutas.values()[index];
    }
    
    public static Object[] getTodo(){
        return Frutas.class.getEnumConstants();
    }

}
