import java.util.ArrayList;
import java.util.Arrays;

public class Movie {
	
	private int budget;
	private ArrayList<Genres> genres;
	private String homepage;
	private int movieID;
	private ArrayList<Keywords> keywords;
	private String originalLanguage;
	private String originalTitle;
	private String overview;
	private double popularity;
	private ArrayList<ProductionCompanies> productionCompanies;
	private ArrayList<ProductionCountries> productionCountries;
	private String releaseDate;
	private long revenue;
	public double runTime;
	public ArrayList<SpokenLanguages> spokenLanguages;
	public String status;
	public String tagLine;
	public String title;
	public double voteAverage;
	public int voteCount;
	
	public Movie() {
		
	}
	
	public Movie(int budget, ArrayList<Genres> genres, String homepage, int movieID, ArrayList<Keywords> keywords,
			String originalLanguage, String originalTitle, String overview, double popularity,
			ArrayList<ProductionCompanies> productionCompanies, ArrayList<ProductionCountries> productionCountries, String releaseDate,
			long revenue, double runTime, ArrayList<SpokenLanguages> spokenLanguages, String status, String tagLine, String title,
			double voteAverage, int voteCount) {
		//super();
		this.budget = budget;
		this.genres = genres;
		this.homepage = homepage;
		this.movieID = movieID;
		this.keywords = keywords;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.overview = overview;
		this.popularity = popularity;
		this.productionCompanies = productionCompanies;
		this.productionCountries = productionCountries;
		this.releaseDate = releaseDate;
		this.revenue = revenue;
		this.runTime = runTime;
		this.spokenLanguages = spokenLanguages;
		this.status = status;
		this.tagLine = tagLine;
		this.title = title;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}
	
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public ArrayList<Genres> getGenres() {
		return genres;
	}
	public void setGenres(ArrayList<Genres> genres) {
		this.genres = genres;
	}
	
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	public ArrayList<Keywords> getKeywords() {
		return keywords;
	}
	public void setKeywords(ArrayList<Keywords> keywords) {
		this.keywords = keywords;
	}
	
	public String getOriginalLanguage() {
		return originalLanguage;
	}
	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}
	
	public String getOriginalTitle() {
		return originalTitle;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	
	public ArrayList<ProductionCompanies> getProductionCompanies() {
		return productionCompanies;
	}
	public void setProductionCompanies(ArrayList<ProductionCompanies> productionCompanies) {
		this.productionCompanies = productionCompanies;
	}
	
	public ArrayList<ProductionCountries> getProductionCountries() {
		return productionCountries;
	}
	public void setProductionCountries(ArrayList<ProductionCountries> productionCountries) {
		this.productionCountries = productionCountries;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public long getRevenue() {
		return revenue;
	}
	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}
	
	public double getRunTime() {
		return runTime;
	}
	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}
	
	public ArrayList<SpokenLanguages> getSpokenLanguages() {
		return spokenLanguages;
	}
	public void setSpokenLanguages(ArrayList<SpokenLanguages> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTagLine() {
		return tagLine;
	}
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public double getVoteAverage() {
		return voteAverage;
	}	
	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}
	
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Movie [budget=" + budget + ", genres=" + genres + ", homepage=" + homepage + ", movieID=" + movieID
				+ ", keywords=" + keywords + ", originalLanguage=" + originalLanguage + ", originalTitle="
				+ originalTitle + ", overview=" + overview + ", popularity=" + popularity + ", productionCompanies="
				+ productionCompanies + ", productionCountries=" + productionCountries + ", releaseDate=" + releaseDate
				+ ", revenue=" + revenue + ", runTime=" + runTime + ", spokenLanguages=" + spokenLanguages + ", status="
				+ status + ", tagLine=" + tagLine + ", title=" + title + ", voteAverage=" + voteAverage + ", voteCount="
				+ voteCount + "]";
	}
	
}

class Genres{
	
	int id;
    String name;

    public Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

	@Override
	public String toString() {
		return "Genres [id=" + id + ", name=" + name + "]";
	}   
}

class Keywords {
    int id;
    String name;

    public Keywords(int id, String name) {
        this.id = id;
        this.name = name;
    }

	@Override
	public String toString() {
		return "Keywords [id=" + id + ", name=" + name + "]";
	}
}

class ProductionCompanies {
    String name;
    int id;

    ProductionCompanies(String name, int id) {
        this.name = name;
        this.id = id;
    }

	@Override
	public String toString() {
		return "ProductionCompanies [name=" + name + ", id=" + id + "]";
	}
}

class ProductionCountries {
    String id;
    String name;

    ProductionCountries(String id, String name) {
        this.id = id;
        this.name = name;
    }

	@Override
	public String toString() {
		return "ProductionCountries [id=" + id + ", name=" + name + "]";
	}
}

class SpokenLanguages {
    String id;
    String name;

    SpokenLanguages(String id, String name) {
        this.id = id;
        this.name = name;
    }

	@Override
	public String toString() {
		return "SpokenLanguages [id=" + id + ", name=" + name + "]";
	}
}
