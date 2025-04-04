package com.prod.demo_example1.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl implements  EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public String sendMail(String to, String subject, String[] cc, String body) {
        //implementation for sending a simple message via email withput attachments
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);

        return "Mail sent sucessfully!!";
    }

    @Override
    public String sendMailWithAttachment(String to, String subject, String[] cc, String body, MultipartFile attachment) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // 'true' enables multipart

            helper.setTo(to);
            if (cc != null && cc.length > 0) {
                helper.setCc(cc);
            }
            helper.setSubject(subject);
            helper.setText(body);

            // Add attachment
            helper.addAttachment(attachment.getOriginalFilename(), new ByteArrayResource(attachment.getBytes()));

            javaMailSender.send(mimeMessage);

            return "Mail with attachment sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending mail with attachment: " + e.getMessage();

        }
    }
}
