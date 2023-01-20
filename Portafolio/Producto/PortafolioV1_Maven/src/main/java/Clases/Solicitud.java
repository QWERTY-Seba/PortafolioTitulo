/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Clases.Valores_Estaticos.Frutas;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

/**
 *
 * @author Seba
 */
@Entity
@Table(name="solicitud")
public class Solicitud {
    @Id
    @Column
    private int id_solicitud;
    
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    
    private int id_producto;
    
    private int cantidad;
    private String calidad;

    @OneToMany(mappedBy="id_tabla",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Where(clause = "entidad = \"Solicitud\"")
    private List<Registro_Estados> registro_estados;
    
    @OneToOne
    @JoinColumn(name="id_solicitud")
    private Especificacion_Costos especificacion_costos;
    
    private boolean pagado;
    
    private boolean indemnizado;
    
    public Solicitud() {
    }
    
    public Frutas getFruta() {
        return Frutas.getById(id_producto);
    }
    public String getFrutaRuta(){
        return Frutas.getByIdRuta(id_producto);
    }

    public Solicitud(int id_solicitud, Cliente cliente, int id_producto, int cantidad, String calidad, List<Registro_Estados> registro_estados, Especificacion_Costos especificacion_costos, boolean pagado, boolean indemnizado) {
        this.id_solicitud = id_solicitud;
        this.cliente = cliente;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.calidad = calidad;
        this.registro_estados = registro_estados;
        this.especificacion_costos = especificacion_costos;
        this.pagado = pagado;
        this.indemnizado = indemnizado;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public List<Registro_Estados> getRegistro_estados() {
        return registro_estados;
    }

    public void setRegistro_estados(List<Registro_Estados> registro_estados) {
        this.registro_estados = registro_estados;
    }

    public Especificacion_Costos getEspecificacion_costos() {
        return especificacion_costos;
    }

    public void setEspecificacion_costos(Especificacion_Costos especificacion_costos) {
        this.especificacion_costos = especificacion_costos;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public boolean isIndemnizado() {
        return indemnizado;
    }

    public void setIndemnizado(boolean indemnizado) {
        this.indemnizado = indemnizado;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "id_solicitud=" + id_solicitud + ", cliente=" + cliente + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", calidad=" + calidad + ", registro_estados=" + registro_estados + ", especificacion_costos=" + especificacion_costos + ", pagado=" + pagado + ", indemnizado=" + indemnizado + '}';
    }

    public Registro_Estados buscarEstado(String estado){
        return this.registro_estados.stream().filter(s -> s.getEstado().equals(estado)).findFirst().orElse(null);
    }
    
    public Registro_Estados UltimoEstado(){
        return this.registro_estados.get(this.registro_estados.size()-1);
    }
    
    public Registro_Estados buscarCreado(){
        return this.buscarEstado("Creada");
    }

}
