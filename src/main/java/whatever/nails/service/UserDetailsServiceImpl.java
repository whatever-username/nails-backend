package whatever.nails.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whatever.nails.entity.auth.Role;
import whatever.nails.entity.auth.User;
import whatever.nails.repository.UserJpaRepository;

import java.util.Arrays;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserJpaRepository userRep;

    public UserDetailsServiceImpl(UserJpaRepository userRep) {
        this.userRep = userRep;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRep.findByEmail(s);
        return org.springframework.security.core.userdetails.User
                .builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .authorities(getAuthorities(user.getRole()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        /*List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .forEach(authorities::add);
        }*/
        return Arrays.asList(new SimpleGrantedAuthority(role.getRoleName()));
    }

}
