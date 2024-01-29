package com.example.leetcodeclone.user.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateDto {
    private String name;
    private Set<String> permissions;
}
