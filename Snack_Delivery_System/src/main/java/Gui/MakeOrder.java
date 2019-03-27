package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Item.Snack;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class MakeOrder extends JFrame {

	private JPanel contentPane;
	private JFrame Orders;
	private JTextField snackName;
	private JTextField snackPrice;
	private JLabel snackImage;
	private ImageIcon icon;
	private JList list;
	private JTextField textField_quantity;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeOrder window = new MakeOrder();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MakeOrder() {
		initialize();
		showSnack(pos);
		DisplayOrderID();
	}
	
	public void DisplayOrderID() {
		Random ran = new Random();
		int n = ran.nextInt(1000000)+1;
		String id = String.valueOf(n);
		order_id.setText(id);
	}

	private void showSnack(int index) {
		snackName.setText(getSnackList().get(index).getName());
		
		snackPrice.setText(Float.toString(getSnackList().get(index).getPrice()));
		
		
		ImageIcon icon = new ImageIcon(getSnackList().get(index).getImage());
		
		Image image = icon.getImage().getScaledInstance(snackImage.getWidth(), snackImage.getHeight(), Image.SCALE_SMOOTH);
		
		snackImage.setIcon(new ImageIcon(image));
		
	}



	



	int pos =0;
	private JTextField textField_name;
	private JTextField textField_id;
	private JTextField textField_Location;
	private JTextField textField_cost;
	private JTextField textField;
	private JTextField order_id;
	
	
	public Connection getConnection() {
		Connection con = null;
				try {
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		return con;
	}
	
	
	public List<Snack> getSnackList(){
		try {
			Connection connect = getConnection();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM snack");
			List<Snack> list = new ArrayList<Snack>();
			Snack snack;
			
			while(rs.next()) {
				snack = new Snack(rs.getString("name"),rs.getFloat("price"),rs.getBytes("image"));
				
				list.add(snack);
			}
			return list;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Orders = new JFrame();
		Orders.setBounds(100, 100, 1341, 837);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Orders.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Orders.getContentPane().setLayout(null);
		Orders.setVisible(true);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1319, 781);
		Orders.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel snackImage = new JLabel("");
		snackImage.setBounds(15, 363, 440, 264);
		panel.add(snackImage);
		
		JLabel lblSnackName = new JLabel("Snack Name:");
		lblSnackName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSnackName.setBounds(484, 344, 116, 20);
		panel.add(lblSnackName);
		
		snackName = new JTextField();
		snackName.setBounds(615, 341, 178, 26);
		panel.add(snackName);
		snackName.setColumns(10);
		
		JLabel lblSnackPrice = new JLabel("Snack Price:");
		lblSnackPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSnackPrice.setBounds(484, 405, 116, 20);
		panel.add(lblSnackPrice);
		
		snackPrice = new JTextField();
		snackPrice.setBounds(615, 402, 178, 26);
		panel.add(snackPrice);
		snackPrice.setColumns(10);
		
		JButton btnNewButton = new JButton("FIRST");
		btnNewButton.setForeground(new Color(0, 204, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pos = 0;
				showSnack(pos);
			}
		});
		btnNewButton.setBounds(15, 643, 115, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NEXT");
		btnNewButton_1.setForeground(new Color(0, 204, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos++;
				if(pos >= getSnackList().size()) {
					pos = getSnackList().size()-1;
				}
				showSnack(pos);
			}
		});
		btnNewButton_1.setBounds(164, 643, 115, 29);
		panel.add(btnNewButton_1);
		
		JButton btnPrevious = new JButton("PREVIOUS");
		btnPrevious.setForeground(new Color(0, 204, 255));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos--;
				if(pos < 0) {
					pos = 0;
				}
				showSnack(pos);
			}
		});
		btnPrevious.setBounds(316, 643, 115, 29);
		panel.add(btnPrevious);
		
		JButton btnLast = new JButton("LAST");
		btnLast.setForeground(new Color(0, 204, 255));
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos = getSnackList().size()-1;
				showSnack(pos);
			}
		});
		btnLast.setBounds(460, 643, 115, 29);
		panel.add(btnLast);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(15, 228, 69, 20);
		panel.add(lblName);
		
		JLabel lblOrderPage = new JLabel("Welcome to John's Shop");
		lblOrderPage.setForeground(new Color(0, 204, 204));
		lblOrderPage.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblOrderPage.setBounds(460, 16, 335, 29);
		panel.add(lblOrderPage);
		
		JLabel lblIdNumber = new JLabel("ID Number:");
		lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdNumber.setBounds(15, 269, 108, 20);
		panel.add(lblIdNumber);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocation.setBounds(15, 320, 83, 20);
		panel.add(lblLocation);
		
		textField_name = new JTextField();
		textField_name.setBounds(90, 225, 199, 26);
		panel.add(textField_name);
		textField_name.setColumns(10);
		
		textField_id = new JTextField();
		textField_id.setText("");
		textField_id.setBounds(118, 266, 171, 26);
		panel.add(textField_id);
		textField_id.setColumns(10);
		
		textField_Location = new JTextField();
		textField_Location.setText("");
		textField_Location.setBounds(99, 317, 199, 26);
		panel.add(textField_Location);
		textField_Location.setColumns(10);
		
		final JList list = new JList();
		list.setBounds(902, 184, 253, 270);
		panel.add(list);
		
		JLabel lblOrderList = new JLabel("Order List");
		lblOrderList.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrderList.setBounds(977, 148, 96, 20);
		panel.add(lblOrderList);
		
		JLabel lblTotalCost = new JLabel("Total Cost:");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalCost.setBounds(810, 536, 108, 20);
		panel.add(lblTotalCost);
		
		textField_cost = new JTextField();
		textField_cost.setBounds(916, 533, 253, 26);
		panel.add(textField_cost);
		textField_cost.setColumns(10);
		
		final DefaultListModel DLM = new DefaultListModel();
		final JButton btnAddSnack = new JButton("ADD SNACK");
		btnAddSnack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float total = 0;
					
				DLM.addElement(snackName.getText());
				list.setModel(DLM); 
				float price = Float.parseFloat(snackPrice.getText());
				int quantity = Integer.parseInt(textField_quantity.getText());
				textField_quantity.setText("");
				total += price*quantity;
				textField_cost.setText(""+total);
			}
		});
		btnAddSnack.setForeground(new Color(0, 204, 255));
		btnAddSnack.setBounds(67, 711, 154, 29);
		panel.add(btnAddSnack);
		
		JButton btnRemoveSnack = new JButton("REMOVE SNACK");
		btnRemoveSnack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLM.removeElement(snackName.getText());
				list.getSelectedValue();
			}
		});
		btnRemoveSnack.setForeground(new Color(0, 204, 255));
		btnRemoveSnack.setBounds(385, 711, 159, 29);
		panel.add(btnRemoveSnack);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(484, 485, 91, 20);
		panel.add(lblQuantity);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DLM.clear();
				list.setModel(DLM);
				textField_cost.setText("");
			}
		});
		btnClear.setForeground(new Color(0, 204, 255));
		btnClear.setBounds(895, 643, 115, 29);
		panel.add(btnClear);
		
		JButton btnPlaceOrder = new JButton("PLACE ORDER");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					PreparedStatement ps = null;
					int stud_id = Integer.parseInt(textField_id.getText());
					String Stud_name = textField_name.getText();
					String location = textField_Location.getText();
					int orderid = Integer.parseInt(order_id.getText());
					int quantity = Integer.parseInt(textField_quantity.getText());
					float Total_cost = Float.parseFloat(textField_cost.getText());
					String Status = "Order Pending";
					
					String stud = "Insert into student values ('"+stud_id+"','"+Stud_name+"','"+location+"')";
					ps.executeUpdate(stud);
					
					String order = "Insert into order values ('"+orderid+"','"+quantity+"','"+Total_cost+"','"+location+"','"+Status+"')";
					ps.executeUpdate(order);
					
					String stud_order = "Insert into studentorder values ('"+stud_id+"','"+orderid+"')";
					ps.executeUpdate(stud_order);
					
					JOptionPane.showMessageDialog(null, "Your Order has been placed. Delivery in 20 minutes");
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnPlaceOrder.setForeground(new Color(0, 204, 255));
		btnPlaceOrder.setBounds(1056, 643, 154, 29);
		panel.add(btnPlaceOrder);
		
		textField_quantity = new JTextField();
		textField_quantity.setBounds(613, 482, 180, 26);
		panel.add(textField_quantity);
		textField_quantity.setColumns(10);
		
		JLabel lblOrderId = new JLabel("Order ID:");
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOrderId.setBounds(15, 118, 96, 20);
		panel.add(lblOrderId);
		
		order_id = new JTextField();
		order_id.setBounds(118, 115, 146, 26);
		panel.add(order_id);
		order_id.setColumns(10);
	}
}
