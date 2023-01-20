/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author Seba
 */
public class TestCorreo {
    final static String username = "sebaba9797@gmail.com";
    final static String password = "}a,U7xLgN&*3Vrc_";
    
    //CrearCorreo
    public static void EnviarCorreoPassword(String correo,String pw){
         
        String mensaje = "";
        String subject = "";
            subject = "Registro Maipo Grande";
             mensaje = "<!DOCTYPE html><html><head><meta charset=utf-8 /><meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'><title>Editor JavaScript online - www.cubicfactory.com</title></head><body>    <div id='a'>    <p class='title'>Tu Pedido ha sido Aprobado</p>   	<div class='text-content'>      <p>    	        <br><br>        <p>Ingresa a <a href='#'>Maipo Grande</a> con tu correo y la siguiente contraseña. <span>" + pw + "</span></p>     </p>     </div>        <a id='b' href='https://imgbb.com/'>      <img src='https://i.ibb.co/gFZJTY0/Logo2.png' alt='Logo2' height='80px'border='0'></a>  </div><style>      #a{        border: 1px solid #CCCCCC;        width: 100%;        max-width: 558px;        height: 350px;        margin: auto;      background: white;      border-radius: 4px;      margin-top: 30px;        position:relative;          }        body{      background: rgb(246, 247, 248);      font-family: sans-serif;      width: 100%;          }    .title{      font-weight: 400;      font-size: 2rem;      color: rgb(33, 37, 41);      text-align: center;    }        .text-content{        max-width: 526px;  	        width:100%;        margin: auto;	      text-align: center;    }    #b{        position: absolute;        top: 72%;        left: 35%;        filter: brightness(0);        opacity: 0.3;    }  </style></body></html>";
        EnviarCorreo(mensaje, subject,correo);

        
        }
        
        public static void EnviarCorreoPedido(String correo){
         
        String mensaje = "";
        String subject = "";
        subject = "Pedido Aprobado";
        mensaje = "<!DOCTYPE html><html><head><meta charset=utf-8 /><meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'><title>Editor JavaScript online - www.cubicfactory.com</title></head><body>    <div id='a'>    <p class='title'>Tu Pedido ha sido Aprobado</p>   	<div class='text-content'>      <p>    	        <br><br>        Para mas informacion visita <a href='#'>Maipo Grande</a>     </p>     </div>        <a id='b' href='https://imgbb.com/'>      <img src='https://i.ibb.co/gFZJTY0/Logo2.png' alt='Logo2' height='80px'border='0'></a>  </div><style>      #a{        border: 1px solid #CCCCCC;        width: 100%;        max-width: 558px;        height: 350px;        margin: auto;      background: white;      border-radius: 4px;      margin-top: 30px;        position:relative;          }        body{      background: rgb(246, 247, 248);      font-family: sans-serif;      width: 100%;          }    .title{      font-weight: 400;      font-size: 2rem;      color: rgb(33, 37, 41);      text-align: center;    }        .text-content{        max-width: 526px;  	        width:100%;        margin: auto;	      text-align: center;    }    #b{        position: absolute;        top: 72%;        left: 35%;        filter: brightness(0);        opacity: 0.3;    }  </style></body></html>";
         EnviarCorreo(mensaje, subject,correo);
        
        }
        
        public static void EnviarCorreoOferta(String correo) {

        String mensaje = "";
        String subject = "";
        subject = "Oferta Transporte Aprobada";
        mensaje = "<!DOCTYPE html><html><head><meta charset=utf-8 /><meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'><title>Editor JavaScript online - www.cubicfactory.com</title></head><body>    <div id='a'>    <p class='title'>Tu Oferta # ha sido Seleccionada</p>   	<div class='text-content'>      <p>    	Te enviaremos un correo dentro de las proximas horas con las condiciones de retiro.        <br><br>        Para mas informacion visita <a href='#'>Maipo Grande</a>     </p>     </div>        <a id='b' href='https://imgbb.com/'>      <img src='https://i.ibb.co/gFZJTY0/Logo2.png' alt='Logo2' height='80px'border='0'></a>  </div><style>      #a{        border: 1px solid #CCCCCC;        width: 100%;        max-width: 558px;        height: 350px;        margin: auto;      background: white;      border-radius: 4px;      margin-top: 30px;        position:relative;          }        body{      background: rgb(246, 247, 248);      font-family: sans-serif;      width: 100%;          }    .title{      font-weight: 400;      font-size: 2rem;      color: rgb(33, 37, 41);      text-align: center;    }        .text-content{        max-width: 526px;  	        width:100%;        margin: auto;	      text-align: center;    }    #b{        position: absolute;        top: 72%;        left: 35%;        filter: brightness(0);        opacity: 0.3;    }  </style></body></html>";
        EnviarCorreo(mensaje, subject, correo);

    }
        
    
    public static void EnviarCorreo(String mensaje,String subject,String correo){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "*");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setSubject(subject);
            message.setContent(mensaje, "text/html; charset=UTF-8");
            message.setFrom(new InternetAddress(username, "Maipo Grande"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

}
