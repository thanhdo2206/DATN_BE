package com.ces.hospitalcare.builder;
import com.ces.hospitalcare.entity.UserEntity;
import com.ces.hospitalcare.http.request.RegisterRequest;
import com.ces.hospitalcare.http.request.UpdateUserProfileRequest;
import org.springframework.stereotype.Component;

@Component
public class UserBuilder {
  public UserEntity userEntityBuild(RegisterRequest request, String password) {
    return UserEntity.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .password(password)
        .email(request.getEmail())
        .role(request.getRole())
        .build();
  }

  public UserEntity userEntityUpdateBuild(UpdateUserProfileRequest request, UserEntity userEntity) {
    userEntity.setFirstName(request.getFirstName());
    userEntity.setLastName(request.getLastName());
    userEntity.setGender(request.getGender());
    userEntity.setPhoneNumber(request.getPhoneNumber());
    userEntity.setAddress(request.getAddress());
    return userEntity;
  }
}
