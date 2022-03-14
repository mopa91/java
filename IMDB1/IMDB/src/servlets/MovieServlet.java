package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.MRAapplication;

/**
 * Servlet implementation class webAppUserServlet
 */

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// receive session form our doPost method,that sended by user for check it in each page
		HttpSession session = request.getSession(false);
		Object name = session.getAttribute("username");
		// session.setAttribute our username send it in other pages 
		session.setAttribute("username", name);
		// setAttribute our username to send it in our movie.ftl page 
		request.setAttribute("username",name);
		request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get whole user information to doing registration
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String age = request.getParameter("age");
		int checkAge = Integer.parseInt(age);
		// check that our date are empty and get an error if there are
		if(name.isEmpty() || email.isEmpty() || age.isEmpty()) {
			request.setAttribute("error","fill all the inputs");
			request.getRequestDispatcher("/templates/register.ftl").forward(request, response);
			// check that our user is under 18 or not
		}else if(checkAge <= 18){
			request.setAttribute("error","age should not be under 18");
			request.getRequestDispatcher("/templates/register.ftl").forward(request, response);
		}
		else {
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
				Statement st=con.createStatement();
				// check to see if this name is already in DB or not
				ResultSet rs=st.executeQuery("select * from register where name='"+name+"'");
				// if count is 1 we have the name else we do not that name in DB
				int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count>0)
				{
					// get an error if this name is in DB
					request.setAttribute("error","The name is already in database.");
					request.getRequestDispatcher("/templates/register.ftl").forward(request, response);
				}else {
					// go to movie.ftl page if registration is successful
					MRAapplication.getInstance().doRegistration(name,email,age);
					HttpSession session = request.getSession();
					session.setAttribute("username", name);
					request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
				}
			}catch (Exception e) {
				System.out.println(e);
			}

		doGet(request, response);
	}
	}

}
