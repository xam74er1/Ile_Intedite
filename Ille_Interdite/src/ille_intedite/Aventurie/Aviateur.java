package ille_intedite.Aventurie;

import java.util.ArrayList;
import java.util.Iterator;

import Carte.NomTresor;
import ille_intedite.Tuile;
import utils.Utils.Pion;

public class Aviateur extends Aventurier {

    public Aviateur(int Num, String nom, Pion pion) {
        super(Num, nom,pion);
    }
    
    
    @Override
    public boolean deplacementPossible(Tuile from, Tuile to) {
		System.out.println("to = "+to+" from = "+from);
		int x = Math.abs(from.getxT() - to.getxT());
		int y = Math.abs(from.getyT() - to.getyT());
		// Si il ce deplace sois a 1 case en X ou 1 case en Y et que la case de destination n'as pas couler
		return ((to.getStatut() < 2)&to.getNum()!=-1);
	}
    
    @Override
    public ArrayList<Tuile> getAdjacent(Tuile from, ArrayList<Tuile> listTuile){
		ArrayList<Tuile> adjacent = new ArrayList<Tuile>();
		int xF=from.getxT();
		int yF=from.getyT();
		
		Iterator<Tuile> it = listTuile.iterator();
		
		while(it.hasNext()) {
			Tuile to = it.next();
			
			int x = Math.abs(xF-to.getxT());
			int y = Math.abs(yF-to.getyT());
			
			if (!(x==0 && y==0) && to.getStatut()!=2 && to.getNum()!=-1) {
				adjacent.add(to);
			}
			
		}
		
		return adjacent;
	}
}