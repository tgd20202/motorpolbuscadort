<%-- 
    Document   : test
    Created on : 30/01/2021, 06:55:55 PM
    Author     : jorop
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Entidades.urls"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>test de entrada api de google</h1>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Titulo</th>

                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<urls> list = new ArrayList<urls>();
                    list = (ArrayList<urls>) request.getAttribute("lista");
                    System.out.println("trae algo de lista");
                    for (int i = 0; i < list.size(); i++) {
                        urls actor = new urls();
                        actor = list.get(i);
                       // out.println(actor.getTitulo());

                    
                %>
                <tr>
                    <td><%=actor.getIdHistorial()%></th>
                    <td><%=actor.getTitulo()%></td>


                </tr>
                <% }
                
                %>
            </tbody>
        </table>
    </body>
</html>
