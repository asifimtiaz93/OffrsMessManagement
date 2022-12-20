import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class OffrProfile extends JFrame {
	
	private JTable tblData;
	private JScrollPane scrollPane;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OffrProfile frame = new OffrProfile();
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
	public OffrProfile() {
		setTitle("Officers Messing Profile");
		
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
			System.out.println("Sorry! Login First!");
			//e2.printStackTrace();
		}
	    
		JLabel lblBa = new JLabel("New label");
		lblBa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblBa.setBounds(653, 60, 46, 14);
		lblBa.setText(ba);
		contentPane.add(lblBa);
		
		JLabel lblName = new JLabel("New label");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblName.setBounds(653, 85, 127, 14);
		lblName.setText(name);
		contentPane.add(lblName);
		
		JLabel lblBill = new JLabel("New label");
		lblBill.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblBill.setBounds(249, 117, 181, 14);
		lblBill.setText(bill);
		contentPane.add(lblBill);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(177, 206, 603, 133);
		contentPane.add(scrollPane);
		
		
		tblData = new JTable();
		tblData.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		scrollPane.setViewportView(tblData);
		
		JButton btnComplain = new JButton("File Complain");
		btnComplain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FileComplain fc = new FileComplain();
				fc.setVisible(true);
			}
		});
		btnComplain.setBounds(460, 395, 143, 23);
		contentPane.add(btnComplain);
		
		JButton btnMessCost = new JButton("Daily Messing Cost");
		btnMessCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DailyPrice dp = new DailyPrice();
				dp.setVisible(true);
			}
		});
		btnMessCost.setBounds(157, 395, 143, 23);
		contentPane.add(btnMessCost);
		
		JButton btnNewButton = new JButton("Meal Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MealOut meal = new MealOut();
				meal.setVisible(true);
			}
		});
		btnNewButton.setBounds(310, 395, 143, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("BA: ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(613, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(613, 85, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total Bill:");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(177, 117, 162, 14);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 113, 450);
		contentPane.add(panel);
		
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
		
		JButton btnNewButton_1 = new JButton("Log Out");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					File myObj = new File("temp.txt");
					 myObj.delete();
					 dispose();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(10, 72, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("DASHBOARD");
		lblNewLabel_4.setForeground(SystemColor.textHighlight);
		lblNewLabel_4.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblNewLabel_4.setBounds(177, 29, 372, 55);
		contentPane.add(lblNewLabel_4);
		
		JButton btnDetailBill = new JButton("Detail Bill");
		btnDetailBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DetailBill db = new DetailBill();
				db.setVisible(true);
			}
		});
		btnDetailBill.setBounds(613, 395, 143, 23);
		contentPane.add(btnDetailBill);
		
		JLabel lblNewLabel_5 = new JLabel("Weekly Menu");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(177, 172, 150, 23);
		contentPane.add(lblNewLabel_5);
		DefaultTableModel model = (DefaultTableModel) tblData.getModel();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String location = "jdbc:mysql://localhost:3306/mess";
			Connection conn = DriverManager.getConnection(location, "root", "admin123");
			
			Statement st = conn.createStatement();
			String query = "select * from menu";
			
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			
			int cols = rsmd.getColumnCount()-1;
			
			String[] colName = new String[cols];
			for (int i=0; i<cols; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String day,bfast,teabrk,lunch,dinner;
			while(rs.next()) {
				day = rs.getString(1);
				bfast = rs.getString(2);
				teabrk = rs.getString(3);
				lunch = rs.getString(4);
				dinner = rs.getString(5);
				
				String[] row = {day,bfast,teabrk,lunch,dinner};
				model.addRow(row);
				
			}
			st.close();
			conn.close();
//			if (rs.next()) {
//				dispose();
//				AdminPanel admin = new AdminPanel();
//				admin.setVisible(true);
//				JOptionPane.showMessageDialog(btnNewButton, "You have succesfully Logged in! ");
//			}else {
//				JOptionPane.showMessageDialog(btnNewButton, "Fuckin Loser ! ");
//			}
			
			
//			String query = "insert into registration values (?,?,?)";
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, textUser.getText());
//			ps.setString(2, textEmail.getText());
//			ps.setString(3, textPasswd.getText());
//			
//			int i = ps.executeUpdate();
//			JOptionPane.showMessageDialog(btnSubmit, i+" Done");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
