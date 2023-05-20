package com.examples.lld.bms.services;

import com.examples.lld.bms.exceptions.EmailExistsException;
import com.examples.lld.bms.exceptions.InvalidEmailException;
import com.examples.lld.bms.exceptions.PasswordMismatchException;
import com.examples.lld.bms.models.User;
import com.examples.lld.bms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User signUp(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new EmailExistsException();
        }

        User user = new User();
        user.setEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public User login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new InvalidEmailException();
        }
        User user = userOptional.get();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new PasswordMismatchException();
        }
        return user;
    }

}
