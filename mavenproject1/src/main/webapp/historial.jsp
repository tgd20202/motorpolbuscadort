<%-- 
    Document   : historial
    Created on : 4/02/2021, 11:01:38 PM
    Author     : jorop
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="Negocio.negocioHistorial"%>
<%@page import="Negocio.negocioUsuario"%>
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
        <title>BuscadorT</title>
    </head>
    <body>
        <%

            //optener los datos de sesion
            HttpSession sesion = request.getSession();
            String usuario = sesion.getAttribute("usuario").toString();
            String rol = sesion.getAttribute("rol").toString();
            negocioHistorial negH = new negocioHistorial();
        %>


        <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <p class="h5 my-0 me-md-auto fw-normal" style="color:#ffc107;"><b>Motor de búsquedas Revista Politécnica</b></p>
            <nav class="my-2 my-md-0 me-md-3">
                <!--Aqui va el form de loggeo en caso de ser exitoso  -->
                <%                   if (rol.equals("editor")) {

                %>             
                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>
                <%} else if (rol.equals("admin")) {

                %>

                <a style="text-decoration:none" class="p-2 text-dark" href="crudCatalogos.jsp">Administrar Catálogos</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="crudEditores.jsp">Administrar Usuarios</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>
                <% } else {%>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>

                <%}%>

            </nav>
            <button type="button" class="btn btn-warning"><a class="btn btn-warning" href="logOut"><b>Salida</b></div></a></button>            <!--    <button class="btn btn-warning" onclick="salida()">Salir</button> -->
        </header>
        <main class="container">
            <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">

                <form id="form-list-client">

                    <h1 class="display-4">Historial Favoritos</h1>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Título</th>
                                <th scope="col">Link</th>
                                <th scope="col">Acción</th>

                            </tr>
                        </thead>
                        <tbody>
                            <%
                                try {
                                    ResultSet rs = negH.getUrls();
                                    while (rs.next()) {
                                        if (rs.getString("usuario").equals(usuario)) {


                            %>
                            <tr>
                                <td><%=rs.getString("titulo")%></td>
                                <td><a target="_blank" rel="noopener noreferrer" href="<%=rs.getString("url")%>">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="yellow" class="bi bi-link" viewBox="0 0 16 16">
                                        <path d="M6.354 5.5H4a3 3 0 0 0 0 6h3a3 3 0 0 0 2.83-4H9c-.086 0-.17.01-.25.031A2 2 0 0 1 7 10.5H4a2 2 0 1 1 0-4h1.535c.218-.376.495-.714.82-1z"/>
                                        <path d="M9 5.5a3 3 0 0 0-2.83 4h1.098A2 2 0 0 1 9 6.5h3a2 2 0 1 1 0 4h-1.535a4.02 4.02 0 0 1-.82 1H12a3 3 0 1 0 0-6H9z"/>
                                        </svg>
                                    </a>
                                </td>
                                <td>
                                    <button type="submit" class="btn btn-secondary"><a class="p-2 text-dark" href="eliminarHistorial?a=<%=rs.getString("idhistorial")%>">Eliminar</a></button>
                                </td>

                            </tr>
                            <%}
                                }
                            } catch (Exception e) {%>

                        <div class="alert alert-danger" role="alert">
                            Error en conexión a base de datos.
                        </div>
                        <%  }%>
                        </tbody>
                    </table>
                </form>
            </div>



        </main>
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
    var alertList = document.querySelectorAll('.alert')
                alertList.forEach(function (alert) {
                    new bootstrap.Alert(alert)
                })
    function onSignIn(googleUser) {
        console.log('user is:' + JSON.stringify(googleUser.getBasicProfile()));

        document.querySelector('#content').innerText = googleUser.getBasicProfile().getEmail();
        document.querySelector('#selectedMail').value = googleUser.getBasicProfile().getEmail();
        console.log(googleUser.getBasicProfile().getEmail());
        // jQuery
        /* $("#selectedVehicles").value(selectedVehicleTypes.join(",")); */

        // Submit the form using javascript
        var form = document.getElementById("login");
        form.submit();
    }

    function signOut() {

        gapi.auth2.getAuthInstance().signOut().then(function () {
            console.log("Usuario deslogueado")
        })
    }

    function  salida() {

        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log("se va a desloguie")
            document.location.href = "index.jsp";
        });

    }



    //jQuery
    /* $("#vehicles").submit(); */



</script>
</html>
