package com.example.demo.email;

import com.example.demo.entity.Token;
import com.example.demo.entity.User.User;
import com.example.demo.entity.Visit.Visit;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.VisitRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MailService {


    private final JavaMailSender javaMailSender;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final TokenRepository tokenRepository;

    public MailService(JavaMailSender javaMailSender, PatientRepository patientRepository, VisitRepository visitRepository, TokenRepository tokenRepository) {
        this.javaMailSender = javaMailSender;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.tokenRepository = tokenRepository;
    }

    public void sendMail(String to,
                         String subject,
                         String text,
                         boolean isHtmlContent) throws MessagingException {


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        javaMailSender.send(mimeMessage);

    }


    @Scheduled(cron = "00 13 15 * * 1-6")
    public void visitReminder() throws MailException {

        LocalDateTime actualDate = LocalDateTime.now();

        System.out.println("dyuuuuupa");

        List<Visit> visitList = visitRepository.findAll();

        String mmailContent = ("Remember that you have visit tomorrow " + "\n" +
                "Informations about your visit: " + "\n" +
                "Doctors Office Number: %s" + "\n" +
                "Date: %s");


        visitList.stream()
                .filter(x -> (actualDate.plusDays(1).getDayOfWeek().equals(x.getVisitDate().getDayOfWeek())) && actualDate.plusDays(1).getYear() == x.getVisitDate().getYear())
                .forEach(x -> {
                    try {
                        sendMail(x.getPatient().getEmail(), "You have a visit tomorrow.", String.format(mmailContent, x.getDoctorsOfficeNumber(), x.getVisitDate().toString()), false);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });

    }

    public void sendNewAccountToken(User user) throws MessagingException {

        String tokenValue = UUID.randomUUID().toString();

        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(user);
        tokenRepository.save(token);

        String url = "http://localhost:8080/users/token?value=" + tokenValue;

        sendMail(user.getEmail(), "Confirm registration", "To confirm registraction click the link below\n"+url, true);

    }
}