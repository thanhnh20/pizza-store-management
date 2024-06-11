/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CustomerDTO;
import dto.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import ultils.DBHepler;


/**
 *
 * @author ASUS
 */
public class OrderDAO implements Serializable{
    private final String SQL_GET_ORDER_BY_ID = "SELECT orderId, customerID, orderDate, shippedDate, amount, freight, shipAddress, status "
                        + "FROM orders "
                        + "WHERE orderId = ? ";
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
    
    public OrderDTO getOrderByOrderID(int orderID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try{
            con = DBHepler.makeConnection();
            if(con != null){
                String sql = SQL_GET_ORDER_BY_ID;
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID); 
                
                rs = stm.executeQuery();
                if(rs.next()){
                    
                    int customerId = rs.getInt("customerID");
                    Date orderDate = rs.getDate("orderDate");
                    Date shippedDate = rs.getDate("shippedDate");
                    int amount = rs.getInt("amount");
                    int freight = rs.getInt("freight");
                    String shipAddress = rs.getString("shipAddress");
                    int status = rs.getInt("status");
                    
                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerDTO customerDTO = customerDAO.getCustomerByCustomerId(customerId);
                    order = new OrderDTO(orderID, orderDate, shippedDate, amount, freight, status, status, customerDTO);
                }
                return order;
            }
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
        }
        return null;
    }
    
}
