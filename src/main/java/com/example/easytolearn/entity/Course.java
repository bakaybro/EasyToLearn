package com.example.easytolearn.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "course_name", length = 100, nullable = false)
    private String courseName;

    @Column(length = 100)
    private String email;

    @Column(name = "phone_number", length = 100)
    private String phoneNumber;

    @Column(name = "course_short_info", length = 300, nullable = false)
    private String courseShortInfo;

    @Column(name = "course_info_title", length = 100)
    private String courseInfoTitle;

    @Column(name = "course_info", length = 2000)
    private String courseInfo;

    @Column(name = "course_info_url")
    private String courseInfoUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}