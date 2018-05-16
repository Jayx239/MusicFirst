package application.service.impl;

import application.models.User;
import application.models.member.Member;
import application.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member user = memberRepository.findByUserName(s);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getMemberRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
        });

        user.getSecurityRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getCredential().getPasswordHash(), authorities);

        return userDetails;
    }
}
