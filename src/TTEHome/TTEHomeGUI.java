package TTEHome;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ctcOffice.*;
import trackModel.TrackModelUI;
import trainController.TrainControllerInstances;
import trainModel.TrainModel;
import trainModel.TrainModelGUI;
import trainModel.Trains;
import waysideController.WaysideControllerUI;
import waysideController.LoadPLCUI;
import waysideController.WaysideController;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class TTEHomeGUI {

	private JFrame frmTteTrainHome;
	public CTCOffice ctc;
	public TrainControllerInstances tci;
	public SystemClock sysClock;
	public Trains modelList; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TTEHomeGUI window = new TTEHomeGUI();
					window.frmTteTrainHome.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TTEHomeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		frmTteTrainHome = new JFrame();
		frmTteTrainHome.setIconImage(Toolkit.getDefaultToolkit().getImage(TTEHomeGUI.class.getResource("/shared/TTE.png")));
		frmTteTrainHome.setTitle("TTE Train Home");
		frmTteTrainHome.setBounds(100, 100, 450, 300);
		frmTteTrainHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTteTrainHome.getContentPane().setLayout(null);

		sysClock = new SystemClock();
		modelList = new Trains(sysClock);
		tci = new TrainControllerInstances(sysClock, modelList);
		ctc = new CTCOffice(sysClock, tci);
		
		JButton btnNewButton = new JButton("The Office");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctc.initLogin();
			}
		});
		btnNewButton.setBounds(10, 11, 135, 109);
		frmTteTrainHome.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TTEHomeGUI.class.getResource("/shared/TTESmall.png")));
		lblNewLabel.setBounds(186, 34, 60, 60);
		frmTteTrainHome.getContentPane().add(lblNewLabel);
		
		JButton btnWaysideController = new JButton("Wayside Controller");
		btnWaysideController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WaysideControllerUI.main(null);
			}
		});
		btnWaysideController.setBounds(10, 141, 135, 109);
		frmTteTrainHome.getContentPane().add(btnWaysideController);

		
		JButton btnTrainController = new JButton("Train Controller");
		btnTrainController.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tci.newUI();
			}
		});
		btnTrainController.setBounds(289, 11, 135, 109);
		frmTteTrainHome.getContentPane().add(btnTrainController);
		
		

		JButton btnTrainModel = new JButton("Train Model");
		btnTrainModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TODO: @Matt revisit after fixing Trains class
				
				//trainModelInstances.newUI();
				//trainModelGUI tmg = new trainModelGUI(usethis);
				//tci.connectModelToUI(1, tmg);
				
				modelList.newUI();	
			}
		});
		btnTrainModel.setBounds(289, 141, 135, 109);
		frmTteTrainHome.getContentPane().add(btnTrainModel);
		
		JButton btnTrackModel = new JButton("Track Model");
		btnTrackModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrackModelUI.main(null);
			}
		});
		btnTrackModel.setBounds(150, 141, 135, 109);
		frmTteTrainHome.getContentPane().add(btnTrackModel);
	}
}