import java.util.ArrayList;

public interface IO
{

    public void saveTeams();
    public void saveResults();
    public void saveMatches();
    public void savecurrentTeam();
    public void saveTournament();

    public ArrayList<Tournament> loadTournaments();
    public ArrayList<Tournament> loadTeamMatches();
    public ArrayList<Team> loadTeams(String file);
    public ArrayList<Match> loadMatches(String file);
    public ArrayList<Player> loadPlayers();
    public void loadResults();

}
