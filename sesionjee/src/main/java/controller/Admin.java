/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDao;
import model.User;

/**
 *
 * @author Eduardo.Onetto
 */
public class Admin extends HttpServlet {
    User u = new User();
    UserDao uDao = new UserDao();
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
        try ( PrintWriter out = response.getWriter()) {
            String res = "Ingresa tus credenciales";
            final String parameter = request.getParameter("action");
            switch (parameter) {
                case "addUser": {
                    if (!request.getParameter("userEmail").equals("") && !request.getParameter("password").equals("")) {
                        final String email = request.getParameter("userEmail");
                        final String password = request.getParameter("password");
                        if (this.uDao.insert(email, password)) {
                            response.sendRedirect("listar.jsp");
                        }
                        else {
                            out.println("<!DOCTYPE html>");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<title>Controlador Auth</title>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<h1> Error al llamar al motor</h1>");
                            out.println("</body>");
                            out.println("</html>");
                        }
                        break;
                    }
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Controlador Auth</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Controlador: Auth <br/> Faltaron Campos.</h1>");
                    out.println("</body>");
                    out.println("</html>");
                    break;
                }
                case "login": {
                    if (!request.getParameter("txtEmail").equals("") && !request.getParameter("txtPassword").equals("")) {
                        final String email = request.getParameter("txtEmail");
                        final String password = request.getParameter("txtPassword");
                        if (this.uDao.validar(email, password) != null) {
                            this.u = this.uDao.validar(email, password);
                            if (this.u.getEmail() == null) {
                                res = "Datos incorrectos";
                                request.setAttribute("res", (Object)res);
                                request.getRequestDispatcher("index.jsp").forward((ServletRequest)request, (ServletResponse)response);
                            }
                            else {
                                final HttpSession session = request.getSession(true);
                                session.setAttribute("session_email", (Object)this.u.getEmail());
                                System.out.println(session.getId());
                                response.sendRedirect("./menu/menu.jsp");
                            }
                        }
                        else {
                            res = "Problemas de conextion";
                            request.setAttribute("res", (Object)res);
                            request.getRequestDispatcher("index.jsp").forward((ServletRequest)request, (ServletResponse)response);
                        }
                        break;
                    }
                    res = "Completa todos los campos";
                    request.setAttribute("res", (Object)res);
                    request.getRequestDispatcher("index.jsp").forward((ServletRequest)request, (ServletResponse)response);
                    break;
                }
            }
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
