package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Commentaire;
import com.bezkoder.spring.login.models.User;

import java.util.List;

public interface CommentaireService {

    List<Commentaire> lister();

    Commentaire creer(Commentaire commentaire);

    Commentaire modifier(Commentaire commentaire, Long id_commentaire);

    String supprimer(Long id_commentaire);
}
