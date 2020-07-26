package com.lysachenko.service;

public interface UserService {

    boolean create(String login, String password, String name, String surname, int age);

    boolean update(int id, String login, String password, String name, String surname, int age);

    void delete(int id);

    void showUsers();
}
