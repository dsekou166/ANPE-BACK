package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Demandeur;

import java.util.List;
import java.util.Optional;

public interface DemandeurService {

    List<Demandeur> lister();

    Demandeur creer(Demandeur demandeur);

    Demandeur modifier(Demandeur demandeur, Long iddemandeur);

    String supprimer(Long id_demandeur);


}
