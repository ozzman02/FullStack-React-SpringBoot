package net.ossant.service;

import net.ossant.dto.LoginDto;
import net.ossant.dto.RegisterDto;

public interface AuthorizationService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

}
