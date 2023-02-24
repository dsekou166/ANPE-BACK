package com.bezkoder.spring.login.security.ServiceImpl;

import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.models.RetourCandidature;
import com.bezkoder.spring.login.repository.RetourRepository;
import com.bezkoder.spring.login.security.services.RetourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RetourServiceImp implements RetourService {
    @Autowired
    RetourRepository retourRepository;
    @Override
    public List<RetourCandidature> lister() {
        return null;
    }

    @Override
    public RetourCandidature creer(RetourCandidature retourCandidature) {
        return null;
    }

    @Override
    public RetourCandidature modifier(RetourCandidature retourCandidature, Long id_retour) {
        return null;
    }

    @Override
    public String supprimer(Long id_retour) {
        return null;
    }

    @Override
    public RetourCandidature message(RetourCandidature retourCandidature) {

       return retourRepository.save(retourCandidature);
        // return "Votre demande a été prise en compte";

    }


}
