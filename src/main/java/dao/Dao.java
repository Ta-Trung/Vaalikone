package dao;

import java.sql.DriverManager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Kysymys;

import java.sql.Connection;

public class Dao {
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	
	public Dao(String url, String user, String pass) {
		this.url=url;
		this.user=user;
		this.pass=pass;
	}
	
	public boolean getConnection() {
		try {
	        if (conn == null || conn.isClosed()) {
	            try {
	                Class.forName("com.mysql.jdbc.Driver").newInstance();
	            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
	                throw new SQLException(e);
	            }
	            conn = DriverManager.getConnection(url, user, pass);
	        }
	        return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public ArrayList<Kysymys> readAllKysymys() {
		ArrayList<Kysymys> list=new ArrayList<>();
		try {
			Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from statements");
			while (RS.next()){
				Kysymys k=new Kysymys();
				k.setId(RS.getInt("id"));
				k.setKysymys(RS.getString("kysymys"));
				k.setNumber(RS.getString("number"));
				list.add(k);
			}
			return list;
		}
		catch(SQLException e) {
			return null;
		}
	}
	public ArrayList<Kysymys> paivitaKysymys(Kysymys k) {
		try {
			String sql="update statements set kysymys=? where id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, k.getKysymys());
			pstmt.setInt(2, k.getId());
			pstmt.executeUpdate();
			return readAllKysymys();
		}
		catch(SQLException e) {
			return null;
		}
	}
	public ArrayList<Kysymys> poistaKysymys(String id) {
		try {
			String sql="delete from statements where id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return readAllKysymys();
		}
		catch(SQLException e) {
			return null;
		}
	}

	public Kysymys lueKysymys(String id) {
		Kysymys k=null;
		try {
			String sql="select * from statements where id=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet RS=pstmt.executeQuery();
			while (RS.next()){
				k=new Kysymys();
				k.setId(RS.getInt("id"));
				k.setKysymys(RS.getString("kysymys"));
			}
			return k;
		}
		catch(SQLException e) {
			return null;
		}
	}
}
