package com.bezkoder.spring.login.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RetourCandidature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_retour;
    private String decriptionretour;

    @ManyToOne
    @JoinColumn(name="id_recruteur")
    private Recruteur recruteur;

    @ManyToOne
    @JoinColumn(name="id_demandeur")
    private Demandeur demandeur;

}
