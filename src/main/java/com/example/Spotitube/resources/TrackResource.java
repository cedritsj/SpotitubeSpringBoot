package com.example.Spotitube.resources;

import com.example.Spotitube.resources.dto.response.TrackResponseDTO;
import com.example.Spotitube.resources.interfaces.ILoginService;
import com.example.Spotitube.resources.interfaces.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tracks")
@RestController
public class TrackResource {

    private ILoginService loginService;

    private ITrackService trackService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<TrackResponseDTO> getTracksNotInPlaylist(@RequestParam("token") String token, @RequestParam("forPlaylist") int id) {
        loginService.verifyToken(token);
        return ResponseEntity.ok(trackService.getTracksNotInPlaylist(id));
    }

    @Autowired
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

}
