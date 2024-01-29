package com.example.leetcodeclone.user.permission;

import com.example.leetcodeclone.common.repository.GenericRepository;
import com.example.leetcodeclone.user.permission.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface PermissionRepository extends GenericRepository<Permission, UUID> {
    Set<Permission> findAllByNameIn(Set<String> names);
}
