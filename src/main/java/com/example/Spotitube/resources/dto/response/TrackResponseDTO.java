package com.example.Spotitube.resources.dto.response;

import com.example.Spotitube.datasource.dao.TrackDAO;
import com.example.Spotitube.resources.dto.TrackDTO;

import java.util.ArrayList;

public class TrackResponseDTO {

    private ArrayList<TrackDTO> tracks = new ArrayList<>();
    private int length;

    public TrackResponseDTO() {
    }

    public TrackResponseDTO(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
