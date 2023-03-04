package com.bezkoder.spring.login.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bezkoder.spring.login.img.SaveImage;
import com.bezkoder.spring.login.models.*;
import com.bezkoder.spring.login.payload.request.SiReqDemandeur;
import com.bezkoder.spring.login.payload.request.SiReqRecruteur;
import com.bezkoder.spring.login.payload.response.JwtResponse;
import com.bezkoder.spring.login.repository.AdminRepository;
import com.bezkoder.spring.login.security.ServiceImpl.AdminServiceImp;
import com.fasterxml.jackson.databind.json.JsonMapper;
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

import com.bezkoder.spring.login.payload.request.LoginRequest;
import com.bezkoder.spring.login.payload.request.SignupRequest;
import com.bezkoder.spring.login.payload.response.MessageResponse;
import com.bezkoder.spring.login.repository.RoleRepository;
import com.bezkoder.spring.login.security.jwt.JwtUtils;
import com.bezkoder.spring.login.security.services.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AdminController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AdminServiceImp adminServiceImp;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin/Admin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailadmin(), loginRequest.getPasswordadmin()));

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

    @PostMapping("/signup/Admin")
    public ResponseEntity<?> registerAdmin(@RequestParam("admin") String signupRequest, @RequestParam("image") MultipartFile image) {
        try {

            SignupRequest signupRequest1 = new JsonMapper().readValue(signupRequest, SignupRequest.class);
            if (adminRepository.existsByEmailadmin(signupRequest1.getEmailadmin())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: cet email existe deja"));
            }else {
                Admin admin = new Admin();
                Set<String> strRoles = signupRequest1.getRole();
                Set<Role> roles = new HashSet<>();
                if (strRoles == null) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
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
            admin.setRoles(roles);
            admin.setNomadmin(signupRequest1.getNomadmin());
            admin.setPrenomadmin(signupRequest1.getPrenomadmin());
            admin.setEmailadmin(signupRequest1.getEmailadmin());
            admin.setPhotoadmin(SaveImage.save("Admin",image, image.getOriginalFilename()));
            admin.setPasswordadmin(encoder.encode(signupRequest1.getPasswordadmin()));
            adminRepository.save(admin);

                return ResponseEntity.ok(new MessageResponse("Admin ajouter avec succes"));
            }

        }catch (Exception e){
            return null;
        }

    }

    @PostMapping("/signout/Admin")
    public ResponseEntity<?> logoutAdmin() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Vous vous êtes deconnecter!"));
    }


    @GetMapping("/getadminbyid/{idadmin}")
    public Optional<Admin> recruteurbyid(@PathVariable Long idadmin) {
        return adminRepository.findById(idadmin);
    }

    @GetMapping("/listadmin")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public List<Admin> lister() {
        return adminRepository.findAll();
    }

    @DeleteMapping("/deleteadmin/{idadmin}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public String delete(@PathVariable Long idadmin) {
        adminRepository.deleteById(idadmin);
        return "Admin supprimer avec success";
    }

    @PutMapping("/Admin/modifier/{idamin}")
    public ResponseEntity<?> modifierAdmin(@RequestParam("admin") String signupRequest,
                                           @RequestParam("image") MultipartFile image,
                                           @PathVariable("idamin") Long idadmin) {
        try {

            SignupRequest signupRequest1 = new JsonMapper().readValue(signupRequest, SignupRequest.class);
            if (adminRepository.existsByEmailadmin(signupRequest1.getEmailadmin())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Erreur: cet email existe deja"));
            }else {
                Admin admin = new Admin();
                Set<String> strRoles = signupRequest1.getRole();
                Set<Role> roles = new HashSet<>();
                if (strRoles == null) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Erreur: Impossible de trouver le rôle!!"));
                    roles.add(userRole);
                } else {
                    strRoles.forEach(role -> {
                        switch (role) {

                            case "mod":
                                Role modRole = roleRepository.findByName(ERole.ROLE_RECRUTEUR)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(modRole);

                                break;
                            default:
                                Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);
                        }
                    });

                }
                admin.setRoles(roles);
                admin.setNomadmin(signupRequest1.getNomadmin());
                admin.setPrenomadmin(signupRequest1.getPrenomadmin());
                admin.setEmailadmin(signupRequest1.getEmailadmin());
                admin.setPhotoadmin(SaveImage.save("Admin",image, image.getOriginalFilename()));
                admin.setPasswordadmin(encoder.encode(signupRequest1.getPasswordadmin()));
                adminServiceImp.modifier(admin,idadmin);

                return ResponseEntity.ok(new MessageResponse("Admin modifier avec succes"));
            }

        }catch (Exception e){
            return null;
        }

    }
}
