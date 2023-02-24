package com.bezkoder.spring.login.security.ServiceImpl;

import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.repository.RecruteurRepository;
import com.bezkoder.spring.login.repository.RetourRepository;
import com.bezkoder.spring.login.security.services.RecruteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecrteurServiceImp implements RecruteurService {
    @Autowired
    RecruteurRepository recruteurRepository;
    @Autowired
    private RetourRepository retourRepository;

    @Override
    public List<Recruteur> lister() {
        return null;
    }

    @Override
    public Recruteur creer(Recruteur recruteur) {
        return null;
    }

    @Override
    public Recruteur modifier(Recruteur recruteur, Long idrecruteur) {
        return recruteurRepository.findById(idrecruteur).map(recruteur1 -> {
            recruteur1.setNomentreprise(recruteur.getNomentreprise());
            recruteur1.setEmailentreprise(recruteur.getEmailentreprise());
            recruteur1.setAdresseentreprise(recruteur.getAdresseentreprise());
            recruteur1.setSecteur(recruteur.getSecteur());
            recruteur1.setPasswordentreprise(recruteur.getPasswordentreprise());
            recruteur1.setRoles(recruteur.getRoles());
            recruteur1.setMessage(recruteur.getMessage());
            if (recruteur1.getPhotoentreprise() == recruteur.getPhotoentreprise())
            {
                recruteur1.setPhotoentreprise(recruteur1.getPhotoentreprise());
            }else {
                recruteur1.setPhotoentreprise(recruteur.getPhotoentreprise());
            }
            return recruteurRepository.save(recruteur1);
        }).orElseThrow(() -> new RuntimeException("Ce demandeur n'existe pas !"));
    }

    @Override
    public String supprimer(Long idrecruteur) {
        recruteurRepository.deleteById(idrecruteur);
        return "Recruteur supprimé avec succès";
    }

    @Override
    public Recruteur recruteurParId(Long idrecruteur) {
         return recruteurRepository.findById(idrecruteur).get();
    }
}
