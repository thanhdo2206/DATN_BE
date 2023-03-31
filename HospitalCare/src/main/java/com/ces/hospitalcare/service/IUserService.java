package com.ces.hospitalcare.service;
import com.ces.hospitalcare.http.response.UserResponse;

public interface IUserService {
  UserResponse findEmailByToken();
}
