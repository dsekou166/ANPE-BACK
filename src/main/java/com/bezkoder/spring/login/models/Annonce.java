package com.bezkoder.spring.login.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idannonce;
    private String nomposte;
    private String typecontrat;
    private String experience;
    private String lieu;
    private Date datedebut;
    private Date datefin;
    private String dossierannonce;
    private String descriptionannonce;
    private String photo;
    private String profil;


   @ManyToOne
    @JoinColumn(name="id_demandeur")
    private Demandeur demandeur;


    @JsonIgnore
    @OneToMany(mappedBy = "annonce")
    private List<Notification> notification;

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name="idrecruteur")
    private Recruteur recruteur;

}
