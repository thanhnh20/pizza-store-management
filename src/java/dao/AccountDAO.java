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
                        + "WHERE username = ? AND password = ? AND status = ?";
    
    private final String SQL_GET_ACCOUNT_BY_USERNAME = "SELECT accountId, username, password, fullName, roleId, status "
                        + "FROM accounts "
                        + "WHERE username = ? ";
    
    private final String SQL_GET_ACCOUNT_BY_ID = "SELECT accountId, username, password, fullName, roleId, status "
                        + "FROM accounts "
                        + "WHERE accountId = ? ";
    
    private final String SQL_CHECK_ACCOUNT_BY_USERNAME = "SELECT username "
                        + "FROM accounts "
                        + "WHERE username = ?";
    
    private final String SQL_INSERT_ACCOUNT = "INSERT into accounts(username, password, fullName, roleId, status)"
                        + " VALUES(?, ?, ?, ?, ?) ";
    
    private final String SQL_INSERT_CUSTOMER = "INSERT INTO customers(accountId)"
                        + " VALUES(?) ";
    
    private final String SQL_UPDATE_USER = "Update accounts Set "
            + "status = ? "
            + "Where accountId = ?";

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
                stm.setBoolean(3, true);
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
    
    public AccountDTO getAccountByID(int accountId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO accountDTO = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_GET_ACCOUNT_BY_ID;
                stm = con.prepareStatement(sql);
                stm.setInt(1, accountId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("username");
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

    public boolean createAcount(AccountDTO account) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                con.setAutoCommit(false);
                String sql = SQL_INSERT_ACCOUNT;
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setInt(4, account.getRole());
                stm.setBoolean(5, true);
                int effectRow1 = stm.executeUpdate();
                
                AccountDTO accountCreated = null;
                sql = SQL_GET_ACCOUNT_BY_USERNAME;
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                rs = stm.executeQuery();
                if (rs.next()) {
                    int accountId = rs.getInt("accountId");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    int role = rs.getInt("roleId");
                    boolean status = rs.getBoolean("status");
                    accountCreated = new AccountDTO(accountId, account.getUsername(), password, fullName, role, status);
                }
                
                if(accountCreated != null){
                    sql = SQL_INSERT_CUSTOMER;
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, accountCreated.getAccountId());
                    int effectRow2 = stm.executeUpdate();

                    con.commit();
                    if(effectRow1 > 0 && effectRow2 > 0){
                        return true;
                    }
                }
            }
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

    public boolean checkAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_CHECK_ACCOUNT_BY_USERNAME;
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
    
    public boolean updateStatusAccount(int accountId, boolean status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHepler.makeConnection();
            if (con != null) {
                String sql = SQL_UPDATE_USER;
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setInt(2, accountId);
                int rowEff = stm.executeUpdate();
                
                if(rowEff > 0){
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
