package com.example.leetcodeclone.topic;

import com.example.leetcodeclone.common.repository.GenericRepository;
import com.example.leetcodeclone.topic.entity.Topic;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends GenericRepository<Topic, String> {
}
