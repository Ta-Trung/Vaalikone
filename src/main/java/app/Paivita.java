package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Kysymys;

@WebServlet(
    name = "Paivita",
    urlPatterns = {"/paivita"}
)
public class Paivita extends HttpServlet {
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikonedb", "trungta", "koodausx");
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException {
		response.sendRedirect("index.html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		String id=request.getParameter("id");
		String kysymys=request.getParameter("kysymys");
		
		Kysymys k=new Kysymys(id, kysymys);
		
		ArrayList<Kysymys> list=null;
		if (dao.getConnection()) {
			list=dao.paivitaKysymys(k);
		}
		
		request.setAttribute("kysymyslista", list);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/naytakysymykset.jsp");
		rd.forward(request, response);
	}
}