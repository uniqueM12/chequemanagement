/**
 *
 */
package com.wizer.test.config.security;

import com.wizer.test.config.security.util.Authority;
import com.wizer.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author uniqueM
 *         <p>
 *         This source file was written by Adindu Stevens as uniqueM.
 *         On 2019-02-09 20:23:41
 *         It may be altered or replicated by anyone capable,
 *         but the risks that might be accrued by such actions shall be borne
 *         by the same.
 *         uniqueM may however, be contacted through chukwudereadindu@gmail.com if
 *         there arises a need for a additional functionaly that may not have been
 *         included in the original project.
 *         If the module package does not include a user manual,
 *         uniqueM may also be contacted to provide one or other technical clarification.
 */
public class JdbcUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String principal) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        com.wizer.test.model.User myUser = null;

        myUser = userRepository.findOneByUsername(principal);


        if (myUser == null) {
            throw new UsernameNotFoundException("User " + principal + " cannot be found");
        }

        Authority authority = new Authority();
        authority.setId(1L);
        authority.setAuthority(myUser.getRole());

        List<Authority> collection = new ArrayList<Authority>(Arrays.asList(authority));
        User user = new User(myUser.getUsername(), myUser.bringPassword(), true, true, true, true, getAuthorities(collection));

        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Authority> authorities) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return grantedAuthorities;
    }

    public boolean isPhone(String principal) {
        if (principal == null) {
            return false;
        }
        if (principal.isEmpty()) {
            return false;
        }
        int i = 0;
        if (principal.charAt(0) == '+') {
            if (principal.length() == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < principal.length(); i++) {
            char c = principal.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
