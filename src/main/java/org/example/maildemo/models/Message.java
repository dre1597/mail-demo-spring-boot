package org.example.maildemo.models;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record Message<T>(
    @NotEmpty List<String> to,
    @NotEmpty String subject,
    String text,
    @NotEmpty String template,
    List<Attachments> attachments,
    @NotEmpty T context
) {
  public static <U> Message<U> of(
      List<String> to,
      String subject,
      String text,
      String template,
      List<Attachments> attachments,
      U context
  ) {
    return new Message<>(to, subject, text, template, attachments, context);
  }

  public static <U> Message<U> of(
      String to,
      String subject,
      String text,
      String template,
      List<Attachments> attachments,
      U context
  ) {
    return new Message<>(List.of(to), subject, text, template, attachments, context);
  }
}
