<%-- 
    Document   : crudOpenJournal
    Created on : 2/02/2021, 11:23:26 PM
    Author     : jorop
--%>


<%@page import="Negocio.negocioUsuario"%>
<%@page import="Entidades.usuarios"%>
<%@page import="Negocio.negocioLogin"%>
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

            //optener los datos de sesion
            HttpSession sesion = request.getSession();
            String usuario = sesion.getAttribute("usuario").toString();
            String rol = sesion.getAttribute("rol").toString();
            negocioUsuario negU = new negocioUsuario();
            String id = null;
            String mail = "", nombre = "", rolOt = "";
            usuarios user = new usuarios();

            try {
                id = request.getParameter("a").toString();
                System.out.println("este es el id a actualizar usuario: " + id);
                int identificador = Integer.parseInt(id);
                if (id != null) {
                    user = negU.buscarActualizar(identificador);
                    mail = user.getMail();
                    nombre = user.getNombre();
                    rolOt = user.getRol();
                    System.out.println("este es el rol que trae:" + rolOt);
                }
            } catch (Exception e) {

            }


        %>
        <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <p class="h5 my-0 me-md-auto fw-normal" style="color:#ffc107;"><b>Motor de búsquedas Revista Politécnica</b></p>
            <nav class="my-2 my-md-0 me-md-3">
                <!--Aqui va el form de loggeo en caso de ser exitoso  -->

                <%                    if (rol.equals("editor")) {

                %>             
                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="historial.jsp">Historia Favoritos</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>
                <%} else if (rol.equals("admin")) {

                %>

                <a style="text-decoration:none" class="p-2 text-dark" href="crudCatalogos.jsp">Administrar Catálogos</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="crudEditores.jsp">Administrar Usuarios</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="historial.jsp">Historia Favoritos</a>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>
                <% } else {%>
                <a style="text-decoration:none" class="p-2 text-dark" href="index.jsp">Inicio</a>
                <a style="text-decoration:none" class="p-2 text-dark" target="_blank" rel="noopener noreferrer" href="https://drive.google.com/file/d/1zR0cbAHYMT8o941tcC6kBWyG9tgEn0fx/view?usp=sharing">Ayuda</a>

                <%}%>

            </nav>
            <button type="button" class="btn btn-warning"><a class="btn btn-warning" href="logOut"><b>Salida</b></div></a></button>        </header>
        <main class="container">
            <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                <h1 class="display-4">Usuarios</h1>
                <div class=col-md-12>

                    <form id="form-list-client" name="formulario" id="formulario" action="saveUsuario" method="post">
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Email</label>
                            <input type="email" id="email" name="email" class="form-control" aria-describedby="emailHelp" value="<%=mail != null ? mail : ""%>" required>
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Nombre</label>
                            <input type="text" id="nombre" name="nombre" class="form-control"  value="<%=nombre != null ? nombre : ""%>" required>
                        </div>
                        <div class="mb-3 form-check">
                            <label for="exampleInputPassword1" class="form-label">Roles</label>

                            <select class="form-select" aria-label="Default select example" name="rol" id="rol">
                                <option value="editor"  <%if (rolOt.equals("editor")) {%> selected <%}%>>Editor</option>
                                <option value="admin"   <%if (rolOt.equals("admin")) {%> selected <%}%>>Admin</option>
                            </select>
                        </div>
                        <input type="hidden" id="idUsuarios" name="idUsuarios" value="<%=id != null ? id : ""%>">   

                        <button type="submit" class="btn btn-primary">Guardar</button>

                    </form>

                </div>
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
