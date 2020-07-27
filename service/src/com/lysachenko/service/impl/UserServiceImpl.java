package com.lysachenko.service.impl;

import com.lysachenko.model.User;
import com.lysachenko.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private Map<Integer, User> users = new HashMap<>();
    private Integer id = 1;

    @Override
    public boolean create(String login, String password, String name, String surname, int age) {
        if (age <= 0 || age > 120) {
            return false;
        } else if (isUserLoginPresent(login)) {
            return false;
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setAge(age);
            users.put(id++, user);
            return true;
        }
    }

    private boolean isUserLoginPresent(String login) {
        return users.values()
                .stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }

    @Override
    public boolean update(int id, String login, String password, String name, String surname, int age) {
        if (users.containsKey(id) || age > 0 && age < 120) {
            users = users.entrySet().stream()
                    .peek(integerUserEntry -> {
                        if (integerUserEntry.getKey() == id) {
                            integerUserEntry.getValue().setLogin(login);
                            integerUserEntry.getValue().setPassword(password);
                            integerUserEntry.getValue().setName(name);
                            integerUserEntry.getValue().setSurname(surname);
                            integerUserEntry.getValue().setAge(age);
                        }
                    }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void delete(int id) {
        users.remove(id);
    }

    @Override
    public void showUsers() {
        users.forEach((key, value) -> System.out.println("ID: " + key.toString() + ", " + value));
    }
}