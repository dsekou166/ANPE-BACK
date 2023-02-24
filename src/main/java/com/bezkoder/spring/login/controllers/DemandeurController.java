package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.img.SaveImage;
import com.bezkoder.spring.login.models.*;
import com.bezkoder.spring.login.payload.request.LoReqDemandeur;
import com.bezkoder.spring.login.payload.request.SiReqDemandeur;
import com.bezkoder.spring.login.payload.request.SignupRequest;
import com.bezkoder.spring.login.payload.response.JwtResponse;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.repository.DemandeurRepository;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.security.ServiceImpl.DemandeurServiceImp;
import com.bezkoder.spring.login.security.jwt.JwtUtils;
import com.bezkoder.spring.login.security.services.DemandeurDetailImpl;
import com.bezkoder.spring.login.security.services.UserDetailsImpl;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:8100/", "http://localhost:8101/","http://localhost:4200/"}, maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class  DemandeurController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    DemandeurRepository demandeurRepository;

    @Autowired
    DemandeurServiceImp demandeurServiceImp;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin/Demandeur")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoReqDemandeur loReqDemandeur) {


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loReqDemandeur.getEmailDemandeur(), loReqDemandeur.getPasswordDemandeur()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
                System.out.println(roles);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(jwtUtils.generateTokenFromUsername(userDetails.getEmail()), userDetails.getId(),

                        userDetails.getEmail(),
                        roles));
    }


    @PostMapping("/signup/Demandeur")
    public ResponseEntity<?> registerDemandeur(@RequestParam("demandeur") String siReqDemandeur, @RequestParam("image") MultipartFile image, @RequestParam("cv") MultipartFile cv, @RequestParam("dossierdemandeur") MultipartFile dossierdemandeur) {
        try {

            SiReqDemandeur siReqDemandeur1 = new JsonMapper().readValue(siReqDemandeur, SiReqDemandeur.class);
            if (demandeurRepository.existsByEmaildemandeur(siReqDemandeur1.getEmailDemandeur())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: cet email existe deja"));
            }else {
                Demandeur demandeur = new Demandeur();
                Set<String> strRoles = siReqDemandeur1.getRole();
                Set<Role> roles = new HashSet<>();
                if (strRoles == null) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Erreur: Impossible de trouver le rôle!!"));
                    roles.add(userRole);
                } else {
                    strRoles.forEach(role -> {
                        switch (role) {
                            case "admin":
                                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(adminRole);

                                break;
                            case "mod":
                                Role modRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(modRole);

                                break;
                            default:
                                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);
                        }
                    });

                }
                demandeur.setRoles(roles);
                demandeur.setNomdemandeur(siReqDemandeur1.getNomDemandeur());
                demandeur.setPrenomdemandeur(siReqDemandeur1.getPrenomDemandeur());
                demandeur.setAdressedemandeur(siReqDemandeur1.getAdresseDemandeur());
                demandeur.setAgedemandeur(siReqDemandeur1.getAgeDemandeur());
                demandeur.setEmaildemandeur(siReqDemandeur1.getEmailDemandeur());
                demandeur.setCv(SaveImage.save("DemandeurCV",cv, cv.getOriginalFilename()));
                demandeur.setDossierdemandeur(SaveImage.save("DemandeurDossier",dossierdemandeur, dossierdemandeur.getOriginalFilename()));
                demandeur.setProfildemandeur(siReqDemandeur1.getProfilDemandeur());
                demandeur.setPhotodemandeur(SaveImage.save("Demandeur",image, image.getOriginalFilename()));
                demandeur.setPassworddemandeur(encoder.encode(siReqDemandeur1.getPasswordDemandeur()));
                demandeurRepository.save(demandeur);

                return ResponseEntity.ok(new MessageResponse("Demandeur ajouter avec succes"));
            }

        }catch (Exception e){
            return null;
        }

    }


    @PostMapping("/signout/Demandeur")
    public ResponseEntity<?> logoutAdmin() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Vous vous êtes deconnecter!"));
    }

    @GetMapping("/getdemandeurbyid/{iddemandeur}")
    public Optional<Demandeur> demandeurbyid(@PathVariable Long iddemandeur) {
        return demandeurRepository.findById(iddemandeur);
    }

    @GetMapping("/listdemandeur")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public List<Demandeur> lister() {
        return demandeurRepository.findAll();
    }

    @DeleteMapping("/deletedemandeur/{iddemandeur}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public String delete(@PathVariable Long iddemandeur) {
        demandeurRepository.deleteById(iddemandeur);
        return "Demandeur supprimer avec success";
    }

    @PutMapping("/modifier/Demandeur/{iddemandeur}")
    public ResponseEntity<?> modifierDemandeur(@RequestParam("demandeur") String siReqDemandeur,
                                               @RequestParam("image") MultipartFile image,
                                               @RequestParam("cv") MultipartFile cv,
                                               @RequestParam("dossierdemandeur") MultipartFile dossierdemandeur,
                                               @PathVariable("iddemandeur") Long iddemandeur) {
        try {

            SiReqDemandeur siReqDemandeur1 = new JsonMapper().readValue(siReqDemandeur, SiReqDemandeur.class);
            if (demandeurRepository.existsByEmaildemandeur(siReqDemandeur1.getEmailDemandeur())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: cet email existe deja"));
            }else {
                Demandeur demandeur = new Demandeur();
                Set<String> strRoles = siReqDemandeur1.getRole();
                Set<Role> roles = new HashSet<>();
                if (strRoles == null) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Erreur: Impossible de trouver le rôle!!"));
                    roles.add(userRole);
                } else {
                    strRoles.forEach(role -> {
                        switch (role) {
                            case "admin":
                                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(adminRole);

                                break;
                            case "mod":
                                Role modRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(modRole);

                                break;
                            default:
                                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);
                        }
                    });

                }
                demandeur.setRoles(roles);
                demandeur.setNomdemandeur(siReqDemandeur1.getNomDemandeur());
                demandeur.setPrenomdemandeur(siReqDemandeur1.getPrenomDemandeur());
                demandeur.setAdressedemandeur(siReqDemandeur1.getAdresseDemandeur());
                demandeur.setAgedemandeur(siReqDemandeur1.getAgeDemandeur());
                demandeur.setEmaildemandeur(siReqDemandeur1.getEmailDemandeur());
                demandeur.setCv(SaveImage.save("DemandeurCV",cv, cv.getOriginalFilename()));
                demandeur.setDossierdemandeur(SaveImage.save("DemandeurDossier",dossierdemandeur, dossierdemandeur.getOriginalFilename()));
                demandeur.setProfildemandeur(siReqDemandeur1.getProfilDemandeur());
                demandeur.setPhotodemandeur(SaveImage.save("Demandeur",image, image.getOriginalFilename()));
                demandeur.setPassworddemandeur(encoder.encode(siReqDemandeur1.getPasswordDemandeur()));



                demandeurServiceImp.modifier(demandeur,iddemandeur);

                return ResponseEntity.ok(new MessageResponse("Demandeur modifier avec succes"));
            }

        }catch (Exception e){
            return null;
        }

    }

}
