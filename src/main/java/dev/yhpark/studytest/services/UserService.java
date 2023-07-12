package dev.yhpark.studytest.services;

import dev.yhpark.studytest.entities.UserEntity;
import dev.yhpark.studytest.mappers.UserMapper;
import dev.yhpark.studytest.results.user.RegisterResult;
import dev.yhpark.studytest.utils.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "dev.yhpark.studytest.services.UserService")
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public RegisterResult register(UserEntity user) {
        if (user == null ||
                !user.getEmail().matches("^(?=.{8,50}$)([\\da-zA-Z][\\da-zA-Z\\-_.]+[\\da-zA-Z])@([\\da-z][\\da-z\\-]*[\\da-z]\\.)?([\\da-z][\\da-z\\-]*[\\da-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$") ||
                !user.getPassword().matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?])([\\da-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{8,16})$")) {
            return RegisterResult.FAILURE;
        }
        if (this.userMapper.selectUserCountByEmail(user.getEmail()) > 0) {
            return RegisterResult.FAILURE_DUPLICATE_EMAIL;
        }
        user.setPassword(CryptoUtil.hashSha512(user.getPassword()));
        return this.userMapper.insertUser(user) > 0
                ? RegisterResult.SUCCESS
                : RegisterResult.FAILURE;
    }
}