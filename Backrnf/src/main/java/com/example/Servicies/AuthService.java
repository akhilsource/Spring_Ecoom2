package com.example.Servicies;

import com.example.Entities.User;
import com.example.Entities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    //@Autowired
  //  BCryptPasswordEncoder encrypter;
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user==null) {
            System.out.println(username);
            return false;
        }
        if (user != null) {
            if(user.getPassword().equals(password))
                return true;
            else
                return false;
        }
       return false;
    }
    public boolean signup(String username,String password)
    {
        User user=userRepository.findByUsername(username);
        if(user!=null)
            return false;
        else
        {
            User u1=new User(username,password);
            userRepository.save(u1);
            return true;
        }
    }
}
