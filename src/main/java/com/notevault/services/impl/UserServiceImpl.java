package com.notevault.services.impl;

import com.notevault.dtos.UserDTO;
import com.notevault.enums.AppRole;
import com.notevault.models.PasswordResetToken;
import com.notevault.models.Role;
import com.notevault.models.User;
import com.notevault.repositories.PasswordResetTokenRepository;
import com.notevault.repositories.RoleRepository;
import com.notevault.repositories.UserRepository;
import com.notevault.services.UserService;
import com.notevault.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Value("${frontend.url}")
    private String frontendUrl;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void updateUserRole(Long userId, String roleName) {
       User user = userRepository.findById(userId).orElseThrow(
               () -> new RuntimeException("User with id " + userId + " not found")
       );
       AppRole appRole = AppRole.valueOf(roleName);
        Role role = roleRepository.findByRoleName(appRole).orElseThrow(
                () -> new RuntimeException("Role " + appRole + " not found")
        );
       user.setRole(role);
       userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return convertToDto(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        return user.orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        try{
            User user = userRepository.findById(userId).orElseThrow(
                    () -> new RuntimeException("User with id " + userId + " not found")
            );
            user.setPassword(newPassword);
            userRepository.save(user);
        }catch (RuntimeException e){
            new RuntimeException("Failed to update password");
        }
    }

    @Override
    public void generarePasswordResetToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found")
        );
        String token = UUID.randomUUID().toString();
        Instant expiryDate = Instant.now().plus(24, ChronoUnit.HOURS);

        PasswordResetToken resetToken = new PasswordResetToken(token,expiryDate, user);
        passwordResetTokenRepository.save(resetToken);

        String resetUrl = frontendUrl+"/reset-password?token="+token;
        emailService.sendPasswordResetEmail(email, resetUrl);


    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token).orElseThrow(
                () -> new RuntimeException("Invalid password reset token")
        );

        //check if used
        if(resetToken.isUsed()){
            throw new RuntimeException("Password reset token is used");
        }
        //check if expired
        if(resetToken.getExpiryDate().isBefore(Instant.now())){
            throw new RuntimeException("Token provided is expired. Please request new password");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // set token as used
        resetToken.setUsed(true);
        passwordResetTokenRepository.save(resetToken);
    }

    private UserDTO convertToDto(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate()
         );
    }
}
