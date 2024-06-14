/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

/**
 *
 * @author Admin
 */
public class ProductErrorDTO {
    
    private String productNameError;
    private String descriptionError;
    private String imagePathError;
    private String quantityError;
    private String priceError;
    private String statusError;
    private String messageError;

    public ProductErrorDTO() {
    }

    public ProductErrorDTO(String productNameError, String descriptionError, String imagePathError, String quantityError, String priceError, String statusError, String messageError) {
        this.productNameError = productNameError;
        this.descriptionError = descriptionError;
        this.imagePathError = imagePathError;
        this.quantityError = quantityError;
        this.priceError = priceError;
        this.statusError = statusError;
        this.messageError = messageError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getImagePathError() {
        return imagePathError;
    }

    public void setImagePathError(String imagePathError) {
        this.imagePathError = imagePathError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    

}
