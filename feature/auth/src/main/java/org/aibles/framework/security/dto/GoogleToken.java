package org.aibles.framework.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GoogleToken {

  private String iss;
  private String azp;
  private String aud;
  private String sub;
  private String email;
  @JsonProperty("email_verified")
  private boolean emailVerified;
  @JsonProperty("at_hash")
  private String atHash;
  private String name;
  private String picture;
  @JsonProperty("given_name")
  private String givenName;
  @JsonProperty("family_name")
  private String familyName;
  private long iat;
  private long exp;
}