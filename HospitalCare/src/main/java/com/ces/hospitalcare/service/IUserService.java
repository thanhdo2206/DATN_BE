package com.ces.hospitalcare.service;
import com.ces.hospitalcare.dto.UserDTO;
import com.ces.hospitalcare.http.request.UpdateUserProfileRequest;
import com.ces.hospitalcare.http.response.UserResponse;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService {
  UserResponse getCurrentUser();

  UserDTO updateUserProfilePicture(Long userId, MultipartFile multipartFile) throws IOException;

  UserDTO updateUserProfile(Long userId, UpdateUserProfileRequest updateUserProfileRequest)
      throws IOException;
}
