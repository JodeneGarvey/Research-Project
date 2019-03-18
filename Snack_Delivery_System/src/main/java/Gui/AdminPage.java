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
		btnEmployeeRecord.setBounds(74, 255, 235, 59);
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
		btnSnackRecord.setBounds(641, 255, 208, 59);
		contentPane.add(btnSnackRecord);
		
		JButton btnStudentRecord = new JButton("STUDENT RECORD");
		btnStudentRecord.setForeground(new Color(0, 204, 255));
		btnStudentRecord.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnStudentRecord.setBounds(74, 437, 235, 59);
		contentPane.add(btnStudentRecord);
		
		JButton btnOrderRecord = new JButton("ORDER RECORD");
		btnOrderRecord.setForeground(new Color(0, 204, 255));
		btnOrderRecord.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnOrderRecord.setBounds(641, 437, 208, 59);
		contentPane.add(btnOrderRecord);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminPage().dispose();
				
			}
		});
		btnHome.setForeground(new Color(0, 204, 255));
		btnHome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnHome.setBounds(382, 597, 145, 51);
		contentPane.add(btnHome);
	}

}
