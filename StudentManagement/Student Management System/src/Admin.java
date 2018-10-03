import java.awt.Component;
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
import Access.User;


public class Admin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JTable table;
	JTable table1;
	JLabel id	=	new JLabel("ID");
	JLabel search = new JLabel("Search");
	JLabel username  = new JLabel("Username");
	JLabel password = new JLabel("Password");
	JLabel course = new JLabel("Type Course");
	JLabel address = new JLabel("Address");
	JLabel nationality = new JLabel("Nationality");
	JLabel userType = new JLabel("User type");
	JLabel newCourse = new JLabel("Type Course Name");
	String[] st = {"student", "admin"};
	JComboBox<String> c = new JComboBox<>(st);

	JTextField id1 = new JTextField(20);
	JTextField search1 = new JTextField(15);
	JTextField user1 = new JTextField(20);
	JPasswordField pass1 = new JPasswordField(20);
	JTextField course1 = new JTextField(15);
	JTextField address1 = new JTextField(20);
	JTextField nationality1 = new JTextField(20);
	JTextField userType1 = new JTextField(20);
	JTextField newCourse1 = new JTextField(20);
	
	JButton btRemoveCourse = new JButton("Remove Course");
	JButton upDateStudent = new JButton("Update");
	JButton sch = new JButton("Search");
	JButton add = new JButton("Add Student");
	JButton addStudent = new JButton("Click to Add Student ");
	JButton addCourse = new JButton("Add New Course!");
	JButton close = new JButton("Log out");

	public Admin(){
		this.setTitle("Admin");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(1090, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		JPanel panel = new JPanel();
		this.add(panel);
		panel.setBounds(5, 10, 640, 450);
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Address");
		model.addColumn("Nationality");
		model.addColumn("Password");
		model.addColumn("Courses");
		model.addColumn("Type");
		table = new JTable(model);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.setPreferredScrollableViewportSize(new Dimension(530, 350));
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);

		panel.add(addStudent);
		addStudent.addActionListener(this);
		addStudent.setActionCommand("add");
		JButton btRemove = new JButton("Remove");
		btRemove.addActionListener(this);
		btRemove.setActionCommand("remove");
		panel.add(btRemove);
		panel.add(search);
		panel.add(search1);
		panel.add(sch);
		sch.addActionListener(this);
		sch.setActionCommand("search");
		panel.setBorder(BorderFactory.createTitledBorder("View Students"));
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(650, 10, 400, 350);
		this.add(panel1);
		
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
		table1.setBounds(10, 630, 150, 150);
		panel1.add(scrollPane1);
		
		panel1.add(btRemoveCourse);
		btRemoveCourse.setBounds(155, 470, 200, 50);
		btRemoveCourse.addActionListener(this);
		btRemoveCourse.setActionCommand("removeCourse");
		
		
		panel1.add(newCourse);
		panel1.add(newCourse1);
		panel1.add(addCourse);
		addCourse.addActionListener(this);
		addCourse.setActionCommand("newCourse");

		panel1.setBorder(BorderFactory.createTitledBorder("View Courses"));
		
		JPanel panel2 = new JPanel();
		this.add(panel2);
		panel2.add(close);
		close.setActionCommand("close");
		close.addActionListener(this);
		panel2.setBounds(660, 370, 200, 100);
		validate();
		repaint();
		upDateStudent();
		listAllCourses();
	}
	public void upDateStudent(){
		List<User> users = Login.controll.getAllUsers();
		User user;
		int i;
		
		for(i = 0; i<users.size(); i++){
			user = users.get(i);
			((DefaultTableModel)table.getModel()).addRow(new Object[]
				{user.getId(), user.getName(), user.getAddress(),
				user.getNationality(), user.getPassword(),
				user.getCourses(), user.getType()});	
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
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add Student")){
			Login.controll.addStudent();
			JOptionPane.showMessageDialog(this, "User Regitered", "", JOptionPane.INFORMATION_MESSAGE);
			upDateStudent();
		}else if(e.getActionCommand().equals("add")){
			JFrame frame1 = new JFrame();
			frame1.setTitle("Add New Student");
			frame1.setVisible(true);
			frame1.setSize(400, 350);
			frame1.setLocationRelativeTo(null);
			frame1.setLayout(null);

			JPanel panel = new JPanel();
			frame1.add(panel);
			panel.add(username);
			username.setBounds(13, 13, 15, 10);
			panel.add(user1);
			panel.add(address);
			address.setBounds(13, 16, 15, 15);
			panel.add(address1);
			panel.add(nationality);
			panel.add(nationality1);
			panel.add(password);
			panel.add(pass1);
			panel.add(course);
			panel.add(course1);
			panel.add(userType);
			userType.setBounds(13, 20, 16, 220);
			panel.add(c);
			panel.add(add);
			add.setActionCommand("Add Student");
			add.addActionListener(this);
			
			panel.setBorder(BorderFactory.createTitledBorder("Add new Student"));
			panel.setBounds(10, 10, 380, 250);
			
			JPanel panel1 = new JPanel();
			frame1.add(panel1);
			panel1.setBounds(100, 260, 200, 100);
			JButton close = new JButton("Close");
			panel1.add(close);
			close.addActionListener(this);
			close.setActionCommand("close");

			validate();
			repaint();
		}else if(e.getActionCommand().equals("newCourse")){
			Course course = new Course();
			course.setName(new String(newCourse1.getText()));
			if(Login.controll.addCourse(course)){
				JOptionPane.showMessageDialog(this, "Ticket Added Successful", "", JOptionPane.INFORMATION_MESSAGE);
				listAllCourses();
			}
		}else if(e.getActionCommand().equals("close")){
			Component com = null;
			int n = JOptionPane.showConfirmDialog(com, "Are you sure you want to Log Out");
			if (n == 0){
				dispose();
			}
		}else if(e.getActionCommand().equals("remove")) {
			if (table.getSelectedRow() != -1) {
				Long id = (Long) ((DefaultTableModel) table.getModel()).getValueAt(table.getSelectedRow(), 0);

				int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this Student?",
						"Confirmation",JOptionPane.YES_NO_OPTION);
				if (n == 0 &&  Login.controll.removeStudent(id.toString())){
					((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
				}
			}
		}else if(e.getActionCommand().equals("removeCourse")) {
			if (table1.getSelectedRow() != -1) {
				Long id = (Long) ((DefaultTableModel) table1.getModel()).getValueAt(table1.getSelectedRow(), 0);

				int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this Course?",
						"Confirmation",JOptionPane.YES_NO_OPTION);
				if (n == 0 &&  Login.controll.removeCourse(id.toString())){
					((DefaultTableModel) table1.getModel()).removeRow(table1.getSelectedRow());

				}
			}
		}else if(e.getActionCommand().equals("search")) {
			try {
				Statement st = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Students?user=root&password=");
				st = conn.prepareStatement("autoReconnect=true");
				ResultSet rs = 	st.executeQuery("SELECT * FROM Student WHERE Name ='" +search1.getText()+ "'");
				while (rs.next()) {
					id1.setText(rs.getString(1));
					user1.setText(rs.getString(2));
					address1.setText(rs.getString(3));
					nationality1.setText(rs.getString(4));
					pass1.setText(rs.getString(5));
					course1.setText(rs.getString(6));
					
					JOptionPane.showMessageDialog(null, "Student'"+user1.getText()+"'Found. ", "Student System", JOptionPane.INFORMATION_MESSAGE);

				}
				JFrame frame = new JFrame();
				frame.setTitle("'"+search1.getText()+"'");
				frame.setVisible(true);
				frame.setSize(400, 450);
				frame.setLocationRelativeTo(null);
				frame.setLayout(null);

				JPanel panel = new JPanel();
				frame.add(panel);
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
				panel.add(upDateStudent);
				upDateStudent.setActionCommand("update");
				upDateStudent.addActionListener(this);
				panel.setBorder(BorderFactory.createTitledBorder("Student Detail"));
				panel.setBounds(10, 10, 350, 400);
				st.close();
			} catch (SQLException s) {
				System.out.println("No record found!\n\n\n");
				System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
			} catch (Exception x) {
				System.out.println("Error" + x.toString() + " " + x.getMessage());
			}
					
		}else if(e.getActionCommand().equals("update")){
			Login.controll.changeInformation(user1.getText(), id1.getText(), pass1.getText(), address1.getText(), course1.getText());
			JOptionPane.showMessageDialog(this, "Information Updated Successfully", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
