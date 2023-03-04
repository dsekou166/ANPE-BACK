package com.bezkoder.spring.login.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idossier;
    private String dossierannonce;
    private String dossierdemandeur;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="idadmin")
    private Admin admin;
}
