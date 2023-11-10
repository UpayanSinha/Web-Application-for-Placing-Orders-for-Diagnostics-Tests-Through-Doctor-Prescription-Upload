package com.diag.config;

import com.diag.dao.UserRepo;
import com.diag.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //fetching user from database
        User user =userRepo.getUserByUserEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("could not found user!!");
        }
        CustomUserDetails customUserDetails;
        customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }
}
