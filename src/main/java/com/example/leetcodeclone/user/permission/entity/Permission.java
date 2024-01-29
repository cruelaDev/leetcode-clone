package com.example.leetcodeclone.user.permission.entity;

import com.example.leetcodeclone.user.entity.User;
import com.example.leetcodeclone.user.role.entity.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Permission {
    @Id
    private UUID id;
    private String name;
    @ManyToMany(mappedBy = "permissions")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Role> roles;
    @ManyToMany(mappedBy = "permissions")
    private Set<User> users;
}
