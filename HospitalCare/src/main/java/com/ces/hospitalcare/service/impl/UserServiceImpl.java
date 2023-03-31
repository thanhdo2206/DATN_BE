package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.repository.UserRepository;
import com.ces.hospitalcare.service.IUserService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class UserServiceImpl implements IUserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  private ModelMapper mapper;

  @Override
  public UserResponse findEmailByToken() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    String userEmail = userDetails.getUsername();

    var user = userRepository.findByEmail(userEmail).orElseThrow();

    UserDTO userDTO = mapper.map(user, UserDTO.class);

    return UserResponse.builder()
        .status(HttpStatus.OK.value())
        .user(userDTO)
        .build();
  }
}
