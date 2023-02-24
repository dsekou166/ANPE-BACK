package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Admin;
import com.bezkoder.spring.login.models.User;

import java.util.List;

public interface AdminService {

    List<Admin> lister();

    Admin creer(Admin admin);

    Admin modifier(Admin admin, Long idadmin);

    String supprimer(Long id_admin);
}
