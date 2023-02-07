package com.example.easytolearn.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateCommentModel implements BaseCommentModel {
    private String comment;
    private Long courseId;
}
