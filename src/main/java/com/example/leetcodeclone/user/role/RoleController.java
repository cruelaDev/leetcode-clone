package com.example.leetcodeclone.user.role;

import com.example.leetcodeclone.user.role.dto.RoleCreateDto;
import com.example.leetcodeclone.user.role.dto.RoleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody @Valid RoleCreateDto createDto) {
        RoleResponseDto roleResponseDto = roleService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<RoleResponseDto>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<RoleResponseDto> roleResponseDtos = roleService.getAll(predicate, pageable);
        return ResponseEntity.ok(roleResponseDtos);
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleResponseDto> get(@PathVariable String name){
        RoleResponseDto roleResponseDto = roleService.getByName(name);
        return ResponseEntity.ok(roleResponseDto);
    }
}
