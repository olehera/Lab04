package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	
	StudenteDAO s = new StudenteDAO();
	CorsoDAO c = new CorsoDAO();
	
	public Studente getS(int mat) {
		return s.getStudente(mat);
	}

	public List<String> getElencoCorsi() {
		List<Corso> l = c.getTuttiICorsi();
		List<String> n = new LinkedList<String>();
		n.add("");
		
		for (Corso temp : l)
			n.add(temp.getNome());
	
	    return n;
	}

	public String getIscrittiCorso(String str) {
		String stampa = "";
		Corso cors = c.getCorso(str);
		List<Studente> lista = c.getStudentiIscrittiAlCorso(cors);
		
		for (Studente temp : lista)
			stampa += temp.toString();
		
		if (stampa.compareTo("")==0)
			stampa = "Nessun Iscritto";
		
		return stampa;
	}

	public String getStudenteCorsi(int matricola) {
		String stampa = "";
		List<Corso> lista = s.getCorsiIscrittoStudente(matricola);
		
		for (Corso temp : lista)
			stampa += temp.toString();
		
		if (stampa.compareTo("")==0)
			stampa = "Nessun Corso";
		
		return stampa;
	}
	
	public boolean isIscritto(String nomeCorso, int matricola) {
		return s.getIscrizione(c.getCorso(nomeCorso).getCodins(),matricola);
	}
	
	public boolean iscrivi(String nomeCorso, int matricola) {
		return c.inscriviStudenteACorso(c.getCorso(nomeCorso).getCodins(), matricola);
	}
	
}
