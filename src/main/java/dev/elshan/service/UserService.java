package dev.elshan.service;

import dev.elshan.model.User;

public interface UserService {

    User createUser(String id,String firstName, String lastName, Integer age, String password, String repeatedPassword);
}
