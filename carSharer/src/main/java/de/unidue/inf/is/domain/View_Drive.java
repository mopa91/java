package de.unidue.inf.is.domain;

public class View_Drive {
	private int fahrtId;
	private Double average;
	private int benutzerId;
	private String anbieter;
	private String fahrtdatumzeit;
	private String startort;
	private String zielort;
	private int anzPlaetze;
	private int fahrtkosten;
	private String status;
	private String beschreibung;
	private String pic;
	
	public int getFahrtId() {
		return fahrtId;
	}
	public void setFahrtId(int fahrtId) {
		this.fahrtId = fahrtId;
	}
	
	
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public int getBenutzerId() {
		return benutzerId;
	}
	public void setBenutzerId(int benutzerId) {
		this.benutzerId = benutzerId;
	}
	public String getAnbieter() {
		return anbieter;
	}
	public void setAnbieter(String anbieter) {
		this.anbieter = anbieter;
	}
	public String getFahrtdatumzeit() {
		return fahrtdatumzeit;
	}
	public void setFahrtdatumzeit(String fahrtdatumzeit) {
		this.fahrtdatumzeit = fahrtdatumzeit;
	}
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
	public int getAnzPlaetze() {
		return anzPlaetze;
	}
	public void setAnzPlaetze(int anzPlaetze) {
		this.anzPlaetze = anzPlaetze;
	}
	public int getFahrtkosten() {
		return fahrtkosten;
	}
	public void setFahrtkosten(int fahrtkosten) {
		this.fahrtkosten = fahrtkosten;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public View_Drive(int fahrtId, String anbieter, String fahrtdatumzeit, String startort, String zielort,
			int anzPlaetze, int fahrtkosten, String status, String beschreibung, String pic) {
		super();
		this.fahrtId = fahrtId;
		this.anbieter = anbieter;
		this.fahrtdatumzeit = fahrtdatumzeit;
		this.startort = startort;
		this.zielort = zielort;
		this.anzPlaetze = anzPlaetze;
		this.fahrtkosten = fahrtkosten;
		this.status = status;
		this.beschreibung = beschreibung;
		this.pic = pic;
	}
	public View_Drive(int fahrtId, int benutzerId, int anzPlaetze) {
		super();
		this.fahrtId = fahrtId;
		this.benutzerId = benutzerId;
		this.anzPlaetze = anzPlaetze;
	}
	public View_Drive(Double average) {
		super();
		this.average = average;
	}
	
}
