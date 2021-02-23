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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");

            //conversion para busqueda
            String busqueda = request.getParameter("busqueda").toString();
            busqueda = busqueda.trim();
            busqueda = busqueda.replace(" ", "|");
            System.out.println("this is the busqueda:" + busqueda);

            //conversion para busqueda ojs
            negocioOpenJournal ojs = new negocioOpenJournal();
            String oJurnalSystem = "";
            ResultSet rs = ojs.getOpenJournal();

            ArrayList<urls> urlsAcademics = new ArrayList<urls>();
            ArrayList<urls> urlsOpenJournal = new ArrayList<urls>();
            ArrayList<urls> urlsOpenCatalogos = new ArrayList<urls>();

            String id = "", text = "", link = "";
            URL getRequestAcademics = new URL("https://testpoli-293416.rj.r.appspot.com/googleScholar/" + busqueda);
            String mensaje1 = "", mensaje2 = "", mensaje3 = "";
            String readLine = null;
            String[] names;
            String jsonString;
            //      HttpURLConnection conection = (HttpURLConnection) getRequest.openConnection();
            HttpURLConnection conection = (HttpURLConnection) getRequestAcademics.openConnection();
            conection.setRequestMethod("GET");
            conection.setConnectTimeout(1000);
            int responseCode = conection.getResponseCode();
            StringBuffer respuesta = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                respuesta = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    respuesta.append(readLine);
                }
                in.close();
                System.out.println("Json result academics: " + respuesta.toString());

                //google academics
                jsonString = respuesta.toString(); //assign your JSON String here
                //jsonString=jsonString.replace("/\\n/g", "\\n");
                JSONArray obj = new JSONArray(jsonString);      // notice that `"posts": [...]`

                for (int i = 0; i < obj.length(); i++) {
                    // String post_id = arr.getJSONObject(i).getString("post_id");
                    id = obj.getJSONObject(i).get("id").toString();
                    text = obj.getJSONObject(i).get("text").toString();
                    link = obj.getJSONObject(i).get("url").toString();
                    urlsAcademics.add(new urls(id, text, link));
                }

            } else {
                System.out.println("Falla en captcha");
                mensaje1 = "Falla en captcha";
            }

//--------------------------------------------------------------------------------------------
            while (rs.next()) {
                oJurnalSystem = rs.getString("url") + busqueda;
                System.out.println("open journal antes:" + oJurnalSystem);
                oJurnalSystem = oJurnalSystem.replace("https://", "mmm").replace("/", "aaa").replace("?", "bbb");
                System.out.println("open journal a enviarse:" + oJurnalSystem);
                URL getRequestOpenJournal = new URL("https://testpoli-293416.rj.r.appspot.com/openJournal/" + oJurnalSystem);

                conection = (HttpURLConnection) getRequestOpenJournal.openConnection();
                conection.setRequestMethod("GET");
                conection.setConnectTimeout(1000);

                responseCode = conection.getResponseCode();
                respuesta = null;
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                    respuesta = new StringBuffer();
                    while ((readLine = in.readLine()) != null) {
                        respuesta.append(readLine);
                    }
                    in.close();
                    System.out.println("Json result un open: " + respuesta.toString());
                    jsonString = respuesta.toString(); //assign your JSON String here
                    //jsonString=jsonString.replace("/\\n/g", "\\n");
                    JSONArray objOpenJournal = new JSONArray(jsonString);      // notice that `"posts": [...]`
                    //oenjornal
                    for (int i = 0; i < objOpenJournal.length(); i++) {
                        // String post_id = arr.getJSONObject(i).getString("post_id");
                        text = objOpenJournal.getJSONObject(i).get("urlText").toString();
                        link = objOpenJournal.getJSONObject(i).get("url").toString();
                        urlsOpenJournal.add(new urls(text, link));
                    }
                } else {
                    System.out.println("Falla en los openJournal:" + oJurnalSystem);
                    mensaje1 = "Falla en los openJournal";
                }
            }
//--------------------------------------------------------
//OJS del poli

            URL getRequestOpenJournalPoli = new URL("https://testpoli-293416.rj.r.appspot.com/openJournalPoli/" + busqueda);

            conection = (HttpURLConnection) getRequestOpenJournalPoli.openConnection();
            conection.setRequestMethod("GET");
            conection.setConnectTimeout(1000);

            responseCode = conection.getResponseCode();
            respuesta = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                respuesta = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    respuesta.append(readLine);
                }
                in.close();
                System.out.println("Json result poli: " + respuesta.toString());
                jsonString = respuesta.toString(); //assign your JSON String here
                //jsonString=jsonString.replace("/\\n/g", "\\n");
                JSONArray objOpenJournalPoli = new JSONArray(jsonString);      // notice that `"posts": [...]`
                //oenjornal
                for (int i = 0; i < objOpenJournalPoli.length(); i++) {
                    // String post_id = arr.getJSONObject(i).getString("post_id");
                    text = objOpenJournalPoli.getJSONObject(i).get("tittle").toString();
                    link = objOpenJournalPoli.getJSONObject(i).get("link").toString();
                    urlsOpenJournal.add(new urls(text, link));
                }
            } else {
                System.out.println("Falla en los openJournal poli");
                mensaje1 = "Falla en los openJournal";
            }

            //-------------------------------------------------------------------------------------
            // para catalogos
            URL getRequestCatalogos = new URL("https://testpoli-293416.rj.r.appspot.com/universidades/" + busqueda);

            conection = (HttpURLConnection) getRequestCatalogos.openConnection();
            conection.setRequestMethod("GET");
            conection.setConnectTimeout(1000);
            responseCode = conection.getResponseCode();
            respuesta = null;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
                respuesta = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    respuesta.append(readLine);
                }
                in.close();
                System.out.println("Json result universidades: " + respuesta.toString());
                jsonString = respuesta.toString(); //assign your JSON String here
                //jsonString=jsonString.replace("/\\n/g", "\\n");
                JSONObject objCatalogo = new JSONObject(jsonString);

                names = JSONObject.getNames(objCatalogo);
                for (String a : names) {
                    try {
                        JSONArray arr = objCatalogo.getJSONArray(a); // notice that `"posts": [...]

                        for (int m = 0; m < arr.length(); m++) {
                            if (arr.getJSONObject(m).length() > 0 || arr.getJSONObject(m) != null || !arr.getJSONObject(m).isEmpty()) {
                                id = arr.getJSONObject(m).get("id").toString();
                                text = arr.getJSONObject(m).get("titulo").toString();
                                link = arr.getJSONObject(m).get("url").toString();
                                urlsOpenCatalogos.add(new urls(id, text, link));
                            }

                        }

                    } catch (JSONException e) {
                    }
                }
            } else {
                System.out.println("Falla en los catalogos del AMVA");
                mensaje1 = "Falla en los catalogos del AMVA";
            }

            request.setAttribute("lista", urlsAcademics);
            request.setAttribute("lista2", urlsOpenJournal);
            request.setAttribute("lista3", urlsOpenCatalogos);
            request.getRequestDispatcher("index.jsp?b=1").forward(request, response);

        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head></head>");
            out.println("<body>");
            out.println("<h1>Error en la conexión a Heroku</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(loginUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
