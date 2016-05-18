package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import domain.Poslanik;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class PoslaniciTableModel extends AbstractTableModel{

	private String[] kolone = new String[]{"ID", "Name", "Last name", "Birth date"};
	private List<Poslanik> poslanici = new LinkedList<>();

	public PoslaniciTableModel(List<Poslanik> poslanici) {
		this.poslanici = poslanici;
	}
	
	@Override
	public int getRowCount() {
		return poslanici.size();
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Poslanik p = poslanici.get(rowIndex);
		switch (columnIndex) {
		case 0: 
			return p.getId();
		case 1: 
			return p.getIme();
		case 2: 
			return p.getPrezime();
		case 3: 
			return p.getDatumRodjenja();
		default:
			return "N/A";
		}
	}

	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Poslanik p = poslanici.get(rowIndex);
		switch (columnIndex) {
		case 0: break;

		case 1: p.setIme(aValue.toString());
		break;

		case 2: p.setPrezime(aValue.toString());
		break;

		case 3: try {
			p.setDatumRodjenja(new SimpleDateFormat("dd.MM.yyyy").parse(aValue.toString()));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Greska prilikom unosa datuma.", "Greska", JOptionPane.ERROR_MESSAGE);
		}
		break;

		default:
			break;
		}
	}
}
