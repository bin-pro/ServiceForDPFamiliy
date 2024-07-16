package org.example.sesacbibimbap.servicefordpfamily.security;

import lombok.extern.slf4j.Slf4j;
import org.example.sesacbibimbap.servicefordpfamily.entity.User;
import org.example.sesacbibimbap.servicefordpfamily.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email : " + userEmail)
        );

        return UserPrincipal.create(user);
    }
}
