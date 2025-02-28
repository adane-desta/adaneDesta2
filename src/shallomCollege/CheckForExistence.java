package shallomCollege;

	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	
public class CheckForExistence {
	
	

//		public static boolean value = false;
		
		public static boolean checkUsername(String uname) throws SQLException {
			
			PreparedStatement mystmt = ConnectToDataBase.getConnected().prepareStatement("select * from student "
					+ "where username = ? ");
			mystmt.setString(1, uname);
			
			ResultSet rst = mystmt.executeQuery();
			 
			if(!rst.next()) {
				
				return  false;
			}
			else {
				return true;
			}
			
			
			
		}
public static boolean checkEmail(String email) throws SQLException {
			
			PreparedStatement mystmt = ConnectToDataBase.getConnected().prepareStatement("select * from student "
					+ "where email = ? ");
			mystmt.setString(1, email);
			
			ResultSet rst = mystmt.executeQuery();
			 
			if(rst.next()) {
				
				return  false;//that means the Email Address is already exist and you can not use it
			}
			else {
				return true;
			}
			
			
			
		}
public static boolean checkPhone(String phone) throws SQLException {
	
	PreparedStatement mystmt = ConnectToDataBase.getConnected().prepareStatement("select * from student "
			+ "where phone = ? ");
	mystmt.setString(1, phone);
	
	ResultSet rst = mystmt.executeQuery();
	 
	if(rst.next()) {
		
		return  false;//that means the phone number is already exist and you can not use it
	}
	else {
		return true;
	}
	
	
	
}

	

}
