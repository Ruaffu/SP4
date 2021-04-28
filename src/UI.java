import java.util.Scanner;

public class UI
{
    int userInput;
    Controller data = new Controller();
    Tournament tour = new Tournament(1,"pik");

    public void mainInterface()
    {
        System.out.println("\nMAIN INTERFACE - TOURNAMENT " + Main.tournaments.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println("1.Team menu");
        System.out.println("2.Match menu");
        System.out.println("3.Tournament menu");
        System.out.println("4.Exit");
        userInput = Main.getUserInput2("UserInput: ");

        switch (userInput)
        {
            case 1:
                teamMenu();
                break;
            case 2:
                matchMenu();
                break;
            case 3:
                tournamentMenu();
                break;

            case 4:
                Main.io.saveTournament();
                Main.io.saveTeams();
                Main.io.savePlayers();
                System.exit(0);
            default:
                System.out.println("not a valid option");
        }

    }

    public void teamMenu()
    {
        System.out.println("\nTEAM MENU - TOURNAMENT " + Main.tournaments.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println("1.Register team");
        System.out.println("2.Delete team");
        System.out.println("3.View all teams registered");
        System.out.println("4.Back to menu");
        userInput = Main.getUserInput2("UserInput: ");

        switch (userInput)
        {
            case 1:
                data.registerTeam();
                mainInterface();
                break;
            case 2:
                data.deleteTeam();
                mainInterface();
                break;
            case 3:
                data.allTeams();
                mainInterface();
                break;
            case 4:
                mainInterface();
                break;
            default:
                System.out.println("Not a valid option");

        }
    }

    public void matchMenu()
    {
        System.out.println("\nMATCH MENU - TOURNAMENT " + Main.tournaments.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println("1.Create Matches");
        System.out.println("2.Register match results");
        System.out.println("3.Show next match");
        System.out.println("4.Show winner of specific match");
        System.out.println("5.Back to menu");
        System.out.println("6.read test");
        userInput = Main.getUserInput2("UserInput: ");

        switch (userInput)
        {
            case 1:
                data.randomMatchUps(Main.currentTeams, Main.matches);
                mainInterface();
                break;
            case 2:
                data.registerMatches(Main.currentmatches);
                mainInterface();
                break;
            case 3:

            case 4:
                data.showWinnerOfMatch();
            case 5:
                mainInterface();
                break;
            case 6:
                Main.matches = Main.io.loadMatches("src/match.txt");
                System.out.println(Main.matches);
                break;
            default:
                System.out.println("Not a valid option");

        }

    }

    public void tournamentMenu()
    {
        System.out.println("\nTOURNAMENT MENU - TOURNAMENT " + Main.tournaments.get(Main.tourChoose).getTournamentName().toUpperCase());
        System.out.println("1.Tournament placements");
        System.out.println("2.Tournament match schedule");
        System.out.println("3.Tournament simulation");
        System.out.println("4.Tournament winners");
        System.out.println("5.Back to menu");
        userInput = Main.getUserInput2("UserInput: ");

        switch (userInput)
        {
            case 1:
                System.out.println("This feature is not functioning ATM.");
                mainInterface();
                break;
            case 2:
                tour.tournamentSchedule();
                mainInterface();
                break;
            case 3:
                tour.tournamentSim();
                mainInterface();
                break;
            case 4:
                data.Winners();
            case 5:
                mainInterface();
                break;
            default:
                System.out.println("Not a valid option");

        }

    }



    public void showNextMatch()
    {

    }




}

