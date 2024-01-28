package org.example.maildemo.controllers;

import jakarta.validation.Valid;
import org.example.maildemo.dtos.MailRequest;
import org.example.maildemo.models.DefaultContext;
import org.example.maildemo.models.Message;
import org.example.maildemo.services.MailService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mail")
public class MailController {
  private MailService mailService;

  public MailController(final MailService mailService) {
    this.mailService = mailService;
  }

  @RequestMapping
  public void send(@Valid @RequestBody final MailRequest input) {
    var message = Message.of(input.to(), input.subject(), null, "mail", List.of(), DefaultContext.of(input.name(), input.message()));
    mailService.send(message);
  }
}
