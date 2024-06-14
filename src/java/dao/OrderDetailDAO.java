/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.OrderDetailDTO;
import dto.ProductDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import ultils.DBHepler;

/**
 *
 * @author ASUS
 */
public class OrderDetailDAO implements Serializable {

    
    private final String SQL_INSERT_ORDER = "INSERT INTO orders(customerID, orderDate, amount, status ) "
                        + "VALUES(?, ?, ?, ?) ";
    
    private final String SQL_INSERT_ORDER_DETAILS = "INSERT INTO order_details(quantity, unitPrice, orderID, productID) "
                            + "VALUES(?, ?, ?, ?) ";
    
    private final String SQL_GET_OD_BY_ORDER_ID = "SELECT orderID, productID, quantity, unitPrice "
                        + "FROM order_details "
                        + "WHERE orderID = ? ";
    public boolean createOrderDetail(Date orderDate, int customerId, int amount, 
            List<ProductDTO> listProduct) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = SQL_INSERT_ORDER;
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setInt(1, customerId);
                stm.setDate(2, orderDate);
                stm.setInt(3, amount);    
                stm.setInt(4, 1);
                int effectRow1 = stm.executeUpdate();       
                ResultSet generatedKeys = stm.getGeneratedKeys();
                int orderId = 0;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
                
                int effectRow2 = 0;
                for (ProductDTO product : listProduct) {
                    sql = SQL_INSERT_ORDER_DETAILS;
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, product.getQuantity());
                    stm.setDouble(2, product.getPrice());
                    stm.setInt(3, orderId);
                    stm.setInt(4, product.getProductId());
                    effectRow2 = stm.executeUpdate();
                }
                
                con.commit();
                if(effectRow1 > 0 && effectRow2 > 0){
                    return true;
                }
            }
        }catch(SQLException ex){
            con.rollback();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
        return false;
    }
    public List<OrderDetailDTO> getOrderDetailByOrderID(int orderID) throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<OrderDetailDTO> listOrderDetail = null;
        try{
            con = DBHepler.makeConnection();
            if(con != null){
                String sql = SQL_GET_OD_BY_ORDER_ID;
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while(rs.next()){
                    int productID = rs.getInt("productID");
                    int quantity = rs.getInt("quantity");
                    int unitPrice = rs.getInt("unitPrice");
                    
                    ProductDAO productDAO = new ProductDAO();
                    ProductDTO productDTO =  productDAO.getProductById(productID);
                    
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderID, productDTO, quantity, unitPrice);
                    
                    if(listOrderDetail == null){
                        listOrderDetail = new ArrayList<>();
                    }
                    listOrderDetail.add(orderDetailDTO);
                }
                return listOrderDetail;
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
        return listOrderDetail;
    }
}
