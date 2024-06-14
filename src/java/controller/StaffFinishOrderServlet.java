/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import dao.OrderDAO;
import dto.AccountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import response.OrderResponseDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "StaffFinishOrderServlet", urlPatterns = {"/StaffFinishOrderServlet"})
public class StaffFinishOrderServlet extends HttpServlet {

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
        String url = Constant.Page.SIGN_IN_PAGE;
        HttpSession session = request.getSession(false);
        try {
            if (session != null) {
                AccountDTO accountDTO = (AccountDTO) session.getAttribute("ADMIN_ROLE");
                if (accountDTO != null) {
                    int orderID = Integer.parseInt(request.getParameter("txtOrderId"));
                    LocalDate today = LocalDate.now();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    Date shippedDate = Date.valueOf(today.format(format));
                    int frieght = Integer.parseInt(request.getParameter("freight"));
                    String shipAddress = request.getParameter("shipAddress");
                    OrderDAO dao = new OrderDAO();
                    Boolean result = dao.staffFinishOrder(orderID, shippedDate, frieght, shipAddress);
                    if (result) {
                        String msg = "You have successfully updated";
                        request.setAttribute("MSG", msg);
                    }
                    url = Constant.Controller.STAFF_SHOW_ORDERS_CONTROLLER;
                } 
            } 
        } catch (Exception e) {
            log("Error at ShowAllBookServlet " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
