package ille_intedite.Aventurie;

import Carte.Carte;
import Carte.CarteTresor;
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
    	System.out.println(" wololo");
		if(assecherPossible(getTuile(), t)){
			System.out.println("derniereActionAssecher");
			if(!derniereActionAssecher) {
				System.out.println(" assecher ");
				actionAnuller();
			}
			t.assecher();
			
			derniereActionAssecher = !derniereActionAssecher;
			
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