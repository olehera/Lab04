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
		
		return stampa;
	}

	public String getStudenteCorsi(int matricola) {
		String stampa = "";
		List<Corso> lista = s.getCorsiIscrittoStudente(matricola);
		
		for (Corso temp : lista)
			stampa += temp.toString();
		
		return stampa;
	}
	
	
}
