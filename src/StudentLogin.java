import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBa;
	private JPasswordField passwordField;
	public int ba;
	public String fullname;
	public Double totalbill;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel lblOffrsLogIn;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
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
	public StudentLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldBa = new JTextField();
		textFieldBa.setBounds(196, 119, 143, 20);
		contentPane.add(textFieldBa);
		textFieldBa.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					if (textFieldBa.getText().trim().isEmpty()) {
					    throw new EmptyInputException("Input cannot be empty");
					  }
					if (passwordField.getText().trim().isEmpty()) {
					    throw new EmptyInputException("Input cannot be empty");
					  }
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					ba = Integer.parseInt(textFieldBa.getText());
					String pass = passwordField.getText();
					
					String query = "select * from offrsinfo where ba=? and password=?";
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1, ba);
					ps.setString(2, pass);
					
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						ba = rs.getInt(1);
						String strBa = ""+ba; 
						fullname = rs.getString(2);
						totalbill = rs.getDouble(4);
						String strtotalbill = ""+totalbill;
						
						int first = rs.getInt(5);
						
						
						
						FileWriter writer = new FileWriter("temp.txt");
						writer.write(strBa+"\n");
						writer.write(fullname+"\n");
						writer.write(strtotalbill+"\n");
						writer.close();
						
						if (first==1) {
							dispose();
							ChangePass cp = new ChangePass();
							cp.setVisible(true);
						}else {
							dispose();
							OffrProfile op = new OffrProfile();
							op.setVisible(true);
						}
						
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
		btnNewButton.setBounds(218, 210, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(196, 175, 143, 20);
		contentPane.add(passwordField);
		
		lblNewLabel = new JLabel("BA No: ");
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel.setBounds(196, 94, 141, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Password: ");
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(196, 150, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 113, 275);
		contentPane.add(panel);
		
		lblNewLabel_2 = new JLabel("Back");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Home hm = new Home();
				hm.setVisible(true);
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel_2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(24, 23, 61, 29);
		panel.add(lblNewLabel_2);
		
		lblOffrsLogIn = new JLabel("Offrs' Log In");
		lblOffrsLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblOffrsLogIn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblOffrsLogIn.setBounds(160, 21, 198, 51);
		contentPane.add(lblOffrsLogIn);
	}
}

class Person {
	public String ba;
	public String date;
	public double bfast_price;
	public double tea_price;
	public double lunch_price;
	public double dinner_price;
	
	
	
	public double calPrice(double daily, int inout) {
		return daily*inout;
	}
	
}

