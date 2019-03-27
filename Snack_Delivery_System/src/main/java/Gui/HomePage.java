package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Gui.MakeOrder;

public class HomePage extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1015, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("JOHN'S TUCK SHOP");
		lblNewLabel.setForeground(new Color(0, 204, 204));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(286, 107, 327, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnOrder = new JButton("ORDER");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new MakeOrder().setVisible(true);
				frame.dispose();
				
				
			}
		});
		btnOrder.setForeground(new Color(0, 204, 255));
		btnOrder.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnOrder.setBounds(366, 263, 141, 59);
		frame.getContentPane().add(btnOrder);
		
		JButton btnAdmin = new JButton("ADMIN");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoginPage().setVisible(true);
				frame.dispose();
			}
		});
		btnAdmin.setForeground(new Color(0, 204, 255));
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnAdmin.setBounds(366, 421, 141, 59);
		frame.getContentPane().add(btnAdmin);
		
		JButton btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setForeground(new Color(0, 204, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(366, 555, 141, 59);
		frame.getContentPane().add(btnNewButton);
	}
}
