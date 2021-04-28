import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Team {
    String teamName;
    int points;
    int id;
    boolean knockedOut = false;
    int tournamentID;
    ArrayList<Player> players = new ArrayList<>();
    UI ui;

    public Team(int tournamentID,int id,String teamName,boolean knockedOut){
        this.tournamentID = tournamentID;
        this.id = id;
        this.teamName = teamName;
        this.knockedOut = knockedOut;

    }

    public Team(String name){
        this.teamName = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPoints(){
        return points;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void setKnockedOut(boolean knockedOut) {
        this.knockedOut = knockedOut;
    }

    public void getPlacement(){ }

    public void setPlacement(){ }

    public void setid(int id) { this.id = id; }

    public int getid() { return id; }

    public boolean isKnockedOut() {
        return knockedOut;
    }

    public int getTournamentID() { return tournamentID; }

    public void setTournamentID(int tournamentID) { this.tournamentID = tournamentID; }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String printPlayers() {
        StringBuilder playerNames = new StringBuilder(players.get(0).getName());
        for (int i = 1; i < players.size(); i++) {
            playerNames.append(", ").append(players.get(i).getName());
        }
        return playerNames.toString();
    }

    @Override
    public String toString() {
        return  "Team: " + teamName + "\n"
                + "Players: " + printPlayers()+ "\n";
    }

}
