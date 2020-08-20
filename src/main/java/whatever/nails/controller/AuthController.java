package whatever.nails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import whatever.nails.dto.RegistrationDto;
import whatever.nails.entity.auth.Role;
import whatever.nails.entity.auth.User;
import whatever.nails.mapper.RegistrationDtoToUserMapper;
import whatever.nails.repository.RoleJpaRepository;
import whatever.nails.repository.UserJpaRepository;

import javax.transaction.Transactional;

@RestController
public class AuthController {
    private UserJpaRepository userRep;
    private RoleJpaRepository roleRep;
    private PasswordEncoder encoder;
    private RegistrationDtoToUserMapper mapper;
    @Autowired
    public AuthController(UserJpaRepository userRep, RoleJpaRepository roleRep, PasswordEncoder encoder, RegistrationDtoToUserMapper mapper) {
        this.userRep = userRep;
        this.roleRep = roleRep;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    @Transactional
    public RegistrationDto register(RegistrationDto dto){
        User user = mapper.convert(dto, User.class);
        userRep.save(user);
        roleRep.save(new Role(1,"asda"));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(roleRep.findByRoleName("ROLE_USER"))   ;
        System.out.println("start123");
        User createdUser = userRep.save(user);
        System.out.println("finish123");
        createdUser.setPassword(null);
        return mapper.convert(createdUser,RegistrationDto.class);
    }

}
