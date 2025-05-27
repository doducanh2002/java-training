package org.aibles.framework.security.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.aibles.adapter.helper.AuthenticationHelper;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.entity.Account;
import org.aibles.framework.security.AuthenticationProvider;
import org.aibles.util.ThreadLocalCacheUntil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class LocalAuthenticationProvider implements AuthenticationProvider {
  private final AuthenticationHelper authenticationHelper;
  private final AccountRepository accountRepository;

  public LocalAuthenticationProvider(AuthenticationHelper authenticationHelper,
      AccountRepository accountRepository) {
    this.authenticationHelper = authenticationHelper;
    this.accountRepository = accountRepository;
  }

  @Override
  public boolean validateAccessToken(String token) {
    String username;
    try {
      username = authenticationHelper.getSubjectFromAccessToken(token);
    } catch (Exception exception) {
      return false;
    }
    if(authenticationHelper.isExpiredAccessToken(token)) {
      return false;
    }
    Optional<Account> optionalAccount = accountRepository.findAccountWithRolesByUsername(username);
    if (optionalAccount.isEmpty()) {
      return false;
    }
    ThreadLocalCacheUntil.put(username, optionalAccount.get());
    return true;
  }

  @Override
  public void addAuthentication(String token) {
    String username = authenticationHelper.getSubjectFromAccessToken(token);
    Account account = (Account) ThreadLocalCacheUntil.get(username);
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    account.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, account.getId(), authorities));
  }
}
