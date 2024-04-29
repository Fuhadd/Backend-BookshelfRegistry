package com.shelfscribe.backendbookshelfregistry.repository;

import com.shelfscribe.backendbookshelfregistry.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
