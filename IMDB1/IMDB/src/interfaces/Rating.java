package interfaces;

/**
 * Interface that provides all method to rate each movie.

 */
public interface Rating {
	public void doRating(int movieid, int registerId, String title,String rateComment,int rate);
}
