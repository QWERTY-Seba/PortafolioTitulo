<%-- 
    Document   : Pedidos
    Created on : 4 oct. 2021, 10:39:03
    Author     : Seba
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- BOOSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
        <style>.footer{background-color: rgb(248, 249, 250)} tr[aria-expanded="true"]{background: rgba(200,200,200,0.5);}</style>
        <!-- BOOSTRAP --> 
        
    </head>
    <body class="bg-white">
        <jsp:include page="Static/Html/Componente-Nav.jsp"/>
        
        <form id="formulario" method="get" action="Pagos">
            <input type="number" name="tipo" value="1" hidden>
            <input type="number" name="id" id="f_ip_pedido" hidden></form>
                
        <main role="main">
            <div class="container-lg mx-0 px-0 mx-lg-auto px-lg-auto">
            
            <section class="jumbotron text-center bg-white p2" style="min-height: 100px;">
                <div class="container-sm mt-3">
                    <h2 class="display-5" style="font-size: 2rem; font-weight: 400;">Pedidos Realizados</h2>
                    <p class="lead">Se enviara la informacion respecto al retiro por email, despues de pagar un pedido</p>
                </div>
            </section>
            <div class="table-responsive">
                <table class="table table-hover w-100">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Fecha Creacion</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Producto</th>
                            <th scope="col">Calidad</th>
                            <th scope="col">Cantidad</th>
                            <th scope="col">Precio/kg</th>
                            <th scope="col">Monto Estimado</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        
                        <c:forEach items="${lista_Pedidos}" var="Pedido" varStatus="id">
                            <tr class="Fila_A" id="Fila_${id.index}" data-bs-toggle="collapse" data-bs-target="#D${id.index}" aria-expanded="false" aria-controls="collapseExample">
                                <td class="F_id_pedido"  scope="row">${Pedido.id_pedido}</td>
                                <td class=" ">
                                    ${Pedido.buscarCreado().fecha}
                                </td>
                                
                                <td class="F_estado">
                                    <c:choose>
                                        <c:when test="${Pedido.pagado}">
                                            <strong class="text-success">Pagado</strong>
                                        </c:when>
                                        <c:otherwise>
                                            <p>${Pedido.UltimoEstado().estado}</p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                
                                
                                
                                <td class="F_fruta">${Pedido.subasta.produccion.getProducto()}</td>
                                <td class="F_calidad">${Pedido.subasta.produccion.calidad}</td>
                                <td class="F_cantidad">${Pedido.cantidad}</td>
                                <td class="F_precio_por_kg">${Pedido.subasta.precio_por_kg}</td>
                                <td class="F_total"></td>
                                
                                
                                <td>
                                    <c:if test="${Pedido.UltimoEstado().estado == 'Aprobado'}">
                                        <button onclick="IrPago(${Pedido.id_pedido})" type="button" class="btn btn-outline-primary">
                                            Pagar Pedido                                            
                                        </button>
                                    </c:if>
                                </td>
                           
                            </tr>
                            <tr class="Fila_B" id="Fila_${id.index}B">
                                <td colspan="100%" style="padding: 0 !important; border: none !important;" >
                                    <div class="collapse container-md border pt-3 justify-content-center" id="D${id.index}">
                                        <p class="fs-2 lh-1 text-center">Registros de Estados</p>
                                        <table class="table border w-60" id="Fila_${id.index}C">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Estado</th>
                                                    <th scope="col">Fecha</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${Pedido.registro_estados}" var="Registro">

                                                    <tr>
                                                    <td>${Registro.estado}</td>
                                                    <td>${Registro.fecha}</td>
                                                    </tr>
                                                    
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        
                                        
                                        
                                            <p>${Pedido.subasta.toString()}</p>
                                            <p>${Pedido.subasta.produccion.toString()}</p>
                                    </div>
                                </td>
                            </tr>
                            
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Pago Aqui</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                            Redirigiendo al pago
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </main>
        <script>
            function IrPago(id){
                var form = document.getElementById('formulario');
                document.getElementById('f_ip_pedido').value = id;
                form.submit();               
            }
            
            window.document.onload = function(e){
               document.querySelectorAll(".Fila_A").forEach(function(s)
                    {
                    try{
                    console.log(s.id);
                    s.querySelector("td.F_fecha_creacion").innerText = document.querySelector('#' + s.id + 'C' + " tr:first-child td:nth-child(2)").innerText;
                    }catch(error){

                    }
                });           
                
            };
            
            function ActivarBotones(){
                document.querySelectorAll("tr table tbody tr:last-child > td:first-child").forEach(function(s){
                    if(s.innerText == "Aprobado"){
                         document.querySelector('#' + s.closest("table").id.substr(0,6) + ' td button').hidden = false;
                    }});               
            }
            ActivarBotones();
        </script>
        <jsp:include page="Static/Html/Footer.html"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
