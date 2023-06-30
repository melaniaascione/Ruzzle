package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.ruzzle.db.DizionarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
	
	//importo nel modello la grandezza della scacchiera, le caratteristiche della scacchiera
	//(board), il dizionario che mi sono creata nella classe DAO, e le proprietà delle Stringhe
	private final int SIZE = 4;
	private Board board ;
	private List<String> dizionario ;
	private StringProperty statusText ;

	public Model() {
		this.statusText = new SimpleStringProperty() ;
		this.board = new Board(SIZE); //creo una nuova scacchiera della misura desiderata
		DizionarioDAO dao = new DizionarioDAO() ;
		this.dizionario = dao.listParola() ;
		statusText.set(String.format("%d parole lette", this.dizionario.size())) ;
	}
	
	public void reset() {
		this.board.reset() ;
		this.statusText.set("Board Reset");
	}

	public Board getBoard() {
		return this.board;
	}

	public final StringProperty statusTextProperty() {
		return this.statusText;
	}
	

	public final String getStatusText() {
		return this.statusTextProperty().get();
	}
	

	public final void setStatusText(final String statusText) {
		this.statusTextProperty().set(statusText);
	}

	public List<Pos> trovaParola(String parola){
		Ricorsione ricorsione = new Ricorsione();
		return ricorsione.trovaParola(parola, this.board);
	}
	
	public List<String> trovaTutte() {
		List<String> risultato = new ArrayList<String>();
		for(String parola : dizionario) {
			if(this.trovaParola(parola.toUpperCase())!=null) {
				risultato.add(parola);
			}
		}
		return risultato;
	}

}
