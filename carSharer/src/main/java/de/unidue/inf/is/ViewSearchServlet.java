package de.unidue.inf.is;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.unidue.inf.is.dao.MainDao;
import de.unidue.inf.is.domain.Main;


/**
 * Servlet implementation class ViewSearchServlet
 */
@WebServlet("/view_search")
public class ViewSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewSearchServlet() {
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
			// TODO Auto-generated method stub
			//HttpSession session = request.getSession(false);
			Object id = session.getAttribute("benutzerId");
			session.setAttribute("benutzerId", id);
			request.getRequestDispatcher("/view_search.ftl").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get all required parameters to use the for find result
		String start = request.getParameter("start");
		String ziel = request.getParameter("ziel");
		String dates = request.getParameter("date");

		// do a simple check for inputs
		if(start.isEmpty() || ziel.isEmpty() || dates.isEmpty()) {
			request.setAttribute("simpleError", "fill all the inputs");
			request.getRequestDispatcher("/view_search.ftl").forward(request, response);
		}else {
			try {
				// use database directly to search for values
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7");
				Statement st = con.createStatement();
				// check for inputs value to print the in frond-End page
				ResultSet rs = st.executeQuery("select * from fahrt where LOWER(startort) like LOWER('"+start+"%') and LOWER(zielort) like LOWER('"+ziel+"%')");
				// got an error if we have not these values in DB
				if(!rs.next()) {
					System.out.println("these date is not in database");
					request.setAttribute("error", "these date is not in database");
					request.getRequestDispatcher("/view_search.ftl").forward(request, response);
				}else {
					//--------------
					rs = st.executeQuery("select * from fahrt where fahrtdatumzeit between to_date('"+dates+" 00:00:00', 'yyyy-mm-dd HH24:MI:SS') and to_date('2040-06-18 00:00:00', 'yyyy-mm-dd HH24:MI:SS') and LOWER(startort) like LOWER('"+start+"%') and LOWER(zielort) like LOWER('"+ziel+"%')");
					//-------------
					while(rs.next()) {
						MainDao dao = new MainDao();
						List<Main> listUser = dao.selectAllUsers();
						request.setAttribute("listUser", listUser);
						String startort = rs.getString("startort");
						request.setAttribute("startort", startort);
						String zielort = rs.getString("zielort");
						request.setAttribute("zielort", zielort);
						String datum = rs.getString("fahrtdatumzeit");
						System.out.println(datum);
						System.out.println(startort);
						System.out.println(zielort);
						request.getRequestDispatcher("/view_search.ftl").forward(request, response);
						}
					//--------------
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		doGet(request, response);
	}

}
