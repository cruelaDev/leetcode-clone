package com.example.leetcodeclone.user.role;

import com.example.leetcodeclone.common.service.GenericService;
import com.example.leetcodeclone.user.permission.PermissionRepository;
import com.example.leetcodeclone.user.permission.entity.Permission;
import com.example.leetcodeclone.user.role.dto.RoleCreateDto;
import com.example.leetcodeclone.user.role.dto.RoleResponseDto;
import com.example.leetcodeclone.user.role.dto.RoleUpdateDto;
import com.example.leetcodeclone.user.role.entity.Role;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class RoleService extends GenericService<Role, UUID, RoleResponseDto, RoleCreateDto, RoleUpdateDto> {
    private final RoleRepository repository;
    private final Class<Role> entityClass = Role.class;
    private final RoleDtoMapper mapper;
    private final PermissionRepository permissionRepository;

    @Override
    protected RoleResponseDto internalCreate(RoleCreateDto roleCreateDto) {
        Role role = mapper.toEntity(roleCreateDto);
        Set<String> dtoPermissionNames = roleCreateDto.getPermissions();
        Set<Permission> permissions = permissionRepository.findAllByNameIn(dtoPermissionNames);
        if (dtoPermissionNames.size() != permissions.size()) {
            Set<String> permissionNames = permissions.stream().map(Permission::getName).collect(Collectors.toSet());
            dtoPermissionNames.removeAll(permissionNames);
            throw new EntityNotFoundException("Permissions with these names are not found. Permissions: %s".formatted(dtoPermissionNames));
        }
        role.setPermissions(permissions);
        role.setId(UUID.randomUUID());
        Role saved = repository.save(role);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected RoleResponseDto internalUpdate(UUID uuid, RoleUpdateDto roleUpdateDto) {
        return null;
    }

    public RoleResponseDto getByName(String name){
        Role role = repository
                .findByName(name)
                .orElseThrow(
                        () -> new EntityNotFoundException("Role with name: %s not found".formatted(name))
                );
        return mapper.toResponseDto(role);
    }
}
