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
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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
			// use Maindao to print our fahrt table as a list in main.ftl
			MainDao dao = new MainDao();
			List<Main> listUser = dao.selectAllUsers();
			// make a session to send our BenutzerId to other pages
			session.setAttribute("benutzerId", benutzerId);
			// use setAttribute to appear fahrt list in Front-End page
			request.setAttribute("listUser", listUser);
			request.getRequestDispatcher("/main.ftl").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get users info to check and insert them in Benutzer table
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		// check for empty input
		if(name.isEmpty() || email.isEmpty()) {
			request.setAttribute("error","fill all the inputs");
			request.getRequestDispatcher("/register.ftl").forward(request, response);
		}else {
			try{
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7");
				Statement st=con.createStatement();
				
				ResultSet rs=st.executeQuery("select * from benutzer where email='"+email+"'");
				// use a count to figure out if we have this email or not
				int count=0;
				// if count is bigger than 0 it means we already have this bid
				while(rs.next())
				{
					count++;
				}
				if(count>0)
				{
					request.setAttribute("error","The email is already in database.");
					request.getRequestDispatcher("/register.ftl").forward(request, response);
				}else {
					// insert values in Benutzer table
					st.executeUpdate("insert into benutzer (name,email) values ('"+name+"','"+email+"')");
					int id=0;
					// find the last user bid, who regesterd and use bid as sessoin in other pages 
					ResultSet rs2= st.executeQuery("select * from benutzer ORDER BY bid DESC");
					if(rs2.next()){	
						id = rs2.getInt("bid");
						}
						HttpSession session = request.getSession();
						session.setAttribute("benutzerId", id);
						response.sendRedirect("main");
				}
			}catch (Exception e) {
				System.out.println(e);
			}

		doGet(request, response);
	}
	}
	
}