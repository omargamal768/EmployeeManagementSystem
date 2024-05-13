package com.aliabou.secuirty.auth;

import com.aliabou.secuirty.config.JwtService;
import com.aliabou.secuirty.demo.Repository.UserRepository;
import com.aliabou.secuirty.entities.*;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest request) {

        var user= User.builder()
                .name(request.getName())
                .salary(request.getSalary())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                /*.departmentId(request.getDepartmentId())
                .projectId(request.getProjectId())*/
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken =  jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken =  jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
    public AuthenticationResponse authenticateAdmin(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var admin = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken =  jwtService.generateToken(admin);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public List<User> getEmployees() {
        return userRepository.findAll();
    }


}
