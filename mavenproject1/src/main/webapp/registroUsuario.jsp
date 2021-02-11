<%-- 
    Document   : crudOpenJournal
    Created on : 2/02/2021, 11:23:26 PM
    Author     : jorop
--%>


<%@page import="Entidades.usuarios"%>
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
        <title>BuscadotT</title>
    </head>
    <body>
        <%  
            
            //optener los datos de sesion
            HttpSession sesion = request.getSession(true);
               String usuario=sesion.getAttribute("usuario").toString();
               String rol= sesion.getAttribute("rol").toString();
               String mail= sesion.getAttribute("mail").toString();
               negocioUsuario negU=new negocioUsuario();
               String accion=request.getParameter("type").toString();
               usuarios user=new usuarios();
               if (accion.equals("a")) {
                       user=negU.listarDirecciones(mail);
                       
                   
                   }


                
        %>
        <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <p class="h5 my-0 me-md-auto fw-normal">Motor de búsquedas Revista Politécnica</p>
            <nav class="my-2 my-md-0 me-md-3">
                <!--Aqui va el form de loggeo en caso de ser exitoso  -->

                <a class="p-2 text-dark" href="crudOpenJournal.jsp">Administrar OJS</a>
                <a class="p-2 text-dark" href="crudCatalogos.jsp">Administrar Catálogos</a>
                <a class="p-2 text-dark" href="crudEditores.jsp">Administrar Usuarios</a>
                <a class="p-2 text-dark" href="historial.jsp">Historia Favoritos</a>
                <a class="p-2 text-dark" href="#">About</a>

            </nav>
            <button type="button" class="btn btn-warning"><a class="btn btn-outline-primary" href="main.jsp">Salida</div></a></button>
        </header>
        <main class="container">
            <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                <h1 class="display-4">Usuarios</h1>
                <div class=col-md-12>

                    <form action="">
                        <div class="mb-3">
                            <label for="exampleInputEmail1" class="form-label">Email</label>
                            <input type="email" id="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Nombre</label>
                            <input type="text" id="nombre" name="nombre" class="form-control" id="exampleInputPassword1">
                        </div>
                        <div class="mb-3 form-check">
                            <label for="exampleInputPassword1" class="form-label">Roles</label>

                            <select class="form-select" aria-label="Default select example" name="rol">
                                <option value="editor">Editor</option>
                                <option value="admin">Admin</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>

                </div>
            </div>
        </div>



    </main>
    <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
    intent="WELCOME"
    chat-title="Pol"
    agent-id="421ee075-3ba2-46c9-91a2-7b385618aa84"
    language-code="en"
    ></df-messenger>


<form id="login" method="POST" action="loginUsuario">

    <input type="hidden" name="selecteGmail" id="selectedMail"/>
    <input type="submit" value="" onClick="processLogin()">
</form>
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
