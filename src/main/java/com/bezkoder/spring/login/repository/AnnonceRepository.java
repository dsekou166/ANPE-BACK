package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    public Annonce findByNomposte(String nomposte);
    //public Annonce findByIddemandeur(Demandeur demandeur);

    List<Annonce> findByRecruteur(Recruteur recruteur);

}
