package domain;

import java.util.LinkedList;
import java.util.List;

public class Skupstina {

	private List<Poslanik> poslanici = new LinkedList<>();
	
	public List<Poslanik> vratiSvePoslanike(){
		return poslanici;
	}
}
