import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MealOut extends JFrame {
	String ba,name,bill;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MealOut frame = new MealOut();
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
	public MealOut() {
		setTitle("Meal Out Register");
		
		try {
			File myObj = new File("temp.txt");
			  Scanner myReader = new Scanner(myObj);
			   ba = myReader.nextLine();
			   name = myReader.nextLine();
			   bill = myReader.nextLine();
			   
			   //System.out.println(ba);
			  myReader.close();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxBfast = new JCheckBox("Break Fast");
		chckbxBfast.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		chckbxBfast.setBounds(161, 163, 97, 23);
		contentPane.add(chckbxBfast);
		
		JCheckBox chckbxTea = new JCheckBox("Tea Break");
		chckbxTea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		chckbxTea.setBounds(260, 163, 97, 23);
		contentPane.add(chckbxTea);
		
		JCheckBox chckbxLunch = new JCheckBox("Lunch");
		chckbxLunch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		chckbxLunch.setBounds(359, 163, 74, 23);
		contentPane.add(chckbxLunch);
		
		JCheckBox chckbxDinner = new JCheckBox("Dinner");
		chckbxDinner.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		chckbxDinner.setBounds(435, 163, 97, 23);
		contentPane.add(chckbxDinner);
		
		
		JLabel lblDate = new JLabel("New label");
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblDate.setBounds(385, 11, 125, 14);
		contentPane.add(lblDate);
		
		Date date = Calendar.getInstance().getTime(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
		String strDate = formatter.format(date);  
		lblDate.setText(strDate);
		Calendar now = Calendar.getInstance();
		int today = (now.get(Calendar.DATE));
		int tomonth = (now.get(Calendar.MONTH))+1;
		int toyear = (now.get(Calendar.YEAR));
		
		
		
		String day[] = new String[32]; 
		for (int i=0; i<=31-today; i++ ) {
			if ((i+today+1)!=32 ) {
				day[i] = ""+(i+today+1);
			}
			
		}
		
		
		JComboBox comboBoxDay = new JComboBox(day);
		comboBoxDay.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBoxDay.setToolTipText("Day");
		comboBoxDay.setBounds(161, 113, 77, 22);
		contentPane.add(comboBoxDay);
		
		String month[] = new String[12]; 
		for (int i=0; i<=12-tomonth; i++ ) {
			month[i] = ""+(i+tomonth);
		}
		JComboBox comboBoxMonth = new JComboBox(month);
		comboBoxMonth.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBoxMonth.setBounds(248, 113, 74, 22);
		contentPane.add(comboBoxMonth);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bfast, tea, lunch, dinner;
				String day,month;
				
				
				
				day = ""+comboBoxDay.getItemAt(comboBoxDay.getSelectedIndex());
				month = ""+comboBoxMonth.getItemAt(comboBoxMonth.getSelectedIndex());
				String date = day+"-"+month+"-"+toyear;
				if (chckbxBfast.isSelected()) {
					bfast = 0;
				}else {
					bfast = 1;
				}
				
				if (chckbxTea.isSelected()) {
					tea = 0;
				}else {
					tea = 1;
				}
				
				if (chckbxLunch.isSelected()) {
					lunch = 0;
				}else {
					lunch = 1;
				}
				
				if (chckbxDinner.isSelected()) {
					dinner = 0;
				}else {
					dinner = 1;
				}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");

					String strtbl = "ba"+ba;

					
					String query = "insert into "+strtbl+" values (?,?,?,?,?,?)";
					
				
					PreparedStatement ps = conn.prepareStatement(query);
					
					
					Double totals = 0.00;
					ps.setString(1, date);
					ps.setDouble(2, bfast);
					ps.setDouble(3,lunch);
					ps.setDouble(4, tea);
					ps.setDouble(5, dinner);
					ps.setDouble(6, totals);
					
					int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirmation", JOptionPane.YES_NO_OPTION);

					// Check the user's response
					if (result == JOptionPane.YES_OPTION) {
						int i = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Your meal is out as per your selection! ");
					} else {
					  
					}
					
					
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnConfirm.setBounds(268, 223, 89, 23);
		contentPane.add(btnConfirm);
		
		
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel.setBounds(344, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblBa = new JLabel("New label");
		lblBa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblBa.setBounds(413, 36, 67, 14);
		lblBa.setText(ba);
		contentPane.add(lblBa);
		
		JLabel lblName = new JLabel("New label");
		lblName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblName.setBounds(413, 63, 119, 14);
		lblName.setText(name);
		contentPane.add(lblName);
		
		JLabel lblNewLabel_1 = new JLabel("Day");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(161, 88, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Month");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(250, 88, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 113, 275);
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
		lblNewLabel_3.setBounds(24, 23, 61, 29);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("BA No: ");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(344, 36, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Name:");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(344, 64, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		
	}
}
