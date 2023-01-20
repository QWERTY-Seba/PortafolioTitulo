/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

/**
 *
 * @author Seba
 */
@Entity
@Table(name="pedido")
public class Pedido {
    /*
    Existen tres tipos de pedido
    Externo, donde se exige calidad cantidad etc
    Interno, donde se exige cantidad personalizada
    Local, donde solo se especifica la cantidad x unidad
    */
    @Id
    @Column
    private int id_pedido;
    
    @OneToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    
    private Integer cantidad;
    
    @OneToMany(fetch=FetchType.EAGER,mappedBy="id_tabla",cascade = CascadeType.ALL)
    @Where(clause = "entidad = \"Pedido\"")
    private List<Registro_Estados> registro_estados;
    
    @OneToOne
    @JoinColumn(name="id_pedido")
    private Registro_Produccion registro_produccion;

    @OneToOne
    @JoinColumn(name="id_pedido")
    private Pago pago;
    
    private boolean pagado;
    
    @ManyToOne
    @JoinColumn(name="id_subasta")
    private Subasta_Venta subasta;
    
    public Registro_Estados buscarEstado(String estado){
        return this.registro_estados.stream().filter(s -> s.getEstado().equals(estado)).findFirst().orElse(null);
    }
    
    public Registro_Estados UltimoEstado(){
        return this.registro_estados.get(this.registro_estados.size()-1);
    }
    
    public Registro_Estados buscarCreado(){
        return this.buscarEstado("Creada");
    }
    
    public Pedido() {
    }

    public Pedido(int id_pedido, Cliente cliente, Integer cantidad, List<Registro_Estados> registro_estados, Registro_Produccion registro_produccion, Pago pago, boolean pagado, Subasta_Venta subasta) {
        this.id_pedido = id_pedido;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.registro_estados = registro_estados;
        this.registro_produccion = registro_produccion;
        this.pago = pago;
        this.pagado = pagado;
        this.subasta = subasta;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public Subasta_Venta getSubasta() {
        return subasta;
    }

    public void setSubasta(Subasta_Venta subasta) {
        this.subasta = subasta;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", cliente=" + cliente + ", cantidad=" + cantidad + ", registro_estados=" + registro_estados + ", registro_produccion=" + registro_produccion + ", pago=" + pago + ", pagado=" + pagado + ", subasta=" + subasta + '}';
    }



   
    
}
