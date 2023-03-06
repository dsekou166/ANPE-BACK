package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Postuler;
import com.bezkoder.spring.login.models.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulerRepository extends JpaRepository<Postuler, Long> {

    List <Postuler> findByAnnonce(Annonce annonce);

    boolean existsByDemandeurAndAnnonce(Demandeur demandeur,Annonce annonce);



}
