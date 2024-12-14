package dev.elshan.service;

import dev.elshan.model.User;

public class EmailSendingServiceImpl {

    public void sendEmail(User user){
        System.out.println("sending email to " + user.getFirstName());
    }
}
