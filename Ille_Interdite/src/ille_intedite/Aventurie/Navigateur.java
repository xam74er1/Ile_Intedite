package ille_intedite.Aventurie;

import utils.Utils.Pion;

public class Navigateur extends Aventurier {

	public Navigateur(int Num, String nom, Pion pion) {
		super(Num, nom, pion);
		this.setNbAction(4);
	}
	//le navigateur a 4 actions (et non 3)
	@Override
	public void finTour() {
		this.setNbAction(4);
	}

}
