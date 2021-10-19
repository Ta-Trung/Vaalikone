package app;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;

import java.io.PrintWriter;  

@WebServlet(
	    name = "VahvistaTunnus",
	    urlPatterns = {"vahvistatunnus"}
	)
  
public class VahvistaTunnus extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("yllapitajannimi");  
    String p=request.getParameter("salasana");  
          
    if(LoginDao.validate(n, p)){  
        RequestDispatcher rd=request.getRequestDispatcher("naytakysymykset");  
        rd.forward(request,response);  
    }  
    else{  
        out.print("Väärät tunnukset");  
        RequestDispatcher rd=request.getRequestDispatcher("/jsp/login.jsp");  
        rd.include(request,response);  
    }  
          
    out.close();  
    }  
}  