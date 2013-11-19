import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButton;

import java.awt.event.*;
import java.awt.image.BufferedImage;


public class SettingsUI extends JPanel implements ActionListener {
	private JTable table;
	private JTable table_1;
	private Control control;
	private JButton btnLogout;
	private JButton btnPlay;
	private GamePlayUI gamePlay;
	private User user1;
	private User user2;
	
    private BufferedImage bi; 
    private Graphics2D mapStroke;
	
	public SettingsUI(Control cont, GamePlayUI gamePlay) {
		this.gamePlay = gamePlay;
		this.control = cont;
		setBackground(Color.CYAN);
		setLayout(null);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblSettings.setBounds(263, 35, 70, 37);
		add(lblSettings);
		
		JLabel label = new JLabel("");
		label.setBounds(54, 119, 61, 16);
		add(label);
		
		/*JLabel lblPlayer = new JLabel(control.getUser1());
		lblPlayer.setBounds(54, 147, 61, 16);
		add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel(control.getUser2());
		lblPlayer_1.setBounds(504, 147, 61, 16);
		add(lblPlayer_1);
		*/
		
		table = new JTable();
		table.setBounds(419, 250, 141, 170);
		add(table);
		
		table_1 = new JTable();
		table_1.setBounds(54, 250, 141, 170);
		add(table_1);
		
		JLabel user1Score = new JLabel("User 1 Score");
		user1Score.setBounds(195, 144, 96, 23);
		add(user1Score);
		
		JLabel user2Score = new JLabel("User 2 Score");
		user2Score.setBounds(350, 141, 90, 28);
		add(user2Score);
		
		JRadioButton rdbtnSlow = new JRadioButton("Slow");
		rdbtnSlow.setBounds(254, 367, 141, 23);
		add(rdbtnSlow);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.setBounds(254, 397, 141, 23);
		add(rdbtnMedium);
		
		JRadioButton rdbtnFast = new JRadioButton("Fast");
		rdbtnFast.setBounds(254, 429, 141, 23);
		add(rdbtnFast);
		
		//so you can only choose 1 speed button at a time
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnSlow);
		group.add(rdbtnMedium);
		group.add(rdbtnFast);
		
		JRadioButton rdbtnMap = new JRadioButton("Map 1");
		rdbtnMap.setBounds(255, 236, 141, 23);
		add(rdbtnMap);
		
		JRadioButton rdbtnMap_1 = new JRadioButton("Map 2");
		rdbtnMap_1.setBounds(254, 271, 141, 23);
		add(rdbtnMap_1);
		
		JRadioButton rdbtnMap_2 = new JRadioButton("Map 3");
		rdbtnMap_2.setBounds(254, 306, 141, 23);
		add(rdbtnMap_2);
		
		//so you can only choose 1 maps button at a time
		ButtonGroup mapGroup = new ButtonGroup();
		mapGroup.add(rdbtnMap);
		mapGroup.add(rdbtnMap_1);
		mapGroup.add(rdbtnMap_2);
		
		this.btnLogout = new JButton("Logout");
		btnLogout.setBounds(6, 6, 117, 29);
		btnLogout.addActionListener(this);
		add(btnLogout);
		
		this.btnPlay = new JButton("PLAY");
		btnPlay.setBounds(229, 578, 141, 37);
		btnPlay.addActionListener(this);
		add(btnPlay);
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		Object src = e.getSource();
	      if ( src == btnLogout ) {
	         control.change("LOGIN");
	      } else if (src == btnPlay) {
	    	  GamePlayUI gameApplet = new GamePlayUI(); 
	    	  JPanel container = new JPanel();
	    	  container.setLayout(new GridLayout(1,0));
	    	  container.add(gameApplet); //Display Applet
	    	  
	    	  add(container);

	    	  gameApplet.init();
	    	  gameApplet.start();
	    	  gameApplet.requestFocus();		//necessary to set focus
	    	  gameApplet.setFocusable(true);

	      }
	}
}
