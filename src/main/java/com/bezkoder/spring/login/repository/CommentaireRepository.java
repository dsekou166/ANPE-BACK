package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
}
