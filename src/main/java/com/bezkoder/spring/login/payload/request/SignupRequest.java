package com.bezkoder.spring.login.payload.request;

import lombok.*;

import java.util.Set;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupRequest {
    //@NotBlank
    @Size(min = 3, max = 20)
    private String nomadmin;
 
   // @NotBlank
    @Size(max = 50)

    private String prenomadmin;
    @Email
    private String emailadmin;

    private String photoadmin;
    private Set<String> role;
    
   // @NotBlank
    @Size(min = 6, max = 40)
    private String passwordadmin;
  
    /*public String getNom_admin() {
        return nom_admin;
    }
 
    public void setNom_admin(String nom_admin) {
        this.nom_admin = nom_admin;
    }
 
    public String getPrenom_admin() {
        return prenom_admin;
    }
 
    public void setPrenom_admin(String prenom_admin) {
        this.prenom_admin = prenom_admin;
    }
 
    public String getPassword_admin() {
        return password_admin;
    }
 
    public void setPassword_admin(String password_admin) {
        this.password_admin = password_admin;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public void setEmail_admin(String email_admin) {
        this.email_admin = email_admin;
    }

    public String getPhoto_admin() {
        return photo_admin;
    }

    public void setPhoto_admin(String photo_admin) {
        this.photo_admin = photo_admin;
    }*/


}
