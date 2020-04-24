package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	//se parola è presente o meno nella matrice Ruzzle che sto usando
	public List<Pos> trovaParola(String parola, Board board) {//parola + matrice su cui sto cercando
		for(Pos p : board.getPositions()) {//la matrice contiene la prima lettera? si-->ricorsione
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				//inzio potenziale della parola, faccio ricorsione
				List<Pos> percorso = new ArrayList<Pos>(); //contiene delle cella della parola se l'ho trovata
				percorso.add(p); //la parziale contiene già la prima lettera
				if(cerca(parola, 1, percorso, board))
					return percorso ; 
			}
		}
		
		return null ;//non ho trovato nessun percorso
	}

	private boolean cerca(String parola, int livello, List<Pos> percorso, Board board) {
		//caso terminale
		
		if(livello == parola.length())
			return true;//trovato un percorso
		
		Pos ultima = percorso.get(percorso.size() -1);//ultima cosa che ho inserito
		List<Pos> adiacenti = board.getAdjacencies(ultima) ;//prendo le adiacenti
		for(Pos p : adiacenti) {
			if(!percorso.contains(p) && parola.charAt(livello) == 
					board.getCellValueProperty(p).get().charAt(0)) {
				//faccio ricorsione
				percorso.add(p);
				if(cerca(parola, livello+1, percorso, board)) 
					return true ; //uscita rapida in caso di soluzione, unica che mi interessa
				percorso.remove(percorso.size() -1) ;
			}
		}
		return false;
	}
}
