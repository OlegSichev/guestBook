package oleg.sichev.guestbook.service;

import jakarta.transaction.Transactional;
import oleg.sichev.guestbook.DTO.UserDTO;
import oleg.sichev.guestbook.model.Role;
import oleg.sichev.guestbook.model.User;
import oleg.sichev.guestbook.repository.RoleRepository;
import oleg.sichev.guestbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User registerNewUserAccount(UserDTO userDto) throws Exception {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new Exception("There is an account with that email address: " + userDto.getUsername());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("ROLE_USER");
            return roleRepository.save(newRole);
        });

        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }
}
