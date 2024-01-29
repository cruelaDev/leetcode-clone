package com.example.leetcodeclone.user;

import com.example.leetcodeclone.common.response.CommonResponse;
import com.example.leetcodeclone.security.JwtService;
import com.example.leetcodeclone.user.dto.*;
import com.example.leetcodeclone.user.otp.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OtpService otpService;
    private final JwtService jwtService;

    @PostMapping("/auth/validate")
    public ResponseEntity<CommonResponse> validatePhoneNumber(@RequestBody @Valid ValidatePhoneNumberRequestDto requestDto) {
        CommonResponse commonResponse = otpService.sendSms(requestDto);
        return ResponseEntity.ok(commonResponse);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserCreateDto createDto) {
        UserResponseDto userResponseDto = userService.create(createDto);
        String token = jwtService.generateToken(userResponseDto.getPhoneNumber());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(userResponseDto);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/create-user")
//    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateDto createDto) {
//        otpService.noSms(createDto.getPhoneNumber());
//        UserResponseDto userResponseDto = userService.createUser(createDto);
//        String token = jwtService.generateToken(userResponseDto.getPhoneNumber());
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                .body(userResponseDto);
//    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<UserResponseDto> signIn(@RequestBody @Valid UserSignInDto signInDto) {
        UserResponseDto responseDto = userService.signIn(signInDto);
        String token = jwtService.generateToken(responseDto.getPhoneNumber());
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(responseDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable UUID id, @RequestBody @Valid UserUpdateDto updateDto){
        UserResponseDto update = userService.update(id, updateDto);
        return ResponseEntity.ok(update);
    }

}
