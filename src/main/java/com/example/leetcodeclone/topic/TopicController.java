package com.example.leetcodeclone.topic;

import com.example.leetcodeclone.topic.dto.TopicDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @PreAuthorize("hasAnyAuthority('topic:create')")
    @PostMapping
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicDto requestDto) {
        TopicDto responseDto = topicService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PreAuthorize("hasAnyAuthority('topic:read')")
    @GetMapping
    public ResponseEntity<Page<TopicDto>> get(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<TopicDto> responseDtos = topicService.getAll(predicate, pageable);
        return ResponseEntity.ok(responseDtos);
    }

    @PreAuthorize("hasAnyAuthority('topic:read')")
    @GetMapping("/{name}")
    public ResponseEntity<TopicDto> get(@PathVariable String name) {
        TopicDto topicDto = topicService.get(name);
        return ResponseEntity.ok(topicDto);
    }

    @PreAuthorize("hasAnyAuthority('topic:update')")
    @PutMapping("/{name}")
    public ResponseEntity<TopicDto> update(@PathVariable String name, @RequestBody TopicDto updateDto) {
        TopicDto responseEntity = topicService.update(name, updateDto);
        return ResponseEntity.ok(responseEntity);
    }

    @PreAuthorize("hasAnyAuthority('topic:delete')")
    @DeleteMapping("/{name}")
    public ResponseEntity<TopicDto> delete(@PathVariable String name) {
        topicService.delete(name);
        ResponseEntity.status(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
