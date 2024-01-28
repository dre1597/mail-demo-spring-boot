package org.example.maildemo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JavaMailSenderImpl {
  @Value("${mail.sender.username}")
  private String username;

  @Value("${mail.sender.password}")
  private String password;

  @Value("${mail.sender.host}")
  private String host;

  @Value("${mail.sender.port}")
  private int port;

  private org.springframework.mail.javamail.JavaMailSenderImpl mailSender = new org.springframework.mail.javamail.JavaMailSenderImpl();

  public org.springframework.mail.javamail.JavaMailSenderImpl getMailSender() {
    System.out.println(host + " " + port + " " + username + " " + password);
    mailSender.setHost(host);
    mailSender.setPort(port);
    mailSender.setUsername(username);
    mailSender.setPassword(password);
    return mailSender;
  }
}
