/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.List;
import Clases.Valores_Estaticos.Frutas;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Seba
 */
@Entity
@Table(name="produccion")
public class Produccion {
    @Id
    @Column(name="id_produccion")
    private int id_produccion;
    private String fecha_publicacion;
    private String fecha_cosecha;
    private String calidad;
    private int cantidad_inicial;
    private int id_cliente;
    private int id_producto;    
    
    //@Formula(value="(SELECT IFNULL(sum(r.cantidad_seleccionada),0) as cantidad_vendida FROM registro_produccion r WHERE r.id_produccion = id_produccion and r.estado in (\"Aprobado\"))")
    
    @OneToMany(fetch=FetchType.EAGER,mappedBy="id_produccion",cascade=CascadeType.ALL)
    private List<Registro_Produccion> lr;// = new ArrayList<Registro_Produccion>();
    
    public String getProducto(){
       return Clases.Valores_Estaticos.Frutas.getById(this.id_producto).name();
   }
   public String getRutaProducto(){
       return Clases.Valores_Estaticos.Frutas.getByIdRuta(this.id_producto);
   }
      /*
   public int getCantidadRestante(){
       return cantidad_inicial - cantidad_vendida;
    }

    public Integer ultimo_valor(){
       int valor_acumulado = 0;
       for (Registro_Produccion r : lr){
           if (r.getEstado().equals("Aprobado")){
                valor_acumulado += r.getCantidad_seleccionada();
           }
       }
       return cantidad_inicial - valor_acumulado;
    }*/

    public Produccion(int id_produccion, String fecha_publicacion, String fecha_cosecha, String calidad, int cantidad_inicial, int id_cliente, int id_producto) {
        this.id_produccion = id_produccion;
        this.fecha_publicacion = fecha_publicacion;
        this.fecha_cosecha = fecha_cosecha;
        this.calidad = calidad;
        this.cantidad_inicial = cantidad_inicial;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
    }

    public Produccion(int id_produccion, String fecha_publicacion, String fecha_cosecha, String calidad, int cantidad_inicial, int id_cliente, int id_producto, List<Registro_Produccion> lr) {
        this.id_produccion = id_produccion;
        this.fecha_publicacion = fecha_publicacion;
        this.fecha_cosecha = fecha_cosecha;
        this.calidad = calidad;
        this.cantidad_inicial = cantidad_inicial;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.lr = lr;
    }

   
    
    public Produccion() {
    }

    public int getId_produccion() {
        return id_produccion;
    }

    public void setId_produccion(int id_produccion) {
        this.id_produccion = id_produccion;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getFecha_cosecha() {
        return fecha_cosecha;
    }

    public void setFecha_cosecha(String fecha_cosecha) {
        this.fecha_cosecha = fecha_cosecha;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getCantidad_inicial() {
        return cantidad_inicial;
    }

    public void setCantidad_inicial(int cantidad_inicial) {
        this.cantidad_inicial = cantidad_inicial;
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

    
    public Integer getCantidad_vendida() {
        return this.getLr().stream()
                .filter(s -> s.getEstado().equals("Aprobado") || s.getEstado().equals("Pagado") )
                .map(s -> s.getCantidad_seleccionada())
                .reduce(0,Integer::sum);
    }

    public Integer getCantidadRestante(){
        return this.cantidad_inicial - this.getCantidad_vendida();
    }

    public List<Registro_Produccion> getLr() {
        return lr;
    }

    public void setLr(List<Registro_Produccion> lr) {
        this.lr = lr;
    }

    @Override
    public String toString() {
        return "Produccion{" + "id_produccion=" + id_produccion + ", fecha_publicacion=" + fecha_publicacion + ", fecha_cosecha=" + fecha_cosecha + ", calidad=" + calidad + ", cantidad_inicial=" + cantidad_inicial + ", id_cliente=" + id_cliente + ", id_producto=" + id_producto + ", lr=" + lr + '}';
    }

    



 
   

    

   

    
    

    

    

    
    
    
}
