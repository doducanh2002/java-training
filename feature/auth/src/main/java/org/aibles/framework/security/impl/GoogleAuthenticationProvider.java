package org.aibles.framework.security.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.entity.Customer;
import org.aibles.enumiration.RoleEnum;
import org.aibles.framework.security.AuthenticationProvider;
import org.aibles.framework.security.dto.GoogleToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class GoogleAuthenticationProvider implements AuthenticationProvider {
  @Value("${spring.security.oauth2.resourceserver.google.jwk-set-uri}")
  private String jwkSetUri;
  private final AuthenticationHelper authenticationHelper;
  private final ObjectMapper objectMapper;
  private final CustomerRepository customerRepository;

  public GoogleAuthenticationProvider(AuthenticationHelper authenticationHelper, ObjectMapper objectMapper, CustomerRepository customerRepository) {
    this.authenticationHelper = authenticationHelper;
    this.objectMapper = objectMapper;
    this.customerRepository = customerRepository;
  }

  @Override
  public boolean validateAccessToken(String token) {
    return authenticationHelper.validateOAuth2Token(token, jwkSetUri);
  }

  @Override
  public void addAuthentication(String token) throws JsonProcessingException {
    GoogleToken googleToken = objectMapper.readValue(authenticationHelper.getTokenPayload(token), GoogleToken.class);
    if (Objects.nonNull(googleToken.getEmail())
        && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      List<SimpleGrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(RoleEnum.AUTHENTICATED.getValue()));
      var usernamePasswordAuthToken =
          new UsernamePasswordAuthenticationToken(
              googleToken.getName(), googleToken.getEmail(), authorities);
      usernamePasswordAuthToken.setDetails(googleToken);
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
      Optional<Customer> customerOpt = customerRepository.findCustomerByEmail(googleToken.getEmail());
      if (customerOpt.isEmpty()) {
        Customer customer = new Customer();
        String name = googleToken.getName();
        customer.setEmail(googleToken.getEmail());
        customer.setFirstName(googleToken.getGivenName());
        customer.setLastName(googleToken.getFamilyName());
        customer.setMiddleName(name.substring(customer.getFirstName().length() + 1, name.length() - customer.getLastName().length()).trim());
        customerRepository.save(customer);
      }
    }
  }
}
