package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.*;
import org.aibles.privatetraining.dto.response.UserProfileResponse;
import org.aibles.privatetraining.dto.response.UserResponse;
import org.aibles.privatetraining.entity.Role;
import org.aibles.privatetraining.entity.UserProfile;
import org.aibles.privatetraining.exception.BadRequestException;
import org.aibles.privatetraining.exception.InvalidOTPException;
import org.aibles.privatetraining.exception.UserNotFoundException;
import org.aibles.privatetraining.exception.UsernameNotFoundException;
import org.aibles.privatetraining.filter.JwtRequestFilter;
import org.aibles.privatetraining.repository.UserProfileRepository;
import org.aibles.privatetraining.util.EmailValidator;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@WebMvcTest(UserProfileService.class)
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @MockBean
    private UserProfileRepository userProfileRepository;


    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private EmailService emailService;

    @MockBean
    private RedisTemplate<String, String> redisTemplate;

    private PasswordEncoder passwordEncoder;

    private UserProfileRequest mockUserProfileRequest() {
        UserProfileRequest request = new UserProfileRequest();
        request.setUsername("john_doe");
        request.setEmail("john.doe@example.com");
        // Set other properties as needed
        return request;
    }

    private UserProfile mockUserProfileEntity() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId("userId");
        userProfile.setUsername("john_doe");
        userProfile.setEmail("john.doe@example.com");
        // Set other properties as needed
        return userProfile;
    }

    @Test
    public void test_CreateUser_Successful() {
        UserProfileRequest userProfileRequest = mockUserProfileRequest();
        UserProfile mockEntity = mockUserProfileEntity();

        Mockito.when(userProfileRepository.save(ArgumentMatchers.any(UserProfile.class))).thenReturn(mockEntity);

        UserProfileResponse response = userProfileService.createUser(userProfileRequest);

        Assertions.assertEquals(userProfileRequest.getUsername(), response.getUsername());
        Assertions.assertEquals(userProfileRequest.getEmail(), response.getEmail());
        // Assert other properties as needed
    }

    @Test
    public void test_GetById_Successful() {
        String userId = "userId";
        UserProfile mockEntity = mockUserProfileEntity();

        Mockito.when(userProfileRepository.findById(userId)).thenReturn(Optional.of(mockEntity));

        UserProfileResponse response = userProfileService.getById(userId);

        Assertions.assertEquals(mockEntity.getUsername(), response.getUsername());
        Assertions.assertEquals(mockEntity.getEmail(), response.getEmail());
        // Assert other properties as needed
    }

    @Test
    public void test_GetById_NotFound() {
        String userId = "invalidUserId";

        Mockito.when(userProfileRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userProfileService.getById(userId));
    }

    @Test
    public void test_UpdateUser_Successful() {
        String userId = "userId";
        UserProfileRequest userProfileRequest = mockUserProfileRequest();
        UserProfile mockEntity = mockUserProfileEntity();

        Mockito.when(userProfileRepository.findById(userId)).thenReturn(Optional.of(mockEntity));
        Mockito.when(userProfileRepository.save(mockEntity)).thenReturn(mockEntity);

        UserProfileResponse response = userProfileService.updateUser(userId, userProfileRequest);

        Assertions.assertEquals(userProfileRequest.getUsername(), response.getUsername());
        Assertions.assertEquals(userProfileRequest.getEmail(), response.getEmail());
    }

    @Test
    public void test_UpdateUser_NotFound() {
        String userId = "invalidUserId";
        UserProfileRequest userProfileRequest = mockUserProfileRequest();

        Mockito.when(userProfileRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userProfileService.updateUser(userId, userProfileRequest));
    }

    @Test
    public void test_Delete_Successful() {
        String userId = "userId";

        Mockito.when(userProfileRepository.existsById(userId)).thenReturn(true);
        Mockito.doNothing().when(userProfileRepository).deleteById(userId);

        Assertions.assertDoesNotThrow(() -> userProfileService.delete(userId));
    }

    @Test
    public void test_Delete_NotFound() {
        String userId = "invalidUserId";

        Mockito.when(userProfileRepository.existsById(userId)).thenReturn(false);

        Assertions.assertThrows(UserNotFoundException.class, () -> userProfileService.delete(userId));
    }

    @Test
    public void test_GetAll_Successful() {
        UserProfile mockEntity = mockUserProfileEntity();
        List<UserProfile> mockUserProfiles = List.of(mockEntity);

        Mockito.when(userProfileRepository.findAll()).thenReturn(mockUserProfiles);

        List<UserResponse> responses = userProfileService.getAll();

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getUsername(), responses.get(0).getUsername());
        Assertions.assertEquals(mockEntity.getEmail(), responses.get(0).getEmail());
    }

    @Test
    public void test_GetByUsername_Successful() {
        String username = "john_doe";
        UserProfile mockEntity = mockUserProfileEntity();

        Mockito.when(userProfileRepository.findByUsername(username)).thenReturn(mockEntity);

        UserProfileResponse response = userProfileService.getByUsername(username);

        Assertions.assertEquals(mockEntity.getUsername(), response.getUsername());
        Assertions.assertEquals(mockEntity.getEmail(), response.getEmail());
    }

    @Test
    public void test_GetByUsername_NotFound() {
        String username = "invalidUsername";

        Mockito.when(userProfileRepository.findByUsername(username)).thenReturn(null);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userProfileService.getByUsername(username));
    }

    @Test
    public void test_Register_Successful() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("password");
        userRequest.setEmail("test@example.com");

        Mockito.mockStatic(EmailValidator.class);
        Mockito.when(EmailValidator.isValidEmail(userRequest.getEmail())).thenReturn(true);
        Mockito.when(userProfileRepository.save(ArgumentMatchers.any(UserProfile.class))).thenAnswer(invocation -> {
            UserProfile userProfile = invocation.getArgument(0);
            userProfile.setUserId("userId");
            return userProfile;
        });

        UserProfile result = userProfileService.register(userRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(userRequest.getUsername(), result.getUsername());
        Assertions.assertEquals(userRequest.getEmail(), result.getEmail());
        Assertions.assertFalse(result.getIsActive());
        Assertions.assertEquals(Role.USER, result.getRole());
    }

    @Test
    public void test_Register_InvalidEmail() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("password");
        userRequest.setEmail("invalid-email");
        Mockito.mockStatic(EmailValidator.class);
        Mockito.when(EmailValidator.isValidEmail(userRequest.getEmail())).thenReturn(false);

        Assertions.assertThrows(BadRequestException.class, () -> userProfileService.register(userRequest));
    }

    @Test
    public void test_SendOTP_Successful() {
        SendOTPRequest request = new SendOTPRequest();
        request.setEmail("test@example.com");
        request.setUsername("testuser");
        String otp = "123456";

        Mockito.mockStatic(EmailValidator.class);
        Mockito.when(EmailValidator.isValidEmail(request.getEmail())).thenReturn(true);
        Mockito.when(emailService.generateOTP()).thenReturn(otp);

        ValueOperations<String, String> valueOperations = Mockito.mock(ValueOperations.class);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        userProfileService.sendOTP(request);

        Mockito.verify(emailService).sendOTP(request.getEmail(), otp);
        Mockito.verify(valueOperations).set(request.getUsername(), otp, 2, TimeUnit.MINUTES);
    }

    @Test
    public void test_SendOTP_InvalidEmail() {
        SendOTPRequest request = new SendOTPRequest();
        request.setEmail("invalid-email");
        request.setUsername("testuser");

        Mockito.mockStatic(EmailValidator.class);
        Mockito.when(EmailValidator.isValidEmail(request.getEmail())).thenReturn(false);

        ValueOperations<String, String> valueOperations = Mockito.mock(ValueOperations.class);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);

        Assertions.assertThrows(BadRequestException.class, () -> userProfileService.sendOTP(request));
    }

    @Test
    public void test_VerifyOTP_Successful() {
        String username = "testuser";
        Integer otp = 123456;
        ActiveOTPRequest request = new ActiveOTPRequest(username, otp);

        ValueOperations<String, String> valueOperations = Mockito.mock(ValueOperations.class);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(username)).thenReturn(String.valueOf(otp));

        userProfileService.verifyOTP(request);

        Mockito.verify(userProfileRepository).findByUsername(username);
        Mockito.verify(userProfileRepository).save(Mockito.any(UserProfile.class));
        Mockito.verify(redisTemplate).delete(username);
    }


    @Test
    public void test_VerifyOTP_Invalid() {
        String username = "testuser";
        Integer otp = 123456;
        Integer invalidOtp = 654321;
        ActiveOTPRequest request = new ActiveOTPRequest(username, invalidOtp);

        ValueOperations<String, String> valueOperations = Mockito.mock(ValueOperations.class);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(valueOperations.get(username)).thenReturn(String.valueOf(otp));

        Assertions.assertThrows(InvalidOTPException.class, () -> userProfileService.verifyOTP(request));
    }
}
