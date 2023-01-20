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

public class Contrato {

    private int id_contrato;
    private String detalle;
    private String fecha_creacion;
    private String fecha_termino;
    private String tipo_contrato;
    private int id_cliente;
    private int id_producto;
    private int cantidad;
    private String calidad;
    private String estado;
    //Aqui deberia haber una imagen

    public Contrato() {
    }

    public Contrato(int id_contrato, String detalle, String fecha_creacion, String fecha_termino, String tipo_contrato, int id_cliente, int id_producto, int cantidad, String calidad, String estado) {
        this.id_contrato = id_contrato;
        this.detalle = detalle;
        this.fecha_creacion = fecha_creacion;
        this.fecha_termino = fecha_termino;
        this.tipo_contrato = tipo_contrato;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.calidad = calidad;
        this.estado = estado;
    }

    public int getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(int id_contrato) {
        this.id_contrato = id_contrato;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Contrato{" + "id_contrato=" + id_contrato + ", detalle=" + detalle + ", fecha_creacion=" + fecha_creacion + ", fecha_termino=" + fecha_termino + ", tipo_contrato=" + tipo_contrato + ", id_cliente=" + id_cliente + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", calidad=" + calidad + ", estado=" + estado + '}';
    }
    
}
