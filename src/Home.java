import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setTitle("Officers' Mess Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdmin = new JButton("Admin Login");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login login_obj = new login();
				login_obj.setVisible(true);				
			}
		});
		btnAdmin.setBounds(100, 122, 216, 23);
		contentPane.add(btnAdmin);
		
		JButton btnStuOffr = new JButton("Student Officer Login");
		btnStuOffr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				StudentLogin stuLog = new StudentLogin();
				stuLog.setVisible(true);		
			}
		});
		btnStuOffr.setBounds(100, 177, 216, 23);
		contentPane.add(btnStuOffr);
		
		JLabel lblNewLabel = new JLabel("Welcome To Mess Management System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 37, 414, 45);
		contentPane.add(lblNewLabel);
	}
}
