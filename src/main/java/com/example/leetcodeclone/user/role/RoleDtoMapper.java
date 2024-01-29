package com.example.leetcodeclone.user.role;

import com.example.leetcodeclone.common.mapper.GenericMapper;
import com.example.leetcodeclone.user.permission.entity.Permission;
import com.example.leetcodeclone.user.role.dto.RoleCreateDto;
import com.example.leetcodeclone.user.role.dto.RoleResponseDto;
import com.example.leetcodeclone.user.role.dto.RoleUpdateDto;
import com.example.leetcodeclone.user.role.entity.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleDtoMapper extends GenericMapper<Role, RoleCreateDto, RoleResponseDto, RoleUpdateDto> {
    private final ModelMapper mapper;
    @Override
    public Role toEntity(RoleCreateDto roleCreateDto) {
        return mapper.map(roleCreateDto, Role.class);
    }

    @Override
    public RoleResponseDto toResponseDto(Role role) {
        RoleResponseDto responseDto = mapper.map(role, RoleResponseDto.class);
        Set<String> permissions = role
                .getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());
        responseDto.setPermissions(permissions);
        return responseDto;
    }

    @Override
    public void toEntity(RoleUpdateDto roleUpdateDto, Role role) {
        mapper.map(roleUpdateDto, role);
    }
}
