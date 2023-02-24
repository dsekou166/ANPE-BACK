package com.bezkoder.spring.login.security.ServiceImpl;

import com.bezkoder.spring.login.Message.ReponseMessage;
import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.repository.AnnonceRepository;
import com.bezkoder.spring.login.security.services.AnnonceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnonceServiceImp implements AnnonceService {
    @Autowired
    AnnonceRepository annonceRepository;
    @Override
    public List<Annonce> lister() {

        return annonceRepository.findAll();
    }

    @Override
    public Annonce creer(Annonce annonce, String nomposte) {
            annonce.setNomposte(nomposte);
            return annonceRepository.save(annonce);

    }

    @Override
    public Annonce modifier(Annonce annonce, Long idannonce) {
      return  annonceRepository.findById(idannonce)
                .map(a ->{
                    a.setNomposte(annonce.getNomposte());
                    a.setTypecontrat(annonce.getTypecontrat());
                    a.setExperience(annonce.getExperience());
                    a.setLieu(annonce.getLieu());
                    a.setDatedebut(annonce.getDatedebut());
                    a.setDatefin(annonce.getDatefin());
                    a.setDossierannonce(annonce.getDossierannonce());
                    a.setDescriptionannonce(annonce.getDescriptionannonce());
                    a.setPhoto(annonce.getPhoto());
                    return annonceRepository.save(a);
                } ).orElseThrow(() -> new RuntimeException("Cette annonce n'existe pas !"));
    }


    @Override
    public String supprimer(Long idannonce) {
        annonceRepository.deleteById(idannonce);
        return "Annonce supprimer avec succès";
    }

    @Override
    public Optional<Annonce> annonceParId(Long idannonce) {
        return annonceRepository.findById(idannonce);
    }
}
