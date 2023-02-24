package com.bezkoder.spring.login.controllers;

import com.bezkoder.spring.login.models.Notification;
import com.bezkoder.spring.login.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    NotificationRepository notificationRepository;
    @GetMapping("/getnotfbyid/{idnotifi}")
    public Optional<Notification> notifbyid(@PathVariable Long idnotifi) {
        return notificationRepository.findById(idnotifi);
    }

    @GetMapping("/listnotification")
    // @PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public List<Notification> lister() {

       // System.out.println(lister());
        return notificationRepository.findAll();
    }

    @DeleteMapping("/deletenotification/{idnotifi}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('RECRUTEUR')")
    public String delete(@PathVariable Long idnotifi) {
        notificationRepository.deleteById(idnotifi);
        return "Notification supprimé avec succès";
    }


}
