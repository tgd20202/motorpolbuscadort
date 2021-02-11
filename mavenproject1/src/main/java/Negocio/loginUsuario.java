/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.urls;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 *
 * @author jorop
 */
public class loginUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            String busqueda = request.getParameter("busqueda").toString();
            System.out.println("this is the busqueda:" + busqueda);
            
            
            ArrayList<urls> urlsAcademics = new ArrayList<urls>();
            ArrayList<urls> urlsOpenJournal = new ArrayList<urls>();
            ArrayList<urls> urlsOpenCatalogos = new ArrayList<urls>();
            
            String id = "", text = "", link="";
            URL getRequestAcademics = new URL("http://127.0.0.1:5000/googleScholar/"+busqueda);
            
            String readLine = null;
            String[] names;
      //      HttpURLConnection conection = (HttpURLConnection) getRequest.openConnection();
            HttpURLConnection conection = (HttpURLConnection) getRequestAcademics.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            StringBuffer respuesta = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                respuesta = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    respuesta.append(readLine);
                }
                in.close();
                System.out.println("Json result: " + respuesta.toString());
            } else {
                System.out.println("it doesnt work");
            }
              //google academics
            String jsonString = respuesta.toString(); //assign your JSON String here
            //jsonString=jsonString.replace("/\\n/g", "\\n");
            JSONArray obj = new JSONArray(jsonString);      // notice that `"posts": [...]`

            for (int i = 0; i < obj.length(); i++) {
                // String post_id = arr.getJSONObject(i).getString("post_id");
                id = obj.getJSONObject(i).get("id").toString();
                text = obj.getJSONObject(i).get("text").toString();
                link = obj.getJSONObject(i).get("url").toString();
                urlsAcademics.add(new urls(id, text,link));
            }
//--------------------------------------------------------------------------------------------
              

            URL getRequestOpenJournal = new URL("http://127.0.0.1:5000/openJournal/mmmrevistas.pascualbravo.edu.coaaaindex.phpaaacintexaaasearchaaasearchbbbquery=sistema");

               conection = (HttpURLConnection) getRequestOpenJournal.openConnection();
            conection.setRequestMethod("GET");
             responseCode = conection.getResponseCode();
             respuesta = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                respuesta = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    respuesta.append(readLine);
                }
                in.close();
                System.out.println("Json result: " + respuesta.toString());
            } else {
                System.out.println("it doesnt work");
            }
             jsonString = respuesta.toString(); //assign your JSON String here
            //jsonString=jsonString.replace("/\\n/g", "\\n");
            JSONArray objOpenJournal = new JSONArray(jsonString);      // notice that `"posts": [...]`
        //oenjornal
            for (int i = 0; i < objOpenJournal.length(); i++) {
                // String post_id = arr.getJSONObject(i).getString("post_id");
                text = objOpenJournal.getJSONObject(i).get("urlText").toString();
                link = objOpenJournal.getJSONObject(i).get("url").toString();
                urlsOpenJournal.add(new urls( text,link));
            }
 //-------------------------------------------------------------------------------------
        // para catalogos
        URL getRequestCatalogos= new URL("http://127.0.0.1:5000/universidades/"+busqueda);
        
         conection = (HttpURLConnection) getRequestCatalogos.openConnection();
            conection.setRequestMethod("GET");
             responseCode = conection.getResponseCode();
             respuesta = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                respuesta = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    respuesta.append(readLine);
                }
                in.close();
                System.out.println("Json result: " + respuesta.toString());
            } else {
                System.out.println("it doesnt work");
            }
            
         jsonString = respuesta.toString(); //assign your JSON String here
        //jsonString=jsonString.replace("/\\n/g", "\\n");
        JSONObject objCatalogo = new JSONObject(jsonString);

       names = JSONObject.getNames(objCatalogo);
        for (String a:names ) {
            JSONArray arr = objCatalogo.getJSONArray(a); // notice that `"posts": [...]`
            for (int m = 0; m < arr.length(); m++) {
                System.out.println(" dato:"+arr.getJSONObject(m).get("autor").toString());
              id = arr.getJSONObject(m).get("id").toString();
              text = arr.getJSONObject(m).get("titulo").toString();
              link = arr.getJSONObject(m).get("url").toString();
               urlsOpenCatalogos.add(new urls(id, text,link));
            }
        }
            request.setAttribute("lista", urlsAcademics);
            request.setAttribute("lista2", urlsOpenJournal);
            request.setAttribute("lista3", urlsOpenCatalogos);
            request.getRequestDispatcher("index.jsp?b=1").forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
