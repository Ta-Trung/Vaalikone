package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Kysymys;

/**
 * Servlet implementation class ShowFish
 */
@WebServlet(
		name = "NaytaKysymykset",
		urlPatterns = {"naytakysymykset"}
		)
public class NaytaKysymykset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao=null;
	
	@Override
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikonedb", "trungta", "koodausx");
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaytaKysymykset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
    	    throws ServletException, IOException {  
    	  
    	    response.setContentType("text/html");  
    	    PrintWriter out = response.getWriter();  
    	          
    	    String n=request.getParameter("yllapitajannimi");  
    	    out.print("Tervetuloa "+n);  
    	    PrintWriter pw = response.getWriter();
    	    pw.println("<br><br><a href= '../naytakysymykset'>Siirry muokkaamaan kysymyslista</a>");  
    	    out.close();  
    	    
    		}  
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Kysymys> list=null;
		if (dao.getConnection()) {
			list=dao.readAllKysymys();
		}
		else {
			System.out.println("No connection to database");
		}
		request.setAttribute("kysymyslista", list);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/naytakysymykset.jsp");
		rd.forward(request, response);
	}
}
