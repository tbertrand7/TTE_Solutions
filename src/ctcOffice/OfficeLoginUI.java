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
		setTitle("CTC Office Login");
		setFont(new Font("SansSerif", Font.PLAIN, 16));
		setIconImage(Toolkit.getDefaultToolkit().getImage(OfficeLoginUI.class.getResource("/shared/TTE.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 210);
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
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{140, 331, 0};
		gbl_contentPane.rowHeights = new int[]{145, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(OfficeLoginUI.class.getResource("/ctcOffice/loginLogo.png")));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.anchor = GridBagConstraints.WEST;
		gbc_lblLogo.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogo.gridx = 0;
		gbc_lblLogo.gridy = 0;
		contentPane.add(lblLogo, gbc_lblLogo);
		
		JPanel mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 1;
		gbc_mainPanel.gridy = 0;
		contentPane.add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0};
		gbl_mainPanel.rowHeights = new int[]{24, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		
		JLabel lblError = new JLabel("Error: Login Failed");
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.insets = new Insets(0, 0, 5, 0);
		gbc_lblError.gridx = 0;
		gbc_lblError.gridy = 0;
		mainPanel.add(lblError, gbc_lblError);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		JPanel inputPanel = new JPanel();
		GridBagConstraints gbc_inputPanel = new GridBagConstraints();
		gbc_inputPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputPanel.insets = new Insets(0, 0, 5, 0);
		gbc_inputPanel.gridx = 0;
		gbc_inputPanel.gridy = 1;
		mainPanel.add(inputPanel, gbc_inputPanel);
		GridBagLayout gbl_inputPanel = new GridBagLayout();
		gbl_inputPanel.columnWidths = new int[]{110, 40, 0};
		gbl_inputPanel.rowHeights = new int[]{28, 28, 0};
		gbl_inputPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		inputPanel.setLayout(gbl_inputPanel);
		
		JLabel lblUserName = new JLabel("Username:");
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.gridx = 0;
		gbc_lblUserName.gridy = 0;
		inputPanel.add(lblUserName, gbc_lblUserName);
		lblUserName.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		txtFieldUsername = new JTextField();
		GridBagConstraints gbc_txtFieldUsername = new GridBagConstraints();
		gbc_txtFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtFieldUsername.gridx = 1;
		gbc_txtFieldUsername.gridy = 0;
		inputPanel.add(txtFieldUsername, gbc_txtFieldUsername);
		txtFieldUsername.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtFieldUsername.setColumns(10);
		
		//Set defaults for login
		txtFieldUsername.setText("admin");
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		inputPanel.add(lblPassword, gbc_lblPassword);
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		inputPanel.add(passwordField, gbc_passwordField);
		passwordField.setColumns(12);
		passwordField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		passwordField.setText("password");
		
		JButton btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 2;
		mainPanel.add(btnLogin, gbc_btnLogin);
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
		lblError.setVisible(false);
	}
}
