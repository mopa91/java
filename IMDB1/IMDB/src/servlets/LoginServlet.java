package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.String;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// use this page if user already have an account
		request.getRequestDispatcher("/templates/login.ftl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get name parameter for checking it in dB
		String name = request.getParameter("name");

		// receive an error if input is empty
		if(name.isEmpty()) {
			request.setAttribute("error","please give your name");
			request.getRequestDispatcher("/templates/login.ftl").forward(request, response);
		}else {
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
				Statement st=con.createStatement();
				// check to see if this name is already in DB or not
				ResultSet rs=st.executeQuery("select * from register where name='"+name+"'");
				// if count stay 0 we have the name else the name is not in DB
				int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count>0)
				{
					// send our name as session to movie page(we need it for check our user in each page)
					HttpSession session = request.getSession();
					session.setAttribute("username", name);
					// if the name is already in DB read the movie page
					request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
				}else {
					// if the name is not in DB give an error
					request.setAttribute("error","your name is not in database");
					request.getRequestDispatcher("/templates/login.ftl").forward(request, response);
				}
			}catch (Exception e) {
				System.out.println(e);
			}

		}
		doGet(request, response);
	}

}
