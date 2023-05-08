package com.ces.hospitalcare.service.impl;
import com.ces.hospitalcare.builder.UserBuilder;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.entity.UserEntity;
import com.ces.hospitalcare.http.exception.ResourceNotFoundException;
import com.ces.hospitalcare.http.request.UpdateUserProfileRequest;
import com.ces.hospitalcare.http.response.UserResponse;
import com.ces.hospitalcare.repository.UserRepository;
import com.ces.hospitalcare.service.IUserService;
import com.ces.hospitalcare.util.Role;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@NoArgsConstructor
public class UserServiceImpl implements IUserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private Cloudinary cloudinary;

  @Autowired
  private UserBuilder userBuilder;

  @Override
  public UserResponse getCurrentUser() {
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    String userEmail = userDetails.getUsername();

    UserEntity user = userRepository.findByEmail(userEmail).orElseThrow();
    UserDTO userDTO = mapper.map(user, UserDTO.class);

    return UserResponse.builder()
        .status(HttpStatus.OK.value())
        .user(userDTO)
        .build();
  }

  @Override
  public UserDTO updateUserProfilePicture(Long userId, MultipartFile multipartFile)
      throws IOException {
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

    Map response = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.asMap(
        "public_id",
        "profilePicture" + "/" + userId + "/" + multipartFile.getName()
    ));

    String profilePictureUrl = (String) response.get("secure_url");
    user.setProfilePicture(profilePictureUrl);
    userRepository.save(user);

    UserDTO userDTO = mapper.map(user, UserDTO.class);
    return userDTO;
  }

  @Override
  public UserDTO updateUserProfile(Long userId, UpdateUserProfileRequest updateUserProfileRequest)
      throws IOException {
    UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

    UserEntity updatedUser = userBuilder.userEntityUpdateBuild(updateUserProfileRequest, user);
    userRepository.save(updatedUser);

    UserDTO userDTO = mapper.map(user, UserDTO.class);
    return userDTO;
  }

  public List<UserDTO> createListUserDTO(
      List<UserEntity> listUserEntity) {
    List<UserDTO> listUserDTO = new ArrayList<>();

    for (UserEntity entity : listUserEntity) {
      UserDTO dto = mapper.map(entity, UserDTO.class);

      listUserDTO.add(dto);
    }

    return listUserDTO;
  }

  @Override
  public List<UserDTO> getAllPatient() {
    List<UserEntity> listPatientEntity = userRepository.getAllByRole(Role.PATIENT);
    return createListUserDTO(listPatientEntity);
  }

  @Override
  public UserDTO getDetailUser(Long userId) {
    return mapper.map(userRepository.getReferenceById(userId), UserDTO.class);
  }
}
