import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static  UI ui;
    public static ArrayList<Team> teams = new ArrayList<>();
    public static ArrayList<Match> matches = new ArrayList<>();
    public static ArrayList<Team> currentTeams = new ArrayList<>();
    public static ArrayList<Match> currentmatches = new ArrayList<>();
    public static ArrayList<Match> quarterFinals = new ArrayList<>();
    public static ArrayList<Match> Finals = new ArrayList<>();
    public static ArrayList<Match> semifinals = new ArrayList<>();
    public static ArrayList<Tournament> tournaments = new ArrayList<>();
    public static ArrayList<Tournament> tournamentSchedule = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();
    public static IO io;
    public static Controller con = new Controller();
    public static int tourChoose;
    //ENUM
    enum Datasource
    {
        DATABASE,
        CSVFILE
    }

    private static Datasource src = Datasource.DATABASE;
    private static String teamPath;
    private static String matchPath;
    private static String currentTeamPath;

    public static void main(String[] args){

        continueTournament();
        ui = new UI();
        ui.mainInterface();
//        saveMatchData();

    }

    public static void promptNewTour() {
        io = getIo();

        String input = getUserInput("Would you like to continue from last session Y/n: " + "\n").trim();
        if (input.equalsIgnoreCase("y") || input.equals("")) {
            teams = io.loadTeams(currentTeamPath);
            if(currentTeams.size() == 0) {
                matches = io.loadMatches(matchPath);
            }
            else if(currentTeams.size() == 8) {
                quarterFinals = io.loadMatches(matchPath);
            }
            else if(currentTeams.size() == 4) {
            semifinals = io.loadMatches(matchPath);
            }
            else if(currentTeams.size() == 2)
            {
                Finals = io.loadMatches(matchPath);
            }

        }else{
            teams = io.loadTeams(teamPath);
        }
    }

    public static void promptNewDBTour()
    {
        io = getIo();
        matches = io.loadMatches(matchPath);
        teams = io.loadTeams(teamPath);
        tournaments = io.loadTournaments();
        tournamentSchedule = io.loadTeamMatches();
        players = io.loadPlayers();
        con.placePlayersInTeams();
        con.setTeamsInMatches();
        con.promptTournaments();
        con.removeTeamsWithFalseID();
        con.removeMatchsWithFalseID();
        con.addCurrentTeams();
    }


    public static String getUserInput(String msg){
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }


    private static IO getIo()
    {
        if(src == Datasource.DATABASE)
        {
            teamPath = null;
            matchPath = null;
            currentTeamPath = null;
            return new DBConnector();
        }

        else if(src == Datasource.CSVFILE)
        {
            teamPath = "src/Teams.txt";
            matchPath = "src/match.txt";
            currentTeamPath = "src/CurrentTeams.txt";
            return new FileReader();
        }
        return null;
    }
    public static void continueTournament() {

            if(src == Datasource.DATABASE) {

                promptNewDBTour();
            }
            else if(src == Datasource.CSVFILE)
            {
                promptNewTour();
            }

    }

    public static String getGameDataFromSession()
    {
        StringBuilder gameData = new StringBuilder();
        for (Team t : Main.currentTeams)
        {
            String teamData = String.format(t.getTeamName()+"\n");
            gameData.append(teamData);
        }
        return gameData.toString();
    }

    public static String getMatchDataFromSession()
    {
        StringBuilder gameData = new StringBuilder();
        for (Match m : Main.matches)
        {
            String matchData = String.format(m.getTeam1() + ":" + m.getTeam2() +":"+m.getTeam1Goals()+":"+m.getTeam2Goals()+"\n");
            gameData.append(matchData);
        }
        return gameData.toString();
    }

    public static void addToCurrentTeams()
    {
        for(int j = 0; j < Main.matches.size(); j++)
        {
            if (Main.matches.get(j).isActive() == true)
            {
                Main.currentmatches.add(Main.matches.get(j));
            }
        }
    }

}
