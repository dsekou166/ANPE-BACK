package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> lister() {

        return userRepository.findAll();
    }

    @Override
    public User creer(User user) {
        return userRepository.save(user);
    }


    @Override
    public User modifier(User user, Long id) {

        return userRepository.findById(id)
                .map(u ->{
                    u.setUsername(user.getUsername());
                    u.setEmail(user.getEmail());
                    u.setPassword(user.getPassword());
                    u.setRoles(user.getRoles());
                    return userRepository.save(u);
                } ).orElseThrow(() -> new RuntimeException("Cet utilisateur n'existe pas !"));
    }

    @Override
    public String supprimer(Long id) {
        userRepository.deleteById(id);
        return "Collaborateur supprimé avec succès";
    }
}
