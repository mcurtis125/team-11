import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;


public class LoginUI extends JPanel implements ActionListener{
	private JTextField textField;
	private JTextField textField_3;
	private JButton btnBegin;
	private JButton btnViewStatistics;
	private StringFrame stringFrame;
	private JPasswordField passwordFieldU1;
	private JPasswordField passwordFieldU2;
	private JButton btnLoginU1, btnLoginU2, btnCreateU1, btnCreateU2;
	private JLabel lblFail1, lblFail2, lblSuccess1, lblSuccess2;
	
	boolean u1Verified = false;
	boolean u2Verified = false;

	private CSV csv;
	
	public LoginUI(StringFrame stringFrame, CSV csv) {
		this.csv = csv;
		this.stringFrame = stringFrame;
		setBackground(Color.CYAN);
		setLayout(null);
		
		btnLoginU1 = new JButton("Login U1");
		btnLoginU1.setBounds(45, 371, 100, 29);
		btnLoginU1.addActionListener(this);
		add(btnLoginU1);
		
		btnLoginU2 = new JButton("Login U2");
		btnLoginU2.setBounds(446, 371, 117, 29);
		btnLoginU2.addActionListener(this);
		add(btnLoginU2);
		
		JLabel lblLoginPage = new JLabel("Login Page");
		lblLoginPage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblLoginPage.setBounds(257, 6, 100, 64);
		add(lblLoginPage);
		
		textField = new JTextField();
		textField.setBounds(45, 212, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(429, 212, 134, 28);
		add(textField_3);
		textField_3.setColumns(10);
		
		this.btnCreateU1 = new JButton("Create U1");
		btnCreateU1.setBounds(45, 142, 117, 29);
		btnCreateU1.addActionListener(this);
		add(btnCreateU1);
		
		this.btnCreateU2 = new JButton("Create U2");
		btnCreateU2.setBounds(446, 142, 117, 29);
		btnCreateU2.addActionListener(this);
		add(btnCreateU2);
		
		this.btnBegin = new JButton("BEGIN");
		this.btnBegin.setBounds(240, 515, 117, 29);
		this.btnBegin.addActionListener(this);
		this.btnBegin.setEnabled(false);
		this.add(btnBegin);
		
		
		this.btnViewStatistics = new JButton("View Statistics");
		this.btnViewStatistics.setBounds(246, 180, 117, 29);
		this.btnViewStatistics.addActionListener(this);
		add(btnViewStatistics);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(45, 193, 90, 16);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 252, 70, 16);
		add(lblPassword);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setBounds(493, 193, 70, 16);
		add(lblUsername_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(493, 252, 61, 16);
		add(lblPassword_1);
		
		this.passwordFieldU1 = new JPasswordField();
		passwordFieldU1.setBounds(45, 280, 134, 29);
		add(passwordFieldU1);
		
		this.passwordFieldU2 = new JPasswordField();
		passwordFieldU2.setBounds(429, 280, 134, 29);
		add(passwordFieldU2);
		
		this.lblFail1 = new JLabel("Login Failed!");
		lblFail1.setEnabled(false);
		lblFail1.setBounds(55, 412, 107, 16);
		add(lblFail1);
		
		this.lblSuccess1 = new JLabel("Login Succesful!");
		lblSuccess1.setEnabled(false);
		lblSuccess1.setBounds(54, 440, 108, 16);
		add(lblSuccess1);
		
		this.lblFail2 = new JLabel("Login Failed!");
		lblFail2.setEnabled(false);
		lblFail2.setBounds(476, 412, 107, 16);
		add(lblFail2);
		
		this.lblSuccess2 = new JLabel("Login Succesful!");
		lblSuccess2.setEnabled(false);
		lblSuccess2.setBounds(455, 440, 108, 16);
		add(lblSuccess2);
	}
	
	@Override
	public void actionPerformed( ActionEvent e ) {
		Object src = e.getSource();
	      if( src == btnBegin ) {
	         stringFrame.change("SETTINGS");
	      } else if (src == btnViewStatistics) {
	    	  stringFrame.change("STATISTICS");
	      } else if (src == btnLoginU1) {
	    	  if (csv.verifyAccount(textField.getText(), passwordFieldU1.getText())) {
	    		  u1Verified = true;
	    		  lblSuccess1.setEnabled(true);
	    		  lblFail1.setEnabled(false);
	    	  } else {
	    		  lblFail1.setEnabled(true);
	    	  }
	      } else if (src == btnLoginU2) {
	    	  if (csv.verifyAccount(textField_3.getText(), passwordFieldU2.getText())) {
	    		  u2Verified = true;
	    		  lblSuccess2.setEnabled(true);
	    		  lblFail2.setEnabled(false);
	    	  } else {
	    		  lblFail2.setEnabled(true);
	    	  }
	      } else if (src == btnCreateU1) {
	    	  csv.addAccount(textField.getText(), passwordFieldU1.getText());
	    	  u1Verified = true;
	    	  lblSuccess1.setEnabled(true);
	      } else if (src == btnCreateU2) {
	    	  csv.addAccount(textField_3.getText(), passwordFieldU2.getText());
	    	  u2Verified = true;
	    	  lblSuccess2.setEnabled(true);
	      } 
	      
	      //confirmes both logins and allows begin to be clicked
	      if (u1Verified && u2Verified) {
	    	  btnBegin.setEnabled(true);
	      }
	}
	
	public void resetLogins() {
		u1Verified = false;
		u2Verified = false;
		btnBegin.setEnabled(false);
		textField.setText("");
		textField_3.setText("");
		passwordFieldU1.setText("");
		passwordFieldU2.setText("");
		lblSuccess1.setEnabled(false);
		lblFail1.setEnabled(false);
		lblSuccess2.setEnabled(false);
		lblFail2.setEnabled(false);
		
	}
}