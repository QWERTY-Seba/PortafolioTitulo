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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Subasta_Produccion extends Subasta {
    
    @OneToOne
    @JoinColumn(name="id_solicitud")
    private Solicitud s;
    
    
    @ManyToMany(cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name="registro_produccion", 
              joinColumns = @JoinColumn(name = "id_subasta"), 
              inverseJoinColumns = @JoinColumn(name = "id_produccion"))
    private List<Produccion> lp;

    
    public Subasta_Produccion() {
    }

    public Subasta_Produccion(Solicitud s, List<Produccion> lp, int id_subasta, String fecha_inicio, String fecha_termino, String estado, String tipo_subasta) {
        super(id_subasta, fecha_inicio, fecha_termino, estado, tipo_subasta);
        this.s = s;
        this.lp = lp;
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

    @Override
    public String toString() {
        return "Subasta_Produccion{" + "s=" + s + ", lp=" + lp + '}';
    }

    

    
    
}
