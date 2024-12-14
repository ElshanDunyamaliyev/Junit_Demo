package dev.elshan.service;

import dev.elshan.exception.UserNotCreatedException;
import dev.elshan.model.User;
import dev.elshan.repository.UserRepository;
import dev.elshan.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final EmailSendingServiceImpl emailSendingService;

    public UserServiceImpl(UserRepository userRepository, EmailSendingServiceImpl emailSendingService) {
        this.userRepository = userRepository;
        this.emailSendingService = emailSendingService;
    }

    @Override
    public User createUser(String id,String firstName, String lastName, Integer age, String password, String repeatedPassword) {
        User user = new User(id,firstName,lastName,age,password,repeatedPassword);
        if(firstName == null || firstName.trim().isEmpty()){
            throw new IllegalArgumentException("User Name cannot be null or empty");
        }
        boolean isUserCreated;
        try {
            isUserCreated = userRepository.save(user);
        }catch (RuntimeException e){
            throw new UserNotCreatedException(e.getMessage());
        }


        try{
            emailSendingService.sendEmail(user);
        }catch (RuntimeException e){
            throw new UserNotCreatedException(e.getMessage());
        }

//        userRepository.save(user); // Just test for mockito.verify that how many times should I can call a method
        if(!isUserCreated) throw new UserNotCreatedException("Something went wrong while creating user");
        return user;
    }
}
