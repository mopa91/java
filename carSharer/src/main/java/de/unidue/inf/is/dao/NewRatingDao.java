package de.unidue.inf.is.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.unidue.inf.is.domain.New_Rating;

public class NewRatingDao {

	// use this method to insert values in Bewertung and schreiben(we use them as a foreign key in this table) tables  (new_rating.ftl)
	public void insertNewRating(New_Rating user) throws SQLException {
		
		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) {
			Statement st = con.createStatement();
			// use Clob to change String value to Clob
		
		
			String INSERT_USERS_SQL = "INSERT INTO bewertung (textnachricht, erstellungsdatum, rating) VALUES (?, ?, ?)";
			System.out.println(INSERT_USERS_SQL);
			// try-with-resource statement will auto close the connection.
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SQL);
			// get beschreibung and set it as a Clob to database
			String text = user.getText();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(text.getBytes());
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			preparedStatement.setClob(1, inputStreamReader);
			
			preparedStatement.setTimestamp(2, new java.sql.Timestamp(java.lang.System.currentTimeMillis()));
			int rating = Integer.parseInt(user.getRating());
			preparedStatement.setInt(3,rating );
			preparedStatement.executeUpdate();
			// choose a last writed id for insert it as a new Benutzerid in schreiben table
			int id=0;
			ResultSet rs= st.executeQuery("select * from bewertung ORDER BY beid DESC");
			if(rs.next()){	
				id = rs.getInt("beid");
				}
				System.out.println("Last inserted Id: " + id);
				// get fahrtid
				int fahrtId = user.getFahrtId();
				int benutzerId = user.getBenutzerId();
				// ---
				st.executeUpdate("insert into schreiben (benutzer, fahrt, bewertung) values ('"+benutzerId+"','"+fahrtId+"','"+id+"')");
			} 
		catch (SQLException e) {
			System.out.println(e);
			}
		finally {
			  //clob.free();
			}
	}

}
