<%-- 
    Document   : Componente-Nav
    Created on : 21 oct. 2021, 22:38:34
    Author     : Seba
--%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>.nav-link{color: #fff!important;}
        
        .ImagenLogo{
            object-fit: cover;
            height: 90px;
            position: absolute;
            left: -100%;
            right: -100%;
            top: -100%;
            bottom: -100%;
            margin: auto;
            filter: brightness(0) invert(1);
        }
        .ImagenContainer{
            height: 40px;
            width: 200px;
            overflow: visible;
            position: absolute;
            left:0;
            top: 8px;
        }
        html {
            overflow-y:scroll;
        }
        
        </style>
        <nav class="p-2 navbar navbar-expand-xl navbar-dark bg-dark shadow-sm justify-content-end">
            
            <div class="ImagenContainer">
                <img class="ImagenLogo" src="/PortafolioV1_Maven/Logo2.png">
            </div>
            
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
       
            
            <div class="collapse navbar-collapse justify-content-center" id="navbarTogglerDemo01">
                <ul class="navbar-nav">
                    
                    
                    
                    <c:set var = "rol" scope = "request" value = "<%=request.getSession(false).getAttribute("role")%>"/>
                    
                    
                    <c:choose>
                        <c:when test="${rol == 'Cliente_Externo'}">
                            <li class="nav-item">
                                <a class="nav-link" href="Solicitudes">Mis Solicitudes</a>
                            </li>
                        </c:when>
                        <c:when test="${rol == 'Cliente_Interno'}">
                            <li class="nav-item">
                                <a class="nav-link" href="Pedidos">Mis Pedidos </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Subasta_Interna">Subastas Internas </a>
                            </li>
                        </c:when>
                        <c:when test="${rol == 'Productor'}">
                            <li class="nav-item">
                                <a class="nav-link" href="Subastas_Externas">Subastas Externas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Ofertas">Mis Ofertas</a>
                            </li>
                        </c:when>
                        <c:when test="${rol == 'Transportista'}">
                            <li class="nav-item">
                                <a class="nav-link" href="Subasta_Transporte">Subastas Transporte</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Transportes">Mis Ofertas</a>
                            </li>
                        </c:when>
                    </c:choose> 
                    
                    <li class="nav-item">
                        <a class="nav-link" href="#">Perfil</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Cerrar_Session">Cerrar Session</a>
                    </li>
                    
                </ul>
                
            </div>
        </nav>
        
