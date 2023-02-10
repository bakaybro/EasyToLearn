package com.example.easytolearn.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson extends BaseEntity {
    @Column(name = "lesson_info", nullable = false, length = 1500)
    private String lessonInfo;

    @Column(name = "lesson_url")
    private String lessonUrl;

    @Column(name = "is_visible")
    private Boolean isVisible;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
