package com.example.leetcodeclone.user.permission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDto {
    private UUID id;
    private String name;
}
