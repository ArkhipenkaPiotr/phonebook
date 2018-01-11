package rnd.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rnd.auth.entity.User;
import rnd.auth.repository.UserRepository;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("lkjtknlrkjnhlkrjn");
        return userRepository.findUserByUsername(username);
    }

    public User loadUserByUsernameAndPassword(String username, String password){
        User user = userRepository.findUserByUsername(username);
        user.setPassword(password);
        return user;
    }
}