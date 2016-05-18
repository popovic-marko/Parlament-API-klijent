package gui;

import java.awt.EventQueue;
import java.util.List;

import domain.Poslanik;
import domain.Skupstina;

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
}
