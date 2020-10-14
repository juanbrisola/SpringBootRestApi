package com.juanbrisola.controller;

import com.juanbrisola.data.model.User;
import com.juanbrisola.repository.UserRepository;
import com.juanbrisola.security.AccountCredentialsVO;
import com.juanbrisola.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Api(value = "Endpoint de Autenticação", tags = "Autenticação")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserRepository repository;

    @ApiOperation(value = "Autentica um usuário e retorna um token")
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin", produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
        try {
            String username = data.getUsername();
            String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = repository.findByUsername(username);

            var token = "";

            if (user != null) {
                token = tokenProvider.createToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
    }
}
