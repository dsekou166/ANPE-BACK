package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.models.RetourCandidature;
import com.bezkoder.spring.login.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RetourService {
    List<RetourCandidature> lister();

    RetourCandidature creer(RetourCandidature retourCandidature);

    RetourCandidature modifier(RetourCandidature retourCandidature, Long id_retour);

    String supprimer(Long id_retour);
    RetourCandidature  message (RetourCandidature retourCandidature);
}
