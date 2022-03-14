package de.unidue.inf.is;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Object benutzerId = session.getAttribute("benutzerId");
		if(benutzerId == null) {
			request.getRequestDispatcher("login.ftl").forward(request, response);
		}else {
			response.sendRedirect("main");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get email as parameter for checking in db
		String email = request.getParameter("email");

		// receive an error if input is empty
		if(email.isEmpty()) {
			request.setAttribute("error","please give your email");
			request.getRequestDispatcher("/login.ftl").forward(request, response);
		}else {
			try{
				Class.forName("com.ibm.db2.jcc.DB2Driver");
				Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7");
				Statement st=con.createStatement();
				// check to see if this name is already in DB or not
				int id = 0;;
				ResultSet rs=st.executeQuery("select * from benutzer where email='"+email+"'");
				// if count stay 0 we have the name else the name is not in DB
				int count=0;
				while(rs.next())
				{
					// get bid and use that as session for insert it in tables in other pages
					id = rs.getInt("bid");
					count++;
				}
				if(count>0)
				{
					HttpSession session = request.getSession();
					session.setAttribute("benutzerId", id);
					response.sendRedirect("main");
				}else {
					// if email is not in DB give us an error
					request.setAttribute("error","your email is not in database");
					request.getRequestDispatcher("/login.ftl").forward(request, response);
				}
			}catch (Exception e) {
				System.out.println(e);
			}

		}
		doGet(request, response);
	}

}
