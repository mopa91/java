package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.MRAapplication;
import datatypes.Movie;


/**
 * Servlet implementation class MovieListServlet
 */
//@WebServlet("/movieList")
public class MovieListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// receive a session from user 
		HttpSession session = request.getSession(false);
		// list whole the movie that we have in DB in frontEnd page
		List<Movie> webAppUser = MRAapplication.getInstance().Movie();
		Object name = session.getAttribute("username");
		request.setAttribute("usernamer",name);
		// make setAttribute to show movieList in our movieList.ftl page
		request.setAttribute("webAppUser", webAppUser);
		request.getRequestDispatcher("/templates/movieList.ftl").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
