/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import dao.ProductDAO;
import dto.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "UserSearchProductServlet", urlPatterns = {"/UserSearchProductServlet"})
public class UserSearchProductServlet extends HttpServlet {

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

        String searchValue = request.getParameter("txtSearchValue");
        String srange = request.getParameter("range");
        int range = 0;
        if (!srange.isEmpty()) {
            range = Integer.parseInt(srange);
        }
        String url = Constant.Page.SIGN_IN_PAGE;
        try {
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> listProduct = productDAO.getAllProduct();
            List<ProductDTO> listSearch = new ArrayList<ProductDTO>();
            int min = 0;
            int max = 1000;
            if (range == 1) {
                max = 20;
            } else if (range == 2) {
                min = 20;
                max = 50;
            } else if (range == 3) {
                min = 50;
                max = 1000;
            }
            for (ProductDTO dto : listProduct) {
                boolean check = true;
                if (!searchValue.isEmpty() && !(dto.getProductName().trim().toLowerCase().contains(searchValue.trim().toLowerCase()))) {
                    check = false;
                }
                if (range != 0 && !(min <= dto.getPrice() && dto.getPrice() < max)) {
                    check = false;
                }
                if (check) {
                    listSearch.add(dto);
                }
            }
            
            int numberBook = listProduct.size();
            int numberResult = listSearch.size();

            url = Constant.Page.USER_HOME_PAGE;
            request.setAttribute("rangeSelected", range);
//            request.setAttribute("NUMBER_BOOK", numberBook);
            request.setAttribute("NUMBER_RESULT", numberResult);
            request.setAttribute("LIST_PRODUCT", listSearch);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("NamingException at UserSearchBookServlet " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException at UserSearchBookServlet " + ex.getMessage());
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
