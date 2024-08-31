import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableComponent {
	
	private JFrame tableFrame;
    private JTable table;
    public DefaultTableModel tableModel;
    
    public TableComponent(String tableType) {
    	
    	tableFrame = new JFrame("Table " + tableType);
    	tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	tableFrame.setSize(800, 300);
    	
    	tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        updateTable(tableType);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        tableFrame.setLayout(new BorderLayout());
        tableFrame.add(scrollPane, BorderLayout.CENTER);
        
        tableFrame.setVisible(true);
    }
    
    public void updateTable(String tableType) {
    	
    	// Clears the current table contents and structure
    	tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        
        if (tableType.equals("Movies")) {
        	
        	tableModel.addColumn("movie_id");
        	tableModel.addColumn("movie_title");
        	tableModel.addColumn("release_date");
        	tableModel.addColumn("language");
        	tableModel.addColumn("popularity");
        	tableModel.addColumn("revenue");
        	tableModel.addColumn("budget");
        	tableModel.addColumn("status");
        	tableModel.addColumn("tagline");
        	tableModel.addColumn("vote_average");
        	tableModel.addColumn("vote_count");
        	tableModel.addColumn("runtime");
			//JTable moviesTable = new JTable(tableModel);
        }
        else if (tableType.equals("Countries")) {
        	
        	tableModel.addColumn("movie_id");
        	tableModel.addColumn("country_abbreviation");
        	tableModel.addColumn("country_name");
			//JTable countriesTable = new JTable(tableModel);
        } 
		else if (tableType.equals("Cast")) {
			
			tableModel.addColumn("movie_id");
        	tableModel.addColumn("cast_id");
        	tableModel.addColumn("actor_name");
        	tableModel.addColumn("gender");
        	tableModel.addColumn("character_cast_id");
        	tableModel.addColumn("character_name");
			//JTable castTable = new JTable(tableModel);
        } 
		else if (tableType.equals("Crew")) {
			
			tableModel.addColumn("movie_id");
        	tableModel.addColumn("crew_id");
        	tableModel.addColumn("crew_member_name");
        	tableModel.addColumn("gender");
        	tableModel.addColumn("department");
        	tableModel.addColumn("job");
			JTable crewTable = new JTable(tableModel);
        }
		else if (tableType.equals("Genres")) {
			
			tableModel.addColumn("movie_id");
        	tableModel.addColumn("genre_id");
        	tableModel.addColumn("genre_name");
			JTable genresTable = new JTable(tableModel);
        }
		else if (tableType.equals("Keywords")) {
			
			tableModel.addColumn("movie_id");
        	tableModel.addColumn("keyword_id");
        	tableModel.addColumn("keyword_name");
			JTable keywordsTable = new JTable(tableModel);
        }
		else if (tableType.equals("Production")) {
			
			tableModel.addColumn("movie_id");
        	tableModel.addColumn("production_company_id");
        	tableModel.addColumn("production_company_name");
			//JTable productionTable = new JTable(tableModel);
        }
		else if (tableType.equals("spoken_languages")) {
			
			tableModel.addColumn("movie_id");
        	tableModel.addColumn("language_code");
        	tableModel.addColumn("language_name");
			//JTable languagesTable = new JTable(tableModel);
        }
    }
}
