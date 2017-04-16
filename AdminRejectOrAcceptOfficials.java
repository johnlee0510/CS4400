import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pending City Official Accounts");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(215, 30, 365, 33);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		model = (DefaultTableModel) table.getModel();
		selectedRowIndex = table.getSelectedRow();
		table.setBounds(81, 89, 619, 262);
		contentPane.add(table);
		
		JButton btnBackToMenu = new JButton("Back to menu");
		btnBackToMenu.setBounds(81, 388, 150, 41);
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
		btnReject.setBounds(322, 388, 150, 41);
		contentPane.add(btnReject);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(550, 388, 150, 41);
		contentPane.add(btnAccept);
		
		btnReject.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				((DefaultTableModel)table.getModel()).removeRow(selectedRowIndex);
			}
		});
		
		btnAccept.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				((DefaultTableModel)table.getModel()).removeRow(selectedRowIndex);
			}
		});
	}
}
