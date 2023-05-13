package com.clinic.helpers;

import com.clinic.controller.UserController;
import com.clinic.dbTables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private UserController userController;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    User user = userController.getUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("No user found with username: " + username);
    }
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    String enPasswd = passwordEncoder.encode(user.getPassword());
    return new org.springframework.security.core.userdetails.User(
      user.getUsername(), enPasswd, enabled, accountNonExpired,
      credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
  }

  private static List<GrantedAuthority> getAuthorities (List<String> roles) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
    }
    return authorities;
  }
}
