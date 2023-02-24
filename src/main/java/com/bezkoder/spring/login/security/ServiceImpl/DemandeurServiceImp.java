package com.bezkoder.spring.login.security.ServiceImpl;

import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.repository.DemandeurRepository;
import com.bezkoder.spring.login.security.services.DemandeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DemandeurServiceImp implements DemandeurService {

    @Autowired
    DemandeurRepository demandeurRepository;
    @Override
    public List<Demandeur> lister() {
        return null;
    }

    @Override
    public Demandeur creer(Demandeur demandeur) {
        return null;
    }

    @Override
    public Demandeur modifier(Demandeur demandeur, Long iddemandeur) {
        return demandeurRepository.findById(iddemandeur).map(demandeur1 ->  {
            demandeur1.setNomdemandeur(demandeur.getNomdemandeur());
            demandeur1.setPrenomdemandeur(demandeur.getPrenomdemandeur());
            demandeur1.setAdressedemandeur(demandeur.getAdressedemandeur());
            demandeur1.setAgedemandeur(demandeur.getAgedemandeur());
            demandeur1.setEmaildemandeur(demandeur.getEmaildemandeur());
            demandeur1.setPassworddemandeur(demandeur.getPassworddemandeur());
            demandeur1.setRoles(demandeur.getRoles());

            if (demandeur1.getDossierdemandeur() == demandeur.getDossierdemandeur())
            {
                demandeur1.setDossierdemandeur(demandeur1.getDossierdemandeur());
            }else {
                demandeur1.setDossierdemandeur(demandeur.getDossierdemandeur());
            }
            if (demandeur1.getPhotodemandeur() == demandeur.getPhotodemandeur())
            {
                demandeur1.setPhotodemandeur(demandeur1.getPhotodemandeur());
            }else {
                demandeur1.setPhotodemandeur(demandeur.getPhotodemandeur());
            }

            if (demandeur1.getCv() == demandeur.getCv())
            {
                demandeur1.setCv(demandeur1.getCv());
            }else {
                demandeur1.setCv(demandeur.getCv());
            }

            demandeur1.setProfildemandeur(demandeur.getProfildemandeur());

            return demandeurRepository.save(demandeur1);

        }).orElseThrow(() -> new RuntimeException("Ce demandeur n'existe pas !"));
    }

    @Override
    public String supprimer(Long id_demandeur) {
        return null;
    }


}
