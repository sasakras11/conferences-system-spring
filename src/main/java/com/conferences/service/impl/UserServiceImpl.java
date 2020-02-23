package com.conferences.service.impl;


import com.conferences.entity.Role;
import com.conferences.entity.User;
import com.conferences.exception.LoginCredentialsException;
import com.conferences.exception.UserIsRegisteredException;
import com.conferences.repository.UserRepository;
import com.conferences.service.UserService;
import com.conferences.service.Validator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl extends AbstractService<User, UserRepository> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Validator validator;

    @Override
    public User register(String username, String password) {

        validator.validate(username, password);
        if (!userRepository.findUserByUsername(username).isPresent()) {
            User user = User.builder().username(username).password(bCryptPasswordEncoder.encode(password)).role(Role.VISITOR).build();
            userRepository.save(user);
            LOGGER.info(String.format("user with username [%s] and password [%s] registered successfully", username, password));

            return user;

        } else {
            throw new UserIsRegisteredException("registration");
        }
    }

    @Override
    public User login(String username, String password) {

        Optional<User> byUsername = userRepository.findUserByUsername(username);
        boolean loggedIn = false;
        if (byUsername.isPresent()) {
            loggedIn = bCryptPasswordEncoder.matches(password, byUsername.get().getPassword());
        }

        if (!loggedIn) {
            throw new LoginCredentialsException("start");
        }
        LOGGER.info(String.format("user with username [%s] and password [%s] logged in successfully", username, password));
        return byUsername.get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteReservation(String userId, String speechId) {
        int userID = getParsedOctalNumberOrRedirect(userId, "userSpeeches");
        int speechID = getParsedOctalNumberOrRedirect(speechId, "userSpeeches");
        userRepository.deleteReservation(userID, speechID);
        LOGGER.info(String.format("user with id [%s] deleted registration on speech with id [%s]", userID, speechID));

    }


}
