package ille_intedite.Aventurie;

import ille_intedite.Grille;
import ille_intedite.Tuile;
import ille_intedite.Aventurie.Aventurier;
import java.util.*;
import java.util.Map.Entry;

import Carte.NomTresor;
import utils.Utils;

public class Plongeur extends Aventurier {

	public Plongeur(int Num, String nom, Utils.Pion pion) {
		super(Num, nom,pion);
	}
	
	//Pas encore implementee
	//@Override
	public ArrayList<Tuile> deplacementPossible(Tuile from, ArrayList<Tuile> listTuile) {
		//creation de la liste des tuiles adjacentes et d'une copie
		ArrayList<Tuile> adjacent = new ArrayList<Tuile>();
		ArrayList<Tuile> newadjacent = adjacent;
		//ajout des tuiles adjacentes a la tuile de depart
		Iterator<Tuile> iAdj = getAdjacent(from, listTuile).iterator();
		while (iAdj.hasNext()) {
			newadjacent.add(iAdj.next());
		}
		//tant que la copie et l'originale ne sont pas identiques on recommence
		while (!(newadjacent.equals(adjacent))) {
			//System.out.println(newadjacent.toString());
			adjacent= newadjacent;
			Iterator<Tuile> it = adjacent.iterator();
			//on regarde toutes les tuiles adjacentes a celles se trouvant dans Adjacent
			while (it.hasNext()) {
				Tuile t = it.next();
				//seulement si les tuiles de depart sont inondees ou coulees
				if (t.getStatue()!=0) {
					ArrayList<Tuile> prox = getAdjacent(t, listTuile);
					Iterator<Tuile> iP = prox.iterator();
					//on rajoute les tuiles dans la copie si celles-ci n'y sont pas deja
					while (iP.hasNext()){
						Tuile tP = iP.next();
						if (!newadjacent.contains(tP)) {
							newadjacent.add(tP);
						}
						
					}
					
				}
				
			}
			
		}
		//on retire la case ou se trouve le plongeur ainsi que toutes les cases coulees, car on ne peut pas s'y arreter
		Iterator<Tuile> it = adjacent.iterator();
		while (it.hasNext()) {
			Tuile t = it.next();
			if (t.equals(from)||t.getStatue()==2) {
				adjacent.remove(t);
			}
		}

		return adjacent;
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
	
	
	
	
	


}