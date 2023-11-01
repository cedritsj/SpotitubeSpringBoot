package com.example.Spotitube.resources.interfaces;

import com.example.Spotitube.resources.dto.UserDTO;
import com.example.Spotitube.resources.dto.request.LoginRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ILoginService {
    UserDTO login(LoginRequestDTO loginRequestDTO);

    UserDTO verifyToken(String token);

    int getUserID(String token);

    UserDTO getUserWithToken(String token);
}
