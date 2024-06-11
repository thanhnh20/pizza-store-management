/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import dao.CustomerDAO;
import dto.AccountDTO;
import dto.CustomerDTO;
import error.CustomerErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UserUpdateProfileServlet", urlPatterns = {"/UserUpdateProfileServlet"})
public class UserUpdateProfileServlet extends HttpServlet {

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

        String address = request.getParameter("address");
        String stringPhone = request.getParameter("phone");
        HttpSession session = request.getSession(false);
        String url = Constant.Page.SIGN_IN_PAGE;
        CustomerErrorDTO error = new CustomerErrorDTO();
        boolean checkError = false;
        try {
            if (session != null) {
                AccountDTO accountDTO = (AccountDTO) session.getAttribute("USER_ROLE");
                if (accountDTO != null) {
                    
                    //check address input format
                    if (address.trim().length() == 0) {
                        error.setErrorAddressLength("Address is required 6-100 characters");
                        checkError = true;
                    }
                    String patternNumberPhone = "^(03|05|07|08|09)([0-9]{8,8})$";
                    //check phone number update
                    if (stringPhone.trim().length() < 10 || stringPhone.trim().length() > 11) {
                        error.setErrorPhoneNumberLength("Number phone is required 10 characters");
                        checkError = true;
                    } else {
                        if (stringPhone.matches(patternNumberPhone) == false) {
                            checkError = true;
                            error.setErrorPhoneNumberFormat("Number phone is invalid format");
                        }
                    }
                    
                    if(checkError){
                        url = Constant.Controller.USER_PROFILE_CONTROLLER;
                        request.setAttribute("ERROR", error);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }else{     
                        url = Constant.Controller.USER_PROFILE_CONTROLLER;
                        CustomerDTO user = new CustomerDTO();
                        CustomerDAO userDAO = new CustomerDAO();                       
                        boolean result = userDAO.updateProfile(address, stringPhone, accountDTO.getAccountId());
                        String msg = "Update Successfully";
                        request.setAttribute("MSG", msg);
                        RequestDispatcher rd = request.getRequestDispatcher(url);
                        rd.forward(request, response);
                    }
                } else {
                    response.sendRedirect(url);
                }
            } else {
                response.sendRedirect(url);
            }
        }catch(SQLException ex){
            log("SQLException at UserUpdateProfileServlet " + ex.getMessage());
        }catch(NamingException ex){
            log("NamingException at UserUpdateProfileServlet " + ex.getMessage());
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
