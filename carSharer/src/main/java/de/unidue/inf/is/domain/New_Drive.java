package de.unidue.inf.is.domain;

import java.sql.Timestamp;

public class New_Drive {
	private String startort;
	private String zielort;
	private Timestamp fahrtdatumzeit;
	private int maxPlaetze;
	private int fahrtkosten;
	private int anbieter;
	private String transportmittel;
	private String beschreibung;


	public String getStartort() {
		return startort;
	}
	public void setStartort(String startort) {
		this.startort = startort;
	}
	public String getZielort() {
		return zielort;
	}
	public void setZielort(String zielort) {
		this.zielort = zielort;
	}
	public Timestamp getFahrtdatumzeit() {
		return fahrtdatumzeit;
	}
	public void setFahrtdatumzeit(Timestamp fahrtdatumzeit) {
		this.fahrtdatumzeit = fahrtdatumzeit;
	}
	public int getMaxPlaetze() {
		return maxPlaetze;
	}
	public void setMaxPlaetze(int maxPlaetze) {
		this.maxPlaetze = maxPlaetze;
	}
	public int getFahrtkosten() {
		return fahrtkosten;
	}
	public void setFahrtkosten(int fahrtkosten) {
		this.fahrtkosten = fahrtkosten;
	}
	public int getAnbieter() {
		return anbieter;
	}
	public void setAnbieter(int anbieter) {
		this.anbieter = anbieter;
	}
	public String getTransportmittel() {
		return transportmittel;
	}
	public void setTransportmittel(String transportmittel) {
		this.transportmittel = transportmittel;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public New_Drive(String startort, String zielort, Timestamp fahrtdatumzeit, int maxPlaetze, int fahrtkosten,
			int anbieter, String transportmittel, String beschreibung) {
		super();
		;
		this.startort = startort;
		this.zielort = zielort;
		this.fahrtdatumzeit = fahrtdatumzeit;
		this.maxPlaetze = maxPlaetze;
		this.fahrtkosten = fahrtkosten;
		this.anbieter = anbieter;
		this.transportmittel = transportmittel;
		this.beschreibung = beschreibung;
	}

	
}
