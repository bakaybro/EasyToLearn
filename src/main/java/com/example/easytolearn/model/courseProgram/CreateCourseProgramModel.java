package com.example.easytolearn.model.courseProgram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateCourseProgramModel implements BaseCourseProgramModel {
    private String title;
    private String description;
    private Long courseId;

    @Override
    public Long getId() {
        return null;
    }
}
