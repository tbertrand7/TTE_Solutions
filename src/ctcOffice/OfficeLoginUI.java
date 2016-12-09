package ctcOffice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.UIManager.LookAndFeelInfo;

public class OfficeLoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldUsername;
	private JPasswordField passwordField;
	private OfficeLogin officeLogin;

	public OfficeLoginUI(OfficeLogin login) {
		setResizable(false);
		setTitle("CTC Office Login");
		setFont(new Font("SansSerif", Font.PLAIN, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(OfficeLoginUI.class.getResource("/shared/TTE.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		officeLogin = login;

		/* MENU */
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		setJMenuBar(menuBar);
		
		JMenu mnSettings = new JMenu("Settings");
		mnSettings.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnSettings);
		
		JMenuItem mntmCreateNewUser = new JMenuItem("Create New User");
		mntmCreateNewUser.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserUI newUserUI = new NewUserUI(officeLogin);
				newUserUI.setVisible(true);
			}
		});
		mnSettings.add(mntmCreateNewUser);
		
		/* Contents */
		
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
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(11, 11, 140, 120);
		lblLogo.setIcon(new ImageIcon(OfficeLoginUI.class.getResource("/ctcOffice/loginLogo.png")));
		contentPane.add(lblLogo);
		contentPane.add(lblUserName);
		contentPane.add(lblPassword);
		contentPane.add(passwordField);
		contentPane.add(txtFieldUsername);
		
		
		JLabel lblError = new JLabel("Error: Login Failed");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblError.setBounds(256, 4, 228, 22);
		lblError.setVisible(false);
		contentPane.add(lblError);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(332, 108, 77, 33);
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(officeLogin.validateLogin(txtFieldUsername.getText(), passwordField.getText()))
				{
					officeLogin.loginSuccess(txtFieldUsername.getText());
					dispose();
				}
				else
				{
					lblError.setVisible(true);
				}
			}
		});
		contentPane.add(btnLogin);
		
		//Set defaults for login
		txtFieldUsername.setText("admin");
		passwordField.setText("password");
	}
}
