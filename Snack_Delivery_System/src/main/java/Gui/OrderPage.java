package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class OrderPage extends JFrame {

	private JPanel contentPane;
	private JTextField order_id;
	private JTextField student_id;
	private JTextField location;
	private JTextField snack;
	private JTextField quantity;
	private JTextField total;
	private JTable table;
	protected AbstractButton rdbtnDelivered;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPage frame = new OrderPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void OrderList() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
			
			
			String sql = "SELECT shopdb.order.order_id, studentorder.student_id, studentorder.snack, shopdb.order.quantity, shopdb.order.total_cost, shopdb.order.location, shopdb.order.status From shopdb.order INNER JOIN studentorder ON shopdb.order.order_id = studentorder.order_id";
			
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public OrderPage() {
		OrderList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1339, 831);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrderPage = new JLabel("ORDER PAGE");
		lblOrderPage.setForeground(new Color(0, 204, 204));
		lblOrderPage.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOrderPage.setBounds(493, 16, 174, 45);
		contentPane.add(lblOrderPage);
		
		JLabel lblOrderid = new JLabel("Order ID");
		lblOrderid.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrderid.setBounds(823, 126, 89, 20);
		contentPane.add(lblOrderid);
		
		order_id = new JTextField();
		order_id.setBounds(927, 123, 184, 26);
		contentPane.add(order_id);
		order_id.setColumns(10);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStudentId.setBounds(823, 181, 89, 20);
		contentPane.add(lblStudentId);
		
		student_id = new JTextField();
		student_id.setBounds(927, 178, 184, 26);
		contentPane.add(student_id);
		student_id.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocation.setBounds(823, 241, 80, 20);
		contentPane.add(lblLocation);
		
		location = new JTextField();
		location.setText("");
		location.setBounds(927, 238, 184, 26);
		contentPane.add(location);
		location.setColumns(10);
		
		JLabel lblSnackName = new JLabel("Snack Name");
		lblSnackName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSnackName.setBounds(823, 304, 119, 20);
		contentPane.add(lblSnackName);
		
		snack = new JTextField();
		snack.setText("");
		snack.setBounds(929, 301, 182, 26);
		contentPane.add(snack);
		snack.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(823, 360, 89, 20);
		contentPane.add(lblQuantity);
		
		quantity = new JTextField();
		quantity.setBounds(927, 357, 184, 26);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalCost.setBounds(823, 414, 89, 20);
		contentPane.add(lblTotalCost);
		
		total = new JTextField();
		total.setText("");
		total.setBounds(927, 411, 184, 26);
		contentPane.add(total);
		total.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStatus.setBounds(823, 470, 69, 20);
		contentPane.add(lblStatus);
		
		JRadioButton rdbtnPending = new JRadioButton("Pending");
		rdbtnPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnPending.isSelected()) {
					 rdbtnDelivered.setSelected(false);
				}
					
			}
		});
		rdbtnPending.setBackground(new Color(175, 238, 238));
		rdbtnPending.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnPending.setBounds(927, 470, 119, 29);
		contentPane.add(rdbtnPending);
		
		JRadioButton rdbtnDelivered = new JRadioButton("Delivered");
		rdbtnDelivered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDelivered.isSelected()) {
					rdbtnPending.setSelected(false);
				}
			}
		});
		rdbtnDelivered.setBackground(new Color(175, 238, 238));
		rdbtnDelivered.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnDelivered.setBounds(927, 512, 125, 29);
		contentPane.add(rdbtnDelivered);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		scrollPane.setBounds(62, 100, 677, 459);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedrow = table.getSelectedRow();
				
				order_id.setText(model.getValueAt(selectedrow, 0).toString());
				student_id.setText(model.getValueAt(selectedrow, 1).toString());
				location.setText(model.getValueAt(selectedrow, 2).toString());
				snack.setText(model.getValueAt(selectedrow, 3).toString());
				quantity.setText(model.getValueAt(selectedrow, 4).toString());
				total.setText(model.getValueAt(selectedrow, 5).toString());
				String Status = model.getValueAt(selectedrow, 6).toString();
				if(Status.equals("Pending")) {
					rdbtnPending.setSelected(true);
				}else {
					rdbtnDelivered.setSelected(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnReturn = new JButton("RETURN");
		btnReturn.setBackground(new Color(240, 240, 240));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AdminPage().setVisible(true);
				new OrderPage().dispose();
			}
		});
		btnReturn.setForeground(new Color(0, 204, 255));
		btnReturn.setBounds(309, 663, 115, 29);
		contentPane.add(btnReturn);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					String status;
					int row = table.getSelectedRow();
					String value = (table.getModel().getValueAt(row, 0).toString());
					String sql = "Update shopdb.order SET status=? where order_id="+value;
					
					PreparedStatement ps = con.prepareStatement(sql);
					
					if(rdbtnPending.isSelected()) {
						status = "Pending";
					}else {
						status = "Delivered";
					}
					ps.setString(1, status);
					
					ps.executeUpdate();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setRowCount(0);
					OrderList();
					JOptionPane.showMessageDialog(null, "Record Updated");
					con.close();
				}catch(Exception exp) {
					JOptionPane.showMessageDialog(null, exp);
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 204, 255));
		btnUpdate.setBounds(943, 663, 115, 29);
		contentPane.add(btnUpdate);
		
		OrderList();
		
	}
}
