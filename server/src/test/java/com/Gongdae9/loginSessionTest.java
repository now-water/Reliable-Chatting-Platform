package com.Gongdae9;

import com.Gongdae9.user.domain.User;
import com.Gongdae9.user.dto.LoginRequestDto;
import com.Gongdae9.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class loginSessionTest {

    @Autowired
    private UserService userService;

    @Test
    public void 회원가입_테스트() throws Exception {
        //given
        User user = User.builder()
            .name("테스트 이름4")
            .phoneNum("테스트 전화번호4")
            .nickName("테스트 닉네임4")
            .accountId("testid4")
            .password("testpw4")
            .build();

        //when
        long createdUserId = userService.signUp(user);

        //then
        Long needCheckId = userService.findById(createdUserId).getUserId();
        assertThat(createdUserId).isEqualTo(needCheckId);
    }

    @Test
    public void 로그인_테스트() throws Exception {
        //given
        LoginRequestDto dto = new LoginRequestDto("testid4", "testpw4");
        MockHttpSession session = new MockHttpSession();

        //when
        long id = userService.login(dto, session).getUserId();
        User user = userService.findByAccountId(dto.getAccountId()).get(0);

        //then
        assertThat(id).isEqualTo(user.getUserId());
    }
}
