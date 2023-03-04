package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.img.SaveImage;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.models.Role;
import com.bezkoder.spring.login.payload.request.LoReqRecruteur;
import com.bezkoder.spring.login.payload.request.SiReqDemandeur;
import com.bezkoder.spring.login.payload.request.SiReqRecruteur;
import com.bezkoder.spring.login.payload.response.JwtResponse;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.repository.DemandeurRepository;
import com.bezkoder.spring.login.repository.RecruteurRepository;
import com.bezkoder.spring.login.repository.RetourRepository;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.security.ServiceImpl.RecrteurServiceImp;
import com.bezkoder.spring.login.security.jwt.JwtUtils;
import com.bezkoder.spring.login.security.services.RecruteurService;
import com.bezkoder.spring.login.security.services.UserDetailsImpl;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
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
public class RecruteurController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RecruteurRepository recruteurRepository;
    @Autowired
    RecrteurServiceImp recrteurServiceImp;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RecruteurService recruteurService;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private RetourRepository retourRepository;
    @Autowired
    private DemandeurRepository demandeurRepository;

    @PostMapping("/signin/Recruteur")
    public ResponseEntity<?> authenticateRecruteur(@Valid @RequestBody LoReqRecruteur loReqRecruteur) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loReqRecruteur.getEmailentreprise(), loReqRecruteur.getPasswordentreprise()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(jwtUtils.generateTokenFromUsername(userDetails.getEmail()), userDetails.getId(),

                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup/Recruteur")
    public ResponseEntity<?> registerRecruteur(@RequestParam("recruteur") String siReqRecruteur,
                                               @RequestParam("image") MultipartFile image) {
        try {

            SiReqRecruteur siReqRecruteur1 = new JsonMapper().readValue(siReqRecruteur, SiReqRecruteur.class);
            if (recruteurRepository.existsByEmailentreprise(siReqRecruteur1.getEmailentreprise())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: cet email existe deja"));
            }else {
                Recruteur recruteur = new Recruteur();
                Set<String> strRoles = siReqRecruteur1.getRole();
                Set<Role> roles = new HashSet<>();
                if (strRoles == null) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
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
                recruteur.setRoles(roles);
                recruteur.setNomentreprise(siReqRecruteur1.getNomentreprise());
                recruteur.setAdresseentreprise(siReqRecruteur1.getAdresseentreprise());
                recruteur.setEmailentreprise(siReqRecruteur1.getEmailentreprise());
                recruteur.setSecteur(siReqRecruteur1.getSecteur());
                recruteur.setPhotoentreprise(SaveImage.save("Recruteur",image, image.getOriginalFilename()));
                recruteur.setPasswordentreprise(encoder.encode(siReqRecruteur1.getPasswordentreprise()));
                recruteurRepository.save(recruteur);

                return ResponseEntity.ok(new MessageResponse("Recruteur ajouter avec succes"));
            }

        }catch (Exception e){
            return null;
        }

    }

    @PostMapping("/signout/Recruteur")
    public ResponseEntity<?> logoutRecruteur() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Vous vous êtes deconnecter!"));
    }


    @GetMapping("/getrecruteureurbyid/{idrecruteur}")
    public Optional<Recruteur> recruteurbyid(@PathVariable Long idrecruteur) {
        return recruteurRepository.findById(idrecruteur);
    }

    @GetMapping("/listrecruteur")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public List<Recruteur> lister() {
        return recruteurRepository.findAll();
    }

    @DeleteMapping("/deleterecruteur/{idrecruteur}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public String delete(@PathVariable Long idrecruteur) {
        recruteurRepository.deleteById(idrecruteur);
        return "Demandeur supprimer avec success";
    }


    @PutMapping("/modifier/Recruteur/{idrecruteur}")
    public ResponseEntity<?> modifierRecruteur(@RequestParam("recruteur") String siReqRecruteur,
                                               @RequestParam("image") MultipartFile image,
                                               @PathVariable("idrecruteur") Long idrecruteur) {
        try {

            SiReqRecruteur siReqRecruteur1 = new JsonMapper().readValue(siReqRecruteur, SiReqRecruteur.class);
            if (recruteurRepository.existsByEmailentreprise(siReqRecruteur1.getEmailentreprise())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: cet email existe deja"));
            }else {
                Recruteur recruteur = new Recruteur();
                Set<String> strRoles = siReqRecruteur1.getRole();
                Set<Role> roles = new HashSet<>();
                if (strRoles == null) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
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
                            /*case "mod":
                                Role modRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(modRole);

                                break;*/
                            default:
                                Role userRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);
                        }
                    });

                }
                recruteur.setRoles(roles);
                recruteur.setNomentreprise(siReqRecruteur1.getNomentreprise());
                recruteur.setAdresseentreprise(siReqRecruteur1.getAdresseentreprise());
                recruteur.setEmailentreprise(siReqRecruteur1.getEmailentreprise());
                recruteur.setSecteur(siReqRecruteur1.getSecteur());
                recruteur.setPhotoentreprise(SaveImage.save("Recruteur",image, image.getOriginalFilename()));
                recruteur.setPasswordentreprise(encoder.encode(siReqRecruteur1.getPasswordentreprise()));
                recrteurServiceImp.modifier(recruteur,idrecruteur);

                return ResponseEntity.ok(new MessageResponse("Recruteur modifier avec succes"));
            }

        }catch (Exception e){
            return null;
        }

    }
}
