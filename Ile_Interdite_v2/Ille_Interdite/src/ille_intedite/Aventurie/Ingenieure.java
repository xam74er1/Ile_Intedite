package ille_intedite.Aventurie;

import Carte.NomTresor;
import ille_intedite.Tuile;
import utils.Utils;

public class Ingenieure extends Aventurier {
	
	private boolean derniereActionAssecher = false;

	public Ingenieure(int Num, String nom, Utils.Pion pion) {
        super(Num, nom,pion);
    }
    
    @Override
    public boolean assecher(Tuile t){

		if(assecherPossible(getTuile(), t)){
			if(!getDerniereActionAssecher()) {
				super.actionAnuller();
			}
			t.assecher();
			setDerniereActionAssecher(!getDerniereActionAssecher());
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
    
    @Override
    public boolean deplacementPossible(Tuile from, Tuile to) {
		System.out.println("to = "+to+" from = "+from);
		int x = Math.abs(from.getxT() - to.getxT());
		int y = Math.abs(from.getyT() - to.getyT());
		// Si il ce deplace sois a 1 case en X ou 1 case en Y et que la case de destination n'as pas couler
		return (((x == 1 & y == 0) || (x == 0 & y == 1)) & (to.getStatue() < 2)&to.getNum()!=-1)&(super.getNbAction()>1 & !getDerniereActionAssecher());
	}
    
    @Override
    public void donneCarteJoueur(Aventurier joueur) {
		// TODO - implement Aventurier.donneCarteJoeur
    	if(getDerniereActionAssecher()) {
			super.actionJouer();
			setDerniereActionAssecher(false);
		}
		throw new UnsupportedOperationException();
	}
    
    @Override
    public void recupereTresor() {
		// TODO - implement Aventurier.RecupereTresort
    	if(getDerniereActionAssecher()) {
			super.actionJouer();
			setDerniereActionAssecher(false);
		}
		throw new UnsupportedOperationException();
	}

    
	private boolean getDerniereActionAssecher() {
		return derniereActionAssecher;
	}

	private void setDerniereActionAssecher(boolean derniereActionAssecher) {
		this.derniereActionAssecher = derniereActionAssecher;
	}
    
    
    
}