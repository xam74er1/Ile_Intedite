package ille_intedite.Aventurie;

import java.util.ArrayList;
import java.util.Iterator;

import Carte.NomTresor;
import ille_intedite.Tuile;
import utils.Utils.Pion;

public class Aviateur extends Aventurier {

	private boolean tp=false;
	private Tuile tFrom;

	public Aviateur(int Num, String nom, Pion pion) {
		super(Num, nom,pion);
	}

	//deplacement de l'aviateur
	@Override
	public ArrayList<Tuile> getAdjacent(Tuile from, ArrayList<Tuile> listTuile){
		tFrom=from;
		ArrayList<Tuile> adjacent = new ArrayList<Tuile>();

		int xF=from.getxT();
		int yF=from.getyT();

		Iterator<Tuile> it = listTuile.iterator();

		while(it.hasNext()) {
			Tuile to = it.next();

			int x = Math.abs(xF-to.getxT());
			int y = Math.abs(yF-to.getyT());
			if(!tp) {
				if (!(x==0 && y==0) && to.getStatut()!=2 && to.getNum()!=-1) {
					adjacent.add(to);
				}
			}else {
				if ((x==0 && y==1 || x==1 && y==0) && to.getStatut()!=2 && to.getNum()!=-1) {
					adjacent.add(to);
				}
			}

		}
		return adjacent;
	}

	@Override
	public void finTour() {
		setNbAction(3);
		tp=false;
	}
	
	public Tuile getFrom() {
		return tFrom;
	}
	
	public void tp() {
		tp=true;
	}
}