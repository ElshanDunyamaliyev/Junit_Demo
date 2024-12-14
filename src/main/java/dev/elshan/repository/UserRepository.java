package dev.elshan.repository;

import dev.elshan.model.User;

public interface UserRepository {

    boolean save(User user);
}
