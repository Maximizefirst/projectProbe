package com.group7.project.service;

import com.group7.project.model.UsersModel;
import com.group7.project.repository.UsersRepository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository userRepository;

    public UserService(UsersRepository userRepository){
        this.userRepository = userRepository;
    }

    public void alreadyExists(String username, String password, String email){
        UsersModel model = new UsersModel();                          
        model.setUsername(username);   
        model.setPassword(password); 
        model.setEmail(email);                         

        ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnorePaths("id","password","email","role")
        .withMatcher("username", new GenericPropertyMatcher().exact());

        Example<UsersModel> example = Example.of(model, matcher);

        System.out.println(example);
    }

    public UsersModel findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UsersModel registerUser(String username, String password, String email){
        if(username == null || password == null){
            return null;
        }
        else {
            UsersModel usersModel = new UsersModel();
            usersModel.setUsername(username);
            usersModel.setEmail(email);
            usersModel.setPassword(password);
            usersModel.setRole("USER");
            return userRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public void allInfo(){
        System.out.println(userRepository.findAll(Sort.by(Sort.Direction.ASC, "username")));
    }
}
