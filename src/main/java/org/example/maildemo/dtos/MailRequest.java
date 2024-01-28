package org.example.maildemo.dtos;

public record MailRequest(
    String to,
    String subject,
    String name,
    String message
) {
}
