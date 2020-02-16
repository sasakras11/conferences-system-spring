package com.conferences.service;

import com.conferences.entity.Speech;
import com.conferences.entity.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(String username, String password);

    Optional<User> login(String username, String password);

    public void save(User user);

    void deleteReservation(int userId,int speechId);

}
