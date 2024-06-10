/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccountDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import ultils.DBHepler;

/**
 *
 * @author ASUS
 */
public class AccountDAO implements Serializable {
    private final String SQL_CHECK_LOGIN = "SELECT username "
                        + "FROM accounts "
                        + "WHERE username = ? AND password = ? ";
    
    private final String SQL_GET_ACCOUNT_BY_USERNAME = "SELECT accountId, username, password, fullName, roleId, status "
                        + "FROM accounts "
                        + "WHERE username = ? ";

    //check login account with username and password
    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_CHECK_LOGIN;
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }

    //get account by username
    public AccountDTO getAccount(String username) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO accountDTO = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ACCOUNT_BY_USERNAME;
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int accountId = rs.getInt("accountId");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("roleId");
                    boolean status = rs.getBoolean("status");
                    accountDTO = new AccountDTO(accountId, username, password, fullName, role, status);
                }
                return accountDTO;
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

//    public boolean createAcount(AccountDTO account) throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                con.setAutoCommit(false);
//                String sql = "INSERT into TblAccount(username, password, fullName, isRole)"
//                        + " VALUES(?, ?, ?, ?) ";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, account.getUsername());
//                stm.setString(2, account.getPassword());
//                stm.setString(3, account.getFullName());
//                stm.setBoolean(4, account.isRole());
//                int effectRow1 = stm.executeUpdate();
//                
//                sql = "INSERT INTO tblUser(username)"
//                        + " VALUES(?) ";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, account.getUsername());
//                int effectRow2 = stm.executeUpdate();
//                
//                con.commit();
//                if(effectRow1 > 0 && effectRow2 > 0){
//                    return true;
//                }
//            }
//        } finally {
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.setAutoCommit(true);
//                con.close();              
//            }
//        }
//        return false;
//    }

//    public boolean checkAccount(String username) throws SQLException, NamingException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        try {
//            con = DBHepler.makeConnection();
//            if (con != null) {
//                String sql = "SELECT username "
//                        + "FROM accounts "
//                        + "WHERE username = ?";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, username);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    return true;
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return false;
//    }
}
