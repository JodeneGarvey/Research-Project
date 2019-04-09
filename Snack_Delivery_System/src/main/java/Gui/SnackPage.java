package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class SnackPage extends JFrame {

	private JPanel contentPane;
	private JTextField sname;
	private JTextField price;
	
	String filename = null;
	byte[] snackImage = null;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnackPage frame = new SnackPage();
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
	public SnackPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1301, 780);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSnackPage = new JLabel("SNACK PAGE");
		lblSnackPage.setForeground(new Color(0, 204, 204));
		lblSnackPage.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblSnackPage.setBounds(297, 27, 216, 37);
		contentPane.add(lblSnackPage);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(81, 161, 69, 20);
		contentPane.add(lblName);
		
		sname = new JTextField();
		sname.setBounds(187, 158, 158, 26);
		contentPane.add(sname);
		sname.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrice.setBounds(81, 259, 69, 20);
		contentPane.add(lblPrice);
		
		price = new JTextField();
		price.setBounds(181, 256, 164, 26);
		contentPane.add(price);
		price.setColumns(10);
		
		final JLabel lbl_image = new JLabel("");
		lbl_image.setBounds(73, 327, 299, 203);
		contentPane.add(lbl_image);
		
		JButton btnChoose = new JButton("CHOOSE");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_image.getWidth(),lbl_image.getHeight(),Image.SCALE_SMOOTH));
				lbl_image.setIcon(imageIcon);
				try {
					File image = new File(filename);
					FileInputStream fis = new FileInputStream(image);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for(int readnum;(readnum=fis.read(buf))!=-1;) {
						bos.write(buf, 0, readnum);
					}
					snackImage = bos.toByteArray();
					
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnChoose.setForeground(new Color(0, 204, 255));
		btnChoose.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnChoose.setBounds(73, 588, 115, 29);
		contentPane.add(btnChoose);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					String sql = "Insert into snack values (?,?,?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, sname.getText());
					ps.setFloat(2, Float.parseFloat(price.getText()));
					ps.setBytes(3, snackImage);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Inserted");
					con.close();
				}catch(Exception s) {
					JOptionPane.showMessageDialog(null, s);
				}
			}
		});
		btnSave.setForeground(new Color(0, 204, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnSave.setBounds(436, 158, 115, 29);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					String sql ="Delete from snack where name = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, sname.getText());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Record Deleted");
				}catch(Exception d) {
					JOptionPane.showMessageDialog(null, d);
				}
			}
		});
		btnDelete.setForeground(new Color(0, 204, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnDelete.setBounds(436, 256, 115, 29);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					
					String sql = "Update snack set price = ?, image = ? where name = ?";
					
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, sname.getText());
					ps.setFloat(2, Float.parseFloat(price.getText()));
					ps.setBytes(3, snackImage);
					
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated");
					con.close();
				}catch(Exception u) {
					JOptionPane.showMessageDialog(null, u);
				}
			}
		});
		btnUpdate.setForeground(new Color(0, 204, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnUpdate.setBounds(436, 361, 115, 29);
		contentPane.add(btnUpdate);
		
		JButton btnSeach = new JButton("SEACH");
		btnSeach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					byte[] imageBytes;
					Image image;
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					String sql = "Select * from snack where name = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, sname.getText());
					
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						price.setText(rs.getString("price"));
						imageBytes= rs.getBytes(3);
						image = getToolkit().createImage(imageBytes);
						ImageIcon icon = new ImageIcon(image);
						lbl_image.setIcon(icon);
						
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Record Not Found");
					}
				}catch(Exception s) {
					JOptionPane.showMessageDialog(null, s);
				}
			}
		});
		btnSeach.setForeground(new Color(0, 204, 255));
		btnSeach.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnSeach.setBounds(436, 463, 115, 29);
		contentPane.add(btnSeach);
		
		JButton btnDisplay = new JButton("DISPLAY");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopdb", "Jodene", "patrice");
					
					String sql = "Select * from snack";
					PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception dis) {
					JOptionPane.showMessageDialog(null, dis);
				}
			}
		});
		btnDisplay.setForeground(new Color(0, 204, 255));
		btnDisplay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnDisplay.setBounds(436, 565, 115, 29);
		contentPane.add(btnDisplay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				sname.setText(model.getValueAt(i, 1).toString());
				price.setText(model.getValueAt(i, 2).toString());
				lbl_image.setText(model.getValueAt(i, 3).toString());
				
				
			}
		});
		scrollPane.setBounds(646, 161, 586, 421);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sname.setText("");
				price.setText("");
				lbl_image.setText(null);
			
			}
		});
		btnReset.setForeground(new Color(0, 204, 255));
		btnReset.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnReset.setBounds(268, 589, 115, 29);
		contentPane.add(btnReset);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SnackPage().dispose();
				new AdminPage().setVisible(true);
			}
		});
		btnBack.setForeground(new Color(0, 204, 255));
		btnBack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnBack.setBounds(170, 679, 115, 29);
		contentPane.add(btnBack);
	}
}
