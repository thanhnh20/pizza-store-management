/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constants.Constant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {
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
        
        String url = "";
        String action = request.getParameter("btnAction");
        try{      
            if(action == null){
                action = "";
            }
            switch(action){
                case Constant.Action.SIGN_IN: {
                    url = Constant.Controller.SIGN_IN_CONTROLLER;
                    break;
                }
                case Constant.Action.SIGN_UP: {
                    url = Constant.Controller.SIGN_UP_CONTROLLER;
                    break;
                }
                case Constant.Action.LOG_OUT: {
                    url = Constant.Controller.LOG_OUT_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_HOME_PAGE: {
                    url = Constant.Controller.USER_HOME_PAGE_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_SEARCH_PRODUCT: {
                    url = Constant.Controller.USER_SEARCH_PRODUCT_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_PROFILE: {
                    url = Constant.Controller.USER_PROFILE_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_UPDATE_PROFILE: {
                    url = Constant.Controller.USER_UPDATE_PROFILE_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_ADD_TO_CART: {
                    url = Constant.Controller.USER_ADD_TO_CART_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_SHOW_CART: {
                    url = Constant.Controller.USER_SHOW_CART_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_REMOVE_CART: {
                    url = Constant.Controller.USER_REMOVE_ITEMS_CART_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_CHECK_OUT_CART: {
                    url = Constant.Controller.USER_CHECK_OUT_CART_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_UPDATE_PRODUCT: {
                    url = Constant.Controller.STAFF_UPDATE_PRODUCT_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_SHOW_PRODUCT: {
                    url = Constant.Controller.STAFF_HOME_PAGE_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_SEARCH_PRODUCT: {
                    url = Constant.Controller.STAFF_SEARCH_PRODUCT_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_CREATE_PRODUCT: {
                    url = Constant.Controller.STAFF_CREATE_PRODUCT_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_SHOW_USERS: {
                    url = Constant.Controller.STAFF_SHOW_USERS_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_UPDATE_USER: {
                    url = Constant.Controller.STAFF_UPDATE_USER_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_SEARCH_USER: {
                    url = Constant.Controller.STAFF_SEARCH_USER_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_SHOW_ORDERS: {
                    url = Constant.Controller.STAFF_SHOW_ORDERS_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_SEARCH_ORDER: {
                    url = Constant.Controller.STAFF_SEARCH_ORDERS_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_DETAIL_ORDER: {
                    url = Constant.Controller.STAFF_DETAIL_ORDER_CONTROLLER;
                    break;
                }
                case Constant.Action.STAFF_FINISH_ORDER: {
                    url = Constant.Controller.STAFF_FINISH_ORDER_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_ORDERS_HISTORY: {
                    url = Constant.Controller.USER_ORDERS_HISTORY_CONTROLLER;
                    break;
                }
                case Constant.Action.USER_ORDER_DETAIL: {
                    url = Constant.Controller.USER_ORDER_DETAIL_CONTROLLER;
                    break;
                }
                default :{
                    url = Constant.Controller.START_UP_CONTROLLER;
                }
            }
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
