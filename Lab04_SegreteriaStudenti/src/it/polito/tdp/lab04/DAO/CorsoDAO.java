package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo una lista di tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return corsi;
	}

	/*
	 * Dato il nome di un corso, ottengo il corso
	 */
	public Corso getCorso(String nomeCorso) {
		
		final String sql = "SELECT * FROM corso WHERE nome = ?";
		Corso c = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeCorso);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String codins = rs.getString("codins");
			    int numeroCrediti = rs.getInt("crediti");
			    String nome = rs.getString("nome");
			    int periodoDidattico = rs.getInt("pd");
			
			    c = new Corso(codins, numeroCrediti, nome, periodoDidattico); 
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return c;
	}
	
	/*
	 * Dato il codice insegnamento di un corso, ottengo il corso
	 */
	public Corso getCorsoCod(String cod) {
		
		final String sql = "SELECT * FROM corso WHERE codins = ?";
		Corso c = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, cod);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
			}
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return c;
	}

	/*
	 * Ottengo una lista di tutti gli studenti iscritti al Corso passato come parametro
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		StudenteDAO stud = new StudenteDAO();
		final String sql = "SELECT matricola FROM iscrizione WHERE codins = ?";
		
		List<Studente> lista = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int matricola = rs.getInt("matricola");

				lista.add(stud.getStudente(matricola));
			}
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lista;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso
	 */
	public boolean inscriviStudenteACorso(String codins, int matricola) {
		StudenteDAO stud = new StudenteDAO();
        final String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?,?)";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);
			st.execute();          // update
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return stud.getIscrizione(codins, matricola);
	}
}
