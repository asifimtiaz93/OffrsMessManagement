import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StuOffr {
	double bfast_state;
	double tea_state;
	double lunch_state;
	double dinner_state;
	String date_state;
	
	double bfast_price;
	double tea_price;
	double lunch_price;
	double dinner_price;
	
	double bfast_total;
	double tea_total;
	double lunch_total;
	double dinner_total;
	
	
	public void fetchState(String payload, String date) {
		try {
//		
//			Calendar now = Calendar.getInstance();
//			int today = (now.get(Calendar.DATE));
//			int tomonth = (now.get(Calendar.MONTH))+1;
//			int toyear = (now.get(Calendar.YEAR));
//			
//			String dt = today+"-"+tomonth+"-"+toyear;
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String location = "jdbc:mysql://localhost:3306/mess";
			Connection conn = DriverManager.getConnection(location, "root", "admin123");
			
			Statement st = conn.createStatement();
			
			String query_check = "select COUNT(*) from "+payload+" where dates=?";
			PreparedStatement check = conn.prepareStatement(query_check);
			check.setString(1, date);
			ResultSet rst = check.executeQuery();
		      //Retrieving the result
		      rst.next();
		      int count = rst.getInt(1);
		      
		    if (count == 0) {
		    	this.bfast_state = 1;
				this.lunch_state = 1;
				this.tea_state = 1;
				this.dinner_state = 1;
		    }else {
		    	String query = "select * from "+payload+" where dates=?";
				
				
				PreparedStatement ps = conn.prepareStatement(query);
				
				
				ps.setString(1, date);
				
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					this.date_state = rs.getString(1);
					this.bfast_state = rs.getDouble(2);
					this.lunch_state = rs.getDouble(3);
					this.tea_state = rs.getDouble(4);
					this.dinner_state = rs.getDouble(5);
				}
		    }
		    
		    
			
			
			
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void fetch_price(String date) {
		try {
			
//			Calendar now = Calendar.getInstance();
//			int today = (now.get(Calendar.DATE));
//			int tomonth = (now.get(Calendar.MONTH))+1;
//			int toyear = (now.get(Calendar.YEAR));
//			
//			String dt = today+"-"+tomonth+"-"+toyear;
			
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String location = "jdbc:mysql://localhost:3306/mess";
			Connection conn = DriverManager.getConnection(location, "root", "admin123");
			
			Statement st = conn.createStatement();
			
			String query = "select * from dailyprice where dates=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, date);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.bfast_price = rs.getInt(2);
				this.lunch_price = rs.getInt(3);
				this.tea_price = rs.getInt(4);
				this.dinner_price = rs.getInt(5);
			}
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void return_price() {
		
			this.bfast_total = this.bfast_price*this.bfast_state;
			this.tea_total = this.tea_price*this.tea_state;
			this.lunch_total = this.lunch_price*this.lunch_state;
			this.dinner_total = this.dinner_price*this.dinner_state;

		
	}
}
