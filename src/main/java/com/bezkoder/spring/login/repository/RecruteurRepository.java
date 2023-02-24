package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {

    Boolean existsByNomentreprise(String NomEntreprise);
    Boolean existsByEmailentreprise(String EmailEntreprise);

    Optional<Recruteur> findByEmailentreprise(String email);

}
