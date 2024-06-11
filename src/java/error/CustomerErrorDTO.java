/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package error;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class CustomerErrorDTO implements Serializable{
    private String errorAddressLength;
    private String errorPhoneNumberLength;
    private String errorPhoneNumberFormat;

    public CustomerErrorDTO() {
    }

    public CustomerErrorDTO(String errorAddressLength, String errorPhoneNumberLength, String errorPhoneNumberFormat) {
        this.errorAddressLength = errorAddressLength;
        this.errorPhoneNumberLength = errorPhoneNumberLength;
        this.errorPhoneNumberFormat = errorPhoneNumberFormat;
    }

    public String getErrorAddressLength() {
        return errorAddressLength;
    }

    public void setErrorAddressLength(String errorAddressLength) {
        this.errorAddressLength = errorAddressLength;
    }

    public String getErrorPhoneNumberLength() {
        return errorPhoneNumberLength;
    }

    public void setErrorPhoneNumberLength(String errorPhoneNumberLength) {
        this.errorPhoneNumberLength = errorPhoneNumberLength;
    }


    public String getErrorPhoneNumberFormat() {
        return errorPhoneNumberFormat;
    }

    public void setErrorPhoneNumberFormat(String errorPhoneNumberFormat) {
        this.errorPhoneNumberFormat = errorPhoneNumberFormat;
    }
    
    
}
