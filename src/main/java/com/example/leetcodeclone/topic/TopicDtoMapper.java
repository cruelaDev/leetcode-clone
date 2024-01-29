package com.example.leetcodeclone.topic;

import com.example.leetcodeclone.common.mapper.GenericMapper;
import com.example.leetcodeclone.topic.dto.TopicDto;
import com.example.leetcodeclone.topic.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TopicDtoMapper extends GenericMapper<Topic, TopicDto, TopicDto, TopicDto> {
    private final ModelMapper mapper;

    @Override
    public Topic toEntity(TopicDto topicDto) {
        Topic topic = mapper.map(topicDto, Topic.class);
        topic.setName(topic.getName().toLowerCase());
        return topic;
    }

    @Override
    public TopicDto toResponseDto(Topic topic) {
        TopicDto topicDto = mapper.map(topic, TopicDto.class);
        String capitalizeName = StringUtils.capitalize(topicDto.getName());
        topicDto.setName(capitalizeName);
        return topicDto;
    }

    @Override
    public void toEntity(TopicDto topicDto, Topic topic) {
        topicDto.setName(topicDto.getName().toLowerCase());
        mapper.map(topicDto, topic);
    }
}
