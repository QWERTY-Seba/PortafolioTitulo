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

public class Transporte {

    private int id_transporte;
    private String patente;
    private String capacidad;
    private int ancho;
    private int alto;
    private int largo;
    private String marca;
    private String seguro;
    private String modelo;
    

    private Cliente cliente;

    public Transporte() {
    }

    public Transporte(int id_transporte, String patente, String capacidad, int ancho, int alto, int largo, String marca, String seguro, String modelo, Cliente cliente) {
        this.id_transporte = id_transporte;
        this.patente = patente;
        this.capacidad = capacidad;
        this.ancho = ancho;
        this.alto = alto;
        this.largo = largo;
        this.marca = marca;
        this.seguro = seguro;
        this.modelo = modelo;
        this.cliente = cliente;
    }
    
    public Transporte(String patente, String capacidad, int ancho, int alto, int largo, String marca, String seguro, String modelo) {
        this.patente = patente;
        this.capacidad = capacidad;
        this.ancho = ancho;
        this.alto = alto;
        this.largo = largo;
        this.marca = marca;
        this.seguro = seguro;
        this.modelo = modelo;
    }

    public int getId_transporte() {
        return id_transporte;
    }

    public void setId_transporte(int id_transporte) {
        this.id_transporte = id_transporte;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Transporte{" + "id_transporte=" + id_transporte + ", patente=" + patente + ", capacidad=" + capacidad + ", ancho=" + ancho + ", alto=" + alto + ", largo=" + largo + ", marca=" + marca + ", seguro=" + seguro + ", modelo=" + modelo + ", cliente=" + cliente + '}';
    }

    
    
    
    
}
