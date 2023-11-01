package com.example.Spotitube.resources.interfaces;

import com.example.Spotitube.resources.dto.response.TrackResponseDTO;

public interface ITrackService {
    TrackResponseDTO getTracksNotInPlaylist(int id);
}
