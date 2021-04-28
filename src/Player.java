public class Player
{
    String name;
    int teamID;

    public Player(String name)
    {
        this.name = name;

    }


    //GETTERS AND SETTERS
    public void setId(int id) {
        this.teamID = id;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }


    //TO STRING
    @Override
    public String toString() {
        return name;
    }
}
