/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author ASUS
 */
public class OrderDetailErrorDTO {
    private String outOfQuantityError;
    private String invalidQuantity;
    private String quantityOutOfStockError;

    public OrderDetailErrorDTO() {
    }   
    
    public OrderDetailErrorDTO(String outOfQuantityError, String invalidQuantity, String quantityOutOfStockError) {
        this.outOfQuantityError = outOfQuantityError;
        this.invalidQuantity = invalidQuantity;
        this.quantityOutOfStockError = quantityOutOfStockError;
    }

    public String getOutOfQuantityError() {
        return outOfQuantityError;
    }

    public void setOutOfQuantityError(String outOfQuantityError) {
        this.outOfQuantityError = outOfQuantityError;
    }

    public String getInvalidQuantity() {
        return invalidQuantity;
    }

    public void setInvalidQuantity(String invalidQuantity) {
        this.invalidQuantity = invalidQuantity;
    }

    public String getQuantityOutOfStockError() {
        return quantityOutOfStockError;
    }

    public void setQuantityOutOfStockError(String quantityOutOfStockError) {
        this.quantityOutOfStockError = quantityOutOfStockError;
    }
    
    
}
