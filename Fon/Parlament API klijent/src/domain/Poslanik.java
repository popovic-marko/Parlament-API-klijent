package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Poslanik {
	@SerializedName("id")
	private int id;
	
	@SerializedName("name")
	private String ime;
	
	@SerializedName("lastName")
	private String prezime;
	
	@SerializedName("birthDate")
	private Date datumRodjenja;
	
	@SerializedName("birthPlace")
	private String mestoRodjenja;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime){
		this.ime = ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	public String getMestoRodjenja() {
		return mestoRodjenja;
	}

	public void setMestoRodjenja(String mestoRodjenja) {
		this.mestoRodjenja = mestoRodjenja;
	}

	@Override
	public String toString() {
		return "Poslanik [id: " + id + ", ime: " + ime + ", prezime: " + prezime + ", datumRodjenja: " + new SimpleDateFormat().format(datumRodjenja)
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Poslanik){
			Poslanik p = (Poslanik) obj;
			if(ime.equals(p.getIme()) && prezime.equals(p.getPrezime()) && (datumRodjenja.compareTo(p.getDatumRodjenja()) != 0)
					&& mestoRodjenja.equals(p.getMestoRodjenja() )){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
