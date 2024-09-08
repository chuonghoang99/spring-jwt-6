package com.chuong.app.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User extends AbstractEntity implements UserDetails {

    @NotBlank(message = "The name field can't be not blank.")
    private String name;

    @NotBlank(message = "The username field can't be not blank.")
    @Column(unique = true)
    private String username;


    @NotBlank(message = "The email field can't be not blank.")
    @Column(unique = true)
    @Email(message = "Please enter email in proper format.")
    private String email;

    @NotBlank(message = "The password field can't be not blank.")
    @Size(min = 5, message = "The password must have at least 5 characters.")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy =
            "user")
    @JsonIgnore
    private Set<Address> addresses = new HashSet<>();

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private RefreshToken refreshToken;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}



