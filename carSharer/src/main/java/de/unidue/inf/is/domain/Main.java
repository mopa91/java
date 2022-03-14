package de.unidue.inf.is.domain;

public class Main {
	private int fid;
	private String pic;
	private String startort;
	private String zielort;
	private String status;
	private int anzPlaetze;
	private int fahrtkosten;
	public int getFahrtkosten() {
		return fahrtkosten;
	}
	public void setFahrtkosten(int fahrtkosten) {
		this.fahrtkosten = fahrtkosten;
	}
	public int getAnzPlaetze() {
		return anzPlaetze;
	}
	public void setAnzPlaetze(int anzPlaetze) {
		this.anzPlaetze = anzPlaetze;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Main(int fid, String pic, String startort, String zielort, String status, int anzPlaetze, int fahrtkosten) {
		super();
		this.fid = fid;
		this.pic = pic;
		this.startort = startort;
		this.zielort = zielort;
		this.status = status;
		this.anzPlaetze = anzPlaetze;
		this.fahrtkosten = fahrtkosten;
	}

	
	
}