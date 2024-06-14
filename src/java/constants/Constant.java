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
        public static final String STAFF_CREATE_PRODUCT_PAGE = "addNewProduct.jsp";
        public static final String STAFF_USER_MANAGEMENT_PAGE = "staffListUser.jsp";
        public static final String STAFF_ORDER_MANAGEMENT_PAGE = "staffListOrder.jsp";
        public static final String STAFF_ORDER_DETAIL_PAGE = "staffOrderDetail.jsp";
        public static final String USER_ORDERS_HISTORY_PAGE = "userOrdersHistory.jsp";
        public static final String USER_ORDER_DETAIL_PAGE = "userOrderDetail.jsp";
    }
    
    public static class Controller {
        public static final String START_UP_CONTROLLER = "StartUpServlet";
        public static final String SIGN_IN_CONTROLLER = "SignInServlet";
        public static final String SIGN_UP_CONTROLLER = "SignUpServlet";
        public static final String LOG_OUT_CONTROLLER = "LogoutServlet";
        public static final String STAFF_HOME_PAGE_CONTROLLER = "StaffHomePageServlet";
        public static final String STAFF_CREATE_PRODUCT_CONTROLLER = "StaffCreateProductServlet";
        public static final String STAFF_SEARCH_PRODUCT_CONTROLLER = "StaffSearchProductServlet";
        public static final String STAFF_UPDATE_PRODUCT_CONTROLLER = "StaffUpdateProductServlet";
        public static final String STAFF_SHOW_USERS_CONTROLLER = "StaffShowUsersServlet";
        public static final String STAFF_UPDATE_USER_CONTROLLER = "StaffUpdateUserServlet";
        public static final String STAFF_SEARCH_USER_CONTROLLER = "StaffSearchUserServlet";
        public static final String STAFF_SHOW_ORDERS_CONTROLLER = "StaffShowOrdersServlet";
        public static final String STAFF_SEARCH_ORDERS_CONTROLLER = "StaffSearchOrderServlet";
        public static final String STAFF_DETAIL_ORDER_CONTROLLER = "StaffShowOrderDetailServlet";
        public static final String STAFF_FINISH_ORDER_CONTROLLER = "StaffFinishOrderServlet";
        
        public static final String USER_HOME_PAGE_CONTROLLER = "UserHomePageServlet";
        public static final String USER_PROFILE_CONTROLLER = "UserProfileServlet";
        public static final String USER_SEARCH_PRODUCT_CONTROLLER = "UserSearchProductServlet";
        public static final String USER_UPDATE_PROFILE_CONTROLLER = "UserUpdateProfileServlet";
        public static final String USER_ADD_TO_CART_CONTROLLER = "UserAddToCartServlet";
        public static final String USER_SHOW_CART_CONTROLLER = "UserShowCartServlet";
        public static final String USER_REMOVE_ITEMS_CART_CONTROLLER = "UserRemoveItemsCartServlet";
        public static final String USER_CHECK_OUT_CART_CONTROLLER = "UserCheckOutCartServlet";
        public static final String USER_ORDERS_HISTORY_CONTROLLER = "UserOrdersHistoryServlet";
        public static final String USER_ORDER_DETAIL_CONTROLLER = "UserOrderDetailServlet";
        
        
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
        public static final String USER_ORDERS_HISTORY = "UserOrdersHistory";
        public static final String USER_ORDER_DETAIL = "Detail";
        
        public static final String STAFF_SEARCH_USER = "SearchUser";
        public static final String STAFF_SHOW_PRODUCT = "StaffShowAllProduct";
        public static final String STAFF_SEARCH_PRODUCT = "StaffSearchProduct";
        public static final String STAFF_CREATE_PRODUCT = "CreateProduct";
        public static final String STAFF_UPDATE_PRODUCT = "UpdateProduct"; 
        public static final String STAFF_SHOW_USERS = "StaffShowUsers";
        public static final String STAFF_UPDATE_USER = "Delete User";
        public static final String STAFF_SHOW_ORDERS = "StaffShowOrders";
        public static final String STAFF_SEARCH_ORDER = "SearchOrder";
        public static final String STAFF_DETAIL_ORDER = "Order Detail";
        public static final String STAFF_FINISH_ORDER = "StaffFinishOrder";
        
    }
}
