package com.conference.demo.service.impl;


import com.conference.demo.Exception.ValidationException;
import com.conference.demo.entity.Role;
import com.conference.demo.entity.User;
import com.conference.demo.repository.ConferenceRepository;
import com.conference.demo.repository.UserRepository;
import com.conference.demo.service.PasswordEncryptor;
import com.conference.demo.service.UserService;
import com.conference.demo.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ConferenceRepository conferenceRepository;
    private PasswordEncryptor encryptor = new PasswordEncryptor();
    private Validator validator = new Validator();



    @Autowired
    public UserServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }




    @Override
    public User register(String username, String password) {

        validator.validate(username, password);

        if (userRepository.findUserByUsername(username)==null) {
            User user = User.builder().username(username).password(encryptor.getHashedPassword(password)).role(Role.VISITOR).build();
            userRepository.save(user);
            return user;

        } else {
            throw new ValidationException("user is already registered");
        }
    }
}
