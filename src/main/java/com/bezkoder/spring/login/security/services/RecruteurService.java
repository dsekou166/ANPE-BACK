package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.models.User;

import java.util.List;

public interface RecruteurService {

    List<Recruteur> lister();

    Recruteur creer(Recruteur recruteur);

    Recruteur modifier(Recruteur recruteur, Long idrecruteur);

    String supprimer(Long idrecruteur);

//    String message (Recruteur recruteur);

    Recruteur recruteurParId(Long idrecruteur);
}
