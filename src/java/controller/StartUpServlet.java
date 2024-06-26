/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import dao.AccountDAO;
import dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
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
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {
    
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
        String url = Constant.Controller.USER_HOME_PAGE_CONTROLLER;
        try{
            //get cookies
            Cookie[] listCookie = request.getCookies();
            if(listCookie != null){
                Cookie cookie = listCookie[listCookie.length - 1];
                String username = cookie.getName();
                String password = cookie.getValue();
                //check login with name and value of cookie
                AccountDAO accountDAO = new AccountDAO();
                boolean result = accountDAO.checkLogin(username, password);
                
                if(result){
                    AccountDTO accountDTO = accountDAO.getAccount(username); 
                    //check role
                    if (accountDTO.getRole() == 1) {
                        HttpSession session = request.getSession();
                        session.setAttribute("ADMIN_ROLE", accountDTO);
                        url = Constant.Controller.STAFF_HOME_PAGE_CONTROLLER;
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER_ROLE", accountDTO);
                    }
                }
            }
        }catch(NamingException ex){
            log("NamingException at StartUpServlet " + ex.getMessage());
        }catch(SQLException ex){
            log("SQLException at StartUpServlet " + ex.getMessage());
        }finally{
            response.sendRedirect(url);
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
