public class Tournament {
    String tournamentName;
    Match match;
    Data data = new Data();
    int matchID;
    int teamID;
    int date;
    int time;
    int goal;
    UI ui;


    public Tournament(String tournamentName){
        this.tournamentName = tournamentName;
    }

    public Tournament(int matchID, int teamID, int goal){

    }


    public void tournamentSchedule()
    {
        for(int i = 0; i < Main.matches.size(); i++)
        {
            System.out.println("\nMatch " + (i+1)+ " " +Main.matches.get(i).getTeam1() + " vs " +
                    Main.matches.get(i).getTeam2() + " " + Main.matches.get(i).getTeam1Goals() + "-" +
                    Main.matches.get(i).getTeam2Goals());
        }

    }

    public void tournamentSim() {

        data.message();

        if(Main.currentTeams.size() == 0) {
            Main.currentTeams = Main.teams;
            data.teamExecute(Main.matches);
        }
        else if(Main.currentTeams.size() == 8) {
            data.teamExecute(Main.quarterFinals);
        }
        else if(Main.currentTeams.size() == 4) {
            data.teamExecute(Main.semifinals);
        }
        else if(Main.currentTeams.size() == 2) {
            data.teamExecute(Main.Finals);
        }

    }

    @Override
    public String toString() {
        return tournamentName;
    }

}
