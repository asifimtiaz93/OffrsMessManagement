import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class AdminPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	public AdminPanel() {
		setTitle("Admin Panel");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(101, 133, 243));
		panel.setBounds(0, 0, 131, 456);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Back");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Imtiaz\\Downloads\\back-button.png"));
		lblNewLabel.setBounds(33, 31, 72, 38);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				login loginpage = new login();
				loginpage.setVisible(true);
				
			}
		});
		
		
		JLabel lbladdpro = new JLabel("");
		lbladdpro.setBounds(220, 157, 72, 80);
		contentPane.add(lbladdpro);
		lbladdpro.setIcon(new ImageIcon(new ImageIcon("add.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		
		JLabel lblCreateProfile = new JLabel("Add/Remove Profile");
		lblCreateProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateProfile.setForeground(new Color(101, 133, 243));
		lblCreateProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				CreateProfile createprofile = new CreateProfile();
				createprofile.setVisible(true);
				//System.out.println("Hello");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCreateProfile.setForeground(new Color(0, 0, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCreateProfile.setForeground(new Color(101, 133, 243));
			}
		});
		lblCreateProfile.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblCreateProfile.setBounds(179, 219, 141, 36);
		contentPane.add(lblCreateProfile);
		
		JLabel lblUpdateMenu = new JLabel("Update Menu");
		lblUpdateMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateMenu.setForeground(new Color(101, 133, 243));
		lblUpdateMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				UpdateMenu updatemenu = new UpdateMenu();
				updatemenu.setVisible(true);
//				System.out.println("Hello");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUpdateMenu.setForeground(new Color(0, 0, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblUpdateMenu.setForeground(new Color(101, 133, 243));
			}
		});
		lblUpdateMenu.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblUpdateMenu.setBounds(350, 219, 141, 36);
		contentPane.add(lblUpdateMenu);
		
		JLabel lblViewComplain = new JLabel("View Complain");
		lblViewComplain.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewComplain.setForeground(new Color(101, 133, 243));
		lblViewComplain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				ViewComplain viewcomplain = new ViewComplain();
				viewcomplain.setVisible(true);
//				System.out.println("Hello");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblViewComplain.setForeground(new Color(0, 0, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblViewComplain.setForeground(new Color(101, 133, 243));
			}
		});
		lblViewComplain.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblViewComplain.setBounds(726, 219, 125, 36);
		contentPane.add(lblViewComplain);
		
		JLabel lblUpdateCost = new JLabel("Update Daily Messing Cost");
		lblUpdateCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateCost.setForeground(new Color(101, 133, 243));
		lblUpdateCost.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				DailyMessingCost dailycost = new DailyMessingCost();
				dailycost.setVisible(true);
//				System.out.println("Hello");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUpdateCost.setForeground(new Color(0, 0, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblUpdateCost.setForeground(new Color(101, 133, 243));
			}
		});
		lblUpdateCost.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblUpdateCost.setBounds(506, 219, 200, 36);
		contentPane.add(lblUpdateCost);
		
		JLabel lblBillPayment = new JLabel("Bill Payment");
		lblBillPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillPayment.setForeground(new Color(101, 133, 243));
		lblBillPayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				BillPayment bp = new BillPayment();
				bp.setVisible(true);
				//System.out.println("Hello");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBillPayment.setForeground(new Color(0, 0, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBillPayment.setForeground(new Color(101, 133, 243));
			}
		});
		lblBillPayment.setFont(new Font("Segoe UI Light", Font.BOLD, 15));
		lblBillPayment.setBounds(179, 347, 141, 36);
		contentPane.add(lblBillPayment);
		
		JLabel lblMenu = new JLabel("");
		lblMenu.setBounds(384, 157, 72, 80);
		contentPane.add(lblMenu);
		lblMenu.setIcon(new ImageIcon(new ImageIcon("menu.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		
		JLabel lblPrice = new JLabel("");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(525, 157, 141, 80);
		contentPane.add(lblPrice);
		lblPrice.setIcon(new ImageIcon(new ImageIcon("price.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		
		JLabel lblComplain = new JLabel("");
		lblComplain.setBounds(754, 157, 72, 80);
		contentPane.add(lblComplain);
		lblComplain.setIcon(new ImageIcon(new ImageIcon("complain.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		
		JLabel lblBill = new JLabel("");
		lblBill.setHorizontalAlignment(SwingConstants.CENTER);
		lblBill.setBounds(217, 279, 72, 80);
		contentPane.add(lblBill);
		lblBill.setIcon(new ImageIcon(new ImageIcon("bill.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
		
		JLabel lblNewLabel_1 = new JLabel("ADMIN DASHBOARD");
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(179, 39, 534, 80);
		contentPane.add(lblNewLabel_1);
	}

}
