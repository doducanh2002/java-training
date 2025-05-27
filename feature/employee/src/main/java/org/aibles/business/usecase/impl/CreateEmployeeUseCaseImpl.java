package org.aibles.business.usecase.impl;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.aibles.adapter.repository.AccountRepository;
import org.aibles.adapter.repository.AccountRoleRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.adapter.repository.EmployeeAccountRepository;
import org.aibles.adapter.repository.EmployeeRepository;
import org.aibles.adapter.repository.RoleRepository;
import org.aibles.business.dto.request.CreateEmployeeUseCaseRequest;
import org.aibles.business.exception.EmailAlreadyExistedException;
import org.aibles.business.exception.EmailSendingException;
import org.aibles.business.exception.RoleNotFoundException;
import org.aibles.business.usecase.CreateEmployeeUseCase;
import org.aibles.entity.Account;
import org.aibles.entity.Employee;
import org.aibles.util.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {

  private static final Logger log = LoggerFactory.getLogger(
      CreateEmployeeUseCaseImpl.class);

  private final EmployeeRepository employeeRepository;
  private final AccountRepository accountRepository;
  private final EmployeeAccountRepository employeeAccountRepository;
  private final AccountRoleRepository accountRoleRepository;
  private final EmailHelper emailHelper;
  private final RoleRepository roleRepository;

  public CreateEmployeeUseCaseImpl(EmployeeRepository employeeRepository,
      AccountRepository accountRepository, EmployeeAccountRepository employeeAccountRepository,
      AccountRoleRepository accountRoleRepository, EmailHelper emailHelper,
      RoleRepository roleRepository) {
    this.employeeRepository = employeeRepository;
    this.accountRepository = accountRepository;
    this.employeeAccountRepository = employeeAccountRepository;
    this.accountRoleRepository = accountRoleRepository;
    this.emailHelper = emailHelper;
    this.roleRepository = roleRepository;
  }

  @Override
  public void invoke(CreateEmployeeUseCaseRequest request) {
    for (String roleId : request.getRoleIds()) {
      log.info("(invoke)roleId: {}", roleId);
      if (!roleRepository.existsById(roleId)) {
        log.error("(invoke)roleId: {}", roleId);
        throw new RoleNotFoundException(roleId);
      }
    }

    if (employeeRepository.existsByEmail(request.getEmail())) {
      log.error("(invoke)email: {}", request.getEmail());
      throw new EmailAlreadyExistedException(request.getEmail());
    }

    var employee = employeeRepository.save(new Employee(
        request.getFirstName(),
        request.getMiddleName(),
        request.getLastName(),
        request.getEmail(),
        request.getPhone(),
        request.getDateOfBirth(),
        request.getGender(),
        request.getAddress(),
        request.getAvatar(),
        true,
        false
    ));

    String username = generateUsername(request.getFirstName(), request.getMiddleName(),
        request.getLastName());
    String password = generatePassword();

    var account = accountRepository.save(
        new Account(username, EncryptUtil.getPasswordEncoder().encode(password), true));

    employeeAccountRepository.save(account.getId(), employee.getId());

    for (String roleId : request.getRoleIds()) {
      log.info("(invoke) roleId: {}", roleId);
      accountRoleRepository.save(account.getId(), roleId);
    }
    sendAccountToEmail(username, password, request.getEmail());
  }

  private String generateUsername(String firstName, String middleName, String lastName) {
    int sumAccount = accountRepository.countAll();
    StringBuilder initials = new StringBuilder();
    for (String part : middleName.split("\\s+")) {
      if (!part.isEmpty()) {
        initials.append(part.charAt(0));
      }
    }
    StringBuilder username = new StringBuilder();
    username.append("AMS-");
    username.append(lastName);
    username.append(firstName.charAt(0));
    username.append(initials);
    username.append("-");
    username.append(sumAccount + 1);
    return username.toString();
  }

  private String generatePassword() {
    int PASSWORD_LENGTH = 16;
    SecureRandom random = new SecureRandom();

    String upperCaseLetters = IntStream.rangeClosed('A', 'Z')
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining());
    String lowerCaseLetters = IntStream.rangeClosed('a', 'z')
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining());
    String digits = IntStream.rangeClosed('0', '9')
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining());
    String specialCharacters = IntStream.rangeClosed(33, 47)
        .mapToObj(c -> String.valueOf((char) c))
        .collect(Collectors.joining()) +
        IntStream.rangeClosed(58, 64)
            .mapToObj(c -> String.valueOf((char) c))
            .collect(Collectors.joining()) +
        IntStream.rangeClosed(91, 96)
            .mapToObj(c -> String.valueOf((char) c))
            .collect(Collectors.joining()) +
        IntStream.rangeClosed(123, 126)
            .mapToObj(c -> String.valueOf((char) c))
            .collect(Collectors.joining());

    specialCharacters = specialCharacters.replace("'", "").replace("\"", "");

    String combinedChars = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;

    StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
    password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
    password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
    password.append(digits.charAt(random.nextInt(digits.length())));
    password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

    for (int i = 4; i < PASSWORD_LENGTH; i++) {
      password.append(combinedChars.charAt(random.nextInt(combinedChars.length())));
    }

    List<Character> passwordChars = password.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.toList());
    Collections.shuffle(passwordChars, random);

    StringBuilder finalPassword = new StringBuilder();
    passwordChars.forEach(finalPassword::append);

    return finalPassword.toString();
  }



  private void sendAccountToEmail(String username, String password, String email) {
    String subject = "Your login information";
    var param = new HashMap<String, Object>();
    param.put("username", username);
    param.put("password", password);
    try {
      emailHelper.send(subject, email, "employee-template", param);
    } catch (Exception e) {
      log.error("(sendAccountToEmail)email: {}", email);
      throw new EmailSendingException(email);
    }
  }
}
