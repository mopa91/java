package de.unidue.inf.is.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.unidue.inf.is.domain.Main;

public class MainDao {
	
	// using this method amd list to print all data in fahrt table in (main.ftl) page (MainServlet)
	public List<Main> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Main> users = new ArrayList<>();

		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) { 
			Statement st = con.createStatement();
			// transportmittel.tid should be same as fahrt.transportmittel afet that we can get icon and find which transportmittel used user
			ResultSet rs = st.executeQuery("select * from fahrt,transportmittel where transportmittel.tid = fahrt.transportmittel");
			while(rs.next()) {
					int fid = rs.getInt("fid");
					String startort = rs.getString("startort");
					String zielort = rs.getString("zielort");
					String status = rs.getString("status");
					String pic = rs.getString("icon");
					int anzPlaetze = rs.getInt("maxPlaetze");
					int fahrtkosten = rs.getInt("fahrtkosten");
					users.add(new Main(fid,pic,startort,zielort,status,anzPlaetze,fahrtkosten));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return users;


	}
	
}