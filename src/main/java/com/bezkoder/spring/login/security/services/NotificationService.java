package com.bezkoder.spring.login.security.services;

import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Notification;
import com.bezkoder.spring.login.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NotificationService {
    List<Notification> lister();

    Notification creer(Notification notification);

    Notification modifier(Notification notification, Long id_notification);

    String supprimer(Long id_notification);

    String generateNotificationByType(Annonce annonce);
}
