package com.example.easytolearn.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentModel {
    private Long id;
    private String comment;
    private Long userId;
    private String username;
    private String userImageUrl;
    private Long courseId;
}
