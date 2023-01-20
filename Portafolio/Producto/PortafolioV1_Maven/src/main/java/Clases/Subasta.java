 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 * 
 * @author Seba
 */

@MappedSuperclass
public class Subasta {
   @Id
   @Column
   protected int id_subasta;
   protected String fecha_inicio;
   protected String fecha_termino;
   protected String estado;
   protected String tipo_subasta;
   
   
    public void finalizarSubasta(){}
   
    public List<Object> seleccionarMejorOferta(){
       return null;
   }
    
    public Subasta() {
    }

    public Subasta(int id_subasta, String fecha_inicio, String fecha_termino, String estado, String tipo_subasta) {
        this.id_subasta = id_subasta;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.estado = estado;
        this.tipo_subasta = tipo_subasta;
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

    @Override
    public String toString() {
        return "Subasta{" + "id_subasta=" + id_subasta + ", fecha_inicio=" + fecha_inicio + ", fecha_termino=" + fecha_termino + ", estado=" + estado + ", tipo_subasta=" + tipo_subasta + '}';
    }
  

    

   
}
