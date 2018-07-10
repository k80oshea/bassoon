package com.katieoshea.greatideas.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.katieoshea.greatideas.models.User;
import com.katieoshea.greatideas.repositories.UserRepo;

@Service
public class UserServ {
    private UserRepo uRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserServ(UserRepo uRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.uRepo = uRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        uRepo.save(user);
    }
    public User findByEmail(String email) {
        return uRepo.findByEmail(email);
    }
    public User findById(Long id) {
        return uRepo.findOne(id);
    }
    public List<User> allUsers() {
    	return (List<User>) uRepo.findAll();
    }
    public void update(User user) {
        uRepo.save(user);
    }

}