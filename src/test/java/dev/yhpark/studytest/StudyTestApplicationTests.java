package dev.yhpark.studytest;

import dev.yhpark.studytest.entities.UserEntity;
import dev.yhpark.studytest.results.user.RegisterResult;
import dev.yhpark.studytest.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudyTestApplicationTests {
    @Autowired
    private UserService userService; //멤버변수

    @Test
    void contextLoads() {
        UserEntity user = new UserEntity()
                .setEmail("testuser1@sample.com")
                .setPassword("test1234!");
        RegisterResult result = this.userService.register(user);
        Assertions.assertEquals(result, RegisterResult.SUCCESS); //전달받은 result랑 success랑 같아야함

        //Assertions 클래스
        //assertEquals(x,y) : x와 y가 equals() 메서드 호출에 대해 결과가 true일 것
        //assertNotEquals(x, y) : assertEquals 의 반대
        //assertNull(x) : x가 null일 것
        //assertNotNull(x) : x가 null이 아닐 것
        //assertSame(x, y) : x != y 가 true dlf rjt
        //assertTure(x) : 논리형 x가 true 일 것
        //assertFalse(x) : 논리형 x가 false 일 것
        
        System.out.println("테스트 성공");
        // 실행버튼 누르면 모든 모델이 테스트됨
        //테스트성공이 뜨면 실제 디비에도 insert가 됐는지 확인하기
    }

}
