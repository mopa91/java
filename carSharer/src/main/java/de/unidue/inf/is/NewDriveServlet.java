package de.unidue.inf.is;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.unidue.inf.is.dao.NewDriveDao;

import de.unidue.inf.is.domain.New_Drive;
import de.unidue.inf.is.utils.DateTimeUtil;


/**
 * Servlet implementation class NewDriveServlet
 */
@WebServlet("/new_drive")
public class NewDriveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewDriveServlet() {
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
			// use a default Timestamp to give it as a min attribute for Date 
			String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
			request.setAttribute("time",date);
			request.setAttribute("benutzerID", benutzerId);
			session.setAttribute("benutzerId", benutzerId);
			request.getRequestDispatcher("/new_drive.ftl").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get all values form our post method in new_drive.ftl to send them in DB
        String startort = request.getParameter("startort");
        String zielort = request.getParameter("zielort");
        // get random number for Anbieter
        /*int[ ] numbers = { 1,3,5,7 };
        int max = numbers.length;
        int random = (int)(Math.random() * max);
        int anbieter = numbers[random];*/
        // ---
        int benutzerID = Integer.parseInt(request.getParameter("benutzerID"));
        int maxPlaetze = Integer.parseInt(request.getParameter("maxPlaetze"));
        int fahrtkosten = Integer.parseInt(request.getParameter("fahrtkosten"));
        String transportmittel = request.getParameter("transportmittel");
        
        String fahrtdatumzeit = request.getParameter("time");
        
        String datum = request.getParameter("date").trim().toString();
        // change time and date with a function to a Timestamp method
        String datapicker = DateTimeUtil.convertDateAndTimeToDB2DateTime(datum, fahrtdatumzeit);
        Timestamp timestamp = Timestamp.valueOf(datapicker);
        // ----
        String beschreibung = request.getParameter("beschreibung");
        // use if else to check our errors for inputs
        if (startort.isEmpty() || zielort.isEmpty() || transportmittel.isEmpty() || fahrtdatumzeit.isEmpty() || request.getParameter("maxPlaetze").isEmpty() || request.getParameter("fahrtkosten").isEmpty()) {

        	request.setAttribute("errors","please fill all the inputs");
        	request.getRequestDispatcher("").forward(request,response);

        }else if(beschreibung.length() > 50) {
        	request.setAttribute("errors","beschreibung should be maximal 50 word");
        	request.getRequestDispatcher("/new_drive.ftl").forward(request,response);
        }else if(maxPlaetze>10 || maxPlaetze <0) {
        	request.setAttribute("errors","maxPlaetze should be between 1 and 10");
        	request.getRequestDispatcher("/new_drive.ftl").forward(request,response);
        }else if(fahrtkosten <=0 ) {
        	request.setAttribute("errors","fahrtkosten should not be negative number");
        	request.getRequestDispatcher("/new_drive.ftl").forward(request,response);
        }else {
        		// send all date to ourginal class and set them for DB
    			New_Drive newUser = new New_Drive(startort, zielort, timestamp, maxPlaetze, fahrtkosten, benutzerID, transportmittel, beschreibung);
    			NewDriveDao ndd = new NewDriveDao();
    		try {
				ndd.insertNewDrive(newUser);
	        	response.sendRedirect("/main");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		doGet(request, response);
	}

}
