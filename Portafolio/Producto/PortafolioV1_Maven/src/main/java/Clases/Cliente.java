/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Seba
 */
@Entity
@Table(name="persona")
public class Cliente {
    @Id
    @Column
    private Integer id_cliente;
    private String rut;
    private String nombres;
    private String apellidos;
    
    @ColumnDefault("Vacio")
    private String correo ;
    
    @ColumnDefault("Vacio")
    private String telefono;
    
    @ColumnDefault("Vacio")
    private String organizacion;
    
    private String c_role;
    private String direccion;
    private String pais;
    private String region;
    
    @OneToMany(cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name="id_cliente")
    private List<Contrato> contratos;
   
   
    
    public Cliente() {
    }

    public Cliente(Integer id_cliente, String rut, String nombres, String apellidos, String correo, String telefono, String organizacion, String c_role, String direccion, String pais, String region, List<Contrato> contratos) {
        this.id_cliente = id_cliente;
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.organizacion = organizacion;
        this.c_role = c_role;
        this.direccion = direccion;
        this.pais = pais;
        this.region = region;
        this.contratos = contratos;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getC_role() {
        return c_role;
    }

    public void setC_role(String c_role) {
        this.c_role = c_role;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", rut=" + rut + ", nombres=" + nombres + ", apellidos=" + apellidos + ", correo=" + correo + ", telefono=" + telefono + ", organizacion=" + organizacion + ", c_role=" + c_role + ", direccion=" + direccion + ", pais=" + pais + ", region=" + region + ", contratos=" + contratos + '}';
    }

    
}
