/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dto.AccountDTO;
import dto.CustomerDTO;
import dto.ProductDTO;
import error.OrderDetailErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
@WebServlet(name = "UserCheckOutCartServlet", urlPatterns = {"/UserCheckOutCartServlet"})
public class UserCheckOutCartServlet extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        String url = Constant.Page.SIGN_IN_PAGE;
        OrderDetailErrorDTO error = new OrderDetailErrorDTO();
        boolean check = true;
        try {
            if (session != null) {
                AccountDTO accountDTO = (AccountDTO) session.getAttribute("USER_ROLE");
                if (accountDTO != null) {
                    String[] listID = request.getParameterValues("checkedItems");
                    if (listID != null) {
                        ProductDAO productDAO = new ProductDAO();
                        int quantity;
                        for (String id : listID) {
                            quantity = Integer.parseInt(request.getParameter(id));
                            ProductDTO productDTO = productDAO.getProductById(Integer.parseInt(id));
                            
                            if(quantity <= 0){
                                error.setInvalidQuantity("Your input quantity is invalid");
                                check = false;
                            }
                            else if (productDTO.getQuantity() == 0) {
                                error.setQuantityOutOfStockError("The "+"\""+  productDTO.getProductName()+"\""+ " product is not ready now, "
                                        + "let remove from the cart");
                                check = false;
                            } else if (quantity > productDTO.getQuantity()) {
                                error.setOutOfQuantityError("Your input quantity is greater than the available quantity in stock\n");
                                check = false;
                            }
                            if (!check) {
                                break;
                            }
                        }
                        if (!check) {
                            url = Constant.Controller.USER_SHOW_CART_CONTROLLER;
                            request.setAttribute("ERROR", error);
                            RequestDispatcher rd = request.getRequestDispatcher(url);
                            rd.forward(request, response);
                        } else {
                            //set field Order
                            OrderDAO orderDAO = new OrderDAO();
                            LocalDate today = LocalDate.now();
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            Date date = Date.valueOf(today.format(format));
                            //set field Order Detail
                            List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
                            int amount = 0;
                            for (String id : listID) {
                                ProductDTO product = productDAO.getProductById(Integer.parseInt(id));
                                quantity = Integer.parseInt(request.getParameter(id));
                                product.setProductId(Integer.parseInt(id));
                                product.setQuantity(quantity);
                                amount += product.getPrice() * quantity;
                                listProduct.add(product);
                            }

                            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                            
                            CustomerDAO customerDAO = new CustomerDAO();
                            CustomerDTO customer = customerDAO.getCustomerByAccountId(accountDTO.getAccountId());
                            boolean result = orderDetailDAO.createOrderDetail(date, customer.getUserID(), amount, listProduct);

                            if (result) {
                                url = Constant.Controller.USER_REMOVE_ITEMS_CART_CONTROLLER;
                                String msg = "Check out successfully";
                                request.setAttribute("MSG", msg);
                                RequestDispatcher rd = request.getRequestDispatcher(url);
                                rd.forward(request, response);
                            }
                        }
                    } else {
                        url = Constant.Controller.USER_SHOW_CART_CONTROLLER;
                        String msg = "Please select items";
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
        } catch (NamingException ex) {
            log("NamingException at CheckOutServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("NamingException at CheckOutServlet " + ex.getMessage());
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
