package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Postuler;
import com.bezkoder.spring.login.repository.AnnonceRepository;
import com.bezkoder.spring.login.repository.DemandeurRepository;
import com.bezkoder.spring.login.repository.PostulerRepository;
import com.bezkoder.spring.login.security.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/postuler")
public class PostulerController {
    @Autowired
    private PostulerRepository postulerRepository;

    @Autowired
    private DemandeurRepository demandeurRepository;

    @Autowired
    AnnonceService annonceService;
    @Autowired
    private AnnonceRepository annonceRepository;

    @PostMapping("/{iddemandeur}/{idannonce}")
    public ResponseEntity<Object> postuler(@PathVariable Long iddemandeur, @PathVariable Long idannonce) {
        Demandeur demandeur = demandeurRepository.findById(iddemandeur).orElseThrow(() -> new NotFoundException("Demandeur non trouve avec id " + iddemandeur));
        Annonce annonce = annonceRepository.findById(idannonce).orElseThrow(() -> new NotFoundException("Annonce non trouvé avec id " + idannonce));

        if (postulerRepository.existsByDemandeurAndAnnonce(demandeur,annonce)) {
            //throw new RuntimeException("Cet utilisteur a dejà postuler");
            return ResponseEntity.ok().body("Cet utilisteur a dejà postuler");
        } else {
            Postuler postuler = new Postuler();
            postuler.setDemandeur(demandeur);
            postuler.setAnnonce(annonce);

            Postuler result = postulerRepository.save(postuler);

            return ResponseEntity.ok().body(result);
        }

    }

    @GetMapping("/{annonce}")
    public ResponseEntity<List<Demandeur>> getApplicantsForJob(@PathVariable("annonce") Annonce annonce) {
        Optional<Annonce> annonces = annonceRepository.findById(annonce.getIdannonce());

        if (!annonces.isPresent()) {

           return ResponseEntity.notFound().build();
        }

         List<Postuler> postulers = postulerRepository.findByAnnonce(annonce);
         List<Demandeur> demandeurs = new ArrayList<>();

         for(Postuler p : postulers){
             demandeurs.add(p.getDemandeur());
         }

        if (demandeurs.size() == 0) {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(demandeurs);
    }

}
