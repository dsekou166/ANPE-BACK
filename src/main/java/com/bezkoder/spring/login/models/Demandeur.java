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
@NoArgsConstructor
public class Demandeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long iddemandeur;
    private String nomdemandeur;
    private String prenomdemandeur;
    private String adressedemandeur;
    private Integer agedemandeur;
    private String emaildemandeur;
    private String cv;
    private String retour;
    private String dossierdemandeur;
    private String profildemandeur;
    private String Photodemandeur;
    private String passworddemandeur;

    @JsonIgnore
    @OneToMany(mappedBy = "demandeur")
    private List<Commentaire> commentaire;

    @JsonIgnore
    @OneToMany(mappedBy = "demandeur")
    private List<RetourCandidature> retourCandidature;

    @JsonIgnore
    @ManyToOne
   private Annonce annonce;

    @JsonIgnore
    @ManyToMany()
   // private List<Notification> Notification;
    private List<Notification> notifications;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "demandeur_roles",
            joinColumns = @JoinColumn(name = "id_demandeur"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Demandeur(String nomDemandeur, String prenomDemandeur, String adresseDemandeur, Integer ageDemandeur, String emailDemandeur, String cv, String dossierDemandeur, String profilDemandeur, String photoDemandeur, String encode) {
        this.nomdemandeur = nomDemandeur;
        this.prenomdemandeur = prenomDemandeur;
        this.adressedemandeur = adresseDemandeur;
        this.agedemandeur = ageDemandeur;
        this.emaildemandeur = emailDemandeur;
        this.cv = cv;
        this.dossierdemandeur = dossierDemandeur;
        this.profildemandeur = profilDemandeur;
        this.passworddemandeur = adresseDemandeur;

    }

    /*public Demandeur(String Nom_demandeur, String Prenom_demandeur, String Adresse_demandeur,
                     Integer Age_demandeur, String Email_demandeur, String CV, String Dossier_demandeur,
                     String Profil_demandeur, String Password_demandeur) {
        this.NomDemandeur = Nom_demandeur;
        this.PrenomDemandeur = Prenom_demandeur;
        this.AdresseDemandeur = Adresse_demandeur;
        this.AgeDemandeur = Age_demandeur;
        this.EmailDemandeur = Email_demandeur;
        this.CV = CV;
        this.DossierDemandeur = Dossier_demandeur;
        this.ProfilDemandeur = Profil_demandeur;
        this.PasswordDemandeur = Password_demandeur;
    }*/
}
