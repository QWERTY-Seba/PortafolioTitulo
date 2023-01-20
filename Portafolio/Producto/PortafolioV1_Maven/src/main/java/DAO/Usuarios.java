    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Clases.Cliente;
import Clases.Contrato;

import Clases.LoginEN;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;


/**
 *
 * @author Seba
 */
public class Usuarios {

    public static void CrearContrato(Contrato c) {
        try (Session ss = Conexion.getSession()) {
            Object nada = ss.createSQLQuery("CALL CrearContrato(:c_meses,:p_tipo_contrato,:p_id_cliente,:p_detalle,:id_producto,:p_cantidad,:p_calidad)")
                    .setParameter("c_meses", Integer.parseInt(c.getFecha_termino()))
                    .setParameter("p_tipo_contrato", c.getTipo_contrato())
                    .setParameter("p_id_cliente", c.getId_cliente())
                    .setParameter("p_detalle", c.getDetalle())
                    .setParameter("id_producto", 0).setParameter("p_cantidad", 0).setParameter("p_calidad", "").getSingleResult();
        } catch (Exception e) {
        }
    }
    public static void CrearContratoFruta(Contrato c) {
        try (Session ss = Conexion.getSession()) {
            Object nada = ss.createSQLQuery("CALL CrearContrato(:c_meses,:p_tipo_contrato,:p_id_cliente,:p_detalle,:id_producto,:p_cantidad,:p_calidad)")
                    .setParameter("c_meses", Integer.parseInt(c.getFecha_termino()))
                    .setParameter("p_tipo_contrato", c.getTipo_contrato())
                    .setParameter("p_id_cliente", c.getId_cliente())
                    .setParameter("p_detalle", c.getDetalle())
                    .setParameter("id_producto", c.getId_producto())
                    .setParameter("p_cantidad", c.getCantidad())
                    .setParameter("p_calidad", c.getCalidad()).getSingleResult();

        } catch (Exception e) {

        }
    }
    
    public static Boolean ContratoExpirado(String correo){
        try (Session ss = Conexion.getSession()) {
            Integer id =  (Integer) ss.createSQLQuery("SELECT `ContratoExpirado`(:correo) AS `ContratoExpirado`")
                    .setParameter("correo", correo).getSingleResult();
            if(id != 0){
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static List<Cliente> ClienteById(Integer id_cliente){
        
        List<Cliente> c = new ArrayList<Cliente>();
        try(Session ss = Conexion.getSession()) {
            c =  ss.createSQLQuery("CALL ClienteById(:id)")
                    .addEntity(Clases.Cliente.class)
                    .setParameter("id", id_cliente).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public static void ExtenderContrato(Integer id_c, Integer cantidad_meses){
        try (Session ss = Conexion.getSession()) {
            Object nada = ss.createSQLQuery("CALL ExtenderContrato(:c_meses,:id_c)")
                    .setParameter("c_meses", cantidad_meses)
                    .setParameter("id_c", id_c).getSingleResult();
        } catch (Exception e) {
            
        }
    
    }
    public static final void CancelarContrato(Integer id_c){
        try(Session ss = Conexion.getSession()) {
            Object nada  = ss.createSQLQuery("CALL CancelarContrato(:id_c)")
                        .setParameter("id_c", id_c).uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    public static final List<Object[]> Ingreso(String correo){
        List<Object[]> a = new ArrayList<>();
        try(Session ss = Conexion.getSession()){
            a =  ss.createSQLQuery("CALL Ingreso(:correo)").setParameter("correo",correo).getResultList();
        }catch(Exception e){
        }
        return a;
    }
    
    public static final List<Cliente> ListarClientes(){
        Session ss = Conexion.getSession();
        List<Cliente> lc = new ArrayList<Cliente>();
        
        try{
            Query q  = ss.createSQLQuery("CALL ListarClientes()")
                    .addEntity(Clases.Cliente.class);
            lc = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lc;
    }
    
    public static final Cliente ClienteByCorreo(String correo){
        Session ss = Conexion.getSession();
        Cliente c = new Cliente();
        try{
            c = (Cliente) ss.createSQLQuery("CALL ClienteByCorreo(:correo)")
                    .addEntity(Clases.Cliente.class)
                    .setParameter("correo", correo).uniqueResult();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return c;
    }
    
    
    public static List<Contrato> ListarContratoCliente(Integer id_cliente){
        Session ss = Conexion.getSession();
        List<Contrato> lc = new ArrayList<Contrato>();
        
        try{
            Query q = ss.createSQLQuery("CALL ContratoCliente(:id)")
                    .addEntity(Clases.Contrato.class)
                    .setParameter("id", id_cliente);
           lc = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lc;
    
    }
    
    /*
    public static List<Especificacion_Contrato> ListarEspecificacionContrato(Integer id_contrato){
        Session ss = Conexion.getSession();
        List<Especificacion_Contrato> lc = new ArrayList<Especificacion_Contrato>();
        try{
            Query q = ss.createSQLQuery("CALL ListarEspecificacionContrato(:id)")
                    .addEntity(Clases.Especificacion_Contrato.class)
                    .setParameter("id", id_contrato);
           lc = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            ss.close();
        }
        return lc;
        
        
    }*/
    
    public static void CrearCliente(Cliente c){
            Session ss = Conexion.getSession();
            try{
                String pw = generateRandomPassword();
                String salt = LoginEN.getSalt(30);
                String pw_enc = LoginEN.generateSecurePassword(pw, salt);
                Integer id  = (Integer) ss.createSQLQuery("CALL CrearCliente(:c_rut,:c_nombres,:c_apellidos,:c_correo,:c_telefono,:c_organizacion,:c_rol,:c_direccion,:c_contrasena,:c_salt,:c_pais,:c_region)")
                        .setParameter("c_rut", c.getRut())
                        .setParameter("c_nombres", c.getNombres())
                        .setParameter("c_apellidos", c.getApellidos())
                        .setParameter("c_correo", c.getCorreo())
                        .setParameter("c_telefono", c.getTelefono())
                        .setParameter("c_organizacion", c.getOrganizacion())
                        .setParameter("c_rol", c.getC_role())
                        .setParameter("c_direccion", c.getDireccion())
                        .setParameter("c_contrasena",pw_enc)
                        .setParameter("c_salt", salt)
                        .setParameter("c_pais", c.getPais())
                        .setParameter("c_region", c.getRegion()).getSingleResult();
               new Thread( () -> {
                    Clases.TestCorreo.EnviarCorreoPassword(c.getCorreo(), pw);
                }).start(); 
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                ss.close();
            }
        }
    
        public static void ActualizarCliente(Cliente c) {
        Session ss = Conexion.getSession();
        try {
            Object nada = ss.createSQLQuery("CALL ActualizarCliente(:id_cliente,:c_rut,:c_nombres,:c_apellidos,:c_correo,:c_telefono,:c_organizacion,:c_direccion,:c_pais,:c_region)")
                    .setParameter("id_cliente", c.getId_cliente())
                    .setParameter("c_rut", c.getRut())
                    .setParameter("c_nombres", c.getNombres())
                    .setParameter("c_apellidos", c.getApellidos())
                    .setParameter("c_correo", c.getCorreo())
                    .setParameter("c_telefono", c.getTelefono())
                    .setParameter("c_organizacion", c.getOrganizacion())
                    .setParameter("c_direccion", c.getDireccion())
                    .setParameter("c_pais", c.getPais())
                    .setParameter("c_region", c.getRegion())
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }
    
        public static String generateRandomPassword() {
            final int len = 20;
            final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            SecureRandom random = new SecureRandom();
            StringBuilder sb = new StringBuilder();

            
            for (int i = 0; i < len; i++) {
                int randomIndex = random.nextInt(chars.length());
                sb.append(chars.charAt(randomIndex));
            }

            return sb.toString();
        }
    
}
