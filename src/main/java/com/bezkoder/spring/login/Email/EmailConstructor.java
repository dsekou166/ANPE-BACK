package com.bezkoder.spring.login.Email;

import com.bezkoder.spring.login.models.Demandeur;
import com.bezkoder.spring.login.models.Recruteur;
import com.bezkoder.spring.login.models.RetourCandidature;
import lombok.AllArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
@AllArgsConstructor
public class EmailConstructor {

    private Environment env;


    private TemplateEngine templateEngine;

    public MimeMessagePreparator constructNewUserEmail(Recruteur recruteur, String message) {
        Context context = new Context();
        context.setVariable("recruteur", recruteur);
        context.setVariable("message", message);
        String text = templateEngine.process("newUserEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(recruteur.getEmailentreprise());
                email.setSubject("Retour suite à votre demande de travail");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructResetPasswordEmail(Demandeur demandeur, RetourCandidature retourCandidature) {
        Context context = new Context();
        context.setVariable("demandeur", demandeur);
        context.setVariable("retourCandidature", retourCandidature);
        String text = templateEngine.process("newDemanderEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(demandeur.getEmaildemandeur());
                email.setSubject("JOB-ANPE");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructUpdateUserProfileEmail(Recruteur recruteur) {
        Context context = new Context();
        context.setVariable("recruteur", recruteur);
        String text = templateEngine.process("updateUserProfileEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(recruteur.getEmailentreprise());
                email.setSubject("JOB-ANPE");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }
}
