package com.bezkoder.spring.login.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class LoReqDemandeur {

    //@NotBlank
    private String EmailDemandeur;

    //@NotBlank
    private String PasswordDemandeur;

   /* public String getEmailDemandeur() {
        return EmailDemandeur;
    }

    public void setEmailRecruteur(String emailRecruteur) {

        this.EmailDemandeur = emailRecruteur;
    }

    public String getPasswordDemandeur() {
        return PasswordDemandeur;
    }

    public void setPasswordRecruteur(String passwordRecruteur) {

        this.PasswordDemandeur = passwordRecruteur;
    }*/
}
