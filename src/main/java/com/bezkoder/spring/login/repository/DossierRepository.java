package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Admin;
import com.bezkoder.spring.login.models.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
}
