package net.ossant.controller;

import lombok.RequiredArgsConstructor;
import net.ossant.dto.LoginDto;
import net.ossant.dto.RegisterDto;
import net.ossant.service.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static net.ossant.constants.ApplicationConstants.*;

@CrossOrigin(ALLOWED_CROSS_ORIGINS)
@RestController
@RequestMapping(AUTHORIZATION_BASE_URL)
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping(REGISTRATION_URL)
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        return new ResponseEntity<>(authorizationService.register(registerDto), HttpStatus.CREATED);
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authorizationService.login(loginDto), HttpStatus.OK);
    }



}
