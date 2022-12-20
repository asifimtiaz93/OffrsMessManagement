import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ChangePass extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePass frame = new ChangePass();
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
	public ChangePass() {
		setTitle("Set Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(111, 94, 177, 20);
		contentPane.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(111, 154, 177, 20);
		contentPane.add(passwordField2);
		
		JLabel lblWng = new JLabel("");
		lblWng.setForeground(Color.RED);
		lblWng.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		lblWng.setBounds(111, 236, 279, 25);
		contentPane.add(lblWng);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String ba = null,name = null,bill=null;
					
					
					
					try {
						File myObj = new File("temp.txt");
						  Scanner myReader = new Scanner(myObj);
						   ba = myReader.nextLine();
						   name = myReader.nextLine();
						   bill = myReader.nextLine();
						  myReader.close();
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					String pass = passwordField1.getText();
					
					if (passwordField1.getText().equals(passwordField2.getText())) {
						
						int bano = Integer.parseInt(ba);
						int flag = 0;
						String query = "UPDATE offrsinfo SET password =?, firsttime=? WHERE ba = ?";
						
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, pass);
						ps.setInt(2, flag);
						ps.setInt(3, bano);
						
						int result = JOptionPane.showConfirmDialog(null, "Are you sure you about your password?", "Confirmation", JOptionPane.YES_NO_OPTION);

						
						if (result == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, ba+"Password changed Successfully ! ");
							lblWng.setText(" ");
							int done = ps.executeUpdate();
						} else {
						  
						}
						
						
						
					}else {
						lblWng.setText("Password Mismatch! ");
					}
					
					
					
					
					
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(156, 205, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Set Password");
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(135, 11, 140, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setBounds(111, 69, 110, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setBounds(111, 129, 122, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				OffrProfile op = new OffrProfile();
				op.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(301, 205, 89, 23);
		contentPane.add(btnNewButton_1);
		

	}
}
