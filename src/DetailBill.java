import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class DetailBill extends JFrame {
	private JPanel contentPane;
	private JTable tblData;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailBill frame = new DetailBill();
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
	public DetailBill() {
		setTitle("Detail BIll ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 88, 426, 139);
		contentPane.add(scrollPane);
		
		
		tblData = new JTable();
		tblData.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		scrollPane.setViewportView(tblData);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(101, 133, 243));
		panel_1.setBounds(0, 0, 109, 315);
		contentPane.add(panel_1);
		
		lblNewLabel_1 = new JLabel("Back");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				OffrProfile op = new OffrProfile();
				op.setVisible(true);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 11, 61, 29);
		panel_1.add(lblNewLabel_1);
		DefaultTableModel model = (DefaultTableModel) tblData.getModel();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String location = "jdbc:mysql://localhost:3306/mess";
			Connection conn = DriverManager.getConnection(location, "root", "admin123");
			
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
			JLabel lblBa = new JLabel("New label");
			lblBa.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			lblBa.setBounds(187, 34, 46, 14);
			lblBa.setText(ba);
			contentPane.add(lblBa);
			
			JLabel lblName = new JLabel("New label");
			lblName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			lblName.setBounds(187, 59, 190, 14);
			lblName.setText(name);
			contentPane.add(lblName);
			
			lblNewLabel_2 = new JLabel("BA No:");
			lblNewLabel_2.setBounds(146, 35, 46, 14);
			contentPane.add(lblNewLabel_2);
			
			lblNewLabel_3 = new JLabel("Name:");
			lblNewLabel_3.setBounds(146, 60, 46, 14);
			contentPane.add(lblNewLabel_3);
			
			
			String dbName = "bill"+ba;
			Statement st = conn.createStatement();
			String query = "select * from "+dbName;
			
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			
			int cols = rsmd.getColumnCount()-1;
			
			String[] colName = new String[cols];
			for (int i=0; i<cols; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String Dates,bfast,teabrk,lunch,dinner,total;
			while(rs.next()) {
				Dates = rs.getString(1);
				bfast = rs.getString(2);
				teabrk = rs.getString(3);
				lunch = rs.getString(4);
				dinner = rs.getString(5);
				total = rs.getString(6);
				
				String[] row = {Dates,bfast,teabrk,lunch,dinner,total};
				model.addRow(row);
				
			}
			st.close();
			conn.close();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
