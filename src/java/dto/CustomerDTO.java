/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class CustomerDTO implements Serializable{
    private int userID;
    private String address;
    private String phone;
    private AccountDTO account;

    public CustomerDTO() {
    }

    public CustomerDTO(int userID, String address, String phone, AccountDTO account) {
        this.userID = userID;
        this.address = address;
        this.phone = phone;
        this.account = account;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
    

    
    
    
}
