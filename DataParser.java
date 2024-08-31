import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataParser {
	
	static Scanner fileScanner = null;
	static PrintWriter fileWriter = null;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Have to put your own path here
		String movieCSV = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProject/tmdb_5000_movies.csv";
		String creditsCSV = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProject/tmdb_5000_credits.csv";
		
		String parsedMoviesOutputCSV = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProject/parsedMovies.csv";
		String parsedGenresCSV = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProject/parsedGenres.csv";
		String parsedKeywordsCSV = "/Users/rohandaram/introtodatabases-workspace/IMDBFinalProject/parsedKeywords.csv";
		
		ArrayList<Movie> parsedMovieData = DataParser.parseMovieCSV(movieCSV);	
		writeMovieData(parsedMovieData, parsedMoviesOutputCSV);
	}
	
	public static ArrayList<Movie> parseMovieCSV(String inputFile) throws FileNotFoundException{
		
		DataParser.fileScanner = new Scanner(new FileReader(inputFile));
		String header = fileScanner.nextLine();
		System.out.println("Parsing and Writing to CSV Files....");
		
		ArrayList<Movie> movieData = new ArrayList<Movie>();
		String fileLine = "";
		
		while (fileScanner.hasNext()) {
			
			fileLine = fileScanner.nextLine();
			Movie movieParsed = parseCsvLine(fileLine);
			movieData.add(movieParsed);
		}
		fileScanner.close();
		return movieData;
	}
	
	public static void writeMovieData(ArrayList<Movie> parsedMovieData, String outputFile) throws FileNotFoundException {
		
		fileWriter = new PrintWriter(outputFile);
		
		fileWriter.println("movie_id,movie_title,release_date,popularity,runtime,budget,revenue,status,tagline,vote_average,vote_count,homepage,original_language,keywords,genres,overview,production_companies");
		
		// Code for parsedMoviesOutputCSV
		for (Movie movie : parsedMovieData) {
			
			//fileWriter.println(movie.getMovieID() + "," + movie.getTitle() + "," + movie.getGenres());
			fileWriter.println(movie.getMovieID() + "," + movie.getTitle() + "," 
			+ movie.getReleaseDate() + "," + movie.getPopularity() + "," +  movie.getRunTime() + ","
			+ movie.getBudget() + "," + movie.getRevenue() + "," + movie.getStatus() 
			+ movie.getTagLine() + "," + movie.getVoteAverage() + "," + + movie.getVoteCount() + ","
			+ movie.getHomepage() + "," + movie.getOriginalLanguage() + "," 
			+ movie.getKeywords() + "," + movie.getGenres() + movie.getOverview()
			+ movie.getProductionCompanies());
		}
		
		// Code for parsedGenresCSV
		for (Movie movie : parsedMovieData) {
			
			//fileWriter.println(movie.getMovieID() + "," + movie.getTitle() + "," + movie.getGenres());
			fileWriter.println(movie.getMovieID() + "," + movie.getTitle() + "," 
			+ movie.getReleaseDate() + "," + movie.getPopularity() + "," +  movie.getRunTime() + ","
			+ movie.getBudget() + "," + movie.getRevenue() + "," + movie.getStatus() 
			+ movie.getTagLine() + "," + movie.getVoteAverage() + "," + + movie.getVoteCount() + ","
			+ movie.getHomepage() + "," + movie.getOriginalLanguage() + "," 
			+ movie.getKeywords() + "," + movie.getGenres() + movie.getOverview()
			+ movie.getProductionCompanies());
		}
		
		fileWriter = new PrintWriter("/Users/rohandaram/introtodatabases-workspace/IMDBFinalProject/parsedGenres.csv");
		
		fileWriter.println("movie_id,genre_id,genre_name");
		
		for (Movie movie : parsedMovieData) {
			
			String genreOutput = "";
			
			if (movie.getGenres() != null) {
				for (Genres genre : movie.getGenres()) {
					
					genreOutput = genreOutput + genre.id + "," + genre.name + ",";
				}
				genreOutput = genreOutput.substring(0, genreOutput.length()-1);
			}
			
			// If the movie has no genres
			else {
				
				genreOutput = "null";
			}
			fileWriter.println(movie.getMovieID() + "," + genreOutput);
		}
		
		fileWriter = new PrintWriter("/Users/rohandaram/introtodatabases-workspace/IMDBFinalProject/parsedKeywords.csv");
		
		fileWriter.println("movie_id,keyword_id,keyword_name");
		
		for (Movie movie : parsedMovieData) {
			
			String keywordOutput = "";
			
			if (movie.getKeywords() != null && movie.getKeywords().size() > 0) {
				for (Keywords keyword : movie.getKeywords()) {
					
					keywordOutput = keywordOutput + keyword.id + "," + keyword.name + ",";
				}
				keywordOutput = keywordOutput.substring(0, keywordOutput.length()-1);
			}
			
			// If the movie has no keywords
			else {
				
				keywordOutput = "null";
			}
			fileWriter.println(movie.getMovieID() + "," + keywordOutput);
		}
		
		System.out.println();
		System.out.println("Finished Parsing and Writing the Records!");
	}	
	
	public static Movie parseCsvLine(String fileLine) {
		
		Movie parsedMovie = new Movie();	
		ArrayList<Genres> genres = new ArrayList<Genres>();
		
		fileLine = fileLine.replace("\"\"", "\"");

		String regex = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
		String[] headerValues = fileLine.split(regex);
		
		for (String header : headerValues) {
			
			//System.out.println(header);
		}
		
		// Makes sure the Movie record is not corrupted before creating the objects
		if (headerValues.length == 20 && headerValues[13] != "" && headerValues[1].length() > 2) {
			
			parsedMovie.setBudget(Integer.parseInt(headerValues[0]));
			parsedMovie.setGenres(parseGenres(headerValues[1]));
			parsedMovie.setHomepage(headerValues[2]);
			parsedMovie.setMovieID(Integer.parseInt(headerValues[3]));
			parsedMovie.setKeywords(parseKeywords(headerValues[4]));
			parsedMovie.setOriginalLanguage(headerValues[5]);
			parsedMovie.setOriginalTitle(headerValues[6]);
			parsedMovie.setOverview(headerValues[7]);
			parsedMovie.setPopularity(Double.parseDouble(headerValues[8]));
			parsedMovie.setProductionCompanies(parseProductionCompanies(headerValues[9]));
			// Not important for Movie recommendation so set to null
			parsedMovie.setProductionCountries(null);
			parsedMovie.setReleaseDate(headerValues[11]);
			parsedMovie.setRevenue(Long.parseLong(headerValues[12]));
			parsedMovie.setRunTime(Double.parseDouble(headerValues[13]));
			// Not important for Movie recommendation so set to null
			parsedMovie.setSpokenLanguages(null);
			parsedMovie.setStatus(headerValues[15]);
			parsedMovie.setTagLine(headerValues[16]);
			parsedMovie.setTitle(headerValues[17]);
			parsedMovie.setVoteAverage(Double.parseDouble(headerValues[18]));
			parsedMovie.setVoteCount(Integer.parseInt(headerValues[19]));
		}
		
		return parsedMovie;
	}
	
	public static ArrayList<Genres> parseGenres(String attribute){
		
		ArrayList<Genres> genres = new ArrayList<Genres>();
		
		if (attribute.length() > 2) {
			attribute = attribute.substring(3, attribute.length() - 3);
			String[] genreFields = attribute.split("\\}, \\{");
			
			for (String genreField : genreFields) {
				
				genreField = genreField.replaceAll("[\\{\\}\"]", "");
				String[] keyValuePairs = genreField.split(", ");
				
				int id = 0;
				String name = "";
				
				for (String keyValuePair : keyValuePairs) {
					
					String[] keyAndValue = keyValuePair.split(": ");
	                
	                if (keyAndValue[0].equals("id")) {
	                    id = Integer.parseInt(keyAndValue[1]);
	                } else if (keyAndValue[0].equals("name")) {
	                    name = keyAndValue[1];
	                }
				}
				genres.add(new Genres(id, name));
			}
		}
		
		return genres;
	}
	
	public static ArrayList<Keywords> parseKeywords(String attribute){
		
		ArrayList<Keywords> keyWords = new ArrayList<Keywords>();
		
		if (attribute.length() > 2) {
			attribute = attribute.substring(3, attribute.length() - 3);
			String[] keyWordFields = attribute.split("\\}, \\{");
			
			for (String keyWordField : keyWordFields) {
				
				keyWordField = keyWordField.replaceAll("[\\{\\}\"]", "");
				String[] keyValuePairs = keyWordField.split(", ");
				
				int id = 0;
				String name = "";
				
				for (String keyValuePair : keyValuePairs) {
					
					String[] keyAndValue = keyValuePair.split(": ");
	                
	                if (keyAndValue[0].equals("id")) {
	                    id = Integer.parseInt(keyAndValue[1]);
	                } else if (keyAndValue[0].equals("name")) {
	                    name = keyAndValue[1];
	                }
				}
				keyWords.add(new Keywords(id, name));
			}
		}
		
		return keyWords;
	}
	
	public static ArrayList<ProductionCompanies> parseProductionCompanies(String attribute){
		
		ArrayList<ProductionCompanies> productionCompanies = new ArrayList<ProductionCompanies>();
		
		if (attribute.length() > 2) {
			attribute = attribute.substring(3, attribute.length() - 3);
			String[] productionFields = attribute.split("\\}, \\{");
			
			for (String productionField : productionFields) {
				
				productionField = productionField.replaceAll("[\\{\\}\"]", "");
				String[] keyValuePairs = productionField.split(", ");
				
				int id = 0;
				String name = "";
				
				for (String keyValuePair : keyValuePairs) {
					
					String[] keyAndValue = keyValuePair.split(": ");
	                
	                if (keyAndValue[0].equals("id")) {
	                    id = Integer.parseInt(keyAndValue[1]);
	                } else if (keyAndValue[0].equals("name")) {
	                    name = keyAndValue[1];
	                }
				}
				productionCompanies.add(new ProductionCompanies(name, id));
			}
		}
		
		return productionCompanies;
	}
}
