package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "LisaaTiedosto",
    urlPatterns = {"/html/lisaatiedosto"}
)
public class LisaaTiedosto extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
	  doGet(request, response);
  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
			
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikonedb", "trungta", "koodausx");
			

			String question_1=request.getParameter("question_1");
			String question_2=request.getParameter("question_2");
			String question_3=request.getParameter("question_3");
			String question_4=request.getParameter("question_4");
			String question_5=request.getParameter("question_5");
			
			Statement stmt=conn.createStatement();

			tallennauser_answers(conn, stmt, question_1, question_2, question_3, question_4, question_5);
			

			ResultSet rs=stmt.executeQuery("select * from user_answers");
			out.println("<ol>");

			while (rs.next()){
				out.println("<li>"+rs.getString("question_1")+" ("+rs.getString("question_2")+" ("+rs.getString("question_3")+"  ("+rs.getString("question_4")+"  ("+rs.getString("question_5")+")");
			}	  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		out.println("<a href='./vaalikone.html'>Palaa lomakkeeseen</a>");

  }

	private int tallennauser_answers(Connection conn, Statement stmt, String question_1, String question_2, String question_3, String question_4, String question_5) throws SQLException {
		int count=stmt.executeUpdate("insert into user_answers(st_101, st_102, st_103, st_104, st_105) values('"+question_1+"', '"+question_2+"', '"+question_3+"', '"+question_4+"', '"+question_5+"')");
		return count; //Return the number of inserted rows (here it is 1)
	}
}