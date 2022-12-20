import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JScrollPane;

public class ViewComplain extends JFrame {

	private JPanel contentPane;
	private JTable tblData;
	private JScrollPane scrollPane;
	private JTable tblComp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewComplain frame = new ViewComplain();
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
	public ViewComplain() {
		setTitle("Complain Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 131, 437);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Back");
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
		
		JLabel lblNewLabel_1 = new JLabel("Complains");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(141, 11, 180, 34);
		contentPane.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(141, 89, 771, 133);
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
			String query = "select * from complain";
			
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			
			int cols = rsmd.getColumnCount();
			
			String[] colName = new String[cols];
			for (int i=0; i<cols; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String ba,name,date,complain;
			while(rs.next()) {
				ba = rs.getString(1);
				name = rs.getString(2);
				date = rs.getString(3);
				complain = rs.getString(4);
				
				String[] row = {ba,name,date,complain};
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
