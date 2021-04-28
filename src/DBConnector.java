import java.lang.management.PlatformLoggingMXBean;
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
                Connection conn = null;
        // Statement stmt = null;
        // for insert a new candidate
        ResultSet rs = null;

        //Insert/upsert
        String sql = "INSERT INTO Teams(id,tournamentID,name,knockOut) "
                + "VALUES(?,?,?,?)";

        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);


            //STEP 2: Execute a query
            System.out.println("Creating statement...");
            //stmt = conn.createStatement();

            for(int i = 0; i <  Main.teams.size();i++){

                pstmt.setInt(1, Main.teams.get(i).getid());
                pstmt.setInt(2, Main.teams.get(i).getTournamentID());
                pstmt.setString(3, Main.teams.get(i).getTeamName());
                pstmt.setBoolean(4, Main.teams.get(i).isKnockedOut());

                pstmt.addBatch();

            }
            pstmt.executeBatch();

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if(rs != null)  rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void saveMatches() {
//        Connection conn = null;
//        // Statement stmt = null;
//        // for insert a new candidate
//        ResultSet rs = null;
//
//        //Insert/upsert
//        String sql = "INSERT INTO Player( tournamentID, matchID, teamID, goals, points) "
//                + "VALUES(?,?,?,?,?,?)  ON DUPLICATE KEY UPDATE goals=?, points=?";
//
//        try{
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
//
//
//            //STEP 2: Execute a query
//            System.out.println("Creating statement...");
//            //stmt = conn.createStatement();
//
//            for(int i = 0; i <  Main.matches.size();i++){
//
//                pstmt.setInt(1,Main.tournaments.get(i).getId());
//                pstmt.setString(2,Main.getMatchByID(i).getMatch());
//                pstmt.setInt(3,Main.getTeamByID(i).getTeamName());
//                pstmt.setInt(4,Main.getGoals(i).getTeamGoals());
//                pstmt.setInt(5,Main.getPoints(i).getPoints());
//
//                // Disse paramtre bruges ved UPDATES
//                pstmt.setInt(6,Main.getGoals(i).getTeamGoals());
//                pstmt.setInt(7,Main.getPoints(i).getPoints());
//
//
//                pstmt.addBatch();
//
//            }
//            pstmt.executeBatch();
//
//        }catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            try {
//                if(rs != null)  rs.close();
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }

    }

    @Override
    public void saveResults()
    {

    }

    @Override
    public void savePlayers()
    {
        ResultSet rs = null;
        Connection conn = null;
        //Statement stmt = null;

        String sql = "INSERT INTO Players(teamID,name)"
                + "VALUES(?,?)";

        try
        {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            //STEP 2: Execute a query
            System.out.println("Creating statement...");
            //  stmt = conn.createStatement();

            for (int i = 0; i < Main.players.size(); i++)
            {
                pstmt.setInt(1, Main.players.get(i).getTeamID());

                pstmt.addBatch();
            }

            pstmt.executeBatch();

        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }finally {
            try{
                if(rs != null) rs.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Player statement Done");
    }

    @Override
    public void saveTournament()
    {
        ResultSet rs = null;
        Connection conn = null;
        Statement stmt = null;

        String sql = "INSERT INTO Tournaments(name) "
                + " VALUES(?)";

        String sql2 = "SELECT * FROM Teams";


        try
        {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);



            //STEP 2: Execute a query
            System.out.println("Creating statement...");
            //  stmt = conn.createStatement();

            for (int i = 0; i < Main.tournaments.size(); i++)
            {


                    pstmt.setString(1, Main.tournaments.get(i).getTournamentName());

                    pstmt.addBatch();


            }
            pstmt.executeBatch();

        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }finally {
            try{
                if(rs != null) rs.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Tournament statement Done");

    }
    public ArrayList<Player> loadPlayers()
    {
        ArrayList<Player> playerList = new ArrayList<>();

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

            String sql = "SELECT * FROM Players";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int teamID = rs.getInt("teamID");
                String name = rs.getString("name");
                Player player = new Player(name);
                player.setId(teamID);
                playerList.add(player);

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

        return playerList;
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
                int tournamentID = rs.getInt("tournamentID");
                String name = rs.getString("name");
                boolean knockOut = rs.getBoolean("knockOut");

                //Display values
                System.out.print("ID: " + ID);
                System.out.print(" | TeamName: " + name);
                System.out.println(" | TeamknockOut: " + knockOut);
                Team team = new Team(tournamentID,name,knockOut);
                team.setid(ID);
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

            String sql =" SELECT * FROM Matches";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int tournamentID = rs.getInt("tournamentID");
                int id = rs.getInt("id");
                String matchType = rs.getString("matchType");
                int date = rs.getInt("date");
                int time = rs.getInt("time");
                boolean active = rs.getBoolean("active");

                //Display values
                System.out.print("Tournament-ID: " + tournamentID);
                System.out.print("Match-ID: " + id);
                System.out.print(" | matchType: " + matchType);
                System.out.print(" | date: " + date);
                System.out.print(" | matchTime: " + time);
                System.out.println(" | active: " + active);
                Match match = new Match(tournamentID,id,matchType,date,time,active);
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
                int tournamentID = rs.getInt("tournamentID");
                int matchID = rs.getInt("matchID");
                int teamID = rs.getInt("teamID");
                int goals = rs.getInt("goals");
                int points = rs.getInt("points");

                //Display values
                System.out.println("TournamentID: " + tournamentID);
                System.out.print("MatchID: " + matchID);
                System.out.print(" | TeamID: " + teamID);
                System.out.print(" | Goals: " + goals);
                System.out.print(" | Points: " + points);
                Tournament tournament = new Tournament(tournamentID,matchID, teamID, goals);
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
                int id = rs.getInt("id");
                String tournametName = rs.getString("name");

                //Display values
                System.out.print("tournament ID: " + id);
                System.out.print("tournamentName: " + tournametName);
                Tournament tournament = new Tournament(id,tournametName);
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
