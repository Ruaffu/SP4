import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader implements IO
{
    public ArrayList<Tournament> loadTournaments(){
        return null;
    }

    public ArrayList<Tournament> loadTeamMatches(){
      return null;
    }

    @Override
    public void loadResults() {

    }

    @Override
    public void saveTeams()
    {

    }
    @Override
    public void saveResults() {

    }

    @Override
    public  ArrayList<Match> loadMatches(String file) {
        ArrayList<Match> matchList = new ArrayList<>();
        Scanner scanner = null;
        File fr = new File(file);
        try {

            scanner = new Scanner(fr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                Team team1 = new Team("teamName");
                Team team2 = new Team("teamName");

                String[] colonSeperatedValues = scanner.nextLine().split(":");
                team1.setTeamName(colonSeperatedValues[0]);
                team2.setTeamName(colonSeperatedValues[1]);

                int i =0;
                while(i < Main.currentTeams.size()) {
                    if (Main.currentTeams.get(i).equals(team1.getTeamName()))
                    {
                        team1 = Main.currentTeams.get(i);
                    }
                    if (Main.currentTeams.get(i).equals(team2.getTeamName()))
                    {
                        team2 = Main.currentTeams.get(i);
                    }
                    i++;
                }

                int team1Goal = Integer.parseInt(colonSeperatedValues[2]);
                int team2Goal = Integer.parseInt(colonSeperatedValues[3]);
                matchList.add(new Match(team1,team2,team1Goal,team2Goal));
            }
        }

        return matchList;
    }

    public void saveMatches() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/match.txt");
            writer.write(Main.getMatchDataFromSession());
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveMatchData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveMatchData()");
                e.printStackTrace();
            }
        }
    }

    public void savecurrentTeam() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/CurrentTeams.txt");
            writer.write(Main.getGameDataFromSession());
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveCurrentTeamData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveCurrentTeamData()");
                e.printStackTrace();
            }
        }
    }

    public  ArrayList<Team> loadTeams(String file) {
        ArrayList<Team> teamList = new ArrayList<>();
        Scanner scanner = null;
        File fr = new File(file);
        try {

            scanner = new Scanner(fr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null)
        {
            while(scanner.hasNextLine())
            {
                String teamName = scanner.nextLine();
                teamList.add(new Team(teamName));
            }
        }

        return teamList;
    }
}
