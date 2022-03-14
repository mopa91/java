package de.unidue.inf.is.domain;

public class New_Rating {
	private int benutzerId;
	private int fahrtId;
	private String text;
	private String rating;
	
	public int getBenutzerId() {
		return benutzerId;
	}
	public void setBenutzerId(int benutzerId) {
		this.benutzerId = benutzerId;
	}
	public int getFahrtId() {
		return fahrtId;
	}
	public void setFahrtId(int fahrtId) {
		this.fahrtId = fahrtId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
	public New_Rating(int fahrtId) {
		super();
		this.fahrtId = fahrtId;
	}
	public New_Rating(int benutzerId, int fahrtId, String text, String rating) {
		super();
		this.benutzerId = benutzerId;
		this.fahrtId = fahrtId;
		this.text = text;
		this.rating = rating;
	}


}
