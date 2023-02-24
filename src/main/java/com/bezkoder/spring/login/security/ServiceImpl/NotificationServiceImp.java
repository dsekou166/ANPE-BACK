package com.bezkoder.spring.login.security.ServiceImpl;

import com.bezkoder.spring.login.models.Annonce;
import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Notification;
import com.bezkoder.spring.login.repository.DemandeurRepository;
import com.bezkoder.spring.login.repository.NotificationRepository;
import com.bezkoder.spring.login.security.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    DemandeurRepository demandeurRepository;
    @Override
    public List<Notification> lister() {
        return null;
    }

    @Override
    public Notification creer(Notification notification) {
        return null;
    }

    @Override
    public Notification modifier(Notification notification, Long id_notification) {
        return null;
    }

    @Override
    public String supprimer(Long id_notification) {
        return null;
    }

    @Override
    public String generateNotificationByType(Annonce annonce) {
System.out.println("profil========="+annonce.getProfil());
        List<Demandeur> DemandeurList=demandeurRepository.findByProfildemandeur(annonce.getProfil());

        for (Demandeur demandeur1 : DemandeurList){
            String message = "Une nouvelle annonce de type " + demandeur1.getProfildemandeur()+ " vient d'être poster, " +
                    annonce.getNomposte() + " .Nous vous invitons à consulter les détails de cette annonce. ";
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setAnnonce(annonce);
            notification.setDatenotif(new Date());
            Notification notifSaved = notificationRepository.save(notification);

            demandeur1.getNotifications().add(notification);
            demandeurRepository.save(demandeur1);
        }
        return "Notification créé avec succès";
    }
}
