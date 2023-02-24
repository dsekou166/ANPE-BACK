package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> lister();

    User creer(User user);

    User modifier(User user, Long id);

    String supprimer(Long id);
}
