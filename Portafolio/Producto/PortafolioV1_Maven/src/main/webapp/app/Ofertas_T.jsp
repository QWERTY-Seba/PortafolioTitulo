<%-- 
    Document   : Ofertas
    Created on : 10 oct. 2021, 4:11:28
    Author     : Seba
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- BOOSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> 
        
        
    </head>
    <body class="bg-white">
        <jsp:include page="Static/Html/Componente-Nav.jsp"/>
        <main role="main" >
            <section class="jumbotron text-center bg-white p2" style="min-height: 100px;">
                <div class="container-sm mt-3">
                    <h2 class="display-5" style="font-size: 2rem; font-weight: 400;">Historial de Ofertas Publicadas</h2>
                    
                </div>
            </section>
        
        <div class="container-md mx-0 px-0 mx-md-auto px-md-auto py-2">  
        <div class="table-responsive">
            <table class="table" style="border-collapse:collapse;">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Monto Transporte Nacional</th>
                        <th scope="col">Monto Transporte Extranjero</th>
                        <th scope="col">Patente</th>
                        <th scope="col">Estado</th>
                        <th scope="col"></th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="Oferta" items="${ofertas_transporte}" varStatus="i" begin="0">

                    <tr data-bs-toggle="collapse" data-bs-target="#demo${i.index}" aria-expanded="false" aria-controls="collapseExample" style="cursor: pointer;">
                        <td scope="row">${Oferta.id_oferta_transporte}</td>
                        <td>${Oferta.monto_transporte_NA}</td>
                        <td>${Oferta.monto_transporte_EX}</td>
                        <td>${Oferta.transporte.patente}</td>
                        <td>${Oferta.estado}</td>
                        <td>
                            <c:if test="${Oferta.estado == 'Seleccionado'}">
                                <button class="btn btn-primary">
                                    <a href="">Registrar Entrega</a>
                                </button>
                            </c:if>
                        </td>
                        
                    </tr>
                    
                    <tr>
                        <td colspan="100%" style="padding: 0 !important; border: none !important;" >
                            <div class="collapse" id="demo${i.index}" style="padding: 5px;">
                                <div></div>
                                
                                   
                            </div>
                        </td>
                    </tr>
                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
        </div>
        </main>
       
        <jsp:include page="Static/Html/Footer.html" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script><!-- BOOSTRAP --> 
         <style>footer{background-color: rgb(248, 249, 250) !important;}</style>
    </body>
</html>