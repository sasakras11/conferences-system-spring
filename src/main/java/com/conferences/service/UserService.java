package com.conferences.service;

import com.conferences.entity.User;

public interface UserService {

    User register(String username, String password);

   User  login(String username, String password);

    public void save(User user);

    void deleteReservation(String userId,String speechId);

}
