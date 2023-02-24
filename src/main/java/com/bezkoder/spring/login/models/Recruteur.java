package com.bezkoder.spring.login.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Recruteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idrecruteur;
    private String nomentreprise;
    private String message;
    private String secteur;
    private String photoentreprise;
    private String adresseentreprise;
    private String emailentreprise;
    private String passwordentreprise;

    @ManyToOne
    @JoinColumn(name="id_admin")
    private Admin admin;

    @JsonIgnore
    @OneToMany(mappedBy = "recruteur")
    private List<Avis> avis;

    @JsonIgnore
    @OneToMany(mappedBy = "recruteur")
    private List<RetourCandidature> retourCandidature;


    @OneToMany(mappedBy = "recruteur")
    private List<Annonce> annonce;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recruteur_roles",
            joinColumns = @JoinColumn(name = "id_recruteur"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Recruteur() {
    }

    public Recruteur(String Nom_entreprise, String Secteur, String Adresse_entreprise, String Photo_entreprise, String Email_entreprise, String Password_entreprise) {
        this.nomentreprise = Nom_entreprise;
        this.secteur = Secteur;
        this.adresseentreprise = Adresse_entreprise;
        this.emailentreprise = Email_entreprise;
        this.passwordentreprise = Password_entreprise;
        this.photoentreprise = Photo_entreprise;

    }

   /* public Recruteur(String message) {
        this.message = message;

    }*/
}
