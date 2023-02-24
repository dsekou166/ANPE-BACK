package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis, Long> {
}
