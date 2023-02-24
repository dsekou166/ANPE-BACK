package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Admin;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.repository.AdminRepository;
import com.bezkoder.spring.login.repository.DemandeurRepository;
import com.bezkoder.spring.login.repository.RecruteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.spring.login.models.User;
import com.bezkoder.spring.login.repository.UserRepository;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  AdminRepository userRepository;
  @Autowired
  private RecruteurRepository recruteurRepository;
  @Autowired
  private DemandeurRepository demandeurRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    try {
      Admin user = userRepository.findByEmailadmin(username)
              .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

      return UserDetailsImpl.build(user);
    }catch (Exception e){
      try {
        Recruteur user = recruteurRepository.findByEmailentreprise(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return RecruteurDetailImpl.build(user);
      }
      catch (Exception e2){
        Demandeur user = demandeurRepository.findByEmaildemandeur(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return DemandeurDetailImpl.build(user);
      }
    }

  }

}
