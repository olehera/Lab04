package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/*
	 *  Data la matricola di uno studente, ottengo uno studente
	 */
	public Studente getStudente(int mat) {
		
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		Studente s = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mat);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");
				
				s = new Studente(matricola, cognome, nome, cds);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return s;
	}

	/*
	 * Ottengo tutti i corsi seguiti da uno studente
	 */
	public List<Corso> getCorsiIscrittoStudente(int matricola) {
		CorsoDAO cors = new CorsoDAO();
        final String sql = "SELECT codins FROM iscrizione WHERE matricola = ?";
		
		List<Corso> lista = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");

				lista.add(cors.getCorsoCod(codins));
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lista;
	}

	public boolean getIscrizione(String codins, int matricola) {
        final String sql = "SELECT * FROM iscrizione WHERE matricola = ? AND codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);
			ResultSet rs = st.executeQuery();

			if (rs.next()) 
				return true;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return false;
	}

}
