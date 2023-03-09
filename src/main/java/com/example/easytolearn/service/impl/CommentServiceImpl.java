package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.CommentConverter;
import com.example.easytolearn.entity.Comment;
import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserImage;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.comment.BaseCommentModel;
import com.example.easytolearn.model.comment.CommentModel;
import com.example.easytolearn.model.comment.CreateCommentModel;
import com.example.easytolearn.model.comment.UpdateCommentModel;
import com.example.easytolearn.repository.CommentRepository;
import com.example.easytolearn.service.CommentService;
import com.example.easytolearn.service.CourseService;
import com.example.easytolearn.service.UserImageService;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserImageService userImageService;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public CommentModel createComment(CreateCommentModel createCommentModel) {
        validateVariablesForNullOrIsEmpty(createCommentModel);
        validateLengthVariables(createCommentModel);

        Course course = courseService.getById(createCommentModel.getCourseId());
        User user = userService.getCurrentUser();
        String commentText = createCommentModel.getComment();

        Comment comment = new Comment();
        comment.setCourseComment(commentText);
        comment.setUser(user);
        comment.setCourse(course);
        UserImage userImage = userImageService.getUserImageByUserId(user.getId());
        if (userImage != null) {
            comment.setUserImageUrl(userImage.getUserImageUrl());
        }
        save(comment);
        return commentConverter.convertFromEntity(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public CommentModel getCommentModelById(Long id) {
        return commentConverter.convertFromEntity(getById(id));
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<CommentModel> getAllCommentModelByCourseId(Long courseId) {
        return commentRepository
                .findAllByCourseId(courseId)
                .stream()
                .map(commentConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CommentModel updateModel(UpdateCommentModel updateCommentModel) {
        Long commentId = updateCommentModel.getId();
        Comment dataComment = getDataCommentByIdWithCheckAccess(commentId, false);

        validateVariablesForNullOrIsEmpty(updateCommentModel);
        validateLengthVariables(updateCommentModel);

        dataComment.setCourseComment(updateCommentModel.getComment());
        commentRepository.save(dataComment);
        return commentConverter.convertFromEntity(dataComment);
    }

    @Override
    public CommentModel deleteCommentById(Long id) {
        Comment deleteComment = getDataCommentByIdWithCheckAccess(id, true);
        commentRepository.delete(deleteComment);
        return commentConverter.convertFromEntity(deleteComment);
    }

    private void validateVariablesForNullOrIsEmpty(BaseCommentModel baseCommentModel) {
        if (baseCommentModel.getComment() == null || baseCommentModel.getComment().isEmpty())
            throw new ApiFailException("Комментарий не заполнен");

        if (baseCommentModel instanceof CreateCommentModel) {
            CreateCommentModel createCommentModel = (CreateCommentModel) baseCommentModel;
            if (createCommentModel.getCourseId() == null)
                throw new ApiFailException("Не указан ID курса");
            else {
                Long courseId = createCommentModel.getCourseId();
                Course course = courseService.getById(courseId);
                if (course == null)
                    throw new ApiFailException("Курс под ID " + courseId + " не найден");
            }
        }
    }

    private void validateLengthVariables(BaseCommentModel baseCommentModel) {
        if (baseCommentModel.getComment() != null && baseCommentModel.getComment().length() > 1000)
            throw new ApiFailException("Длинна символов ограниченно(1000)");
    }

    private Comment getDataCommentByIdWithCheckAccess(Long id, boolean isDelete) {
        if (id == null)
            throw new ApiFailException("Не указан ID комментария");

        Comment dataComment = getById(id);

        if (dataComment == null)
            throw new ApiFailException("Комментарий под ID " + id + " не найден");

        Long currentUserId = userService.getCurrentUser().getId();
        Long authorCommentId = dataComment.getUser().getId();

        String access = "Доступ ограничен";
        if (isDelete) {
            Long authorCourseId = courseService.getById(dataComment.getCourse().getId()).getUser().getId();
            if (!currentUserId.equals(authorCommentId) && !currentUserId.equals(authorCourseId))
                throw new ApiFailException(access);
        } else {
            if (!currentUserId.equals(authorCommentId))
                throw new ApiFailException(access);
        }
        return dataComment;
    }
}
