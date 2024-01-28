package org.example.maildemo.services;

import jakarta.mail.MessagingException;
import org.example.maildemo.models.DefaultContext;
import org.example.maildemo.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Objects;

@Service
public class MailService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
  private final TemplateEngine templateEngine;
  private final JavaMailSenderImpl javaMailSenderImpl;
  @Value("${mail.sender.from}")
  private String from;

  public MailService(final TemplateEngine templateEngine, final JavaMailSenderImpl javaMailSenderImpl) {
    this.templateEngine = Objects.requireNonNull(templateEngine);
    this.javaMailSenderImpl = Objects.requireNonNull(javaMailSenderImpl);
  }

  public void send(final Message<DefaultContext> message) {
    try {
      var javaMailSender = javaMailSenderImpl.getMailSender();

      var mimeMessage = javaMailSender.createMimeMessage();
      var helper = new MimeMessageHelper(mimeMessage);

      configure(message, helper);

      javaMailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("Error while sending email", e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while sending email");
    }
  }

  private void configure(final Message<DefaultContext> message, final MimeMessageHelper helper) throws MessagingException {
    helper.setSubject(message.subject());
    helper.setFrom(from);

    addBody(message, helper);
    addRecipients(message, helper);
    addAttachments(message, helper);
  }

  private void addBody(Message<DefaultContext> message, MimeMessageHelper helper) throws MessagingException {
    if (message.text() != null) {
      helper.setText(message.text(), false);
    } else {
      helper.setText(templateEngine.process(message.template(), getContext(message.context())), true);
    }
  }

  private Context getContext(final DefaultContext defaultContext) {
    var context = new Context();
    context.setVariable("name", defaultContext.name());
    context.setVariable("message", defaultContext.message());
    return context;
  }

  private void addRecipients(Message<DefaultContext> message, MimeMessageHelper helper) {
    message.to().forEach(to -> {
      try {
        helper.setTo(to);
      } catch (MessagingException e) {
        LOGGER.error("Error while adding recipient", e);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while adding recipient");
      }
    });
  }

  private void addAttachments(Message<DefaultContext> message, MimeMessageHelper helper) {
    message.attachments().forEach(attachment -> {
      try {
        helper.addAttachment(attachment.name(), attachment.file());
      } catch (MessagingException e) {
        LOGGER.error("Error while adding attachment", e);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while adding attachment");
      }
    });
  }
}
