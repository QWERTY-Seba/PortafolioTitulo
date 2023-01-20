/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.List;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Seba
 */
@Entity
@Table(name="subasta")
public class Subasta_Venta extends Subasta{
   
   private Integer precio_por_kg;
   
   @OneToOne
   @JoinColumn(name="id_produccion")
   private Produccion produccion;
   @Transient
   private List<Pedido> lpe;
   
   /*          
   public String getProducto(){
       return Clases.Valores_Estaticos.Frutas.getById(this.id_producto).toString();
   }
   public String getRutaProducto(){
       return Clases.Valores_Estaticos.Frutas.getByIdRuta(this.id_producto);
   }*/

    public Subasta_Venta() {
    }

    public Subasta_Venta(Integer precio_por_kg, Produccion produccion, List<Pedido> lpe, int id_subasta, String fecha_inicio, String fecha_termino, String estado, String tipo_subasta) {
        super(id_subasta, fecha_inicio, fecha_termino, estado, tipo_subasta);
        this.precio_por_kg = precio_por_kg;
        this.produccion = produccion;
        this.lpe = lpe;
    }

    public Integer getPrecio_por_kg() {
        return precio_por_kg;
    }

    public void setPrecio_por_kg(Integer precio_por_kg) {
        this.precio_por_kg = precio_por_kg;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public List<Pedido> getLpe() {
        return lpe;
    }

    public void setLpe(List<Pedido> lpe) {
        this.lpe = lpe;
    }

    @Override
    public String toString() {
        return "Subasta_Venta{" + "precio_por_kg=" + precio_por_kg + ", produccion=" + produccion + ", lpe=" + lpe + '}';
    }

    
    

    

   
} 
