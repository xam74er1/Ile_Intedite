package ille_intedite.Aventurie;

import ille_intedite.Tuile;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import Carte.NomTresor;
import utils.Utils;

public class Plongeur extends Aventurier {

	public Plongeur(int Num, String nom, Utils.Pion pion) {
		super(Num, nom,pion);
	}

	//@Override
	public ArrayList<Tuile> deplacementPossible(Tuile from, ArrayList<Tuile> listTuile) {

		ArrayList<Tuile> adjacent = new ArrayList<Tuile>();
		ArrayList<Tuile> newadjacent = adjacent;
		newadjacent.addAll(getAdjacent(from, listTuile));
		
		while (!newadjacent.equals(adjacent)) {
			adjacent= newadjacent;
			Iterator<Tuile> it = adjacent.iterator();
			while (it.hasNext()) {
				Tuile t = it.next();
				if (t.getStatue()!=0) {
					ArrayList<Tuile> prox = getAdjacent(t, listTuile);
					Iterator<Tuile> iP = prox.iterator();
					while (iP.hasNext()){
						Tuile tP = iP.next();
						if (!newadjacent.contains(tP)) {
							newadjacent.add(tP);
						}
						
					}
					
				}
				
			}
			
		}


		return null;
	}


}