package com.example.easytolearn.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserImage extends BaseEntity {
    @Column(name = "user_image_url", nullable = false)
    private String userImageUrl;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;
}
