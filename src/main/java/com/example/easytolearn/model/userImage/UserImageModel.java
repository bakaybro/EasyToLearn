package com.example.easytolearn.model.userImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserImageModel {
    private Long id;
    private String userImageUrl;
    private Long userId;
}
