import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;

public class DatabaseConsole extends JFrame{
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/Databases_Final_Project";
	static final String USER = "root";
	static final String PASS = "passwordHere";
	
	private Connection conn;
	
	//private JTextField jtf;
	private JLabel tableSelect;
	private String[] tableOptions;
	private JComboBox tableComboBox;
	private JLabel queryTypeLabel;
	private JRadioButton selectRadioButton;
	private JRadioButton insertRadioButton;
	private JRadioButton updateRadioButton;
	private JRadioButton deleteRadioButton;
	private ButtonGroup radioButtonGroup;
	private JLabel recordlimitLabel;
	private JSpinner recordLimitSpinner;
	private JButton submitButton;
	private JButton clearButton;
	private JLabel customQueryLabel;
	private JTextField customQueryTextField;
	private DefaultTableModel tableModel;
	private JLabel insertQueryLabel;
	//private JLabel updateQueryLabel;
	//private JLabel deleteQueryLabel;
	private JTextField insertQueryTextField;
	private JLabel noteDeleteLabel;
	//private JTextField updateQueryTextField;
	//private JTextField deleteQueryTextField;
	
	public DatabaseConsole() {
		
		this.setTitle("Database Console");
		this.tableSelect = new JLabel("Select Table");
		
		this.tableOptions = new String[8];
		this.tableOptions[0] = "Cast";
		this.tableOptions[1] = "Countries";
		this.tableOptions[2] = "Crew";
		this.tableOptions[3] = "Genres";
		this.tableOptions[4] = "Keywords";
		this.tableOptions[5] = "Movies";
		this.tableOptions[6] = "Production";
		this.tableOptions[7] = "Spoken Languages";
		this.tableComboBox = new JComboBox<String>(this.tableOptions);
		
		this.queryTypeLabel = new JLabel("Select Query Type:");
		
		this.selectRadioButton = new JRadioButton("SELECT");
		this.selectRadioButton.setSelected(false);
		this.insertRadioButton = new JRadioButton("INSERT");
		this.insertRadioButton.setSelected(false);
		//this.updateRadioButton = new JRadioButton("UPDATE");
		//this.updateRadioButton.setSelected(false);
		//this.deleteRadioButton = new JRadioButton("DELETE");
		//this.deleteRadioButton.setSelected(false);
		this.radioButtonGroup = new ButtonGroup();
		this.radioButtonGroup.add(this.selectRadioButton);
		this.radioButtonGroup.add(this.insertRadioButton);
		//this.radioButtonGroup.add(this.updateRadioButton);
		//this.radioButtonGroup.add(this.deleteRadioButton);
		
		Box queryHorizontalBox = Box.createHorizontalBox();
		queryHorizontalBox.add(this.selectRadioButton);
		queryHorizontalBox.add(this.insertRadioButton);
		//queryHorizontalBox.add(this.updateRadioButton);
		//queryHorizontalBox.add(this.deleteRadioButton);
		
		this.recordlimitLabel = new JLabel("Number of records you want (Up to 100):");
		// Creates new JSpinner and sets the default, minimum, maximum, and increment values
		this.recordLimitSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		
		this.tableModel = new DefaultTableModel(new String[]{"movie_id", "movie_title", "release_date", "language", "popularity", "revenue", "budget", "status", "tagline", "vote_average", "vote_count", "runtime"}, 0);
		JTable moviesTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(moviesTable);
		scrollPane.setPreferredSize(new Dimension(800, 300));
	
		// need to add the table to the console later
		//this.add(scrollPane, BorderLayout.CENTER);
		
		this.submitButton = new JButton("Submit Query");
		this.clearButton = new JButton("Clear Selections");
		this.clearButton.setBackground(Color.YELLOW); // Sets Clear button to yellow
		this.submitButton.setBackground(Color.GREEN); // Sets Submit button to green
		
		InnerActionListener actionListener = new InnerActionListener();
		this.submitButton.addActionListener(actionListener);
		this.submitButton.setName("Submit Query");
		this.clearButton.addActionListener(actionListener);
		this.clearButton.setName("Clear Selections");
		
		this.noteDeleteLabel = new JLabel("Note: If you want to delete records from movie table, you need to delete records from all tables that reference it!");
		
		this.customQueryLabel = new JLabel("Enter other custom query here (Update or Delete Queries only) (Make sure to type the complete command):");
		this.customQueryTextField = new JTextField(40);
		
		// Lets the program know that the GridBagLayout manager will be used for this custom frame
		this.setLayout(new GridBagLayout());
		// Creates GridBagConstraints object
		GridBagConstraints layoutManager = new GridBagConstraints();
		
		// Sets the layoutManager axis to 0,0
		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		
		// Sets the amount of spacing between the different components
		// Bottom, Left, Right, Top
		layoutManager.insets = new Insets(5, 5, 10, 10); 
		
		this.insertQueryLabel = new JLabel("Insert Values (Type with space separations): (Only if Insert Option Selected / Make sure to first create movie record)");
		/*this.updateQueryLabel = new JLabel("Update Values (Type with space separations): (Only if Update Option Selected / Make sure the record first exists)");
		this.deleteQueryLabel = new JLabel("Delete Values (Type with space separations): (Only if Delete Option Selected / Make sure the record first exists)");*/
		
		this.insertQueryTextField = new JTextField(40);
		
		/*
		this.updateQueryTextField = new JTextField(40);
		this.deleteQueryTextField = new JTextField(40);*/
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 0;
		this.add(this.tableSelect, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 0;
		this.add(this.tableComboBox, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 1;
		this.add(this.queryTypeLabel, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 2;
		this.add(this.insertQueryLabel, layoutManager);
		
		/*layoutManager.gridx = 0;
		layoutManager.gridy = 3;
		this.add(this.updateQueryLabel, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 4;
		this.add(this.deleteQueryLabel, layoutManager);*/
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 2;
		this.add(this.insertQueryTextField, layoutManager);
		
		/*layoutManager.gridx = 1;
		layoutManager.gridy = 3;
		this.add(this.updateQueryTextField, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 4;
		this.add(this.deleteQueryTextField, layoutManager);*/
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 1;
		this.add(queryHorizontalBox, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 3;
		this.add(this.recordlimitLabel, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 3;
		this.add(this.recordLimitSpinner, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 4;
		this.add(this.customQueryLabel, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 4;
		this.add(this.customQueryTextField, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 5;
		this.add(this.submitButton, layoutManager);
		
		layoutManager.gridx = 1;
		layoutManager.gridy = 5;
		this.add(this.clearButton, layoutManager);
		
		layoutManager.gridx = 0;
		layoutManager.gridy = 6;
		this.add(this.noteDeleteLabel, layoutManager);
	}
	
	class InnerActionListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton) e.getSource(); // Gets the source of the action
			
			// Gets the name of the button and stores it in the name variable
			String name = button.getName();
			
			Statement statement = null;
			ResultSet resultSet = null;
			
			String selectedTable = "";
			String queryType = "";
			
			// Checks if the name of the button is Submit
			if(name.equals("Submit Query")) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
				}
				catch(Exception ex){
						
					ex.printStackTrace();	
				}
			
				if (customQueryTextField.getText().equals("")) {
					
					String insertValues = insertQueryTextField.getText();
					
					String[] insertedValues = insertValues.split(" ");
					
					queryType = "";
					
					selectedTable = (String) tableComboBox.getSelectedItem();
					
					if (selectedTable.equals("Spoken Languages")) {
						
						selectedTable = "spoken_languages";
					}
					
					TableComponent table = new TableComponent(selectedTable); 
					
					//Chooses the correct table 
					//updateTable(selectedTable);
				
					if(selectRadioButton.isSelected()) {
						queryType = "SELECT";
					}
					else if(insertRadioButton.isSelected()) {
						queryType = "INSERT";
					}
					else if(updateRadioButton.isSelected()){
						queryType = "UPDATE";
					}
					else if(deleteRadioButton.isSelected()){
						queryType = "DELETE";
					}
					else {
						queryType = "SELECT"; // If no radio button is selected, defaults to SELECT
					}
					
					int limit = (int) recordLimitSpinner.getValue();
					
					String fullSQLQuery = queryType + " * " + "FROM " + selectedTable + " LIMIT " + limit;
					String sqlQuery = "";
					
					if (queryType.equals("INSERT")) {
						
						if (selectedTable.equals("Movies")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setString(2, insertedValues[1]);
								pstmt.setString(3, insertedValues[2]);
								pstmt.setString(4, insertedValues[3]);
								pstmt.setFloat(5, Float.parseFloat(insertedValues[4]));
								pstmt.setFloat(6, Float.parseFloat(insertedValues[5]));
								pstmt.setFloat(7, Float.parseFloat(insertedValues[5]));
								pstmt.setString(8, insertedValues[7]);
								pstmt.setString(9, insertedValues[8]);
								pstmt.setFloat(10, Float.parseFloat(insertedValues[9]));
								pstmt.setFloat(11, Float.parseFloat(insertedValues[10]));
								pstmt.setFloat(12, Float.parseFloat(insertedValues[11]));
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
						
						if (selectedTable.equals("Cast")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?, ?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setFloat(2, Float.parseFloat(insertedValues[1]));
								pstmt.setString(3, insertedValues[2]);
								pstmt.setString(4, insertedValues[3]);
								pstmt.setFloat(5, Float.parseFloat(insertedValues[4]));
								pstmt.setString(6, insertedValues[5]);
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
						
						if (selectedTable.equals("Countries")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setString(2, insertedValues[1]);
								pstmt.setString(3, insertedValues[2]);
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
						
						if (selectedTable.equals("Crew")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?, ?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setFloat(2, Float.parseFloat(insertedValues[1]));
								pstmt.setString(3, insertedValues[2]);
								pstmt.setString(4, insertedValues[3]);
								pstmt.setString(5, insertedValues[4]);
								pstmt.setString(6, insertedValues[5]);
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
						
						if (selectedTable.equals("Genres")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setFloat(2, Float.parseFloat(insertedValues[1]));
								pstmt.setString(3, insertedValues[2]);
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
						
						if (selectedTable.equals("Keywords")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setFloat(2, Float.parseFloat(insertedValues[1]));
								pstmt.setString(3, insertedValues[2]);
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
						
						if (selectedTable.equals("Production")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setFloat(2, Float.parseFloat(insertedValues[1]));
								pstmt.setString(3, insertedValues[2]);
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
						
						if (selectedTable.equals("spoken_languages")) {
							sqlQuery = "INSERT INTO " + selectedTable + " VALUES(?, ?, ?);";
							
							PreparedStatement pstmt = null;
							try {
								pstmt = conn.prepareStatement(sqlQuery);
								pstmt.clearParameters();
								
								pstmt.setFloat(1, Float.parseFloat(insertedValues[0]));
								pstmt.setString(2, insertedValues[1]);
								pstmt.setString(3, insertedValues[2]);
							}
							catch (Exception exc) {
								
							}
							
							try {
								pstmt.executeUpdate();
							}
							catch (Exception exc) {
								
							}
							
							System.out.println("Executed Query: " + sqlQuery);
							clearForm();
						}
					}
					
					if (queryType.equals("SELECT")) {
						try {
							
							if (selectedTable.equals("Movies")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									String movie_title = resultSet.getString("movie_title");
									String movie_release_date = resultSet.getString("movie_release_date");
									String movie_language = resultSet.getString("movie_language");
									double movie_popularity = resultSet.getFloat("movie_popularity");
									double movie_revenue = resultSet.getFloat("movie_revenue");
									double movie_budget = resultSet.getFloat("movie_budget");
									String movie_status = resultSet.getString("movie_status");
									String movie_tagline = resultSet.getString("movie_tagline");
									double movie_vote_average = resultSet.getFloat("movie_vote_average");
									double movie_vote_count = resultSet.getFloat("movie_vote_count");
									double movie_runtime = resultSet.getFloat("movie_runtime");
									
									table.tableModel.addRow(new Object[]{movie_id, movie_title, movie_release_date, movie_language, movie_popularity, movie_revenue, movie_budget, movie_status, movie_tagline, movie_vote_average, movie_vote_count, movie_runtime});									
								}
							}
							
							else if (selectedTable.equals("Countries")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									String country_abbreviation = resultSet.getString("country_abbreviation");
									String country_name = resultSet.getString("country_name");
									
									table.tableModel.addRow(new Object[]{movie_id, country_abbreviation, country_name});
								}
							}
							
							else if (selectedTable.equals("Cast")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									double cast_id = resultSet.getFloat("cast_id");
									String actor_name = resultSet.getString("actor_name");
									String gender = resultSet.getString("gender");
									double character_cast_id = resultSet.getFloat("character_cast_id");
									String character_name = resultSet.getString("character_name");		
									
									table.tableModel.addRow(new Object[]{movie_id, cast_id, actor_name, gender, character_cast_id, character_name});									
								}
							}
							
							else if (selectedTable.equals("Crew")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									double crew_id = resultSet.getFloat("crew_id");
									String crew_member_name = resultSet.getString("crew_member_name");
									String gender = resultSet.getString("gender");
									String department = resultSet.getString("department");
									String job = resultSet.getString("job");			
									
									table.tableModel.addRow(new Object[]{movie_id, crew_id, crew_member_name, gender, department, job});
									
									//tableModel.addRow(new Object[]{movie_id, movie_title, movie_release_date, movie_language, movie_popularity, movie_revenue, movie_budget, movie_status, movie_tagline, movie_vote_average, movie_vote_count, movie_runtime});
								}
							}
							
							else if (selectedTable.equals("Genres")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									double genre_id = resultSet.getFloat("genre_id");
									String genre_name = resultSet.getString("genre_name");								
									
									table.tableModel.addRow(new Object[]{movie_id, genre_id, genre_name});									
								}
							}
							
							else if (selectedTable.equals("Keywords")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									double keyword_id = resultSet.getFloat("keyword_id");
									String keyword_name = resultSet.getString("keyword_name");								
									
									table.tableModel.addRow(new Object[]{movie_id, keyword_id, keyword_name});									
								}
							}
							
							else if (selectedTable.equals("Production")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									double production_company_id = resultSet.getFloat("production_company_id");
									String production_company_name = resultSet.getString("production_company_name");								
									
									table.tableModel.addRow(new Object[]{movie_id, production_company_id, production_company_name});									
								}
							}
							
							else if (selectedTable.equals("spoken_languages")) {
								statement = conn.createStatement();
					
								resultSet = statement.executeQuery(fullSQLQuery);
								
								while(resultSet.next()) {
									
									double movie_id = resultSet.getFloat("movie_id");
									String language_code = resultSet.getString("language_code");
									String language_name = resultSet.getString("language_name");								
									
									table.tableModel.addRow(new Object[]{movie_id, language_code, language_name});									
								}
							}
						}
						
						catch(Exception ex) {
							
							ex.printStackTrace();
						}
						
						System.out.println("Executed Query: " + fullSQLQuery);
						
						clearForm();
					}
				}
				
				else {
					
					String fullSQLQuery = customQueryTextField.getText();
					
					try {
						
						statement = conn.createStatement();
						statement.execute(fullSQLQuery);
						
						int updatedRecordsCount = statement.getUpdateCount();
			            System.out.println("Rows affected: " + updatedRecordsCount);
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
						
					System.out.println("Executed Query: " + customQueryTextField.getText());
					
					clearForm();
				}		
			}
			
			else {
				
				clearForm();
			}
		}
		
		private void clearForm() {
		
			tableComboBox.setSelectedIndex(0);
			radioButtonGroup.clearSelection();
			recordLimitSpinner.setValue(1);
			tableModel.setRowCount(0);
			insertQueryTextField.setText("");
			customQueryTextField.setText("");
		}
	}	
}
