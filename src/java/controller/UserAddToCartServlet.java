/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cart.CartList;
import constants.Constant;
import dao.ProductDAO;
import dto.AccountDTO;
import dto.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "UserAddToCartServlet", urlPatterns = {"/UserAddToCartServlet"})
public class UserAddToCartServlet extends HttpServlet {

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

        String productId = request.getParameter("productId");
        HttpSession session = request.getSession(false);
        String url = Constant.Page.SIGN_IN_PAGE;
        try {
            if (session != null) {
                AccountDTO accountDTO = (AccountDTO) session.getAttribute("USER_ROLE");
                if (accountDTO != null) {
                    ProductDAO productDAO = new ProductDAO();
                    //get book add
                    ProductDTO bookDTO = productDAO.getProductById(Integer.parseInt(productId));

                    //add book to cart                    
                    CartList cartList = (CartList) session.getAttribute("CART");
                    if (cartList == null) {
                        cartList = new CartList();
                    }
                    cartList.addBookToCart(bookDTO);
                    session.setAttribute("CART", cartList);
                    url = Constant.Controller.USER_SEARCH_PRODUCT_CONTROLLER;
                    String msg = "Add product to cart successfull";
                    request.setAttribute("MSG", msg);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    response.sendRedirect(url);
                }
            } else {
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            log("NamingException at AddBookToCartServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException at AddBookToCartServlet " + ex.getMessage());
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
