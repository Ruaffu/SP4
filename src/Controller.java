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
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your team name");
        String teamName = scan.nextLine();
        System.out.println("Write name of first player");
        String player1 = scan.nextLine();
        System.out.println("Write name of second player");
        String player2 = scan.nextLine();

        try
        {
            Team team = new Team(teamName);
            Main.teams.add(team);
            Writer output;
            output = new BufferedWriter(new FileWriter("src/Teams.txt",true));
            output.append("\n");
            output.append(teamName + ":player 1 - " + player1 +":player 2 - " + player2);
            output.close();
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");
        }
        System.out.println("Welcome to the tournament " + teamName + " Your team has now been registered");

    }

    public void deleteTeam()
    {
        //Removes team from arraylist
        //todo make it remove from txt file too
        Scanner sc = new Scanner(System.in);
        System.out.println(Main.teams);
        System.out.println("Type name of team to remove");
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
            Match match = new Match(team1,team2, 0, 0);
            randomMatch.add(match);
            io.saveMatches();
            System.out.println("A match between " + team1 + " and " + team2 + " has now been created");
        }

    }

    public void allTeams()
    {
        System.out.println("Enrolled teams ");
        for (Team c : Main.teams)
        {
            System.out.println(c);
        }

        return;
    }

    public void teamExecute(ArrayList<Match> matches) //MANGLER
    {
        int i = 0;
        while(matches.size() > i)
        {
            Match match = matches.get(i);

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
            i++;

        }
        if (Main.currentTeams.size() > 1) {
            System.out.println("Qualified teams: " + Main.currentTeams + "\n");
            io.savecurrentTeam();

        } else if (Main.currentTeams.size() <= 1) {
            System.out.println("\n" + "The winner of the tournament is " + Main.currentTeams + "\n");
        }

    }
    public void message(){
        if (Main.teams.size() >= 16){
            System.out.println("GruppeSpil");
        }else if(Main.teams.size() == 8){
            System.out.println("Quarterfinals");
        }else if(Main.teams.size() == 4){
            System.out.println("Semifinals");
        }else if(Main.teams.size() == 2){
            System.out.println("Finals");
        }
    }



    public void registerMatches(ArrayList<Match> matchList)
    {
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


}
