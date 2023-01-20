<%-- 
    Document   : Pago_S
    Created on : 10 dic. 2021, 8:35:27
    Author     : Seba
--%>

<%-- 
    Document   : Pago
    Created on : 23 nov. 2021, 13:10:34
    Author     : Seba
--%>

<%-- 
    Document   : Subasta_Interna
    Created on : 5 oct. 2021, 15:35:42
    Author     : Seba
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- BOOSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 

        <title>JSP Page</title>

        <style>
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
              -webkit-appearance: none;
               margin: 0;
            }
            .footer{
                background-color: white !important;
            }
        </style>
        
        
    </head>
    <body class="bg-light">
        <script src="https://www.paypal.com/sdk/js?client-id=Ad_GvF4ieMHIVweZcTgzhvX57GII5tMQcH64WB-_jCaTCWOCULKu1ueXKXvKY14PYYFnRCbiOl6bFtVU"></script>
        
        <jsp:include page="Static/Html/Componente-Nav.jsp"/>
        
        <main role="main" > 

            
            <!-- EJEMPLO PUBLICACION DE SUBASTA -->

            <!-- EJEMPLO PUBLICACION DE SUBASTA -->
            <div class="container-md mx-0 px-0 mx-md-auto px-md-auto py-4 mt-4 bg-white">
                <div class="row ">
                    <p class="fs-1 text-center">Pago de Solicitud</p>
                    <section class="col-12 col-lg-8 ">
                        <div class="p-md-4">
                            <div class="w-100 ">
                                <table class="table">
                                    <thead>
                                        <tr class="d-none d-sm-table-row"><th></th>
                                            <th>Descripcion</th>
                                            <th>Precio x KG</th>
                                            <th>Cantidad</th>
                                            <th>Total</th>

                                        </tr></thead>
                                    <tbody>
                                        <tr class="p-2">
                                            <td class="p-2">
                                                <div >
                                                    <img src="Static/Imagenes/Frutas/${solicitud.getFrutaRuta()}" style="width: 90px;">
                                                </div>
                                            </td>

                                            <td>${solicitud.getFruta().name()} tamaño ${solicitud.calidad}</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tbody></table>


                            </div>
                        </div>
                    </section>

                    <style>
                        @media(max-width:992px){
                            #test-border{
                                border: none !important;
                            }
                        }</style>

                    <section class="col-12 col-lg-4 mt-4 m-md-auto">
                        <div class="p-4 border-start bg-white" id="test-border">
                            <h3>Metodos de Pago Disponibles</h3>
                            <div>
                                </p><p>
                                </p><hr>
                                <p class="fs-3">Total a pagar <bolder>$${Pedido.cantidad * Pedido.subasta.precio_por_kg}</bolder>
                                </p>
                                <div id="paypal-button-container"></div>

                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </main>
        
        <script>
            let cantidad = 1;//${Pedido.cantidad};
            let precio_kg = 1;//${subasta.precio_por_kg};
            let dolar = 1;//800;
            let total = 1;//Number((cantidad * precio_kg)/dolar).toFixed(0);
        paypal.Buttons({
  createOrder: function(data, actions) {
    // This function sets up the details of the transaction, including the amount and line item details.
    return actions.order.create({
      
      purchase_units: [{
        amount: {
          currency_code : 'USD',
          value: total,
          breakdown: {
            item_total: {value: total, currency_code: 'USD'}
                    }
        },
        items : [{
            
            name : 'solicitud',
            description : '1',
            sku : '${solicitud.id_solicitud}',
            unit_amount: {value: total, currency_code: 'USD'},
            quantity : '1'          
        }]
        
        
      }],
        application_context: {
             shipping_preference: "NO_SHIPPING"
        }
    });
  },
  onApprove: function(data, actions) {
        return actions.order.capture().then(function (orderData) {
        console.log(orderData);
        var pago = 
       {id_order : orderData.id,
        id_payment : orderData.purchase_units[0].payments.captures[0].id,
        currency : orderData.purchase_units[0].amount.currency_code,
        value : orderData.purchase_units[0].amount.value,
        nombre : orderData.payer.name.given_name + " " + orderData.payer.name.surname,
        correo : orderData.payer.email_address,
        pais : orderData.payer.address.country_code,
        id_solicitud : orderData.purchase_units[0].items[0].sku}; 
      var url = 'http://localhost:8181/PortafolioV1_Maven/app/Pagos';
      fetch(url,
      {method : 'POST',
      body : JSON.stringify(pago),
      headers : {'Content-type' : 'application/json'}})
          .then(res => console.log(res))
          .then(alert("PAGO OKA"));
        })}
}).render('#paypal-button-container');
        </script>
        <jsp:include page="Static/Html/Footer.html"/>


        
        

        <!-- BOOSTRAP -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script><!-- BOOSTRAP -->



    </body>
</html>

