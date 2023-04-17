package com.example.easytolearn.model.course;

import com.example.easytolearn.model.CourseImageModel;
import com.example.easytolearn.model.LikeModel;
import com.example.easytolearn.model.comment.CommentModel;
import com.example.easytolearn.model.courseProgram.CourseProgramModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDataModel {
    private String authorFullName;
    private CourseModel courseModel;
    private CourseImageModel imageModel;
    private Long lessonCount;
    private List<CourseProgramModel> programs;
    private List<LikeModel> likes;
    private List<CommentModel> comments;
}