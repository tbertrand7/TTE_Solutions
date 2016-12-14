package trainController;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import TTEHome.SystemClock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestTrainController extends JFrame {

	private JPanel contentPane;
	
	private static TrainControllerInstances tci;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SystemClock sys = new SystemClock();
		
		tci = new TrainControllerInstances(sys, null);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestTrainController frame = new TestTrainController();
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
	public TestTrainController() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 195, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateTrain = new JButton("Create Train");
		btnCreateTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tci.createTrain("green");
			}
		});
		btnCreateTrain.setBounds(10, 11, 146, 36);
		contentPane.add(btnCreateTrain);
		
		JButton btnDeleteTrain = new JButton("Delete Train");
		btnDeleteTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tci.deleteTrain();
			}
		});
		btnDeleteTrain.setBounds(10, 52, 146, 36);
		contentPane.add(btnDeleteTrain);
		
		JButton btnNewUI = new JButton("New UI");
		btnNewUI.setBounds(10, 122, 146, 36);
		btnNewUI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tci.newUI();
			}
		});
		contentPane.add(btnNewUI);
	}
}
