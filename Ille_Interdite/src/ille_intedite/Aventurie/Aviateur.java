package ille_intedite.Aventurie;

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
		return ((to.getStatue() < 2)&to.getNum()!=-1);
	}
}