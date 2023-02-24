package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeurRepository extends JpaRepository<Demandeur, Long> {

    Demandeur findDemandeurByIddemandeur(Long iddemmandeur);
    Boolean existsByEmaildemandeur(String Emaildemandeur);

    Optional<Demandeur> findByEmaildemandeur(String email);

    List<Demandeur> findByAnnonce(Annonce annonce);

    List<Demandeur> findByProfildemandeur(String profil);


}
