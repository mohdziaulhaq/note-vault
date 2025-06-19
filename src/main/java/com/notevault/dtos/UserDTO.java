package com.notevault.dtos;

import com.notevault.models.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private boolean isAccountNonLocked;
    private boolean isAccountNonExpired;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;
    private String twoFactorSecret;
    private boolean isTwoFactorEnabled;
    private String signUpMethod;
    private Role role;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;

    public UserDTO(Long userId, String username, String email, boolean isAccountNonLocked, boolean isAccountNonExpired, boolean isCredentialsNonExpired, boolean isEnabled, LocalDate credentialsExpiryDate, LocalDate accountExpiryDate, String twoFactorSecret, boolean isTwoFactorEnabled, String signUpMethod, Role role, LocalDateTime creationDate, LocalDateTime updatedDate) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.credentialsExpiryDate = credentialsExpiryDate;
        this.accountExpiryDate = accountExpiryDate;
        this.twoFactorSecret = twoFactorSecret;
        this.isTwoFactorEnabled = isTwoFactorEnabled;
        this.signUpMethod = signUpMethod;
        this.role = role;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public UserDTO() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public LocalDate getCredentialsExpiryDate() {
        return this.credentialsExpiryDate;
    }

    public LocalDate getAccountExpiryDate() {
        return this.accountExpiryDate;
    }

    public String getTwoFactorSecret() {
        return this.twoFactorSecret;
    }

    public boolean isTwoFactorEnabled() {
        return this.isTwoFactorEnabled;
    }

    public String getSignUpMethod() {
        return this.signUpMethod;
    }

    public Role getRole() {
        return this.role;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public void setAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setCredentialsExpiryDate(LocalDate credentialsExpiryDate) {
        this.credentialsExpiryDate = credentialsExpiryDate;
    }

    public void setAccountExpiryDate(LocalDate accountExpiryDate) {
        this.accountExpiryDate = accountExpiryDate;
    }

    public void setTwoFactorSecret(String twoFactorSecret) {
        this.twoFactorSecret = twoFactorSecret;
    }

    public void setTwoFactorEnabled(boolean isTwoFactorEnabled) {
        this.isTwoFactorEnabled = isTwoFactorEnabled;
    }

    public void setSignUpMethod(String signUpMethod) {
        this.signUpMethod = signUpMethod;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDTO)) return false;
        final UserDTO other = (UserDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        if (this.isAccountNonLocked() != other.isAccountNonLocked()) return false;
        if (this.isAccountNonExpired() != other.isAccountNonExpired()) return false;
        if (this.isCredentialsNonExpired() != other.isCredentialsNonExpired()) return false;
        if (this.isEnabled() != other.isEnabled()) return false;
        final Object this$credentialsExpiryDate = this.getCredentialsExpiryDate();
        final Object other$credentialsExpiryDate = other.getCredentialsExpiryDate();
        if (this$credentialsExpiryDate == null ? other$credentialsExpiryDate != null : !this$credentialsExpiryDate.equals(other$credentialsExpiryDate))
            return false;
        final Object this$accountExpiryDate = this.getAccountExpiryDate();
        final Object other$accountExpiryDate = other.getAccountExpiryDate();
        if (this$accountExpiryDate == null ? other$accountExpiryDate != null : !this$accountExpiryDate.equals(other$accountExpiryDate))
            return false;
        final Object this$twoFactorSecret = this.getTwoFactorSecret();
        final Object other$twoFactorSecret = other.getTwoFactorSecret();
        if (this$twoFactorSecret == null ? other$twoFactorSecret != null : !this$twoFactorSecret.equals(other$twoFactorSecret))
            return false;
        if (this.isTwoFactorEnabled() != other.isTwoFactorEnabled()) return false;
        final Object this$signUpMethod = this.getSignUpMethod();
        final Object other$signUpMethod = other.getSignUpMethod();
        if (this$signUpMethod == null ? other$signUpMethod != null : !this$signUpMethod.equals(other$signUpMethod))
            return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$creationDate = this.getCreationDate();
        final Object other$creationDate = other.getCreationDate();
        if (this$creationDate == null ? other$creationDate != null : !this$creationDate.equals(other$creationDate))
            return false;
        final Object this$updatedDate = this.getUpdatedDate();
        final Object other$updatedDate = other.getUpdatedDate();
        if (this$updatedDate == null ? other$updatedDate != null : !this$updatedDate.equals(other$updatedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        result = result * PRIME + (this.isAccountNonLocked() ? 79 : 97);
        result = result * PRIME + (this.isAccountNonExpired() ? 79 : 97);
        result = result * PRIME + (this.isCredentialsNonExpired() ? 79 : 97);
        result = result * PRIME + (this.isEnabled() ? 79 : 97);
        final Object $credentialsExpiryDate = this.getCredentialsExpiryDate();
        result = result * PRIME + ($credentialsExpiryDate == null ? 43 : $credentialsExpiryDate.hashCode());
        final Object $accountExpiryDate = this.getAccountExpiryDate();
        result = result * PRIME + ($accountExpiryDate == null ? 43 : $accountExpiryDate.hashCode());
        final Object $twoFactorSecret = this.getTwoFactorSecret();
        result = result * PRIME + ($twoFactorSecret == null ? 43 : $twoFactorSecret.hashCode());
        result = result * PRIME + (this.isTwoFactorEnabled() ? 79 : 97);
        final Object $signUpMethod = this.getSignUpMethod();
        result = result * PRIME + ($signUpMethod == null ? 43 : $signUpMethod.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $creationDate = this.getCreationDate();
        result = result * PRIME + ($creationDate == null ? 43 : $creationDate.hashCode());
        final Object $updatedDate = this.getUpdatedDate();
        result = result * PRIME + ($updatedDate == null ? 43 : $updatedDate.hashCode());
        return result;
    }

    public String toString() {
        return "UserDTO(userId=" + this.getUserId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", isAccountNonLocked=" + this.isAccountNonLocked() + ", isAccountNonExpired=" + this.isAccountNonExpired() + ", isCredentialsNonExpired=" + this.isCredentialsNonExpired() + ", isEnabled=" + this.isEnabled() + ", credentialsExpiryDate=" + this.getCredentialsExpiryDate() + ", accountExpiryDate=" + this.getAccountExpiryDate() + ", twoFactorSecret=" + this.getTwoFactorSecret() + ", isTwoFactorEnabled=" + this.isTwoFactorEnabled() + ", signUpMethod=" + this.getSignUpMethod() + ", role=" + this.getRole() + ", creationDate=" + this.getCreationDate() + ", updatedDate=" + this.getUpdatedDate() + ")";
    }
}
