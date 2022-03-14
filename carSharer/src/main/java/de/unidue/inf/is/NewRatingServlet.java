package de.unidue.inf.is;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.unidue.inf.is.dao.NewRatingDao;
import de.unidue.inf.is.dao.ViewDriveDao;
import de.unidue.inf.is.domain.New_Rating;

import de.unidue.inf.is.domain.View_Drive;

/**
 * Servlet implementation class NewRatingServlet
 */
@WebServlet("/new_rating")
public class NewRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewRatingServlet() {
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
		// get BenutzerId from previous page to insert it in specific tables 
		//HttpSession session = request.getSession(false);
		Object id = session.getAttribute("benutzerId");
		request.setAttribute("benutzerId", id);
		// fetch fid as a parameter to show rate page for users,who used these cars
		int fid = Integer.parseInt(request.getParameter("fid"));
		ViewDriveDao dao = new ViewDriveDao();
		// send fid to dao file and fetch it for specific driver
		View_Drive viewDrive = dao.viewDrive(fid);
		request.setAttribute("viewDrive", viewDrive);
		
		request.getRequestDispatcher("/new_rating.ftl").forward(request, response);
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get all parameters from users
		int benutzerId = Integer.parseInt(request.getParameter("benutzerId"));
        String text = request.getParameter("Bewertungstext");
        String rating = request.getParameter("Bewertungsrating");
        // fetch fid as a default parameter
        int fahrtId = Integer.parseInt(request.getParameter("fid"));
        // simple check for inputs
        if (text.isEmpty() || rating.isEmpty()) {
    		// fetch fid as a parameter to show rate page for users,who used these cars
    		int fid = Integer.parseInt(request.getParameter("fid"));
    		ViewDriveDao dao = new ViewDriveDao();
    		// send fid to dao file and fetch it for specific driver
    		View_Drive viewDrive = dao.viewDrive(fid);
    		request.setAttribute("viewDrive", viewDrive);
    		request.setAttribute("errors","please fill all the inputs");
    		request.getRequestDispatcher("/new_rating.ftl").forward(request, response);
        }else {
        	try {
        		Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7");
				Statement st = con.createStatement();
				
				ResultSet rs2= st.executeQuery("select * from schreiben where fahrt= '"+fahrtId+"' and benutzer= '"+benutzerId+"'");
				// get an error for a Benutzer, who already reserved and vehicle
				if(rs2.next()){
					request.setAttribute("error", "you already rated");
					// get BenutzerId from previous page to insert it in specific tables 
					HttpSession session = request.getSession(false);
					Object id = session.getAttribute("benutzerId");
					request.setAttribute("benutzerId", id);
					// fetch fid as a parameter to show rate page for users,who used these cars
					int fid = Integer.parseInt(request.getParameter("fid"));
					ViewDriveDao dao = new ViewDriveDao();
					// send fid to dao file and fetch it for specific driver
					View_Drive viewDrive = dao.viewDrive(fid);
					request.setAttribute("viewDrive", viewDrive);
					
					request.getRequestDispatcher("/new_rating.ftl").forward(request, response);
				}else {
	        		// use dao method to send parameters to DB
	        		New_Rating newRating = new New_Rating(benutzerId,fahrtId,text,rating);
	        		NewRatingDao nrd = new NewRatingDao();
					nrd.insertNewRating(newRating);
					//---------
		        	response.sendRedirect("/main");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
		doGet(request, response);
	}

}
