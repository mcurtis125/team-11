import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;



public class StatisticsUI extends JPanel {

	private JTable dataTable;
	private JScrollPane dataScroll;
	private Control control;
	private Stats stats;
	
	private final String[] COLUMN_NAMES = {"Username:", "Number Of Games Won:", "Number Of Games Played:"};
	
	public StatisticsUI(Control controller, Stats stats) {
		this.control = controller;
		this.stats = stats;
		setBackground(Color.CYAN);
		setLayout(null);
		
		dataTable = new JTable(stats.getRowData(), COLUMN_NAMES);
		dataTable.setEnabled(false);
		dataScroll = new JScrollPane(dataTable);
		
		dataScroll.setBounds(6, 140, 588, 619);	
		dataTable.setBounds(6, 140, 588, 619);
		//dataTable.setAutoCreateRowSorter(true);
		add( dataScroll );
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblStatistics.setBounds(251, 27, 78, 32);
		add(lblStatistics);
		
		JButton btnLogin = new JButton("Back to Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.change("LOGIN");
				
			}
		});
		btnLogin.setBounds(477, 73, 117, 21);
		add(btnLogin);
	}
	
}
