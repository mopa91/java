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
import datatypes.Movie;

/**
 * Servlet implementation class RatingServlet
 */
//@WebServlet("/rate")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// receive title and userName to check the in our DB(we receive these information from movieList.ftl)
		String title = request.getParameter("title");
		String username = request.getParameter("username");

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb", "root", "");
			Statement st=con.createStatement();
			// check the parameters to see that user already rated or not
			ResultSet rs=st.executeQuery("select register.id,movies.id,rating.movieId,rating.registerId from register,movies,rating where register.name='"+username+"' and movies.title = '"+title+"' and register.id = rating.registerId and movies.title = rating.title");
			int count=0;
			while(rs.next())
			{
				count++;
			}
			if(count>0)
			{
				// we got an error if count is more than 0 
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				// checkingIfMovieIsIndDB <== this method help us to check that user already rated or not
				Movie movieRate = MRAapplication.getInstance().checkingIfMovieIsIndDB(title,username);
				request.setAttribute("movieRate", movieRate);
				Movie AverageRate = MRAapplication.getInstance().doAccessOverview(title);
				request.setAttribute("AverageRate", AverageRate);
				request.setAttribute("existError","you already rated this movie");
				request.getRequestDispatcher("/templates/rating.ftl").forward(request, response);
			}else {
				// we can add our rating in DB
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				// now we use this method to have our information in user page
				Movie movieRate = MRAapplication.getInstance().checkingIfMovieIsIndDB(title,username);
				request.setAttribute("movieRate", movieRate);
				Movie AverageRate = MRAapplication.getInstance().doAccessOverview(title);
				request.setAttribute("AverageRate", AverageRate);
				request.getRequestDispatcher("/templates/rating.ftl").forward(request, response);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get whole the parameters from rating.ftl 
		int movieid = Integer.parseInt(request.getParameter("movieid"));
		int registerId = Integer.parseInt(request.getParameter("registerId"));
		String title = request.getParameter("title");
		String comment = request.getParameter("comment");
		String rate = request.getParameter("rate");
		String username = request.getParameter("username");
		int rating = Integer.parseInt(request.getParameter("rate"));
		// check if parameters are empty or not
		if(comment.isEmpty() || rate.isEmpty()) {
			// try to keep title and userName in same page
			Movie movieRate = MRAapplication.getInstance().checkingIfMovieIsIndDB(title,username);
			request.setAttribute("movieRate", movieRate);
			request.setAttribute("error", "fill all the inputs");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.getRequestDispatcher("/templates/rating.ftl").forward(request, response);
		}else {
			try {
				// send our data in DB with help of methods
				MRAapplication.getInstance().doRating(movieid,registerId,title,comment,rating);
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				request.getRequestDispatcher("/templates/movie.ftl").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
		doGet(request, response);
	}

}
