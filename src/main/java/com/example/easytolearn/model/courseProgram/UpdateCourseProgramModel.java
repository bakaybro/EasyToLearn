package com.example.easytolearn.model.courseProgram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateCourseProgramModel implements BaseCourseProgramModel {
    private Long id;
    private String title;
    private String description;

    @Override
    public Long getCourseId() {
        return null;
    }
}
