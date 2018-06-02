package ille_intedite;

import Carte.Carte;

public class CarteInondation extends Carte {

	Tuile tuile;

    public CarteInondation(String nom,Tuile tuile) {
        super(nom);
        this.tuile = tuile;
    }

	public Tuile getTuile() {
		return tuile;
	}

	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}

    
}