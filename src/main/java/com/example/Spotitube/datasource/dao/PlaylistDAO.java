package com.example.Spotitube.datasource.dao;

import com.example.Spotitube.resources.dto.PlaylistDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class PlaylistDAO extends BaseDAO<PlaylistDTO> {

    @Override
    public PreparedStatement statementBuilder(Connection connection, String action, Optional<PlaylistDTO> playlistDTO, Optional<Integer> id) throws SQLException {
        if(action.equals("SELECT") && id.isPresent()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlists WHERE id = ?;");
            statement.setInt(1, id.get());
            return statement;
        } else if(action.equals("SELECT") && !id.isPresent()) {
            PreparedStatement statement = connection.prepareStatement("SELECT p.*, u.token " +
                    "FROM playlists p" +
                    "         LEFT JOIN spotitube.tracks_in_playlist tip ON tip.playlist_id = p.id" +
                    "         LEFT JOIN spotitube.tracks t ON t.id = tip.track_id" +
                    "         JOIN users u ON p.owner = u.id" +
                    "         GROUP BY p.id;");
            return statement;
        } else if(action.equals("INSERT")) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO playlists (name, owner) VALUES (?, ?);");
            statement.setString(1, playlistDTO.get().getName());
            statement.setInt(2, playlistDTO.get().getOwnerID());
            return statement;
        } else if(action.equals("UPDATE")) {
            PreparedStatement statement = connection.prepareStatement("UPDATE playlists SET name = ? WHERE id = ?;");
            statement.setString(1, playlistDTO.get().getName());
            statement.setInt(2, id.get());
            return statement;
        } else if(action.equals("DELETE")) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM playlists WHERE id = ?;");
            statement.setInt(1, id.get());
            return statement;
        } return null;
    }

    @Override
    public ArrayList<PlaylistDTO> buildFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<PlaylistDTO> playlists = new ArrayList<>();
        while (rs.next()) {
            PlaylistDTO playlist = new PlaylistDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("owner"));
            playlists.add(playlist);
        }
        rs.close();
        return playlists;
    }
}
