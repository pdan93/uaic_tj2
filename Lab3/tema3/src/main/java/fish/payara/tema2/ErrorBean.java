/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.tema2;

import java.io.Serializable;

/**
 *
 * @author x
 */
public class ErrorBean implements Serializable {
    private String errorMessage="null";
    private Boolean isSet=false;

    public Boolean getIsSet() {
        return isSet;
    }

    public void setIsSet(Boolean isSet) {
        this.isSet = isSet;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        isSet=true;
    }
    
}
