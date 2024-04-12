package org.aibles.privatetraining.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor(staticName = "of")
@Data
@NoArgsConstructor
public class Response {

  private int status;
  private String timestamp;
  private Object data;

  public static Response of(int status, Object data) {
    return Response.of(status, String.valueOf(LocalDateTime.now()), data);
  }

  public static Response of(int status) {
    return Response.of(status, String.valueOf(LocalDateTime.now()), null);
  }
}
