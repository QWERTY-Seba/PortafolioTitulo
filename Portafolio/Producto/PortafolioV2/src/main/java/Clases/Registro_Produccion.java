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

//Esta clase registra los cambios por los que pasa la produccion
//Ejemplo, de X produccion solo se tomo el 50% para la venta externa
//         Luego se tomo otro 20% para venta interna

public class Registro_Produccion {

    private int id_registro;
    private int id_produccion;
    private int id_subasta;
    private Integer id_pedido;
    //private int id_factura;
    private int cantidad_seleccionada;
    private int precio_por_kg;
    //Pendiente Vendido Cancelado
    private String estado;
    private String fecha_creacion;

    public Registro_Produccion() {
    }

    @Override
    public String toString() {
        return "Registro_Produccion{" + "id_registro=" + id_registro + ", id_produccion=" + id_produccion + ", id_subasta=" + id_subasta + ", id_pedido=" + id_pedido + ", cantidad_seleccionada=" + cantidad_seleccionada + ", precio_por_kg=" + precio_por_kg + ", estado=" + estado + ", fecha_creacion=" + fecha_creacion + '}';
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public int getId_produccion() {
        return id_produccion;
    }

    public void setId_produccion(int id_produccion) {
        this.id_produccion = id_produccion;
    }

    public int getId_subasta() {
        return id_subasta;
    }

    public void setId_subasta(int id_subasta) {
        this.id_subasta = id_subasta;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCantidad_seleccionada() {
        return cantidad_seleccionada;
    }

    public void setCantidad_seleccionada(int cantidad_seleccionada) {
        this.cantidad_seleccionada = cantidad_seleccionada;
    }

    public int getPrecio_por_kg() {
        return precio_por_kg;
    }

    public void setPrecio_por_kg(int precio_por_kg) {
        this.precio_por_kg = precio_por_kg;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Registro_Produccion(int id_registro, int id_produccion, int id_subasta, int id_pedido, int cantidad_seleccionada, int precio_por_kg, String estado, String fecha_creacion) {
        this.id_registro = id_registro;
        this.id_produccion = id_produccion;
        this.id_subasta = id_subasta;
        this.id_pedido = id_pedido;
        this.cantidad_seleccionada = cantidad_seleccionada;
        this.precio_por_kg = precio_por_kg;
        this.estado = estado;
        this.fecha_creacion = fecha_creacion;
    }

   
    
    
}
