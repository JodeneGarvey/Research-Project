package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 813);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministrationPage = new JLabel("ADMINISTRATION PAGE");
		lblAdministrationPage.setForeground(new Color(0, 204, 204));
		lblAdministrationPage.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAdministrationPage.setBounds(286, 66, 386, 59);
		contentPane.add(lblAdministrationPage);
		
		JButton btnEmployeeRecord = new JButton("EMPLOYEE RECORD");
		btnEmployeeRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EmployeePage().setVisible(true);
				new AdminPage().dispose();
			}
		});
		btnEmployeeRecord.setForeground(new Color(0, 204, 255));
		btnEmployeeRecord.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnEmployeeRecord.setBounds(336, 198, 235, 59);
		contentPane.add(btnEmployeeRecord);
		
		JButton btnSnackRecord = new JButton("SNACK RECORD");
		btnSnackRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SnackPage().setVisible(true);
				new AdminPage().dispose();
			}
		});
		btnSnackRecord.setForeground(new Color(0, 204, 255));
		btnSnackRecord.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnSnackRecord.setBounds(336, 329, 235, 59);
		contentPane.add(btnSnackRecord);
		
		JButton btnOrderRecord = new JButton("ORDER RECORD");
		btnOrderRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OrderPage().setVisible(true);
				new AdminPage().dispose();
			}
		});
		btnOrderRecord.setForeground(new Color(0, 204, 255));
		btnOrderRecord.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnOrderRecord.setBounds(336, 453, 235, 59);
		contentPane.add(btnOrderRecord);
		
		JButton btnHome = new JButton("EXIT");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnHome.setForeground(new Color(0, 204, 255));
		btnHome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnHome.setBounds(336, 597, 235, 51);
		contentPane.add(btnHome);
	}

}
