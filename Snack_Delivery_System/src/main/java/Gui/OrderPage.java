package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Item.Snack;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField_price;
	private JTextField textField_sname;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lbl_simage = new javax.swing.JLabel("");

	
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
	 int pos = 0;
	
	public Connection getConnection() {
		Connection con = null;
		
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return con;
	}
	
	public List<Snack> getSnack(){
		try {
			Connection connect = getConnection();
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("Select * from snack");
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
	
	public void showSnack(int Index) {
		try {
			
			textField_sname.setText(getSnack().get(Index).getName());
			textField_price.setText(Float.toString(getSnack().get(Index).getPrice()));
			ImageIcon icon = new ImageIcon(getSnack().get(Index).getImage());
			Image image = icon.getImage().getScaledInstance(lbl_simage.getWidth(),lbl_simage.getHeight(),Image.SCALE_SMOOTH);
			
			lbl_simage.setIcon(new ImageIcon(image));
			
		
		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}

	
	/**
	 * Create the frame.
	 */
	public OrderPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrderPage = new JLabel("ORDER PAGE");
		lblOrderPage.setForeground(new Color(0, 204, 204));
		lblOrderPage.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblOrderPage.setBounds(311, 16, 163, 20);
		contentPane.add(lblOrderPage);
		
		JLabel lbl_simage = new javax.swing.JLabel("");
		lbl_simage.setBounds(15, 54, 281, 273);
		contentPane.add(lbl_simage);
		
		JLabel Sname = new JLabel("Name");
		Sname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Sname.setBounds(330, 129, 69, 20);
		contentPane.add(Sname);
		
		JLabel Sprice = new JLabel("Price");
		Sprice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Sprice.setBounds(330, 229, 69, 20);
		contentPane.add(Sprice);
		
		textField_price = new JTextField();
		textField_price.setBounds(330, 265, 146, 26);
		contentPane.add(textField_price);
		textField_price.setColumns(10);
		
		textField_sname = new JTextField();
		textField_sname.setBounds(328, 175, 146, 26);
		contentPane.add(textField_sname);
		textField_sname.setColumns(10);
		
		showSnack(pos);
		
		JButton btnFirst = new JButton("FIRST");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pos = 0;
				showSnack(pos);
			}
		});
		btnFirst.setForeground(new Color(0, 204, 255));
		btnFirst.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnFirst.setBounds(37, 391, 115, 29);
		contentPane.add(btnFirst);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos++;
				if(pos>=getSnack().size()) {
					pos = getSnack().size()-1;
					
				}
				showSnack(pos);
			}
		});
		btnNext.setForeground(new Color(0, 204, 255));
		btnNext.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNext.setBounds(200, 391, 115, 29);
		contentPane.add(btnNext);
		
		JButton btnPrevious = new JButton("PREVIOUS");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos--;
				if(pos < 0) {
					pos = 0;
				}
				showSnack(pos);
			}
		});
		btnPrevious.setForeground(new Color(0, 204, 255));
		btnPrevious.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnPrevious.setBounds(355, 391, 163, 29);
		contentPane.add(btnPrevious);
		
		JButton btnLast = new JButton("LAST");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos = getSnack().size() - 1;
				showSnack(pos);
			}
		});
		btnLast.setForeground(new Color(0, 204, 255));
		btnLast.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnLast.setBounds(573, 393, 115, 29);
		contentPane.add(btnLast);
		
		textField = new JTextField();
		textField.setBounds(662, 49, 281, 242);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTotalPrice = new JLabel("Total Price");
		lblTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalPrice.setBounds(672, 307, 115, 20);
		contentPane.add(lblTotalPrice);
		
		textField_1 = new JTextField();
		textField_1.setBounds(790, 307, 146, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
