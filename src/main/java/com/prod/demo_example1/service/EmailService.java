package com.prod.demo_example1.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

     String sendMail(String to, String subject, String[] cc, String body) ;

     String sendMailWithAttachment(String to, String subject, String[] cc, String body, MultipartFile attachment);
}
