package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.entity.UserEntity;
import com.ces.hospitalcare.http.exception.AlreadyExistException;
import com.ces.hospitalcare.http.exception.LoginUserException;
import com.ces.hospitalcare.http.request.AuthenticationRequest;
import com.ces.hospitalcare.http.request.RegisterRequest;
import com.ces.hospitalcare.http.response.LoginResponse;
import com.ces.hospitalcare.http.response.RegisterResponse;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.repository.UserRepository;
import com.ces.hospitalcare.security.service.JwtService;
import com.ces.hospitalcare.service.IAuthenticationService;
import com.ces.hospitalcare.util.ExceptionMessage;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
  @Autowired
  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  @Autowired
  private ModelMapper mapper;

  public RegisterResponse register(RegisterRequest request) {
    if (userRepository.findByEmail(request.getEmail()).isEmpty() == false) {
      throw new AlreadyExistException("Email already exists");
    }
    var user = UserEntity.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .phoneNumber(request.getPhoneNumber())
        .password(passwordEncoder.encode(request.getPassword()))
        .email(request.getEmail())
        .gender(request.getGender())
        .role(request.getRole())
        .build();
    userRepository.save(user);
    return RegisterResponse.builder()
        .message("Register successfully")
        .status(HttpStatus.CREATED.value())
        .build();
  }

  public LoginResponse authenticate(AuthenticationRequest request) {
    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new LoginUserException(
            ExceptionMessage.USERNAME_PASSWORD_INVALIDATE.getMessage()));
    boolean validatePassword = passwordEncoder.matches(request.getPassword(), user.getPassword());

    if (!validatePassword) {
      throw new LoginUserException(ExceptionMessage.USERNAME_PASSWORD_INVALIDATE.getMessage());
    }

    var jwtToken = jwtService.generateToken(user);
    var cookie = ResponseCookie.from("token", jwtToken)
        .httpOnly(true)
        .maxAge(Duration.ofHours(1))
        .path("/")
//        .sameSite("None")
//        .secure(true)
        .build();

    var userDTO = mapper.map(user, UserDTO.class);
    UserResponse authResponse = new UserResponse(userDTO, HttpStatus.ACCEPTED.value());

    return LoginResponse.builder()
        .token(cookie.toString())
        .authResponse(authResponse)
        .build();
  }
}
