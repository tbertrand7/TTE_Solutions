package ctcOffice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;

import ctcOffice.CTCOffice.Mode;
import trackModel.*;
import trackModel.TrackBlock.*;

public class OfficeUI extends JFrame {

	private JPanel contentPane, trackDisplayPanel, topButtonPanel, statusPanel, notificationPanel, schedulePanel;
	private JTextArea notificationArea;
	private DefaultTableModel tableModel;
	private JSlider simulationSpeed;
	private CTCOffice ctcOffice;
	private JTextField txtFieldSpeed;
	private JButton btnSetSpeed, btnSetDestination, btnToggleSwitch, btnCloseTrack;
	private TrackButton[] greenLine = new TrackButton[152], redLine = new TrackButton[77];
    private JComboBox<TrackBlock> cmbDestinations;
    private JLabel lblSpeedInfo, lblDestInfo, lblAuthInfo, lblTrainNumInfo;
	private JLabel lblLineInfo, lblSectionInfo, lblBlockInfo, lblStatusInfo;
	private JLabel lblElevationInfo, lblSpeedLimitInfo, lblGradeInfo, lblLengthInfo;
	private JLabel lblSwitchInfo, lblUnderInfo, lblCrossingInfo, lblThroughputInfo, lblSwPosInfo, lblStationInfo;

    private TrackButton selectedBlockBtn = null;
	private ScheduledExecutorService exec;
	private ScheduledFuture<?> trackUpdate;

	public OfficeUI(CTCOffice ctc) {
		ctcOffice = ctc;

		setFont(new Font("SansSerif", Font.PLAIN, 16));
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(OfficeUI.class.getResource("/shared/TTE.png")));
		setBackground(new Color(240, 240, 240));
		setTitle("CTC Office Control Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1272, 862);

		initStaticElements();
        initDynamicElements();
		initTrackButtons();
		setHasTrain(false);

		//Create Thread to update track info
		exec = Executors.newSingleThreadScheduledExecutor();
		trackUpdate = exec.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				ctcOffice.loadTrackData();
				updateTrackButtons();
				selectedBlockChanged();
			}
		}, 0, 10, TimeUnit.MILLISECONDS);
	}

	/**
	 * Change simulation speed
	 */
	private void simulationSpeedChanged()
	{
		if (!simulationSpeed.getValueIsAdjusting() && simulationSpeed.getValue() != ctcOffice.getSimulationSpeed())
			ctcOffice.setSimulationSpeed(simulationSpeed.getValue());
	}

	/**
	 * Launch JFileChooser to select csv schedule file
	 */
	private void loadScheduleClick()
	{
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
		fc.setFileFilter(filter);
		fc.showOpenDialog(contentPane);
		ctcOffice.loadSchedule(fc.getSelectedFile(), tableModel);
	}

	private void runScheduleClick()
	{
		//TODO: Implement run schedule
	}

	private void btnCloseTrackClick()
	{
		ctcOffice.closeBlock(selectedBlockBtn.line, selectedBlockBtn.block);
	}

	private void btnToggleSwitchClick()
	{
		//TODO: Enable switch toggling once class is properly implemented
	}

	private void btnSetSpeedClick()
	{
		try 
		{
			double newTrainSpeed = Double.parseDouble(txtFieldSpeed.getText());
			
			if (newTrainSpeed < 1 || newTrainSpeed > 1000)
				throw new NumberFormatException();
			else
			{
				TrackBlock selectedBlock;

				if (selectedBlockBtn.line.equals("Green"))
					selectedBlock = ctcOffice.greenLine[selectedBlockBtn.block - 1];
				else
					selectedBlock = ctcOffice.redLine[selectedBlockBtn.block - 1];
				
				ctcOffice.suggestSpeed(newTrainSpeed,selectedBlock.trainID);
				logNotification("Speed of " + newTrainSpeed + " mph suggested for Train " + selectedBlock.trainID);
				txtFieldSpeed.setText("");
				lblSpeedInfo.setText(newTrainSpeed + " mph");
			}
		}
		catch(NumberFormatException nfe)
		{
			logNotification("ERROR: '" + txtFieldSpeed.getText() + "' is not a valid speed");
			txtFieldSpeed.setText("");
		}
	}
	
	private void btnSetDestinationClick()
	{
		TrackBlock selectedBlock, destBlock;

		if (selectedBlockBtn.line.equals("Green"))
			selectedBlock = ctcOffice.greenLine[selectedBlockBtn.block - 1];
		else
			selectedBlock = ctcOffice.redLine[selectedBlockBtn.block - 1];

		destBlock = (TrackBlock)cmbDestinations.getSelectedItem();
		ctcOffice.suggestDestination(destBlock, selectedBlock.trainID);
		logNotification("Train " + selectedBlock.trainID + " dispatched to " + destBlock.toString());
	}

	/**
	 * Update UI with information of selected block
	 */
	private void selectedBlockChanged() {
		if (selectedBlockBtn != null) {
			TrackBlock selectedBlock;

			if (selectedBlockBtn.line.equals("Green"))
				selectedBlock = ctcOffice.greenLine[selectedBlockBtn.block - 1];
			else
				selectedBlock = ctcOffice.redLine[selectedBlockBtn.block - 1];

			//Set block info labels
			lblLineInfo.setText(selectedBlockBtn.line);
			lblBlockInfo.setText(Integer.toString(selectedBlockBtn.block));
			lblSectionInfo.setText(selectedBlock.section);
			lblLengthInfo.setText(selectedBlock.blockLength + " ft");
			lblGradeInfo.setText(selectedBlock.blockGrade + "%");
			lblSpeedLimitInfo.setText(selectedBlock.speedLimit + " mph");
			lblElevationInfo.setText(selectedBlock.elevation + " ft");

			//Set status text
			if (selectedBlock.status == BlockStatus.UNOCCUPIED) {
				lblStatusInfo.setText("Unoccupied");
			} else if (selectedBlock.status == BlockStatus.CLOSED) {
				lblStatusInfo.setText("Closed");
			} else {
				lblStatusInfo.setText("Occupied");
			}

			if (selectedBlock.status == BlockStatus.CLOSED)
				btnCloseTrack.setText("Open Block");
			else
				btnCloseTrack.setText("Close Block");

			lblThroughputInfo.setText(ctcOffice.calcThroughput(selectedBlock) + " trains/hr");

			//Set infrastructure info
			String[] infr = selectedBlock.infrastructure.split(";");

			//Set infrastructure info to defaults
			lblUnderInfo.setText("No");
			lblStationInfo.setText("N/A");
			lblSwitchInfo.setText("N/A");
			lblSwPosInfo.setText("N/A");
			lblCrossingInfo.setText("No");
			btnToggleSwitch.setEnabled(false);

			for (int i = 0; i < infr.length; i++) {
				infr[i] = infr[i].trim();

				if (infr[i].equals("STATION")) {
					if (i + 1 < infr.length)
						lblStationInfo.setText(infr[i + 1].trim());
					else
						lblStationInfo.setText("Unnamed");
				}

				if (infr[i].equals("UNDERGROUND"))
					lblUnderInfo.setText("Yes");

				if (infr[i].equals("SWITCH")) {
					lblSwitchInfo.setText(selectedBlock.switchBlock.getID());
					lblSwPosInfo.setText(selectedBlock.switchBlock.getPosition());
					btnToggleSwitch.setEnabled(true);
				}

				if (infr[i].equals("RAILWAY CROSSING"))
					lblCrossingInfo.setText("Yes");
			}

			//Train info
			if (selectedBlock.trainID <= 0) {
				lblTrainNumInfo.setText("");
				lblSpeedInfo.setText("");
				lblDestInfo.setText("");
				lblAuthInfo.setText("");
				setHasTrain(false);
			} else {
				lblTrainNumInfo.setText(selectedBlock.trainID + "");
				lblSpeedInfo.setText(selectedBlock.speed + " mph");
				lblDestInfo.setText(""); //TODO: Display destination block name for train
				lblAuthInfo.setText(selectedBlock.authority + " blocks");
				setHasTrain(true);
			}
		}
    }

	/**
	 * Updates the status colors for all of the track buttons every time new data is brought in from DB
	 */
	private void updateTrackButtons()
	{
        for (int i=0; i < greenLine.length; i++)
        {
            greenLine[i].setStatus(ctcOffice.greenLine[i]);
            if (greenLine[i].checkWarning(ctcOffice.greenLine[i])) {
            	logNotification("WARNING: " + greenLine[i].toString() + " may be broken!");
			}
            if (i < redLine.length) {
				redLine[i].setStatus(ctcOffice.redLine[i]);
				if (redLine[i].checkWarning(ctcOffice.redLine[i])) {
					logNotification("WARNING: " + redLine[i].toString() + " may be broken!");
				}
			}
        }
	}

	/**
	 * Logs notification with the time to the notifications panel
	 * @param msg message to be displayed
	 */
	void logNotification(String msg)
	{
		String timeStamp = new SimpleDateFormat("hh:mm:ss aa").format(Calendar.getInstance().getTime());
		notificationArea.append(timeStamp + ": " + msg + "\n");
	}

	/**
	 * Enables elements if block is occupied
	 * @param x is block occupied
	 */
	private void setHasTrain(boolean x)
	{
		txtFieldSpeed.setEnabled(x);
		cmbDestinations.setEnabled(x);
		btnSetSpeed.setEnabled(x);
		btnSetDestination.setEnabled(x);
	}

	/**
	 * Set Manual Mode
	 */
	private void rdbtnManualClick()
	{
		ctcOffice.setMode(Mode.MANUAL);
	}

	/**
	 * Set Automatic Mode
	 */
	private void rdbtnAutoClick()
	{
		ctcOffice.setMode(Mode.AUTOMATIC);
	}

	/**
	 * Initializes static elements of Office UI
	 */
	private void initStaticElements()
	{
		/* Menus */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("SansSerif", Font.PLAIN, 16));
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnFile);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog (null, "Are you sure you want to logout?","Logout", JOptionPane.YES_NO_OPTION) == 0)
					ctcOffice.logout();
			}
		});
		mnFile.add(mntmLogout);
		
		JMenu mnDispatch = new JMenu("Dispatch");
		mnDispatch.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnDispatch);
		
		JMenuItem mntmDispatchNewTrain = new JMenuItem("Dispatch New Train");
		mntmDispatchNewTrain.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmDispatchNewTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DispatchNewUI dispNew = new DispatchNewUI(ctcOffice);
				dispNew.setVisible(true);
			}
		});
		mnDispatch.add(mntmDispatchNewTrain);

		JMenu mnSchedule = new JMenu("Schedule");
		mnSchedule.setFont(new Font("SansSerif", Font.PLAIN, 14));
		menuBar.add(mnSchedule);

		JMenuItem mntmLoadSchedule = new JMenuItem("Load Schedule");
		mntmLoadSchedule.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmLoadSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadScheduleClick();
			}
		});
		mnSchedule.add(mntmLoadSchedule);

		JMenuItem mntmRunSchedule = new JMenuItem("Run Schedule");
		mntmRunSchedule.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mntmRunSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runScheduleClick();
			}
		});
		mnSchedule.add(mntmRunSchedule);

		/* Panels */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		trackDisplayPanel = new JPanel();
		trackDisplayPanel.setBackground(new Color(255, 255, 255));
		trackDisplayPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		trackDisplayPanel.setBounds(519, 64, 737, 733);
		contentPane.add(trackDisplayPanel);
		trackDisplayPanel.setLayout(null);

		topButtonPanel = new JPanel();
		topButtonPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		topButtonPanel.setBounds(0, 0, 1256, 66);
		contentPane.add(topButtonPanel);
		topButtonPanel.setLayout(null);

		JTabbedPane mainMenuTabPane = new JTabbedPane(JTabbedPane.TOP);
		mainMenuTabPane.setFont(new Font("SansSerif", Font.PLAIN, 16));
		mainMenuTabPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.setBounds(0, 297, 520, 500);
		contentPane.add(mainMenuTabPane);

		statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.addTab("Details", null, statusPanel, null);
		statusPanel.setLayout(null);

		notificationPanel = new JPanel();
		notificationPanel.setBounds(0, 64, 520, 234);
		contentPane.add(notificationPanel);
		notificationPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		notificationPanel.setLayout(null);

		schedulePanel = new JPanel();
		schedulePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainMenuTabPane.addTab("Schedule", null, schedulePanel, null);
		schedulePanel.setLayout(null);

		/* Legend */
		JPanel legendPanel = new JPanel();
		legendPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		legendPanel.setBackground(Color.WHITE);
		legendPanel.setBounds(6, 44, 121, 111);
		trackDisplayPanel.add(legendPanel);
		legendPanel.setLayout(null);

		JToggleButton toggleButton = new JToggleButton("");
		toggleButton.setBounds(6, 30, 20, 20);
		legendPanel.add(toggleButton);

		JLabel lblOpen = new JLabel("Unoccupied");
		lblOpen.setBounds(33, 28, 86, 21);
		lblOpen.setFont(new Font("SansSerif", Font.PLAIN, 16));
		legendPanel.add(lblOpen);

		JToggleButton tglbtnNewToggleButton = new JToggleButton("");
		tglbtnNewToggleButton.setForeground(Color.BLUE);
		tglbtnNewToggleButton.setBackground(Color.BLUE);
		tglbtnNewToggleButton.setBounds(6, 57, 20, 20);
		legendPanel.add(tglbtnNewToggleButton);

		JLabel lblOccupied = new JLabel("Occupied");
		lblOccupied.setBounds(33, 55, 67, 21);
		lblOccupied.setFont(new Font("SansSerif", Font.PLAIN, 16));
		legendPanel.add(lblOccupied);

		JToggleButton toggleButton_1 = new JToggleButton("");
		toggleButton_1.setBackground(Color.BLACK);
		toggleButton_1.setBounds(6, 84, 20, 20);
		legendPanel.add(toggleButton_1);

		JLabel lblClosed = new JLabel("Closed");
		lblClosed.setBounds(33, 82, 50, 21);
		lblClosed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		legendPanel.add(lblClosed);

		JLabel lblLegend = new JLabel("Legend");
		lblLegend.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegend.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblLegend.setBounds(6, 5, 109, 20);
		legendPanel.add(lblLegend);

		/* Top Button Panel */
		JLabel lblSimulationSpeed = new JLabel("Simulation Speed");
		lblSimulationSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSimulationSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimulationSpeed.setBounds(1071, 2, 175, 18);
		topButtonPanel.add(lblSimulationSpeed);

		JLabel lblCtcOfficeControl = new JLabel("CTC Office Control Panel");
		lblCtcOfficeControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblCtcOfficeControl.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblCtcOfficeControl.setBounds(182, 0, 791, 64);
		topButtonPanel.add(lblCtcOfficeControl);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(OfficeUI.class.getResource("/ctcOffice/officeLogo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(2, 2, 180, 62);
		topButtonPanel.add(lblLogo);

		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnManual.setToolTipText("Enable manual mode");
		rdbtnManual.setSelected(true);
		rdbtnManual.setBounds(985, 12, 80, 23);
		rdbtnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnManualClick();
			}
		});
		topButtonPanel.add(rdbtnManual);

		JRadioButton rdbtnAuto = new JRadioButton("Auto");
		rdbtnAuto.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnAuto.setToolTipText("Enable automatic simulation");
		rdbtnAuto.setBounds(985, 32, 80, 23);
		rdbtnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAutoClick();
			}
		});
		topButtonPanel.add(rdbtnAuto);

		ButtonGroup execMode = new ButtonGroup();
		execMode.add(rdbtnAuto);
		execMode.add(rdbtnManual);

		/* Status Panel */
		JLabel lblTrackInfo = new JLabel("Track Info");
		lblTrackInfo.setBounds(5, 6, 180, 20);
		statusPanel.add(lblTrackInfo);
		lblTrackInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTrackInfo.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblLine = new JLabel("Line:");
		lblLine.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLine.setBounds(5, 30, 90, 20);
		statusPanel.add(lblLine);

		JLabel lblSection = new JLabel("Section:");
		lblSection.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSection.setBounds(5, 55, 90, 20);
		statusPanel.add(lblSection);

		JLabel lblBlock = new JLabel("Block:");
		lblBlock.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblBlock.setBounds(5, 80, 90, 20);
		statusPanel.add(lblBlock);

		JLabel lblLength = new JLabel("Length:");
		lblLength.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblLength.setBounds(5, 105, 90, 20);
		statusPanel.add(lblLength);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblGrade.setBounds(5, 130, 90, 20);
		statusPanel.add(lblGrade);

		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		lblSpeedLimit.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSpeedLimit.setBounds(5, 155, 90, 20);
		statusPanel.add(lblSpeedLimit);

		JLabel lblElevation = new JLabel("Elevation:");
		lblElevation.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblElevation.setBounds(5, 180, 90, 20);
		statusPanel.add(lblElevation);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStatus.setBounds(5, 205, 90, 20);
		statusPanel.add(lblStatus);

		JLabel lblSwitch = new JLabel("Switch:");
		lblSwitch.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwitch.setBounds(260, 80, 75, 20);
		statusPanel.add(lblSwitch);

		JLabel lblStation = new JLabel("Station:");
		lblStation.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStation.setBounds(260, 30, 75, 20);
		statusPanel.add(lblStation);

		JLabel lblInfrastracture = new JLabel("Infrastructure");
		lblInfrastracture.setBounds(260, 6, 243, 20);
		statusPanel.add(lblInfrastracture);
		lblInfrastracture.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfrastracture.setFont(new Font("Tahoma", Font.BOLD, 18));

		JLabel lblAuthority = new JLabel("Authority:");
		lblAuthority.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAuthority.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAuthority.setBounds(5, 400, 75, 20);
		statusPanel.add(lblAuthority);

		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblDestination.setBounds(5, 375, 90, 20);
		statusPanel.add(lblDestination);

		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSpeed.setBounds(5, 350, 75, 20);
		statusPanel.add(lblSpeed);

		JLabel lblTrainInfo = new JLabel("Train Info");
		lblTrainInfo.setBounds(5, 300, 165, 20);
		statusPanel.add(lblTrainInfo);
		lblTrainInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTrainInfo.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblUnder = new JLabel("Underground:");
		lblUnder.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUnder.setBounds(260, 130, 103, 20);
		statusPanel.add(lblUnder);

		JLabel lblCrossing = new JLabel("Railway Crossing:");
		lblCrossing.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCrossing.setBounds(260, 155, 125, 20);
		statusPanel.add(lblCrossing);

		JLabel lblThroughput = new JLabel("Throughput:");
		lblThroughput.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblThroughput.setBounds(260, 55, 90, 20);
		statusPanel.add(lblThroughput);

		JLabel lblTrainNum = new JLabel("Train Number:");
		lblTrainNum.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTrainNum.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTrainNum.setBounds(5, 325, 103, 20);
		statusPanel.add(lblTrainNum);

		JLabel lblSwitchPos = new JLabel("Switch Position:");
		lblSwitchPos.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwitchPos.setBounds(260, 105, 125, 20);
		statusPanel.add(lblSwitchPos);

		JLabel lblMph = new JLabel("mph");
		lblMph.setBounds(445, 277, 30, 20);
		statusPanel.add(lblMph);
		lblMph.setFont(new Font("SansSerif", Font.PLAIN, 16));

		JLabel lblSetNewSpeed = new JLabel("Enter new speed:");
		lblSetNewSpeed.setBounds(336, 248, 121, 20);
		statusPanel.add(lblSetNewSpeed);
		lblSetNewSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));

		JLabel lblSelectNewDestination = new JLabel("Select new destination:");
		lblSelectNewDestination.setBounds(313, 350, 160, 20);
		statusPanel.add(lblSelectNewDestination);
		lblSelectNewDestination.setFont(new Font("SansSerif", Font.PLAIN, 16));

		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotifications.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNotifications.setBounds(6, 6, 508, 22);
		notificationPanel.add(lblNotifications);
	}

	/**
	 * Initializes dynamic elements of Office UI
	 */
	private void initDynamicElements()
    {
        /* Top Button Panel */

        simulationSpeed = new JSlider();
        simulationSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
        simulationSpeed.setToolTipText("Set speed of auto simulation");
        simulationSpeed.setMajorTickSpacing(3);
        simulationSpeed.setSnapToTicks(true);
        simulationSpeed.setPaintLabels(true);
        simulationSpeed.setPaintTicks(true);
        simulationSpeed.setMinimum(1);
        simulationSpeed.setMinorTickSpacing(1);
        simulationSpeed.setMaximum(10);
        simulationSpeed.setValue(ctcOffice.getSimulationSpeed());
        simulationSpeed.setBounds(1071, 20, 175, 42);
        simulationSpeed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                simulationSpeedChanged();
            }
        });
        topButtonPanel.add(simulationSpeed);

        /* Track Info Elements */

        lblLineInfo = new JLabel("");
        lblLineInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblLineInfo.setBounds(95, 30, 90, 20);
        statusPanel.add(lblLineInfo);
        lblLineInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblStatusInfo = new JLabel("");
        lblStatusInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblStatusInfo.setBounds(95, 205, 90, 20);
        statusPanel.add(lblStatusInfo);
        lblStatusInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblElevationInfo = new JLabel("");
        lblElevationInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblElevationInfo.setBounds(95, 180, 90, 20);
        statusPanel.add(lblElevationInfo);
        lblElevationInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblSpeedLimitInfo = new JLabel("");
        lblSpeedLimitInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblSpeedLimitInfo.setBounds(95, 155, 90, 20);
        statusPanel.add(lblSpeedLimitInfo);
        lblSpeedLimitInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblGradeInfo = new JLabel("");
        lblGradeInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblGradeInfo.setBounds(95, 130, 90, 20);
        statusPanel.add(lblGradeInfo);
        lblGradeInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblLengthInfo = new JLabel("");
        lblLengthInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblLengthInfo.setBounds(95, 105, 90, 20);
        statusPanel.add(lblLengthInfo);
        lblLengthInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblBlockInfo = new JLabel("");
        lblBlockInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblBlockInfo.setBounds(95, 80, 90, 20);
        statusPanel.add(lblBlockInfo);
        lblBlockInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        lblSectionInfo = new JLabel("");
        lblSectionInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblSectionInfo.setBounds(95, 55, 90, 20);
        statusPanel.add(lblSectionInfo);
        lblSectionInfo.setHorizontalAlignment(SwingConstants.RIGHT);

        btnCloseTrack = new JButton("Close Track");
        btnCloseTrack.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnCloseTrack.setBounds(51, 230, 112, 30);
        statusPanel.add(btnCloseTrack);
		btnCloseTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCloseTrackClick();
			}
		});

		lblStationInfo = new JLabel("");
		lblStationInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblStationInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStationInfo.setBounds(313, 30, 190, 20);
		statusPanel.add(lblStationInfo);

		lblSwitchInfo = new JLabel("");
		lblSwitchInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwitchInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSwitchInfo.setBounds(413, 80, 90, 20);
		statusPanel.add(lblSwitchInfo);

		lblUnderInfo = new JLabel("");
		lblUnderInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUnderInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnderInfo.setBounds(423, 130, 80, 20);
		statusPanel.add(lblUnderInfo);

		lblSpeedInfo = new JLabel("");
		lblSpeedInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSpeedInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeedInfo.setBounds(80, 350, 90, 20);
		statusPanel.add(lblSpeedInfo);

		lblDestInfo = new JLabel("");
		lblDestInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblDestInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestInfo.setBounds(80, 375, 90, 20);
		statusPanel.add(lblDestInfo);

		lblAuthInfo = new JLabel("");
		lblAuthInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblAuthInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthInfo.setBounds(80, 400, 90, 20);
		statusPanel.add(lblAuthInfo);

		lblCrossingInfo = new JLabel("");
		lblCrossingInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCrossingInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCrossingInfo.setBounds(433, 155, 70, 20);
		statusPanel.add(lblCrossingInfo);

		lblThroughputInfo = new JLabel("");
		lblThroughputInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThroughputInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblThroughputInfo.setBounds(387, 55, 116, 20);
		statusPanel.add(lblThroughputInfo);

		lblSwPosInfo = new JLabel("");
		lblSwPosInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSwPosInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSwPosInfo.setBounds(413, 105, 90, 20);
		statusPanel.add(lblSwPosInfo);

		btnToggleSwitch = new JButton("Toggle Switch Position");
		btnToggleSwitch.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnToggleSwitch.setBounds(288, 180, 188, 30);
		btnToggleSwitch.setEnabled(false);
		btnToggleSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnToggleSwitchClick();
			}
		});
		statusPanel.add(btnToggleSwitch);

		lblTrainNumInfo = new JLabel("");
		lblTrainNumInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTrainNumInfo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTrainNumInfo.setBounds(80, 325, 90, 20);
		statusPanel.add(lblTrainNumInfo);

		btnSetSpeed = new JButton("Set Speed");
		btnSetSpeed.setBounds(344, 306, 103, 30);
		statusPanel.add(btnSetSpeed);
		btnSetSpeed.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSetSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSetSpeedClick();
			}
		});

		txtFieldSpeed = new JTextField();
		txtFieldSpeed.setBounds(318, 273, 122, 30);
		statusPanel.add(txtFieldSpeed);
		txtFieldSpeed.setColumns(10);

		btnSetDestination = new JButton("Set Destination");
		btnSetDestination.setBounds(323, 408, 135, 30);
		statusPanel.add(btnSetDestination);
		btnSetDestination.setFont(new Font("SansSerif", Font.PLAIN, 16));
		btnSetDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSetDestinationClick();
			}
		});

		cmbDestinations = new JComboBox<>();
		cmbDestinations.setBounds(313, 375, 162, 30);
		statusPanel.add(cmbDestinations);
		cmbDestinations.setFont(new Font("SansSerif", Font.PLAIN, 16));

		/* Notification Panel */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 27, 508, 201);
		notificationPanel.add(scrollPane_1);

		notificationArea = new JTextArea();
		notificationArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		scrollPane_1.setViewportView(notificationArea);
		notificationArea.setEditable(false);
		notificationArea.setLineWrap(true);
		notificationArea.setWrapStyleWord(true);

		/* Schedule Table */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 506, 451);
		schedulePanel.add(scrollPane);

		JTable tblSchedule = new JTable();
		tblSchedule.setEnabled(false);
		tblSchedule.setCellSelectionEnabled(true);
		tblSchedule.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSchedule.setShowVerticalLines(true);
		tblSchedule.setShowHorizontalLines(true);
		tableModel = new DefaultTableModel(0,0);
		tableModel.setColumnIdentifiers(new String[] {
				"Line", "Train", "Destination", "Time"
		});
		tblSchedule.setModel(tableModel);
		tblSchedule.getColumnModel().getColumn(0).setResizable(false);
		tblSchedule.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblSchedule.getColumnModel().getColumn(1).setResizable(false);
		tblSchedule.getColumnModel().getColumn(1).setPreferredWidth(20);
		tblSchedule.getColumnModel().getColumn(2).setResizable(false);
		tblSchedule.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblSchedule.getColumnModel().getColumn(3).setResizable(false);
		tblSchedule.getColumnModel().getColumn(3).setPreferredWidth(30);
		scrollPane.setViewportView(tblSchedule);
	}

	/**
	 * Initializes all the track buttons
	 */
	private void initTrackButtons()
	{
		TrackButton toggleButtonG1 = new TrackButton("");
		toggleButtonG1.setBackground(Color.LIGHT_GRAY);
		toggleButtonG1.setBounds(354, 24, 15, 15);
		trackDisplayPanel.add(toggleButtonG1);
		greenLine[0] = toggleButtonG1;

		TrackButton toggleButtonG2 = new TrackButton("");
		toggleButtonG2.setBackground(Color.LIGHT_GRAY);
		toggleButtonG2.setBounds(373, 44, 20, 20);
		trackDisplayPanel.add(toggleButtonG2);
		greenLine[1] = toggleButtonG2;

		TrackButton toggleButtonG3 = new TrackButton("");
		toggleButtonG3.setBackground(Color.LIGHT_GRAY);
		toggleButtonG3.setBounds(387, 61, 15, 15);
		trackDisplayPanel.add(toggleButtonG3);
		greenLine[2] = toggleButtonG3;

		TrackButton toggleButtonG4 = new TrackButton("");
		toggleButtonG4.setBackground(Color.LIGHT_GRAY);
		toggleButtonG4.setBounds(404, 76, 15, 15);
		trackDisplayPanel.add(toggleButtonG4);
		greenLine[3] = toggleButtonG4;

		TrackButton toggleButtonG5 = new TrackButton("");
		toggleButtonG5.setBackground(Color.LIGHT_GRAY);
		toggleButtonG5.setBounds(427, 91, 15, 15);
		trackDisplayPanel.add(toggleButtonG5);
		greenLine[4] = toggleButtonG5;

		TrackButton toggleButtonG6 = new TrackButton("");
		toggleButtonG6.setBackground(Color.LIGHT_GRAY);
		toggleButtonG6.setBounds(455, 97, 15, 15);
		trackDisplayPanel.add(toggleButtonG6);
		greenLine[5] = toggleButtonG6;

		TrackButton toggleButtonG7 = new TrackButton("");
		toggleButtonG7.setBackground(Color.LIGHT_GRAY);
		toggleButtonG7.setBounds(502, 91, 15, 15);
		trackDisplayPanel.add(toggleButtonG7);
		greenLine[6] = toggleButtonG7;

		TrackButton toggleButtonG8 = new TrackButton("");
		toggleButtonG8.setBackground(Color.LIGHT_GRAY);
		toggleButtonG8.setBounds(557, 68, 15, 15);
		trackDisplayPanel.add(toggleButtonG8);
		greenLine[7] = toggleButtonG8;

		TrackButton toggleButtonG9 = new TrackButton("");
		toggleButtonG9.setBackground(Color.LIGHT_GRAY);
		toggleButtonG9.setBounds(491, 32, 20, 20);
		trackDisplayPanel.add(toggleButtonG9);
		greenLine[8] = toggleButtonG9;

		TrackButton toggleButtonG10 = new TrackButton("");
		toggleButtonG10.setBackground(Color.LIGHT_GRAY);
		toggleButtonG10.setBounds(466, 25, 15, 15);
		trackDisplayPanel.add(toggleButtonG10);
		greenLine[9] = toggleButtonG10;

		TrackButton toggleButtonG11 = new TrackButton("");
		toggleButtonG11.setBackground(Color.LIGHT_GRAY);
		toggleButtonG11.setBounds(427, 20, 15, 15);
		trackDisplayPanel.add(toggleButtonG11);
		greenLine[10] = toggleButtonG11;

		TrackButton toggleButtonG12 = new TrackButton("");
		toggleButtonG12.setBackground(Color.LIGHT_GRAY);
		toggleButtonG12.setBounds(354, 11, 15, 15);
		trackDisplayPanel.add(toggleButtonG12);
		greenLine[11] = toggleButtonG12;

		TrackButton toggleButtonG13 = new TrackButton("");
		toggleButtonG13.setBackground(Color.LIGHT_GRAY);
		toggleButtonG13.setBounds(302, 14, 15, 15);
		trackDisplayPanel.add(toggleButtonG13);
		greenLine[12] = toggleButtonG13;

		TrackButton toggleButtonG14 = new TrackButton("");
		toggleButtonG14.setBackground(Color.LIGHT_GRAY);
		toggleButtonG14.setBounds(280, 14, 15, 15);
		trackDisplayPanel.add(toggleButtonG14);
		greenLine[13] = toggleButtonG14;

		TrackButton toggleButtonG15 = new TrackButton("");
		toggleButtonG15.setBackground(Color.LIGHT_GRAY);
		toggleButtonG15.setBounds(255, 15, 15, 15);
		trackDisplayPanel.add(toggleButtonG15);
		greenLine[14] = toggleButtonG15;

		TrackButton toggleButtonG16 = new TrackButton("");
		toggleButtonG16.setBackground(Color.LIGHT_GRAY);
		toggleButtonG16.setBounds(235, 14, 20, 20);
		trackDisplayPanel.add(toggleButtonG16);
		greenLine[15] = toggleButtonG16;

		TrackButton toggleButtonG17 = new TrackButton("");
		toggleButtonG17.setBackground(Color.LIGHT_GRAY);
		toggleButtonG17.setBounds(188, 22, 15, 15);
		trackDisplayPanel.add(toggleButtonG17);
		greenLine[16] = toggleButtonG17;

		TrackButton toggleButtonG18 = new TrackButton("");
		toggleButtonG18.setBackground(Color.LIGHT_GRAY);
		toggleButtonG18.setBounds(167, 34, 15, 15);
		trackDisplayPanel.add(toggleButtonG18);
		greenLine[17] = toggleButtonG18;

		TrackButton toggleButtonG19 = new TrackButton("");
		toggleButtonG19.setBackground(Color.LIGHT_GRAY);
		toggleButtonG19.setBounds(150, 47, 15, 15);
		trackDisplayPanel.add(toggleButtonG19);
		greenLine[18] = toggleButtonG19;

		TrackButton toggleButtonG20 = new TrackButton("");
		toggleButtonG20.setBackground(Color.LIGHT_GRAY);
		toggleButtonG20.setBounds(138, 61, 15, 15);
		trackDisplayPanel.add(toggleButtonG20);
		greenLine[19] = toggleButtonG20;

		TrackButton toggleButtonG21 = new TrackButton("");
		toggleButtonG21.setBackground(Color.LIGHT_GRAY);
		toggleButtonG21.setBounds(134, 93, 15, 15);
		trackDisplayPanel.add(toggleButtonG21);
		greenLine[20] = toggleButtonG21;

		TrackButton toggleButtonG22 = new TrackButton("");
		toggleButtonG22.setBackground(Color.LIGHT_GRAY);
		toggleButtonG22.setBounds(132, 105, 20, 20);
		trackDisplayPanel.add(toggleButtonG22);
		greenLine[21] = toggleButtonG22;

		TrackButton toggleButtonG23 = new TrackButton("");
		toggleButtonG23.setBackground(Color.LIGHT_GRAY);
		toggleButtonG23.setBounds(135, 122, 15, 15);
		trackDisplayPanel.add(toggleButtonG23);
		greenLine[22] = toggleButtonG23;

		TrackButton toggleButtonG24 = new TrackButton("");
		toggleButtonG24.setBackground(Color.LIGHT_GRAY);
		toggleButtonG24.setBounds(135, 134, 15, 15);
		trackDisplayPanel.add(toggleButtonG24);
		greenLine[23] = toggleButtonG24;

		TrackButton toggleButtonG25 = new TrackButton("");
		toggleButtonG25.setBackground(Color.LIGHT_GRAY);
		toggleButtonG25.setBounds(135, 146, 15, 15);
		trackDisplayPanel.add(toggleButtonG25);
		greenLine[24] = toggleButtonG25;

		TrackButton toggleButtonG26 = new TrackButton("");
		toggleButtonG26.setBackground(Color.LIGHT_GRAY);
		toggleButtonG26.setBounds(135, 158, 15, 15);
		trackDisplayPanel.add(toggleButtonG26);
		greenLine[25] = toggleButtonG26;

		TrackButton toggleButtonG27 = new TrackButton("");
		toggleButtonG27.setBackground(Color.LIGHT_GRAY);
		toggleButtonG27.setBounds(135, 170, 15, 15);
		trackDisplayPanel.add(toggleButtonG27);
		greenLine[26] = toggleButtonG27;

		TrackButton toggleButtonG28 = new TrackButton("");
		toggleButtonG28.setBackground(Color.LIGHT_GRAY);
		toggleButtonG28.setBounds(135, 182, 15, 15);
		trackDisplayPanel.add(toggleButtonG28);
		greenLine[27] = toggleButtonG28;

		TrackButton toggleButtonG29 = new TrackButton("");
		toggleButtonG29.setBackground(Color.LIGHT_GRAY);
		toggleButtonG29.setBounds(134, 199, 15, 15);
		trackDisplayPanel.add(toggleButtonG29);
		greenLine[28] = toggleButtonG29;

		TrackButton toggleButtonG30 = new TrackButton("");
		toggleButtonG30.setBackground(Color.LIGHT_GRAY);
		toggleButtonG30.setBounds(134, 211, 15, 15);
		trackDisplayPanel.add(toggleButtonG30);
		greenLine[29] = toggleButtonG30;

		TrackButton toggleButtonG31 = new TrackButton("");
		toggleButtonG31.setBackground(Color.LIGHT_GRAY);
		toggleButtonG31.setBounds(132, 223, 20, 20);
		trackDisplayPanel.add(toggleButtonG31);
		greenLine[30] = toggleButtonG31;

		TrackButton toggleButtonG32 = new TrackButton("");
		toggleButtonG32.setBackground(Color.LIGHT_GRAY);
		toggleButtonG32.setBounds(135, 240, 15, 15);
		trackDisplayPanel.add(toggleButtonG32);
		greenLine[31] = toggleButtonG32;

		TrackButton toggleButtonG33 = new TrackButton("");
		toggleButtonG33.setBackground(Color.LIGHT_GRAY);
		toggleButtonG33.setBounds(136, 262, 15, 15);
		trackDisplayPanel.add(toggleButtonG33);
		greenLine[32] = toggleButtonG33;

		TrackButton toggleButtonG34 = new TrackButton("");
		toggleButtonG34.setBackground(Color.LIGHT_GRAY);
		toggleButtonG34.setBounds(147, 281, 15, 15);
		trackDisplayPanel.add(toggleButtonG34);
		greenLine[33] = toggleButtonG34;

		TrackButton toggleButtonG35 = new TrackButton("");
		toggleButtonG35.setBackground(Color.LIGHT_GRAY);
		toggleButtonG35.setBounds(177, 299, 15, 15);
		trackDisplayPanel.add(toggleButtonG35);
		greenLine[34] = toggleButtonG35;

		TrackButton toggleButtonG36 = new TrackButton("");
		toggleButtonG36.setBackground(Color.LIGHT_GRAY);
		toggleButtonG36.setBounds(205, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG36);
		greenLine[35] = toggleButtonG36;

		TrackButton toggleButtonG37 = new TrackButton("");
		toggleButtonG37.setBackground(Color.LIGHT_GRAY);
		toggleButtonG37.setBounds(218, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG37);
		greenLine[36] = toggleButtonG37;

		TrackButton toggleButtonG38 = new TrackButton("");
		toggleButtonG38.setBackground(Color.LIGHT_GRAY);
		toggleButtonG38.setBounds(230, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG38);
		greenLine[37] = toggleButtonG38;

		TrackButton toggleButtonG39 = new TrackButton("");
		toggleButtonG39.setBackground(Color.LIGHT_GRAY);
		toggleButtonG39.setBounds(242, 302, 20, 20);
		trackDisplayPanel.add(toggleButtonG39);
		greenLine[38] = toggleButtonG39;

		TrackButton toggleButtonG40 = new TrackButton("");
		toggleButtonG40.setBackground(Color.LIGHT_GRAY);
		toggleButtonG40.setBounds(260, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG40);
		greenLine[39] = toggleButtonG40;

		TrackButton toggleButtonG41 = new TrackButton("");
		toggleButtonG41.setBackground(Color.LIGHT_GRAY);
		toggleButtonG41.setBounds(271, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG41);
		greenLine[40] = toggleButtonG41;

		TrackButton toggleButtonG42 = new TrackButton("");
		toggleButtonG42.setBackground(Color.LIGHT_GRAY);
		toggleButtonG42.setBounds(295, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG42);
		greenLine[41] = toggleButtonG42;

		TrackButton toggleButtonG43 = new TrackButton("");
		toggleButtonG43.setBackground(Color.LIGHT_GRAY);
		toggleButtonG43.setBounds(307, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG43);
		greenLine[42] = toggleButtonG43;

		TrackButton toggleButtonG44 = new TrackButton("");
		toggleButtonG44.setBackground(Color.LIGHT_GRAY);
		toggleButtonG44.setBounds(319, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG44);
		greenLine[43] = toggleButtonG44;

		TrackButton toggleButtonG45 = new TrackButton("");
		toggleButtonG45.setBackground(Color.LIGHT_GRAY);
		toggleButtonG45.setBounds(344, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG45);
		greenLine[44] = toggleButtonG45;

		TrackButton toggleButtonG46 = new TrackButton("");
		toggleButtonG46.setBackground(Color.LIGHT_GRAY);
		toggleButtonG46.setBounds(357, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG46);
		greenLine[45] = toggleButtonG46;

		TrackButton toggleButtonG47 = new TrackButton("");
		toggleButtonG47.setBackground(Color.LIGHT_GRAY);
		toggleButtonG47.setBounds(370, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG47);
		greenLine[46] = toggleButtonG47;

		TrackButton toggleButtonG48 = new TrackButton("");
		toggleButtonG48.setBackground(Color.LIGHT_GRAY);
		toggleButtonG48.setBounds(383, 303, 20, 20);
		trackDisplayPanel.add(toggleButtonG48);
		greenLine[47] = toggleButtonG48;

		TrackButton toggleButtonG49 = new TrackButton("");
		toggleButtonG49.setBackground(Color.LIGHT_GRAY);
		toggleButtonG49.setBounds(403, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG49);
		greenLine[48] = toggleButtonG49;

		TrackButton toggleButtonG50 = new TrackButton("");
		toggleButtonG50.setBackground(Color.LIGHT_GRAY);
		toggleButtonG50.setBounds(416, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG50);
		greenLine[49] = toggleButtonG50;

		TrackButton toggleButtonG51 = new TrackButton("");
		toggleButtonG51.setBackground(Color.LIGHT_GRAY);
		toggleButtonG51.setBounds(428, 305, 15, 15);
		trackDisplayPanel.add(toggleButtonG51);
		greenLine[50] = toggleButtonG51;

		TrackButton toggleButtonG52 = new TrackButton("");
		toggleButtonG52.setBackground(Color.LIGHT_GRAY);
		toggleButtonG52.setBounds(441, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG52);
		greenLine[51] = toggleButtonG52;

		TrackButton toggleButtonG53 = new TrackButton("");
		toggleButtonG53.setBackground(Color.LIGHT_GRAY);
		toggleButtonG53.setBounds(453, 304, 15, 15);
		trackDisplayPanel.add(toggleButtonG53);
		greenLine[52] = toggleButtonG53;

		TrackButton toggleButtonG54 = new TrackButton("");
		toggleButtonG54.setBackground(Color.LIGHT_GRAY);
		toggleButtonG54.setBounds(467, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG54);
		greenLine[53] = toggleButtonG54;

		TrackButton toggleButtonG55 = new TrackButton("");
		toggleButtonG55.setBackground(Color.LIGHT_GRAY);
		toggleButtonG55.setBounds(480, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG55);
		greenLine[54] = toggleButtonG55;

		TrackButton toggleButtonG56 = new TrackButton("");
		toggleButtonG56.setBackground(Color.LIGHT_GRAY);
		toggleButtonG56.setBounds(494, 303, 15, 15);
		trackDisplayPanel.add(toggleButtonG56);
		greenLine[55] = toggleButtonG56;

		TrackButton toggleButtonG57 = new TrackButton("");
		toggleButtonG57.setBackground(Color.LIGHT_GRAY);
		toggleButtonG57.setBounds(508, 301, 20, 20);
		trackDisplayPanel.add(toggleButtonG57);
		greenLine[56] = toggleButtonG57;

		TrackButton toggleButtonG58 = new TrackButton("");
		toggleButtonG58.setBackground(Color.LIGHT_GRAY);
		toggleButtonG58.setBounds(546, 307, 15, 15);
		trackDisplayPanel.add(toggleButtonG58);
		greenLine[57] = toggleButtonG58;

		TrackButton toggleButtonG59 = new TrackButton("");
		toggleButtonG59.setBackground(Color.LIGHT_GRAY);
		toggleButtonG59.setBounds(579, 310, 15, 15);
		trackDisplayPanel.add(toggleButtonG59);
		greenLine[58] = toggleButtonG59;

		TrackButton toggleButtonG60 = new TrackButton("");
		toggleButtonG60.setBackground(Color.LIGHT_GRAY);
		toggleButtonG60.setBounds(602, 318, 15, 15);
		trackDisplayPanel.add(toggleButtonG60);
		greenLine[59] = toggleButtonG60;

		TrackButton toggleButtonG61 = new TrackButton("");
		toggleButtonG61.setBackground(Color.LIGHT_GRAY);
		toggleButtonG61.setBounds(635, 335, 15, 15);
		trackDisplayPanel.add(toggleButtonG61);
		greenLine[60] = toggleButtonG61;

		TrackButton toggleButtonG62 = new TrackButton("");
		toggleButtonG62.setBackground(Color.LIGHT_GRAY);
		toggleButtonG62.setBounds(656, 356, 15, 15);
		trackDisplayPanel.add(toggleButtonG62);
		greenLine[61] = toggleButtonG62;

		TrackButton toggleButtonG63 = new TrackButton("");
		toggleButtonG63.setBackground(Color.LIGHT_GRAY);
		toggleButtonG63.setBounds(676, 413, 15, 15);
		trackDisplayPanel.add(toggleButtonG63);
		greenLine[62] = toggleButtonG63;

		TrackButton toggleButtonG64 = new TrackButton("");
		toggleButtonG64.setBackground(Color.LIGHT_GRAY);
		toggleButtonG64.setBounds(676, 434, 15, 15);
		trackDisplayPanel.add(toggleButtonG64);
		greenLine[63] = toggleButtonG64;

		TrackButton toggleButtonG65 = new TrackButton("");
		toggleButtonG65.setBackground(Color.LIGHT_GRAY);
		toggleButtonG65.setBounds(673, 454, 20, 20);
		trackDisplayPanel.add(toggleButtonG65);
		greenLine[64] = toggleButtonG65;

		TrackButton toggleButtonG66 = new TrackButton("");
		toggleButtonG66.setBackground(Color.LIGHT_GRAY);
		toggleButtonG66.setBounds(675, 483, 15, 15);
		trackDisplayPanel.add(toggleButtonG66);
		greenLine[65] = toggleButtonG66;

		TrackButton toggleButtonG67 = new TrackButton("");
		toggleButtonG67.setBackground(Color.LIGHT_GRAY);
		toggleButtonG67.setBounds(675, 513, 15, 15);
		trackDisplayPanel.add(toggleButtonG67);
		greenLine[66] = toggleButtonG67;

		TrackButton toggleButtonG68 = new TrackButton("");
		toggleButtonG68.setBackground(Color.LIGHT_GRAY);
		toggleButtonG68.setBounds(676, 549, 15, 15);
		trackDisplayPanel.add(toggleButtonG68);
		greenLine[67] = toggleButtonG68;

		TrackButton toggleButtonG69 = new TrackButton("");
		toggleButtonG69.setBackground(Color.LIGHT_GRAY);
		toggleButtonG69.setBounds(671, 623, 15, 15);
		trackDisplayPanel.add(toggleButtonG69);
		greenLine[68] = toggleButtonG69;

		TrackButton toggleButtonG70 = new TrackButton("");
		toggleButtonG70.setBackground(Color.LIGHT_GRAY);
		toggleButtonG70.setBounds(660, 651, 15, 15);
		trackDisplayPanel.add(toggleButtonG70);
		greenLine[69] = toggleButtonG70;

		TrackButton toggleButtonG71 = new TrackButton("");
		toggleButtonG71.setBackground(Color.LIGHT_GRAY);
		toggleButtonG71.setBounds(646, 673, 15, 15);
		trackDisplayPanel.add(toggleButtonG71);
		greenLine[70] = toggleButtonG71;

		TrackButton toggleButtonG72 = new TrackButton("");
		toggleButtonG72.setBackground(Color.LIGHT_GRAY);
		toggleButtonG72.setBounds(621, 692, 15, 15);
		trackDisplayPanel.add(toggleButtonG72);
		greenLine[71] = toggleButtonG72;

		TrackButton toggleButtonG73 = new TrackButton("");
		toggleButtonG73.setBackground(Color.LIGHT_GRAY);
		toggleButtonG73.setBounds(591, 701, 20, 20);
		trackDisplayPanel.add(toggleButtonG73);
		greenLine[72] = toggleButtonG73;

		TrackButton toggleButtonG74 = new TrackButton("");
		toggleButtonG74.setBackground(Color.LIGHT_GRAY);
		toggleButtonG74.setBounds(538, 709, 15, 15);
		trackDisplayPanel.add(toggleButtonG74);
		greenLine[73] = toggleButtonG74;

		TrackButton toggleButtonG75 = new TrackButton("");
		toggleButtonG75.setBackground(Color.LIGHT_GRAY);
		toggleButtonG75.setBounds(467, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG75);
		greenLine[74] = toggleButtonG75;

		TrackButton toggleButtonG76 = new TrackButton("");
		toggleButtonG76.setBackground(Color.LIGHT_GRAY);
		toggleButtonG76.setBounds(401, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG76);
		greenLine[75] = toggleButtonG76;

		TrackButton toggleButtonG77 = new TrackButton("");
		toggleButtonG77.setBackground(Color.LIGHT_GRAY);
		toggleButtonG77.setBounds(349, 705, 20, 20);
		trackDisplayPanel.add(toggleButtonG77);
		greenLine[76] = toggleButtonG77;

		TrackButton toggleButtonG78 = new TrackButton("");
		toggleButtonG78.setBackground(Color.LIGHT_GRAY);
		toggleButtonG78.setBounds(334, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG78);
		greenLine[77] = toggleButtonG78;

		TrackButton toggleButtonG79 = new TrackButton("");
		toggleButtonG79.setBackground(Color.LIGHT_GRAY);
		toggleButtonG79.setBounds(318, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG79);
		greenLine[78] = toggleButtonG79;

		TrackButton toggleButtonG80 = new TrackButton("");
		toggleButtonG80.setBackground(Color.LIGHT_GRAY);
		toggleButtonG80.setBounds(303, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG80);
		greenLine[79] = toggleButtonG80;

		TrackButton toggleButtonG81 = new TrackButton("");
		toggleButtonG81.setBackground(Color.LIGHT_GRAY);
		toggleButtonG81.setBounds(286, 706, 15, 15);
		trackDisplayPanel.add(toggleButtonG81);
		greenLine[80] = toggleButtonG81;

		TrackButton toggleButtonG82 = new TrackButton("");
		toggleButtonG82.setBackground(Color.LIGHT_GRAY);
		toggleButtonG82.setBounds(271, 706, 15, 15);
		trackDisplayPanel.add(toggleButtonG82);
		greenLine[81] = toggleButtonG82;

		TrackButton toggleButtonG83 = new TrackButton("");
		toggleButtonG83.setBackground(Color.LIGHT_GRAY);
		toggleButtonG83.setBounds(258, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG83);
		greenLine[82] = toggleButtonG83;

		TrackButton toggleButtonG84 = new TrackButton("");
		toggleButtonG84.setBackground(Color.LIGHT_GRAY);
		toggleButtonG84.setBounds(244, 708, 15, 15);
		trackDisplayPanel.add(toggleButtonG84);
		greenLine[83] = toggleButtonG84;

		TrackButton toggleButtonG85 = new TrackButton("");
		toggleButtonG85.setBackground(Color.LIGHT_GRAY);
		toggleButtonG85.setBounds(228, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG85);
		greenLine[84] = toggleButtonG85;

		TrackButton toggleButtonG86 = new TrackButton("");
		toggleButtonG86.setBackground(Color.LIGHT_GRAY);
		toggleButtonG86.setBounds(178, 707, 15, 15);
		trackDisplayPanel.add(toggleButtonG86);
		greenLine[85] = toggleButtonG86;

		TrackButton toggleButtonG87 = new TrackButton("");
		toggleButtonG87.setBackground(Color.LIGHT_GRAY);
		toggleButtonG87.setBounds(146, 706, 15, 15);
		trackDisplayPanel.add(toggleButtonG87);
		greenLine[86] = toggleButtonG87;

		TrackButton toggleButtonG88 = new TrackButton("");
		toggleButtonG88.setBackground(Color.LIGHT_GRAY);
		toggleButtonG88.setBounds(121, 704, 20, 20);
		trackDisplayPanel.add(toggleButtonG88);
		greenLine[87] = toggleButtonG88;

		TrackButton toggleButtonG89 = new TrackButton("");
		toggleButtonG89.setBackground(Color.LIGHT_GRAY);
		toggleButtonG89.setBounds(91, 705, 15, 15);
		trackDisplayPanel.add(toggleButtonG89);
		greenLine[88] = toggleButtonG89;

		TrackButton toggleButtonG90 = new TrackButton("");
		toggleButtonG90.setBackground(Color.LIGHT_GRAY);
		toggleButtonG90.setBounds(73, 689, 15, 15);
		trackDisplayPanel.add(toggleButtonG90);
		greenLine[89] = toggleButtonG90;

		TrackButton toggleButtonG91 = new TrackButton("");
		toggleButtonG91.setBackground(Color.LIGHT_GRAY);
		toggleButtonG91.setBounds(62, 674, 15, 15);
		trackDisplayPanel.add(toggleButtonG91);
		greenLine[90] = toggleButtonG91;

		TrackButton toggleButtonG92 = new TrackButton("");
		toggleButtonG92.setBackground(Color.LIGHT_GRAY);
		toggleButtonG92.setBounds(58, 659, 15, 15);
		trackDisplayPanel.add(toggleButtonG92);
		greenLine[91] = toggleButtonG92;

		TrackButton toggleButtonG93 = new TrackButton("");
		toggleButtonG93.setBackground(Color.LIGHT_GRAY);
		toggleButtonG93.setBounds(58, 639, 15, 15);
		trackDisplayPanel.add(toggleButtonG93);
		greenLine[92] = toggleButtonG93;

		TrackButton toggleButtonG94 = new TrackButton("");
		toggleButtonG94.setBackground(Color.LIGHT_GRAY);
		toggleButtonG94.setBounds(64, 621, 15, 15);
		trackDisplayPanel.add(toggleButtonG94);
		greenLine[93] = toggleButtonG94;

		TrackButton toggleButtonG95 = new TrackButton("");
		toggleButtonG95.setBackground(Color.LIGHT_GRAY);
		toggleButtonG95.setBounds(79, 599, 15, 15);
		trackDisplayPanel.add(toggleButtonG95);
		greenLine[94] = toggleButtonG95;

		TrackButton toggleButtonG96 = new TrackButton("");
		toggleButtonG96.setBackground(Color.LIGHT_GRAY);
		toggleButtonG96.setBounds(111, 593, 20, 20);
		trackDisplayPanel.add(toggleButtonG96);
		greenLine[95] = toggleButtonG96;

		TrackButton toggleButtonG97 = new TrackButton("");
		toggleButtonG97.setBackground(Color.LIGHT_GRAY);
		toggleButtonG97.setBounds(139, 621, 15, 15);
		trackDisplayPanel.add(toggleButtonG97);
		greenLine[96] = toggleButtonG97;

		TrackButton toggleButtonG98 = new TrackButton("");
		toggleButtonG98.setBackground(Color.LIGHT_GRAY);
		toggleButtonG98.setBounds(146, 665, 15, 15);
		trackDisplayPanel.add(toggleButtonG98);
		greenLine[97] = toggleButtonG98;

		TrackButton toggleButtonG99 = new TrackButton("");
		toggleButtonG99.setBackground(Color.LIGHT_GRAY);
		toggleButtonG99.setBounds(156, 683, 15, 15);
		trackDisplayPanel.add(toggleButtonG99);
		greenLine[98] = toggleButtonG99;

		TrackButton toggleButtonG100 = new TrackButton("");
		toggleButtonG100.setBackground(Color.LIGHT_GRAY);
		toggleButtonG100.setBounds(175, 694, 15, 15);
		trackDisplayPanel.add(toggleButtonG100);
		greenLine[99] = toggleButtonG100;

		TrackButton toggleButtonG101 = new TrackButton("");
		toggleButtonG101.setBackground(Color.LIGHT_GRAY);
		toggleButtonG101.setBounds(395, 682, 15, 15);
		trackDisplayPanel.add(toggleButtonG101);
		greenLine[100] = toggleButtonG101;

		TrackButton toggleButtonG102 = new TrackButton("");
		toggleButtonG102.setBackground(Color.LIGHT_GRAY);
		toggleButtonG102.setBounds(432, 660, 15, 15);
		trackDisplayPanel.add(toggleButtonG102);
		greenLine[101] = toggleButtonG102;

		TrackButton toggleButtonG103 = new TrackButton("");
		toggleButtonG103.setBackground(Color.LIGHT_GRAY);
		toggleButtonG103.setBounds(457, 660, 15, 15);
		trackDisplayPanel.add(toggleButtonG103);
		greenLine[102] = toggleButtonG103;

		TrackButton toggleButtonG104 = new TrackButton("");
		toggleButtonG104.setBackground(Color.LIGHT_GRAY);
		toggleButtonG104.setBounds(481, 662, 15, 15);
		trackDisplayPanel.add(toggleButtonG104);
		greenLine[103] = toggleButtonG104;

		TrackButton toggleButtonG105 = new TrackButton("");
		toggleButtonG105.setBackground(Color.LIGHT_GRAY);
		toggleButtonG105.setBounds(524, 660, 20, 20);
		trackDisplayPanel.add(toggleButtonG105);
		greenLine[104] = toggleButtonG105;

		TrackButton toggleButtonG106 = new TrackButton("");
		toggleButtonG106.setBackground(Color.LIGHT_GRAY);
		toggleButtonG106.setBounds(554, 655, 15, 15);
		trackDisplayPanel.add(toggleButtonG106);
		greenLine[105] = toggleButtonG106;

		TrackButton toggleButtonG107 = new TrackButton("");
		toggleButtonG107.setBackground(Color.LIGHT_GRAY);
		toggleButtonG107.setBounds(587, 635, 15, 15);
		trackDisplayPanel.add(toggleButtonG107);
		greenLine[106] = toggleButtonG107;

		TrackButton toggleButtonG108 = new TrackButton("");
		toggleButtonG108.setBackground(Color.LIGHT_GRAY);
		toggleButtonG108.setBounds(605, 617, 15, 15);
		trackDisplayPanel.add(toggleButtonG108);
		greenLine[107] = toggleButtonG108;

		TrackButton toggleButtonG109 = new TrackButton("");
		toggleButtonG109.setBackground(Color.LIGHT_GRAY);
		toggleButtonG109.setBounds(625, 584, 15, 15);
		trackDisplayPanel.add(toggleButtonG109);
		greenLine[108] = toggleButtonG109;

		TrackButton toggleButtonG110 = new TrackButton("");
		toggleButtonG110.setBackground(Color.LIGHT_GRAY);
		toggleButtonG110.setBounds(631, 543, 15, 15);
		trackDisplayPanel.add(toggleButtonG110);
		greenLine[109] = toggleButtonG110;

		TrackButton toggleButtonG111 = new TrackButton("");
		toggleButtonG111.setBackground(Color.LIGHT_GRAY);
		toggleButtonG111.setBounds(632, 529, 15, 15);
		trackDisplayPanel.add(toggleButtonG111);
		greenLine[110] = toggleButtonG111;

		TrackButton toggleButtonG112 = new TrackButton("");
		toggleButtonG112.setBackground(Color.LIGHT_GRAY);
		toggleButtonG112.setBounds(631, 513, 15, 15);
		trackDisplayPanel.add(toggleButtonG112);
		greenLine[111] = toggleButtonG112;

		TrackButton toggleButtonG113 = new TrackButton("");
		toggleButtonG113.setBackground(Color.LIGHT_GRAY);
		toggleButtonG113.setBounds(631, 499, 15, 15);
		trackDisplayPanel.add(toggleButtonG113);
		greenLine[112] = toggleButtonG113;

		TrackButton toggleButtonG114 = new TrackButton("");
		toggleButtonG114.setBackground(Color.LIGHT_GRAY);
		toggleButtonG114.setBounds(631, 482, 20, 20);
		trackDisplayPanel.add(toggleButtonG114);
		greenLine[113] = toggleButtonG114;

		TrackButton toggleButtonG115 = new TrackButton("");
		toggleButtonG115.setBackground(Color.LIGHT_GRAY);
		toggleButtonG115.setBounds(631, 468, 15, 15);
		trackDisplayPanel.add(toggleButtonG115);
		greenLine[114] = toggleButtonG115;

		TrackButton toggleButtonG116 = new TrackButton("");
		toggleButtonG116.setBackground(Color.LIGHT_GRAY);
		toggleButtonG116.setBounds(631, 454, 15, 15);
		trackDisplayPanel.add(toggleButtonG116);
		greenLine[115] = toggleButtonG116;

		TrackButton toggleButtonG117 = new TrackButton("");
		toggleButtonG117.setBackground(Color.LIGHT_GRAY);
		toggleButtonG117.setBounds(628, 420, 15, 15);
		trackDisplayPanel.add(toggleButtonG117);
		greenLine[116] = toggleButtonG117;

		TrackButton toggleButtonG118 = new TrackButton("");
		toggleButtonG118.setBackground(Color.LIGHT_GRAY);
		toggleButtonG118.setBounds(618, 400, 15, 15);
		trackDisplayPanel.add(toggleButtonG118);
		greenLine[117] = toggleButtonG118;

		TrackButton toggleButtonG119 = new TrackButton("");
		toggleButtonG119.setBackground(Color.LIGHT_GRAY);
		toggleButtonG119.setBounds(601, 381, 15, 15);
		trackDisplayPanel.add(toggleButtonG119);
		greenLine[118] = toggleButtonG119;

		TrackButton toggleButtonG120 = new TrackButton("");
		toggleButtonG120.setBackground(Color.LIGHT_GRAY);
		toggleButtonG120.setBounds(576, 362, 15, 15);
		trackDisplayPanel.add(toggleButtonG120);
		greenLine[119] = toggleButtonG120;

		TrackButton toggleButtonG121 = new TrackButton("");
		toggleButtonG121.setBackground(Color.LIGHT_GRAY);
		toggleButtonG121.setBounds(526, 349, 15, 15);
		trackDisplayPanel.add(toggleButtonG121);
		greenLine[120] = toggleButtonG121;

		TrackButton toggleButtonG122 = new TrackButton("");
		toggleButtonG122.setBackground(Color.LIGHT_GRAY);
		toggleButtonG122.setBounds(484, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG122);
		greenLine[121] = toggleButtonG122;

		TrackButton toggleButtonG123 = new TrackButton("");
		toggleButtonG123.setBackground(Color.LIGHT_GRAY);
		toggleButtonG123.setBounds(466, 342, 20, 20);
		trackDisplayPanel.add(toggleButtonG123);
		greenLine[122] = toggleButtonG123;

		TrackButton toggleButtonG124 = new TrackButton("");
		toggleButtonG124.setBackground(Color.LIGHT_GRAY);
		toggleButtonG124.setBounds(454, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG124);
		greenLine[123] = toggleButtonG124;

		TrackButton toggleButtonG125 = new TrackButton("");
		toggleButtonG125.setBackground(Color.LIGHT_GRAY);
		toggleButtonG125.setBounds(441, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG125);
		greenLine[124] = toggleButtonG125;

		TrackButton toggleButtonG126 = new TrackButton("");
		toggleButtonG126.setBackground(Color.LIGHT_GRAY);
		toggleButtonG126.setBounds(428, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG126);
		greenLine[125] = toggleButtonG126;

		TrackButton toggleButtonG127 = new TrackButton("");
		toggleButtonG127.setBackground(Color.LIGHT_GRAY);
		toggleButtonG127.setBounds(416, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG127);
		greenLine[126] = toggleButtonG127;

		TrackButton toggleButtonG128 = new TrackButton("");
		toggleButtonG128.setBackground(Color.LIGHT_GRAY);
		toggleButtonG128.setBounds(406, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG128);
		greenLine[127] = toggleButtonG128;

		TrackButton toggleButtonG129 = new TrackButton("");
		toggleButtonG129.setBackground(Color.LIGHT_GRAY);
		toggleButtonG129.setBounds(394, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG129);
		greenLine[128] = toggleButtonG129;

		TrackButton toggleButtonG130 = new TrackButton("");
		toggleButtonG130.setBackground(Color.LIGHT_GRAY);
		toggleButtonG130.setBounds(383, 345, 15, 15);
		trackDisplayPanel.add(toggleButtonG130);
		greenLine[129] = toggleButtonG130;

		TrackButton toggleButtonG131 = new TrackButton("");
		toggleButtonG131.setBackground(Color.LIGHT_GRAY);
		toggleButtonG131.setBounds(371, 345, 15, 15);
		trackDisplayPanel.add(toggleButtonG131);
		greenLine[130] = toggleButtonG131;

		TrackButton toggleButtonG132 = new TrackButton("");
		toggleButtonG132.setBackground(Color.LIGHT_GRAY);
		toggleButtonG132.setBounds(355, 342, 20, 20);
		trackDisplayPanel.add(toggleButtonG132);
		greenLine[131] = toggleButtonG132;

		TrackButton toggleButtonG133 = new TrackButton("");
		toggleButtonG133.setBackground(Color.LIGHT_GRAY);
		toggleButtonG133.setBounds(343, 345, 15, 15);
		trackDisplayPanel.add(toggleButtonG133);
		greenLine[132] = toggleButtonG133;

		TrackButton toggleButtonG134 = new TrackButton("");
		toggleButtonG134.setBackground(Color.LIGHT_GRAY);
		toggleButtonG134.setBounds(317, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG134);
		greenLine[133] = toggleButtonG134;

		TrackButton toggleButtonG135 = new TrackButton("");
		toggleButtonG135.setBackground(Color.LIGHT_GRAY);
		toggleButtonG135.setBounds(304, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG135);
		greenLine[134] = toggleButtonG135;

		TrackButton toggleButtonG136 = new TrackButton("");
		toggleButtonG136.setBackground(Color.LIGHT_GRAY);
		toggleButtonG136.setBounds(290, 342, 15, 15);
		trackDisplayPanel.add(toggleButtonG136);
		greenLine[135] = toggleButtonG136;

		TrackButton toggleButtonG137 = new TrackButton("");
		toggleButtonG137.setBackground(Color.LIGHT_GRAY);
		toggleButtonG137.setBounds(276, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG137);
		greenLine[136] = toggleButtonG137;

		TrackButton toggleButtonG138 = new TrackButton("");
		toggleButtonG138.setBackground(Color.LIGHT_GRAY);
		toggleButtonG138.setBounds(262, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG138);
		greenLine[137] = toggleButtonG138;

		TrackButton toggleButtonG139 = new TrackButton("");
		toggleButtonG139.setBackground(Color.LIGHT_GRAY);
		toggleButtonG139.setBounds(247, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG139);
		greenLine[138] = toggleButtonG139;

		TrackButton toggleButtonG140 = new TrackButton("");
		toggleButtonG140.setBackground(Color.LIGHT_GRAY);
		toggleButtonG140.setBounds(232, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG140);
		greenLine[139] = toggleButtonG140;

		TrackButton toggleButtonG141 = new TrackButton("");
		toggleButtonG141.setBackground(Color.LIGHT_GRAY);
		toggleButtonG141.setBounds(214, 340, 20, 20);
		trackDisplayPanel.add(toggleButtonG141);
		greenLine[140] = toggleButtonG141;

		TrackButton toggleButtonG142 = new TrackButton("");
		toggleButtonG142.setBackground(Color.LIGHT_GRAY);
		toggleButtonG142.setBounds(199, 343, 15, 15);
		trackDisplayPanel.add(toggleButtonG142);
		greenLine[141] = toggleButtonG142;

		TrackButton toggleButtonG143 = new TrackButton("");
		toggleButtonG143.setBackground(Color.LIGHT_GRAY);
		toggleButtonG143.setBounds(180, 344, 15, 15);
		trackDisplayPanel.add(toggleButtonG143);
		greenLine[142] = toggleButtonG143;

		TrackButton toggleButtonG144 = new TrackButton("");
		toggleButtonG144.setBackground(Color.LIGHT_GRAY);
		toggleButtonG144.setBounds(140, 340, 15, 15);
		trackDisplayPanel.add(toggleButtonG144);
		greenLine[143] = toggleButtonG144;

		TrackButton toggleButtonG145 = new TrackButton("");
		toggleButtonG145.setBackground(Color.LIGHT_GRAY);
		toggleButtonG145.setBounds(109, 327, 15, 15);
		trackDisplayPanel.add(toggleButtonG145);
		greenLine[144] = toggleButtonG145;

		TrackButton toggleButtonG146 = new TrackButton("");
		toggleButtonG146.setBackground(Color.LIGHT_GRAY);
		toggleButtonG146.setBounds(92, 306, 15, 15);
		trackDisplayPanel.add(toggleButtonG146);
		greenLine[145] = toggleButtonG146;

		TrackButton toggleButtonG147 = new TrackButton("");
		toggleButtonG147.setBackground(Color.LIGHT_GRAY);
		toggleButtonG147.setBounds(90, 280, 15, 15);
		trackDisplayPanel.add(toggleButtonG147);
		greenLine[146] = toggleButtonG147;

		TrackButton toggleButtonG148 = new TrackButton("");
		toggleButtonG148.setBackground(Color.LIGHT_GRAY);
		toggleButtonG148.setBounds(90, 265, 15, 15);
		trackDisplayPanel.add(toggleButtonG148);
		greenLine[147] = toggleButtonG148;

		TrackButton toggleButtonG149 = new TrackButton("");
		toggleButtonG149.setBackground(Color.LIGHT_GRAY);
		toggleButtonG149.setBounds(90, 249, 15, 15);
		trackDisplayPanel.add(toggleButtonG149);
		greenLine[148] = toggleButtonG149;

		TrackButton toggleButtonG150 = new TrackButton("");
		toggleButtonG150.setBackground(Color.LIGHT_GRAY);
		toggleButtonG150.setBounds(108, 219, 15, 15);
		trackDisplayPanel.add(toggleButtonG150);
		greenLine[149] = toggleButtonG150;

		TrackButton toggleButtonG151 = new TrackButton("");
		toggleButtonG151.setBackground(Color.LIGHT_GRAY);
		toggleButtonG151.setBounds(604, 292, 15, 15);
		trackDisplayPanel.add(toggleButtonG151);
		greenLine[150] = toggleButtonG151;

		TrackButton toggleButtonG152 = new TrackButton("");
		toggleButtonG152.setBackground(Color.LIGHT_GRAY);
		toggleButtonG152.setBounds(672, 323, 15, 15);
		trackDisplayPanel.add(toggleButtonG152);
		greenLine[151] = toggleButtonG152;

		TrackButton toggleButtonR1 = new TrackButton("");
		toggleButtonR1.setBackground(Color.LIGHT_GRAY);
		toggleButtonR1.setBounds(500, 179, 15, 15);
		trackDisplayPanel.add(toggleButtonR1);
		redLine[0] = toggleButtonR1;

		TrackButton toggleButtonR2 = new TrackButton("");
		toggleButtonR2.setBackground(Color.LIGHT_GRAY);
		toggleButtonR2.setBounds(522, 175, 15, 15);
		trackDisplayPanel.add(toggleButtonR2);
		redLine[1] = toggleButtonR2;

		TrackButton toggleButtonR3 = new TrackButton("");
		toggleButtonR3.setBackground(Color.LIGHT_GRAY);
		toggleButtonR3.setBounds(537, 168, 15, 15);
		trackDisplayPanel.add(toggleButtonR3);
		redLine[2] = toggleButtonR3;

		TrackButton toggleButtonR4 = new TrackButton("");
		toggleButtonR4.setBackground(Color.LIGHT_GRAY);
		toggleButtonR4.setBounds(549, 137, 15, 15);
		trackDisplayPanel.add(toggleButtonR4);
		redLine[3] = toggleButtonR4;

		TrackButton toggleButtonR5 = new TrackButton("");
		toggleButtonR5.setBackground(Color.LIGHT_GRAY);
		toggleButtonR5.setBounds(569, 124, 15, 15);
		trackDisplayPanel.add(toggleButtonR5);
		redLine[4] = toggleButtonR5;

		TrackButton toggleButtonR6 = new TrackButton("");
		toggleButtonR6.setBackground(Color.LIGHT_GRAY);
		toggleButtonR6.setBounds(591, 115, 15, 15);
		trackDisplayPanel.add(toggleButtonR6);
		redLine[5] = toggleButtonR6;

		TrackButton toggleButtonR7 = new TrackButton("");
		toggleButtonR7.setBackground(Color.LIGHT_GRAY);
		toggleButtonR7.setBounds(637, 108, 20, 20);
		trackDisplayPanel.add(toggleButtonR7);
		redLine[6] = toggleButtonR7;

		TrackButton toggleButtonR8 = new TrackButton("");
		toggleButtonR8.setBackground(Color.LIGHT_GRAY);
		toggleButtonR8.setBounds(665, 121, 15, 15);
		trackDisplayPanel.add(toggleButtonR8);
		redLine[7] = toggleButtonR8;

		TrackButton toggleButtonR9 = new TrackButton("");
		toggleButtonR9.setBackground(Color.LIGHT_GRAY);
		toggleButtonR9.setBounds(691, 133, 15, 15);
		trackDisplayPanel.add(toggleButtonR9);
		redLine[8] = toggleButtonR9;

		TrackButton toggleButtonR10 = new TrackButton("");
		toggleButtonR10.setBackground(Color.LIGHT_GRAY);
		toggleButtonR10.setBounds(684, 176, 15, 15);
		trackDisplayPanel.add(toggleButtonR10);
		redLine[9] = toggleButtonR10;

		TrackButton toggleButtonR11 = new TrackButton("");
		toggleButtonR11.setBackground(Color.LIGHT_GRAY);
		toggleButtonR11.setBounds(656, 186, 15, 15);
		trackDisplayPanel.add(toggleButtonR11);
		redLine[10] = toggleButtonR11;

		TrackButton toggleButtonR12 = new TrackButton("");
		toggleButtonR12.setBackground(Color.LIGHT_GRAY);
		toggleButtonR12.setBounds(618, 190, 15, 15);
		trackDisplayPanel.add(toggleButtonR12);
		redLine[11] = toggleButtonR12;

		TrackButton toggleButtonR13 = new TrackButton("");
		toggleButtonR13.setBackground(Color.LIGHT_GRAY);
		toggleButtonR13.setBounds(549, 193, 15, 15);
		trackDisplayPanel.add(toggleButtonR13);
		redLine[12] = toggleButtonR13;

		TrackButton toggleButtonR14 = new TrackButton("");
		toggleButtonR14.setBackground(Color.LIGHT_GRAY);
		toggleButtonR14.setBounds(524, 192, 15, 15);
		trackDisplayPanel.add(toggleButtonR14);
		redLine[13] = toggleButtonR14;

		TrackButton toggleButtonR15 = new TrackButton("");
		toggleButtonR15.setBackground(Color.LIGHT_GRAY);
		toggleButtonR15.setBounds(498, 193, 15, 15);
		trackDisplayPanel.add(toggleButtonR15);
		redLine[14] = toggleButtonR15;

		TrackButton toggleButtonR16 = new TrackButton("");
		toggleButtonR16.setBackground(Color.LIGHT_GRAY);
		toggleButtonR16.setBounds(456, 191, 20, 20);
		trackDisplayPanel.add(toggleButtonR16);
		redLine[15] = toggleButtonR16;

		TrackButton toggleButtonR17 = new TrackButton("");
		toggleButtonR17.setBackground(Color.LIGHT_GRAY);
		toggleButtonR17.setBounds(444, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR17);
		redLine[16] = toggleButtonR17;

		TrackButton toggleButtonR18 = new TrackButton("");
		toggleButtonR18.setBackground(Color.LIGHT_GRAY);
		toggleButtonR18.setBounds(432, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR18);
		redLine[17] = toggleButtonR18;

		TrackButton toggleButtonR19 = new TrackButton("");
		toggleButtonR19.setBackground(Color.LIGHT_GRAY);
		toggleButtonR19.setBounds(420, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR19);
		redLine[18] = toggleButtonR19;

		TrackButton toggleButtonR20 = new TrackButton("");
		toggleButtonR20.setBackground(Color.LIGHT_GRAY);
		toggleButtonR20.setBounds(408, 191, 15, 15);
		trackDisplayPanel.add(toggleButtonR20);
		redLine[19] = toggleButtonR20;

		TrackButton toggleButtonR21 = new TrackButton("");
		toggleButtonR21.setBackground(Color.LIGHT_GRAY);
		toggleButtonR21.setBounds(373, 194, 20, 20);
		trackDisplayPanel.add(toggleButtonR21);
		redLine[20] = toggleButtonR21;

		TrackButton toggleButtonR22 = new TrackButton("");
		toggleButtonR22.setBackground(Color.LIGHT_GRAY);
		toggleButtonR22.setBounds(359, 201, 15, 15);
		trackDisplayPanel.add(toggleButtonR22);
		redLine[21] = toggleButtonR22;

		TrackButton toggleButtonR23 = new TrackButton("");
		toggleButtonR23.setBackground(Color.LIGHT_GRAY);
		toggleButtonR23.setBounds(346, 207, 15, 15);
		trackDisplayPanel.add(toggleButtonR23);
		redLine[22] = toggleButtonR23;

		TrackButton toggleButtonR24 = new TrackButton("");
		toggleButtonR24.setBackground(Color.LIGHT_GRAY);
		toggleButtonR24.setBounds(330, 222, 15, 15);
		trackDisplayPanel.add(toggleButtonR24);
		redLine[23] = toggleButtonR24;

		TrackButton toggleButtonR25 = new TrackButton("");
		toggleButtonR25.setBackground(Color.LIGHT_GRAY);
		toggleButtonR25.setBounds(329, 233, 20, 20);
		trackDisplayPanel.add(toggleButtonR25);
		redLine[24] = toggleButtonR25;

		TrackButton toggleButtonR26 = new TrackButton("");
		toggleButtonR26.setBackground(Color.LIGHT_GRAY);
		toggleButtonR26.setBounds(330, 250, 15, 15);
		trackDisplayPanel.add(toggleButtonR26);
		redLine[25] = toggleButtonR26;

		TrackButton toggleButtonR27 = new TrackButton("");
		toggleButtonR27.setBackground(Color.LIGHT_GRAY);
		toggleButtonR27.setBounds(330, 262, 15, 15);
		trackDisplayPanel.add(toggleButtonR27);
		redLine[26] = toggleButtonR27;

		TrackButton toggleButtonR28 = new TrackButton("");
		toggleButtonR28.setBackground(Color.LIGHT_GRAY);
		toggleButtonR28.setBounds(330, 274, 15, 15);
		trackDisplayPanel.add(toggleButtonR28);
		redLine[27] = toggleButtonR28;

		TrackButton toggleButtonR29 = new TrackButton("");
		toggleButtonR29.setBackground(Color.LIGHT_GRAY);
		toggleButtonR29.setBounds(330, 286, 15, 15);
		trackDisplayPanel.add(toggleButtonR29);
		redLine[28] = toggleButtonR29;

		TrackButton toggleButtonR30 = new TrackButton("");
		toggleButtonR30.setBackground(Color.LIGHT_GRAY);
		toggleButtonR30.setBounds(330, 298, 15, 15);
		trackDisplayPanel.add(toggleButtonR30);
		redLine[29] = toggleButtonR30;

		TrackButton toggleButtonR31 = new TrackButton("");
		toggleButtonR31.setBackground(Color.LIGHT_GRAY);
		toggleButtonR31.setBounds(331, 314, 15, 15);
		trackDisplayPanel.add(toggleButtonR31);
		redLine[30] = toggleButtonR31;

		TrackButton toggleButtonR32 = new TrackButton("");
		toggleButtonR32.setBackground(Color.LIGHT_GRAY);
		toggleButtonR32.setBounds(331, 326, 15, 15);
		trackDisplayPanel.add(toggleButtonR32);
		redLine[31] = toggleButtonR32;

		TrackButton toggleButtonR33 = new TrackButton("");
		toggleButtonR33.setBackground(Color.LIGHT_GRAY);
		toggleButtonR33.setBounds(330, 338, 15, 15);
		trackDisplayPanel.add(toggleButtonR33);
		redLine[32] = toggleButtonR33;

		TrackButton toggleButtonR34 = new TrackButton("");
		toggleButtonR34.setBackground(Color.LIGHT_GRAY);
		toggleButtonR34.setBounds(330, 351, 15, 15);
		trackDisplayPanel.add(toggleButtonR34);
		redLine[33] = toggleButtonR34;

		TrackButton toggleButtonR35 = new TrackButton("");
		toggleButtonR35.setBackground(Color.LIGHT_GRAY);
		toggleButtonR35.setBounds(329, 363, 20, 20);
		trackDisplayPanel.add(toggleButtonR35);
		redLine[34] = toggleButtonR35;

		TrackButton toggleButtonR36 = new TrackButton("");
		toggleButtonR36.setBackground(Color.LIGHT_GRAY);
		toggleButtonR36.setBounds(330, 380, 15, 15);
		trackDisplayPanel.add(toggleButtonR36);
		redLine[35] = toggleButtonR36;

		TrackButton toggleButtonR37 = new TrackButton("");
		toggleButtonR37.setBackground(Color.LIGHT_GRAY);
		toggleButtonR37.setBounds(330, 391, 15, 15);
		trackDisplayPanel.add(toggleButtonR37);
		redLine[36] = toggleButtonR37;

		TrackButton toggleButtonR38 = new TrackButton("");
		toggleButtonR38.setBackground(Color.LIGHT_GRAY);
		toggleButtonR38.setBounds(330, 402, 15, 15);
		trackDisplayPanel.add(toggleButtonR38);
		redLine[37] = toggleButtonR38;

		TrackButton toggleButtonR39 = new TrackButton("");
		toggleButtonR39.setBackground(Color.LIGHT_GRAY);
		toggleButtonR39.setBounds(330, 413, 15, 15);
		trackDisplayPanel.add(toggleButtonR39);
		redLine[38] = toggleButtonR39;

		TrackButton toggleButtonR40 = new TrackButton("");
		toggleButtonR40.setBackground(Color.LIGHT_GRAY);
		toggleButtonR40.setBounds(331, 425, 15, 15);
		trackDisplayPanel.add(toggleButtonR40);
		redLine[39] = toggleButtonR40;

		TrackButton toggleButtonR41 = new TrackButton("");
		toggleButtonR41.setBackground(Color.LIGHT_GRAY);
		toggleButtonR41.setBounds(331, 436, 15, 15);
		trackDisplayPanel.add(toggleButtonR41);
		redLine[40] = toggleButtonR41;

		TrackButton toggleButtonR42 = new TrackButton("");
		toggleButtonR42.setBackground(Color.LIGHT_GRAY);
		toggleButtonR42.setBounds(331, 447, 15, 15);
		trackDisplayPanel.add(toggleButtonR42);
		redLine[41] = toggleButtonR42;

		TrackButton toggleButtonR43 = new TrackButton("");
		toggleButtonR43.setBackground(Color.LIGHT_GRAY);
		toggleButtonR43.setBounds(331, 459, 15, 15);
		trackDisplayPanel.add(toggleButtonR43);
		redLine[42] = toggleButtonR43;

		TrackButton toggleButtonR44 = new TrackButton("");
		toggleButtonR44.setBackground(Color.LIGHT_GRAY);
		toggleButtonR44.setBounds(331, 470, 15, 15);
		trackDisplayPanel.add(toggleButtonR44);
		redLine[43] = toggleButtonR44;

		TrackButton toggleButtonR45 = new TrackButton("");
		toggleButtonR45.setBackground(Color.LIGHT_GRAY);
		toggleButtonR45.setBounds(329, 482, 20, 20);
		trackDisplayPanel.add(toggleButtonR45);
		redLine[44] = toggleButtonR45;

		TrackButton toggleButtonR46 = new TrackButton("");
		toggleButtonR46.setBackground(Color.LIGHT_GRAY);
		toggleButtonR46.setBounds(318, 527, 15, 15);
		trackDisplayPanel.add(toggleButtonR46);
		redLine[45] = toggleButtonR46;

		TrackButton toggleButtonR47 = new TrackButton("");
		toggleButtonR47.setBackground(Color.LIGHT_GRAY);
		toggleButtonR47.setBounds(293, 547, 15, 15);
		trackDisplayPanel.add(toggleButtonR47);
		redLine[46] = toggleButtonR47;

		TrackButton toggleButtonR48 = new TrackButton("");
		toggleButtonR48.setBackground(Color.LIGHT_GRAY);
		toggleButtonR48.setBounds(263, 557, 20, 20);
		trackDisplayPanel.add(toggleButtonR48);
		redLine[47] = toggleButtonR48;

		TrackButton toggleButtonR49 = new TrackButton("");
		toggleButtonR49.setBackground(Color.LIGHT_GRAY);
		toggleButtonR49.setBounds(223, 561, 15, 15);
		trackDisplayPanel.add(toggleButtonR49);
		redLine[48] = toggleButtonR49;

		TrackButton toggleButtonR50 = new TrackButton("");
		toggleButtonR50.setBackground(Color.LIGHT_GRAY);
		toggleButtonR50.setBounds(208, 562, 15, 15);
		trackDisplayPanel.add(toggleButtonR50);
		redLine[49] = toggleButtonR50;

		TrackButton toggleButtonR51 = new TrackButton("");
		toggleButtonR51.setBackground(Color.LIGHT_GRAY);
		toggleButtonR51.setBounds(192, 561, 15, 15);
		trackDisplayPanel.add(toggleButtonR51);
		redLine[50] = toggleButtonR51;

		TrackButton toggleButtonR52 = new TrackButton("");
		toggleButtonR52.setBackground(Color.LIGHT_GRAY);
		toggleButtonR52.setBounds(152, 562, 15, 15);
		trackDisplayPanel.add(toggleButtonR52);
		redLine[51] = toggleButtonR52;

		TrackButton toggleButtonR53 = new TrackButton("");
		toggleButtonR53.setBackground(Color.LIGHT_GRAY);
		toggleButtonR53.setBounds(132, 561, 15, 15);
		trackDisplayPanel.add(toggleButtonR53);
		redLine[52] = toggleButtonR53;

		TrackButton toggleButtonR54 = new TrackButton("");
		toggleButtonR54.setBackground(Color.LIGHT_GRAY);
		toggleButtonR54.setBounds(114, 559, 15, 15);
		trackDisplayPanel.add(toggleButtonR54);
		redLine[53] = toggleButtonR54;

		TrackButton toggleButtonR55 = new TrackButton("");
		toggleButtonR55.setBackground(Color.LIGHT_GRAY);
		toggleButtonR55.setBounds(73, 554, 15, 15);
		trackDisplayPanel.add(toggleButtonR55);
		redLine[54] = toggleButtonR55;

		TrackButton toggleButtonR56 = new TrackButton("");
		toggleButtonR56.setBackground(Color.LIGHT_GRAY);
		toggleButtonR56.setBounds(42, 536, 15, 15);
		trackDisplayPanel.add(toggleButtonR56);
		redLine[55] = toggleButtonR56;

		TrackButton toggleButtonR57 = new TrackButton("");
		toggleButtonR57.setBackground(Color.LIGHT_GRAY);
		toggleButtonR57.setBounds(22, 499, 15, 15);
		trackDisplayPanel.add(toggleButtonR57);
		redLine[56] = toggleButtonR57;

		TrackButton toggleButtonR58 = new TrackButton("");
		toggleButtonR58.setBackground(Color.LIGHT_GRAY);
		toggleButtonR58.setBounds(26, 449, 15, 15);
		trackDisplayPanel.add(toggleButtonR58);
		redLine[57] = toggleButtonR58;

		TrackButton toggleButtonR59 = new TrackButton("");
		toggleButtonR59.setBackground(Color.LIGHT_GRAY);
		toggleButtonR59.setBounds(40, 432, 15, 15);
		trackDisplayPanel.add(toggleButtonR59);
		redLine[58] = toggleButtonR59;

		TrackButton toggleButtonR60 = new TrackButton("");
		toggleButtonR60.setBackground(Color.LIGHT_GRAY);
		toggleButtonR60.setBounds(55, 417, 20, 20);
		trackDisplayPanel.add(toggleButtonR60);
		redLine[59] = toggleButtonR60;

		TrackButton toggleButtonR61 = new TrackButton("");
		toggleButtonR61.setBackground(Color.LIGHT_GRAY);
		toggleButtonR61.setBounds(92, 424, 15, 15);
		trackDisplayPanel.add(toggleButtonR61);
		redLine[60] = toggleButtonR61;

		TrackButton toggleButtonR62 = new TrackButton("");
		toggleButtonR62.setBackground(Color.LIGHT_GRAY);
		toggleButtonR62.setBounds(105, 447, 15, 15);
		trackDisplayPanel.add(toggleButtonR62);
		redLine[61] = toggleButtonR62;

		TrackButton toggleButtonR63 = new TrackButton("");
		toggleButtonR63.setBackground(Color.LIGHT_GRAY);
		toggleButtonR63.setBounds(116, 488, 15, 15);
		trackDisplayPanel.add(toggleButtonR63);
		redLine[62] = toggleButtonR63;

		TrackButton toggleButtonR64 = new TrackButton("");
		toggleButtonR64.setBackground(Color.LIGHT_GRAY);
		toggleButtonR64.setBounds(122, 532, 15, 15);
		trackDisplayPanel.add(toggleButtonR64);
		redLine[63] = toggleButtonR64;

		TrackButton toggleButtonR65 = new TrackButton("");
		toggleButtonR65.setBackground(Color.LIGHT_GRAY);
		toggleButtonR65.setBounds(134, 545, 15, 15);
		trackDisplayPanel.add(toggleButtonR65);
		redLine[64] = toggleButtonR65;

		TrackButton toggleButtonR66 = new TrackButton("");
		toggleButtonR66.setBackground(Color.LIGHT_GRAY);
		toggleButtonR66.setBounds(152, 550, 15, 15);
		trackDisplayPanel.add(toggleButtonR66);
		redLine[65] = toggleButtonR66;

		TrackButton toggleButtonR67 = new TrackButton("");
		toggleButtonR67.setBackground(Color.LIGHT_GRAY);
		toggleButtonR67.setBounds(308, 466, 15, 15);
		trackDisplayPanel.add(toggleButtonR67);
		redLine[66] = toggleButtonR67;

		TrackButton toggleButtonR68 = new TrackButton("");
		toggleButtonR68.setBackground(Color.LIGHT_GRAY);
		toggleButtonR68.setBounds(285, 446, 15, 15);
		trackDisplayPanel.add(toggleButtonR68);
		redLine[67] = toggleButtonR68;

		TrackButton toggleButtonR69 = new TrackButton("");
		toggleButtonR69.setBackground(Color.LIGHT_GRAY);
		toggleButtonR69.setBounds(285, 430, 15, 15);
		trackDisplayPanel.add(toggleButtonR69);
		redLine[68] = toggleButtonR69;

		TrackButton toggleButtonR70 = new TrackButton("");
		toggleButtonR70.setBackground(Color.LIGHT_GRAY);
		toggleButtonR70.setBounds(285, 415, 15, 15);
		trackDisplayPanel.add(toggleButtonR70);
		redLine[69] = toggleButtonR70;

		TrackButton toggleButtonR71 = new TrackButton("");
		toggleButtonR71.setBackground(Color.LIGHT_GRAY);
		toggleButtonR71.setBounds(308, 399, 15, 15);
		trackDisplayPanel.add(toggleButtonR71);
		redLine[70] = toggleButtonR71;

		TrackButton toggleButtonR72 = new TrackButton("");
		toggleButtonR72.setBackground(Color.LIGHT_GRAY);
		toggleButtonR72.setBounds(303, 325, 15, 15);
		trackDisplayPanel.add(toggleButtonR72);
		redLine[71] = toggleButtonR72;

		TrackButton toggleButtonR73 = new TrackButton("");
		toggleButtonR73.setBackground(Color.LIGHT_GRAY);
		toggleButtonR73.setBounds(283, 314, 15, 15);
		trackDisplayPanel.add(toggleButtonR73);
		redLine[72] = toggleButtonR73;

		TrackButton toggleButtonR74 = new TrackButton("");
		toggleButtonR74.setBackground(Color.LIGHT_GRAY);
		toggleButtonR74.setBounds(283, 297, 15, 15);
		trackDisplayPanel.add(toggleButtonR74);
		redLine[73] = toggleButtonR74;

		TrackButton toggleButtonR75 = new TrackButton("");
		toggleButtonR75.setBackground(Color.LIGHT_GRAY);
		toggleButtonR75.setBounds(283, 285, 15, 15);
		trackDisplayPanel.add(toggleButtonR75);
		redLine[74] = toggleButtonR75;

		TrackButton toggleButtonR76 = new TrackButton("");
		toggleButtonR76.setBackground(Color.LIGHT_GRAY);
		toggleButtonR76.setBounds(303, 268, 15, 15);
		trackDisplayPanel.add(toggleButtonR76);
		redLine[75] = toggleButtonR76;

		TrackButton toggleButtonR77 = new TrackButton("");
		toggleButtonR77.setBackground(Color.LIGHT_GRAY);
		toggleButtonR77.setBounds(692, 196, 15, 15);
		trackDisplayPanel.add(toggleButtonR77);
		redLine[76] = toggleButtonR77;

		//Track picture initialization must take place after buttons
		JLabel lblTrackPicture = new JLabel("");
		lblTrackPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrackPicture.setIcon(new ImageIcon(OfficeUI.class.getResource("/ctcOffice/track.png")));
		lblTrackPicture.setBounds(2, 2, 730, 729);
		trackDisplayPanel.add(lblTrackPicture);

		ButtonGroup trackBtns = new ButtonGroup();
		ActionListener blockSelectionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedBlockBtn = (TrackButton)e.getSource();
				selectedBlockChanged();

                //Populate destinations for combo box
                cmbDestinations.removeAllItems();
                TrackBlock[] line;
                if (selectedBlockBtn.line.equals("Red"))
                    line = ctcOffice.redLine;
                else
                    line = ctcOffice.greenLine;

                for (int i=0; i < line.length; i++)
                {
                    cmbDestinations.addItem(line[i]);
                }
            }
		};

		//Init variables for TrackButton class for each button
		for (int i=0; i < greenLine.length; i++)
		{
			greenLine[i].addActionListener(blockSelectionListener);
			trackBtns.add(greenLine[i]);
			greenLine[i].line = "Green";
			greenLine[i].block = i + 1;

			if (i < redLine.length)
			{
				redLine[i].addActionListener(blockSelectionListener);
				trackBtns.add(redLine[i]);
				redLine[i].line = "Red";
				redLine[i].block = i + 1;
			}
		}
	}
}
