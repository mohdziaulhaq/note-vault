package com.notevault.services;

import com.notevault.dtos.UserDTO;
import com.notevault.models.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);
    List<User> getAllUsers();
    UserDTO getUserById(Long id);

    User findByUsername(String username);
    void updatePassword(Long userId, String newPassword);

    void generarePasswordResetToken(String email);
}
