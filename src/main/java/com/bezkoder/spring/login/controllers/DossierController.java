package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.Message.ReponseMessage;
import com.bezkoder.spring.login.img.SaveImage;
import com.bezkoder.spring.login.models.*;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.repository.AdminRepository;
import com.bezkoder.spring.login.repository.DossierRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Dossier")

public class DossierController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private DossierRepository dossierRepository;

    @PostMapping("/add/{idadmin}")

    public Object createdossier(
            @PathVariable("idadmin") Long idadmin,
            @RequestParam(value = "adossier", required = true) MultipartFile adossier,
            @RequestParam(value = "ddossier", required = true) MultipartFile ddossier) throws JsonProcessingException {



        Admin admin1 = adminRepository.findById(idadmin).get();
        admin1.setIdadmin(idadmin);
        Dossier dossier = new Dossier();
        dossier.setAdmin(admin1);
        dossier.setDossierannonce(SaveImage.save("Dossierannonce",adossier, adossier.getOriginalFilename()));
        dossier.setDossierdemandeur(SaveImage.save("Dossierdossier",ddossier, ddossier.getOriginalFilename()));
        dossierRepository.save(dossier);

        return ResponseEntity.ok(new MessageResponse("Dossiers ajouté avec succès"));

    }

    @GetMapping("/getdossiersbyid/{iddossier}")
    public Optional<Dossier> dossierbyid(@PathVariable Long iddossier) {
        return dossierRepository.findById(iddossier);
    }

    @GetMapping("/listdossier")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public List<Dossier> lister() {
        return dossierRepository.findAll();
    }

    @DeleteMapping("/deletedossier/{iddossier}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public String delete(@PathVariable Long iddossier) {
        dossierRepository.deleteById(iddossier);
        return "Dossiers supprimer avec success";
    }
}
