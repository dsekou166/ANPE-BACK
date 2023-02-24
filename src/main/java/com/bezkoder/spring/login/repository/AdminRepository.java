package com.bezkoder.spring.login.repository;

import com.bezkoder.spring.login.models.Admin;
import com.bezkoder.spring.login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository  extends JpaRepository <Admin, Long> {


  // Boolean existsByPrenomAdmin(String prenom_admin);
  // Boolean existsByNomAdmin(String nom_admin);
   Boolean existsByEmailadmin(String email_admin);
   //Boolean existsByPhotoAdmin(String photo_admin);

   Optional<Admin> findByEmailadmin(String email);

}
