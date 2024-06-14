/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package response;

import dto.CustomerDTO;
import dto.OrderDetailDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class OrderResponseDTO {
    private int orderId;
    private Date orderDate;
    private Date shippedDate;
    private int amount;
    private int freight;
    private String shipAddress;
    private int status;
    private String username;
    private String fullName;
    private String phoneNumber;
    private List<OrderDetailDTO> orderDetails;

    public OrderResponseDTO(int orderId, Date orderDate, Date shippedDate, int amount, int freight, String shipAddress, int status, String username, String fullName, String phoneNumber, List<OrderDetailDTO> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.amount = amount;
        this.freight = freight;
        this.shipAddress = shipAddress;
        this.status = status;
        this.username = username;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    
    
}
