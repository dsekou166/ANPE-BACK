package com.bezkoder.spring.login.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postuler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpostuler;
    @ManyToOne
    private Annonce annonce;

    @ManyToOne
    private Demandeur demandeur;
}
