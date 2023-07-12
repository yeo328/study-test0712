package dev.yhpark.studytest.mappers;

import dev.yhpark.studytest.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertUser(UserEntity user);

    int selectUserCountByEmail(@Param(value = "email") String email);
}