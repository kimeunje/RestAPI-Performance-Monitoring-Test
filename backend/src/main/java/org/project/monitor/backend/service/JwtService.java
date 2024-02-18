package org.project.monitor.backend.service;

public interface JwtService {
  public String getToken(String key, Object value);
}
