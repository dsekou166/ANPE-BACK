package com.bezkoder.spring.login.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idadmin;
    private String nomadmin;
    private String prenomadmin;
    private String emailadmin;
    private String photoadmin;
    private String passwordadmin;

    @OneToMany(mappedBy = "admin")
    private List<Recruteur> recruteur;
    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private List<Avis> avis;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "admin_roles",
            joinColumns = @JoinColumn(name = "idAdmin"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    public Admin ( String nomAdmin, String prenomAdmin, String emailAdmin,String photoAdmin, String passwordAdmin){

        this.nomadmin = nomAdmin;
        this.prenomadmin = prenomAdmin;
        this.emailadmin = emailAdmin;
        this.photoadmin = photoAdmin;
        this.passwordadmin = passwordAdmin;


    }


    public Admin(String passwordAdmin) {
        this.passwordadmin=passwordAdmin;
    }


}
