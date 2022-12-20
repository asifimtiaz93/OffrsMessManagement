import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsr;
	private JTextField textPasswd;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("Officers' Mess Mangement System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 113, 275);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Back");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Home op = new Home();
				op.setVisible(true);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(24, 23, 61, 29);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("Admin Log In");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblNewLabel.setBounds(177, 36, 198, 51);
		contentPane.add(lblNewLabel);
		
		textUsr = new JTextField();
		textUsr.setBounds(177, 136, 198, 20);
		contentPane.add(textUsr);
		textUsr.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 188, 198, 20);
		contentPane.add(passwordField);
		
//		textPasswd = new JTextField();
//		textPasswd.setBounds(177, 188, 198, 20);
//		contentPane.add(textPasswd);
//		textPasswd.setColumns(10);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textUsr.getText().trim().isEmpty()) {
					    throw new EmptyInputException("Input cannot be empty");
					  }
					if (passwordField.getText().trim().isEmpty()) {
					    throw new EmptyInputException("Input cannot be empty");
					  }
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					String user = textUsr.getText();
					String pass = passwordField.getText();
					
					String query = "select name,password from admin where name=? and password=?";
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, user);
					ps.setString(2, pass);
					
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						dispose();
						AdminPanel admin = new AdminPanel();
						admin.setVisible(true);
						JOptionPane.showMessageDialog(btnNewButton, "You have succesfully Logged in! ");
					}else {
						JOptionPane.showMessageDialog(btnNewButton, "Wrong Credentials ! ");
					}
					
					
//					String query = "insert into registration values (?,?,?)";
//					PreparedStatement ps = conn.prepareStatement(query);
//					ps.setString(1, textUser.getText());
//					ps.setString(2, textEmail.getText());
//					ps.setString(3, textPasswd.getText());
//					
//					int i = ps.executeUpdate();
//					JOptionPane.showMessageDialog(btnSubmit, i+" Done");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(btnNewButton, "Invalid Input! ");
					//e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(null);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		btnNewButton.setBounds(177, 219, 99, 27);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(177, 114, 98, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(177, 163, 74, 23);
		contentPane.add(lblNewLabel_2);
		
		
	}
}
