package com.bezkoder.spring.login.security.ServiceImpl;

import com.bezkoder.spring.login.models.Admin;
import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Dossier;
import com.bezkoder.spring.login.repository.DossierRepository;
import com.bezkoder.spring.login.security.services.DossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor


public class DossierServiceImp implements DossierService {
    @Autowired
    DossierRepository dossierRepository;

    @Override
    public List<Dossier> lister() {
        return null;
    }

    @Override
    public Dossier creer(Dossier dossier) {
        return dossierRepository.save(dossier);}

    @Override
    public Dossier modifier(Dossier dossier, Long iddossier) {
        return null;
    }

    @Override
    public String supprimer(Long iddossier) {
        return null;
    }
}
