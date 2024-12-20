package com.boogionandon.backend.security.handler;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {

    Gson gson = new Gson();

    String jsonStr = gson.toJson(Map.of("error", "ERROR_ACCESS_DENIED"));

    response.setContentType("application/json; charset=UTF-8");
    response.setStatus(HttpStatus.FORBIDDEN.value());

    PrintWriter writer = response.getWriter();
    writer.println(jsonStr);
    writer.close();
  }
}
