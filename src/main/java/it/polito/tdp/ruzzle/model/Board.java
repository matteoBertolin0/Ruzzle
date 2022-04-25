package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Memorizza le lettere presenti nella scacchiera Ruzzle.
 * @author Fulvio
 *
 */
public class Board {
	private List<Pos> positions;
	private Map<Pos, StringProperty> cells;

	private int size;

	/**
	 * Crea una nuova scacchiera della dimensione specificata
	 * @param size
	 */
	public Board(int size) {
		this.size = size;

		//Definisco le "caselle" del gioco (e la forma del piano di gioco)
		this.positions = new ArrayList<>();
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				this.positions.add(new Pos(row, col));
			}
		}

		//Definisco il contenuto delle caselle
		this.cells = new HashMap<>();

		//Ogni casella conterrà una String Property, inizialmente vuota, per contenere il proprio carattere  
		for (Pos p : this.positions) {
			this.cells.put(p, new SimpleStringProperty());
		}
	}
	
	/**
	 * Fornisce la {@link StringProperty} corrispondente alla {@link Pos} specificata. <p>
	 * 
	 * Può essere usata per sapere che lettera è presente
	 * (es. {@code getCellValueProperty(p).get()}) oppure per fare un binding della proprietà stessa sulla mappa visuale.
	 * @param p
	 * @return
	 */
	public StringProperty getCellValueProperty(Pos p) {
		return this.cells.get(p) ;
	}

	/**
	 * Restituisce la lista di oggetti {@link  Pos} che corrispondono alle posizioni lecite sulla scacchiera. Gli elementi sono ordinati per righe.
	 * @return
	 */
	public List<Pos> getPositions() {
		return positions;
	}

	/**
	 * Crea una nuova scacchiera generando tutte lettere casuali
	 */
	public void reset() {
		for(Pos p: this.positions) {
			
			//TODO: migliorare l'assegnazione secondo la probabiltà di ogni lettetera di essere utilizzata nella lingua italiana
			int random = (int)(Math.random()*26) ;
			String letter = Character.toString((char)('A'+random)) ;
			
			//grazie al "binding" fatto in FXMLController, la "set" modifica direttamente il testo del botone collegato alla posizione corrente
			this.cells.get(p).set(letter); 
		}
		
		String search = "IEAZICTSRAONOLAR";
		
		this.cells.get(positions.get(0)).set(this.charToString(search.charAt(0)));
		this.cells.get(positions.get(1)).set(this.charToString(search.charAt(1)));
		this.cells.get(positions.get(2)).set(this.charToString(search.charAt(2)));
		this.cells.get(positions.get(3)).set(this.charToString(search.charAt(3)));
		
		this.cells.get(positions.get(4)).set(this.charToString(search.charAt(4)));
		this.cells.get(positions.get(5)).set(this.charToString(search.charAt(5)));
		this.cells.get(positions.get(6)).set(this.charToString(search.charAt(6)));
		this.cells.get(positions.get(7)).set(this.charToString(search.charAt(7)));
		
		this.cells.get(positions.get(8)).set(this.charToString(search.charAt(8)));
		this.cells.get(positions.get(9)).set(this.charToString(search.charAt(9)));
		this.cells.get(positions.get(10)).set(this.charToString(search.charAt(10)));
		this.cells.get(positions.get(11)).set(this.charToString(search.charAt(11)));

		this.cells.get(positions.get(12)).set(this.charToString(search.charAt(12)));
		this.cells.get(positions.get(13)).set(this.charToString(search.charAt(13)));
		this.cells.get(positions.get(14)).set(this.charToString(search.charAt(14)));
		this.cells.get(positions.get(15)).set(this.charToString(search.charAt(15)));
		
	}
	
	public String charToString(char c) {
		String stmp = "";
		stmp+=c;
		return stmp;
	}
	
	/**
	 * Data una posizione, restituisce tutte le posizioni adiacenti
	 * @param p
	 * @return
	 */
	public List<Pos> getAdjacencies(Pos p) {
		List<Pos> result = new ArrayList<>() ;
		
		for(int r = -1; r<=1; r++) {
			for(int c = -1; c<=1; c++) {
				// tutte le 9 posizioni nell'intorno della cella				
				if(r!=0 || c!=0) { // escludo la cella stessa (offset 0,0)
					Pos adj = new Pos(p.getRow()+r, p.getCol()+c) ;
					//controllo che gli indici non siano fuori dalla griglia
					if(positions.contains(adj)) {
						result.add(adj) ;
					}
				}
			}
		}
		
		return result ;
	}


	
}
