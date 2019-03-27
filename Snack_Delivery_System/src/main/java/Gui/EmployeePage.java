package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class EmployeePage extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JTextField eid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePage frame = new EmployeePage();
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
	public EmployeePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeePage = new JLabel("EMPLOYEE PAGE");
		lblEmployeePage.setForeground(new Color(0, 204, 204));
		lblEmployeePage.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblEmployeePage.setBounds(500, 16, 221, 46);
		contentPane.add(lblEmployeePage);
		
		JLabel lblEmployeeUsername = new JLabel("Employee Username");
		lblEmployeeUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmployeeUsername.setBounds(28, 216, 169, 20);
		contentPane.add(lblEmployeeUsername);
		
		username = new JTextField();
		username.setBounds(253, 214, 177, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblEmployeePassword = new JLabel("Employee Password");
		lblEmployeePassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmployeePassword.setBounds(28, 316, 169, 20);
		contentPane.add(lblEmployeePassword);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					String sql = "Insert into employee values (?,?,?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(eid.getText()));
					ps.setString(2, username.getText());
					ps.setString(3, password.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Inserted");
					con.close();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnSave.setForeground(new Color(0, 204, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnSave.setBounds(57, 464, 115, 29);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					String sql ="Delete from employee where id = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, eid.getText());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Record Deleted");
				}catch(Exception d) {
					JOptionPane.showMessageDialog(null, d);
				}
			}
		});
		btnDelete.setForeground(new Color(0, 204, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnDelete.setBounds(484, 159, 115, 29);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					String sql = "Update employee set username = ?, password = ? where id = ?";
					
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, username.getText());
					ps.setString(2, password.getText());
					ps.setInt(3, Integer.parseInt(eid.getText()));
					
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					con.close();
				}catch(Exception exp) {
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 204, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnUpdate.setBounds(484, 316, 115, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					String sql = "Select id,username from employee";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					
				}catch(Exception i) {
					JOptionPane.showMessageDialog(null, i);
				}
			}
		});
		btnDisplay.setForeground(new Color(0, 204, 255));
		btnDisplay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnDisplay.setBounds(484, 239, 115, 29);
		contentPane.add(btnDisplay);
		
		password = new JTextField();
		password.setText("");
		password.setBounds(257, 314, 173, 26);
		contentPane.add(password);
		password.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(28, 128, 153, 20);
		contentPane.add(lblNewLabel);
		
		eid = new JTextField();
		eid.setBounds(253, 126, 177, 26);
		contentPane.add(eid);
		eid.setColumns(10);
		
		JScrollPane tbl = new JScrollPane();
		tbl.setBounds(648, 132, 536, 322);
		contentPane.add(tbl);
		
		table = new JTable();
		tbl.setViewportView(table);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eid.setText("");
				username.setText("");
				password.setText("");
			}
		});
		btnReset.setForeground(new Color(0, 204, 255));
		btnReset.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnReset.setBounds(278, 465, 115, 29);
		contentPane.add(btnReset);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					String sql = "Select * from employee where id = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(eid.getText()));
					
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						username.setText(rs.getString("username"));
						password.setText(rs.getString("password"));
					}else {
						JOptionPane.showMessageDialog(null, "Record Not Found");
					}
				}catch(Exception s) {
					JOptionPane.showMessageDialog(null, s);
				}
			}
		});
		btnSearch.setForeground(new Color(0, 204, 255));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnSearch.setBounds(484, 389, 115, 29);
		contentPane.add(btnSearch);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeePage().dispose();
				new AdminPage().setVisible(true);
			}
		});
		btnBack.setForeground(new Color(0, 204, 255));
		btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnBack.setBounds(484, 465, 115, 29);
		contentPane.add(btnBack);
	}
}