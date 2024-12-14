package dev.elshan.repository;

import dev.elshan.model.User;

import java.util.HashMap;

public class UserRepositoryImpl implements UserRepository{

    private HashMap<String,User> map = new HashMap<>();
    @Override
    public boolean save(User user) {
        boolean result = false;
        if(!map.containsKey(user.getId())){
            map.put(user.getId(),user);
            result = true;
        }
        return result;
    }
}
