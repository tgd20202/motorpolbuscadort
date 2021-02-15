<%-- 
    Document   : index
    Created on : 29/01/2021, 12:00:32 AM
    Author     : jorop
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidades.urls"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="google-signin-client_id" content="6173572769-497jg1v7c628secsvve1g2a6hao7kq61.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link href="./css/index.css" rel="stylesheet">
        <link href="./css/main.css" rel="stylesheet">
        <title>BuscadotT</title>
    </head>
    <body>
        <%

            String mensaje = (String) request.getAttribute("mensaje");
            String rol = "";
            String booleanLogg = "";

        %>
        <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <p class="h5 my-0 me-md-auto fw-normal" style="color:#ffc107;"><b>Motor de búsquedas Revista Politécnica</b></p>

            <nav class="my-2 my-md-0 me-md-3">
                <!--Aqui va el form de loggeo en caso de ser exitoso  -->
                <% try {
                        booleanLogg = (String) request.getSession().getAttribute("booleanLogg");
                        if (booleanLogg.equals("l")) {
                            String usuario = (String) request.getSession().getAttribute("usuario");
                            rol = (String) request.getSession().getAttribute("rol");
                            if (usuario == null) {
                                System.out.println("entro a no nulo");

                %>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="#">About</a>

                <%} else if (rol.equals("editor")) {
                    System.out.println("entro a editor");
                    System.out.println(usuario);
                %>
                <a style="text-decoration:none" class="p-2 text-dark" href="historial.jsp">Historia Favoritos</a>    
                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="#">About</a>
                <%} else {
                    System.out.println("entro a admin");
                    System.out.println(usuario);
                %>




                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>

                <a style="text-decoration:none" class="p-2 text-dark" href="crudCatalogos.jsp">Administrar Catálogos</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="crudEditores.jsp">Administrar Usuarios</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="historial.jsp">Historia Favoritos</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>
                <%}
                    }
                } catch (Exception e) {
                    int aux2 = 1;
                %>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>
                <% }%>
            </nav>
            <%  try {
                    booleanLogg = (String) request.getSession().getAttribute("booleanLogg");
                    if (booleanLogg.equals("l")) {%>

            <button type="button" class="btn btn-warning"><a class="btn btn-warning" href="logOut"><b>Salida</b></div></a></button>

            <%  } else {
            %>   
            <a class="btn btn-outline-primary" href="#"><div class="g-signin2" data-onsuccess="onSignIn"></div></a>


            <%  }

            } catch (Exception e) {
            %>
            <a class="btn btn-outline-primary" href="#"><div class="g-signin2" onclick="ClickLogin()" data-onsuccess="onSignIn"></div></a>
                <%}%>
        </header>
        <main class="container">
            <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                <form name="formulario"  id="formulario" action="loginUsuario" method="post">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Palabra clave" aria-label="Recipient's username" aria-describedby="basic-addon2" name="busqueda" id="busqueda">
                        <div class="input-group-append">
                            <button name="enviar" id="enviar" class="btn btn-outline-secondary btn-warning" type="submit"><bold>Buscar</bold></button>
                        </div>
                    </div>
                </form>
                <!-- logo -->


                <%  if (mensaje != null) {


                %>
                <h1><%=mensaje%></h1>
                <%
                    }
                %>
                <% if ((request.getParameter("b") != null)) { %>
                <form id="form-list-client" name="formulario" id="formulario" action="saveUrls" method="post">
                    <nav>
                        <div class="nav nav-tabs justify-content-center" id="nav-tab" role="tablist">
                            <a class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Google Academics</a>
                            <a class="nav-link" id="nav-profile-tab" data-bs-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Open Journal</a>
                            <a class="nav-link" id="nav-contact-tab" data-bs-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Catálogos</a>
                        </div>
                    </nav>
                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                            <table class="table">
                                <thead>
                                    <tr>

                                        <th scope="col">Titulo</th>
                                        <th scope="col">Link</th>
                                            <%if (rol.equals("editor") || rol.equals("admin")) {%>
                                        <th scope="col">Guardar</th>
                                            <% }%>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%

                                        ArrayList<urls> list = new ArrayList<urls>();
                                        list = (ArrayList<urls>) request.getAttribute("lista");
                                        System.out.println("trae algo de lista academics");
                                        for (int i = 0; i < list.size(); i++) {
                                            urls aux = new urls();
                                            aux = list.get(i);

                                    %>
                                    <tr>

                                        <td><%=aux.getTitulo()%></td>
                                        <td><a target="_blank" rel="noopener noreferrer" href="<%=aux.getUrl()%>">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="yellow" class="bi bi-link" viewBox="0 0 16 16">
                                                <path d="M6.354 5.5H4a3 3 0 0 0 0 6h3a3 3 0 0 0 2.83-4H9c-.086 0-.17.01-.25.031A2 2 0 0 1 7 10.5H4a2 2 0 1 1 0-4h1.535c.218-.376.495-.714.82-1z"/>
                                                <path d="M9 5.5a3 3 0 0 0-2.83 4h1.098A2 2 0 0 1 9 6.5h3a2 2 0 1 1 0 4h-1.535a4.02 4.02 0 0 1-.82 1H12a3 3 0 1 0 0-6H9z"/>
                                                </svg>
                                            </a></td>

                                        <%if (rol.equals("editor") || rol.equals("admin")) {%>

                                        <td>
                                            <div class="custom-control custom-switch">
                                                <input type="checkbox" class="custom-control-input" id="historial" name="historial" value="<%=aux.getUrl()%>">
                                            </div>
                                            <input type="hidden" id="titulo" name="titulo" value="<%=aux.getTitulo()%>">
                                            <input type="hidden" id="historialAux" name="historialAux" value="<%=aux.getUrl()%>">

                                        </td>
                                        <% }%>
                                    </tr>
                                    <% }

                                    %>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                            <table class="table">
                                <thead>
                                    <tr>

                                        <th scope="col">Titulo</th>
                                        <th scope="col">Link</th>
                                            <%if (rol.equals("editor") || rol.equals("admin")) {%>
                                        <th scope="col">Guardar</th>
                                            <% }%>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%  ArrayList<urls> list2 = new ArrayList<urls>();
                                        list2 = (ArrayList<urls>) request.getAttribute("lista2");
                                        System.out.println("trae algo de lista");
                                        for (int i = 0; i < list2.size(); i++) {
                                            urls aux = new urls();
                                            aux = list2.get(i);

                                    %>
                                    <tr>

                                        <td><%=aux.getTitulo()%></td>
                                        <td><a target="_blank" rel="noopener noreferrer" href="<%=aux.getUrl()%>">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="yellow" class="bi bi-link" viewBox="0 0 16 16">
                                                <path d="M6.354 5.5H4a3 3 0 0 0 0 6h3a3 3 0 0 0 2.83-4H9c-.086 0-.17.01-.25.031A2 2 0 0 1 7 10.5H4a2 2 0 1 1 0-4h1.535c.218-.376.495-.714.82-1z"/>
                                                <path d="M9 5.5a3 3 0 0 0-2.83 4h1.098A2 2 0 0 1 9 6.5h3a2 2 0 1 1 0 4h-1.535a4.02 4.02 0 0 1-.82 1H12a3 3 0 1 0 0-6H9z"/>
                                                </svg>
                                            </a></td>
                                            <%if (rol.equals("editor") || rol.equals("admin")) {%>
                                        <td>
                                            <div class="custom-control custom-switch">
                                                <input type="checkbox" class="custom-control-input" id="historial" name="historial" value="<%=aux.getUrl()%>">
                                            </div>
                                            <input type="hidden" id="titulo" name="titulo" value="<%=aux.getTitulo()%>">
                                            <input type="hidden" id="historialAux" name="historialAux" value="<%=aux.getUrl()%>">

                                        </td>
                                        <% }%>

                                    </tr>
                                    <% }

                                    %>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
                            <table class="table">
                                <thead>
                                    <tr>

                                        <th scope="col">Titulo</th>
                                        <th scope="col">Link</th>
                                            <%if (rol.equals("editor") || rol.equals("admin")) {%>
                                        <th scope="col">Guardar</th>
                                            <% }%>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        ArrayList<urls> list3 = new ArrayList<urls>();
                                        list3 = (ArrayList<urls>) request.getAttribute("lista3");
                                        System.out.println("entra  a crear la tabla de catalogos");
                                        for (int i = 0; i < list3.size(); i++) {
                                            urls aux = new urls();
                                            aux = list3.get(i);
                                            System.out.println("aqui hace el recorrido de titulo:" + aux.getTitulo());

                                    %>
                                    <tr>

                                        <td><%=aux.getTitulo()%></td>
                                        <td><a target="_blank" rel="noopener noreferrer" href="<%=aux.getUrl()%>">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="yellow" class="bi bi-link" viewBox="0 0 16 16">
                                                <path d="M6.354 5.5H4a3 3 0 0 0 0 6h3a3 3 0 0 0 2.83-4H9c-.086 0-.17.01-.25.031A2 2 0 0 1 7 10.5H4a2 2 0 1 1 0-4h1.535c.218-.376.495-.714.82-1z"/>
                                                <path d="M9 5.5a3 3 0 0 0-2.83 4h1.098A2 2 0 0 1 9 6.5h3a2 2 0 1 1 0 4h-1.535a4.02 4.02 0 0 1-.82 1H12a3 3 0 1 0 0-6H9z"/>
                                                </svg>
                                            </a></td>
                                            <%if (rol.equals("editor") || rol.equals("admin")) {%>
                                        <td>
                                            <div class="custom-control custom-switch">
                                                <input type="checkbox" class="custom-control-input" id="historial" name="historial" value="<%=aux.getUrl()%>">
                                            </div>
                                            <input type="hidden" id="titulo" name="titulo" value="<%=aux.getTitulo()%>">
                                            <input type="hidden" id="historialAux" name="historialAux" value="<%=aux.getUrl()%>">

                                        </td>
                                        <% }%>

                                    </tr>
                                    <% }

                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <% }%>
                    <%if ((rol.equals("editor") || rol.equals("admin")) && (request.getParameter("b") != null)) {%>
                    <button type="submit" class="btn btn-warning">Guardar favoritos</button>
                    <% }%>

                </form>
            </div>



        </main>
        <%if (request.getParameter("b") == null) {%>
        <header class="bg-success text-center py-5 mb-4">
            <div class="container">
                <p class="font-weight-light text-white"><b>En este motor de busqueda de articulos y catálogos publicados podra encontrar información por autor (ramiro suarez ), por palabra clave(informática) y por correo(ejemplo@gmail.com)</b></p>
            </div>
        </header>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <!-- Team Member 1 -->
                <div class="col-xl-4 col-md-6 mb-4">
                    <div class="card border-0 shadow">
                        <img src="./img/busqueda.jpg" class="card-img-top" alt="Imagen aun no cargada">
                        <div class="card-body text-center">
                            <h5 class="card-title mb-0">Búsquedas</h5>
                            <div class="card-text text-black-50">Busqueda en catálogos, Google academics, OJS</div>
                        </div>
                    </div>
                </div>
                <!-- Team Member 2 -->
                <div class="col-xl-4 col-md-6 mb-4">
                    <div class="card border-0 shadow">
                        <img src="./img/ojs.jpg" class="card-img-top" alt="Imagen aun no cargada">
                        <div class="card-body text-center">
                            <h5 class="card-title mb-0">OJS</h5>
                            <div class="card-text text-black-50">Agrega las OJS que desees para las busquedas</div>
                        </div>
                    </div>
                </div>
                <!-- Team Member 3 -->
                <div class="col-xl-4 col-md-6 mb-4">
                    <div class="card border-0 shadow">
                        <img src="./img/favoritos.jpg" class="card-img-top" alt="Imagen aun no cargada">
                        <div class="card-body text-center">
                            <h5 class="card-title mb-0">Favoritos</h5>
                            <div class="card-text text-black-50">Consulta las busquedas que guardas como favoritas</div>
                        </div>
                    </div>
                </div>
                <!-- Team Member 4 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->
        <% }%>
        <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
    <df-messenger
        intent="WELCOME"
        chat-title="Poli"
        agent-id="0f3afa28-1a27-41b0-a85d-a970877cb46e"
        language-code="es"
        ></df-messenger>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>

<script>

                var clicked = false;//Global Variable
                function ClickLogin()
                {
                    clicked = true;
                }
                function onSignIn(googleUser) {
                    if (clicked) {
                        console.log('user is:' + JSON.stringify(googleUser.getBasicProfile()));

                        // document.querySelector('#content').innerText = googleUser.getBasicProfile().getEmail();
                        var gmail = googleUser.getBasicProfile().getEmail();
                        console.log(googleUser.getBasicProfile().getEmail());
                        // jQuery
                        /* $("#selectedVehicles").value(selectedVehicleTypes.join(",")); */

                        // Submit the form using javascript
                        document.location.href = "logginGmail?correo=" + gmail;
                    }
                }

                function signOut() {

                    gapi.auth2.getAuthInstance().signOut().then(function () {
                        console.log("Usuario deslogueado")
                        document.location.href = "index.jsp";
                    })
                }



                //jQuery
                /* $("#vehicles").submit(); */



</script>
</html>
