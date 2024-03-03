package net.ossant.security.provider;

import lombok.RequiredArgsConstructor;
import net.ossant.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import static net.ossant.utils.ApplicationUtil.badCredentialsErrorMessage;
import static net.ossant.utils.ApplicationUtil.userNotFoundErrorMessage;

@Component
@RequiredArgsConstructor
public class AppAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return userRepository.findByUsernameOrEmail(authentication.getName(), authentication.getName())
                .map(existingUser -> {
                    if (passwordEncoder.matches(authentication.getCredentials().toString(),
                            existingUser.getPassword())) {
                        Set<GrantedAuthority> authorities = existingUser.getRoles().stream()
                                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toSet());
                        return new UsernamePasswordAuthenticationToken(authentication.getName(),
                                authentication.getCredentials().toString(), authorities);
                    } else {
                        throw new BadCredentialsException(badCredentialsErrorMessage());
                    }
                }).orElseThrow(
                        () -> new UsernameNotFoundException(
                                userNotFoundErrorMessage(authentication.getCredentials().toString())
                        )
                );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
