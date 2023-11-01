package com.example.Spotitube.resources.interfaces;

import com.example.Spotitube.resources.dto.PlaylistDTO;
import com.example.Spotitube.resources.dto.TrackDTO;
import com.example.Spotitube.resources.dto.response.PlaylistResponseDTO;
import com.example.Spotitube.resources.dto.response.TrackResponseDTO;

public interface IPlaylistService {
    PlaylistResponseDTO getAllPlaylists(int userID);

    void addPlaylist(PlaylistDTO playlist, int userID);

    void editPlaylist(PlaylistDTO playlist, int id);

    void deletePlaylist(int id);

    TrackResponseDTO getTracksPerPlaylist(int id);

    void addTrackToPlaylist(TrackDTO trackDTO, int playlistId);

    void removeTrackFromPlaylist(int trackId, int playlistId);
}
