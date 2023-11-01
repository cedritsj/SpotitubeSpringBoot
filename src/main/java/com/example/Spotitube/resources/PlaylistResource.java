package com.example.Spotitube.resources;

import com.example.Spotitube.resources.dto.PlaylistDTO;
import com.example.Spotitube.resources.dto.TrackDTO;
import com.example.Spotitube.resources.dto.response.PlaylistResponseDTO;
import com.example.Spotitube.resources.dto.response.TrackResponseDTO;
import com.example.Spotitube.resources.interfaces.ILoginService;
import com.example.Spotitube.resources.interfaces.IPlaylistService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/playlists")
@RestController
public class PlaylistResource {

    private IPlaylistService playlistService;
    private ILoginService loginService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<PlaylistResponseDTO> getPlaylists(@RequestParam("token") String token) {
        loginService.verifyToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getAllPlaylists(loginService.getUserID(token)));
    }

    @PostMapping
    public ResponseEntity<PlaylistResponseDTO> addPlaylist(@RequestBody PlaylistDTO playlist, @RequestParam("token") String token) {
        loginService.verifyToken(token);
        int userID = loginService.getUserID(token);
        playlistService.addPlaylist(playlist, userID);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.getAllPlaylists(userID));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PlaylistResponseDTO> editPlaylist(@RequestBody PlaylistDTO playlist, @PathParam("id") int id, @RequestParam("token") String token) {
        loginService.verifyToken(token);
        playlistService.editPlaylist(playlist, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playlistService.getAllPlaylists(loginService.getUserID(token)));

    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PlaylistResponseDTO> deletePlaylist(@PathParam("id") int id, @RequestParam("token") String token) {
        loginService.verifyToken(token);
        playlistService.deletePlaylist(id);
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getAllPlaylists(loginService.getUserID(token)));
    }

    @GetMapping(value = "/{id}/tracks", produces = "application/json")
    public ResponseEntity<TrackResponseDTO> getTracksFromPlaylist(@PathParam("id") int id, @RequestParam("token") String token) {
        loginService.verifyToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getTracksPerPlaylist(id));
    }

    @PostMapping(value = "/{id}/tracks", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TrackResponseDTO> addTrackToPlaylist(@PathParam("id") int playlistId, TrackDTO trackDTO, @RequestParam("token") String token) {
        loginService.verifyToken(token);
        playlistService.addTrackToPlaylist(trackDTO, playlistId);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.getTracksPerPlaylist(playlistId));
    }

    @DeleteMapping(value = "/{id}/tracks/{trackId}", produces = "application/json")
    public ResponseEntity<TrackResponseDTO> removeTrackFromPlaylist(@PathParam("id") int playlistId, @PathParam("trackId") int trackId, @RequestParam("token") String token) {
        loginService.verifyToken(token);
        playlistService.removeTrackFromPlaylist(trackId, playlistId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(playlistService.getTracksPerPlaylist(playlistId));
    }

    @Autowired
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setPlaylistService(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }
}
