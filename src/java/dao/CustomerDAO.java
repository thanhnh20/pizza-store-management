/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccountDTO;
import dto.CustomerDTO;
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
public class CustomerDAO implements Serializable {

    private final String SQL_GET_CUSTOMER_BY_ACCOUNT_ID = "SELECT customerID, address, phone "
            + "FROM customers "
            + "WHERE accountID = ? ";
    private final String SQL_GET_CUSTOMER_BY_CUSTOMER_ID = "SELECT accountId, address, phone "
            + "FROM customers "
            + "WHERE customerID = ? ";

    private final String SQL_UPDATE_CUSTOMER = "UPDATE customers "
            + "SET address = ?, phone = ? "
            + "WHERE accountId = ? ";

    private final String SQL_GET_ALL_CUSTOMER = "SELECT * FROM customers ";

    private final String SQL_SEARCH_CUSTOMER_BY_NAME = "SELECT * from accounts WHERE username like ? AND roleId = ? ";

    public CustomerDTO getCustomerByAccountId(int accountID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO user = null;
        try {
            if (con == null) {
                con = DBHepler.makeConnection();
            }
            String sql = SQL_GET_CUSTOMER_BY_ACCOUNT_ID;
            stm = con.prepareStatement(sql);
            stm.setInt(1, accountID);
            rs = stm.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("customerID");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                AccountDAO accountDAO = new AccountDAO();
                AccountDTO account = accountDAO.getAccountByID(accountID);
                user = new CustomerDTO(customerId, address, phone, account);
            }
            return user;

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
    }

    public CustomerDTO getCustomerByCustomerId(int customerId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO user = null;
        try {
            if (con == null) {
                con = DBHepler.makeConnection();
            }
            String sql = SQL_GET_CUSTOMER_BY_CUSTOMER_ID;
            stm = con.prepareStatement(sql);
            stm.setInt(1, customerId);
            rs = stm.executeQuery();
            if (rs.next()) {
                int accountID = rs.getInt("accountID");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                AccountDAO accountDAO = new AccountDAO();
                AccountDTO account = accountDAO.getAccountByID(accountID);
                user = new CustomerDTO(customerId, address, phone, account);
            }
            return user;

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
    }

    public boolean updateProfile(String address, String phoneNumber, int accountId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_UPDATE_CUSTOMER;
                stm = con.prepareStatement(sql);
                stm.setString(1, address);
                stm.setString(2, phoneNumber);
                stm.setInt(3, accountId);

                int effectRow1 = stm.executeUpdate();
                if (effectRow1 > 0) {
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

    public List<CustomerDTO> getAllUser() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CustomerDTO> list = null;
        //Connecting to a database
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ALL_CUSTOMER;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                //load data into list
                //if userId and password are correct
                while (rs.next()) {
                    CustomerDTO user = new CustomerDTO();
                    user.setUserID(rs.getInt("customerID"));
                    user.setAddress(rs.getString("address"));
                    user.setPhone(rs.getString("phone"));
                    int accountId = rs.getInt("accountId");
                    AccountDAO d = new AccountDAO();
                    AccountDTO accountDTO = d.getAccountByID(accountId);
                    user.setAccount(accountDTO);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(user);
                }
            }
            //Creating and executing sql statements            
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
        //Closing the connection

        return list;
    }

    public List<CustomerDTO> search(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<CustomerDTO> list = null;
        try {
            //Connecting to a database
            con = DBHepler.makeConnection();
            //Creating and executing sql statements            
            String sql = SQL_SEARCH_CUSTOMER_BY_NAME;
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + username + "%");
            stm.setInt(2, 2);
            rs = stm.executeQuery();
            //load data into list
            //if userId and password are correct
            while (rs.next()) {
                int accountId = rs.getInt("accountId");
                CustomerDTO user = getCustomerByAccountId(accountId);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(user);
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
        //Closing the connection
        return list;
    }
}
