import java.util.Scanner;

public class UI
{
    Scanner interfaceScan = new Scanner(System.in);
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
        userInput = interfaceScan.nextInt();

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
        System.out.println("3.View all teams registered"); //VIKER
        System.out.println("4.Back to menu");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                data.registerTeam();
                mainInterface();
                break;
            case 2:
                data.deleteTeam();
                System.out.println(Main.teams);
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
        System.out.println("2.Register match results"); //todo VIRKER IKKE
        System.out.println("3.Back to menu");
        System.out.println("4.read test");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                if(Main.currentTeams.size() == 16) {
                    data.randomMatchUps(Main.teams, Main.matches);
                }
                else if(Main.currentTeams.size() == 8) {
                    data.randomMatchUps(Main.currentTeams, Main.matches);
                }
                else if(Main.currentTeams.size() == 4) {
                    data.randomMatchUps(Main.currentTeams, Main.matches);
                }
                else if(Main.currentTeams.size() == 2) {
                    data.randomMatchUps(Main.currentTeams, Main.matches);
                }
                mainInterface();
                break;
            case 2:
                if(Main.currentTeams.size() == 16) {
                    System.out.println("Jeg har lavet hold i matches");
                    data.registerMatches(Main.currentmatches);
                }
                else if(Main.currentTeams.size() == 8) {
                    System.out.println("Jeg har lavet hold i quaterfinals");
                    data.registerMatches(Main.currentmatches);
                }
                else if(Main.currentTeams.size() == 4) {
                    data.registerMatches(Main.currentmatches);
                }
                else if(Main.currentTeams.size() == 2){
                    data.registerMatches(Main.currentmatches);
                }
                mainInterface();
                break;
            case 3:
                mainInterface();
                break;
            case 4:
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
        System.out.println("1.Tournament placements"); //todo lav imorgen
        System.out.println("2.Tournament match schedule"); //todo VIKER IKKE
        System.out.println("3.Tournament simulation"); //todo lav måske imorgen
        System.out.println("4.Back to menu");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                tournamentPlacement();
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
                mainInterface();
                break;
            default:
                System.out.println("Not a valid option");

        }

    }




    public void tournamentPlacement()
    {
        System.out.println("Stats for Matches" + "\n");

        System.out.println("Gruppespil");
        System.out.println(Main.matches + "\n");
        System.out.println("QuarterFinals");
        System.out.println(Main.quarterFinals + "\n");
        System.out.println("SemiFinals");
        System.out.println(Main.semifinals + "\n");
        System.out.println("Final");
        System.out.println(Main.Finals + "\n");

    }

}

