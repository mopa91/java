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

public class WebAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		Object name = session.getAttribute("username");
		Object time = session.getAttribute("time");
		session.setAttribute("username", name);
		request.setAttribute("time",time);
		request.setAttribute("username",name);
		request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String age = request.getParameter("age");
		int checkAge = Integer.parseInt(age);
		if(name.isEmpty() || email.isEmpty() || age.isEmpty()) {
			request.setAttribute("error","fill all the inputs");
			request.getRequestDispatcher("/templates/register.ftl").forward(request, response);
		}else if(checkAge <= 18){
			request.setAttribute("error","age should not be under 18");
			request.getRequestDispatcher("/templates/register.ftl").forward(request, response);
		}
		else {
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from register where name='"+name+"'");
				int count=0;
				while(rs.next())
				{
					count++;
				}
				if(count>0)
				{
					request.setAttribute("error","The name is already in database.");
					request.getRequestDispatcher("/templates/register.ftl").forward(request, response);
				}else {
					MRAapplication.getInstance().doRegistration(name,email,age);
					HttpSession session = request.getSession();
					session.setAttribute("username", name);
					String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
					session.setAttribute("time", date);
					request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
				}
			}catch (Exception e) {
				System.out.println(e);
			}

		doGet(request, response);
	}
	}

}
