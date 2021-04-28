public class Tournament {
    Controller data = new Controller();
    private int id;
    private int matchID;
    private int teamID;
    private int teamMatchesID;
    private int date;
    private int time;
    private int goal;
    private String tournamentName;
    private Match match;
    UI ui;

    public Tournament(String tournamentName)
    {
        this.tournamentName = tournamentName;
    }

    public Tournament(int id ,String tournamentName){
        this.id = id;
        this.tournamentName = tournamentName;
    }

    public Tournament(int teamMatchesID,int matchID, int teamID, int goal)
    {
        this.teamMatchesID = teamMatchesID;
        this.matchID = matchID;
        this.teamID = teamID;
        this.goal = goal;
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

        if(Main.currentTeams.size() == 16) {
            System.out.println("current teams 16");
            data.teamExecute(Main.currentmatches);
        }
        else if(Main.currentTeams.size() == 8) {
            System.out.println("current teams 8");
            data.teamExecute(Main.currentmatches);
        }
        else if(Main.currentTeams.size() == 4) {
            data.teamExecute(Main.currentmatches);
        }
        else if(Main.currentTeams.size() == 2) {
            data.teamExecute(Main.currentmatches);
        }

    }

    //GETTERS AND SETTERS
    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Controller getData() {
        return data;
    }

    public void setData(Controller data) {
        this.data = data;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public int getTeamMatchesID() {
        return teamMatchesID;
    }

    public void setTeamMatchesID(int teamMatchesID) {
        this.teamMatchesID = teamMatchesID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //TO STRING
    @Override
    public String toString() {
        return tournamentName ;
    }

}
