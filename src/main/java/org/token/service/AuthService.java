package org.token.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.token.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

}
