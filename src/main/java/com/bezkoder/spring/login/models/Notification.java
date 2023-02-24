package com.bezkoder.spring.login.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idnotifi;
    private String Message;
    private Date datenotif;

   @ManyToOne
    @JoinColumn(name="id_annonce")
    private Annonce annonce;
   //@JsonIgnore
   @ManyToMany()
   private List<Demandeur> demandeur;
}
