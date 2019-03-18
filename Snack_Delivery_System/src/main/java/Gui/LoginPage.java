package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN PAGE");
		lblNewLabel.setForeground(new Color(0, 204, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(289, 37, 180, 46);
		contentPane.add(lblNewLabel);
		
		JLabel Username = new JLabel("Username");
		Username.setFont(new Font("Tahoma", Font.BOLD, 18));
		Username.setBounds(114, 170, 95, 35);
		contentPane.add(Username);
		
		textField = new JTextField();
		textField.setBounds(246, 171, 256, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.BOLD, 18));
		Password.setBounds(120, 281, 112, 27);
		contentPane.add(Password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(246, 278, 256, 35);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					String sql = "Select * from employee where username=? and password=?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, textField.getText());
					ps.setString(2, passwordField.getText());
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Successfully");
						new AdminPage().setVisible(true);
						new LoginPage().dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect Username and Password....");
					}
					con.close();
				}catch(Exception e) {
					System.out.println(e);
				}
			}
		});
		btnLogin.setForeground(new Color(0, 204, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnLogin.setBounds(143, 400, 149, 46);
		contentPane.add(btnLogin);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				passwordField.setText("");
				
			}
		});
		btnReset.setForeground(new Color(0, 204, 255));
		btnReset.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnReset.setBounds(400, 400, 167, 45);
		contentPane.add(btnReset);
	}
}
