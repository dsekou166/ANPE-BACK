package com.bezkoder.spring.login.security.ServiceImpl;

import com.bezkoder.spring.login.models.Admin;
import com.bezkoder.spring.login.repository.AdminRepository;
import com.bezkoder.spring.login.security.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public List<Admin> lister() {
        return null;
    }

    @Override
    public Admin creer(Admin admin) {
        return null;
    }

    @Override
    public Admin modifier(Admin admin, Long idadmin) {
        return adminRepository.findById(idadmin).map(admin1 -> {
            admin1.setNomadmin(admin.getNomadmin());
            admin1.setPrenomadmin(admin.getPrenomadmin());
            admin1.setEmailadmin(admin.getEmailadmin());
            admin1.setPasswordadmin(admin.getPasswordadmin());
            admin1.setRoles(admin.getRoles());
            if (admin1.getPhotoadmin() == admin.getPhotoadmin())
            {
                admin1.setPhotoadmin(admin1.getPhotoadmin());
            }else {
                admin1.setPhotoadmin(admin.getPhotoadmin());
            }
            return adminRepository.save(admin1);
        }).orElseThrow(() -> new RuntimeException("Ce demandeur n'existe pas !"));
    }

    @Override
    public String supprimer(Long id_admin) {
        return null;
    }
}
