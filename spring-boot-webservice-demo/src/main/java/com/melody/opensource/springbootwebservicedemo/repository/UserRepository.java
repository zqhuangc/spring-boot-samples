package com.melody.opensource.springbootwebservicedemo.repository;

import com.melody.opensource.springbootwebservicedemo.domain.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户的仓储
 *
 * @author zqhuangc
 */
@Repository
public class UserRepository {

    private Map<Long, User> cachedUsers = new HashMap<>();

    @PostConstruct
    public void init() {
        User user = createUser(1, "小明", 32);
        User user2 = createUser(2, "刘德华", null);
        User user3 = createUser(3, "黄小明", null);
        cachedUsers.put(1L, user);
        cachedUsers.put(2L, user2);
        cachedUsers.put(3L, user3);
    }


    public User findById(long id) {
        return cachedUsers.get(id);
    }


    private User createUser(long id, String name, Integer age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

}
