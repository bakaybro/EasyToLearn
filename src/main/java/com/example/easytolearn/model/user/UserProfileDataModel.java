package com.example.easytolearn.model.user;

import com.example.easytolearn.model.balance.UserBalanceModel;
import com.example.easytolearn.model.course.CourseDataModel;
import com.example.easytolearn.model.userImage.UserImageModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDataModel {
    private String token;
    private UserModelToSend userModelToSend;
    private UserBalanceModel userBalanceModel;
    private UserImageModel userImageModel;
    private List<CourseDataModel> userCreateCourseModels;
    private List<CourseDataModel> userPurchasedCourseModels;
}