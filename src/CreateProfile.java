import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;

public class CreateProfile extends JFrame {

	private JPanel contentPane;
	private JTextField textBaNo;
	private JTextField textName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProfile frame = new CreateProfile();
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
	public CreateProfile() {
		setResizable(false);
		setTitle("Add/Remove Profile");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add/ Remove Profile ");
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblNewLabel.setBounds(133, 11, 141, 21);
		contentPane.add(lblNewLabel);
		
		textBaNo = new JTextField();
		textBaNo.setBounds(132, 86, 61, 20);
		contentPane.add(textBaNo);
		textBaNo.setColumns(10);
		
		textName = new JTextField();
		textName.setBounds(132, 136, 224, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("BA No");
		lblNewLabel_1.setBounds(132, 61, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(132, 117, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					int ba = Integer.parseInt(textBaNo.getText());
					String name = textName.getText();
					String passwd = "1234";
					Double bill = 0.00;
					int first = 1;
					
					String query = "insert into offrsinfo values (?,?,?,?,?)";
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1, ba);
					ps.setString(2, name);
					ps.setString(3, passwd);
					ps.setDouble(4, bill);
					ps.setInt(5, first);
//					
					int i = ps.executeUpdate();
					
					String query2 = "select count(*) from offrsinfo";
					PreparedStatement ps2 = conn.prepareStatement(query2);
					
					ResultSet rs = ps2.executeQuery();
					rs.next();
					int count = rs.getInt(1);
					String strCount = ""+count;
					
					
					File myCount = new File("Count.txt");
					FileWriter writer = new FileWriter(myCount);
					writer.write(strCount);
					writer.close();
					
					String strBa = "ba"+ba;
					String strBaBill = "bill"+ba;
					String query3 = "create table "+strBa+"(dates varchar(20), bfast double, lunch double , tea double, dinner double, total double, PRIMARY KEY (dates))";
					String query33 = "create table "+strBaBill+"(dates varchar(20), bfastBill double, lunchBill double , teaBill double, dinnerBill double, totalBill double, PRIMARY KEY (dates))";
					PreparedStatement ps3 = conn.prepareStatement(query3);
					PreparedStatement ps33 = conn.prepareStatement(query33);
					int j = ps3.executeUpdate();
					int jj = ps33.executeUpdate();
					
					
					Calendar now = Calendar.getInstance();
					int today = (now.get(Calendar.DATE));
					int tomonth = (now.get(Calendar.MONTH))+1;
					int toyear = (now.get(Calendar.YEAR));
					
					String strmonth = ""+tomonth;
					String stryr = ""+toyear;
					
//					for (int k=today; k<=31; k++) {
//						String strDate = k+"-"+strmonth+"-"+toyear;
//						//System.out.println(strDate);
//										
//						String query4 = "insert into "+strBa+" values (?,?,?,?,?,?)";
//						//System.out.println(query4);
//						
//						int flag=1;
//						double tot = 0;
//						PreparedStatement ps5 = conn.prepareStatement(query4);
//						ps5.setString(1, strDate);
//						ps5.setDouble(2, flag);
//						ps5.setDouble(3, flag);
//						ps5.setDouble(4, flag);
//						ps5.setDouble(5, flag);
//						ps5.setDouble(6, tot);
////						
//						int ne = ps5.executeUpdate();
//					}
					
					
					JOptionPane.showMessageDialog(btnAdd, "New Profile Successfully Added! ");
								
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnAdd, "Something Wrong! Try again!");
					//e1.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(129, 192, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					int ba = Integer.parseInt(textBaNo.getText());
										
					String query = "delete from offrsinfo where ba=?";
					
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1, ba);			
					int i = ps.executeUpdate();
					JOptionPane.showMessageDialog(btnAdd, ba+" Successfully Deleted! ");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(null, "Invalid Input! ");
					//e1.printStackTrace();
				}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					String bano = "ba"+textBaNo.getText();
					String bill = "bill"+textBaNo.getText();
									
					String query = "drop table "+bano;
					
					PreparedStatement ps = conn.prepareStatement(query);
								
					int i = ps.executeUpdate();
					
					String query2 = "drop table "+bill;
					PreparedStatement ps2 = conn.prepareStatement(query2);
					int ok = ps2.executeUpdate();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Invalid Input! ");
					//e1.printStackTrace();
				}
			}
		});
		btnRemove.setBounds(287, 192, 89, 23);
		contentPane.add(btnRemove);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 109, 261);
		contentPane.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Back");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminPanel adminpan = new AdminPanel();
				adminpan.setVisible(true);
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel_3.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(26, 11, 61, 29);
		panel.add(lblNewLabel_3);
	}

}
