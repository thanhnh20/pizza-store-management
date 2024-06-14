/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CustomerDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import response.OrderResponseDTO;
import ultils.DBHepler;

/**
 *
 * @author ASUS
 */
public class OrderDAO implements Serializable {

    private final String SQL_GET_ORDER_BY_ID = "SELECT orderId, customerID, orderDate, shippedDate, amount, freight, shipAddress, status "
            + "FROM orders "
            + "WHERE orderId = ? ";

    private final String SQL_GET_ALL_ORDER = "SELECT orderId, customerID, orderDate, shippedDate, amount, freight, shipAddress, status "
            + "FROM orders ";

    private final String SQL_GET_ALL_ORDER_CUSTOMER = "SELECT orderId, customerID, orderDate, shippedDate, amount, freight, shipAddress, status "
            + "FROM orders "
            + "WHERE customerID = ? ";

    private final String SQL_GET_DETAIL_ORDER = "SELECT orderId, customerID, orderDate, shippedDate, amount, freight, shipAddress, status "
            + "FROM orders "
            + "WHERE orderId = ?";

    private final String SQL_STAFF_FINISH_ORDER = "Update orders Set "
            + "shippedDate = ?, "
            + "freight = ?, "
            + "shipAddress = ?, "
            + "status = ? "
            + "Where orderId = ?";

    private final String SQL_SEARCH_ORDER = "SELECT orderId, customerID, orderDate, shippedDate, amount, freight, shipAddress, status "
            + "FROM orders "
            + "WHERE orderId like ? ";

//    
//    public List<OrderDTO> getListOrderByUsername(String username) throws NamingException, SQLException{
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        List<OrderDTO> listOrder = null;
//        try{
//            con = DBHepler.makeConnection();
//            if(con != null){
//                String sql = "SELECT orderID, purchaseDate "
//                        + "FROM tblOrder "
//                        + "WHERE username = ? ";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, username);
//                rs = stm.executeQuery();
//                while(rs.next()){
//                    String orderID = rs.getString("orderID");
//                    Date purchaseDate = rs.getDate("purchaseDate");
//                    OrderDTO order = new OrderDTO();
//                    order.setOrderID(orderID);
//                    order.setPurchaseDate(purchaseDate);
//                    if(listOrder == null){
//                        listOrder = new ArrayList<TblOrderDTO>();
//                    }
//                    listOrder.add(order);
//                }
//                return listOrder;
//            }
//        }finally{
//            if(rs != null){
//                rs.close();
//            }
//            if(stm != null){
//                stm.close();
//            }
//            if(con != null){
//                con.close();
//            }
//        }
//        return null;
//    }
    public OrderDTO getOrderByOrderID(int orderID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ORDER_BY_ID;
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);

                rs = stm.executeQuery();
                if (rs.next()) {

                    int customerId = rs.getInt("customerID");
                    Date orderDate = rs.getDate("orderDate");
                    Date shippedDate = rs.getDate("shippedDate");
                    int amount = rs.getInt("amount");
                    int freight = rs.getInt("freight");
                    String shipAddress = rs.getString("shipAddress");
                    int status = rs.getInt("status");

                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(customerId);
                    order = new OrderDTO(orderID, orderDate, shippedDate, amount, freight, shipAddress, status, customerDTO);
                }
                return order;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public List<OrderDTO> getAllOrder() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> listOrder = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ALL_ORDER;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    int customerID = rs.getInt("customerID");
                    Date orderDate = rs.getDate("orderDate");
                    Date shippedDate = rs.getDate("shippedDate");
                    int amount = rs.getInt("amount");
                    int freight = rs.getInt("freight");
                    String shipAddress = rs.getString("shipAddress");
                    int status = rs.getInt("status");

                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(customerID);

                    OrderDTO order = new OrderDTO(orderID, orderDate, shippedDate, amount, freight, shipAddress, status, customerDTO);

                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    listOrder.add(order);
                }
                return listOrder;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public List<OrderDTO> getOrdersCustomer(int customerId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> listOrder = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ALL_ORDER_CUSTOMER;
                stm = con.prepareStatement(sql);
                stm.setInt(1, customerId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    int customerID = rs.getInt("customerID");
                    Date orderDate = rs.getDate("orderDate");
                    Date shippedDate = rs.getDate("shippedDate");
                    int amount = rs.getInt("amount");
                    int freight = rs.getInt("freight");
                    String shipAddress = rs.getString("shipAddress");
                    int status = rs.getInt("status");

                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(customerID);

                    OrderDTO order = new OrderDTO(orderID, orderDate, shippedDate, amount, freight, shipAddress, status, customerDTO);

                    if (listOrder == null) {
                        listOrder = new ArrayList<>();
                    }
                    listOrder.add(order);
                }
                return listOrder;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public List<OrderDTO> searchOrderByID(String id, String fromDate, String endDate) throws NamingException, SQLException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDTO> listOrder = new ArrayList<>();
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                //check  
                rs = filterSearchOrder(id, fromDate, endDate, stm, con);
                while (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    int customerID = rs.getInt("customerID");
                    Date orderDate = rs.getDate("orderDate");
                    Date shippedDate = rs.getDate("shippedDate");
                    int amount = rs.getInt("amount");
                    int freight = rs.getInt("freight");
                    String shipAddress = rs.getString("shipAddress");
                    int status = rs.getInt("status");

                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(customerID);

                    OrderDTO order = new OrderDTO(orderID, orderDate, shippedDate, amount, freight, shipAddress, status, customerDTO);
                    listOrder.add(order);
                }
                return listOrder;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return listOrder;
    }

    private ResultSet filterSearchOrder(String orderId, String fromDate, String endDate, PreparedStatement stm, Connection con) throws ParseException, SQLException {
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        String sql = SQL_SEARCH_ORDER;
        if (fromDate.isEmpty() && endDate.isEmpty()) {
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + orderId + "%");
            return stm.executeQuery();
        } else if (!fromDate.isEmpty() && endDate.isEmpty()) {
            Date dateFrom = new Date(formatter.parse(fromDate).getTime());
            sql = sql.concat("AND orderDate >= ? ");
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + orderId + "%");
            stm.setDate(2, dateFrom);
            return stm.executeQuery();
        } else if (fromDate.isEmpty() && !endDate.isEmpty()) {
            Date dateEnd = new Date(formatter.parse(endDate).getTime());
            sql = sql.concat("AND orderDate <= ? ");
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + orderId + "%");
            stm.setDate(2, dateEnd);
            return stm.executeQuery();
        } else if (!fromDate.isEmpty() && !endDate.isEmpty()) {
            Date dateFrom = new Date(formatter.parse(fromDate).getTime());
            Date dateEnd = new Date(formatter.parse(endDate).getTime());
            sql = sql.concat("AND orderDate >= ? AND orderDate <= ? ");
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + orderId + "%");
            stm.setDate(2, dateFrom);
            stm.setDate(3, dateEnd);
            return stm.executeQuery();
        }
        return null;
    }

    public OrderResponseDTO getDetailOrder(int orderId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_DETAIL_ORDER;
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("orderId");
                    int customerID = rs.getInt("customerID");
                    Date orderDate = rs.getDate("orderDate");
                    Date shippedDate = rs.getDate("shippedDate");
                    int amount = rs.getInt("amount");
                    int freight = rs.getInt("freight");
                    String shipAddress = rs.getString("shipAddress");
                    int status = rs.getInt("status");

                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(customerID);

                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    List<OrderDetailDTO> orderDetails = orderDetailDAO.getOrderDetailByOrderID(orderID);
                    OrderResponseDTO order = new OrderResponseDTO(orderID, orderDate, shippedDate, amount, freight, shipAddress, status, customerDTO.getAccount().getUsername(), customerDTO.getAccount().getFullName(), customerDTO.getPhone(), orderDetails);
                    return order;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean staffFinishOrder(int orderId, Date shippedDate, int freight, String shipAddress) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_STAFF_FINISH_ORDER;
                stm = con.prepareStatement(sql);
                stm.setDate(1, shippedDate);
                stm.setInt(2, freight);
                stm.setString(3, shipAddress);
                stm.setInt(4, 2);
                stm.setInt(5, orderId);
                int rowEff = stm.executeUpdate();
                if (rowEff > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
