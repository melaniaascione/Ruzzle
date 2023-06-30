package it.polito.tdp.ruzzle.db;

//CLASSE CHE MI SERVE PER CREARMI UN DIZIONARIO DI PAROLE ACCETTABILI

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DizionarioDAO {
	
	/**
	 * Ritorna una lista con tutte le parole presenti nel dizionario (database)
	 * @return
	 */
	public List<String> listParola() {
		
		List<String> result = new ArrayList<>() ;
		
		String query = "SELECT nome FROM parola ORDER BY nome" ;
		
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(query) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add(res.getString("nome")) ;
			}
			
			res.close();
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result ;

	}

}
