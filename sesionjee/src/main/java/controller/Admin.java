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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
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
@MultipartConfig
@WebServlet("/Admin")
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
            String parameter = request.getParameter("action");
            switch (parameter) {
                case "addUser": {
                    if (!request.getParameter("fname").equals("") && !request.getParameter("lname").equals("") && !request.getParameter("email").equals("") && !request.getParameter("pass").equals("")) {
                        final String email_add = request.getParameter("email");
                        final String password_add = request.getParameter("pass");           
                        final String nombre_add = request.getParameter("fname");
                        final String apellido_add = request.getParameter("lname");
                        if (this.uDao.insert(nombre_add, apellido_add, email_add, password_add)) {
                            response.sendRedirect("AdminUser.jsp");
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
                    }else{
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Controlador Auth</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Controlador: Auth <br/> Faltaron Campos.</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    break;
                }
                case "login":
                {
                    if (!request.getParameter("email").equals("") && !request.getParameter("password").equals("")) {
                        System.out.println(request.getParameter("email"));
                        System.out.println(request.getParameter("password"));
                        final String email = request.getParameter("email");
                        final String password = request.getParameter("password");
                        this.u = this.uDao.validar(email, password);
                        System.out.println(this.u);
                        if (this.u != null) {
                            if (this.u.getEmail() == null) {
                                res = "Datos incorrectos";
                                request.setAttribute("res", res);
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                            }
                            else {
                                System.out.println("Estoy aca");
                                HttpSession session = request.getSession(true);
                                session.setAttribute("session_email", (Object)this.u.getEmail());   
                                session.setAttribute("session_fname", (Object)this.u.getNombre());
                                session.setAttribute("session_lname", (Object)this.u.getApellido());

                                System.out.println(session.getId());
                                response.sendRedirect("AdminUser.jsp");
                            }
                        }
                    }else{
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Controlador Auth</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Controlador: Auth <br/> Faltaron Campos.</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    break;
                }
                case "logout":
                {
                    System.out.println("Estoy aca 1");
                    HttpSession session = request.getSession(true);
                    session.invalidate();
                    response.sendRedirect("index.jsp");
                    break;
                }
                case "get_info_by_id": {
                    HttpSession session = request.getSession();
                    if(session.getAttribute("session_email") != null){
                        int id = Integer.parseInt(request.getParameter("id_user"));
                        System.out.println(request.getParameter("id_user"));
                        this.u = this.uDao.getById(id);
                        response.setContentType("application/json;charset=UTF-8");
                        if (this.u != null) {
                            String json_ok = "{\"status\":\"200\", \"id\":\"" + this.u.getId() +"\", \"nombre\" : \"" + this.u.getNombre()+ "\", \"apellido\" : \"" + this.u.getApellido()+ "\", \"email\" : \"" + this.u.getEmail() + "\", \"password\" : \"" + this.u.getPassword()+ "\"}";
                            out.println(json_ok);
                        }else{
                            String json_error = "{\"data\" : [{\"status\":\"500\", \"msg\" : \"Error editando el regitro :(\"}]}";
                            out.println(json_error);
                        }
                    }else{
                        response.setContentType("application/json;charset=UTF-8");
                        String json_sinPermiso = "{\"data\" : [{\"status\":\"500\", \"msg\" : \"Debes iniciar sesion. :(\"}]}";
                        out.println(json_sinPermiso);
                    }
                    break;
                }
                case "edit": {
                    if (!request.getParameter("ed_id").equals("") && !request.getParameter("ed_nombre").equals("") && !request.getParameter("ed_apellido").equals("") && !request.getParameter("ed_password").equals("") && !request.getParameter("ed_email").equals("")) {
                        int ed_id = Integer.parseInt(request.getParameter("ed_id"));
                        String ed_nombre = request.getParameter("ed_nombre");
                        String ed_apellido = request.getParameter("ed_apellido");
                        String ed_email = request.getParameter("ed_email");
                        String ed_password = request.getParameter("ed_password");
                        String json_ok = "{\"data\" : [{\"status\":\"200\", \"msg\" : \"Registro editado con exito :)\"}]}";
                        String json_error = "{\"data\" : [{\"status\":\"500\", \"msg\" : \"Error editando el regitro :(\"}]}";

                        if (uDao.edit(ed_id,ed_email,ed_password,ed_nombre,ed_apellido)) {
                                out.println(json_ok);
                                break;
                        }else{
                                out.println(json_error);
                                break;
                        }
                    }else{
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Controlador Auth</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Controlador: Auth <br/> Faltaron Campos.</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    break;
               }
                case "delete": {
                    System.out.println("ENTRE AL DELETE");
                    int ed_id = Integer.parseInt(request.getParameter("del_id"));
                    //set_response
                    response.setContentType("application/json;charset=UTF-8");
                    String json_ok = "{\"data\" : [{\"status\":\"200\", \"msg\" : \"Registro eliminado con exito :)\"}]}";
                    String json_error = "{\"data\" : [{\"status\":\"500\", \"msg\" : \"Error eliminando el regitro :(\"}]}";
                   
                    if (uDao.delete(ed_id)) {
                            out.println(json_ok);
                    }else{
                            out.println(json_error);
                    }
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
