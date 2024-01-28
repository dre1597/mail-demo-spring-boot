package org.example.maildemo.models;

public record DefaultContext(
    String name,
    String message
) {
  public static DefaultContext of(String name, String message) {
    return new DefaultContext(name, message);
  }
}
