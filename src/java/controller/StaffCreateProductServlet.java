/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import dao.ProductDAO;
import dto.AccountDTO;
import dto.CategoryDTO;
import dto.ProductDTO;
import error.ProductErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Locale.Category;
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
 * @author Admin
 */
@WebServlet(name = "StaffCreateProductServlet", urlPatterns = {"/StaffCreateProductServlet"})
public class StaffCreateProductServlet extends HttpServlet {

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
                    String productName = request.getParameter("txtProductName");
                    String description = request.getParameter("txtDescription");
                    String imagePath = request.getParameter("txtImagePath");
                    int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                    int price = Integer.parseInt(request.getParameter("txtPrice"));
                    int categoryId = Integer.parseInt(request.getParameter("category"));

                    boolean check = true;
                    ProductErrorDTO productError = new ProductErrorDTO();

                    if (productName.trim().length() < 1 || productName.trim().length() > 50) {
                        check = false;
                        productError.setProductNameError("Product Name length must be [1..50] characters !");
                    }

                    if (description.trim().length() < 1 || description.trim().length() > 200) {
                        check = false;
                        productError.setProductNameError("Product description length must be [1..200] characters !");
                    }
                    if (imagePath.trim().length() < 1 || imagePath.trim().length() > 300) {
                        check = false;
                        productError.setImagePathError("ImagePath length must be [1..300] characters !");
                    }
                    if (quantity < 0) {
                        check = false;
                        productError.setQuantityError("Quantity must be greater than 0 !");
                    }
                    if (price <= 0) {
                        check = false;
                        productError.setPriceError("Price must be greater than 0 !");
                    }
                    if (check) {
                        ProductDAO dao = new ProductDAO();
                        ProductDTO dto = new ProductDTO(productName, quantity, price, description, imagePath, true, new CategoryDTO(categoryId));
                        boolean checkInsert = dao.insertProduct(dto);
                        if (checkInsert) {
                            url = Constant.Controller.STAFF_HOME_PAGE_CONTROLLER;
                            request.getRequestDispatcher(url).forward(request, response);
                        } else {
                            url = Constant.Page.STAFF_CREATE_PRODUCT_PAGE;
                            productError.setMessageError("Can not Create New Book !");
                            request.getRequestDispatcher(url).forward(request, response);
                        }

                    } else {
                        url = Constant.Page.STAFF_CREATE_PRODUCT_PAGE;
                        productError.setMessageError("Can not Create New Book !");
                        request.setAttribute("PRODUCT_ERROR", productError);
                        request.getRequestDispatcher(url).forward(request, response);
                    }
                } else {
                    response.sendRedirect(url);
                }
            } else {
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            log("NamingException at SignUpBookServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException at SignUpBookServlet " + ex.getMessage());
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
