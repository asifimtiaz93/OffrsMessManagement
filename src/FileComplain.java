import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FileComplain extends JFrame {
	String bano,name;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileComplain frame = new FileComplain();
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
	public FileComplain() {
		setTitle("File Complain");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		try {
			File myObj = new File("temp.txt");
			  Scanner myReader = new Scanner(myObj);
			   bano = myReader.nextLine();
			   name = myReader.nextLine();
			   
			  myReader.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		Date date = Calendar.getInstance().getTime(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		String strDate = formatter.format(date);  
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(138, 100, 403, 137);
		contentPane.add(textArea);
		
		JLabel lblNewBa = new JLabel("New label");
		lblNewBa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewBa.setBounds(203, 11, 89, 14);
		lblNewBa.setText(bano);
		contentPane.add(lblNewBa);
		
		JLabel lblNewName = new JLabel("New label");
		lblNewName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewName.setBounds(203, 36, 296, 14);
		lblNewName.setText(name);
		contentPane.add(lblNewName);
		
		JLabel lbldate = new JLabel("New label");
		lbldate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbldate.setBounds(408, 11, 133, 14);
		lbldate.setText(strDate);
		contentPane.add(lbldate);
		
		JLabel lblNewLabel = new JLabel("Complain:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(138, 71, 361, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					
					String com = textArea.getText();
					
					
					String query = "insert into complain values (?,?,?,?)";
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, bano);
					ps.setString(2, name);
					ps.setString(3, strDate);
					ps.setString(4, com);
					
					int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);

					
					if (result == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Complain endorsed Successfully ! ");
						int i = ps.executeUpdate();
						
					} else {
					  
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(285, 268, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("BA: ");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(138, 11, 146, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(138, 36, 361, 14);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 111, 321);
		contentPane.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Back");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				OffrProfile op = new OffrProfile();
				op.setVisible(true);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(26, 11, 61, 29);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Date:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(352, 12, 46, 14);
		contentPane.add(lblNewLabel_4);
	}
}
