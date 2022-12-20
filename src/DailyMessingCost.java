import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.opencsv.CSVWriter;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DailyMessingCost extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBfast;
	private JTextField textFieldLunch;
	private JTextField textFieldTea;
	private JTextField textFieldDinner;
	double bfast, lunch, tea, dinner;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DailyMessingCost frame = new DailyMessingCost();
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
	public DailyMessingCost() {
		setTitle("Daily Messing Cost Update");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String year[] = {"2022", "2023", "2024"}; 
		JComboBox comboBoxYr = new JComboBox(year);
		comboBoxYr.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxYr.setBounds(388, 56, 62, 22);
		contentPane.add(comboBoxYr);
		
		String month[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}; 
		JComboBox comboBoxMonth = new JComboBox(month);
		comboBoxMonth.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxMonth.setBounds(297, 56, 62, 22);
		contentPane.add(comboBoxMonth);
		
		String day[] = new String[32]; 
		for (int i=1; i<=31; i++) {
			day[i] = ""+i;
		}
		
		JComboBox comboBoxDay = new JComboBox(day);
		comboBoxDay.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBoxDay.setBounds(213, 56, 55, 22);
		contentPane.add(comboBoxDay);
		
		textFieldBfast = new JTextField();
		textFieldBfast.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldBfast.setBounds(348, 120, 86, 20);
		contentPane.add(textFieldBfast);
		textFieldBfast.setColumns(10);
		
		textFieldLunch = new JTextField();
		textFieldLunch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldLunch.setBounds(348, 168, 86, 20);
		contentPane.add(textFieldLunch);
		textFieldLunch.setColumns(10);
		
		textFieldTea = new JTextField();
		textFieldTea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldTea.setBounds(348, 216, 86, 20);
		contentPane.add(textFieldTea);
		textFieldTea.setColumns(10);
		
		textFieldDinner = new JTextField();
		textFieldDinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldDinner.setBounds(348, 262, 86, 20);
		contentPane.add(textFieldDinner);
		textFieldDinner.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Break Fast");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(222, 123, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lunch");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(222, 171, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tea Break");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(222, 219, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Dinner");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(222, 265, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Total");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(222, 317, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTotal.setBounds(348, 309, 86, 22);
		contentPane.add(lblTotal);
		
		
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Double.parseDouble(textFieldBfast.getText())<0) {
					    throw new EmptyInputException("Price Can't be less than 0");
					  }
					if (Double.parseDouble(textFieldLunch.getText())<0) {
					    throw new EmptyInputException("Price Can't be less than 0");
					  }
					if (Double.parseDouble(textFieldTea.getText())<0) {
					    throw new EmptyInputException("Price Can't be less than 0");
					  }
					if (Double.parseDouble(textFieldDinner.getText())<0) {
					    throw new EmptyInputException("Price Can't be less than 0");
					  }
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
					
					String day = ""+comboBoxDay.getItemAt(comboBoxDay.getSelectedIndex());
					String month = ""+comboBoxMonth.getItemAt(comboBoxMonth.getSelectedIndex());
					String year = ""+comboBoxYr.getItemAt(comboBoxYr.getSelectedIndex());
					
					String date = day+"-"+month+"-"+year;
					
					//System.out.println(date);
								
					
					
					bfast = Double.parseDouble(textFieldBfast.getText());
					lunch = Double.parseDouble(textFieldLunch.getText());
					tea = Double.parseDouble(textFieldTea.getText());
					dinner = Double.parseDouble(textFieldDinner.getText());
					
					double total = bfast + lunch + tea + dinner;
					String totals = ""+total;
					lblTotal.setText(totals);
					String query = "insert into dailyprice values (?,?,?,?,?,?)";
					
					
					PreparedStatement ps = conn.prepareStatement(query);
					
					ps.setString(1, date);
					ps.setDouble(2, bfast);
					ps.setDouble(3, lunch);
					ps.setDouble(4, tea);
					ps.setDouble(5, dinner);
					ps.setDouble(6, total);
					
					int result = JOptionPane.showConfirmDialog(null, "Are you sure about the prices?", "Confirmation", JOptionPane.YES_NO_OPTION);

					// Check the user's response
					if (result == JOptionPane.YES_OPTION) {
						int i = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Daily Messing Cost Added");
					} else {
					  
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(211, 361, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnCalc = new JButton("Generate Bill");
		btnCalc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
					String day = ""+comboBoxDay.getItemAt(comboBoxDay.getSelectedIndex());
					String month = ""+comboBoxMonth.getItemAt(comboBoxMonth.getSelectedIndex());
					String year = ""+comboBoxYr.getItemAt(comboBoxYr.getSelectedIndex());
					
					String date = day+"-"+month+"-"+year;
					
//					Calendar now = Calendar.getInstance();
//        			int today = (now.get(Calendar.DATE));
//        			int tomonth = (now.get(Calendar.MONTH))+1;
//        			int toyear = (now.get(Calendar.YEAR));
//        			
//        			String dt = today+"-"+tomonth+"-"+toyear;
        			
					int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to perform this action?", "Confirmation", JOptionPane.YES_NO_OPTION);

					// Check the user's response
					if (result == JOptionPane.YES_OPTION) {
						
						JOptionPane.showMessageDialog(null, "Bill generated for all users succesfully");
					} else {
					  
					}
					Class.forName("com.mysql.cj.jdbc.Driver");
					String location = "jdbc:mysql://localhost:3306/mess";
					Connection conn = DriverManager.getConnection(location, "root", "admin123");
		            // Get a list of table names from the database
		            DatabaseMetaData meta = conn.getMetaData();
		            ResultSet rs = meta.getTables(null, null, "%", null);

		            // Iterate over the table names and print them to the console
		            while (rs.next())
		            {
		                String tableName = rs.getString(3);
		                if (tableName.charAt(0) == 'b' && tableName.charAt(1)  == 'a') {
		                	//StuOffr st = new StuOffr();
		                	//System.out.println(tableName);
		                	
		                	Meals ml = new Meals();
		                	
		                	ml.fetchState(tableName,date);
		                		                	
		                	
		                	//System.out.println(st.tea_state);
		                	ml.fetch_price(date);
		                	ml.return_price();
		                	
		                	try {
		                		String ba_No = tableName.substring(2);
		                		String billtable = "bill"+ba_No;
		                		
		            			double totalDay = ml.bfast_total+ml.lunch_total+ml.tea_total+ml.dinner_total;
		            			
								String query = "insert into "+billtable+" values (?,?,?,?,?,?)";
								
								PreparedStatement ps = conn.prepareStatement(query);
								ps.setString(1, date);
								ps.setDouble(2, ml.bfast_total);
								ps.setDouble(3, ml.lunch_total);
								ps.setDouble(4, ml.tea_total);
								ps.setDouble(5, ml.dinner_total);
								ps.setDouble(6, totalDay);
								
								
								
								int done = ps.executeUpdate();
								
								
								double totalbill=0;
								 
								//fetch total bill
								
								try {

								String query1 = "select * from offrsinfo where ba=?";
								
								PreparedStatement ps1 = conn.prepareStatement(query1);
								
								
								ps1.setString(1, ba_No);
								
								ResultSet rs1 = ps1.executeQuery();
								if (rs1.next()) {
									totalbill = rs1.getDouble(4);
									
								}
								
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								//Calculate cumulative total
								
								totalbill = totalbill+totalDay;
								
								//updating total bill in offrsinfo table
								String query2 = "UPDATE offrsinfo SET totalbill =? WHERE ba = ?";
								PreparedStatement ps2 = conn.prepareStatement(query2);
								ps2.setDouble(1, totalbill);
								ps2.setString(2, ba_No);
								
								int in = ps2.executeUpdate();
								
								
								
							
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                	
//		                	System.out.println(st.bfast_total);
//		                	System.out.println(st.tea_total);
//		                	System.out.println(st.lunch_total);
//		                	System.out.println(st.dinner_total);
//		                	System.out.println("\n");
		                }
		                 
		            }
		        }
		        catch (Exception e1)
		        {
		            e1.printStackTrace();
		        }
			}
		});
		btnCalc.setBounds(370, 361, 126, 23);
		contentPane.add(btnCalc);
		
		JLabel lblNewLabel_6 = new JLabel("Day");
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(222, 31, 46, 22);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Month");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(302, 31, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Year");
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(388, 31, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(101, 133, 243));
		panel_1.setBounds(0, 0, 109, 411);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Back");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				AdminPanel op = new AdminPanel();
				op.setVisible(true);
			}
		});
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel_1_1.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(26, 11, 61, 29);
		panel_1.add(lblNewLabel_1_1);
	}
}

class Meals extends StuOffr{
	Meals(){
		super.bfast_state=0;
		super.tea_state=0;
		super.lunch_state=0;
		super.dinner_state=0;
		
		
		super.bfast_price=0;
		super.tea_price=0;
		super.lunch_price=0;
		super.dinner_price=0;
		
		super.bfast_total=0;
		super.tea_total=0;
		super.lunch_total=0;
		super.dinner_total=0;
	}
}
