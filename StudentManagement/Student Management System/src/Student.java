import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Access.Course;

public class Student extends JFrame implements ActionListener{
	
	
	JTable table1;
	JLabel id = new JLabel("ID");
	JLabel username  = new JLabel("Username");
	JLabel password = new JLabel("Password");
	JLabel course = new JLabel("Course");
	JLabel address = new JLabel("Address");
	JLabel nationality = new JLabel("Nationality");
	JLabel checkInformation = new JLabel("Type Password to check information:");

	JLabel newCourse = new JLabel("Course ");
	String[] st = {"student", "admin"};
	JComboBox<String> c = new JComboBox<>(st);

	JTextField id1 = new JTextField(20);
	JTextField user1 = new JTextField(20);
	JPasswordField pass1 = new JPasswordField(20);
	JPasswordField pass2 = new JPasswordField(20);
	JTextField course1 = new JTextField(10);
	JTextField address1 = new JTextField(20);
	JTextField nationality1 = new JTextField(20);
	JTextField userType1 = new JTextField(20);
	JTextField newCourse1 = new JTextField(20);
	
	JButton button1 = new JButton("Click to check information");
	
	public Student(){
		
		
		this.setTitle("Student");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(550, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	
		JPanel panel = new JPanel();
		this.add(panel);
		
		panel.add(checkInformation);
		panel.add(pass2);
		panel.add(button1);
		button1.addActionListener(this);
		button1.setActionCommand("checkInformation");
		
		panel.add(id);
		panel.add(id1);
		panel.add(username);
		panel.add(user1);
		panel.add(address);
		panel.add(address1);
		panel.add(nationality);
		panel.add(nationality1);
		panel.add(password);
		panel.add(pass1);
		panel.add(course);
		panel.add(course1);
		
		JButton upDate = new JButton("Update Informations");
		panel.add(upDate);
		upDate.setActionCommand("update");
		upDate.addActionListener(this);
		panel.setBorder(BorderFactory.createTitledBorder("Student Information"));
		panel.setBounds(5, 15, 275, 500);
		
		JPanel panel1 = new JPanel();
		this.add(panel1);
		panel1.setBounds(290, 15, 250, 350);
		DefaultTableModel model1 = new DefaultTableModel();
		model1.addColumn("ID");
		model1.addColumn("Name");
		table1 = new JTable(model1);
		table1.setName("Students Table");
		table1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//table1.getColumnModel().getColumn(2).setPreferredWidth(5);
		table1.setPreferredScrollableViewportSize(new Dimension(150, 150));
		JScrollPane scrollPane1 = new JScrollPane(table1);
		panel1.add(scrollPane1);
		panel1.setBorder(BorderFactory.createTitledBorder("Courses"));
		
		JPanel panel2 = new JPanel();
		this.add(panel2);
		panel2.setBounds(295, 450, 200, 300);
		JButton button3 = new JButton("Logout");
		panel2.add(button3);
		button3.addActionListener(this);
		button3.setActionCommand("logout");
		
		validate();
		repaint();
		listAllCourses();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (e.getActionCommand().equals("checkInformation")) {
			try {
				Statement st = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Students?user=root&password=");
				st = conn.prepareStatement("autoReconnect=true");
				ResultSet rs = 	st.executeQuery("SELECT * FROM Student WHERE Password ='" + pass2.getText() + "'");
				while (rs.next()) {
					id1.setText(rs.getString(1));
					user1.setText(rs.getString(2));
					address1.setText(rs.getString(3));
					nationality1.setText(rs.getString(4));
					pass1.setText(rs.getString(5));
					course1.setText(rs.getString(6));
					
					JOptionPane.showMessageDialog(null, "Welcome'"+user1.getText()+"'.", "Student System", JOptionPane.INFORMATION_MESSAGE);

				}
				st.close();
			} catch (SQLException s) {
				System.out.println("No record found!\n\n\n");
				System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
			} catch (Exception x) {
				System.out.println("Error" + x.toString() + " " + x.getMessage());
			}
		}else if(e.getActionCommand().equals("logout")){

			int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
					"Confirmation",JOptionPane.YES_NO_OPTION);
			if (a == 0){
				dispose();
			}
		}else if(e.getActionCommand().equals("update")){
			Login.controll.changeInformation(user1.getText(), id1.getText(), pass1.getText(), address1.getText(), course1.getText());
			JOptionPane.showMessageDialog(this, "Information Updated Successfully", "", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	public void listAllCourses(){
		List<Course> courses = Login.controll.getAllCourses();
		Course course;
		int i;
		
		for(i = 0; i<courses.size(); i++){
			course = courses.get(i);
			((DefaultTableModel)table1.getModel()).addRow(new Object[]{
					course.getId(), course.getName()});
		}
	}
}
