package ille_intedite.Aventurie;

import ille_intedite.Grille;
import ille_intedite.Tuile;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import java.util.Map.Entry;

import Carte.NomTresor;
import utils.Utils;

public class Plongeur extends Aventurier {


	private ArrayList<Tuile> tuilesDep = new ArrayList<Tuile>();
	private boolean firstIter=true;

	
	public Plongeur(int Num, String nom, Utils.Pion pion) {
		super(Num, nom,pion);
	}	

	//fonction pour deplacer le plongeur
	@Override
	public ArrayList<Tuile> deplacer2(){
		ArrayList<Tuile> listTuile = new ArrayList<Tuile>();
		
		for (Tuile t : Grille.tuilesListe.values()) {
			listTuile.add(t);
		}
		firstIter=true;
		getAdjacent(this.getTuile(), listTuile);
		
		//on retire toutes les cases coulees
		Iterator<Tuile> it = Grille.tuilesListe.values().iterator();
		while(it.hasNext()) {
			Tuile t = it.next();
			if(t.getStatut()==2) {
				tuilesDep.remove(t);
			}
		}
		//on retire la case sur laquelle se trouve le plongeur
		tuilesDep.remove(this.getTuile());
		
		return tuilesDep;
	}

	//retourne toutes les cases ou peut se deplacer le plongeur, y comprise les cases coulees
	@Override
	public ArrayList<Tuile> getAdjacent(Tuile from, ArrayList<Tuile> listTuile){
		
		if (firstIter){
			tuilesDep = new ArrayList<Tuile>();
			firstIter=false;
		}

		if (from.getStatut()==0 && from!=this.getTuile()) {
			return null;
		}else {
			
			for(Tuile to : listTuile) {

				int x = Math.abs(from.getxT() - to.getxT());
				int y = Math.abs(from.getyT() - to.getyT());

				if (((x == 1 & y == 0) || (x == 0 & y == 1)) &to.getNum()!=-1){
					
					if (!tuilesDep.contains(to)) {
						tuilesDep.add(to);
						getAdjacent(to, listTuile);
					}
				}
			}

		}
		
		
		return tuilesDep;
		

	}

}
