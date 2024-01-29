package com.example.leetcodeclone.topic.entity;

import com.example.leetcodeclone.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Topic {
    @Id
    private String name;
    @ManyToMany
    @JoinTable(
            name = "problem_topic",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Problem> problems;
}
