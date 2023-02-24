package com.bezkoder.spring.login.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class LoReqRecruteur {

    //@NotBlank
    private String emailentreprise;

   // @NotBlank
    private String passwordentreprise;

   /* public String getEmailRecruteur() {
        return EmailRecruteur;
    }

    public void setEmailRecruteur(String emailRecruteur) {

        this.EmailRecruteur = emailRecruteur;
    }

    public String getPasswordRecruteur() {
        return PasswordRecruteur;
    }

    public void setPasswordRecruteur(String passwordRecruteur) {

        this.PasswordRecruteur = passwordRecruteur;
    }*/
}

