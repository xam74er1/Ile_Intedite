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

	//Pas encore implementee
	//@Override
	public ArrayList<Tuile> deplacementPossible(Tuile from, ArrayList<Tuile> listTuile) {



		return null;
	}

	@Override
	public boolean deplacementPossible(Tuile from, Tuile to) {

		ArrayList<Tuile> listTuile = new ArrayList<Tuile>();
		Iterator<Tuile> it = Grille.tuilesListe.values().iterator();
		while(it.hasNext()) {
			listTuile.add(it.next());
		}

		ArrayList<Tuile> listdep = deplacementPossible(from, listTuile);

		//return listdep.contains(to);
		return listdep.contains(to);
	}
	
	@Override
	public ArrayList<Tuile> deplacer2(){
		ArrayList<Tuile> listTuile = new ArrayList<Tuile>();
		
		for (Tuile t : Grille.tuilesListe.values()) {
			listTuile.add(t);
		}
		
		return getAdjacent(this.getTuile(), listTuile);
	}

	@Override
	public ArrayList<Tuile> getAdjacent(Tuile from, ArrayList<Tuile> listTuile){
		
		if (firstIter){
			tuilesDep = new ArrayList<Tuile>();
			firstIter=false;;
		}

		if (from.getStatue()==0 && from!=this.getTuile()) {
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
		
		tuilesDep.remove(this.getTuile());
		for (Tuile t : tuilesDep) {
			if (t.getStatue()==2) {
				tuilesDep.remove(t);
			}
		}
		return tuilesDep;

	}

}