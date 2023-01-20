<%-- 
    Document   : Solicitudes
    Created on : 21 oct. 2021, 23:17:02
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
    </head>
    <body>
        <form action="Pagos" method="get" id="form_x">                        
            <input hidden="true" value="0" type="number" name="tipo">
            <input hidden="true" value="0" type="number" id="form_x_id" name="id">
        </form>
        <jsp:include page="Static/Html/Componente-Nav.jsp"/>
            
            <div id="alertaEntregado" style="display: none;" class="alert alert-warning align-items-center justify-content-center alert-dismissible fade show" role="alert">
                <svg xmlns="http://www.w3.org/2000/svg" style="display: none;"><symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16"><path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/></symbol></svg><svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Info:"><use xlink:href="#info-fill"/></svg>
                <div>Si presentas problemas con la carga por favor contactate con Administracion @correo </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <script></script>
        
        
        <main role="main">
            <section class="jumbotron text-center bg-white py-2" style="min-height: 70px;">
                <div class="container-sm">
                    <h2 class="display-5" style="font-size: 2rem; font-weight: 400;">Solicitudes</h2>
                    <p class="lead"></p>
                    <!--
                    <button onclick="RemoveFiltro()">Quitar Filtro</button>
                    <button onclick="filtro(1)">Manzana</button>
                    <button onclick="filtro(2)">Cosa</button>
                    -->
                </div>
            </section>
            
            <div class="container d-flex" style="min-height: 100px; align-items: center;" >
                <jsp:include page="Static/Html/Componente-NuevaSolicitud.jsp"/>
            </div>
            
            
            
            
            <div class="container-md mx-0 px-0 mx-md-auto px-md-auto py-2">
            <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Id Solicitud</th>
                        <th scope="col">Producto</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">Calidad</th>
                        <th scope="col">Estado</th>
                        <th scope="col"></th>
                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  items="${lista_solicitudes}" var="solicitud" varStatus="i">
                <tr  data-bs-toggle="collapse" data-bs-target="#D${i.index}" aria-expanded="false" aria-controls="collapseExample">
                    <td scope="row">${solicitud.id_solicitud}</td>
                    <td>${solicitud.getFruta()}</td>
                    <td>${solicitud.cantidad}</td>
                    <td>${solicitud.calidad}</td>
                    <td>
                        ${solicitud.UltimoEstado().estado}
                    </td>
                    <td>
                        <c:if test="${solicitud.UltimoEstado().estado == 'Entregada'}">
                            <button class="btn btn-primary BotonPagar" onclick="document.getElementById('form_x_id').value = ${solicitud.id_solicitud};document.getElementById('form_x').submit()">
                                Pagar Carga
                            </button>
                        </c:if>
                    </td>
                    
                    
                    
                </tr>
                
                
                <tr>
                    <td colspan="100%" style="padding: 0 !important; border: none !important;" >
                        <div class="collapse container-md border pt-3 justify-content-center" id="D${i.index}">
                            <p class="fs-2 lh-1 text-center">Registros de Estados</p>
                            <table class="table border w-60">
                                <thead>
                                    <tr>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Fecha</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${solicitud.registro_estados}" var="Registro">
                                        <tr>
                                            <td>${Registro.estado}</td>
                                            <td>${Registro.fecha}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </td>
                </tr>
                
                
            </c:forEach>
                </tbody>
            </table>
                </div>
            </div>
        </main>
            <script>
                document.querySelector('.BotonPagar') != null ? document.querySelector('.alert').style.display = 'flex' : 0;
            </script>
        
        <jsp:include page="Static/Html/Footer.html"/>
        <!-- BOOSTRAP -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script><!-- BOOSTRAP -->
    </body>
</html>
