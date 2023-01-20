/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Seba
 */
@Entity
@Table(name="subasta")
public class Subasta_Transporte extends Subasta{
    
    @ManyToOne
    @JoinColumn(name="id_solicitud")
    private Solicitud solicitud;
    
    //@OneToMany(mappedBy="id_subasta",cascade = CascadeType.ALL)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @Transient
    private List<Oferta_Transporte> lista_ofertas;

    public Subasta_Transporte() {
    }

    public Subasta_Transporte(Solicitud solicitud, List<Oferta_Transporte> lista_ofertas, int id_subasta, String fecha_inicio, String fecha_termino, String estado, String tipo_subasta) {
        super(id_subasta, fecha_inicio, fecha_termino, estado, tipo_subasta);
        this.solicitud = solicitud;
        this.lista_ofertas = lista_ofertas;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public List<Oferta_Transporte> getLista_ofertas() {
        return lista_ofertas;
    }

    public void setLista_ofertas(List<Oferta_Transporte> lista_ofertas) {
        this.lista_ofertas = lista_ofertas;
    }

    @Override
    public String toString() {
        return super.toString() + "Subasta_Transporte{" + "solicitud=" + solicitud + ", lista_ofertas=" + lista_ofertas + '}';
    }
    
    
    
    
    

}
