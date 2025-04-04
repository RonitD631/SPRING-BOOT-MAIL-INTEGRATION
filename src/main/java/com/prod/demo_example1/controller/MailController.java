package com.prod.demo_example1.controller;

import com.prod.demo_example1.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mailSender")
public class MailController {


    private EmailService emailService;

    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    //1)API FOR SENDING EMAIL USING FORM -DATA  ONLY!!!!!!!!!!!!!!

    @PostMapping("/sendSimpleMessage")
    private ResponseEntity<String> sendMail(String to, String subject, String [] cc , String body){
          String responseResult = emailService.sendMail(to, subject, cc, body);
        return new ResponseEntity<String>(responseResult, HttpStatus.OK);
    }

    //API FOR SENDING EMAIL USING FORM -DATA CUM ATTACHMENT
    @PostMapping("/sendMessageWithAttachment")
    public ResponseEntity<String> sendMailWithAttachment(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam(value = "cc", required = false) String[] cc,
            @RequestParam("body") String body,
            @RequestParam("attachment") MultipartFile attachment
    ) {
        String response = emailService.sendMailWithAttachment(to, subject, cc, body, attachment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
