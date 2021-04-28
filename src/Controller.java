import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Controller
{
IO io;

    public void registerTeam()
    {
        registerTeamAndPlayers(Main.players);

        try
        {
            Writer output;
            output = new BufferedWriter(new FileWriter("src/Teams.txt",true));
            output.append("\n");

            output.close();
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");
        }
    }

    public void registerTeamAndPlayers (ArrayList<Player> registerPlayers){
        //Add team
        System.out.println("\n" +
                "_ _ _ ____ _    ____ ____ _  _ ____    ___ ____    ___ ____ ____ _  _    ____ ____ ____ _ ____ ___ ____ ____ ___ _ ____ _  _ \n" +
                "| | | |___ |    |    |  | |\\/| |___     |  |  |     |  |___ |__| |\\/|    |__/ |___ | __ | [__   |  |__/ |__|  |  | |  | |\\ | \n" +
                "|_|_| |___ |___ |___ |__| |  | |___     |  |__|     |  |___ |  | |  |    |  \\ |___ |__] | ___]  |  |  \\ |  |  |  | |__| | \\| \n");
        System.out.println("Please write your teams name");
        String teamName = Main.getUserInput("Userinput: ");

        Team team = new Team(Main.tourChoose+1,teamName,false);
        Main.players.get(2).setTeamID(team.getid());
        Main.teams.add(team);

        //Add players
        System.out.println(
                "\n" +
                        "___  _    ____ _   _ ____ ____    ____ ____ ____ _ ____ ___ ____ ____ ___ _ ____ _  _ \n" +
                        "|__] |    |__|  \\_/  |___ |__/    |__/ |___ | __ | [__   |  |__/ |__|  |  | |  | |\\ | \n" +
                        "|    |___ |  |   |   |___ |  \\    |  \\ |___ |__] | ___]  |  |  \\ |  |  |  | |__| | \\| \n" );
        System.out.println("Register a max of 6 players per team (press q to quit)");
        final int maxPlayers = 6;
        ArrayList<Player> players = new ArrayList<>();
        int count = 0;
        int pcount = 1;
        while(players.size() < maxPlayers){
            System.out.println("Write name of player " + pcount+ ": ");
            String playerName = Main.getUserInput("Userinput: ");

            if(playerName.toLowerCase().equals("q")){
                break;
            }

            Player player = new Player(playerName);
            player.setTeamID(team.getid());
            players.add(player);
            count++;
            pcount++;
        }
        System.out.println("Welcome to the tournament " + teamName + " Your team has now been registered");
    }

    public void deleteTeam()
    {
        //Removes team from arraylist
        //todo make it remove from txt file too
        Scanner sc = new Scanner(System.in);
        System.out.println(Main.teams);
        System.out.println("Type name of team to remove: ");
        String teamName = sc.nextLine();

        Iterator i = Main.teams.iterator();
        Team tm;
        while(i.hasNext())
        {
            tm = (Team) i.next();
            if (tm.toString().equals(teamName))
            {
                i.remove();
                System.out.println("\nThe team " +teamName+ " was removed");
                break;
            }
        }
    }


    public void randomMatchUps(ArrayList<Team> randomTeams, ArrayList<Match> randomMatch) // creates random match-ups of the teams
    {
        ArrayList<Team> teamsList = new ArrayList<>();
        teamsList = randomTeams;
        Collections.shuffle(randomTeams);
        for (int i = 0; i < teamsList.size()-1; i+=2)
        {
            Team team1;
            Team team2;
            team1 = teamsList.get(i);
            team2 = teamsList.get(i+1);
            Match match = new Match(team1,team2, 0, 0,true);
            randomMatch.add(match);
//            io.saveMatches();
            System.out.println("A match between " + team1 + " and " + team2 + " has now been created");
        }

    }

    public void allTeams()
    {
        System.out.println("ENROLLED TEAMS: \n");
        for (Team c : Main.teams)
        {
            System.out.println(c);
        }

        return;
    }

    public void teamExecute(ArrayList<Match> matches) //MANGLER
    {
        System.out.println(Main.currentTeams.size());
        int i = 0;
        while(matches.size() > i)
        {
            Match match = matches.get(i);
                System.out.println("match is active");
                //set teams that lose to have boolean true knockOut
                if (match.getTeam1Goals() > match.getTeam2Goals()) {
                    match.getTeam2().setKnockedOut(true);
                } else if (match.getTeam1Goals() < match.getTeam2Goals()) {
                    match.getTeam1().setKnockedOut(true);

                }

                //Removes teams that lose a knockOut game
                if (match.getTeam1().isKnockedOut() == true) {
                    Main.currentTeams.remove(match.getTeam1());
                } else if (match.getTeam2().isKnockedOut() == true) {
                    Main.currentTeams.remove(match.getTeam2());
                }
                //set the matches that just got simulasted to false in active
                match.setActive(false);
                i++;
        }

        if (Main.currentTeams.size() > 1) {
            System.out.println("Qualified teams: " + Main.currentTeams + "\n");


        } else if (Main.currentTeams.size() <= 1) {
            System.out.println("\n" + "The winner of the tournament is " + Main.currentTeams + "\n");
            Main.currentTeams.get(0).setWinner(true);
        }

        Main.currentmatches.removeAll(Main.currentmatches);

    }
    public void message(){
        if (Main.currentTeams.size() >= 16){
            System.out.println("GruppeSpil");
        }else if(Main.currentTeams.size() == 8){
            System.out.println("Quarterfinals");
        }else if(Main.currentTeams.size() == 4){
            System.out.println("Semifinals");
        }else if(Main.currentTeams.size() == 2){
            System.out.println("Finals");
        }
    }



    public void registerMatches(ArrayList<Match> matchList)
    {
        Main.addToCurrentTeams();

        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Match goal register");
            for (int i = 0; i < matchList.size(); i++) {
                System.out.println("Match "+(i + 1)  + "" + matchList.get(i));

            }

            System.out.println("\nChoose a Match to register goals");
            int input = scan.nextInt();
            Match match = matchList.get(input - 1);

            System.out.println(" Match " + input);

            System.out.println("Set goals for team 1 " + match.getTeam1());
            int input2 = scan.nextInt();
            match.setTeam1Goals(input2);

            System.out.println("Set goals for team 2 " + match.getTeam2());
            int input3 = scan.nextInt();
            match.setTeam2Goals(input3);

            if(input3 == input2)
            {
                System.out.println("A match can not be tie!");
                match.setTeam1Goals(0);
                match.setTeam2Goals(0);
                System.out.println("Match have been reset to 0-0 score");
            }

            System.out.println("\ndo you want to register again y/n");
            char input4 = scan.next().charAt(0);
            if(input4 == 'y')
            {
                System.out.println("Register a match again\n");
            }
            else if (input4 == 'n')
            {
                return;
            }
            else
            {
                System.out.println("That is not a valid option");
                break;
            }
        }
    }


    public void createTournament()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type tournament name");
        System.out.println("\nUserInput: ");
        String tournamentName = scan.nextLine();
        Tournament tournament = new Tournament(tournamentName);
        Main.tournaments.add(tournament);
        System.out.println("Tournament " + tournamentName + " has now been created");
    }

    public void placePlayersInTeams()
    {
        //double for loop to place Players in the right teams
        for(int t = 0; t < Main.teams.size(); t++)
        {
            for(int y = 0; y < Main.players.size(); y++)
            {
                if(Main.players.get(y).getTeamID() == Main.teams.get(t).getid())
                {
                    Main.teams.get(t).getPlayers().add(Main.players.get(y));
                }
            }
        }
    }

    public void setTeamsInMatches()
    {
        int x = 0;
        int g = 0;
        while(g < Main.tournamentSchedule.size()/2)
        {
            Main.matches.get(g).setTeam1(Main.teams.get(x));
            Main.matches.get(g).setTeam2(Main.teams.get(x + 1));
            x +=2;
            g++;
        }
    }


    public void promptTournaments() {
        Scanner tourInput = new Scanner(System.in);
        System.out.println("\nSaved Tournaments: \n");
        int i = 1;
        for (Tournament t : Main.tournaments) {
            System.out.println(" " + (i) + "." + t + "\n");
            i++;
        }
        System.out.println("Create new Tournament:\n");
        System.out.println(" " + (i) + ".Create new tournament\n");
        int input = tourInput.nextInt();

        if (input <= Main.tournaments.size()) {
            Main.tourChoose = input - 1;
            for (int e = 0; e < Main.tournamentSchedule.size(); e++) {
                //Removes schedules from arraylist that dosent have the id of the choosed tournament name
                if (Main.tournamentSchedule.get(e).getTeamMatchesID() != Main.tournaments.get(Main.tourChoose).getId()) {
                    Main.tournamentSchedule.remove(e);
                    e--;
                }
            }
        }

        else if(input == i)
        {
            Main.tourChoose = input -1;
            Controller data = new Controller();
            data.createTournament();
        }
        else
        {
            System.out.println("That is not a valid option");
        }
    }

    public void removeTeamsWithFalseID()
    {
        //Removes teams from arraylist that dosent have the id of the choosed tournament name
        for(int n = 0; n < Main.teams.size(); n++)
        {
            if(Main.teams.get(n).getTournamentID() != Main.tournaments.get(Main.tourChoose).getId())
            {
                Main.teams.remove(n);
                n--;
            }
        }
    }

    public void removeMatchsWithFalseID() {
        //Removes matches from arraylist that dosent have the id of the choosed tournament name
        for(int h = 0; h < Main.matches.size(); h++)
        {
            if (Main.matches.get(h).getTournamentID() != Main.tournaments.get(Main.tourChoose).getId())
            {
                Main.matches.remove(h);
                h--;
            }
        }
    }

    public void addCurrentTeams() {
        //add all teams that is not knockedOut of the tournament
        for(int p = 0; p < Main.teams.size(); p++) {
            if (Main.teams.get(p).isKnockedOut() == false)
            {
                Main.currentTeams.add(Main.teams.get(p));
            }
        }
    }

    public void showWinnerOfMatch()
    {
        int count = 0;
        for(Match m : Main.matches)
        {
            System.out.println("Match."+(count+1)+ " - " + Main.matches.get(count).getMatchType());
            System.out.println(" "+m);
            System.out.println();
            count++;
        }

        System.out.println("Type match number to view players that won the match");
        Scanner scan = new Scanner(System.in);
        int matchNumb = scan.nextInt();

        matchNumb--;
        if(Main.matches.get(matchNumb).getTeam1Goals() > Main.matches.get(matchNumb).getTeam2Goals())
        {
            Main.matches.get(matchNumb).getTeam1().getPlayers();
        }
        else if(Main.matches.get(matchNumb).getTeam2Goals() > Main.matches.get(matchNumb).getTeam1Goals())
        {
            Main.matches.get(matchNumb).getTeam2().getPlayers();
        }
        else
        {
            System.out.println("This match has no winner yet");
        }
        return;
    }

    public void Winners()
    {
        for(int i = 0; i < Main.teams.size(); i++)
        {
            if (Main.teams.get(i).isWinner() == true)
            {
                System.out.println("\nWinners:");
                System.out.println("Team: " + Main.teams.get(i));

            }
            else
            {
                System.out.println("\nWinners");
                System.out.println("Tournament is not over yet, no winner is found!");
                break;
            }
        }
    }
}
