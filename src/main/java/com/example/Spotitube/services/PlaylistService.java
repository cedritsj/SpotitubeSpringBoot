package com.example.Spotitube.services;

import com.example.Spotitube.datasource.dao.PlaylistDAO;
import com.example.Spotitube.datasource.dao.TrackDAO;
import com.example.Spotitube.resources.dto.PlaylistDTO;
import com.example.Spotitube.resources.dto.TrackDTO;
import com.example.Spotitube.resources.dto.response.PlaylistResponseDTO;
import com.example.Spotitube.resources.dto.response.TrackResponseDTO;
import com.example.Spotitube.resources.interfaces.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlaylistService implements IPlaylistService {

    private PlaylistDAO playlistDAO;
    private TrackDAO trackDAO;

    @Override
    public PlaylistResponseDTO getAllPlaylists(int userID) {
        ArrayList<PlaylistDTO> playlists = playlistDAO.getAll();
        for (PlaylistDTO playlist : playlists) {
            playlist.setOwner(userID == playlist.getOwnerID());
            playlist.setTracks(getTracksPerPlaylist(playlist.getId()).getTracks());
            for (TrackDTO track : playlist.getTracks()) {
                int length = playlist.getLength();
                playlist.setLength(length + track.getDuration());
            }
        }
        return new PlaylistResponseDTO(playlists, playlists.stream().mapToInt(PlaylistDTO::getLength).sum());
    }

    @Override
    public void addPlaylist(PlaylistDTO playlist, int userID) {
        playlist.setOwnerID(userID);
        playlistDAO.insert(playlist);
    }

    @Override
    public void editPlaylist(PlaylistDTO playlist, int id) {
        playlistDAO.update(playlist, id);
    }

    @Override
    public void deletePlaylist(int id) {
        playlistDAO.delete(id);
    }

    @Override
    public TrackResponseDTO getTracksPerPlaylist(int id) {
        return new TrackResponseDTO(trackDAO.getAllTracksInPlaylist(id));
    }

    @Override
    public void addTrackToPlaylist(TrackDTO trackDTO, int playlistId) {
        trackDAO.update(trackDTO, trackDTO.getId());
        trackDAO.insertTrackInPlaylist(trackDTO, playlistId);
    }

    @Override
    public void removeTrackFromPlaylist(int trackId, int playlistId) {
        trackDAO.deleteTrackFromPlaylist(trackId, playlistId);
    }

    @Autowired
    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Autowired
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
