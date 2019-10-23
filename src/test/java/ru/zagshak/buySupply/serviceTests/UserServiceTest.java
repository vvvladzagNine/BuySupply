package ru.zagshak.buySupply.serviceTests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zagshak.buySupply.domain.Role;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.service.UserService;
import ru.zagshak.buySupply.util.exception.NotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zagshak.buySupply.UserTestData.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void create() {
        User newUser = new User(null, "New", "New@mail.ru", "password","Sakhalin", Role.ROLE_USER);
        User created = userService.create(newUser);
        newUser.setId(created.getId());
        assertMatch(userService.getAll(), USER1, USER2, ADMIN, newUser);
    }

    @Test(expected = Exception.class)
    public void duplicateMailCreate() throws Exception {
        User wrongUser = new User(null, "New", "userS@yandex.ru", "password","Sakhalin", Role.ROLE_USER);
        userService.create(wrongUser);
    }

    @Test
    public void update() {
        User updated = new User(USER2);
        updated.setCity("New York");
        updated.setPassword("Normal Password");
        userService.update(updated);
        assertMatch(userService.get(USER2_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        userService.delete(USER1_ID);
        assertMatch(userService.getAll(), USER2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        userService.delete(1);
    }

    @Test
    public void get() throws Exception {
        User user = userService.get(USER1_ID);
        assertMatch(user, USER1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        User user = userService.get(1);
    }

    public void getAll() throws Exception {
        assertMatch(userService.getAll(), USER1, USER2, ADMIN);
    }

    @Test
    public void getByEmail() {
        assertMatch(userService.getByEmail("admin@gmail.com"), ADMIN);
    }

}
