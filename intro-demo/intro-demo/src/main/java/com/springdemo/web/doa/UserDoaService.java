package com.springdemo.web.doa;

import com.springdemo.web.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class UserDoaService {

    private static List<User> users = new ArrayList<>();
    private static int countService = 0;
    static {
        users.add(new User(++countService,"Vijay", LocalDate.now().minusYears(31)));
        users.add(new User(++countService,"Amar", LocalDate.now().minusYears(28)));
        users.add(new User(++countService,"Ajay", LocalDate.now().minusYears(25)));

    }

    public List<User> findAll()
    {
        return users;
    }
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> Objects.equals(user.getId(), id);
        users.removeIf(predicate);
    }
    public User saveUser(User user)
    {
        user.setId(++countService);
        users.add(user);
        return user;
    }
}
