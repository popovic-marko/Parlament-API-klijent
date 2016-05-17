package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;

public class PoslaniciGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;


	/**
	 * Create the frame.
	 */
	public PoslaniciGUI() {
		setTitle("Parlament Members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel istocniPanel = new JPanel();
		istocniPanel.setPreferredSize(new Dimension(120, 10));
		contentPane.add(istocniPanel, BorderLayout.EAST);
		istocniPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnGetMembers = new JButton("GET Members");
		btnGetMembers.setPreferredSize(new Dimension(113, 23));
		istocniPanel.add(btnGetMembers);
		
		JButton btnFillTable = new JButton("Fill table");
		btnFillTable.setPreferredSize(new Dimension(113, 23));
		istocniPanel.add(btnFillTable);
		
		JButton btnUpdateMembers = new JButton("Update members");
		istocniPanel.add(btnUpdateMembers);
		
		JPanel juzniPanel = new JPanel();
		juzniPanel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		juzniPanel.setPreferredSize(new Dimension(10, 60));
		contentPane.add(juzniPanel, BorderLayout.SOUTH);
		juzniPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		juzniPanel.add(scrollPane_1);
		
		JTextArea taStatus = new JTextArea();
		taStatus.setEditable(false);
		scrollPane_1.setViewportView(taStatus);
	}

}
