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

public class Pago {

    private int id_pago;
    private String id_order; 
    private String id_payment;
    private String currency;
    private int value;
    private Integer id_pedido;
    private String correo;
    private String nombre;
    private String pais;
    private String fecha_pago;
    private Integer id_pago_in;
    private Integer id_solicitud;
    public Pago() {
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getId_payment() {
        return id_payment;
    }

    public void setId_payment(String id_payment) {
        this.id_payment = id_payment;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Integer getId_pago_in() {
        return id_pago_in;
    }

    public void setId_pago_in(Integer id_pago_in) {
        this.id_pago_in = id_pago_in;
    }

    public Integer getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Integer id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    @Override
    public String toString() {
        return "Pago{" + "id_pago=" + id_pago + ", id_order=" + id_order + ", id_payment=" + id_payment + ", currency=" + currency + ", value=" + value + ", id_pedido=" + id_pedido + ", correo=" + correo + ", nombre=" + nombre + ", pais=" + pais + ", fecha_pago=" + fecha_pago + ", id_pago_in=" + id_pago_in + ", id_solicitud=" + id_solicitud + '}';
    }


}
