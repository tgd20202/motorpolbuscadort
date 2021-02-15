/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Persistencia.daoHistoria;
import Persistencia.daoOpenJournal;
import Persistencia.daoUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
public class saveUrls extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            int aux1 = 0;
            int aux2 = 0;
            int aux3 = 0;
            if (request.getParameterValues("historial") != null) {
                String urls[] = request.getParameterValues("historial");
                String titulosAux[] = request.getParameterValues("titulo");

                String urlsAux[] = request.getParameterValues("historialAux");
                String titulos[] = new String[urls.length];

                String titulosHlep[] = new String[]{"google.com", "nani.com", "test.com"};

                String cosas[] = new String[]{"google.com", "nani.com", "test.com", "other.com"};

                ////Ligar las urls a los titulos
                for (String linkMain : urlsAux) {
                    aux2=0;
                    for (String linkOther : urls) {
                        if (linkMain.equals(linkOther)) {
                            titulos[aux3] = titulosAux[aux1];
                            aux3++;
                            break;
                        }
                        aux2++;
                    }
                    aux1++;
                }
                
                

                ////
                HttpSession sesion = request.getSession();
                String usuario = sesion.getAttribute("usuario").toString();
                daoHistoria dao = new daoHistoria();
                int aux = 0;
                for (String a : urls) {
                    System.out.println("titulos obtenidos:");
                    System.out.println(titulos[aux]);
                    System.out.println("urls obtenina");
                    System.out.println(urls[aux]);
                    dao.insertRegistroOpenJournal(usuario, a, titulos[aux]);
                    aux++;
                }
            }

            response.sendRedirect("historial.jsp");
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
