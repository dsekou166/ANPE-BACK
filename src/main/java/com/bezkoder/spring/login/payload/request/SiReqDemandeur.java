package com.bezkoder.spring.login.payload.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SiReqDemandeur {

    @Size(min = 3, max = 20)
    private String nomDemandeur;

    @Size(max = 50)
    private String prenomDemandeur;

    private String adresseDemandeur;

    private Integer ageDemandeur;
    @Email
    private String emailDemandeur;

    private String cv;

    private String dossierDemandeur;

    private String profilDemandeur;

    private String photoDemandeur;


    //@NotBlank
    @Size(min = 6, max = 40)
    private String passwordDemandeur;
    private Set<String> role;


   /* public String getNomDemandeur() {
        return NomDemandeur;
    }

    public void setNomDemandeur(String nomDemandeur) {
        this.NomDemandeur = nomDemandeur;
    }

    public String getPrenomDemandeur() {
        return PrenomDemandeur;
    }

    public void setPrenomDemandeur(String prenomDemandeur) {
        this.NomDemandeur = prenomDemandeur;
    }

    public String getAdresseDemandeur() {
        return AdresseDemandeur;
    }

    public void setAdresseDemandeur(String adresseDemandeur) {
        this.AdresseDemandeur = adresseDemandeur;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public Integer getAgeDemandeur() {
        return AgeDemandeur;
    }

    public void setAgeDemandeur(Integer ageDemandeur) {
        this.AgeDemandeur = ageDemandeur;
    }

    public String getEmailDemandeur() {
        return EmailDemandeur;
    }

    public void setEmailDemandeur(String emailDemandeur) {
        this.EmailDemandeur = emailDemandeur;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String cv) {
        this.CV = cv;
    }

    public String getDossierDemandeur() {
        return DossierDemandeur;
    }

    public void setDossierDemandeur(String dossierDemandeur) {
        this.DossierDemandeur = dossierDemandeur;
    }

    public String getProfilDemandeur() {
        return ProfilDemandeur;
    }

    public void setProfilDemandeur(String profilDemandeur) {
        this.ProfilDemandeur = profilDemandeur;
    }

    public String getPhotoDemandeur() {
        return PhotoDemandeur;
    }

    public void setPhotoDemandeur(String photoDemandeur) {
        this.PhotoDemandeur = photoDemandeur;
    }

    public String getPasswordDemandeur() {
        return PasswordDemandeur;
    }

    public void setPasswordDemandeur(String passwordDemandeur) {
        this.PasswordDemandeur = passwordDemandeur;
    }*/


}
