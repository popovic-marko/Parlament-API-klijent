package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import models.PoslaniciTableModel;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class PoslaniciGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabelaPoslanici;
	private JPanel istocniPanel;
	private JPanel juzniPanel;
	private JScrollPane scrollPane_1;
	private JTextArea taStatus;
	private JButton btnGetMember;
	private JButton btnFillTable;
	private JButton btnUpdateMembers;


	/**
	 * Create the frame.
	 */
	public PoslaniciGUI() {
		setTitle("Parlament Members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getIstocniPanel(), BorderLayout.EAST);
		contentPane.add(getJuzniPanel(), BorderLayout.SOUTH);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTabelaPoslanici());
		}
		return scrollPane;
	}
	private JTable getTabelaPoslanici() {
		if (tabelaPoslanici == null) {
			tabelaPoslanici = new JTable();
			tabelaPoslanici.setModel(new PoslaniciTableModel(GUIKontroler.vratiSvePoslanike()));
		}
		return tabelaPoslanici;
	}
	private JPanel getIstocniPanel() {
		if (istocniPanel == null) {
			istocniPanel = new JPanel();
			istocniPanel.setPreferredSize(new Dimension(150, 10));
			istocniPanel.add(getBtnGetMember());
			istocniPanel.add(getBtnFillTable());
			istocniPanel.add(getBtnUpdateMembers());
		}
		return istocniPanel;
	}
	private JPanel getJuzniPanel() {
		if (juzniPanel == null) {
			juzniPanel = new JPanel();
			juzniPanel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			juzniPanel.setPreferredSize(new Dimension(10, 75));
			juzniPanel.setLayout(new BorderLayout(0, 0));
			juzniPanel.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return juzniPanel;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTaStatus());
		}
		return scrollPane_1;
	}
	private JTextArea getTaStatus() {
		if (taStatus == null) {
			taStatus = new JTextArea();
			taStatus.setEditable(false);
		}
		return taStatus;
	}
	private JButton getBtnGetMember() {
		if (btnGetMember == null) {
			btnGetMember = new JButton("GET members\r\n");
			btnGetMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						GUIKontroler.preuzmiPoslanike(taStatus);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(getContentPane(), "Greska prilikom uspostavljenja konekcije sa serverom.", "Greska", JOptionPane.ERROR_MESSAGE);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getContentPane(), "Greska prilikom uspostavljenja preuzimanja datuma.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnGetMember.setPreferredSize(new Dimension(145, 23));
		}
		return btnGetMember;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
			btnFillTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						GUIKontroler.ucitajPoslanikeIzJson(tabelaPoslanici, taStatus);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(getContentPane(), "Greska prilikom ucitavanja poslanika.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnFillTable.setPreferredSize(new Dimension(145, 23));
		}
		return btnFillTable;
	}
	private JButton getBtnUpdateMembers() {
		if (btnUpdateMembers == null) {
			btnUpdateMembers = new JButton("Update members");
			btnUpdateMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						GUIKontroler.sacuvajIzmenjenePoslanike(tabelaPoslanici, taStatus);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(getContentPane(), "Greska prilikom cuvanja izmenjenih podataka.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnUpdateMembers.setPreferredSize(new Dimension(145, 23));
		}
		return btnUpdateMembers;
	}
}
