package ctcOffice;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;

public class OfficeLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficeLogin frame = new OfficeLogin();
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
	public OfficeLogin() {
		setResizable(false);
		setTitle("CTC Office Login");
		setFont(new Font("SansSerif", Font.PLAIN, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(OfficeLogin.class.getResource("/shared/TTE.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 200);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		mnSettings.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnSettings);
		
		JMenuItem mntmCreateNewUser = new JMenuItem("Create New User");
		mntmCreateNewUser.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mnSettings.add(mntmCreateNewUser);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setBounds(256, 32, 228, 33);
		txtFieldUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtFieldUsername.setColumns(10);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(169, 38, 81, 21);
		lblUserName.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(256, 71, 228, 33);
		passwordField.setColumns(12);
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(169, 77, 81, 21);
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(332, 108, 77, 33);
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 16));
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(11, 11, 140, 120);
		lblLogo.setIcon(new ImageIcon(OfficeLogin.class.getResource("/ctcOffice/loginLogo.png")));
		contentPane.add(lblLogo);
		contentPane.add(lblUserName);
		contentPane.add(lblPassword);
		contentPane.add(passwordField);
		contentPane.add(txtFieldUsername);
		contentPane.add(btnLogin);
		
		JLabel lblErrorLoginFailed = new JLabel("Error: Login Failed");
		lblErrorLoginFailed.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorLoginFailed.setForeground(Color.RED);
		lblErrorLoginFailed.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblErrorLoginFailed.setBounds(256, 4, 228, 22);
		contentPane.add(lblErrorLoginFailed);
	}
}
