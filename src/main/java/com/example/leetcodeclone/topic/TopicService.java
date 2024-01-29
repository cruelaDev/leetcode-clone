package com.example.leetcodeclone.topic;
import com.example.leetcodeclone.common.service.GenericService;
import com.example.leetcodeclone.topic.dto.TopicDto;
import com.example.leetcodeclone.topic.entity.Topic;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Getter
public class TopicService extends GenericService<Topic, String, TopicDto, TopicDto, TopicDto> {
    private final TopicRepository repository;
    private final Class<Topic> entityClass = Topic.class;
    private final TopicDtoMapper mapper;

    @Override
    protected TopicDto internalCreate(TopicDto topicDto) {
        Topic topic = mapper.toEntity(topicDto);
        Topic saved = repository.save(topic);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected TopicDto internalUpdate(String name, TopicDto topicDto) {
        Topic topic = repository.findById(name).orElseThrow(() -> new EntityNotFoundException("Topic with name %s not found".formatted(name)));
        mapper.toEntity(topicDto,topic);
        Topic saved = repository.save(topic);
        return mapper.toResponseDto(saved);
    }
}
