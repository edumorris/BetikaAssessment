package com.betika.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class MatchesSelected {
    private int betPlaced;
    private Map<String, String> teamsPlaying;

    public MatchesSelected(int betPlaced, Map<String, String> teamsPlaying) {
        this.betPlaced = betPlaced;
        this.teamsPlaying = teamsPlaying;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "betPlaced=" + betPlaced +
                ", teamsPlaying=" + teamsPlaying +
                '}';
    }
}
