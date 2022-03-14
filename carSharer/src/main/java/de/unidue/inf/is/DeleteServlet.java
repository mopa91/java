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

import de.unidue.inf.is.dao.ViewDriveDao;
import de.unidue.inf.is.domain.Schreiben;
import de.unidue.inf.is.domain.View_Drive;


/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// fetch fid to delete all data for created a view-page(same time delete all info in schreiben and Bewertung table)
		int fid = Integer.parseInt(request.getParameter("fid"));
		HttpSession session = request.getSession();
		int bid = Integer.parseInt(session.getAttribute("benutzerId").toString());
		// check to see user want to delete vehicle or driver (got an error if he/she is user but not driver)
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Connection con = DriverManager.getConnection("jdbc:db2://hecate.is.inf.uni-due.de:50067/sharer", "dbp067", "uquoofi7");
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery("select anbieter from fahrt where fid = '"+fid+"' and anbieter = '"+bid+"'");
			if(!rs.next()) {
					request.setAttribute("error", "you are not a driver");
					ViewDriveDao dao = new ViewDriveDao();
					// use methods to show information in page
					View_Drive viewDrive = dao.viewDrive(fid);
					List<Schreiben> schreibenDrive = dao.schreibenDrive(fid);
					request.setAttribute("viewDrive", viewDrive);
					request.setAttribute("schreibenDrive", schreibenDrive);
					request.getRequestDispatcher("/view_drive.ftl").forward(request, response);
			}else {
				ViewDriveDao dao = new ViewDriveDao();
				dao.delete(fid);
				response.sendRedirect("main");
				}
		}catch(Exception e){
			System.out.println(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
