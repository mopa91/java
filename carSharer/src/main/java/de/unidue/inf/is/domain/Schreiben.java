package de.unidue.inf.is.domain;

import java.sql.Clob;

public class Schreiben {
	private String benutzer;
	private int rating;
	private Clob bewertung;
	private String textnachricht;
	
	public String getTextnachricht() {
		return textnachricht;
	}
	public void setTextnachricht(String textnachricht) {
		this.textnachricht = textnachricht;
	}
	public String getBenutzer() {
		return benutzer;
	}
	public void setBenutzer(String benutzer) {
		this.benutzer = benutzer;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Clob getBewertung() {
		return bewertung;
	}
	public void setBewertung(Clob bewertung) {
		this.bewertung = bewertung;
	}
	public Schreiben(String benutzer, int rating, Clob bewertung) {
		super();
		this.benutzer = benutzer;
		this.rating = rating;
		this.bewertung = bewertung;
	}
	public Schreiben(String benutzer, int rating, String textnachricht) {
		super();
		this.benutzer = benutzer;
		this.rating = rating;
		this.textnachricht = textnachricht;
	}
	
	
}
