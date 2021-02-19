<%-- 
    Document   : crudCatalogos
    Created on : 4/02/2021, 11:00:48 PM
    Author     : jorop
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="Negocio.negocioCatalogos"%>
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
            negocioCatalogos negC = new negocioCatalogos();

            try {
                //optener los datos de sesion
                HttpSession sesion = request.getSession();
                String usuario = sesion.getAttribute("usuario").toString();
                String rol = sesion.getAttribute("rol").toString();
        %>


        <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <p class="h5 my-0 me-md-auto fw-normal" style="color:#ffc107;"><b>Motor de búsquedas Revista Politécnica</b></p>
            <nav class="my-2 my-md-0 me-md-3">
                <!--Aqui va el form de loggeo en caso de ser exitoso  -->
                <%
                    if (rol.equals("editor")) {

                %>             
                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="historial.jsp">Historia Favoritos</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">About</a>
                <%} else if (rol.equals("admin")) {

                %>

                <a style="text-decoration:none" class="p-2 text-dark" href="crudEditores.jsp">Administrar Usuarios</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="historial.jsp">Historia Favoritos</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>
                <% } else {%>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>

                <%}
                } catch (Exception e) {%>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>

                <% }%>

            </nav>
            <button type="button" class="btn btn-warning"><a class="btn btn-warning" href="logOut"><b>Salida</b></div></a></button>        </header>
        <main class="container">
            <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                <div class=col-md-12>

                    <form id="form-list-client">
                        <h1 class="display-4">Catálogos</h1>
                        <br><br>
                        <div class="pull-right">
                            <br><br>
                        </div>
                        <table class="table table-bordered table-condensed table-hover">
                            <thead>
                                <tr>
                                    <td>#</td>
                                    <th>Nombre</th>
                                    <th>Url</th>
                                </tr>

                            </thead>
                            <tbody id="form-list-client-body">
                                <%
                                    try {
                                        ResultSet rs = negC.getCatalogos();
                                        while (rs.next()) {
                                %>
                                <tr>
                                    <td><%=rs.getInt("idcatalogos")%></td>
                                    <td><%=rs.getString("nombre")%></td>
                                    <td><%=rs.getString("url")%></td>

                                </tr>

                                <%}
                                } catch (Exception e) {%>

                            <div class="alert alert-danger" role="alert">
                                Error en conexión a base de datos.
                            </div>
                            <%  }%>
                            </tbody>
                        </table>
                    </form>


                </div>

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



    //jQuery
    /* $("#vehicles").submit(); */



</script>
</html>