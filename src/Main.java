import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    public static IO io;
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
        Main.matches.get(0).getTeam1Goals();
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
        Scanner tourInput = new Scanner(System.in);
        System.out.println("\nSaved Tournaments: \n");
        int i = 1;
        for(Tournament t: tournaments)
        {
            System.out.println(" "+(i) + "." + t + "\n");
            i++;
        }
        System.out.println("Create new Tournament:\n");
        System.out.println(" "+(i+1) + ".Create new tournament\n");
        int input = tourInput.nextInt();
        io = getIo();

        //todo: double for loop der finder tournamentSchedule for det tournament ID der blev valgt (VIGTIGST)

        //todo: double for loop der finder teams for det tournament ID der blev valgt

        //todo: double for loop der finder matches for det tournament ID der blev valgt

        //add all teams that is not knockedOut of the tournament
        for(int p = 0; p < teams.size(); p++) {
            if (teams.get(p).isKnockedOut() == false)
            {
                Main.currentTeams.add(teams.get(p));
            }
        }

        //add all matches that is active in the tournament
        for(int j = 0; j < matches.size(); j++)
        {
            if (matches.get(j).isActive() == true)
            {
                Main.currentmatches.add(matches.get(j));
            }
        }

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






}
