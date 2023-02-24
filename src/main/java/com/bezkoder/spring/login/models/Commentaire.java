package com.bezkoder.spring.login.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_commentaire;
    private String Description_comment;

    @ManyToOne
    @JoinColumn(name="id_avis")
    private Avis avis;

    @ManyToOne
    @JoinColumn(name="id_demandeur")
    private Demandeur demandeur;
}
