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
    public static IO io;
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
        System.out.println("\nTOURNAMENTSCHEDULE");
        System.out.println(tournamentSchedule.get(1).getTeamID());

                //set Teams in the right Matches
                int x = 0;
                int g = 0;
                while(g < tournamentSchedule.size()/2)
                {
                    matches.get(g).setTeam1(teams.get(x));
                    matches.get(g).setTeam2(teams.get(x + 1));
                    x +=2;
                    g++;
                }

        for(int u = 0; u < tournamentSchedule.size(); u++)
        {
          int y = tournamentSchedule.get(u).getTeamID();

            if(teams.get(u).getid() == y)
            {
                System.out.println("HOLDNAVN");
                System.out.println(teams.get(u).getTeamName());
                //matches.get(0).setTeam1(teams.get(u));
            }
        }

        Scanner tourInput = new Scanner(System.in);
        System.out.println("\nSaved Tournaments: \n");
        int i = 1;
        for(Tournament t: tournaments)
        {
            System.out.println(" "+(i) + "." + t + "\n");
            i++;
        }
        System.out.println("Create new Tournament:\n");
        System.out.println(" "+(i) + ".Create new tournament\n");
        int input = tourInput.nextInt();
        io = getIo();

        if (input <= tournaments.size())
        {
            tourChoose = input - 1;
            for(int e = 0; e < tournamentSchedule.size(); e++)
            {
                //Removes schedules from arraylist that dosent have the id of the choosed tournament name
                if(tournamentSchedule.get(e).getTeamMatchesID() != tournaments.get(tourChoose).getId())
                {
                    tournamentSchedule.remove(e);
                    e--;
                }
            }


        //Removes teams from arraylist that dosent have the id of the choosed tournament name
        for(int n = 0; n < teams.size(); n++)
        {
            if(teams.get(n).getTournamentID() != tournaments.get(tourChoose).getId())
            {
                teams.remove(n);
                n--;
            }
        }

        //Removes matches from arraylist that dosent have the id of the choosed tournament name
        for(int h = 0; h < matches.size(); h++)
        {
            if (matches.get(h).getTournamentID() != tournaments.get(tourChoose).getId())
            {
                matches.remove(h);
                h--;
            }
        }
        }
        else if(input == i+1)
        {
            Controller data = new Controller();
            data.createTournament();
        }
        else
        {
            System.out.println("That is not a valid option");
        }

        //add all teams that is not knockedOut of the tournament
        for(int p = 0; p < teams.size(); p++) {
            if (teams.get(p).isKnockedOut() == false)
            {
                Main.currentTeams.add(teams.get(p));
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
