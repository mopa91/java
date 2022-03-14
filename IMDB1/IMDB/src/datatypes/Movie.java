package datatypes;

import java.util.ArrayList;

/**
 * Contains Movie informations about adding movies,showing whole movies in frontEnd page, average of movies.

 *
 */

public class Movie {
	private Double rate;
	private int registerId;
	private int movieid;
	private String username;
	private String title;
	private String director;
	private String actor;
	private String publishDate;
	private ArrayList<Movie> movie;
	
	
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRegisterId() {
		return registerId;
	}
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public ArrayList<Movie> getMovie() {
		return movie;
	}
	public void setMovie(ArrayList<Movie> movie) {
		this.movie = movie;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public Movie(String title, String director, String actor, String publishDate) {
		super();
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.publishDate = publishDate;
	}
	
	public Movie(int movieid, int registerId, String title, String director, String actor, String publishDate) {
		super();
		this.movieid = movieid;
		this.registerId = registerId;
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.publishDate = publishDate;

	}
	public Movie(ArrayList<Movie> movie) {
		super();
		this.movie = movie;
	}
	public Movie(int movieid,int registerId, String username, String title, String director, String actor,
			String publishDate) {
		super();
		this.movieid = movieid;
		this.registerId = registerId;
		this.username = username;
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.publishDate = publishDate;
	}
	public Movie(Double rate) {
		super();
		this.rate = rate;
	}

	
}
