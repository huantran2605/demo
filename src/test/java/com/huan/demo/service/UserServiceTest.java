package com.huan.demo.service;

import com.huan.demo.entity.User;
import com.huan.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static final User user1 = User.builder().name("huan").email("huan@abc.com").build();
    private static final User user2 = User.builder().name("tran").email("tran@abc.com").build();

    @BeforeEach
    public void beforeEach() {
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @AfterEach
    public void afterEach() {
        userRepository.delete(user1);
        userRepository.delete(user2);
    }

    @Test
    void testGetUser() {
        List<User> users = userService.getAllUsers();
        assertThat(users).hasSize(2);
    }

    @Test
    void testFindByName() {
        User user = userService.findByName("huan");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("huan@abc.com");
    }

}
