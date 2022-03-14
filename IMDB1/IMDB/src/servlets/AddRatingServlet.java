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

import dbadapter.DBFacade;

/**
 * Servlet implementation class AddRatingServlet
 */
public class AddRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddRatingServlet() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// get whole parameter for adding new movie in DB
	String name = request.getParameter("username");
	String title = request.getParameter("title");
	String director = request.getParameter("director");
	String actor = request.getParameter("actor");
	String publish = request.getParameter("date");
    // check if inputs are empty or not
	if(title.isEmpty() || director.isEmpty() || actor.isEmpty()) {
		request.setAttribute("error","fill all the inputs");
		request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
	}else {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
			Statement st=con.createStatement();
			// check to see if these information for new movie is already in DB or not
			ResultSet rs=st.executeQuery("select * from movies where title ='"+title+"' and director='"+director+"' and actor= '"+actor+"'");
			// if count is 1 we have the name else we do not that name in DB
			int count=0;
			while(rs.next())
			{
				count++;
			}
			if(count>0)
			{
				// receive session,that belong to our user
				HttpSession session = request.getSession();
				session.setAttribute("username", name);
				// get an error if this name is in DB
				request.setAttribute("error","these data is already in database");
				request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
			}else {
					// stay in to movie.ftl page if registration is successful and add our successful message
					request.setAttribute("successful","you add this movie successfully to database");
					// receive session,that belong to our user and set our title for movie name in movie.ftl
					request.setAttribute("title",title);
					HttpSession session = request.getSession();
					session.setAttribute("username", name);
					// add these info to our method in DBFacade and DB
					DBFacade.getInstance().addingMovieToDB(title,director,actor,publish);
					request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
			}
		}catch (Exception e) {
		System.out.println(e);
		}
	}
	doGet(request, response);
}
}
