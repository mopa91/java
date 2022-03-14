package datatypes;

/**
 * Contains Movie informations about adding rating by each user.

 *
 */
public class RateMovies {
	int movieid;
	int registerId; 
	String title;
	private String rateComment;
	private int rate;
	
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public int getRegisterId() {
		return registerId;
	}
	public void setRegisterId(int registerId) {
		this.registerId = registerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRateComment() {
		return rateComment;
	}
	public void setRateComment(String rateComment) {
		this.rateComment = rateComment;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public RateMovies(int movieid, int registerId, String title, String rateComment, int rate) {
		super();
		this.movieid = movieid;
		this.registerId = registerId;
		this.title = title;
		this.rateComment = rateComment;
		this.rate = rate;
	}

	
}
