import java.util.ArrayList;
import java.sql.*;

public class DBConnector implements IO{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/TournamentManager";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "darkdogx85";


    @Override
    public void saveTeams()
    {

    }

    @Override
    public void saveMatches() {

    }

    @Override
    public void saveResults() {

    }

    @Override
    public ArrayList<Team> loadTeams(String file)
    {
        ArrayList<Team> teamList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Teams";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int ID = rs.getInt("id");
                String name = rs.getString("name");
                boolean knockOut = rs.getBoolean("knockOut");

                //Display values
                System.out.print("ID: " + ID);
                System.out.print(" | TeamName: " + name);
                System.out.println(" | TeamknockOut: " + knockOut);
                Team team = new Team(ID,name,knockOut);
                teamList.add(team);

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return teamList;
    }

    @Override
    public ArrayList<Match> loadMatches(String file)
    {
        ArrayList<Match> matchList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Matches";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int tourId = rs.getInt("tourId");
                int id = rs.getInt("id");
                String matchType = rs.getString("matchType");
                int date = rs.getInt("date");
                int time = rs.getInt("time");
                boolean active = rs.getBoolean("active");

                //Display values
                System.out.print("Tournament-ID: " + tourId);
                System.out.print("Match-ID: " + id);
                System.out.print(" | matchType: " + matchType);
                System.out.print(" | date: " + date);
                System.out.print(" | matchTime: " + time);
                System.out.println(" | active: " + active);
                Match match = new Match(tourId,id,matchType,date,time,active);
                matchList.add(match);


            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return matchList;
    }

    @Override
    public void loadResults() {

    }



    public ArrayList<Tournament> loadTeamMatches()
    {
        ArrayList<Tournament> tournamentList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM TeamMatches";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int matchID = rs.getInt("matchID");
                int teamID = rs.getInt("teamID");
                int goals = rs.getInt("goals");
                int points = rs.getInt("points");

                //Display values
                System.out.print("MatchID: " + matchID);
                System.out.print(" | TeamID: " + teamID);
                System.out.print(" | Goals: " + goals);
                System.out.print(" | Points: " + points);
                Tournament tournament = new Tournament(matchID, teamID, goals);
                tournamentList.add(tournament);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return tournamentList;
    }


    public ArrayList<Tournament> loadTournaments()
    {
        ArrayList<Tournament> tournamentList = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Tournaments";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String tournametName = rs.getString("name");

                //Display values
                System.out.print("tournamentName: " + tournametName);
                Tournament tournament = new Tournament(tournametName);
                tournamentList.add(tournament);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return tournamentList;
    }

    public void savecurrentTeam(){

    }
}
