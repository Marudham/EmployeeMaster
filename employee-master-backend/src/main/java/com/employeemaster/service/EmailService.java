package com.employeemaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String to, String subject, String htmlBody) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(htmlBody, true);

		javaMailSender.send(message);
	}

	//    public void sendEmail(String to, String subject, String body) {
	//        SimpleMailMessage message = new SimpleMailMessage();
	//        message.setTo(to);
	//        message.setSubject(subject);
	//        message.setText(body);
	//
	//        javaMailSender.send(message);
	//    }
}
