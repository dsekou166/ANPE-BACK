package com.bezkoder.spring.login.payload.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SiReqRecruteur {


    @Size(min = 3, max = 20)
    private String nomentreprise;

    @Size(max = 50)

    private String secteur;
    @Email
    private String emailentreprise;

    private String adresseentreprise;

    private String photoentreprise;
    //@NotBlank
    @Size(min = 6, max = 40)
    private String passwordentreprise;
    private Set<String> role;


    /*public String getNomEntreprise() {
        return NomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.NomEntreprise = nomEntreprise;
    }

    public String getSecteur() {
        return Secteur;
    }

    public void setSecteur(String secteur) {
        this.Secteur = secteur;
    }

    public String getAdresseEntreprise() {
        return AdresseEntreprise;
    }

    public void setAdresseEntreprise(String adresseEntreprise) {
        this.AdresseEntreprise = adresseEntreprise;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getEmailEntreprise() {
        return EmailEntreprise;
    }

    public void setEmailEntreprise(String emailEntreprise) {
        this.EmailEntreprise = emailEntreprise;
    }

    public String getPhotoEntreprise() {
        return PhotoEntreprise;
    }

    public void setPhotoEntreprise(String photoEntreprise) {
        this.PhotoEntreprise = photoEntreprise;
    }

    public String getPasswordEntreprise() {
        return PasswordEntreprise;
    }

    public void setPasswordEntreprise(String passwordEntreprise) {
        this.PasswordEntreprise = passwordEntreprise;
    }*/



}
