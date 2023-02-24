package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Annonce;

import java.util.List;
import java.util.Optional;

public interface AnnonceService {
    List<Annonce> lister();

    Annonce creer(Annonce annonce,String nomposte);

    Annonce modifier(Annonce annonce, Long idannonce);

    String supprimer(Long idannonce);

    Optional<Annonce> annonceParId(Long idannonce);
}
