import java.util.ArrayList;
import java.util.Collections;

public class Match {
    private int id;
    private int tournamentID;
    private int date;
    private int time;
    private int team1Goals;
    private int team2Goals;
    private boolean active;
    private String matchType;
    private String startTime = "12.00";
    private Team team1;
    private Team team2;
    private Team winner;
    UI ui;


    public Match(Team team1, Team team2, int team1Goals, int team2Goals, boolean active)
    {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Goals = team1Goals;
        this.team2Goals = team2Goals;
        this.active = active;
    }

    public Match(int tourId, int id, String matchType, int date, int time, boolean active)
    {
        this.tournamentID = tourId;
        this.id = id;
        this.active = active;
        this.matchType = matchType;
        this.date = date;
        this.time = time;
    }


    //GETTERS AND SETTERS
    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    public Team getTeam1()
    {
        return team1;
    }

    public Team getTeam2()
    {
        return team2;
    }

    public int getTeam1Goals()
    {
        return team1Goals;
    }

    public int getTeam2Goals()
    {
        return team2Goals;
    }

    public int getTournamentID() { return tournamentID; }

    public void setTournamentID(int tourId) { this.tournamentID = tourId; }

    public boolean isActive() { return active; }

    public void setActive(boolean active) { this.active = active; }

    public String getMatchType() { return matchType; }

    public void setMatchType(String matchType) { this.matchType = matchType; }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    //TO STRING
    @Override
    public String toString() {
        return team1.getTeamName() + " Goals: " + team1Goals + " vs " + team2.getTeamName() + " Goals: " + team2Goals;
    }

}
