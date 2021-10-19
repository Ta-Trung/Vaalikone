package app;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KysymysDao;
import data.Kysymys;

@WebServlet(
    name = "LisaaKysymys",
    urlPatterns = {"/html/lisaakysymys"}
)
public class LisaaKysymys extends HttpServlet {
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
      public void doPost(HttpServletRequest request, HttpServletResponse response)
              throws IOException, ServletException {

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
           
            PrintWriter out=response.getWriter();
           
            Kysymys kysymys=this.createKysymys(request);
            this.lisaaKysymys(kysymys);
            /*
             * With a RequestDispatcher object is the htmlstart.html file included to this servlet
             */
            RequestDispatcher rd=request.getRequestDispatcher("htmlstart.html");
            rd.include(request,  response);
            
            out.println("<html><body><b>Kysymys lisätty tietokantaan"
                        + "</b></body></html><br>");
            
            out.println("<a href='./kysymyslomake.html'>Palaa lomakkeeseen</a>");
           
            /*
             * With a RequestDispatcher object is the htmlend.html file included to this servlet
             */
            rd=request.getRequestDispatcher("htmlend.html");
                rd.include(request,  response);;
               
        }
           
      /**
       * Method reads request parameters and creates a new Kysymys object and returns it.
       * @param request Data coming from an html form
       * @return New Kysymys object generated according to the data of request
       */
        private Kysymys createKysymys(HttpServletRequest request) {
            /*
             * Reading the data from the html form - everything comes as a String
             */
            String kysymys=request.getParameter("kysymys");

           
            Kysymys k=new Kysymys(kysymys, kysymys);
            return k;
        }
        /**
         * The method saves the Kysymys object
         * @param v Kysymys to be saved
         */
        private void lisaaKysymys(Kysymys k) {
            KysymysDao dao=new KysymysDao();
            dao.lisaaKysymys(k);
            dao.close();
        }
        
}