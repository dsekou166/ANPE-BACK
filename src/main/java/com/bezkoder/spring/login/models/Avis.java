package com.bezkoder.spring.login.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_avis;
    private String Description;

    @ManyToOne
    @JoinColumn(name="id_admin")
    private Admin admin;

    @JsonIgnore
    @OneToMany(mappedBy = "avis")
    private List<Commentaire> commentaire;

    @ManyToOne
    @JoinColumn(name="id_recruteur")
    private Recruteur recruteur;

}
