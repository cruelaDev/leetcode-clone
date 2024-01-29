package com.example.leetcodeclone.user;

import com.example.leetcodeclone.common.repository.GenericRepository;
import com.example.leetcodeclone.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends GenericRepository<User, UUID> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberOrEmail(String phoneNumber, String email);
}
