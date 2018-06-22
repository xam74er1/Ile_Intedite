package ille_intedite.Aventurie;

import Carte.Carte;
import Carte.CarteTresor;
import Carte.NomTresor;
import ille_intedite.Tuile;
import utils.Utils;

public class Ingenieur extends Aventurier {
	
	private boolean derniereActionAssecher = false;

	public Ingenieur(int Num, String nom, Utils.Pion pion) {
        super(Num, nom,pion);
    }
    //pour la derniere action assecher (vu qu'il peut assecher deux fois)
    @Override
    public NomTresor recupereTresor() {
		// TODO - implement Aventurier.RecupereTresort
    	if(getDerniereActionAssecher()) {
			super.actionJouer();
			setDerniereActionAssecher(false);
		}

		int num = getTuile().getNum();
		int numTresor=0;
		if(num/100==3) {
			 numTresor = (num/10)%10;
			int nbr = 0;

			for(Carte c : listeCarteJoueur) {
				if(c instanceof CarteTresor) {
					CarteTresor ct = (CarteTresor) c;
					if(ct.getType().getNum()==num) {
						nbr ++;
					}
				}
			}

		}
		
		if(num>=4) {
		return NomTresor.getWisNum(numTresor);
		}
		return null;
		
	
	}

    
	public boolean getDerniereActionAssecher() {
		return derniereActionAssecher;
	}

	public void setDerniereActionAssecher(boolean derniereActionAssecher) {
		this.derniereActionAssecher = derniereActionAssecher;
	}
    
    
    
}