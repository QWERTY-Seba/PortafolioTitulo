<%-- 
    Document   : Subastas_Externas
    Created on : 10 oct. 2021, 5:04:07
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
        <jsp:include page="Static/Html/Componente-Nav.jsp"/>
        
        
        <main role="main">
            <section class="jumbotron text-center bg-white py-2" style="min-height: 100px;">
                <div class="container-sm">
                    <h2 class="display-5" style="font-size: 2rem; font-weight: 400;">Solicitudes de Fruta al extranjero</h2>
                    <p class="lead"></p>
                    <!--
                    <button onclick="RemoveFiltro()">Quitar Filtro</button>
                    <button onclick="filtro(1)">Manzana</button>
                    <button onclick="filtro(2)">Cosa</button>
                    -->
                </div>
            </section>
        <div class="container-md mx-0 px-0 mx-md-auto px-md-auto py-2">
        <div class="card-group">
            
          
            
            
            <c:forEach items="${Lista_Subasta}" var="Subasta" varStatus="i">
            <div class="Filt Filtro${Subasta.s.id_producto} col-12 col-md-6 col-lg-4 p-1">
                <div class="card" style="min-height: 300px;">
                        <div class="card-img-top d-flex justify-content-center mt-2">
                            <img  src="Static/Imagenes/Frutas/${Subasta.s.getFrutaRuta()}" height="80px" >
                        </div>
                    <div class="card-body">
                        <h5 class="card-title text-center"><span class="Nombre_Fruta">${Subasta.s.getFruta()}</span> a ${Subasta.s.cliente.direccion.replace('&',',').replace('%',',')}</h5>
                        <div class="row">
                            
                                <p class="card-text text-center">
                                    Desde ${Subasta.fecha_inicio} Hasta ${Subasta.fecha_termino}  <br>
                                    
                                Cantidad Necesaria ${Subasta.s.cantidad}kg <br>
                                Calidad Esperada ${Subasta.s.calidad} <br>
                                </p>
                        </div>
                    </div>
                    
                        <div class="card-footer bg-white d-flex justify-content-center">
                            <button class="btn btn-primary" onclick="MoverFormulario(this,${Subasta.id_subasta},${Subasta.s.id_producto})">Ingresar Oferta</button>
                        </div>
                    
                </div>
            </div>
            </c:forEach>
        </div>
        </div>
        </main>

        <div id="divFormulario" hidden=true class="w-100">
            <div class="card-body pb-0">
                <form id="formulario_subasta" action="Subastas_Externas" method="post" name="Solicitar">
                    <input type="hidden" value="0" name="id_producto">
                    <input type="hidden" value="0" name="id_subasta">

                    <div class="form-row">
                        <div class="col-12">
                            <label for="11">Fecha de Cosecha</label>
                            <input type="date" class="form-control" id="11" name="fecha_cosecha" required >
                        </div>
                        <div class="col-12">
                            <label for="13">Calidad</label>
                            <select class="form-control" name="calidad" id="13" required></select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-12">
                            <label for="12">Cantidad cosechada Kg*</label>
                            <input type="number" class="form-control" id="12" name="cantidad_inicial"  required>
                        </div>
                        <div class="col-12">
                            <label for="14">Precio por Kilogramo</label>
                            <input type="number" class="form-control" id="14" name="precio_por_kg" required>
                        </div>
                    </div>
                    
                    <div class="card-footer bg-transparent border-0 d-flex justify-content-center">
                        <input type="submit" class="btn btn-success"/>
                    </div>
                </form>
            </div>
        </div>
        
        
        <script>
            var u = null;
             //var ultimaId = none;
            function MoverFormulario(p, id1, id2){
                if(u == null){
                    u = p;                                        
                }else{
                    u.hidden = false;
                    u = p;
                }
                
                var formulario = document.getElementById("divFormulario");
                document.getElementById('formulario_subasta').elements['id_subasta'].value = id1;
                document.getElementById('formulario_subasta').elements['id_producto'].value = id2;
                formulario.hidden = false;
                p.hidden = true;
                p.parentElement.appendChild(formulario);
                
                
                var fruta = formulario.closest(".card").querySelector(".Nombre_Fruta").innerText;
                crear(fruta);
            }
            
            function CheckForm (){
            var r = document.getElementById("h");
            var x = r.getElementsByTagName("input");
            for(var i = 0; i < x.length; i++){
                    if(x[i].checked){
                      return true;
                    } 	
                  }
                return false;
              }


        const frutas = <%=Clases.Valores_Estaticos.Frutas.Datos()%>;
        var select = document.getElementById('13');
        const tamanos = ['XS','S','M','L','XL'];

        function crear(fruta)
                        {
                          if(select.getElementsByTagName("option").length > 0){
                                for(var i = 0; i<5;i++){
                                        select.getElementsByTagName("option")[0].remove();
                            }
                          }
                                var min = frutas[fruta][0];	
                                var max = frutas[fruta][1];
                                let inc = Number((max - min)/5);
                                var act = min;
                            for(var tamano of tamanos){
                                let before = Number(act);
                                act = Number(act + inc); 
                                var opt = document.createElement('option');
                                opt.value = tamano;
                                opt.innerHTML = tamano + ' ' + before.toFixed(1) + 'cm - ' + act.toFixed(1) + 'cm';

                                        select.appendChild(opt);    
                                }

                        }
                        crear('Manzana');
                        
                    

   

        </script>
        <jsp:include page="Static/Html/Footer.html"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script><!-- BOOSTRAP --> 
       
    </body>
</html>
