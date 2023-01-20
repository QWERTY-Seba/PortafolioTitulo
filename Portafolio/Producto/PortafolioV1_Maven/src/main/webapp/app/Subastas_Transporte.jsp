<%-- 
    Document   : Subastas_Transporte
    Created on : 4 oct. 2021, 11:21:50
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
    <style>

            .footer{
                background-color: white !important;
            }
            .card
            
        </style>
    
    </head>
    <body class="bg-light">
        <style>#formulario_subasta > *{display: block;}</style>
        <jsp:include page="Static/Html/Componente-Nav.jsp"/>
        <main role="main">
            
            <section class="jumbotron text-center bg-white py-2" style="min-height: 100px;">
                <div class="container-sm">
                    <h2 class="display-5" style="font-size: 2rem; font-weight: 400;">Ofertas de Transporte Nacional e Internacional</h2>
                    <p class="lead"></p>
                    <!--
                    <button onclick="RemoveFiltro()">Quitar Filtro</button>
                    <button onclick="filtro(1)">Manzana</button>
                    <button onclick="filtro(2)">Cosa</button>
                    -->
                </div>
            </section>
            
            <div class="container-md mx-0 px-0 mx-md-auto px-md-5 py-4">
                
                <c:forEach items="${lista_subastas}" var="subasta" varStatus="i">
                    <div class="card row mb-3" data-pais="${subasta.solicitud.cliente.pais}">
                        <div class="card-body">
                            <h4 class="card-title ms-4">
                                Transporte de ${subasta.solicitud.getFruta()} hacia ${subasta.solicitud.cliente.region} ${subasta.solicitud.cliente.pais} 
                            </h4>
                            <p class="lead"></p>
                            <p class="card-text fs-5">
                                Empieza : ${subasta.fecha_inicio}<br>
                                Termina : ${subasta.fecha_termino}
                                
                            </p>
                            <div class="card-bottom">
                                <button onclick="MoverFormulario(this,${subasta.id_subasta})" class="btn btn-primary float-end">
                                    Ingresar Oferta
                                </button>
                            </div>
                        </div>
                        
                    </div>
                </c:forEach>
            </div>
            
            
            <div id="divFormulario" hidden="true">
                <button id="btnformulario" style="float: right; margin: 5px;" type="button" class="btn-close" onclick="this.parentElement.hidden = true; u.hidden = false" aria-label="Close"></button>

                <form id="formulario_subasta" action="Oferta_Transporte" method="post">
                    <input type="hidden" id="fid_subasta" value="0" name="id_subasta">
                    
                    <label for="lista_transportes">Transportes Registrados</label>
                    <select required name="id_transporte" id="lista_transportes" class="form-select form-select-sm" aria-label=".form-select-sm example">
                        <c:forEach var="transporte" items="${lista_transportes}">
                            <option value="${transporte.id_transporte}">${transporte.patente} ${transporte.marca} ${transporte.modelo}</option>                           
                        </c:forEach>
                    </select>

                    <label for="monto_transporte_NA">Monto Transporte Local</label>
                    <small class="form-text text-muted">
                        Si el transporte es al extranjero, este costo corresponde Maipo Grande hacia Puerto/Aeropuerto 
                    </small>
                    <input id="monto_transporte_NA" type="number" name="monto_transporte_NA" required />
                    
                    <div id="formulario_subasta_externa">
                        
                        <label for="monto_transporte_EX">Monto Transporte Extranjero</label>
                        <input id="monto_transporte_EX" type="number" name="monto_transporte_EX" required/>
                        
                        <label for="compania_externa">Compañia Externa</label>
                        <input onchange="AlterarEstado()" id="compania_externa"type="checkbox"  name="compania_externa"/>    
                        
                        <div id="div_compania_externa" class="row">
                            <div class="col-6">
                                <label for="nombre_compania">Nombre Compañia</label>
                                <input id="nombre_compania" type="text" name="nombre_compania" required/>
                            </div>
                            <div class="col-6">
                                <label for="rut_compania">Rut Compañia</label>
                                <input id="rut_compania" type="text" name="rut_compania" required/>
                            </div>
                        </div>
                        
                    </div>
                    
                    <input type="submit" class="btn btn-success"/>
                </form>
                
            </div>
            
            
        </main>
        <script>
            var u = null;
            var activado = false;
            function AlterarEstado(){
                activado = !activado;
                document.getElementById('div_compania_externa').querySelectorAll('input').forEach(s => s.disabled = activado)
                
            }
            AlterarEstado();
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
                
                var subdiv = formulario.querySelector("#formulario_subasta_externa");
                //Ajuste de Formulario
                if (p.closest('.card').getAttribute("data-pais") == "Chile"){
                    subdiv.hidden = true;
                }else{
                    subdiv.hidden = false;
                    
                }
                
            }
            
            
            
            var respuesta = {};
            document.getElementById("formulario_subasta").addEventListener("submit", Enviar2);
            function Enviar2(event){
                event.preventDefault();
                const form = event.currentTarget;
                fetch("Subasta_Transporte",{
                    method : 'Post',
                    headers: {'Content-Type': 'application/json'},
                    body : JSON.stringify(Object.fromEntries(new FormData(form)))
                }).then(res => res.json())
                  .then(function(res){
                      if(res.exito){
                          form.reset();
                          document.getElementById("btnformulario").click();
                          alert("Tu oferta se registro exitosamente");
                          
                      }
                      
                      
                  });
            }
            
        </script>
        <jsp:include page="Static/Html/Footer.html"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script><!-- BOOSTRAP --> 
    </body>
</html>
