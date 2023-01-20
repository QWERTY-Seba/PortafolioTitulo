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
public class Subasta {
    private int id_subasta;
   private String fecha_inicio;
   private String fecha_termino;
   private String estado;
   private String tipo_subasta;
   
   private Solicitud s;
   private List<Produccion> lp;
   private List<Oferta_Transporte> lista_ofertas;
   private List<Pedido> lpe;
   private Produccion produccion;
   private Integer precio_por_kg;

    public Subasta() {
    }

    public Subasta(int id_subasta, String fecha_inicio, String fecha_termino, String estado, String tipo_subasta, Solicitud s, List<Produccion> lp, List<Oferta_Transporte> lista_ofertas, List<Pedido> lpe, Produccion produccion, Integer precio_por_kg) {
        this.id_subasta = id_subasta;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.estado = estado;
        this.tipo_subasta = tipo_subasta;
        this.s = s;
        this.lp = lp;
        this.lista_ofertas = lista_ofertas;
        this.lpe = lpe;
        this.produccion = produccion;
        this.precio_por_kg = precio_por_kg;
    }

    public int getId_subasta() {
        return id_subasta;
    }

    public void setId_subasta(int id_subasta) {
        this.id_subasta = id_subasta;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_subasta() {
        return tipo_subasta;
    }

    public void setTipo_subasta(String tipo_subasta) {
        this.tipo_subasta = tipo_subasta;
    }

    public Solicitud getS() {
        return s;
    }

    public void setS(Solicitud s) {
        this.s = s;
    }

    public List<Produccion> getLp() {
        return lp;
    }

    public void setLp(List<Produccion> lp) {
        this.lp = lp;
    }

    public List<Oferta_Transporte> getLista_ofertas() {
        return lista_ofertas;
    }

    public void setLista_ofertas(List<Oferta_Transporte> lista_ofertas) {
        this.lista_ofertas = lista_ofertas;
    }

    public List<Pedido> getLpe() {
        return lpe;
    }

    public void setLpe(List<Pedido> lpe) {
        this.lpe = lpe;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public Integer getPrecio_por_kg() {
        return precio_por_kg;
    }

    public void setPrecio_por_kg(Integer precio_por_kg) {
        this.precio_por_kg = precio_por_kg;
    }

    @Override
    public String toString() {
        return "Subasta{" + "id_subasta=" + id_subasta + ", fecha_inicio=" + fecha_inicio + ", fecha_termino=" + fecha_termino + ", estado=" + estado + ", tipo_subasta=" + tipo_subasta + ", s=" + s + ", lp=" + lp + ", lista_ofertas=" + lista_ofertas + ", lpe=" + lpe + ", produccion=" + produccion + ", precio_por_kg=" + precio_por_kg + '}';
    }
   
   
   
    
    
  
}
