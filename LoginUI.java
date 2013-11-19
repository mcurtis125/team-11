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
import java.awt.*;
 
 
public class LoginUI extends JPanel implements ActionListener{
    private JTextField textField;
    private JTextField textField_3;
    private JButton btnBegin;
    private JButton btnViewStatistics;
    private Control control;
    private JPasswordField passwordFieldU1;
    private JPasswordField passwordFieldU2;
    private JButton btnLoginU1, btnLoginU2, btnCreateU1, btnCreateU2;
    private JLabel lblFail1, lblFail2, lblSuccess1, lblSuccess2, lblExistsU1, lblExistsU2;
     
    boolean u1Verified = false;
    boolean u2Verified = false;
 
    private CSV csv;
     
    public LoginUI(Control control, CSV csv) {
        this.csv = csv;
        this.control = control;
        setBackground(Color.CYAN);
         
        btnLoginU1 = new JButton("Login U1");
        btnLoginU1.setBounds(45, 371, 100, 29);
        btnLoginU1.addActionListener(this);
        setLayout(null);
        add(btnLoginU1);
         
        btnLoginU2 = new JButton("Login U2");
        btnLoginU2.setBounds(446, 371, 117, 29);
        btnLoginU2.addActionListener(this);
        add(btnLoginU2);
         
        JLabel lblLoginPage = new JLabel("Login Page");
        lblLoginPage.setBounds(257, 6, 100, 64);
        lblLoginPage.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
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
        btnBegin.setBounds(240, 515, 117, 29);
        this.btnBegin.addActionListener(this);
        this.btnBegin.setEnabled(false);
        this.add(btnBegin);
         
         
        this.btnViewStatistics = new JButton("View Statistics");
        btnViewStatistics.setBounds(246, 180, 117, 29);
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
        lblFail1.setBounds(55, 412, 107, 16);
        lblFail1.setVisible(false);
        add(lblFail1);
         
        this.lblSuccess1 = new JLabel("Login Successful!");
        lblSuccess1.setBounds(54, 440, 110, 16);
        lblSuccess1.setVisible(false);
        add(lblSuccess1);
         
        this.lblFail2 = new JLabel("Login Failed!");
        lblFail2.setBounds(476, 412, 107, 16);
        lblFail2.setVisible(false);
        add(lblFail2);
         
        this.lblSuccess2 = new JLabel("Login Successful!");
        lblSuccess2.setBounds(455, 440, 110, 16);
        lblSuccess2.setVisible(false);
        add(lblSuccess2);
        
        this.lblExistsU1 = new JLabel("Username Already Exists");
        lblExistsU1.setBounds(54, 468, 155, 16);
        lblExistsU1.setVisible(false);
        add(lblExistsU1);
        
        this.lblExistsU2 = new JLabel("Username Already Exists");
        lblExistsU2.setBounds(408, 468, 155, 16);
        lblExistsU2.setVisible(false);
        add(lblExistsU2);
    }
     
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object src = e.getSource();
          if( src == btnBegin ) {
             control.change("SETTINGS");
          } else if (src == btnViewStatistics) {
              control.change("STATISTICS");
          } else if (src == btnLoginU1) {
              if (csv.verifyAccount(textField.getText(), passwordFieldU1.getText())) {
                  u1Verified = true;
                  lblSuccess1.setVisible(true);
                  lblFail1.setVisible(false);
                  lblExistsU1.setVisible(false);
              } else {
                  lblFail1.setVisible(true);
                  lblExistsU1.setVisible(false);
              }
          } else if (src == btnLoginU2) {
              if (csv.verifyAccount(textField_3.getText(), passwordFieldU2.getText())) {
                  u2Verified = true;
                  lblSuccess2.setVisible(true);
                  lblFail2.setVisible(false);
                  lblExistsU2.setVisible(false);
              } else {
                  lblFail2.setVisible(true);
                  lblExistsU2.setVisible(false);
              }
          } else if (src == btnCreateU1) {
              if (csv.addAccount(textField.getText(), passwordFieldU1.getText())) {
            	  u1Verified = true;
            	  lblSuccess1.setVisible(true);
            	  lblExistsU1.setVisible(false);
              } else {
            	  lblExistsU1.setVisible(true);
              }
          } else if (src == btnCreateU2) {
        	  if (csv.addAccount(textField.getText(), passwordFieldU2.getText())) {
            	  u2Verified = true;
            	  lblSuccess2.setVisible(true);
            	  lblExistsU2.setVisible(false);
              } else {
            	  lblExistsU2.setVisible(true);
              }
          } 
           
          //confirms both logins and allows begin to be clicked
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
        lblSuccess1.setVisible(false);
        lblFail1.setVisible(false);
        lblSuccess2.setVisible(false);
        lblFail2.setVisible(false);
        lblExistsU1.setVisible(false);
        lblExistsU2.setVisible(false);
         
    }
}
