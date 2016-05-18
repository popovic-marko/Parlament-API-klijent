package util;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Poslanik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParlamentApiKomunikacija {
	
	private static final String urlServisa = "http://147.91.128.71:9090/parlament/api/members";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	public static List<Poslanik> vratiPoslanike() throws IOException, ParseException{
		
		String rezultat = sendGet(urlServisa);
		Gson gson = new GsonBuilder().create();
		JsonArray jsonArray = gson.fromJson(rezultat, JsonArray.class);
		
		List<Poslanik> poslanici = new LinkedList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			
			JsonObject jsonObj = (JsonObject) jsonArray.get(i);
			Poslanik p = new Poslanik();
			if(jsonObj.get("id") != null)
				p.setId(jsonObj.get("id").getAsInt());
			if(jsonObj.get("name") != null)
				p.setIme(jsonObj.get("name").getAsString());
			if(jsonObj.get("lastName") != null)
				p.setPrezime(jsonObj.get("lastName").getAsString());
			if(jsonObj.get("birthDate") != null)
				p.setDatumRodjenja(sdf.parse(jsonObj.get("birthDate").getAsString()));
			if(jsonObj.get("birthPlace") != null)
				p.setMestoRodjenja(jsonObj.get("birthPlace").getAsString());
			
			poslanici.add(p);
			
		}
		return poslanici;
	}
	
	private static String sendGet(String stringUrl) throws IOException{
		
		URL url = new URL(stringUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
//		boolean kraj = false;
//		String odgovor = "";
//		while(!kraj){
//			String s = reader.readLine();
//			if(s != null){
//				odgovor += s;
//			}else{
//				kraj = true;
//			}
//		}
//		reader.close();
		String odgovor = "";
		String linija;
		while( (linija = reader.readLine()) != null){
			odgovor += linija;
		}
		return odgovor;
	}
}

