package org.aibles.privatetraining.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ForgotPasswordRequest {

  private String username;
  private String otp;
  private String password;
}