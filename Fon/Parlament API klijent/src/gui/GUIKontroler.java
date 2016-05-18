package gui;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import domain.Poslanik;
import domain.Skupstina;
import models.PoslaniciTableModel;
import util.ParlamentApiKomunikacija;
import util.PoslaniciJsonUtility;

public class GUIKontroler {

	private static Skupstina skupstina;
	private static PoslaniciGUI poslaniciGUI;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					skupstina = new Skupstina();
					poslaniciGUI = new PoslaniciGUI();
					poslaniciGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public static List<Poslanik> vratiSvePoslanike(){
		return skupstina.vratiSvePoslanike();
	}


	public static void preuzmiPoslanike(JTextArea taStatus) throws IOException, ParseException {
		List<Poslanik> poslanici = ParlamentApiKomunikacija.vratiPoslanike();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		FileWriter out = new FileWriter("data/serviceMembers.json");

		out.write(gson.toJson(poslanici));

		out.close();
		
		taStatus.append("Poslanici su preuzeti sa servisa. \n");
	}


	public static void ucitajPoslanikeIzJson(JTable tabela, JTextArea textArea) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("data/serviceMembers.json"));
		
		Gson gson = new GsonBuilder().create();
		
		JsonArray jsonArray = gson.fromJson(in, JsonArray.class);
		
		in.close();
		
		LinkedList<Poslanik> listaPoslanika = PoslaniciJsonUtility.konvertujPoslanike(jsonArray);
		
		PoslaniciTableModel model = (PoslaniciTableModel) tabela.getModel();
		model.staviPoslanikeUModel(listaPoslanika);
		
		textArea.append("Tabela je popunjena podacima preuzetim sa servisa. \n");
	}


	public static void sacuvajIzmenjenePoslanike(JTable tabela, JTextArea taStatus) throws IOException {
		PoslaniciTableModel model = (PoslaniciTableModel) tabela.getModel();
		LinkedList<Poslanik> poslanici = model.vratiPoslanike();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		BufferedWriter out = new BufferedWriter(new FileWriter("data/updatedMembers.json"));
		
		out.write(gson.toJson(poslanici));
		
		out.close();
		
		taStatus.append("Izmenjeni podaci su sacuvani. \n");
	}
	

}
