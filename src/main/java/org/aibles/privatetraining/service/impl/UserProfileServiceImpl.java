package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.*;
import org.aibles.privatetraining.dto.response.AuthenticationResponse;
import org.aibles.privatetraining.dto.response.UserProfileResponse;
import org.aibles.privatetraining.dto.response.UserResponse;
import org.aibles.privatetraining.entity.Role;
import org.aibles.privatetraining.entity.UserProfile;
import org.aibles.privatetraining.exception.*;
import org.aibles.privatetraining.repository.UserProfileRepository;
import org.aibles.privatetraining.service.EmailService;
import org.aibles.privatetraining.service.UserProfileService;
import org.aibles.privatetraining.util.EmailValidator;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private final UserProfileRepository repository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private EmailService emailService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserProfileResponse createUser(UserProfileRequest request) {
        log.info("(createUser)request :{}", request);
        checkEmail(request.getEmail());
        checkUsername(request.getUsername());
        UserProfile userProfile = UserProfile.of(request);
        repository.save(userProfile);
        return UserProfileResponse.from(userProfile);
    }

    @Override
    public UserProfileResponse getById(String id) {
        var user =
                repository
                        .findById(id)
                        .orElseThrow(() -> {
                            throw new UserNotFoundException(id);
                        });
        return UserProfileResponse.from(user);
    }


    @Transactional
    @Override
    public void delete(String id) {
        log.info("(delete)id: {}", id);
        repository.findById(id);
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public void checkUserId(String userId) {
        if (!repository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public List<UserResponse> getAll() {
        List<UserProfile> userProfiles = repository.findAll();
        return userProfiles.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserProfile register(UserRequest userRequest) {
        if (!EmailValidator.isValidEmail(userRequest.getEmail())) {
            throw new BadRequestException();
        }

        UserProfile newUser = new UserProfile();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setEmail(userRequest.getEmail());
        newUser.setIsActive(false);
        return userProfileRepository.save(newUser);
    }

    @Override
    public void sendOTP(SendOTPRequest request) {
        if (!EmailValidator.isValidEmail(request.getEmail())) {
            throw new BadRequestException();
        }
        String otp = emailService.generateOTP();

        // Send OTP to the provided email
        emailService.sendOTP(request.getEmail(), otp);
        saveOTPToRedis(request.getUsername(), otp);

    }

    @Override
    public void verifyOTP(ActiveOTPRequest request) {
        String cachedOTP = getCachedOTPFromRedis(request.getUsername()); // Lấy OTP từ Redis
        if (!request.getOtp().equals(cachedOTP)) {
            UserProfile userProfile = userProfileRepository.findByUsername(request.getUsername());
            userProfile.setIsActive(true); // Cập nhật trường isActive thành true
            userProfileRepository.save(userProfile); // Lưu thay đổi vào cơ sở dữ liệu
            redisTemplate.delete(request.getUsername()); // Xóa OTP khỏi Redis
        } else {
            throw new InvalidOTPException();
        }
    }


    private String getCachedOTPFromRedis(String username) {
        log.info("(getCachedOTPFromRedis)username: {}", username);
        return redisTemplate.opsForValue().get(username);
    }

    private void saveOTPToRedis(String username, String otp) {
        redisTemplate.opsForValue().set(username, otp, 2, TimeUnit.MINUTES); // Lưu trong Redis với thời gian hết hạn 2 phút
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        if (!repository.existsByUsername(request.getUsername())) {
            throw new UsernameNotFoundException(request.getUsername());
        }
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        if (!validatePassword(userDetails, request.getPassword())) {
            throw new PasswordIncorrect(request.getPassword());
        }

        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        final long accessTokenExpiration = jwtTokenUtil.getAccessTokenExpiration(accessToken);
        final long refreshTokenExpiration = jwtTokenUtil.getRefreshTokenExpiration(refreshToken);

        return new AuthenticationResponse(accessToken, refreshToken, accessTokenExpiration, refreshTokenExpiration);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        UserProfile user = userProfileRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userProfileRepository.save(user);
    }

    @Override
    @Transactional
    public UserProfileResponse updateUser(String id, UserProfileRequest userProfileRequest) {
        log.info("(updateUser) id: {}, userProfileRequest: {}", id, userProfileRequest);
        var user = repository
                .findById(id)
                .orElseThrow(() -> {
                    throw new UserNotFoundException(id);
                });
        user.setUsername(userProfileRequest.getUsername());
        user.setEmail(userProfileRequest.getEmail());
        repository.save(user);
        return UserProfileResponse.from(user);
    }

    void checkEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistedException(email);
        }
    }

    void checkUsername(String username) {
        if (repository.existsByUsername(username)) {
            throw new UsernameAlreadyExistedException(username);
        }
    }

    public boolean validatePassword(UserDetails userDetails, String rawPassword) {
        return passwordEncoder.matches(rawPassword, userDetails.getPassword());
    }

    @Override
    public UserProfileResponse getByUsername(String username) {
        log.info("(getByUsername)username: {}", username);
        var user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return UserProfileResponse.from(user);
    }

    @Override
    public List<UserResponse> searchUserProfile(String username, String email) {
        List<UserProfile> userProfiles = userProfileRepository.searchUserProfile(username, email);
        return userProfiles.stream()
                .map(UserResponse::from)
                .collect(Collectors.toList());
    }

}

