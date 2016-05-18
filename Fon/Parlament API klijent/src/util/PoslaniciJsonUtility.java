package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Poslanik;

public class PoslaniciJsonUtility {
	public static LinkedList<Poslanik> konvertujPoslanike(JsonArray json) throws ParseException{
		LinkedList<Poslanik> poslanici = new LinkedList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		
		for (int i = 0; i < json.size(); i++) {
			JsonObject objJson = (JsonObject) json.get(i);
			
			Poslanik p = new Poslanik();
			if(objJson.get("id") != null)
				p.setId(objJson.get("id").getAsInt());
			if(objJson.get("name") != null)
				p.setIme(objJson.get("name").getAsString());
			if(objJson.get("lastName") != null)
				p.setPrezime(objJson.get("lastName").getAsString());
			if(objJson.get("birthDate") != null)
				p.setDatumRodjenja(sdf.parse(objJson.get("birthDate").getAsString()));
			
			poslanici.add(p);
		}
		return poslanici;
	}
}
