package com.example.leetcodeclone;

import com.example.leetcodeclone.user.UserRepository;
import com.example.leetcodeclone.user.entity.User;
import com.example.leetcodeclone.user.role.RoleRepository;
import com.example.leetcodeclone.user.role.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@RequiredArgsConstructor
public class LeetcodeCloneApplication implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LeetcodeCloneApplication.class, args);
    }

    @Override
    public void run(String... args) {
        createAdmin();
    }

    private void createAdmin() {
        String phoneNumber = "998937797499";
        String email = "asilbekdev@gmail.com";
        if (!userRepository.existsByPhoneNumberOrEmail(phoneNumber, email)) {
            Role role = roleRepository.findByName("ADMIN").get();
            User user = new User(UUID.randomUUID(),
                    "Admin",
                    "Admin",
                    phoneNumber,
                    email,
                    passwordEncoder.encode("admin"),
                    LocalDate.now(),
                    Set.of(role),
                    Collections.emptySet(),
                    null, null
            );

            userRepository.save(user);
        }
    }
}
