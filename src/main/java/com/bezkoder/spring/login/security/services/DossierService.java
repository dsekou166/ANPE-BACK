package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Dossier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DossierService {

    List<Dossier> lister();

    Dossier creer(Dossier dossier);

    Dossier modifier(Dossier dossier, Long iddossier);

    String supprimer(Long iddossier);
}
