package de.unidue.inf.is.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.unidue.inf.is.domain.New_Drive;


public class NewDriveDao {

	// use this method to insert all neu fahrt to fahrt table (new_drive.ftl) (NewDriveServlet)
	public void insertNewDrive(New_Drive user) throws SQLException {
		String INSERT_USERS_SQL = "INSERT INTO fahrt (startort, zielort, fahrtdatumzeit, maxPlaetze, fahrtkosten, anbieter, transportmittel, beschreibung) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7")) {
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SQL);
			// use Clob to change String value to Clob
			
		
			preparedStatement.setString(1, user.getStartort());
			preparedStatement.setString(2, user.getZielort());
			preparedStatement.setTimestamp(3, user.getFahrtdatumzeit());
			preparedStatement.setInt(4, user.getMaxPlaetze());
			preparedStatement.setInt(5, user.getFahrtkosten());
			preparedStatement.setInt(6, user.getAnbieter());
			preparedStatement.setString(7, user.getTransportmittel());
			// get beschreibung and set it as a Clob to database
			String beschreibung = user.getBeschreibung();
			ByteArrayInputStream inputStream = new ByteArrayInputStream(beschreibung.getBytes());
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			preparedStatement.setClob(8, inputStreamReader);
			
			preparedStatement.executeUpdate();


		} catch (SQLException e) {
			System.out.println(e);
		}finally {
			// we should wirte this line otherwise we get an SQL error
			  //clob.free();
		}
	}
	
}
