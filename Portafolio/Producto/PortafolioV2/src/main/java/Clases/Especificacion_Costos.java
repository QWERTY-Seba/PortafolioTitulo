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

public class Especificacion_Costos {
    private int id_solicitud;
    private float c_transporte;
    private float c_aduana;
    private float c_servicio;
    private float c_comision;

    public Especificacion_Costos() {
    }

    public Especificacion_Costos(int id_solicitud, float c_transporte, float c_aduana, float c_servicio, float c_comision) {
        this.id_solicitud = id_solicitud;
        this.c_transporte = c_transporte;
        this.c_aduana = c_aduana;
        this.c_servicio = c_servicio;
        this.c_comision = c_comision;
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public float getC_transporte() {
        return c_transporte;
    }

    public void setC_transporte(float c_transporte) {
        this.c_transporte = c_transporte;
    }

    public float getC_aduana() {
        return c_aduana;
    }

    public void setC_aduana(float c_aduana) {
        this.c_aduana = c_aduana;
    }

    public float getC_servicio() {
        return c_servicio;
    }

    public void setC_servicio(float c_servicio) {
        this.c_servicio = c_servicio;
    }

    public float getC_comision() {
        return c_comision;
    }

    public void setC_comision(float c_comision) {
        this.c_comision = c_comision;
    }

    @Override
    public String toString() {
        return "Especificacion_Costos{" + "id_solicitud=" + id_solicitud + ", c_transporte=" + c_transporte + ", c_aduana=" + c_aduana + ", c_servicio=" + c_servicio + ", c_comision=" + c_comision + '}';
    }
    
    
}
