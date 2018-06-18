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
	

	@Override
	public ArrayList<Tuile> deplacer2(){
		ArrayList<Tuile> listTuile = new ArrayList<Tuile>();
		
		for (Tuile t : Grille.tuilesListe.values()) {
			listTuile.add(t);
		}
		firstIter=true;
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