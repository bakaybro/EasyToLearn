package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.UserImageConverter;
import com.example.easytolearn.entity.Comment;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserImage;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.userImage.UserImageModel;
import com.example.easytolearn.repository.CommentRepository;
import com.example.easytolearn.repository.UserImageRepository;
import com.example.easytolearn.service.UserImageService;
import com.example.easytolearn.service.UserService;
import com.example.easytolearn.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;
    private final UserImageRepository userImageRepository;
    private final UserImageConverter userImageConverter;

    @Override
    public UserImage save(UserImage userImage) {
        return userImageRepository.save(userImage);
    }

    @Override
    public UserImageModel createUserImage(MultipartFile file) {
        User user = userService.getCurrentUser();

        UserImage userImage = getUserImageByUserId(user.getId());

        if (userImage != null)
            throw new ApiFailException("User image is already");

        String savedImageUrl = ImageUtil.saveImageInCloudinary(file);
        userImage = new UserImage();
        userImage.setUserImageUrl(savedImageUrl);
        userImage.setUser(user);
        save(userImage);
        updateUserImageUrlForCommentsByUserId(user.getId(), savedImageUrl);
        return userImageConverter.convertFromEntity(userImage);
    }

    @Override
    public UserImage getById(Long id) {
        return userImageRepository.findById(id).orElse(null);
    }

    @Override
    public UserImageModel getUserImageModelById(Long id) {
        return userImageConverter.convertFromEntity(getById(id));
    }


    @Override
    public UserImage getUserImageByUserId(Long userId) {
        return userImageRepository.findByUser_Id(userId).orElse(null);
    }

    @Override
    public UserImageModel getUserImageModelByUserId(Long userId) {
        return userImageConverter.convertFromEntity(getUserImageByUserId(userId));
    }

    @Override
    public List<UserImage> getAll() {
        return userImageRepository.findAll();
    }

    @Override
    public List<UserImageModel> getAllUserImageModel() {
        return getAll().stream()
                .map(userImageConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserImageModel updateUserImage(MultipartFile file) {
        Long currentUserId = userService.getCurrentUser().getId();
        UserImage updateUserImage = getDataUserImageByCurrentUser(currentUserId);
        String updateImageUrl = ImageUtil.saveImageInCloudinary(file);
        updateUserImage.setUserImageUrl(updateImageUrl);
        userImageRepository.save(updateUserImage);
        updateUserImageUrlForCommentsByUserId(currentUserId, updateImageUrl);
        return userImageConverter.convertFromEntity(updateUserImage);
    }

    @Override
    public UserImageModel deleteImage() {
        Long currentUserId = userService.getCurrentUser().getId();
        UserImage deleteUserImage = getDataUserImageByCurrentUser(currentUserId);
        userImageRepository.delete(deleteUserImage);
        updateUserImageUrlForCommentsByUserId(currentUserId, null);
        return userImageConverter.convertFromEntity(deleteUserImage);
    }

    private UserImage getDataUserImageByCurrentUser(Long currentUserId) {
        UserImage dataUserImage = getUserImageByUserId(currentUserId);

        if (dataUserImage == null)
            throw new ApiFailException("User image by user id " + currentUserId + " not found");

        return dataUserImage;
    }

    private void updateUserImageUrlForCommentsByUserId(Long userId, String url) {
        List<Comment> comments = commentRepository.findAllByUser_Id(userId);
        if (!comments.isEmpty()) {
            comments.forEach(comment -> comment.setUserImageUrl(url));
            commentRepository.saveAll(comments);
        }
    }
}