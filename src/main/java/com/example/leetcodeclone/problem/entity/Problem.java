package com.example.leetcodeclone.problem.entity;

import com.example.leetcodeclone.topic.entity.Topic;
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
public class Problem {
    @Id
    private UUID id;
    private String question;
    private String title;
    private boolean isPaid;
    @ManyToMany(mappedBy = "problems")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Topic> topics;
}
