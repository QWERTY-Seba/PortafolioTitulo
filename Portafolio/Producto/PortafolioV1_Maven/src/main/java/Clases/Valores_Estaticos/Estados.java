/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Valores_Estaticos;

/**
 *
 * @author Seba
 */
//Agrupacion de todos los estados de las entidades del proyecto 
public class Estados {
    
    public enum Contrato{
        Activo,
        Cancelado,
        Expirado
    
    
    }
    
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
        Rechazado // Se cancelo su compra ETC

    }
    
    public enum Tipos_Contrato {
        Fruta, // Fruta a Comprar, Cantidad , Calidad 
        Acceso, // Rol Asignado , Duracion 
        Proveedor // No se
    }

    public enum Tipos_Subasta{
        Venta_Interna,
        Venta_Local,
        Subasta_Produccion,
        Subasta_Transporte
    }
    
    public enum Solicitud{
        Creada,
        Subasta_Produccion,
        Subasta_Transporte,
        En_Entrega,
        Entregada,
        Aprobada,
        Rechazada   
    }
    
    public enum Meses{
        Enero,
        Febreo,
        Marzo,
        Abril,
        Mayo,
        Junio, 
        Julio,
        Agosto,
        Septiembre,
        Octubre,
        Noviembre,
        Diciembre      
    }
    
}
