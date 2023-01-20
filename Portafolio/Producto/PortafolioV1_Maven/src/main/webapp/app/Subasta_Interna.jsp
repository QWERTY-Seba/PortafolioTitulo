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
            #btnformulario{
                float: right;                
            }
        </style>
    </head>
    <body class="bg-light">
        <form id="formulario" method="get" action="Pagos"><input type="number" name="id" id="id_form" hidden><input type="text" name="tipo" value="saldo" hidden></form>
            
            <!-- Alerta -->
            
        
        
        <jsp:include page="Static/Html/Componente-Nav.jsp"/>
        
        <main role="main" > 
            <!-- Presentacion -->
            <section class="jumbotron text-center bg-white py-2" style="min-height: 100px;">
                <div class="container-sm">
                    <h2 class="display-5" style="font-size: 2rem; font-weight: 400;">Ventas Activas</h2>
                    <p class="lead">Ventas de fruta al kg y Saldos.</p>
                    <!--
                    <button onclick="RemoveFiltro()">Quitar Filtro</button>
                    <button onclick="filtro(1)">Manzana</button>
                    <button onclick="filtro(2)">Cosa</button>
                    -->
                </div>
            </section>
            
            
            <div class="container-md mx-0 px-0 mx-md-auto px-md-auto py-2">
                
                
            
            
            <!-- SUBASTAS -->
            <div class="card-group">                
                
                <!--
                <div class="Filt Filtro2 col-12 col-md-6 col-lg-4 p-1">
                    <div class="card" style="max-height: 330px;">
                        <div class="card-img-top d-flex justify-content-center mt-2">
                            <img  src="Static/Imagenes/Frutas/Uvas.jpg" height="100px" >
                        </div>
                        <div class="card-body">
                            <h5 class="card-title text-center">Venta Uva al KG</h5>
                            
                            <table>
                                <tr>
                                    <td>Calidad</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Disponible</td>
                                    <td>500kg</td>
                                </tr>
                                <tr>
                                    <td>Precio</td>
                                    <td>500$ kg</td>
                                </tr>
                            </table>
                                  
                        </div>
                        <div class="card-footer bg-white d-flex justify-content-center">
                            <button type="button" class="btn btn-primary" onclick="MoverFormulario(this)">Ingresar Pedido</button>
                        </div>
                    </div>
                </div> 
                -->
                <c:forEach items="${Lista_Subasta}" var="Subasta">
                    <c:if test="${Subasta.produccion.getCantidadRestante() > 0}">
                                    <c:if test="${Subasta.tipo_subasta == 'Venta_Local'}">
                                        <div id="${Subasta.id_subasta}" class="Filt Filtro${Subasta.produccion.id_producto} col-12 col-md-6 col-lg-4 p-1">
                                            <div class="card" style="min-height: 300px;">
                                                <div class="card-img-top d-flex justify-content-center mt-2">
                                                    <img  src="Static/Imagenes/Frutas/${Subasta.produccion.getRutaProducto()}" height="100px" >
                                                </div>
                                                <div class="card-body">

                                                    <h5 class="card-title text-center">
                                                        Saldo de ${Subasta.produccion.getProducto()}
                                                    </h5>
                                                    <p class="card-text text-center">
                                                        Disponible hasta ${Subasta.fecha_termino}<br>
                                                        Calidad ${Subasta.produccion.calidad} <br>
                                                        Disponible <span class="cantidad_disponible">${Subasta.produccion.getCantidadRestante()}</span> kg <br>
                                                        Precio  <span id="">${Subasta.precio_por_kg}$</span><br> 
                                                    </p>
                                                </div>


                                                
                                                    <div class="card-footer bg-white d-flex justify-content-center">
                                                        <button onclick="Crear_Saldo(${Subasta.id_subasta})" type="button" class="btn btn-success">
                                                            Comprar Saldo
                                                        </button>
                                                    </div>
                                                
                                            </div>
                                        </div>
                                    </c:if>
                                    
                                    
                                    <c:if test="${Subasta.tipo_subasta == 'Venta_Interna'}">
                                        <div id="${Subasta.id_subasta}" class="Filt Filtro${Subasta.produccion.id_producto} col-12 col-md-6 col-lg-4 p-1">
                                            <div class="card" style="min-height: 300px;">
                                                <div class="card-img-top d-flex justify-content-center mt-2">
                                                    <img  src="Static/Imagenes/Frutas/${Subasta.produccion.getRutaProducto()}" height="100px" >
                                                </div>
                                                <div class="card-body">
                                                    <h5 class="card-title text-center">
                                                        Venta ${Subasta.produccion.getProducto()} al KG
                                                    </h5>
                                                    <p class="card-text text-center">
                                                        Termina    ${Subasta.fecha_termino}<br>
                                                        Calidad    ${Subasta.produccion.calidad} <br>
                                                        Disponible <span class="cantidad_disponible">${Subasta.produccion.getCantidadRestante()}</span>kg <br>
                                                        Precio  <span class="precio_subasta">${Subasta.precio_por_kg}</span>$<br> 
                                                    </p>
                                                </div>
                                                        <div class="card-footer bg-white d-flex justify-content-center">
                                                            <button type="button" class="btn btn-primary" onclick="MoverFormulario(this,${Subasta.id_subasta})">Ingresar Pedido</button>
                                                        </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:if>
                </c:forEach>
            </div>
            </div>
        </main>
        <jsp:include page="Static/Html/Footer.html"/>


        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                        Redirigiendo al pago
                    </div>
                </div>
            </div>
        </div>
        
        <div id="divFormulario" hidden= true>
            <button id="btnformulario" type="button" class="btn-close" onclick="this.parentElement.hidden = true; u.hidden = false" aria-label="Close"></button>
            <p>Monto Estimado <span class="text-muted" id="MontoEstimado">$0</span></p>
            
            <form id="formulario_subasta" action="Pedidos" method="post" name="Solicitar">
                <input type="hidden" id="fid_subasta" value="0" name="id_subasta">
                
                <table>
                    <td>
                        <input id="InputCantidad" type="number" class="form-control" required name="cantidad" placeholder="Ingresar Cantidad"/>
                    </td>
                    <td>
                         <input type="submit" class="btn btn-success"/>
                    </td>
                </table>
                
               
            </form>
        </div>
        <script>
            var u = null;
            var restante = 0;
            
            function MoverFormulario(p,id){
                
                if(u == null){
                    u = p;                                        
                }else{
                    u.hidden = false;
                    u = p;
                }
                var formulario = document.getElementById("divFormulario");
                formulario.hidden = false;
                document.getElementById("fid_subasta").value = id;
                p.hidden = true;
                p.parentElement.appendChild(formulario);
                
                restante = document.querySelector("#formulario_subasta").closest(".card").querySelector(".precio_subasta").innerText;
            }
            
            function filtro(id){
                document.querySelectorAll(".Filt").forEach(f => f.classList.contains("Filtro"+id) ? f.hidden = false : f.hidden = true)
            }
            
            function RemoveFiltro(){
                 document.querySelectorAll(".Filt").forEach(f => f.hidden = false);
            }
            var MontoEstimado = document.getElementById("MontoEstimado");
            const InputCantidad = document.getElementById("InputCantidad");

              const formatter = new Intl.NumberFormat('es-CL', {
                style: 'currency',
                currency: 'CLP'                
              });

            InputCantidad.onchange =  function(){
                
                MontoEstimado.innerHTML = formatter.format(InputCantidad.value * Number(restante));
            };

            var respuesta = {};
            document.getElementById("formulario_subasta").addEventListener("submit", Enviar2);
            function Enviar2(event){
                event.preventDefault();
                const form = event.currentTarget;
                fetch("Pedidos",{
                    method : 'Post',
                    headers: {'Content-Type': 'application/json'},
                    body : JSON.stringify(Object.fromEntries(new FormData(form)))
                }).then(res => res.json())
                  .then(function(res){
                      if(res.id_pedido != null){
                          alert("Tu pedido se ha creado exitosamente");
                          document.getElementById("btnformulario").click();
                      }
                      
                  });
            }
            function Crear_Saldo(id_subasta){
                 var form = document.getElementById("formulario_subasta");
                 form.querySelector('#fid_subasta').value = id_subasta;
                 form.querySelector('#InputCantidad').value = document.getElementById(id_subasta).querySelector(".cantidad_disponible").innerText;
                 form.dispatchEvent(new Event('submit'));
                 
                for(var i = 0; i<=5; i++){
                    setTimeout(() => { 
                        if(respuesta.id_pedido != null){
                            window.location.href = "Pagos?id_pedido="+respuesta.id_pedido;                            
                        }            
                    }, 500);
                }
            }
        </script>
        
        <!-- BOOSTRAP -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script><!-- BOOSTRAP -->
        
    
    
    </body>
</html>
