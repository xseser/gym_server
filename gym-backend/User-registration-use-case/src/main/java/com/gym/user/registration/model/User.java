package com.gym.user.registration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import static com.gym.user.registration.model.authorities.Auth.getAdminAuthorities;
import static com.gym.user.registration.model.authorities.Auth.getMemberAuthorities;
import static com.gym.user.registration.model.authorities.Auth.getPremiumAuthorities;
import static com.gym.user.registration.model.authorities.Auth.getSuperAdminAuthorities;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users", schema = "gym")
public class User implements UserDetails {

    @Id
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "mail", unique = true)
    private String mail;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "role_expiration_time")
    private LocalDateTime roleExpirationTime;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case MEMBER -> getMemberAuthorities();
            case PREMIUM -> getPremiumAuthorities();
            case SUPER_ADMIN -> getSuperAdminAuthorities();
            case ADMIN -> getAdminAuthorities();
        };
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isVerified;
    }
}
