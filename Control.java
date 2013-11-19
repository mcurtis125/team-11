import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Control {
 
    private JPanel cards;
    private CardLayout layout;
    private GamePlayUI gamePlay;
    private LoginUI card1;
    private Stats statistics;
    private CSV csv;

    public void addComponentToPane(Container pane) {
       //this.gamePlay = new GameplayUI();
       //gamePlay.init();
       //gamePlay.start();
    	this.csv = new CSV();
 
    	this.card1 = new LoginUI(this, csv);
 
        //SettingsUI card2 = new SettingsUI(this, gamePlay);
    	this.statistics = new Stats(csv);
        StatisticsUI card3 = new StatisticsUI(this, statistics);
        
 
 
        cards=new JPanel(new CardLayout());
        cards.add(card1, "LOGIN");
//        cards.add(card2, "SETTINGS");
        cards.add(card3, "STATISTICS");
 
        pane.add(cards, BorderLayout.CENTER);
        this.layout = (CardLayout)(cards.getLayout());
    }
 
 
    private static void createAndShowGUI() {
 
        JFrame frame = new JFrame ("TRONNNNNNNN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);
 
 
        Control stringFrame = new Control();
        stringFrame.addComponentToPane(frame.getContentPane());
        frame.setVisible(true);
    }
 
    public static void main(String [] args)	{
        createAndShowGUI();
    }
 
    public void change(String newPage) {
    	if (newPage.equals("SETTINGS")) {
    		SettingsUI card2 = new SettingsUI(this, gamePlay);
    		cards.add(card2, "SETTINGS");
    	} else if (newPage.equals("LOGIN")) {
    		card1.resetLogins();
    	} else if (newPage.equals("STATISTICS")) {
    		csv = new CSV();
    		statistics = new Stats(csv);
    		StatisticsUI card3 = new StatisticsUI(this, statistics);
    		cards.add(card3, "STATISTICS");
    		
    		System.out.println("test");
    	}
        layout.show(cards, newPage);
        cards.revalidate();
        //cards.repaint();
    }

}
