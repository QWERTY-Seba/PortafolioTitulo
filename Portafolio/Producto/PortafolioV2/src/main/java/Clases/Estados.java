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
//Agrupacion de todos los estados de las entidades del proyecto 
public class Estados {
    
    public enum Pedido{    
        Creado, 
        Aprobado, // Aprobado por el administador
        Pagado, 
        Retirado, // ...
        Cancelado, // Cancelado por el cliente
        Rechazado // Rechazado por el administrador}
        }
    
    public enum Produccion {
        Creada, //Creada fuera de una Subasta Externa
        E_Externa, // En espera en Venta Externa
        Externa, // Vendida completamente en venta Externa 
        R_Externa, // Vendida Incompleta/Parcialmente en Venta Externa
        Rechazada // No aprobo para venta Externa

    }
    
    public enum Registro_Produccion {
        // Las Producciones se van dividiendo en pequeñas porciones que se llaman
        // Registros Produccion, estos registros se asignan automaticamente a un pedido una vez el 
        // Administrador aprueba el pedido. Los posibles estados aparecen dependiendo de lo que pase con el pedido
        Pendiente, // Por pagar 
        Aprobado, // Pagado
        Retirado, // Retirado por el cliente?
        Rechazado // Se cancelo su compra ETC

    }
    
    public enum Tipos_Contrato {
        Fruta, // Fruta a Comprar, Cantidad , Calidad 
        Acceso, // Rol Asignado , Duracion , Creado Automaticamente al crear Cliente // Autmaticamente 3 Meses
        Proveedor // No se
    }

    public enum Tipos_Subasta{
        Venta_Interna,
        Venta_Local,
        Subasta_Produccion,
        Subasta_Transporte
    }
    
    public enum Roles {
        Cliente_Interno,
        Cliente_Externo,
        Productor,
        Transportista
    }
}
