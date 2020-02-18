package clovin;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;


public class PreAuthChecks implements UserDetailsChecker {


    public void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            throw new RuntimeException("User is locked");
        }
    }
}