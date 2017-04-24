import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.TableRowSorter;

import javax.swing.table.TableModel;

public class AdminRejectOrAccept extends JFrame {

	/**
	 * this is a class where the Administrator rejects or accepts an existing data data point
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;

	/**
	 * Create the frame.
	 */
	public AdminRejectOrAccept() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] title = { "Select", "POI location", "Data type", "Data value", "Time&date of data reading" };

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
			String sql = "SELECT COUNT(*) num FROM `DataPoint` WHERE accepted IS NULL";
			ConnectDB db = new ConnectDB();
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt("num"); // number of data
			}

			stmt.close();
			rs.close();
			Object[][] data = new Object[x][5];

			String sql1 = "SELECT * FROM DataPoint WHERE accepted IS NULL";
			stmt1 = conn.prepareStatement(sql1);
			rs1 = stmt1.executeQuery();
			int i = 0;
			while (rs1.next()) {
				data[i][0] = false;
				data[i][1] = rs1.getString("locName");
				data[i][2] = rs1.getString("dataType");
				data[i][3] = rs1.getString("dataValue");
				String dateTime = rs1.getTimestamp("dateTime").toString();
				data[i][4] = dateTime;
				i++;
			}
			rs1.close();
			stmt1.close();
			conn.close();
			JScrollPane scrollPane = new JScrollPane();

			table_1 = new JTable();
			table_1.setModel(new DefaultTableModel(data, title) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] { Boolean.class, String.class, String.class, String.class,
						String.class };

				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_1.getModel());
			table_1.setRowSorter(sorter);

			scrollPane.add(table_1);
			scrollPane.setViewportView(table_1);
			scrollPane.setBounds(12, 100, 858, 236);
			contentPane.add(scrollPane);

		} catch (SQLException e) {
			e.printStackTrace();

		}
		JLabel lblPendingDataPoints = new JLabel("Pending Data Points ");
		lblPendingDataPoints.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPendingDataPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPendingDataPoints.setBounds(318, 26, 251, 33);
		contentPane.add(lblPendingDataPoints);

		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(80, 398, 150, 41);
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
		btnReject.setBounds(376, 398, 150, 41);
		contentPane.add(btnReject);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(665, 398, 150, 41);
		contentPane.add(btnAccept);

		btnReject.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					ConnectDB db = new ConnectDB();
					Connection conn;
					conn = db.getConnection();
					PreparedStatement stmt;
					Boolean checked = false;
					for (int i = 0; i < table_1.getRowCount(); i++) {
						checked = (Boolean) table_1.getValueAt(i, 0);
						if (checked) {
							// String[] values = new String[2];
							String locName = (String) table_1.getValueAt(i, 1);
							String date = (String) table_1.getValueAt(i, 4);
							String sql2 = "Update DataPoint SET accepted = 0" + " WHERE locName = '" + locName
									+ "' AND dateTime = '" + date + "';";
							stmt = conn.prepareStatement(sql2);
							stmt.executeUpdate();
							stmt.close();
							}

					}
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SuperChooseFunctionalityPage frame = new SuperChooseFunctionalityPage();
				frame.setVisible(true);
				frame.setResizable(false);
				dispose();
			}
		});

		List<String[]> list = new ArrayList<>();
		btnAccept.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					ConnectDB db = new ConnectDB();
					Connection conn;
					conn = db.getConnection();
					PreparedStatement stmt;
					Boolean checked = false;
					for (int i = 0; i < table_1.getRowCount(); i++) {
						checked = (Boolean) table_1.getValueAt(i, 0);
						if (checked) {
							// String[] values = new String[2];
							String locName = (String) table_1.getValueAt(i, 1);
							String date = (String) table_1.getValueAt(i, 4);
							String sql2 = "Update DataPoint SET accepted = 1" + " WHERE locName = '" + locName
									+ "' AND dateTime = '" + date + "';";
							stmt = conn.prepareStatement(sql2);
							stmt.executeUpdate();
							stmt.close();
							}

					}
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				SuperChooseFunctionalityPage frame = new SuperChooseFunctionalityPage();
				frame.setVisible(true);
				frame.setResizable(false);
				dispose();
			}
		});
	}

}
