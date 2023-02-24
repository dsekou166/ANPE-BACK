package com.bezkoder.spring.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.login.models.ERole;
import com.bezkoder.spring.login.models.Role;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);

 /* @Modifying
  @Transactional
  @Query(value = "INSERT INTO roles (name) VALUES('ROLE_USER'), ('ROLE_RECRUTEUR'), ('ROLE_ADMIN');", nativeQuery = true)
  void creerrole();*/



}
