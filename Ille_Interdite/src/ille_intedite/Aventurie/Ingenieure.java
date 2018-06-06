package ille_intedite.Aventurie;

import Carte.NomTresor;
import ille_intedite.Tuile;
import utils.Utils;

public class Ingenieure extends Aventurier {

    public Ingenieure(int Num, String nom, Utils.Pion pion) {
        super(Num, nom,pion);
    }
    
    @Override
    public boolean assecher(Tuile t){

		if(assecherPossible(getTuile(), t)){
			t.assecher();
			return true;
		}
		return false;

	}

    @Override
	public boolean assecherPossible(Tuile from, Tuile to) {
		int x = Math.abs(from.getxT() - to.getxT());
		int y = Math.abs(from.getyT() - to.getyT());
		// Si il ce deplace sois a 1 case en X ou 1 case en Y et que la case de destination n'as pas couler
		return ((x == 1 & y == 0) || (x == 0 & y == 1)) || (x==0 & y==0) & (to.getStatue() ==1);
		
	}
}