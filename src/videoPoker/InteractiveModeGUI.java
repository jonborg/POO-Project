package videoPoker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import java.awt.Panel;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Cursor;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.ComponentOrientation;
import javax.swing.JMenu;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InteractiveModeGUI {

	public JFrame frmInteractiveVideoPoker;
	ParentGame game;
	Player player;

	/**
	 * Create the application.
	 */
	public InteractiveModeGUI(ParentGame game, int balance) {
		this.game = game;
		player = new Player(balance);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */
	private void initialize() {
		int lastBet;
		int maxBet = 5;
		
		if (player.credit < maxBet)
			maxBet = player.credit;
		lastBet = maxBet;
		
		frmInteractiveVideoPoker = new JFrame();
		frmInteractiveVideoPoker.setResizable(false);
		frmInteractiveVideoPoker.setTitle("Interactive Video Poker");
		frmInteractiveVideoPoker.getContentPane().setBackground(new Color(0, 128, 0));

		JLabel deck_label = new JLabel("");
		deck_label.setIcon(new ImageIcon(InteractiveModeGUI.class.getResource("/videoPoker/Deck_back.jpg")));
		deck_label.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel Dealerlabel = new JLabel("Dealer");
		Dealerlabel.setVerticalAlignment(SwingConstants.TOP);
		Dealerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		Dealerlabel.setFont(new Font("Sitka Text", Font.BOLD, 18));

		JLabel card_2label = new JLabel("");
		card_2label.setIcon(null);
		card_2label.setHorizontalAlignment(SwingConstants.CENTER);
		card_2label.setAlignmentX(Component.CENTER_ALIGNMENT);
		card_2label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));

		JLabel lblYourHand = new JLabel("Your Hand");
		lblYourHand.setVerticalAlignment(SwingConstants.TOP);
		lblYourHand.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourHand.setFont(new Font("Sitka Text", Font.BOLD, 18));

		JLabel card_3label = new JLabel("");
		card_3label.setIcon(new ImageIcon(InteractiveModeGUI.class.getResource("/videoPoker/K_of_clubs.png")));
		card_3label.setHorizontalAlignment(SwingConstants.CENTER);
		card_3label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_3label.setAlignmentX(0.5f);

		JLabel card_4label = new JLabel("");
		card_4label.setIcon(null);
		card_4label.setHorizontalAlignment(SwingConstants.CENTER);
		card_4label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_4label.setAlignmentX(0.5f);

		JLabel card_1label = new JLabel("");
		card_1label.setIcon(null);
		card_1label.setHorizontalAlignment(SwingConstants.CENTER);
		card_1label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_1label.setAlignmentX(0.5f);

		JLabel card_5label = new JLabel("");
		card_5label.setIcon(null);
		card_5label.setHorizontalAlignment(SwingConstants.CENTER);
		card_5label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_5label.setAlignmentX(0.5f);

		JLabel lblYourBalance = new JLabel("Your balance: " + player.credit +" Credits" );
		lblYourBalance.setFont(new Font("Sitka Display", Font.BOLD, 17));

		JCheckBox checkBox = new JCheckBox("");

		JCheckBox checkBox_1 = new JCheckBox("");

		JCheckBox checkBox_2 = new JCheckBox("");

		JCheckBox checkBox_3 = new JCheckBox("");

		JCheckBox checkBox_4 = new JCheckBox("");

		JButton btnDeal = new JButton("DEAL!");
		btnDeal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeal.setFont(new Font("Sitka Text", Font.BOLD, 16));
		btnDeal.setToolTipText("Deal a new Hand");

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(lastBet, 1, maxBet, 1));

		JLabel lblNewLabel = new JLabel("Bet:");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 17));
		lblNewLabel.setLabelFor(spinner);
		GroupLayout groupLayout = new GroupLayout(frmInteractiveVideoPoker.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(checkBox)
					.addGap(125)
					.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(125)
					.addComponent(checkBox_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(125)
					.addComponent(checkBox_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
					.addComponent(checkBox_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(87))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(291)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addComponent(deck_label))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addComponent(Dealerlabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(308)
							.addComponent(lblYourHand))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblYourBalance)
							.addPreferredGap(ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(btnDeal)
							.addGap(12)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(card_1label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(card_2label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(card_3label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(card_4label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(card_5label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(Dealerlabel)
					.addGap(11)
					.addComponent(deck_label)
					.addGap(50)
					.addComponent(lblYourHand)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(card_1label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_2label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_3label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_4label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_5label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBox_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(checkBox_1, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
								.addComponent(checkBox_2, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
								.addComponent(checkBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(lblYourBalance))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(checkBox_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDeal)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))))
					.addGap(20))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {card_2label, card_3label, card_4label, card_1label, card_5label});
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		frmInteractiveVideoPoker.getContentPane().setLayout(groupLayout);
		frmInteractiveVideoPoker.setBounds(100, 100, 784, 621);
		frmInteractiveVideoPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setVerifyInputWhenFocusTarget(false);
		menuBar.setBackground(new Color(255, 255, 255));
		frmInteractiveVideoPoker.setJMenuBar(menuBar);

		JButton btnAdvice = new JButton("Advice");
		btnAdvice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdvice.setToolTipText("Shows tips on which cards to Hold");
		menuBar.add(btnAdvice);

		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setToolTipText("Shows some statistics about the played games");
		btnStatistics.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(btnStatistics);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmInteractiveVideoPoker.dispose();
			}
		});
		btnQuit.setToolTipText("Closes the game");
		btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(btnQuit);
	}
}
