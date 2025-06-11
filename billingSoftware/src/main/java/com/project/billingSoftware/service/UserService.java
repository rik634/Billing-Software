package com.project.billingSoftware.service;

import com.project.billingSoftware.io.UserRequest;
import com.project.billingSoftware.io.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readUsers();

    void deleteUser(String id);
}
