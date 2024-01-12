package rensyuu_9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsDAO {
	public static ArrayList<Item> findByMinimumPrice(int i) {
	try {
		Class.forName("org.h2.Driver");
	}catch (ClassNotFoundException e) {
		throw new IllegalStateException("ドライバのロードに失敗しました");
	}
	
	Connection con = null;
	try {
		con = DriverManager.getConnection("jdbc:h2:~/rpgdb","sa","");
		
		
		PreparedStatement pstmt = con.prepareStatement("select * from ITEM where price >= ?");
		pstmt.setInt(1,i);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Item> items = new ArrayList<>();
		while(rs.next()) {
			Item item = new Item();
			item.setName(rs.getString("name"));
			item.setPrice(rs.getInt("price"));
			item.setWeight(rs.getInt("weight"));
			items.add(item);
		}
		rs.close();
		pstmt.close();
		return items;
		
		
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
		if(con != null) {
			try {
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	}
}

