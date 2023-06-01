package com.example.admin.config;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ResourceNotFoundException extends UsernameNotFoundException {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
