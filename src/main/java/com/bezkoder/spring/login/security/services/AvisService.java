package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Avis;
import com.bezkoder.spring.login.models.User;

import java.util.List;

public interface AvisService {

    List<Avis> lister();

    Avis creer(Avis avis);

    Avis modifier(Avis avis, Long id_avis);

    String supprimer(Long id);
}
