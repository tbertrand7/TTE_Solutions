package trainController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JComboBox;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class TestPanel extends JFrame {

	private JPanel contentPane;
	private JTextField txtSpeedLimit;
	private JTextField txtSpeedCommand;
	private JTextField txtCurrentSpeed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPanel frame = new TestPanel();
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
	public TestPanel() {
		setTitle("Train Controller Test Panel");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TestPanel.class.getResource("/trainController/computer1.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<Integer> TrainSelect = new JComboBox<Integer>();
		TrainSelect.setSelectedIndex(0);
		TrainSelect.setMaximumRowCount(100);
		TrainSelect.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		TrainSelect.setBounds(10, 47, 154, 31);
		TrainSelect.addItem(new Integer(300000));
		TrainSelect.addItem(new Integer(300100));
		TrainSelect.addItem(new Integer(300300));
		contentPane.add(TrainSelect);
		
		JLabel lblTrainId = new JLabel("Train ID");
		lblTrainId.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblTrainId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrainId.setBounds(10, 11, 154, 25);
		contentPane.add(lblTrainId);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit");
		lblSpeedLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSpeedLimit.setBounds(10, 102, 154, 31);
		contentPane.add(lblSpeedLimit);
		
		txtSpeedLimit = new JTextField();
		txtSpeedLimit.setBounds(187, 102, 86, 31);
		contentPane.add(txtSpeedLimit);
		txtSpeedLimit.setColumns(10);
		
		JButton btnSendSpeedLimit = new JButton("Send");
		btnSendSpeedLimit.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendSpeedLimit.setBounds(283, 102, 69, 31);
		contentPane.add(btnSendSpeedLimit);
		
		JLabel lblSpeedCommand = new JLabel("Speed Command");
		lblSpeedCommand.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeedCommand.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblSpeedCommand.setBounds(10, 144, 154, 31);
		contentPane.add(lblSpeedCommand);
		
		txtSpeedCommand = new JTextField();
		txtSpeedCommand.setColumns(10);
		txtSpeedCommand.setBounds(187, 144, 86, 31);
		contentPane.add(txtSpeedCommand);
		
		JButton btnSendSpeedCommand = new JButton("Send");
		btnSendSpeedCommand.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendSpeedCommand.setBounds(283, 144, 69, 31);
		contentPane.add(btnSendSpeedCommand);
		
		JLabel lblCurrentSpeed = new JLabel("Current Speed");
		lblCurrentSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSpeed.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
		lblCurrentSpeed.setBounds(10, 186, 154, 31);
		contentPane.add(lblCurrentSpeed);
		
		txtCurrentSpeed = new JTextField();
		txtCurrentSpeed.setColumns(10);
		txtCurrentSpeed.setBounds(187, 186, 86, 31);
		contentPane.add(txtCurrentSpeed);
		
		JButton btnSendCurrentSpeed = new JButton("Send");
		btnSendCurrentSpeed.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSendCurrentSpeed.setBounds(283, 186, 69, 31);
		contentPane.add(btnSendCurrentSpeed);
	}
}
