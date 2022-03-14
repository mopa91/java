package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import datatypes.Movie;


/**
 * Class which acts as the connector between application and database. Creates
 * Java objects from SQL returns.

 */
public class DBFacade {
	private static DBFacade instance;
	
	/**
	 * Implementation of the Singleton pattern.
	 * 

	 */
	
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}
	
	
	/**
	 * Function that insert registration values in DB
	 
	 */

	public void registering(String name, String email, int age) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
		    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO register (name,email,age) VALUES (?, ?, ?)");

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setInt(3, age);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

	/**
	 * Function that insert addingMovie values in DB
	 
	 */
	
	public void addingMovieToDB(String title, String director, String actor, String publish) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
		    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO movies (title,director,actor,publishDate) VALUES (?, ?, ?, ?)");

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, director);
			preparedStatement.setString(3, actor);
			preparedStatement.setString(4, publish);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * Function that print whole the movieList in frontEnd page
	 
	 */
	
	public List <Movie> Movie () {
		// TODO Auto-generated method stub
		List<Movie> users = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from movies");
			while(rs.next()) {
					String title = rs.getString("title");
					String director = rs.getString("director");
					String actor = rs.getString("actor");
					String datum = rs.getString("publishDate");
					users.add(new Movie(title,director,actor,datum));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return users;
	}
	

	/**
	 * Function that check,that inserted movie name is already in DB or not
	 
	 */
	
	public datatypes.Movie checkingIfMovieIsIndDB(String title,String name) {
		Movie movieRate = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from movies,register where title = '"+title+"' and name ='"+name+"'");
			if(rs.next()) {
				int movieId = rs.getInt("movies.id");
				int registerId = rs.getInt("register.id");
				String username = rs.getString("register.name");
				String movieTitle = rs.getString("title");
				String director = rs.getString("director");
				String actor = rs.getString("actor");
				String datum = rs.getString("publishDate");
				movieRate = new Movie(movieId,registerId,username,movieTitle,director,actor,datum);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return movieRate;
	}

	/**
	 * Function that insert rating values in DB
	 
	 */
	public void get_presentMovieInfo(int movieid, int registerId, String title, String rateComment, int rate) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
		    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO rating (movieId,registerId,title,rateComment,rate) VALUES (? ,? ,? ,?, ?)");
		    preparedStatement.setInt(1, movieid);
		    preparedStatement.setInt(2,registerId);
		    preparedStatement.setString(3, title);
		    preparedStatement.setString(4, rateComment);
			preparedStatement.setInt(5, rate);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * Function that show the average of rating for each movie in frontEnd page
	 
	 */
	
	public  Movie get_MovieOverview( String title) {
		// TODO Auto-generated method stub
		Movie users = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select avg(rate) as average from rating where title = '"+title+"'");
			while(rs.next()) {
					String avgRate = rs.getString("average");
					Double avg = Double.parseDouble(avgRate);
					users = new Movie(avg);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return users;
	}


}
