package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.Email.EmailConstructor;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.models.RetourCandidature;
import com.bezkoder.spring.login.repository.DemandeurRepository;
import com.bezkoder.spring.login.repository.RetourRepository;
import com.bezkoder.spring.login.security.services.DemandeurService;
import com.bezkoder.spring.login.security.services.RecruteurService;
import com.bezkoder.spring.login.security.services.RetourService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class RetourController {
    @Autowired
    EmailConstructor emailConstructor;

    private JavaMailSender mailSender;
    @Autowired
    RecruteurService recruteurService;
    @Autowired
    RetourService retourService;
    @Autowired
    DemandeurRepository demandeurRepository;


    @Autowired
    RetourRepository retourRepository;
    @PostMapping("/creer/{idrecruteur}/{iddem}")
        //@PreAuthorize("hasRole('APPRENANT')")
        //@PreAuthorize("hasRole('ADMIN')")
    RetourCandidature message(@RequestBody RetourCandidature retourCandidature, @PathVariable Long idrecruteur, @PathVariable Long iddem){
        Recruteur us = recruteurService.recruteurParId(idrecruteur);
        retourCandidature.setRecruteur(us);
        Demandeur demandeur = demandeurRepository.findDemandeurByIddemandeur(iddem);
        mailSender.send(emailConstructor.constructResetPasswordEmail(demandeur,retourCandidature));
        return retourRepository.save(retourCandidature);
    }
}
