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

public class Oferta_Transporte {

    private int id_oferta_transporte;
    private Integer monto_transporte_NA;
    private Integer monto_transporte_EX;
    private String currency;
    private boolean compania_externa;
    private String nombre_compania;
    private String rut_compania;
    private String fecha_publicacion;
    private String estado;
    private int id_subasta;
    
    private Transporte transporte;

    public Oferta_Transporte() {
        transporte = new Transporte();
    }

    public Oferta_Transporte(int id_oferta_transporte, Integer monto_transporte_NA, Integer monto_transporte_EX, String currency, boolean compania_externa, String nombre_compania, String rut_compania, String fecha_publicacion, String estado, int id_subasta, Transporte transporte) {
        this.id_oferta_transporte = id_oferta_transporte;
        this.monto_transporte_NA = monto_transporte_NA;
        this.monto_transporte_EX = monto_transporte_EX;
        this.currency = currency;
        this.compania_externa = compania_externa;
        this.nombre_compania = nombre_compania;
        this.rut_compania = rut_compania;
        this.fecha_publicacion = fecha_publicacion;
        this.estado = estado;
        this.id_subasta = id_subasta;
        this.transporte = transporte;
    }

    public int getId_oferta_transporte() {
        return id_oferta_transporte;
    }

    public void setId_oferta_transporte(int id_oferta_transporte) {
        this.id_oferta_transporte = id_oferta_transporte;
    }

    public Integer getMonto_transporte_NA() {
        return monto_transporte_NA;
    }

    public void setMonto_transporte_NA(Integer monto_transporte_NA) {
        this.monto_transporte_NA = monto_transporte_NA;
    }

    public Integer getMonto_transporte_EX() {
        return monto_transporte_EX;
    }

    public void setMonto_transporte_EX(Integer monto_transporte_EX) {
        this.monto_transporte_EX = monto_transporte_EX;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isCompania_externa() {
        return compania_externa;
    }

    public void setCompania_externa(boolean compania_externa) {
        this.compania_externa = compania_externa;
    }

    public String getNombre_compania() {
        return nombre_compania;
    }

    public void setNombre_compania(String nombre_compania) {
        this.nombre_compania = nombre_compania;
    }

    public String getRut_compania() {
        return rut_compania;
    }

    public void setRut_compania(String rut_compania) {
        this.rut_compania = rut_compania;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_subasta() {
        return id_subasta;
    }

    public void setId_subasta(int id_subasta) {
        this.id_subasta = id_subasta;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    @Override
    public String toString() {
        return "Oferta_Transporte{" + "id_oferta_transporte=" + id_oferta_transporte + ", monto_transporte_NA=" + monto_transporte_NA + ", monto_transporte_EX=" + monto_transporte_EX + ", currency=" + currency + ", compania_externa=" + compania_externa + ", nombre_compania=" + nombre_compania + ", rut_compania=" + rut_compania + ", fecha_publicacion=" + fecha_publicacion + ", estado=" + estado + ", id_subasta=" + id_subasta + ", transporte=" + transporte + '}';
    }

    

    
    
    
    
    
}
