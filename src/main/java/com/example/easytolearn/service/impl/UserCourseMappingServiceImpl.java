package com.example.easytolearn.service.impl;

import com.example.easytolearn.converter.UserCourseMappingConverter;
import com.example.easytolearn.entity.Course;
import com.example.easytolearn.entity.User;
import com.example.easytolearn.entity.UserBalance;
import com.example.easytolearn.entity.UserCourseMapping;
import com.example.easytolearn.exception.ApiFailException;
import com.example.easytolearn.model.UserCourseMappingModel;
import com.example.easytolearn.model.course.CourseDataModel;
import com.example.easytolearn.model.user.BaseUserModel;
import com.example.easytolearn.repository.UserCourseMappingRepository;
import com.example.easytolearn.service.CourseService;
import com.example.easytolearn.service.UserBalanceService;
import com.example.easytolearn.service.UserCourseMappingService;
import com.example.easytolearn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCourseMappingServiceImpl implements UserCourseMappingService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserBalanceService userBalanceService;
    private final UserCourseMappingConverter userCourseMappingConverter;
    private final UserCourseMappingRepository userCourseMappingRepository;

    @Override
    public UserCourseMapping save(UserCourseMapping userCourseMapping) {
        return userCourseMappingRepository.save(userCourseMapping);
    }

    @Override
    public UserCourseMappingModel createByCourseId(Long courseId) {
        Course dataCourse = getCourseWithCheckForNull(courseId);

        User user = userService.getCurrentUser();
        Long currentUserId = user.getId();
        Long authorCourseId = dataCourse.getUser().getId();

        validatePurchaseWasMade(courseId, currentUserId, authorCourseId);
        transaction(dataCourse.getPrice(), currentUserId, authorCourseId);
        return userCourseMappingConverter.convertFromEntity(save(new UserCourseMapping(user, dataCourse)));
    }

    @Override
    public UserCourseMapping getById(Long id) {
        return userCourseMappingRepository.findById(id).orElse(null);
    }

    @Override
    public UserCourseMappingModel getUserCourseMappingModelById(Long id) {
        return userCourseMappingConverter.convertFromEntity(getById(id));
    }

    @Override
    public List<BaseUserModel> getAllCustomersByCourseId(Long courseId) {
        return userCourseMappingRepository
                .findAllByCourseId(courseId)
                .stream()
                .map(UserCourseMapping::getUser)
                .map(i -> userService.getUserModelById(i.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public UserCourseMapping getByCourseIdAndUserId(Long courseId, Long userId) {
        return userCourseMappingRepository
                .findByCourseIdAndUserId(courseId, userId)
                .orElse(null);
    }

    @Override
    public List<UserCourseMapping> getAll() {
        return userCourseMappingRepository.findAll();
    }

    @Override
    public List<CourseDataModel> getAllPurchasedCoursesBuUserId(Long userId) {
        return userCourseMappingRepository
                .findAllByUserId(userId)
                .stream()
                .map(UserCourseMapping::getCourse)
                .map(i -> courseService.getCourseDataModelByCourseId(i.getId()))
                .collect(Collectors.toList());
    }

    private void transaction(BigDecimal coursePrice, Long currentUserId, Long authorCourseUserId) {
        UserBalance currentUserBalance = userBalanceService.getUserBalanceByUserId(currentUserId);

        if (currentUserBalance.getBalance().compareTo(coursePrice) < 0)
            throw new ApiFailException("Недостаточно средств");

        currentUserBalance.setBalance(currentUserBalance.getBalance().subtract(coursePrice));
        userBalanceService.save(currentUserBalance);

        UserBalance authorUserBalance = userBalanceService.getUserBalanceByUserId(authorCourseUserId);
        authorUserBalance.setBalance(authorUserBalance.getBalance().add(coursePrice));
        userBalanceService.save(authorUserBalance);
    }

    private void validatePurchaseWasMade(Long courseId,  Long currentUserId, Long authorCourseId) {
        UserCourseMapping dataUserCourseMapping = getByCourseIdAndUserId(courseId, currentUserId);
        if (dataUserCourseMapping != null)
            throw new ApiFailException("Курс уже приобретен");
        if (authorCourseId.equals(currentUserId))
            throw new ApiFailException("Автор не может купить свои курсы");
    }

    private Course getCourseWithCheckForNull(Long courseId) {
        Course dataCourse = courseService.getById(courseId);
        if (dataCourse == null)
            throw new ApiFailException("Курс под ID " + courseId + " не найден");

        return dataCourse;
    }
}
