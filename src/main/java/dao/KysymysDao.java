package dao;

import java.sql.*;
import java.util.*;

import data.*;

public class KysymysDao {
    private Connection conn;
    public KysymysDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikonedb", "trungta", "koodausx");
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public int lisaaKysymys(Kysymys kysymys) {
        int count=0;
        String sql="insert into statements(kysymys) values(?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kysymys.getKysymys());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return count;
    }
    public ArrayList<Kysymys> readAllKysymys() {
        ArrayList<Kysymys> list=new ArrayList<>();
        Statement stmt=null;
        try {
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from statements");
            while (rs.next()) {
                Kysymys kysymys=new Kysymys();
                kysymys.setId(rs.getInt("id"));
                kysymys.setKysymys(rs.getString("kysymys"));
                list.add(kysymys);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}