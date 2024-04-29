package com.shelfscribe.backendbookshelfregistry.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shelfscribe.backendbookshelfregistry.entity.*;
import com.shelfscribe.backendbookshelfregistry.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String accountNumber;
    private String accountName;
    private String bankName;
    private Double walletBalance;
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
//    @OneToMany(mappedBy = "user")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private List<Book> books;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Favourite> favourites;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Reading> readings;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Rating> ratings;

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    private List<Cart> cart;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<AuditLog> auditLogs;


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
