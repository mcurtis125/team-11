import javax.swing.*;


public class Control {
 
    private JPanel cards;
    private CardLayout layout;
    private String user1;
    private String user2;
    private GameplayUI gamePlay;
    private LoginUI card1;

    public void addComponentToPane(Container pane) {
       this.gamePlay = new GameplayUI();
       gamePlay.init();
       gamePlay.start();
    	CSV csv = new CSV();
 
    	this.card1 = new LoginUI(this, csv);
 
        //SettingsUI card2 = new SettingsUI(this, gamePlay);
        StatisticsUI card3 = new StatisticsUI(this);
 
 
        cards=new JPanel(new CardLayout());
        cards.add(card1, "LOGIN");
        //cards.add(card2, "SETTINGS");
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
    	}
        layout.show(cards, newPage);
        cards.revalidate();
        //cards.repaint();
    }
 
    public String getUser1() {
    	return user1;
    }
    public String getUser2() {
    	return user2;
    }
    public void setUser1(String user1) {
    	this.user1 = user1;
    }
    public void setUser2(String user2) {
    	this.user2 = user2;
    }
}