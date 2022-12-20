import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateMenu extends JFrame {

	private JPanel contentPane;
	private JTable tblData;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateMenu frame = new UpdateMenu();
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
	public UpdateMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 43, 726, 275);
		contentPane.add(scrollPane);
		
		
		tblData = new JTable();
		tblData.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
		scrollPane.setViewportView(tblData);
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
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 109, 315);
		contentPane.add(panel);
		
		lblNewLabel = new JLabel("Back");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminPanel adminpan = new AdminPanel();
				adminpan.setVisible(true);
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel.setBounds(26, 11, 61, 29);
		panel.add(lblNewLabel);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(10, 56, 89, 29);
		panel.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i< model.getRowCount(); i++) {
					String day = model.getValueAt(i, 0).toString();
					String bfast = model.getValueAt(i, 1).toString();
					String teabrk = model.getValueAt(i, 2).toString();
					String lunch = model.getValueAt(i, 3).toString();
					String dinner = model.getValueAt(i, 4).toString();
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						String location = "jdbc:mysql://localhost:3306/mess";
						Connection conn = DriverManager.getConnection(location, "root", "admin123");
							
						String query = "UPDATE menu SET day =?, bfast=?, teabrk=?, lunch=?, dinner=? WHERE id = ?";
						
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, day);
						ps.setString(2, bfast);
						ps.setString(3, teabrk);
						ps.setString(4, lunch);
						ps.setString(5, dinner);
						ps.setInt(6, i+1);
						
						int done = ps.executeUpdate();
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
				
				JOptionPane.showMessageDialog(btnUpdate, "Menu Updated Successfully! ");
			}
		});
		btnUpdate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 15));
		
	}
}
