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
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mat);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");
				
				conn.close();

				return new Studente(matricola, cognome, nome, cds);
			}
			
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
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
				
				conn.close();

				return lista;
			}
			
			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	

}
