package de.unidue.inf.is.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import de.unidue.inf.is.domain.Schreiben;
import de.unidue.inf.is.domain.View_Drive;



public class ViewDriveDao {
	
	// use this method and id to print that specific row for each driver (view_drive.ftl) (ViewDriveServlet)
	public View_Drive viewDrive(int fid) {
		View_Drive viewDrive = null;

		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from fahrt,transportmittel,benutzer,reservieren where transportmittel.tid = fahrt.transportmittel and fahrt.anbieter = benutzer.bid and fahrt.fid = '"+fid+"'" );
			while(rs.next()) {
				int fahrtId = rs.getInt("fid");
				String anbieter = rs.getString("email");
				String fahrtdatumzeit = rs.getString("fahrtdatumzeit");
				String startort = rs.getString("startort");
				String zielort = rs.getString("zielort");
				int anzPlaetze = rs.getInt("maxPlaetze");
				int fahrtkosten = rs.getInt("fahrtkosten");
				String status = rs.getString("status");
				String beschreibung = rs.getString("beschreibung");
				String pic = rs.getString("icon");

				viewDrive = new View_Drive(fahrtId,anbieter, fahrtdatumzeit, startort, zielort,anzPlaetze,fahrtkosten,status,beschreibung,pic);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return viewDrive;
	}
	
	// using this method to insert values in reservieren table and update our fahrt table (view_dirve.ftl) (ViewDriveServlet(post))
	public View_Drive restPlatz(int fahrtId, int benutzerId, int anzPlaetze) {
		
		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) {
			Statement st = con.createStatement();
			st.executeUpdate("insert into reservieren (kunde, fahrt, anzPlaetze) values ('"+benutzerId+"','"+fahrtId+"','"+anzPlaetze+"')");	
			ResultSet rs= st.executeQuery("select maxPlaetze from fahrt where fid='"+fahrtId+"'");
			// get the difference for maxPlatze and anzPlateze and update specific row in fhart table
			int bleib = 0;
			if(rs.next()){	
				bleib = Integer.parseInt(rs.getString("maxPlaetze")) - anzPlaetze;
				}
				if(bleib == 0) {
					st.executeUpdate("update fahrt set status='geschlossen' where fid='"+fahrtId+"'");
				}else {
					st.executeUpdate("update fahrt set maxPlaetze= '"+bleib+"' where fid='"+fahrtId+"'");
				}
		} 
		 
		catch(Exception e){
			System.out.println(e);
		}
		return null;
		
	}
	
	// use this method to print Bewertung for each driver (ViewDriveServlet)
	public List<Schreiben> schreibenDrive(int fid) {
		
		List<Schreiben> users = new ArrayList<>();

		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select benutzer.email,bewertung.rating,bewertung.textnachricht from schreiben,benutzer,bewertung where schreiben.fahrt = '"+fid+"' and benutzer.bid=schreiben.benutzer and bewertung.beid=schreiben.bewertung");
			while(rs.next()) {
				String benutzer = rs.getString("email");
				int rating = rs.getInt("rating");
				String textnachricht = rs.getString("textnachricht");
				users.add(new Schreiben(benutzer,rating,textnachricht));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return users;
	}
	
	// use this method to get an average for each drive with help of fid (ViewDriveServlet)
	public View_Drive get_Average (int fid) {
		View_Drive users = null;
		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) {
			Statement st = con.createStatement();
			ResultSet rs2 = st.executeQuery("select fahrt from schreiben where fahrt = '"+fid+"'");
			// if there is no rate for this driver we got a zero
			if(!rs2.next()) {
				users = new View_Drive(0.0);
			}else {
				ResultSet rs = st.executeQuery("select avg(rating) as average from bewertung,schreiben where bewertung=beid and fahrt = '"+fid+"'");
				while(rs.next()) {
					String avgRate = rs.getString("average");
					Double avg = Double.parseDouble(avgRate);
					System.out.print(avg);
					users = new View_Drive(avg);
				}
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return users;
	}
	
	// use this method to delete whole data which belong to driver (in schreiben,reservieren,bewertung nad fahrt table) (DeleteServlet)
	public View_Drive delete(int fid) {
				
		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) {
			System.out.println("Verbindung:" + con);
			Statement st = con.createStatement();
			
			//fetch all related beid from schreiben
			ResultSet rsSchrWithBeids = st.executeQuery("select bewertung from schreiben where fahrt="+fid);
			//and convert rs into List
			ArrayList<Integer> beids = new ArrayList<>();
			while (rsSchrWithBeids.next()) {
				beids.add(rsSchrWithBeids.getInt("bewertung"));
			}
			
			// delete schreiben row
			ResultSet rsSchreiben = st.executeQuery("select * from schreiben where fahrt="+fid);
			if(!rsSchreiben.next()) {
				System.out.println("there is no fahrt id");
			}
			st.executeUpdate("delete from schreiben where fahrt="+fid);
			System.out.println("Row in schreiben Deleted Successfully!");	
			// delete reservieren row
			ResultSet rsReservieren = st.executeQuery("select * from reservieren where fahrt="+fid);
			if(!rsReservieren.next()) {
				System.out.println("there is no fahrt id");
			}else {
				st.executeUpdate("delete from reservieren where fahrt="+fid);
				System.out.println("Row in reservieren Deleted Successfully!");
			}
			
			//delete bewertung row with help of beid-List
			for (int i: beids) {
				st.executeUpdate("delete from bewertung where beid=" + i);
				System.out.println("gelöscht in bewertung");
			}
			//delete fahrt row
			st.executeUpdate("delete from fahrt where fid="+fid);
			System.out.println("gelöscht in Fahrt");
			
			
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
}

