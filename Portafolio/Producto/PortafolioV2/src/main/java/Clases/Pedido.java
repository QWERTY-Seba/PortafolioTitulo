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

public class Pedido {
    /*
    Existen tres tipos de pedido
    Externo, donde se exige calidad cantidad etc
    Interno, donde se exige cantidad personalizada
    Local, donde solo se especifica la cantidad x unidad
    */
    private int id_pedido;
    
    private int id_subasta;    

    private Cliente cliente;
    
    private Integer cantidad;
    
    private List<Registro_Estados> registro_estados;
    
    private Registro_Produccion registro_produccion;

    private Pago pago;
    
    private boolean pagado;
    
    public Pedido() {
    }

    public Pedido(int id_pedido, int id_subasta, Cliente cliente, Integer cantidad, List<Registro_Estados> registro_estados, Registro_Produccion registro_produccion, Pago pago, boolean pagado) {
        this.id_pedido = id_pedido;
        this.id_subasta = id_subasta;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.registro_estados = registro_estados;
        this.registro_produccion = registro_produccion;
        this.pago = pago;
        this.pagado = pagado;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_subasta() {
        return id_subasta;
    }

    public void setId_subasta(int id_subasta) {
        this.id_subasta = id_subasta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<Registro_Estados> getRegistro_estados() {
        return registro_estados;
    }

    public void setRegistro_estados(List<Registro_Estados> registro_estados) {
        this.registro_estados = registro_estados;
    }

    public Registro_Produccion getRegistro_produccion() {
        return registro_produccion;
    }

    public void setRegistro_produccion(Registro_Produccion registro_produccion) {
        this.registro_produccion = registro_produccion;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", id_subasta=" + id_subasta + ", cliente=" + cliente + ", cantidad=" + cantidad + ", registro_estados=" + registro_estados + ", registro_produccion=" + registro_produccion + ", pago=" + pago + ", pagado=" + pagado + '}';
    }

    
    
    
}
