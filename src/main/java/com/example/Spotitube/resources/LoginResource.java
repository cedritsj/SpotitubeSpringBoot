package com.example.Spotitube.resources;

import com.example.Spotitube.resources.dto.UserDTO;
import com.example.Spotitube.resources.dto.request.LoginRequestDTO;
import com.example.Spotitube.resources.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginResource {

    private ILoginService loginService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(loginService.login(loginRequestDTO));
    }

    @Autowired
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }
}