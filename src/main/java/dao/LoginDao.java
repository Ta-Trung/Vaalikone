package dao;

import java.sql.*;  
  
public class LoginDao {  
public static boolean validate(String nimi,String pass){  
boolean status=false;  
try{  
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikonedb", "trungta", "koodausx");  
      
PreparedStatement ps=con.prepareStatement(  
"select * from yllapitajat where nimi=? and pass=?");  
ps.setString(1,nimi);  
ps.setString(2,pass);  
      
ResultSet rs=ps.executeQuery();  
status=rs.next();  
          
}catch(Exception e){System.out.println(e);}  
return status;  
}  
}  