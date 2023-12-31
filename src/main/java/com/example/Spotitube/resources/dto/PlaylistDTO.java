package com.example.Spotitube.resources.dto;

import java.util.ArrayList;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean owner = false;
    private int ownerID;
    private ArrayList<TrackDTO> tracks = new ArrayList<>();

    private int length;

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, int ownerID) {
        this.id = id;
        this.name = name;
        this.ownerID = ownerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
