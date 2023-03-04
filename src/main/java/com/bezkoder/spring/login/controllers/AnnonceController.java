package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.Message.ReponseMessage;
import com.bezkoder.spring.login.img.SaveImage;
import com.bezkoder.spring.login.models.*;
import com.bezkoder.spring.login.repository.*;
import com.bezkoder.spring.login.security.ServiceImpl.AnnonceServiceImp;
import com.bezkoder.spring.login.security.services.AnnonceService;
import com.bezkoder.spring.login.security.services.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
//import jdk.internal.loader.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:8100/", "http://localhost:8101/","http://localhost:4200/"}, maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/Annonce")
public class AnnonceController {

    @Autowired
    NotificationService notificationService;
    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private DemandeurRepository demandeurRepository;
    @Autowired
    private AnnonceServiceImp annonceServiceImp;
    @Autowired
    private RecruteurRepository recruteurRepository;


    @DeleteMapping("/delete/{idannonce}")
   // @PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public String delete(@PathVariable("idannonce") Long idannonce) {
        annonceRepository.deleteById(idannonce);
        return "Utilisateur supprimer avec success";
    }

   /* @PutMapping("/modifier/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public Annonce update(@RequestBody Annonce annonce, @PathVariable Long idannonce) {

        return annonceService.modifier(annonce, idannonce);
    }*/

    @GetMapping("/list")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public List<Annonce> lister() {
        return annonceRepository.findAll();
    }


    @PutMapping("/update/{idannonce}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public Object updateannonce(
            // @PathVariable("recruteur") Recruteur recruteur,
            @PathVariable Long idannonce,
            @RequestParam(value = "annonce") String annonce,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "dossier", required = false) MultipartFile dossier) throws JsonProcessingException {
        // Optional<Recruteur> recruteur1 = recruteurRepository.findById(recruteur.getIdrecruteur());
        Annonce annonce1 = new JsonMapper().readValue(annonce, Annonce.class);
        //Optional<Annonce> annonce2 = annonceRepository.findById(idannonce);
        //annonce2.get().setNomposte(annonce);
        if (file != null) {

            annonce1.setPhoto(SaveImage.save("Annonce", file, annonce1.getNomposte()));
            annonce1.setDossierannonce(SaveImage.save("DossierAnnonce", dossier, dossier.getOriginalFilename()));

            //   Annonce annonce2 =annonceService.creer(annonce1,nomposte);

            // appel de la méthode générant les notifications
            annonceServiceImp.modifier(annonce1,idannonce);

            return annonce1;
        } else {

            ReponseMessage message = new ReponseMessage("error", false, "fichiervide ");


            return message;
        }

    }
    @PostMapping("/add/{nomposte}/{idrecruteur}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public Object createannonce(
            @PathVariable("idrecruteur") Long idrecruteur,
            @PathVariable String nomposte,
            @RequestParam(value = "annonce", required = true) String annonce,
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "dossier", required = true) MultipartFile dossier) throws JsonProcessingException {

        Annonce annonce1 = new JsonMapper().readValue(annonce, Annonce.class);
        Recruteur recruteur1 = new Recruteur();

       Recruteur recruteur2 = recruteurRepository.findById(idrecruteur).get();
        recruteur1.setIdrecruteur(idrecruteur);
        if (file != null) {
            annonce1.setRecruteur(recruteur2);
            annonce1.setPhoto(SaveImage.save("Annonce", file, nomposte));
            annonce1.setDossierannonce(SaveImage.save("DossierAnnonce", dossier, dossier.getOriginalFilename()));

            //   Annonce annonce2 =annonceService.creer(annonce1,nomposte);

            // appel de la méthode générant les notifications
            this.notificationService.generateNotificationByType(annonceService.creer(annonce1, nomposte));

            return annonce1;
        } else {

            ReponseMessage message = new ReponseMessage("error", false, "fichiervide ");


            return message;
        }

    }
    @GetMapping("/getannoncebyid/{idannonce}")
    public Optional<Annonce> annoncebyid(@PathVariable Long idannonce) {
       return annonceRepository.findById(idannonce);
    }

    @GetMapping("/getannoncebyidrecruteur/{idrecruteur}")
    public List<Annonce> annoncebyidrecruteur(@PathVariable Long idrecruteur) {
        Recruteur rec = recruteurRepository.findById(idrecruteur).get();
        return annonceRepository.findByRecruteur(rec);
    }


}
