<%-- 
    Document   : Componente-NuevaSolicitud
    Created on : 21 oct. 2021, 22:52:36
    Author     : Seba
--%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        
                                    
                                    <!-- Button trigger modal -->
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                        Crear Solicitud
                                    </button>

                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Crear Solicitud</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="Solicitudes" method="POST">

                                                        <div class="form-group">
                                                            <label for="exampleFormControlSelect1">Example select</label>
                                                            <select onchange="crear(this.options[this.selectedIndex].innerHTML)" class="form-control" name="fruta" id="exampleFormControlSelect1">
                                                                <%Object[] orr = Clases.Valores_Estaticos.Frutas.getTodo();%>

                                                                <c:forEach items="<%=orr%>" var="fruta" varStatus="i" begin="1">
                                                                    <option value="${i.index}">${fruta}</option>
                                                                </c:forEach>

                                                            </select>

                                                            <div class="form-group">
                                                                <label for="cantidadFruta">Cantidad KG*</label>
                                                                <input type="number" required class="form-control" name="cantidad" min="1" id="cantidadFruta">

                                                            </div>
                                                            <div class="form-group">
                                                                <label for="calidades">Calidad</label>
                                                                <select class="form-control" name="calidad" id="calidades">
                                                                </select>
                                                            </div>

                                                            <div class="modal-footer ">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                <button type="submit" class="btn btn-primary">Submit</button>
                                                            </div>

                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                    <script>
                        const frutas = <%=Clases.Valores_Estaticos.Frutas.Datos()%>;
                        var select = document.getElementById('calidades');
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
                                   
                                   