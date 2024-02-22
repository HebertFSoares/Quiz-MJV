package com.quiz.mjv.dto;

import java.util.List;

public class GameRoomDTO {
    private String id;
    private int maxPlayers;
    private List<String> players;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public GameRoomDTO() {
    }

    public GameRoomDTO(String id, int maxPlayers, List<String> players) {
        this.id = id;
        this.maxPlayers = maxPlayers;
        this.players = players;
    }
}
