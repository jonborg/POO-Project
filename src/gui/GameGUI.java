package gui;

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

import gameObjects.Card;
import gameObjects.Player;
import videoPoker.IGame;
import videoPoker.ParentGame;
import gameObjects.CardMask;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JTextPane;

public class GameGUI {

	public JFrame frmInteractiveVideoPoker;
	private ParentGame game;

	private JSpinner spinner;
	private int bet, lastBet;
	private JCheckBox checkBox_0, checkBox_1, checkBox_2, checkBox_3, checkBox_4;
	private JLabel betLabel;
	private JButton btnDeal, btnAdvice;
	private JLabel card_1label, card_2label, card_3label, card_4label, card_5label;
	private JTextPane lblStats, txtpnResult;
	JLabel lblYourBalance;
	private JLabel lblResult;

	/**
	 * Create the application.
	 */
	public GameGUI(ParentGame game) {
		this.game = game;		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */
	private void initialize() {
		int maxBet = 5;

		if (game.credit() < maxBet)
			maxBet = game.credit();
		lastBet = maxBet;

		frmInteractiveVideoPoker = new JFrame();
		frmInteractiveVideoPoker.setResizable(false);
		frmInteractiveVideoPoker.setTitle("Video Poker");
		frmInteractiveVideoPoker.getContentPane().setBackground(new Color(0, 128, 0));

		JLabel deck_label = new JLabel("");
		deck_label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/Deck_back.jpg")));
		deck_label.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel Dealerlabel = new JLabel("Dealer");
		Dealerlabel.setVerticalAlignment(SwingConstants.TOP);
		Dealerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		Dealerlabel.setFont(new Font("Sitka Text", Font.BOLD, 18));

		card_2label = new JLabel("");
		card_2label.setIcon(null);
		card_2label.setHorizontalAlignment(SwingConstants.CENTER);
		card_2label.setAlignmentX(Component.CENTER_ALIGNMENT);
		card_2label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));

		JLabel lblYourHand = new JLabel("Your Hand");
		lblYourHand.setVerticalAlignment(SwingConstants.TOP);
		lblYourHand.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourHand.setFont(new Font("Sitka Text", Font.BOLD, 18));

		card_3label = new JLabel("");
		card_3label.setIcon(null);
		card_3label.setHorizontalAlignment(SwingConstants.CENTER);
		card_3label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_3label.setAlignmentX(0.5f);

		card_4label = new JLabel("");
		card_4label.setIcon(null);
		card_4label.setHorizontalAlignment(SwingConstants.CENTER);
		card_4label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_4label.setAlignmentX(0.5f);

		card_1label = new JLabel("");
		card_1label.setIcon(null);
		card_1label.setHorizontalAlignment(SwingConstants.CENTER);
		card_1label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_1label.setAlignmentX(0.5f);

		card_5label = new JLabel("");
		card_5label.setIcon(null);
		card_5label.setHorizontalAlignment(SwingConstants.CENTER);
		card_5label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
		card_5label.setAlignmentX(0.5f);

		lblYourBalance = new JLabel("Your balance: " + game.credit() +" Credits" );
		lblYourBalance.setFont(new Font("Sitka Display", Font.BOLD, 17));

		checkBox_0 = new JCheckBox("");
		checkBox_0.setEnabled(true);
		checkBox_0.setVisible(false);

		checkBox_1 = new JCheckBox("");
		checkBox_1.setEnabled(true);
		checkBox_1.setVisible(false);

		checkBox_2 = new JCheckBox("");
		checkBox_2.setEnabled(true);
		checkBox_2.setVisible(false);

		checkBox_3 = new JCheckBox("");
		checkBox_3.setEnabled(true);
		checkBox_3.setVisible(false);

		checkBox_4 = new JCheckBox("");
		checkBox_4.setEnabled(true);
		checkBox_4.setVisible(false);

		btnDeal = new JButton("DEAL!");
		btnDeal.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnDeal.addActionListener(new ActionDeal());
		btnDeal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeal.setFont(new Font("Sitka Text", Font.BOLD, 16));
		btnDeal.setToolTipText("Deal a new Hand");

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(lastBet, 1, maxBet, 1));

		betLabel = new JLabel("Bet:");
		betLabel.setFont(new Font("Sitka Display", Font.BOLD, 17));
		betLabel.setLabelFor(spinner);

		lblStats = new JTextPane();
		lblStats.setEditable(false);
		lblStats.setBackground(new Color(0, 128, 0));
		lblStats.setFont(new Font("Sitka Text", Font.BOLD, 14));
		
		lblResult = new JLabel("");
		
		txtpnResult = new JTextPane();
		txtpnResult.setText("");
		txtpnResult.setForeground(new Color(0, 0, 128));
		txtpnResult.setFont(new Font("Sitka Text", Font.BOLD, 20));
		txtpnResult.setEditable(false);
		txtpnResult.setBackground(new Color(0, 128, 0));
		GroupLayout groupLayout = new GroupLayout(frmInteractiveVideoPoker.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(53)
									.addComponent(checkBox_0)
									.addGap(125)
									.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(125)
									.addComponent(checkBox_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(125)
									.addComponent(checkBox_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
									.addGap(135)
									.addComponent(checkBox_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(308)
									.addComponent(lblYourHand))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(lblYourBalance)
									.addPreferredGap(ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
									.addComponent(betLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnDeal))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(card_1label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(card_2label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(card_3label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(card_4label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(card_5label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStats, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(29)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addComponent(deck_label)
											.addGap(236)
											.addComponent(lblResult))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(38)
											.addComponent(Dealerlabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(173)
									.addComponent(txtpnResult, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(103)
									.addComponent(lblResult))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(Dealerlabel)
									.addGap(11)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(txtpnResult, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
										.addComponent(deck_label))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblYourHand)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblStats, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(card_1label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_2label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_3label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_4label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(card_5label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(checkBox_1, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
							.addComponent(checkBox_2, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
							.addComponent(checkBox_0))
						.addComponent(checkBox_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(checkBox_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addComponent(lblYourBalance))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDeal)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(betLabel))))
					.addGap(20))
		);
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {card_2label, card_3label, card_4label, card_1label, card_5label});
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		frmInteractiveVideoPoker.getContentPane().setLayout(groupLayout);
		frmInteractiveVideoPoker.setBounds(100, 100, 784, 706);
		frmInteractiveVideoPoker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setVerifyInputWhenFocusTarget(false);
		menuBar.setBackground(new Color(255, 255, 255));
		frmInteractiveVideoPoker.setJMenuBar(menuBar);

		btnAdvice = new JButton("Advice");
		btnAdvice.setEnabled(false);
		btnAdvice.addActionListener(new ActionAdvice());
		btnAdvice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdvice.setToolTipText("Shows tips on which cards to Hold");
		menuBar.add(btnAdvice);

		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.addActionListener(new ActionStats());
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

	private class ActionDeal implements ActionListener {
		int HoldBtn;
		public ActionDeal() {
			HoldBtn = 0;
		}
		public void actionPerformed(ActionEvent e) {
			String[] suits = { "hearts", "spades", "diamonds", "clubs" };
			String[] ranksToString = { "A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2" };
			int maxBet;
			int[] toHold = new int[5];
			/*Button in DEAL mode*/
			if (HoldBtn == 0) {
				bet = (Integer) spinner.getValue();
				lastBet = bet;
				spinner.setVisible(false);

				game.bet(bet);;
				betLabel.setText("Bet: " + bet);
				lblYourBalance.setText("Your balance: " + game.credit() +" Credits");

				game.deal();

				ArrayList<Card> cards = game.player.getHand().getcards();

				card_1label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(0).getRank()]+"_of_"+suits[cards.get(0).getSuit()]+".png")));
				card_2label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(1).getRank()]+"_of_"+suits[cards.get(1).getSuit()]+".png")));
				card_3label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(2).getRank()]+"_of_"+suits[cards.get(2).getSuit()]+".png")));
				card_4label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(3).getRank()]+"_of_"+suits[cards.get(3).getSuit()]+".png")));
				card_5label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(4).getRank()]+"_of_"+suits[cards.get(4).getSuit()]+".png")));

				checkBox_0.setVisible(true);
				checkBox_1.setVisible(true);
				checkBox_2.setVisible(true);
				checkBox_3.setVisible(true);
				checkBox_4.setVisible(true);

				btnAdvice.setEnabled(true);

				btnDeal.setText("HOLD!");
				HoldBtn = 1;

			}

			/*Button in Hold mode*/
			else if (HoldBtn == 1){
				if(((ActionAdvice) btnAdvice.getActionListeners()[0]).getToggle() == 1)
					btnAdvice.doClick();
		
				btnAdvice.setEnabled(false);
				
				if (checkBox_0.isSelected() == true) {
					toHold[0] = 1;
					checkBox_0.setSelected(false);
				}
				
				if (checkBox_1.isSelected() == true){					
					toHold[1] = 1;
					checkBox_1.setSelected(false);
				}
				if (checkBox_2.isSelected() == true){
					toHold[2] = 1;
					checkBox_2.setSelected(false);
				}
				if (checkBox_3.isSelected() == true) {
					toHold[3] = 1;
					checkBox_3.setSelected(false);
				}
				if (checkBox_4.isSelected() == true) {
					toHold[4] = 1;
					checkBox_4.setSelected(false);
				}
				checkBox_0.setVisible(false);
				checkBox_1.setVisible(false);
				checkBox_2.setVisible(false);
				checkBox_3.setVisible(false);
				checkBox_4.setVisible(false);

				game.hold(toHold);
				ArrayList<Card> cards = game.player.getHand().getcards();
				card_1label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(0).getRank()]+"_of_"+suits[cards.get(0).getSuit()]+".png")));
				card_2label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(1).getRank()]+"_of_"+suits[cards.get(1).getSuit()]+".png")));
				card_3label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(2).getRank()]+"_of_"+suits[cards.get(2).getSuit()]+".png")));
				card_4label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(3).getRank()]+"_of_"+suits[cards.get(3).getSuit()]+".png")));
				card_5label.setIcon(new ImageIcon(GameGUI.class.getResource("/gui/"+ranksToString[cards.get(4).getRank()]+"_of_"+suits[cards.get(4).getSuit()]+".png")));
				
				txtpnResult.setText(game.play(bet));
				
				lblYourBalance.setText("Your balance: " + game.credit() +" Credits");
				if (game.credit()<= 0) {
					btnDeal.setText("0 Credit");
					btnDeal.setEnabled(false);
				}
				else {
					btnDeal.setText("Again!");
					HoldBtn = 2;
				}
				
			}
			else {
				if (game.credit() < 5)
					maxBet = game.credit();
				else maxBet = 5;
				if (game.credit() < lastBet)
					lastBet = game.credit();
				
				betLabel.setText("Bet:");
				txtpnResult.setText("");
				
				card_1label.setIcon(null);
				card_2label.setIcon(null);
				card_3label.setIcon(null);
				card_4label.setIcon(null);
				card_5label.setIcon(null);

				checkBox_0.setVisible(false);
				checkBox_1.setVisible(false);
				checkBox_2.setVisible(false);
				checkBox_3.setVisible(false);
				checkBox_4.setVisible(false);
				
				spinner.setVisible(true);
				spinner.setModel(new SpinnerNumberModel(lastBet, 1, maxBet, 1));
				
				btnDeal.setText("DEAL!");
				HoldBtn = 0;
			}
				

		}
	}

	private class ActionAdvice implements ActionListener {
		int adviceToggle=0;
		public ActionAdvice() {
		}
		public void actionPerformed(ActionEvent e) {
			if(adviceToggle == 0) {
				int[] toHold = game.advice();
				if (toHold[0] == 1) 
					card_1label.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
				if (toHold[1] == 1) 
					card_2label.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
				if (toHold[2] == 1) 
					card_3label.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
				if (toHold[3] == 1) 
					card_4label.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
				if (toHold[4] == 1) 
					card_5label.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));

				adviceToggle = 1;
			}
			else {
				card_1label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
				card_2label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
				card_3label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
				card_4label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));
				card_5label.setBorder(new LineBorder(new Color(0, 100, 0), 2, true));

				adviceToggle = 0;
			}
		}
		public int getToggle() {
			return adviceToggle;
		}
	}

	private class ActionStats implements ActionListener {
		int statsToggle = 0;
		public ActionStats() {
		}
		public void actionPerformed(ActionEvent e) {
			if (statsToggle == 0) {
				lblStats.setText(game.statistics());
				statsToggle = 1;
			}
			else {
				lblStats.setText("");
				statsToggle = 0;
			}
		}
	}
}
