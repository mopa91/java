package application;

import java.util.ArrayList;
import java.util.List;

import datatypes.Movie;
import dbadapter.DBFacade;
import interfaces.AddMovie;
import interfaces.IntRegister;
import interfaces.Rating;

/**
 * This class contains the MRApplication which acts as the interface between all
 * components.

 */

public class MRAapplication implements IntRegister,AddMovie,Rating {
	private static MRAapplication instance;
	
	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	
	public static MRAapplication getInstance() {
		if (instance == null) {
			instance = new MRAapplication();
		}

		return instance;
	}
	
	/**
	 * Calls DBFacace method to doing registration
	 * parameters.
	 * 
	 */
	
	@Override
	public void doRegistration(String name, String email, String age) {
		// TODO Auto-generated method stub

		try {
			String nameSQL = name;
			String emailSQl = email;
			int ageSQL = Integer.parseInt(age);
			DBFacade.getInstance().registering(nameSQL,emailSQl,ageSQL);
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	/**
	 * Calls DBFacace method to doing movies
	 * parameters.
	 * 
	 */

	@Override
	public void doAdding(String title, String director, String actor, String publish) {
		// TODO Auto-generated method stub
		try {
			String titleSQL = title;
			String drictorSQL = director;
			String actorSQL = actor;
			String publishSQL = publish;
			DBFacade.getInstance().addingMovieToDB(titleSQL, drictorSQL, actorSQL, publishSQL);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * Calls DBFacace method to show movieList in page
	 * parameters.
	 * 
	 */
	
	public List <Movie> Movie () {
		// TODO Auto-generated method stub
		List<Movie> users = new ArrayList<>();
		try {

			users = DBFacade.getInstance().Movie();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}
	
	/**
	 * Calls DBFacace method to check,that we already rated or not
	 * parameters.
	 * 
	 */

	public datatypes.Movie checkingIfMovieIsIndDB(String title,String name) {

			return DBFacade.getInstance().checkingIfMovieIsIndDB(title,name);

	}
	
	/**
	 * Calls DBFacace method to add rating to DB
	 * parameters.
	 * 
	 */

	@Override
	public void doRating(int movieid, int registerId, String title, String rateComment, int rate) {
		// TODO Auto-generated method stub
		 DBFacade.getInstance().get_presentMovieInfo(movieid,registerId,title,rateComment,rate);
	}
	
	/**
	 * Calls DBFacace method to get average movieRate
	 * parameters.
	 * 
	 */

	public  Movie doAccessOverview( String title) {
		// TODO Auto-generated method stub
		return DBFacade.getInstance().get_MovieOverview(title);
	}




}
