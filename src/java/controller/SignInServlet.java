/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import dao.AccountDAO;
import dto.AccountDTO;
import error.AccountErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SignInServlet", urlPatterns = {"/SignInServlet"})
public class SignInServlet extends HttpServlet {
    
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
        
        //get parameter        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String checkRemember = request.getParameter("checkRemember");
                
        String url = Constant.Page.SIGN_IN_PAGE;
        try {
            AccountErrorDTO error = new AccountErrorDTO();
            boolean foundError = false;
            // check username is not empty
            if (username.trim().length() == 0) {
                error.setUsernameEmpty("Please enter username");
                foundError = true;
            }
            //check password is not empty
            if (password.trim().length() == 0) {
                error.setPasswordEmpty("Please enter password");
                foundError = true;
            }
            //set error if found
            if (foundError) {
                request.setAttribute("ERROR", error);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                AccountDAO accountDAO = new AccountDAO();
                boolean result = accountDAO.checkLogin(username, password);
                if (result) {
                    AccountDTO accountDTO = accountDAO.getAccount(username);
                    // add cookie                 
                    if (checkRemember != null) {
                        Cookie cookie = new Cookie(username, password);
                        cookie.setMaxAge(60 * 1);
                        response.addCookie(cookie);
                    }
                    //check role
                    if (accountDTO.getRole() == 1) {
                        HttpSession session = request.getSession();
                        session.setAttribute("ADMIN_ROLE", accountDTO);
                        url = Constant.Controller.STAFF_HOME_PAGE_CONTROLLER;
                        response.sendRedirect(url);
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER_ROLE", accountDTO);
                        url = Constant.Controller.USER_HOME_PAGE_CONTROLLER;
                        response.sendRedirect(url);
                    }
                } else {
                    //set error account
                    error.setWrongAccount("Your username or password invalid");
                    request.setAttribute("ERROR", error);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }
        } catch (NamingException ex) {
            log("LoginController_NamingException " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginController_SQLException " + ex.getMessage());
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
