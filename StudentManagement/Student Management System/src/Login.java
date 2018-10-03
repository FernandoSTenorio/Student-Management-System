import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


public class Login extends JFrame implements ActionListener{
	
	public static Statement stmt = null;
	public static Student student;
	public static Admin admin;
	public static SystemControll controll  = new SystemControll();
	
	JTextField username = null;
	JPasswordField newpass = new JPasswordField(20);
	
	public Login(){
		
		this.setTitle("Login");
		this.setSize(350, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagConstraints c1 = new GridBagConstraints();
		
		JLabel label = new JLabel("Username: ");
		JLabel label1 = new JLabel("Password: ");       
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(label);
		username = new JTextField(20);
		panel.add(username);

		panel.add(label1);
		panel.add(newpass);
		
		JButton btn = new JButton("Close");
		panel.add(btn);
		btn.addActionListener(this);
		btn.setActionCommand("close");
		this.getContentPane().add(panel);


		JButton button2 = new JButton("Login");
		panel.add(button2, c1);
		button2.addActionListener(this);      
		button2.setActionCommand("login");
		
		JButton button3 = new JButton("Student Login");
		panel.add(button3);
		button3.addActionListener(this);
		button3.setActionCommand("studentLogin");
		
		validate();
		repaint();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Login();
	}
	
	public static Statement getStatment(){

		try 
		{
			if (stmt == null){
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Students?user=root&password=");
				stmt = conn.createStatement();
			}
		}
		catch(Exception ex ){}
		return stmt;
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("login")){
			controll.systemLogin(username.getText(), newpass.getText());
		}else if(e.getActionCommand().equals("close")){
			Component com = null;
			int n = JOptionPane.showConfirmDialog(com, "Are you sure you want to close the system?");
			if(n == 0){
				System.exit(0);
			}
		}else if(e.getActionCommand().equals("studentLogin")){
			JFrame frame = new JFrame("Student Login");
			
			frame.setSize(350, 200);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			
			JLabel label = new JLabel("Username: ");
			JLabel label1 = new JLabel("Password: ");       
			JPanel panel = new JPanel();
			frame.add(panel);
			panel.add(label);
			username = new JTextField(20);
			panel.add(username);

			panel.add(label1);
			panel.add(newpass);
			
			JButton btn = new JButton("Close");
			panel.add(btn);
			btn.addActionListener(this);
			btn.setActionCommand("close");
			
			
			JButton button2 = new JButton("Login");
			panel.add(button2);
			button2.addActionListener(this);      
			button2.setActionCommand("addStudent");
			
			validate();
			repaint();	
			
		}
		if(e.getActionCommand().equals("addStudent")){
			controll.studenLogin(username.getText(), newpass.getText());
		}
		
	}

}
