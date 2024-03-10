package net.ossant.service.impl;

import lombok.RequiredArgsConstructor;
import net.ossant.dto.LoginDto;
import net.ossant.dto.RegisterDto;
import net.ossant.entity.User;
import net.ossant.exception.TodoAPIException;
import net.ossant.repository.RoleRepository;
import net.ossant.repository.UserRepository;
import net.ossant.security.authentication.AppAuthenticationProvider;
import net.ossant.service.AuthorizationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static net.ossant.utils.ApplicationUtil.todoApiEmailErrorMessage;
import static net.ossant.utils.ApplicationUtil.todoApiUsernameErrorMessage;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AppAuthenticationProvider appAuthenticationProvider;

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new TodoAPIException(todoApiUsernameErrorMessage(registerDto.getUsername()));
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new TodoAPIException(todoApiEmailErrorMessage(registerDto.getEmail()));
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));

        userRepository.save(user);

        return "User has been successfully registered";
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = appAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User successfully logged in";
    }

}
