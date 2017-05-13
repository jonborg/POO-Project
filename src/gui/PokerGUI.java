package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import videoPoker.Debug;
import videoPoker.DebugMode;
import videoPoker.IGame;
import videoPoker.IGameType;
import videoPoker.Interactive;
import videoPoker.InteractiveMode;
import videoPoker.ParentGame;
import videoPoker.Simulation;
import videoPoker.SimulationMode;

import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

public class PokerGUI {
	private JSpinner spinner;
	private IGameType mode = null;

	private JFrame frmPokerGame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ParentGame game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerGUI window = new PokerGUI();
					window.frmPokerGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmPokerGame = new JFrame();
		frmPokerGame.setVisible(true);
		frmPokerGame.setResizable(false);
		frmPokerGame.setTitle("Poker Game");
		frmPokerGame.setBounds(100, 100, 374, 316);
		frmPokerGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		frmPokerGame.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblChooseGameMode = new JLabel("Choose Game Mode");
		lblChooseGameMode.setVerticalAlignment(SwingConstants.TOP);
		lblChooseGameMode.setFont(new Font("Stencil", Font.BOLD, 18));
		lblChooseGameMode.setHorizontalAlignment(SwingConstants.CENTER);

		JRadioButton rdbtnInteractiveMode = new JRadioButton("Interactive Mode");
		rdbtnInteractiveMode.setActionCommand("Interactive Mode");
		rdbtnInteractiveMode.setSelected(true);
		rdbtnInteractiveMode.setBackground(new Color(0, 128, 0));
		rdbtnInteractiveMode.setToolTipText("Play normal Double Bonus 10/7 Video Poker with interactive GUI");
		rdbtnInteractiveMode.setVerticalAlignment(SwingConstants.TOP);
		buttonGroup.add(rdbtnInteractiveMode);

		JRadioButton rdbtnSimulationMode = new JRadioButton("Simulation Mode");
		rdbtnSimulationMode.setActionCommand("Simulation Mode");
		rdbtnSimulationMode.setBackground(new Color(0, 128, 0));
		rdbtnSimulationMode.setToolTipText("Play the perfect game and see some statistics");
		rdbtnSimulationMode.setVerticalAlignment(SwingConstants.TOP);
		buttonGroup.add(rdbtnSimulationMode);

		JLabel lblStartingCredit = new JLabel("Starting credit");
		lblStartingCredit.setFont(new Font("Stencil", Font.PLAIN, 16));

		spinner = new JSpinner();
		spinner.setToolTipText("Number of starting credits");
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblCredits = new JLabel("Credits");
		lblCredits.setLabelFor(spinner);
		lblCredits.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnStart = new JButton("START!");
		btnStart.addActionListener(new ActionStart());

		btnStart.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(89)
							.addComponent(lblChooseGameMode, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(116)
							.addComponent(lblStartingCredit))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(157)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCredits))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(144)
							.addComponent(btnStart))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(119)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnSimulationMode, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnInteractiveMode))))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseGameMode)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnInteractiveMode, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnSimulationMode)
					.addGap(61)
					.addComponent(lblStartingCredit)
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCredits))
					.addGap(31)
					.addComponent(btnStart)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {rdbtnInteractiveMode, rdbtnSimulationMode});
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {rdbtnInteractiveMode, rdbtnSimulationMode});
		gl_panel.setAutoCreateGaps(true);
		gl_panel.setAutoCreateContainerGaps(true);
		panel.setLayout(gl_panel);
	}


	private class ActionStart implements ActionListener {
		int balance;
		int[] args = new int[3];
		public ActionStart() {
			balance = 0;
			mode = null;
		}
		public void actionPerformed(ActionEvent e) {
			balance = (Integer) spinner.getValue();
			args[0] = balance;
			if (buttonGroup.getSelection().getActionCommand().equals("Interactive Mode")){
				mode = new InteractiveMode();
				game = mode.select(new Interactive(balance), new Debug(0), new Simulation(args), balance);
			}

			if (buttonGroup.getSelection().getActionCommand().equals("Simulation Mode")) {
				mode = new SimulationMode();
				game = mode.select(new Interactive(0), new Debug(0), new Simulation(args), balance);
			}


			frmPokerGame.dispose();

			/*Open new window*/
			GameGUI window = new GameGUI(game);
			window.frmInteractiveVideoPoker.setVisible(true);

		}
	}
}
