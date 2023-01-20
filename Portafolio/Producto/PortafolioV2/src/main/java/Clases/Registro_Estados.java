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

public class Registro_Estados {

    private int id_registro;
    private String entidad;
    private String estado;
    private String fecha;
    private int id_tabla;

    
    public static Registro_Estados buscarEstado(List<Registro_Estados> lista,String estado){
        return lista.stream().filter(s -> s.getEstado().equals(estado)).findFirst().orElse(null);
    }
    
    public static Registro_Estados buscarCreado(List<Registro_Estados> lista){
        return Clases.Registro_Estados.buscarEstado(lista, "Creada");
    }
    
    public Registro_Estados() {
    }

    public Registro_Estados(int id_registro, String entidad, String estado, String fecha, int id_tabla) {
        this.id_registro = id_registro;
        this.entidad = entidad;
        this.estado = estado;
        this.fecha = fecha;
        this.id_tabla = id_tabla;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_tabla() {
        return id_tabla;
    }

    public void setId_tabla(int id_tabla) {
        this.id_tabla = id_tabla;
    }

    @Override
    public String toString() {
        return "Registro_Estados{" + "id_registro=" + id_registro + ", entidad=" + entidad + ", estado=" + estado + ", fecha=" + fecha + ", id_tabla=" + id_tabla + '}';
    }
    
    
}
