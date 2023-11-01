package com.example.Spotitube.services;

import com.example.Spotitube.datasource.dao.LoginDAO;
import com.example.Spotitube.exceptions.AuthenticationException;
import com.example.Spotitube.exceptions.InvalidCredentialsException;
import com.example.Spotitube.resources.dto.UserDTO;
import com.example.Spotitube.resources.dto.request.LoginRequestDTO;
import com.example.Spotitube.resources.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService implements ILoginService {
    private LoginDAO loginDAO;

    @Override
    public UserDTO login(LoginRequestDTO loginRequestDTO) {
        UserDTO userDTO = loginDAO.getUserWithLoginRequest(loginRequestDTO);
        if (userDTO == null) {
            throw new InvalidCredentialsException();
        }

        String token = UUID.randomUUID().toString();
        userDTO.setToken(token);
        loginDAO.update(userDTO, userDTO.getId());

        return userDTO;
    }

    @Override
    public UserDTO verifyToken(String token) {
        return getUserWithToken(token);
    }

    @Override
    public int getUserID(String token) {
        return getUserWithToken(token).getId();
    }

    @Override
    public UserDTO getUserWithToken(String token) {
        Optional<UserDTO> userDTO = loginDAO.getUserWithToken(token);

        if (userDTO.isEmpty()) {
            throw new AuthenticationException();
        }

        return userDTO.get();
    }

    @Autowired
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
}