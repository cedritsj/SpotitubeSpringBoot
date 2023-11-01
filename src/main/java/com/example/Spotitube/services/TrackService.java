package com.example.Spotitube.services;

import com.example.Spotitube.datasource.dao.TrackDAO;
import com.example.Spotitube.resources.dto.response.TrackResponseDTO;
import com.example.Spotitube.resources.interfaces.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService implements ITrackService {

    private TrackDAO trackDAO;

    @Override
    public TrackResponseDTO getTracksNotInPlaylist(int id) {
        TrackResponseDTO trackResponseDTO = new TrackResponseDTO();
        trackResponseDTO.setTracks(trackDAO.getAllTracksNotInPlaylist(id));
        return trackResponseDTO;
    }

    @Autowired
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
