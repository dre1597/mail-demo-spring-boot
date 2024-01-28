package org.example.maildemo.models;

import java.io.File;
import java.util.UUID;

public record Attachments(
    String name,
    File file
) {
  public static Attachments of(String name, File file) {
    return new Attachments(name, file);
  }

  public static Attachments of(String name, String path) {
    return new Attachments(name, new File(path));
  }

  public static Attachments of(File file) {
    return new Attachments(UUID.randomUUID().toString(), file);
  }

  public static Attachments of(String path) {
    return new Attachments(UUID.randomUUID().toString(), new File(path));
  }
}
