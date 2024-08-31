import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.SwingUtilities;

public class DatabaseLoadPlusConsole {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/?useSSL=false";
	static final String USER = "root";
	static final String PASS = "passwordHere";
	
	public static void main(String[] args) {
		
		Connection conn = null;
		String csvFile = "";
		BufferedReader br = null;
		String line = "";
		String delimiter = ",";
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			Statement stmt = conn.createStatement();
			
			// Drop Database
			String sqlQuery = "DROP DATABASE IF EXISTS Databases_Final_Project";
			stmt.executeUpdate(sqlQuery);
			
			// Create Database
			sqlQuery = "CREATE DATABASE Databases_Final_Project";
			stmt.executeUpdate(sqlQuery);
			System.out.println("Database created");
			
			sqlQuery = "USE Databases_Final_Project";
			stmt.executeUpdate(sqlQuery);
			
			// Have to put your own path here
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/movie.csv";
			
			System.out.println();
			System.out.println("Creating movies Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS movies ("
					+ "movie_id FLOAT NOT NULL,"
					+ "movie_title VARCHAR(100) NULL,"
					+ "movie_release_date VARCHAR(20) NULL,"
					+ "movie_language VARCHAR(10) NULL,"
					+ "movie_popularity FLOAT NULL,"
					+ "movie_revenue FLOAT NULL,"
					+ "movie_budget FLOAT NULL,"
					+ "movie_status VARCHAR(100) NULL,"
					+ "movie_tagline VARCHAR(1000) NULL,"
					+ "movie_vote_average FLOAT NULL,"
					+ "movie_vote_count FLOAT NULL,"
					+ "movie_runtime FLOAT NULL,"
					+ "PRIMARY KEY (movie_id),"
					+ "UNIQUE INDEX movie_id_UNIQUE (movie_id ASC) VISIBLE)";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				sqlQuery = "INSERT INTO movies VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setString(2, token[++i]);
				pstmt.setString(3, token[++i]);
				pstmt.setString(4, token[++i]);
				pstmt.setFloat(5, Float.parseFloat(token[++i]));
				pstmt.setFloat(6, Float.parseFloat(token[++i]));
				pstmt.setFloat(7, Float.parseFloat(token[++i]));
				pstmt.setString(8, token[++i]);
				pstmt.setString(9, token[++i]);
				pstmt.setFloat(10, Float.parseFloat(token[++i]));
				pstmt.setFloat(11, Float.parseFloat(token[++i]));
				pstmt.setFloat(12, Float.parseFloat(token[++i]));
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				//pstmt.close();
				
			}
			
			System.out.println("Movies Table Created");
			System.out.println();	
						
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/cast.csv";
			
			System.out.println();
			System.out.println("Creating cast Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS cast ("
					+ "movie_id FLOAT NOT NULL,"
					+ "cast_id FLOAT NULL,"
					+ "actor_name VARCHAR(100) NULL,"
					+ "gender VARCHAR(1) NULL,"
					+ "character_cast_id FLOAT NULL,"
					+ "character_name VARCHAR(100) NULL,"
					+ "FOREIGN KEY (movie_id) REFERENCES movies(movie_id))";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				//sqlQuery = "INSERT INTO cast VALUES(?, ?, ?, ?, ?, ?)";
			    sqlQuery = "INSERT INTO cast (movie_id, cast_id, actor_name, gender, character_cast_id, character_name) VALUES(?, ?, ?, ?, ?, ?)";
				
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setFloat(2, Float.parseFloat(token[++i]));
				pstmt.setString(3, token[++i]);
				pstmt.setString(4, token[++i]);
				pstmt.setFloat(5, Float.parseFloat(token[++i]));
				pstmt.setString(6, token[++i]);
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				//pstmt.close();
				
			}
			
			System.out.println("Cast Table Created");
			System.out.println();
			
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/countries.csv";
			
			System.out.println();
			System.out.println("Creating countries Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS countries ("
					+ "movie_id FLOAT NOT NULL,"
					+ "country_abbreviation VARCHAR(2) NULL,"
					+ "country_name VARCHAR(200) NULL,"
					+ "FOREIGN KEY (movie_id) REFERENCES movies(movie_id))";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				sqlQuery = "INSERT INTO countries VALUES(?, ?, ?)";
			    
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setString(2, token[++i]);
				pstmt.setString(3, token[++i]);
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				//pstmt.close();
				
			}
			
			System.out.println("Countries Table Created");
			System.out.println();
			
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/crew.csv";
			
			System.out.println();
			System.out.println("Creating crew Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS crew ("
					+ "movie_id FLOAT NOT NULL,"
					+ "crew_id FLOAT NULL,"
					+ "crew_member_name VARCHAR(100) NULL,"
					+ "gender VARCHAR(1) NULL,"
					+ "department VARCHAR(100) NULL,"
					+ "job VARCHAR(100) NULL,"
					+ "FOREIGN KEY (movie_id) REFERENCES movies(movie_id))";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				sqlQuery = "INSERT INTO crew VALUES(?, ?, ?, ?, ?, ?)";
			    
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setFloat(2, Float.parseFloat(token[++i]));
				pstmt.setString(3, token[++i]);
				pstmt.setString(4, token[++i]);
				pstmt.setString(5, token[++i]);
				pstmt.setString(6, token[++i]);
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				//pstmt.close();
				
			}
			
			System.out.println("Crew Table Created");
			System.out.println();
			
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/genres.csv";
			
			System.out.println();
			System.out.println("Creating genres Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS genres ("
					+ "movie_id FLOAT NOT NULL,"
					+ "genre_id FLOAT NULL,"
					+ "genre_name VARCHAR(100) NULL,"
					+ "FOREIGN KEY (movie_id) REFERENCES movies(movie_id))";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				sqlQuery = "INSERT INTO genres VALUES(?, ?, ?)";
			    
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setFloat(2, Float.parseFloat(token[++i]));
				pstmt.setString(3, token[++i]);
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				//pstmt.close();
				
			}
			
			System.out.println("Genres Table Created");
			System.out.println();
			
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/keywords.csv";
			
			System.out.println();
			System.out.println("Creating keywords Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS keywords ("
					+ "movie_id FLOAT NOT NULL,"
					+ "keyword_id FLOAT NULL,"
					+ "keyword_name VARCHAR(100) NULL,"
					+ "FOREIGN KEY (movie_id) REFERENCES movies(movie_id))";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				sqlQuery = "INSERT INTO keywords VALUES(?, ?, ?)";
			    
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setFloat(2, Float.parseFloat(token[++i]));
				pstmt.setString(3, token[++i]);
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				//pstmt.close();
				
			}
			
			System.out.println("Keywords Table Created");
			System.out.println();
			
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/production.csv";
			
			System.out.println();
			System.out.println("Creating production Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS production ("
					+ "movie_id FLOAT NOT NULL,"
					+ "production_company_id FLOAT NULL,"
					+ "production_company_name VARCHAR(100) NULL,"
					+ "FOREIGN KEY (movie_id) REFERENCES movies(movie_id))";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				sqlQuery = "INSERT INTO production VALUES(?, ?, ?)";
			    
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				// Some of the records are not in the proper format
				// 13677 records
				// Such as this one, 81005,Pictures,Big Kid Pictures
				// Sets the default production_company_id to 0 if this happens
				try{
					
					Float.parseFloat(token[1]);
				}
				catch (NumberFormatException nfe){
					
					token[1] = "0";
				}
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setFloat(2, Float.parseFloat(token[++i]));
				pstmt.setString(3, token[++i]);
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				//pstmt.close();
				
			}
			
			System.out.println("Production Table Created");
			System.out.println();
			
			csvFile = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProjectGUI/spoken_languages.csv";
			
			System.out.println();
			System.out.println("Creating Spoken Languages Table...");
			System.out.println();
			
			sqlQuery = "CREATE TABLE IF NOT EXISTS spoken_languages ("
					+ "movie_id FLOAT NOT NULL,"
					+ "language_code VARCHAR(2) NULL,"
					+ "language_name VARCHAR(100) NULL,"
					+ "FOREIGN KEY (movie_id) REFERENCES movies(movie_id))";
			
			stmt.executeUpdate(sqlQuery);
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				
				int i = -1;
				String[] token = line.split(delimiter);	
				sqlQuery = "INSERT INTO spoken_languages VALUES(?, ?, ?)";
			    
				PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
				pstmt.clearParameters();
				
				pstmt.setFloat(1, Float.parseFloat(token[++i]));
				pstmt.setString(2, token[++i]);
				pstmt.setString(3, token[++i]);
				
				try {
					pstmt.executeUpdate();
				}
				catch (Exception e) {
					
				}
				pstmt.close();
				
			}
			
			System.out.println("Spoken Languages Table Created");
			System.out.println();
		}
		catch (Exception e) {
			
			System.out.println(line);
			System.out.println("SQL Exception.");
			e.printStackTrace();
		}
		
		
		// Start of the GUI
		DatabaseConsole databaseConsoleFrame = new DatabaseConsole();
		databaseConsoleFrame.setDefaultCloseOperation(DatabaseConsole.EXIT_ON_CLOSE); // Sets up the default close method
		databaseConsoleFrame.pack(); // Makes sure to resize the window according to how many components placed
		databaseConsoleFrame.setVisible(true); // Makes sure to set the frame to be visible to the user
	}
}
