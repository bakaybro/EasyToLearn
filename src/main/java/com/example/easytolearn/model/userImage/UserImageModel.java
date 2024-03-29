package com.example.easytolearn.model.userImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserImageModel {
    private Long id;
    private String userImageUrl;
    private Long userId;
}
