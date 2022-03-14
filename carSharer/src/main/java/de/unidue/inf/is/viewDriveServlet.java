package de.unidue.inf.is;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.unidue.inf.is.dao.ViewDriveDao;
import de.unidue.inf.is.domain.Schreiben;
import de.unidue.inf.is.domain.View_Drive;

/**
 * Servlet implementation class viewDriverServlet
 */
@WebServlet("/view_drive")
public class viewDriveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewDriveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object benutzerId = session.getAttribute("benutzerId");
		if(benutzerId == null) {
			request.getRequestDispatcher("/register.ftl").forward(request, response);
		}else {
		// fetch Fid as a query and get it as a parameter
		int fid = Integer.parseInt(request.getParameter("fid"));
		ViewDriveDao dao = new ViewDriveDao();
		// use fid to show unique fahrt and Bewertung row for specific fid
		View_Drive viewDrive = dao.viewDrive(fid);
		List<Schreiben> schreibenDrive = dao.schreibenDrive(fid);
		// set these data for print the in front-End page
		request.setAttribute("viewDrive", viewDrive);
		request.setAttribute("schreibenDrive", schreibenDrive);
		// fetch our random BenutzerId and use it in page to wirte that in tables 
		//HttpSession session = request.getSession(false);
		Object id = session.getAttribute("benutzerId");

		request.setAttribute("benutzerId", id);
		// send BenutzerId to other pages
		session.setAttribute("benutzerId", id);
		// use fid to show ating average for each driver afer rating and show it in front-End page
		View_Drive get_Average = dao.get_Average(fid);
		request.setAttribute("get_Average", get_Average.getAverage());

		request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
	}
	}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	// fetch all parameter to reserve Platz
		int benutzerId = Integer.parseInt(request.getParameter("benutzerId"));
		//---------
		int fahrtId = Integer.parseInt(request.getParameter("fid"));
		//check if user is driver or not
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7");
			Statement st = con.createStatement();
			
			ResultSet rs= st.executeQuery("select * from fahrt where fid= '"+fahrtId+"'");
			if(rs.next()) {
				String anbieterS = rs.getString("anbieter");
				int anbieter = Integer.parseInt(anbieterS);
				if(benutzerId == anbieter ) {
					request.setAttribute("error", "you can not reserve yor own vehicle");
					ViewDriveDao dao = new ViewDriveDao();
					// use methods to show information in page
					View_Drive viewDrive = dao.viewDrive(fahrtId);
					List<Schreiben> schreibenDrive = dao.schreibenDrive(fahrtId);
					request.setAttribute("viewDrive", viewDrive);
					request.setAttribute("schreibenDrive", schreibenDrive);
					
					request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

			// search in reservieren table to see if user already registered or not
			try {
				Connection con2 = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7");
				Statement st2 = con2.createStatement();
				
				ResultSet rs2= st2.executeQuery("select * from reservieren where fahrt= '"+fahrtId+"' and kunde= '"+benutzerId+"'");
				// get an error for a Benutzer, who already reserved and vehicle
				if(rs2.next()){
					request.setAttribute("error", "you already reserved");
					ViewDriveDao dao = new ViewDriveDao();
					// use methods to show information in page
					View_Drive viewDrive = dao.viewDrive(fahrtId);
					List<Schreiben> schreibenDrive = dao.schreibenDrive(fahrtId);
					request.setAttribute("viewDrive", viewDrive);
					request.setAttribute("schreibenDrive", schreibenDrive);
					
					request.getRequestDispatcher("/view_drive.ftl").forward(request, response);	
				}else {
					int anzPlaetze = Integer.parseInt(request.getParameter("anzPlaetze"));
					ViewDriveDao dao = new ViewDriveDao();
					dao.restPlatz(fahrtId,benutzerId,anzPlaetze);
					// use our class to show all updated data in view_drive.ftl page
					View_Drive viewDrive = dao.viewDrive(fahrtId);
					List<Schreiben> schreibenDrive = dao.schreibenDrive(fahrtId);
					request.setAttribute("viewDrive", viewDrive);
					request.setAttribute("schreibenDrive", schreibenDrive);
					
					request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
				}
			}catch(Exception e){
				System.out.println(e);
			}
		

    }

}
