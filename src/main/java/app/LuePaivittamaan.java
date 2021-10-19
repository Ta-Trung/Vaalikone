package app;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Kysymys;

/**
 * Servlet implementation class ReadToUpdate
 */
@WebServlet("/luepaivittamaan")
public class LuePaivittamaan extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikonedb", "trungta", "koodausx");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LuePaivittamaan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		Kysymys k=null;
		if (dao.getConnection()) {
			k=dao.lueKysymys(id);
		}
		request.setAttribute("kysymys", k);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/muokkauslomake.jsp");
		rd.forward(request, response);
	}
}
