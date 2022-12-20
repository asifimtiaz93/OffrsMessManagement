import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BillPayment extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBa;
	int ba;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillPayment frame = new BillPayment();
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
	public BillPayment() {
		setTitle("Bill Payment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(101, 133, 243));
		panel_1.setBounds(0, 0, 109, 315);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Back");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminPanel op = new AdminPanel();
				op.setVisible(true);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 11, 61, 29);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("");
		lblName.setBounds(200, 105, 115, 14);
		contentPane.add(lblName);
		
		JLabel lblBill = new JLabel("");
		lblBill.setBounds(200, 130, 115, 14);
		contentPane.add(lblBill);
		
		JLabel lblNewLabel = new JLabel("BA No:");
		lblNewLabel.setBounds(119, 56, 46, 14);
		contentPane.add(lblNewLabel);
		
		textFieldBa = new JTextField();
		textFieldBa.setBounds(168, 53, 86, 20);
		contentPane.add(textFieldBa);
		textFieldBa.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textFieldBa.getText().trim().isEmpty()) {
					    throw new EmptyInputException("Empty Input not valid!");
					  }
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					ba = Integer.parseInt(textFieldBa.getText());
					
					
					String query = "select * from offrsinfo where ba=?";
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1, ba);
					
					
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						ba = rs.getInt(1);
						String strBa = ""+ba; 
						String fullname = rs.getString(2);
						double totalbill = rs.getDouble(4);
						String strtotalbill = ""+totalbill;
						
						lblName.setText(fullname);
						lblBill.setText(strtotalbill);
						
						if (lblName.getText().length() <2) {
						    throw new EmptyInputException("No User Found");
						  }
					}
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(btnNewButton, "Invalid Input! ");
					//e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(287, 52, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(119, 105, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total Arrear: ");
		lblNewLabel_3.setBounds(119, 130, 89, 14);
		contentPane.add(lblNewLabel_3);
		
		
		
		JButton btnNewButton_1 = new JButton("Paid");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					int ba = Integer.parseInt(textFieldBa.getText());
					double zero = 0.0;
					
					String query = "UPDATE offrsinfo SET totalbill =? WHERE ba = ?";
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setDouble(1, zero);
					ps.setInt(2, ba);
					
					int result = JOptionPane.showConfirmDialog(null, "Are you sure payment is done?", "Confirmation", JOptionPane.YES_NO_OPTION);

					// Check the user's response
					if (result == JOptionPane.YES_OPTION) {
						int done = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "payment Succsessfull");
					} else {
					  
					}
					
					
					
					lblBill.setText("0");
					
				}catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(200, 181, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
