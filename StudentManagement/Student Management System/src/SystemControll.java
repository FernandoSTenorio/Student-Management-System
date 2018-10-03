import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Access.Course;
import Access.User;

public class SystemControll {

	String[] st = {"student", "admin"};
	JComboBox<String> c = new JComboBox<>(st);
	
	JTextField id = new JTextField(10);
	JTextField user1 = new JTextField(20);
	JPasswordField pass1 = new JPasswordField(20);
	JTextField course1 = new JTextField(10);
	JTextField address1 = new JTextField(20);
	JTextField nationality1 = new JTextField(20);
	JTextField userType1 = new JTextField(20);
	JTextField newCourse1 = new JTextField(20);
	

	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		User user;
		Statement stmt = Login.getStatment();
		ResultSet rs = null;

		try{
			rs = stmt.executeQuery("SELECT * FROM Student ");

			while(rs.next()){
				user = new User();
				user.setId(new Long(rs.getString("id")));
				user.setName(new String(rs.getString("Name")));
				user.setAddress(new String(rs.getString("Address")));
				user.setNationality(new String(rs.getString("Nationality")));
				user.setPassword(new String(rs.getString("Password")));
				user.setCourse(new String(rs.getString("Courses")));
				user.setType(new String(rs.getString("Type")));
				users.add(user);
			}
		}catch(SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return users;
	}
	public List<Course> getAllCourses(){
		List<Course> courses = new ArrayList<>();
		Statement stmt = Login.getStatment();
		ResultSet rs = null;
		Course course;
		
		try{
			rs = stmt.executeQuery("SELECT * FROM Course");
			while(rs.next()){
				course = new Course();
				course.setId(new Long(rs.getString("id")));
				course.setName(new String(rs.getString("Name")));
				courses.add(course);
			}
			
		}catch(SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		
		return courses;
	}
	public void addStudent(){

		Statement smt = Login.getStatment();
		ResultSet rs = null;
		try{
			String un = user1.getText();
			String address = address1.getText();
			String nationality = nationality1.getText();
			String pw = pass1.getText();
			String course = course1.getText();
			String tp = c.getSelectedItem().toString();

			if (smt.execute(" INSERT INTO Student (`Name`, `Address`, `Nationality`, `Password`,`Courses`, `Type`) VALUES ('"+un+"','"+address+"',"
					+ " '"+nationality+"', '"+pw+"',"
					+ "'"+course+"', '"+tp+"');")){
			}
		}catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());

		}
	}
	
	public boolean addCourse(Course course){
		Statement smt = Login.getStatment();
		ResultSet rs = null;

		try{
			String name = newCourse1.getText();
			if(smt.execute("INSERT INTO `Course`(`Name`) VALUES('"+course.getName()+"')")){
				
				return true;
			}

		}catch(SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return false;
	}
	
	public void systemLogin(String user,String pass){

		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		}catch(Exception f){}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn =
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/Students?user=root&password=");
	
			stmt = conn.createStatement();

			if (stmt.execute("select * from Admin where Name = '"+user+"' and Password = '"+pass+"';")) {
				rs = stmt.getResultSet();
			}
			while(rs.next()){
				String type = rs.getString("Type");
				System.out.println(type);
				if(type.equalsIgnoreCase("admin")){
					Login.admin = new Admin();
				}
			}			
		}catch(SQLException ex){
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	
	}
	public void studenLogin(String user,String pass){

		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		}catch(Exception f){}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn =
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/Students?user=root&password=");
	
			stmt = conn.createStatement();

			if (stmt.execute("SELECT * FROM Student WHERE Name = '"+user+"' and Password = '"+pass+"';")) {
				rs = stmt.getResultSet();
				}
			while(rs.next()){
				String type = rs.getString("Type");
				System.out.println(type);
				if(type.equalsIgnoreCase("student")){
					Login.student = new Student();
				}
			}
					
		}catch(SQLException ex){
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	
	}
	public boolean removeStudent(String id){

		Statement stmt = Login.getStatment();

		try{

			if(stmt.execute("DELETE FROM Student WHERE id = '"+id+"'; "));
			{
				return true;
			}
		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}

		return false;
	}
	public boolean removeCourse(String id){

		Statement stmt = Login.getStatment();

		try{

			if(stmt.execute("DELETE FROM Course WHERE id = '"+id+"'; "));
			{
				return true;
			}
		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());
		}

		return false;
	}
	public void changeInformation(String usname, String newid, String pssd, String address, String course) {
		System.out.println(usname);
		System.out.println(newid);
		System.out.println(pssd);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		}catch(Exception ex ){}
		try{
			Connection conn = null;
			Statement stmt = Login.getStatment();
			ResultSet rs = null;
			conn =
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/Students?user=root&password=");
			stmt = conn.createStatement();
			if(stmt.executeUpdate("UPDATE Student SET Password = '"+pssd+"', Name = '"+usname+"', Address = '"+address+"', Courses = '"+course+"' WHERE id = '"+newid+"'") > 0){
				rs = stmt.getResultSet();
			}else if(usname.equals(" ") && newid.equals(" ") && pssd.equals(" ") && course.equals("") && address.equals("")){

			}
		} 
		catch (SQLException es){
			System.out.println("SQLException: " + es.getMessage());
			System.out.println("SQLState: " + es.getSQLState());
			System.out.println("VendorError: " + es.getErrorCode());

		}
	}
}
