import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class StatisticsUI extends JPanel {
	private JTable table;
	private JTable table_1;
	private StringFrame stringFrame;
	
	public StatisticsUI(StringFrame frame) {
		this.stringFrame = frame;
		setBackground(Color.CYAN);
		setLayout(null);
		
		table = new JTable();
		table.setBounds(225, 5, 0, 0);
		add(table);
		
		table_1 = new JTable();
		table_1.setBounds(6, 140, 588, 619);
		add(table_1);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblStatistics.setBounds(251, 27, 78, 32);
		add(lblStatistics);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stringFrame.change("LOGIN");
				
			}
		});
		btnLogin.setBounds(477, 73, 117, 21);
		add(btnLogin);
	}
}
