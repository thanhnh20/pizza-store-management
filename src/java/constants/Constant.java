/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author ASUS
 */
public class Constant {
    public static class Page {
        public static final String SIGN_IN_PAGE = "signin.jsp";
        public static final String SIGN_UP_PAGE = "signup.jsp";
        public static final String USER_HOME_PAGE = "userHomePage.jsp";
        public static final String STAFF_HOME_PAGE = "staffHomePage.jsp";
        public static final String USER_PROFILE_PAGE  = "userProfile.jsp";
        public static final String CART_PAGE = "cart.jsp";
    }
    
    public static class Controller {
        public static final String START_UP_CONTROLLER = "StartUpServlet";
        public static final String SIGN_IN_CONTROLLER = "SignInServlet";
        public static final String SIGN_UP_CONTROLLER = "SignUpServlet";
        public static final String LOG_OUT_CONTROLLER = "LogoutServlet";
        public static final String STAFF_HOME_PAGE_CONTROLLER = "StaffHomePageServlet";
        
        public static final String USER_HOME_PAGE_CONTROLLER = "UserHomePageServlet";
        public static final String USER_PROFILE_CONTROLLER = "UserProfileServlet";
        public static final String USER_SEARCH_PRODUCT_CONTROLLER = "UserSearchProductServlet";
        public static final String USER_UPDATE_PROFILE_CONTROLLER = "UserUpdateProfileServlet";
        public static final String USER_ADD_TO_CART_CONTROLLER = "UserAddToCartServlet";
        public static final String USER_SHOW_CART_CONTROLLER = "UserShowCartServlet";
        public static final String USER_REMOVE_ITEMS_CART_CONTROLLER = "UserRemoveItemsCartServlet";
        public static final String USER_CHECK_OUT_CART_CONTROLLER = "UserCheckOutCartServlet";
        
        
    }
    
    public static class Action {
        public static final String SIGN_IN = "SignIn";
        public static final String SIGN_UP = "SignUp";
        public static final String LOG_OUT = "LogOut";
        public static final String USER_HOME_PAGE = "UserHomePage";
        public static final String USER_SEARCH_PRODUCT = "UserSearchProduct";
        public static final String USER_PROFILE = "UserProfile";
        public static final String USER_UPDATE_PROFILE = "UserUpdateProfile";
        public static final String USER_ADD_TO_CART = "UserAddToCart";
        public static final String USER_SHOW_CART = "UserShowCart";
        public static final String USER_CHECK_OUT_CART = "UserCheckout";
        public static final String USER_REMOVE_CART = "UserRemoveCart";
    }
}
