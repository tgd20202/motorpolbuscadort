/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Persistencia.daoOpenJournal;
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
public class saveOpenJournal extends HttpServlet {

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

            daoOpenJournal dao = new daoOpenJournal();
            HttpSession sesion = request.getSession();
            String mail = (String) sesion.getAttribute("usuario");
            String url = request.getParameter("url").toString().trim();
            String nombre = request.getParameter("name").toString().trim();
            String ids = request.getParameter("idopenjournal").toString();
            int id=0;
            if (!ids.equals("")) {
                 id= Integer.parseInt(ids);
            }

            System.out.println("id para actualizar en sevlet:" + id);

            System.out.println("usuario para actualizar:" + mail);
            System.out.println("url para actualizar:" + url);

            String mensaje = dao.insertRegistroOpenJournal(mail, url, nombre, id);
            System.out.println("mensaje del servlet de openjournal:" + mensaje);
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("crudOpenJournal.jsp").forward(request, response);

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
