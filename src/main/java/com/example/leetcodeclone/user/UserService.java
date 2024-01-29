package com.example.leetcodeclone.user;

import com.example.leetcodeclone.common.exception.OtpException;
import com.example.leetcodeclone.common.service.GenericService;
import com.example.leetcodeclone.user.dto.UserCreateDto;
import com.example.leetcodeclone.user.dto.UserResponseDto;
import com.example.leetcodeclone.user.dto.UserSignInDto;
import com.example.leetcodeclone.user.dto.UserUpdateDto;
import com.example.leetcodeclone.user.entity.User;
import com.example.leetcodeclone.user.otp.OtpRepository;
import com.example.leetcodeclone.user.otp.entity.Otp;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class UserService extends GenericService<User, UUID, UserResponseDto, UserCreateDto, UserUpdateDto>
        implements UserDetailsService {
    private final UserRepository repository;
    private final OtpRepository otpRepository;
    private final Class<User> entityClass = User.class;
    private final UserDtoMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected UserResponseDto internalCreate(UserCreateDto userCreateDto) {
        User entity = mapper.toEntity(userCreateDto);
        entity.setId(UUID.randomUUID());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        isPhoneNumberVerified(userCreateDto.getPhoneNumber());

        User saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public UserResponseDto internalUpdate(UUID uuid, UserUpdateDto userUpdateDto) {
        return null;
    }

    private void isPhoneNumberVerified(String phoneNumber) {
        Otp otp = otpRepository
                .findById(phoneNumber)
                .orElseThrow(() -> new OtpException.PhoneNumberNotVerified(phoneNumber));

        if (!otp.isVerified()) {
            throw new OtpException.PhoneNumberNotVerified(phoneNumber);
        }
    }

    @Transactional
    public UserResponseDto signIn(UserSignInDto signInDto) {
        User user = repository.findByPhoneNumber(signInDto.getPhoneNumber())
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Username or password is not correct");
        }

        return mapper.toResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByPhoneNumber(username).orElseThrow(
                // todo handle this exception
                () -> new EntityNotFoundException("User with phone number: %s not found".formatted(username))
        );
    }
}
