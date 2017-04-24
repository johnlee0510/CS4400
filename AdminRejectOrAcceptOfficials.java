import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRejectOrAcceptOfficials extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private int selectedRowIndex;

	/**
	 * Create the frame.
	 */
	public AdminRejectOrAcceptOfficials() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Pending City Official Accounts");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(281, 30, 365, 33);
		contentPane.add(lblNewLabel);

		String[] title = { "Select", "Username", "Email", "City", "State", "Title" };

		int x = 0;

		PreparedStatement stmt, stmt1 = null;
		ResultSet rs, rs1 = null;
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.err.print("ClassNotFoundException: ");
		}
		try {
			String sql = "SELECT COUNT(*) num FROM `CityOfficial` WHERE approval IS NULL";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt("num"); // number of data
			}
			rs.close();
			stmt.close();
			Object[][] data = new Object[x][6];

			String sql1 = "SELECT approval, username, emailAddress, city, state, title FROM CityOfficial WHERE approval IS NULL";
			stmt1 = conn.prepareStatement(sql1);
			rs1 = stmt1.executeQuery();
			int i = 0;
			while (rs1.next()) {
				data[i][0] = false;
				data[i][1] = rs1.getString("username");
				data[i][2] = rs1.getString("emailAddress");
				data[i][3] = rs1.getString("city");
				data[i][4] = rs1.getString("state");
				data[i][5] = rs1.getString("title");
				i++;
			}
			JScrollPane scrollPane = new JScrollPane();

			table = new JTable();
			table.setModel(new DefaultTableModel(data, title) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] { Boolean.class, String.class, String.class, String.class,
						String.class, String.class };

				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			table.setRowSorter(sorter);
			scrollPane.add(table);
			scrollPane.setViewportView(table);
			scrollPane.setBounds(12, 100, 958, 236);
			contentPane.add(scrollPane);
			rs1.close();
			stmt1.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(131, 388, 150, 41);
		contentPane.add(btnBackToMenu);

		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
				page.setVisible(true);
				page.setResizable(false);
			}
		});

		JButton btnReject = new JButton("Reject");
		btnReject.setBounds(417, 388, 150, 41);
		contentPane.add(btnReject);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(702, 388, 150, 41);
		contentPane.add(btnAccept);

		btnReject.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Boolean checked = false; 
				for (int i = 0; i <table.getRowCount(); i++) {
					checked = (Boolean) table.getValueAt(i, 0);
					if (checked) {
						String value = (String) table.getValueAt(i, 1);
						try {
						String sql2 = "Update CityOfficial SET approval = 0 WHERE username = '" + value + "'";
						ConnectDB db = new ConnectDB();
						Connection conn = db.getConnection();
						PreparedStatement statement = conn.prepareStatement(sql2);
						statement.executeUpdate();
						dispose();
						SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
						page.setVisible(true);
						page.setResizable(false);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		});

		btnAccept.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Boolean checked = false; 
				for (int i = 0; i <table.getRowCount(); i++) {
					checked = (Boolean) table.getValueAt(i, 0);
					if (checked) {
						String value = (String) table.getValueAt(i, 1);
						try {
						String sql2 = "Update CityOfficial SET approval = 1 WHERE username = '" + value + "'";
						ConnectDB db = new ConnectDB();
						Connection conn = db.getConnection();
						PreparedStatement statement = conn.prepareStatement(sql2);
						statement.executeUpdate();
						dispose();
						SuperChooseFunctionalityPage page = new SuperChooseFunctionalityPage();
						page.setVisible(true);
						page.setResizable(false);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		});
	}

	public DefaultTableModel getmodel() {
		return model;
	}
}
